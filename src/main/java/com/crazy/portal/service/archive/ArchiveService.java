package com.crazy.portal.service.archive;

import com.crazy.portal.dao.archive.ArchiveMapper;
import com.crazy.portal.dao.archive.ArchiveUserRelationMapper;
import com.crazy.portal.entity.archive.Archive;
import com.crazy.portal.entity.archive.ArchiveUserRelation;
import com.crazy.portal.util.DateUtil;
import com.crazy.portal.util.PortalUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import javax.annotation.Resource;
import java.util.List;

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

    /**
     * 文档分页列表
     * @param thirdFileName 文档名称
     * @param version   版本
     * @param pageNum   页码
     * @param pageSize  每页数量
     * @return
     */
    public PageInfo<Archive> getListByPage(String thirdFileName, String version, Integer pageNum, Integer pageSize) {
        PortalUtil.defaultStartPage(pageNum, pageSize);
        Page<Archive> result = archiveMapper.selectPageListData(thirdFileName, version);
        return new PageInfo<>(result);
    }

    /**
     * 单个文档记录获取
     * @param id    文档ID
     * @return
     */
    public Archive getDetailInfoById(Integer id) {
        Archive dbRecord = archiveMapper.selectByPrimaryKey(id);
        Assert.notNull(dbRecord, "No relevant record information was found by id");
        dbRecord.setRelations(archiveUserRelationMapper.selectByArchiveId(id));
        return dbRecord;
    }

    /**
     * 文档发布及修改
     * @param param 文档参数
     * @param userId 操作用户ID
     */
    @Transactional
    public void authorizeArchiveToUser(Archive param, Integer userId) {
        Archive dbRecord = archiveMapper.selectByPrimaryKey(param.getId());
        Assert.notNull(dbRecord, "No relevant record information was found by id");
        //不允许对已撤销的记录进行变更
        Assert.isTrue(!(dbRecord.getStatus() == -1), "Archive has been revoked");
        //需要发布的记录信息
        Archive releaseRecord = new Archive();
        releaseRecord.setId(param.getId());
        String releaseFileName = StringUtils.isEmpty(param.getReleaseFileName()) ? dbRecord.getThirdFileName() : param.getReleaseFileName();
        releaseRecord.setReleaseFileName(releaseFileName);
        releaseRecord.setReleaseIntroduction(param.getReleaseIntroduction());
        releaseRecord.setOperationalMode(param.getOperationalMode());
        releaseRecord.setUpdateTime(DateUtil.getCurrentTS());
        if(dbRecord.getStatus() == 0){
            releaseRecord.setReleaseUserId(dbRecord.getReleaseUserId() == null ? userId : dbRecord.getReleaseUserId());
            releaseRecord.setStatus(1);
            releaseRecord.setReleaseTime(DateUtil.getCurrentTS());
        }
        archiveMapper.updateByPrimaryKeySelective(releaseRecord);
        //防止重复记录及不必要的关联关系，维护前先物理清理掉之前的记录
        archiveUserRelationMapper.deleteByArchiveId(param.getId());
        //维护文档与授权用户关系记录
        List<ArchiveUserRelation> relations = param.getRelations();
        for(ArchiveUserRelation relation : relations){
            relation.setArchiveId(param.getId());
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
        Assert.notNull(dbRecord, "No relevant record information was found by id");
        Assert.isTrue((dbRecord.getStatus() == 1), "Archive has been revoked or not issued");
        dbRecord.setStatus(-1);
        archiveMapper.updateByPrimaryKeySelective(dbRecord);
    }


}
