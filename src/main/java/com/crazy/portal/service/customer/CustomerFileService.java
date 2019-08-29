package com.crazy.portal.service.customer;

import com.crazy.portal.dao.cusotmer.CustomerFileMapper;
import com.crazy.portal.entity.cusotmer.CustomerFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: CustomerFileService
 * @Author: God Man Qiu~
 * @Date: 2019/8/29 19:40
 */
@Slf4j
@Service
public class CustomerFileService {
    @Resource
    private CustomerFileMapper customerFileMapper;

    public List<CustomerFile> selectByCustId(Integer custId){
        return customerFileMapper.selectByCustId(custId);
    }

    public void saveOrUpdate(List<CustomerFile> customerFiles, Integer custId){
        if(null == customerFiles || customerFiles.isEmpty()){
            return;
        }
        customerFiles.forEach(e->{
            if(null == e.getFileId()){
                e.setCustId(custId);
                e.setActive(1);
                customerFileMapper.insertSelective(e);
            }else{
                customerFileMapper.updateByPrimaryKeySelective(e);
            }
        });
    }

    public void deleteByCustId(List<CustomerFile> customerFiles, List<CustomerFile> results, Integer custId){
        if(null != results && !results.isEmpty()){
            if(null == customerFiles || customerFiles.isEmpty()){
                customerFileMapper.deleteByCustId(custId);
            }else{
                for(CustomerFile r : results){
                    Boolean flg = true;
                    for(CustomerFile c : customerFiles){
                        if(r.getFileId() == c.getFileId()){
                            flg = false;
                            break;
                        }
                    }
                    if(flg){
                        customerFileMapper.deleteByPrimaryKey(r.getFileId());
                    }
                }
            }
        }
    }
}
