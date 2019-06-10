package com.crazy.portal.dao.archive;

import com.crazy.portal.entity.archive.ArchiveUserRelation;
import com.crazy.portal.entity.archive.ArchiveUserRelationKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArchiveUserRelationMapper {
    int deleteByPrimaryKey(ArchiveUserRelationKey key);

    int insert(ArchiveUserRelation record);

    int insertSelective(ArchiveUserRelation record);

    ArchiveUserRelation selectByPrimaryKey(ArchiveUserRelationKey key);

    int updateByPrimaryKeySelective(ArchiveUserRelation record);

    int updateByPrimaryKey(ArchiveUserRelation record);

    int deleteByArchiveId(@Param(value = "archiveId") Integer archiveId);

    List<ArchiveUserRelation> selectByArchiveId(@Param(value = "archiveId") Integer archiveId);
}