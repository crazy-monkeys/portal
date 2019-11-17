package com.crazy.portal.service.customer;

import com.crazy.portal.dao.cusotmer.CustAssetsInformationMapper;
import com.crazy.portal.entity.cusotmer.CustAssetsInformation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: CustAssetsInformationService
 * @Author: God Man Qiu~
 * @Date: 2019/8/29 23:32
 */
@Slf4j
@Service
public class CustAssetsInformationService {
    @Resource
    private CustAssetsInformationMapper custAssetsInformationMapper;

    public List<CustAssetsInformation> selectByCustId(Integer custId){
        return custAssetsInformationMapper.selectByCustId(custId);
    }

    @Transactional
    public void saveOrUpdate(List<CustAssetsInformation> custAssetsInformations, Integer custId){
        if(null == custAssetsInformations || custAssetsInformations.isEmpty()){
            return;
        }
        custAssetsInformations.forEach(e->{
            if(null == e.getAsseteInfoId()){
                e.setCustId(custId);
                e.setActive(1);
                custAssetsInformationMapper.insertSelective(e);
            }else{
                custAssetsInformationMapper.updateByPrimaryKeySelective(e);
            }
        });
    }

    @Transactional
    public void deleteByCustId(List<CustAssetsInformation> custAssetsInformations, List<CustAssetsInformation> results, Integer custId){
        if(null != results && !results.isEmpty()){
            if(null == custAssetsInformations || custAssetsInformations.isEmpty()){
                custAssetsInformationMapper.deleteByCustId(custId);
            }else{
                for(CustAssetsInformation r : results){
                    Boolean flg = true;
                    for(CustAssetsInformation c : custAssetsInformations){
                        if(r.getAsseteInfoId().equals(c.getAsseteInfoId())){
                            flg = false;
                            break;
                        }
                    }
                    if(flg){
                        custAssetsInformationMapper.deleteByPrimaryKey(r.getAsseteInfoId());
                    }
                }
            }
        }
    }

    @Transactional
    public void deleteByCustId(Integer custId){
        custAssetsInformationMapper.deleteByCustId(custId);
    }

    @Transactional
    public void save(CustAssetsInformation record){
        custAssetsInformationMapper.insertSelective(record);
    }
}
