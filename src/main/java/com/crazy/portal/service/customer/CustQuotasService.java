package com.crazy.portal.service.customer;

import com.crazy.portal.dao.cusotmer.CustSalesQuotaMapper;
import com.crazy.portal.entity.cusotmer.CustSalesQuota;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: CustQuotasService
 * @Author: God Man Qiu~
 * @Date: 2019/9/8 16:56
 */
@Slf4j
@Service
public class CustQuotasService {
    @Resource
    private CustSalesQuotaMapper custSalesQuotaMapper;

    public List<CustSalesQuota> selectByCustId(Integer custId){
        return custSalesQuotaMapper.selectByCustId(custId);
    }

    @Transactional
    public void saveOrUpdate(List<CustSalesQuota> custSalesQuotas, Integer custId, Integer userId){
        if(null == custSalesQuotas || custSalesQuotas.isEmpty()){
            return;
        }
        custSalesQuotas.forEach(e->{
            if(null == e.getQuoatId()){
                e.setCustId(custId);
                e.setCreateUserId(userId);
                e.setActive(1);
                custSalesQuotaMapper.insertSelective(e);
            }else{
                e.setUpdateUserId(userId);
                custSalesQuotaMapper.updateByPrimaryKeySelective(e);
            }
        });
    }

    @Transactional
    public void deleteByCustId(List<CustSalesQuota> custSalesQuotas, List<CustSalesQuota> results, Integer custId){
        if(null != results && !results.isEmpty()){
            if(null == custSalesQuotas || custSalesQuotas.isEmpty()){
                custSalesQuotaMapper.deleteByCustId(custId);
            }else{
                for(CustSalesQuota r : results){
                    Boolean flg = true;
                    for(CustSalesQuota c : custSalesQuotas){
                        if(r.getQuoatId().equals(c.getQuoatId())){
                            flg = false;
                            break;
                        }
                    }
                    if(flg){
                        custSalesQuotaMapper.deleteByPrimaryKey(r.getQuoatId());
                    }
                }
            }
        }
    }

    @Transactional
    public void deleteByCustId(Integer custId){
        custSalesQuotaMapper.deleteByCustId(custId);
    }

    @Transactional
    public void save(CustSalesQuota record){
        custSalesQuotaMapper.insertSelective(record);
    }

}
