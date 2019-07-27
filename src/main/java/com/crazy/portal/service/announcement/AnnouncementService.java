package com.crazy.portal.service.announcement;

import com.alibaba.fastjson.JSONObject;
import com.crazy.portal.bean.customer.basic.FileVO;
import com.crazy.portal.dao.announcement.AnnouncementDOMapper;
import com.crazy.portal.dao.announcement.AnnouncementFileMapper;
import com.crazy.portal.entity.announcement.Announcement;
import com.crazy.portal.entity.announcement.AnnouncementFile;
import com.crazy.portal.util.BusinessUtil;
import com.crazy.portal.util.DateUtil;
import com.crazy.portal.util.FileUtil;
import com.crazy.portal.util.PortalUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.crazy.portal.util.ErrorCodes.BusinessEnum.*;

/**
 * Created by lee on 2019/6/4.
 */

@Slf4j
@Service
public class AnnouncementService {

    @Value("${file.path.announcement.local}")
    private String filePath;
    @Resource
    private AnnouncementDOMapper announcementDOMapper;
    @Resource
    private AnnouncementFileMapper announcementFileMapper;

    /**
     * 分页获取公告列表
     * @param title
     * @param releaseStartTime
     * @param releaseEndTime
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<Announcement> getPageListByTitleOrTime(String title, String releaseStartTime, String releaseEndTime,
                                                           Integer pageNum, Integer pageSize, Integer typeId){
        PortalUtil.defaultStartPage(pageNum,pageSize);
        Page<Announcement> result = announcementDOMapper.selectPageListData(title, releaseStartTime, releaseEndTime, typeId);
        return new PageInfo<>(result);
    }

    /**
     * 获取单条公告详细记录
     * @param id 公告ID
     * @return
     */
    @Deprecated
    public Announcement previewById(Integer id) {
        Announcement announcement = announcementDOMapper.selectByPrimaryKey(id);
        Assert.notNull(announcement, "No relevant record information was found by id");
        announcement.setFileList(announcementFileMapper.selectByAnnouncementId(announcement.getId()));
        return announcement;
    }

    /**
     * 公告编辑（新增/修改）
     * @param reqRecord 公告信息
     * @param userId 操作用户ID
     */
    @Transactional
    public Integer editByInfo(Announcement reqRecord, Integer userId) {
        Announcement dbRecord = announcementDOMapper.selectByPrimaryKey(reqRecord.getId());
        if(log.isDebugEnabled()){
            log.debug("No relevant record information was found by id, {}", reqRecord.getId());
        }
        boolean isExists = null == dbRecord;
        log.info("Edit announcement information, save operation:{}", isExists);
        if(log.isDebugEnabled()){
            log.debug("Announcement parameter information:{}", JSONObject.toJSONString(reqRecord));
        }
        int num = isExists ? saveAnnouncementInfo(reqRecord, userId) : updateAnnouncementInfo(dbRecord, reqRecord, userId);
        editFile(reqRecord.getFileList(), reqRecord.getId());
        log.info("Announcement information edit completion, num:{}", num);
        return isExists ? reqRecord.getId() : dbRecord.getId();
    }

    /**
     * 发布公告，只能发布未发布且未撤销的公告
     * @param id    公告ID
     * @param userId    操作用户ID
     */
    @Deprecated
    @Transactional
    public void releaseById(Integer id, Integer userId) {
        Announcement dbRecord = announcementDOMapper.selectByPrimaryKey(id);
        Assert.notNull(dbRecord, "No relevant record information was found by id");
        Assert.isTrue((dbRecord.getCreateUserId() == userId), "Do not operate other announcement information");
        Assert.isTrue((0 == dbRecord.getStatus()), "Announcement has been revoked or issued");
        dbRecord.setStatus(1);
        dbRecord.setReleaseTime(DateUtil.getCurrentTS());
        announcementDOMapper.updateByPrimaryKeySelective(dbRecord);
    }

