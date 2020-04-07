package com.crazy.portal.service.customer;

import com.crazy.portal.config.exception.BusinessException;
import com.crazy.portal.dao.cusotmer.CustomerContactMapper;
import com.crazy.portal.entity.cusotmer.CustomerContact;
import com.crazy.portal.entity.cusotmer.CustomerInfo;
import com.crazy.portal.util.BusinessUtil;
import com.crazy.portal.util.ErrorCodes;
import com.crazy.portal.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public List<CustomerContact> selectByCustName(String custName){
        return customerContactMapper.selectByCustName(custName);
    }

    @Transactional
    public void saveOrUpdate(List<CustomerContact> customerContacts, Integer custId, Integer userId){
        if(null == customerContacts || customerContacts.isEmpty()){
            throw new BusinessException(ErrorCodes.BusinessEnum.CUSTOMER_CONTACT_IS_NOT_ENPTY);
        }
        customerContacts.forEach(e->{
            checkContact(e);
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

    @Transactional
    public void deleteByCustId(List<CustomerContact> customerContacts, List<CustomerContact> results, Integer custId){
        if(null != results && !results.isEmpty()){
            if(null == customerContacts || customerContacts.isEmpty()){
                customerContactMapper.deleteByCustId(custId);
            }else{
                for(CustomerContact r : results){
                    Boolean flg = true;
                    for(CustomerContact c : customerContacts){
                        if(r.getContactId().equals(c.getContactId())){
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

    @Transactional
    public void deleteByCustId(Integer custId){
        customerContactMapper.deleteByCustId(custId);
    }

    @Transactional
    public void save(CustomerContact record){
        checkContact(record);
        customerContactMapper.insertSelective(record);
    }

    private void checkContact(CustomerContact record){
        if(StringUtil.isNotEmpty(record.getMobile())){
            String telRegex = "[1][345678]\\d{9}";
            BusinessUtil.assertTrue(record.getMobile().matches(telRegex), ErrorCodes.BusinessEnum.CUSTOMER_MOBILE_IS_INACTIVE);
        }
        if(StringUtil.isNotEmpty(record.getEmail())){
            String mailRegex = "^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9]{2,6}$";
            BusinessUtil.assertTrue(record.getEmail().matches(mailRegex),ErrorCodes.BusinessEnum.CUSTOMER_EMAIL_IS_INACTIVE);
    }
    }
}
