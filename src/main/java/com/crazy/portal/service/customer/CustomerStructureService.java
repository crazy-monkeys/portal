package com.crazy.portal.service.customer;

import com.crazy.portal.dao.cusotmer.CustomerStructureMapper;
import com.crazy.portal.entity.cusotmer.CustomerStructure;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
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

    public void saveOrUpdate(List<CustomerStructure> customerStructures, Integer custId, Integer userId){
        if(null == customerStructures || customerStructures.isEmpty()){
            return;
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

    public void deleteByCustId(List<CustomerStructure> customerStructures, List<CustomerStructure> results, Integer custId){
        if(null != results && !results.isEmpty()){
            if(null == customerStructures || customerStructures.isEmpty()){
                customerStructureMapper.deleteByCustId(custId);
            }else{
                for(CustomerStructure r : results){
                    Boolean flg = true;
                    for(CustomerStructure c : customerStructures){
                        if(r.getStructureId() == c.getStructureId()){
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

    public void deleteByCustId(Integer custId){
        customerStructureMapper.deleteByCustId(custId);
    }

    public void save(CustomerStructure record){
        customerStructureMapper.insertSelective(record);
    }

}
