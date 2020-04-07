package com.crazy.portal.service.customer;

import com.crazy.portal.config.exception.BusinessException;
import com.crazy.portal.dao.cusotmer.CustInvoiceInfoMapper;
import com.crazy.portal.entity.cusotmer.CustInvoiceInfo;
import com.crazy.portal.util.ErrorCodes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: CustInvoiceInfoService
 * @Author: God Man Qiu~
 * @Date: 2019/8/29 22:39
 */
@Slf4j
@Service
public class CustInvoiceInfoService {
    @Resource
    private CustInvoiceInfoMapper custInvoiceInfoMapper;

    public List<CustInvoiceInfo> selectByCustId(Integer custId){
        return custInvoiceInfoMapper.selectByCustId(custId);
    }

    @Transactional
    public void saveOrUpdate(List<CustInvoiceInfo> custInvoiceInfos, Integer custId, Integer userId){
        if(null == custInvoiceInfos || custInvoiceInfos.isEmpty()){
            throw new BusinessException(ErrorCodes.BusinessEnum.CUSTOMER_INVOICE_IS_NOT_ENPTY);
        }
        custInvoiceInfos.forEach(e->{
            if(null == e.getInvoiceId()){
                e.setCustId(custId);
                e.setCreateUser(userId);
                e.setActive(1);
                custInvoiceInfoMapper.insertSelective(e);
            }else{
                e.setUpdateUser(userId);
                custInvoiceInfoMapper.updateByPrimaryKeySelective(e);
            }
        });
    }

    @Transactional
    public void deleteByCustId(List<CustInvoiceInfo> custInvoiceInfos, List<CustInvoiceInfo> results, Integer custId){
        if(null != results && !results.isEmpty()){
            if(null == custInvoiceInfos || custInvoiceInfos.isEmpty()){
                custInvoiceInfoMapper.deleteByCustId(custId);
            }else{
                for(CustInvoiceInfo r : results){
                    Boolean flg = true;
                    for(CustInvoiceInfo c : custInvoiceInfos){
                        if(r.getInvoiceId().equals(c.getInvoiceId())){
                            flg = false;
                            break;
                        }
                    }
                    if(flg){
                        custInvoiceInfoMapper.deleteByPrimaryKey(r.getInvoiceId());
                    }
                }
            }
        }
    }

    @Transactional
    public void deleteByCustId(Integer custId){
        custInvoiceInfoMapper.deleteByCustId(custId);
    }

    @Transactional
    public void save(CustInvoiceInfo record){
        custInvoiceInfoMapper.insertSelective(record);
    }
}
