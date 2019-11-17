package com.crazy.portal.service.customer;

import com.crazy.portal.dao.cusotmer.CustBusinessInformationMapper;
import com.crazy.portal.entity.cusotmer.CustBusinessInformation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: CustBusinessInformationService
 * @Author: God Man Qiu~
 * @Date: 2019/8/29 23:33
 */
@Slf4j
@Service
public class CustBusinessInformationService {
    @Resource
    private CustBusinessInformationMapper custBusinessInformationMapper;

    public List<CustBusinessInformation> selectByCustId(Integer custId){
        return custBusinessInformationMapper.selectByCustId(custId);
    }

    @Transactional
    public void saveOrUpdate(List<CustBusinessInformation> custBusinessInformations, Integer custId){
        if(null == custBusinessInformations || custBusinessInformations.isEmpty()){
            return;
        }
        custBusinessInformations.forEach(e->{
            if(null == e.getBusInfoId()){
                e.setCustId(custId);
                e.setActive(1);
                custBusinessInformationMapper.insertSelective(e);
            }else{
                custBusinessInformationMapper.updateByPrimaryKeySelective(e);
            }
        });
    }

    @Transactional
    public void deleteByCustId(List<CustBusinessInformation> custBusinessInformations, List<CustBusinessInformation> results, Integer custId){
        if(null != results && !results.isEmpty()){
            if(null == custBusinessInformations || custBusinessInformations.isEmpty()){
                custBusinessInformationMapper.deleteByCustId(custId);
            }else{
                for(CustBusinessInformation r : results){
                    Boolean flg = true;
                    for(CustBusinessInformation c : custBusinessInformations){
                        if(r.getBusInfoId().equals(c.getBusInfoId())){
                            flg = false;
                            break;
                        }
                    }
                    if(flg){
                        custBusinessInformationMapper.deleteByPrimaryKey(r.getBusInfoId());
                    }
                }
            }
        }
    }

    @Transactional
    public void deleteByCustId(Integer custId){
        custBusinessInformationMapper.deleteByCustId(custId);
    }

    @Transactional
    public void save(CustBusinessInformation record){
        custBusinessInformationMapper.insertSelective(record);
    }
}
