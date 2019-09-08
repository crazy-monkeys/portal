package com.crazy.portal.service.customer;

import com.crazy.portal.dao.cusotmer.CustomerAgentMapper;
import com.crazy.portal.entity.cusotmer.CustomerAgent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: CustomerAgentsService
 * @Author: God Man Qiu~
 * @Date: 2019/9/8 12:09
 */
@Slf4j
@Service
public class CustomerAgentsService {
    @Resource
    private CustomerAgentMapper customerAgentMapper;

    public List<CustomerAgent> selectByCustId(Integer custId){
        return customerAgentMapper.selectByCustId(custId);
    }

    public void deleteByCustId(Integer custId){
        customerAgentMapper.deleteByCustId(custId);
    }

    public void save(CustomerAgent agent){
        customerAgentMapper.insertSelective(agent);
    }

}
