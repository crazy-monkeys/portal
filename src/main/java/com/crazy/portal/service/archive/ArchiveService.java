package com.crazy.portal.service.archive;

import com.crazy.portal.bean.customer.basic.FileVO;
import com.crazy.portal.dao.archive.ArchiveMapper;
import com.crazy.portal.dao.archive.ArchiveUserRelationMapper;
import com.crazy.portal.entity.archive.Archive;
import com.crazy.portal.entity.archive.ArchiveUserRelation;
import com.crazy.portal.util.BusinessUtil;
import com.crazy.portal.util.FileUtil;
import com.crazy.portal.util.PortalUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

import static com.crazy.portal.util.ErrorCodes.BusinessEnum.*;

/**
 * Created by lee on 2019/6/4.
 */

@Slf4j
@Service
public class ArchiveService {

    @Resource
    private ArchiveMapper archiveMapper;
    @Resource
    private ArchiveUserRelationMapper archiveUserRelationMapper;

    @Value("${file.path.archive}")
    private String filePath;

    /**
     * 文档分页列表
     * @param fileName 文档名称
     * @param pageNum   页码
     * @param pageSize  每页数量
     * @return
     */
    public PageInfo<Archive> getListByPage(String fileName, Integer typeId, Integer isRole,
                                           Integer pageNum, Integer pageSize, Integer userId) {
        PortalUtil.defaultStartPage(pageNum, pageSize);
        Page<Archive> result;
        if(null != isRole && isRole == 1){
            result = archiveMapper.selectPageListDataByRole(fileName, typeId, userId);
        }else{
            result = archiveMapper.selectPageListData(fileName, typeId, userId);
        }
        return new PageInfo<>(result);
    }

    /**
     * 单个文档记录获取
     * @param id    文档ID
     * @return
     */
    public Archive getDetailInfoById(Integer id) {
        Archive dbRecord = archiveMapper.selectByPrimaryKey(id);
        BusinessUtil.notNull(dbRecord, ARCHIVE_DB_RECORD_NOT_FOUND);
        dbRecord.setRelations(archiveUserRelationMapper.selectByArchiveId(id));
        return dbRecord;
    }

    /**
     * 文档发布及修改
     * @param param 文档参数
     * @param releaseUserId 操作用户ID
     */
    @Transactional
    public void authorizeArchiveToUser(Archive param, Integer releaseUserId) {
        Archive dbRecord = archiveMapper.selectByPrimaryKey(param.getId());
        BusinessUtil.notNull(dbRecord, ARCHIVE_DB_RECORD_NOT_FOUND);
        dbRecord.setReleaseFileName(param.getReleaseFileName());
        dbRecord.setReleaseUserId(releaseUserId);
        dbRecord.setReleaseTime(new Date());
        dbRecord.setTypeId(param.getTypeId());
        archiveMapper.updateByPrimaryKeySelective(dbRecord);
        //防止重复记录及不必要的关联关系，维护前先物理清理掉之前的记录
        archiveUserRelationMapper.deleteByArchiveId(param.getId());
        for(Integer authUserId : param.getUserIds()){
            ArchiveUserRelation relation = new ArchiveUserRelation();
            relation.setArchiveId(param.getId());
            relation.setUserId(authUserId);
            archiveUserRelationMapper.insertSelective(relation);
        }
    }

    /**
     * 撤销已发布文档
     * @param id    文档ID
     * @param userId    操作用户ID
     */
    public void revokeById(Integer id, Integer userId) {
        Archive dbRecord = archiveMapper.selectByPrimaryKey(id);
        BusinessUtil.notNull(dbRecord, ARCHIVE_DB_RECORD_NOT_FOUND);
        BusinessUtil.assertFlase((dbRecord.getStatus() == 1), ARCHIVE_DB_RECORD_STATUS_ERROR);
        dbRecord.setStatus(-1);
        archiveMapper.updateByPrimaryKeySelective(dbRecord);
    }

    public Archive pushFile(MultipartFile file) {
        BusinessUtil.notNull(file, ARCHIVE_FILE_NOT_EMPTY);
        FileVO fileVO = FileUtil.upload(file, filePath);
        Archive archive = new Archive();
        archive.setThirdFileName(fileVO.getFileName());
        archive.setStatus(0);
        archive.setFileStoragePath(fileVO.getFullPath());
        archive.setActive(1);
        archive.setCreateTime(new Date());
        archiveMapper.insertSelective(archive);
        return archive;
    }

    public void downloadFile(HttpServletResponse response, Integer id) {
        Archive dbRecord = archiveMapper.selectByPrimaryKey(id);
        BusinessUtil.notNull(dbRecord, ARCHIVE_DB_RECORD_NOT_FOUND);
        FileUtil.download(response, filePath, dbRecord.getThirdFileName());
    }

}
