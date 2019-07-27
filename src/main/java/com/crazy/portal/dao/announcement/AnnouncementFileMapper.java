package com.crazy.portal.dao.announcement;

import com.crazy.portal.entity.announcement.AnnouncementFile;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AnnouncementFileMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AnnouncementFile record);

    int insertSelective(AnnouncementFile record);

    AnnouncementFile selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AnnouncementFile record);

    int updateByPrimaryKey(AnnouncementFile record);

    List<AnnouncementFile> selectByAnnouncementId(@Param(value = "announcementId") Integer announcementId);

    List<AnnouncementFile> selectByIds(@Param(value = "ids") List<Integer> ids);
}