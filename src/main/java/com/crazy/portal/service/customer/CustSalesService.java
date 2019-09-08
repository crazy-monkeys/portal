package com.crazy.portal.service.customer;

import com.crazy.portal.dao.cusotmer.CustSalesMapper;
import com.crazy.portal.entity.cusotmer.CustSales;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: CustSalesService
 * @Author: God Man Qiu~
 * @Date: 2019/8/29 22:54
 */
@Slf4j
@Service
public class CustSalesService {
    @Resource
    private CustSalesMapper custSalesMapper;

    public List<CustSales> selectByCustId(Integer custId){
        return custSalesMapper.selectByCustId(custId);
    }

    public void saveOrUpdate(List<CustSales> custSales, Integer custId, Integer userId){
        if(null == custSales || custSales.isEmpty()){
            return;
        }
        custSales.forEach(e->{
            if(null == e.getSalesId()){
                e.setCustId(custId);
                e.setCreateUser(userId);
                e.setActive(1);
                custSalesMapper.insertSelective(e);
            }else{
                e.setUpdateUser(userId);
                custSalesMapper.updateByPrimaryKeySelective(e);
            }
        });
    }

    public void deleteByCustId(List<CustSales> custSales, List<CustSales> results, Integer custId){
        if(null != results && !results.isEmpty()){
            if(null == custSales || custSales.isEmpty()){
                custSalesMapper.deleteByCustId(custId);
            }else{
                for(CustSales r : results){
                    Boolean flg = true;
                    for(CustSales c : custSales){
                        if(r.getSalesId() == c.getSalesId()){
                            flg = false;
                            break;
                        }
                    }
                    if(flg){
                        custSalesMapper.deleteByPrimaryKey(r.getSalesId());
                    }
                }
            }
        }
    }

    public void deleteByCustId(Integer custId){
        custSalesMapper.deleteByCustId(custId);
    }

    public void save(CustSales record){
        custSalesMapper.insertSelective(record);
    }
}
