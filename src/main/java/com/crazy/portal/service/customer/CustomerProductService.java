package com.crazy.portal.service.customer;

import com.crazy.portal.config.exception.BusinessException;
import com.crazy.portal.dao.cusotmer.CustomerProductMapper;
import com.crazy.portal.entity.cusotmer.CustomerProduct;
import com.crazy.portal.util.ErrorCodes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: CustomerProductService
 * @Author: God Man Qiu~
 * @Date: 2019/8/29 19:35
 */
@Slf4j
@Service
public class CustomerProductService {
    @Resource
    private CustomerProductMapper customerProductMapper;

    public List<CustomerProduct> selectByCustId(Integer custId){
        return customerProductMapper.selectByCustId(custId);
    }

    @Transactional
    public void saveOrUpdate(List<CustomerProduct> customerProducts, Integer custId, Integer userId){
        if(null == customerProducts || customerProducts.isEmpty()){
            throw new BusinessException(ErrorCodes.BusinessEnum.CUSTOMER_PRODUCT_IS_NOT_ENPTY);
        }
        customerProducts.forEach(e->{
            if(null == e.getProId()){
                e.setCustId(custId);
                e.setInsertUser(userId);
                e.setActive(1);
                customerProductMapper.insertSelective(e);
            }else{
                e.setUpdateUser(userId);
                customerProductMapper.updateByPrimaryKeySelective(e);
            }
        });
    }

    @Transactional
    public void deleteByCustId(List<CustomerProduct> customerProducts, List<CustomerProduct> results, Integer custId){
        if(null != results && !results.isEmpty()){
            if(null == customerProducts || customerProducts.isEmpty()){
                customerProductMapper.deleteByCustId(custId);
            }else{
                for(CustomerProduct r : results){
                    Boolean flg = true;
                    for(CustomerProduct c : customerProducts){
                        if(r.getProId().equals(c.getProId())){
                            flg = false;
                            break;
                        }
                    }
                    if(flg){
                        customerProductMapper.deleteByPrimaryKey(r.getProId());
                    }
                }
            }
        }
    }

    @Transactional
    public void deleteByCustId(Integer custId){
        customerProductMapper.deleteByCustId(custId);
    }

    @Transactional
    public void save(CustomerProduct record){
        customerProductMapper.insertSelective(record);
    }
}
