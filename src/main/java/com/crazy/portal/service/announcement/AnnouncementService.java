package com.crazy.portal.service.announcement;

import com.crazy.portal.dao.announcement.AnnouncementDOMapper;
import com.crazy.portal.entity.announcement.AnnouncementDO;
import com.crazy.portal.util.PortalUtil;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lee on 2019/6/4.
 */

@Slf4j
@Service
public class AnnouncementService {

    @Resource
    private AnnouncementDOMapper announcementDOMapper;

    public PageInfo<AnnouncementDO> getPageListByTitleOrTime(String title, String releaseStartTime, String releaseEndTime,
                                                             Integer pageNum, Integer pageSize){
        PortalUtil.defaultStartPage(pageNum,pageSize);
        List<AnnouncementDO> result = announcementDOMapper.selectPageListData(title, releaseStartTime, releaseEndTime);
        return new PageInfo<>(result);
    }

}
