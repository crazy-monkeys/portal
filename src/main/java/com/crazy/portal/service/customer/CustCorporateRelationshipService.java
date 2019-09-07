package com.crazy.portal.service.customer;

import com.crazy.portal.dao.cusotmer.CustCorporateRelationshipMapper;
import com.crazy.portal.entity.cusotmer.CustCorporateRelationship;
import com.crazy.portal.entity.cusotmer.CustomerInfo;
import com.crazy.portal.entity.system.User;
import com.crazy.portal.util.Enums;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: CustCorporateRelationshipService
 * @Author: God Man Qiu~
 * @Date: 2019/8/29 22:34
 */
@Slf4j
@Service
public class CustCorporateRelationshipService {
    @Resource
    private CustCorporateRelationshipMapper custCorporateRelationshipMapper;

    public List<CustCorporateRelationship> selectByCustId(Integer custId){
        return custCorporateRelationshipMapper.selectDealerShip(custId);
    }

    public void saveOrUpdate(List<CustCorporateRelationship> custCorporateRelationships, Integer custId, Integer userId){
        if(null == custCorporateRelationships || custCorporateRelationships.isEmpty()){
            return;
        }
        custCorporateRelationships.forEach(e->{
            if(null == e.getShipId()){
                e.setCustId(custId);
                e.setCreateUser(userId);
                e.setActive(1);
                custCorporateRelationshipMapper.insertSelective(e);
            }else{
                e.setUpdateUser(userId);
                custCorporateRelationshipMapper.updateByPrimaryKeySelective(e);
            }
        });
    }

    public void deleteByCustId(List<CustCorporateRelationship> custCorporateRelationships, List<CustCorporateRelationship> results, Integer custId){
        if(null != results && !results.isEmpty()){
            if(null == custCorporateRelationships || custCorporateRelationships.isEmpty()){
                custCorporateRelationshipMapper.deleteByCustId(custId);
            }else{
                for(CustCorporateRelationship r : results){
                    Boolean flg = true;
                    for(CustCorporateRelationship c : custCorporateRelationships){
                        if(r.getShipId() == c.getShipId()){
                            flg = false;
                            break;
                        }
                    }
                    if(flg){
                        custCorporateRelationshipMapper.deleteByPrimaryKey(r.getShipId());
                    }
                }
            }
        }
    }

    /**
     * 维护代理商和客户的关系
     * @param custId
     */
    public void UpdateCustShip(Integer custId, Integer userId, CustomerInfo dealer){
        if(null != dealer) {
            CustCorporateRelationship ship = new CustCorporateRelationship();
            ship.setCustId(custId);
            ship.setCorporateType("Z001");
            ship.setCorporateId(dealer.getId());
            ship.setCorporateName(dealer.getCustName());
            ship.setCreateUser(userId);
            ship.setActive(1);
            custCorporateRelationshipMapper.insertSelective(ship);
        }
    }
}
