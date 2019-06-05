package com.crazy.portal.dao.announcement;

import com.crazy.portal.entity.announcement.AnnouncementDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AnnouncementDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AnnouncementDO record);

    int insertSelective(AnnouncementDO record);

    AnnouncementDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AnnouncementDO record);

    int updateByPrimaryKeyWithBLOBs(AnnouncementDO record);

    int updateByPrimaryKey(AnnouncementDO record);

    List<AnnouncementDO> selectPageListData(@Param(value = "title") String title,
                                            @Param(value = "releaseStartTime") String releaseStartTime,
                                            @Param(value = "releaseEndTime") String releaseEndTime);
}