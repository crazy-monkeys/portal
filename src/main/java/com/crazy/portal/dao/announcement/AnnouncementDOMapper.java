package com.crazy.portal.dao.announcement;

import com.crazy.portal.entity.announcement.Announcement;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AnnouncementDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Announcement record);

    int insertSelective(Announcement record);

    Announcement selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Announcement record);

    int updateByPrimaryKeyWithBLOBs(Announcement record);

    int updateByPrimaryKey(Announcement record);

    Page<Announcement> selectPageListData(@Param(value = "title") String title,
                                          @Param(value = "releaseStartTime") String releaseStartTime,
                                          @Param(value = "releaseEndTime") String releaseEndTime,
                                          @Param(value = "typeId") Integer typeId);

}