    /**
     * 公告撤销，只能撤销处于'已发布'状态的公告记录
     * @param id    公告ID
     * @param userId    操作用户ID
     */
    @Transactional
    public void revokeById(Integer id, Integer userId) {
        Announcement dbRecord = announcementDOMapper.selectByPrimaryKey(id);
        Assert.notNull(dbRecord, "No relevant record information was found by id");
        Assert.isTrue((dbRecord.getCreateUserId() == userId), "Do not operate other announcement information");
        Assert.isTrue((1 == dbRecord.getStatus()), "Announcement has been revoked or not issued");
        dbRecord.setStatus(-1);
        announcementDOMapper.updateByPrimaryKeySelective(dbRecord);
    }

    /**
     * 保存公告文件
     * @param files
     * @return
     */
    public List<AnnouncementFile> pushFile(MultipartFile[] files) {
        BusinessUtil.assertFlase(files.length > 1, ANNOUNCEMENT_FILE_SIZE_ERROR);
        List<FileVO> result = FileUtil.upload(files, filePath);
        List<Integer> keys = new ArrayList<>();
        for(FileVO fileVO : result){
            AnnouncementFile file = new AnnouncementFile();
            file.setFileName(fileVO.getFileName());
            file.setFileStoragePath(fileVO.getFilePath());
            announcementFileMapper.insertSelective(file);
            keys.add(file.getId());
        }
        return announcementFileMapper.selectByIds(keys);
    }

    /**
     * 获取公告文件访问的url
     * @param id
     * @return
     */
    public String getFileUrl(Integer id) {
        List<AnnouncementFile> result = announcementFileMapper.selectByAnnouncementId(id);
        BusinessUtil.assertFlase(null == result, ANNOUNCEMENT_FILE_NOT_FOUND_BY_ID);
        BusinessUtil.assertFlase(result.size() != 1, ANNOUNCEMENT_FILE_ERROR_BY_ID);
        AnnouncementFile announcementFile = result.get(0);
//        return String.format("%s%s", announcementFile.getFileStoragePath(), announcementFile.getFileName());
        return announcementFile.getFileName();
    }

    private int saveAnnouncementInfo(Announcement reqRecord, Integer userId){
        boolean condition = null == reqRecord.getFileList() || reqRecord.getFileList().size() == 0;
        BusinessUtil.assertFlase(condition, ANNOUNCEMENT_FILE_PARAM_EMPTY);
        reqRecord.setCreateTime(DateUtil.getCurrentTS());
        reqRecord.setCreateUserId(userId);
        //初始值为'已发布'
        reqRecord.setStatus(1);
        reqRecord.setReleaseTime(new Date());
        return announcementDOMapper.insertSelective(reqRecord);
    }

    private int updateAnnouncementInfo(Announcement dbRecord, Announcement reqRecord, Integer userId){
        /*boolean condition = dbRecord.getCreateUserId() == userId;
        Assert.isTrue(condition, "Do not operate other announcement information");
        dbRecord.setTitle(reqRecord.getTitle());
//        dbRecord.setContent(reqRecord.getContent());
//        dbRecord.setTopmost(reqRecord.getTopmost());
        dbRecord.setUpdateTime(DateUtil.getCurrentTS());
        dbRecord.setTypeId(reqRecord.getTypeId());
        return announcementDOMapper.updateByPrimaryKeySelective(dbRecord);*/
        return 0;
    }

    private void editFile(List<AnnouncementFile> fileList, Integer announcementId) {
        for(AnnouncementFile file : fileList) {
            BusinessUtil.assertFlase(file.getId() == null, ANNOUNCEMENT_FILE_LIST_PARAM_EMPTY);
            AnnouncementFile dbRecord = announcementFileMapper.selectByPrimaryKey(file.getId());
            BusinessUtil.assertFlase(dbRecord == null, ANNOUNCEMENT_FILE_LIST_PARAM_EMPTY);
            dbRecord.setAnnouncementId(announcementId);
            announcementFileMapper.updateByPrimaryKeySelective(dbRecord);
        }
    }

}
