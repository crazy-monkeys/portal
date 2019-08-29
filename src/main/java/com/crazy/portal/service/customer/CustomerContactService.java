package com.crazy.portal.service.customer;

import com.crazy.portal.dao.cusotmer.CustomerContactMapper;
import com.crazy.portal.entity.cusotmer.CustomerContact;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: CustomerContactService
 * @Author: God Man Qiu~
 * @Date: 2019/8/29 19:20
 */
@Slf4j
@Service
public class CustomerContactService {
    @Resource
    private CustomerContactMapper customerContactMapper;

    public List<CustomerContact> selectByCustId(Integer custId){
        return customerContactMapper.selectByCustId(custId);
    }

    public void saveOrUpdate(List<CustomerContact> customerContacts, Integer custId, Integer userId){
        if(null == customerContacts || customerContacts.isEmpty()){
            return;
        }
        customerContacts.forEach(e->{
            if(null == e.getContactId()){
                e.setCustId(custId);
                e.setInsertUser(userId);
                e.setActive(1);
                customerContactMapper.insertSelective(e);
            }else{
                e.setUpdateUser(userId);
                customerContactMapper.updateByPrimaryKeySelective(e);
            }
        });
    }

    public void deleteByCustId(List<CustomerContact> customerContacts, List<CustomerContact> results, Integer custId){
        if(null != results && !results.isEmpty()){
            if(null == customerContacts || customerContacts.isEmpty()){
                customerContactMapper.deleteByCustId(custId);
            }else{
                for(CustomerContact r : results){
                    Boolean flg = true;
                    for(CustomerContact c : customerContacts){
                        if(r.getContactId() == c.getContactId()){
                            flg = false;
                            break;
                        }
                    }
                    if(flg){
                        customerContactMapper.deleteByPrimaryKey(r.getContactId());
                    }
                }
            }
        }
    }
}