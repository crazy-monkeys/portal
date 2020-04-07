package com.crazy.portal.service.customer;

import com.crazy.portal.config.exception.BusinessException;
import com.crazy.portal.dao.cusotmer.CustomerStructureMapper;
import com.crazy.portal.entity.cusotmer.CustomerStructure;
import com.crazy.portal.util.ErrorCodes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: CustomerStructureService
 * @Author: God Man Qiu~
 * @Date: 2019/9/2 23:43
 */
@Slf4j
@Service
public class CustomerStructureService {
    @Resource
    private CustomerStructureMapper customerStructureMapper;

    public List<CustomerStructure> selectByCustId(Integer custId){
        return customerStructureMapper.selectByCustId(custId);
    }

    @Transactional
    public void saveOrUpdate(List<CustomerStructure> customerStructures, Integer custId, Integer userId){
        if(null == customerStructures || customerStructures.isEmpty()){
            throw new BusinessException(ErrorCodes.BusinessEnum.CUSTOMER_Structure_IS_NOT_ENPTY);
        }
        customerStructures.forEach(e->{
            if(null == e.getStructureId()){
                e.setCustId(custId);
                e.setCreateUserId(userId);
                e.setActive(1);
                customerStructureMapper.insertSelective(e);
            }else{
                e.setUpdateUserId(userId);
                customerStructureMapper.updateByPrimaryKeySelective(e);
            }
        });
    }

    @Transactional
    public void deleteByCustId(List<CustomerStructure> customerStructures, List<CustomerStructure> results, Integer custId){
        if(null != results && !results.isEmpty()){
            if(null == customerStructures || customerStructures.isEmpty()){
                customerStructureMapper.deleteByCustId(custId);
            }else{
                for(CustomerStructure r : results){
                    Boolean flg = true;
                    for(CustomerStructure c : customerStructures){
                        if(r.getStructureId().equals(c.getStructureId())){
                            flg = false;
                            break;
                        }
                    }
                    if(flg){
                        customerStructureMapper.deleteByPrimaryKey(r.getStructureId());
                    }
                }
            }
        }
    }

    @Transactional
    public void deleteByCustId(Integer custId){
        customerStructureMapper.deleteByCustId(custId);
    }

    @Transactional
    public void save(CustomerStructure record){
        customerStructureMapper.insertSelective(record);
    }

}
