package com.crazy.portal.dao.archive;

import com.crazy.portal.entity.archive.Archive;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

public interface ArchiveMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Archive record);

    int insertSelective(Archive record);

    Archive selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Archive record);

    int updateByPrimaryKey(Archive record);

    Page<Archive> selectPageListData(@Param(value = "thirdFileName") String fileName,
                                     @Param(value = "typeId") Integer typeId,
                                     @Param(value = "userId") Integer userId);

    Page<Archive> selectPageListDataByRole(@Param(value = "thirdFileName") String fileName,
                                          @Param(value = "typeId") Integer typeId,
                                          @Param(value = "userId") Integer userId);
}