package com.crazy.portal.service.customer;

import com.crazy.portal.dao.cusotmer.CustomerAccountTeamMapper;
import com.crazy.portal.entity.cusotmer.CustomerAccountTeam;
import com.crazy.portal.entity.cusotmer.CustomerContact;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: CustomerAccountTeamService
 * @Author: God Man Qiu~
 * @Date: 2019/8/29 23:01
 */
@Slf4j
@Service
public class CustomerAccountTeamService {
    @Resource
    private CustomerAccountTeamMapper customerAccountTeamMapper;

    public List<CustomerAccountTeam> selectByCustId(Integer custId){
        return customerAccountTeamMapper.selectByCustId(custId);
    }

    public void saveOrUpdate(List<CustomerAccountTeam> customerAccountTeams, Integer custId, Integer userId){
        if(null == customerAccountTeams || customerAccountTeams.isEmpty()){
            return;
        }
        customerAccountTeams.forEach(e->{
            if(null == e.getTeamId()){
                e.setCustId(custId);
                e.setCreateUser(userId);
                e.setActive(1);
                customerAccountTeamMapper.insertSelective(e);
            }else{
                e.setUpdateUser(userId);
                customerAccountTeamMapper.updateByPrimaryKeySelective(e);
            }
        });
    }

    public void deleteByCustId(List<CustomerAccountTeam> customerAccountTeams, List<CustomerAccountTeam> results, Integer custId){
        if(null != results && !results.isEmpty()){
            if(null == customerAccountTeams || customerAccountTeams.isEmpty()){
                customerAccountTeamMapper.deleteByCustId(custId);
            }else{
                for(CustomerAccountTeam r : results){
                    Boolean flg = true;
                    for(CustomerAccountTeam c : customerAccountTeams){
                        if(r.getTeamId() == c.getTeamId()){
                            flg = false;
                            break;
                        }
                    }
                    if(flg){
                        customerAccountTeamMapper.deleteByPrimaryKey(r.getTeamId());
                    }
                }
            }
        }
    }
}
