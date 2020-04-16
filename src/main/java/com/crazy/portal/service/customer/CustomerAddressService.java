package com.crazy.portal.service.customer;

import com.crazy.portal.config.exception.BusinessException;
import com.crazy.portal.dao.cusotmer.CustomerAddressMapper;
import com.crazy.portal.entity.cusotmer.CustomerAddress;
import com.crazy.portal.entity.cusotmer.CustomerContact;
import com.crazy.portal.util.ErrorCodes;
import com.crazy.portal.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public void saveOrUpdate(List<CustomerAddress> customerAddresses, Integer custId, Integer userId){
        if(null == customerAddresses || customerAddresses.isEmpty()){
            throw new BusinessException(ErrorCodes.BusinessEnum.CUSTOMER_ADDRESS_IS_NOT_ENPTY);
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

    @Transactional
    public void deleteByCustId(List<CustomerAddress> customerAddresses, List<CustomerAddress> results, Integer custId){
        if(null != results && !results.isEmpty()){
            if(null == customerAddresses || customerAddresses.isEmpty()){
                customerAddressMapper.deleteByCustId(custId);
            }else{
                for(CustomerAddress r : results){
                    Boolean flg = true;
                    for(CustomerAddress c : customerAddresses){
                        if(r.getAddressId().equals(c.getAddressId())){
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

    @Transactional
    public void deleteByCustId(Integer custId){
        customerAddressMapper.deleteByCustId(custId);
    }

    @Transactional
    public void save(CustomerAddress record){
        customerAddressMapper.insertSelective(record);
    }

    private void checkAddress(CustomerAddress address){
        if(StringUtil.isEmpty(address.getAddressType())){
            throw new BusinessException(ErrorCodes.BusinessEnum.CUSTOMER_ADDRESS_TYPE_IS_NOT_ENPTY);
        }
        if(StringUtil.isEmpty(address.getCountry())||StringUtil.isEmpty(address.getCity())){
            throw new BusinessException(ErrorCodes.BusinessEnum.CUSTOMER_ADDRESS_CONTRY_IS_NOT_ENPTY);
        }
    }
}
