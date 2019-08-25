package com.crazy.portal.service.group;

import com.crazy.portal.dao.group.SalesGroupMapper;
import com.crazy.portal.dao.group.SalesGroupOfficeMapper;
import com.crazy.portal.entity.group.SalesGroup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: SalesGroupService
 * @Author: God Man Qiu~
 * @Date: 2019/8/25 13:30
 */
@Slf4j
@Service
public class SalesGroupService {
    @Resource
    private SalesGroupMapper salesGroupMapper;
    @Resource
    private SalesGroupOfficeMapper salesGroupOfficeMapper;

    public List<SalesGroup> selectSalesGroup(){
        return salesGroupMapper.selectSalesGroup();
    }
}
