package com.crazy.portal.service.customer;

import com.crazy.portal.dao.cusotmer.CustomerAddressMapper;
import com.crazy.portal.entity.cusotmer.CustomerAddress;
import com.crazy.portal.entity.cusotmer.CustomerContact;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: CustomerAddressService
 * @Author: God Man Qiu~
 * @Date: 2019/8/29 22:57
 */
@Slf4j
@Service
public class CustomerAddressService {
    @Resource
    private CustomerAddressMapper customerAddressMapper;

    public List<CustomerAddress> selectByCustId(Integer custId){
        return customerAddressMapper.selectByCustId(custId);
    }

    public void saveOrUpdate(List<CustomerAddress> customerAddresses, Integer custId, Integer userId){
        if(null == customerAddresses || customerAddresses.isEmpty()){
            return;
        }
        customerAddresses.forEach(e->{
            if(null == e.getAddressId()){
                e.setCustId(custId);
                e.setCrateUser(userId);
                e.setActive(1);
                customerAddressMapper.insertSelective(e);
            }else{
                e.setUpdateUser(userId);
                customerAddressMapper.updateByPrimaryKeySelective(e);
            }
        });
    }

    public void deleteByCustId(List<CustomerAddress> customerAddresses, List<CustomerAddress> results, Integer custId){
        if(null != results && !results.isEmpty()){
            if(null == customerAddresses || customerAddresses.isEmpty()){
                customerAddressMapper.deleteByCustId(custId);
            }else{
                for(CustomerAddress r : results){
                    Boolean flg = true;
                    for(CustomerAddress c : customerAddresses){
                        if(r.getAddressId() == c.getAddressId()){
                            flg = false;
                            break;
                        }
                    }
                    if(flg){
                        customerAddressMapper.deleteByPrimaryKey(r.getAddressId());
                    }
                }
            }
        }
    }

}
