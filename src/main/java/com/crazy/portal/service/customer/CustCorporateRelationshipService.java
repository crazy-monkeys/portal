package com.crazy.portal.service.customer;

import com.crazy.portal.dao.cusotmer.CustCorporateRelationshipMapper;
import com.crazy.portal.dao.cusotmer.CustomerInfoMapper;
import com.crazy.portal.entity.cusotmer.CustCorporateRelationship;
import com.crazy.portal.entity.cusotmer.CustomerInfo;
import com.crazy.portal.entity.system.User;
import com.crazy.portal.util.BusinessUtil;
import com.crazy.portal.util.Enums;
import com.crazy.portal.util.ErrorCodes;
import com.crazy.portal.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    @Resource
    private CustomerInfoMapper customerInfoMapper;

    public List<CustCorporateRelationship> selectByCustId(Integer custId){
        return custCorporateRelationshipMapper.selectDealerShip(custId);
    }

    public List<CustCorporateRelationship> selectZShip(Integer custId){
        return custCorporateRelationshipMapper.selectZShip(custId);
    }

    /**
     * 获取客户的内部客户
     * @param custId
     * @return
     */
    public CustCorporateRelationship selectInCustomer(Integer custId){
        return custCorporateRelationshipMapper.selectInCustomer(custId);
    }

    /**
     * 获取内部客户的外部客户
     * @param inCode
     */
    public List<CustCorporateRelationship> selectOutCustomer(String inCode){
        return custCorporateRelationshipMapper.selectOutCustomer(inCode);
    }

    @Transactional
    public void saveOrUpdate(List<CustCorporateRelationship> custCorporateRelationships, Integer custId, Integer userId){
        if(null == custCorporateRelationships || custCorporateRelationships.isEmpty()){
            return;
        }
        custCorporateRelationships.forEach(e->{
            if(StringUtil.isNotEmpty(e.getCorporateId()) && e.getCorporateId().equals(e.getCorporateName())){
                CustomerInfo oldCt = customerInfoMapper.selectByInCode(e.getCorporateId());
                e.setCorporateName(oldCt.getCustName());
            }
            if(null == e.getShipId()){
                e.setCustId(custId);
                e.setCreateUser(userId);
                e.setActive(1);
                custCorporateRelationshipMapper.insertSelective(e);
                saveShip(e);
            }else{
                e.setUpdateUser(userId);
                custCorporateRelationshipMapper.updateByPrimaryKeySelective(e);
            }
        });
    }

    @Transactional
    public void deleteByCustId(List<CustCorporateRelationship> custCorporateRelationships, List<CustCorporateRelationship> results, Integer custId){
        if(null != results && !results.isEmpty()){
            if(null == custCorporateRelationships || custCorporateRelationships.isEmpty()){
                deleteShip(custId);
                custCorporateRelationshipMapper.deleteByCustId(custId);
            }else{
                for(CustCorporateRelationship r : results){
                    Boolean flg = true;
                    for(CustCorporateRelationship c : custCorporateRelationships){
                        if(r.getShipId().equals(c.getShipId())){
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
    @Transactional
    public void updateCustShip(Integer custId, Integer userId, CustomerInfo dealer){
        if(null != dealer) {
            CustCorporateRelationship ship = new CustCorporateRelationship();
            ship.setCustId(custId);
            ship.setCorporateType("Z004");
            ship.setCorporateId(dealer.getInCode());
            ship.setCorporateName(dealer.getCustName());
            ship.setCreateUser(userId);
            ship.setActive(1);
            custCorporateRelationshipMapper.insertSelective(ship);
            saveShip(ship);
        }
    }

    @Transactional
    public void deleteByCustId(Integer custId){
        deleteShip(custId);
        custCorporateRelationshipMapper.deleteByCustId(custId);
    }

    @Transactional
    public void save(CustCorporateRelationship record){
        custCorporateRelationshipMapper.insertSelective(record);
    }

    @Transactional
    public void saveShip(CustCorporateRelationship ship){
        //找到 关系客户
        CustomerInfo oldCt = customerInfoMapper.selectByInCode(ship.getCorporateId());
        //找到客户
        CustomerInfo ct = customerInfoMapper.selectByPrimaryKey(ship.getCustId());
        if(null != oldCt){
            //找到关系客户的 所有关系
            List<CustCorporateRelationship> oldShips = custCorporateRelationshipMapper.selectByCustId(oldCt.getId());
            //遍历关系客户的关系 看是否有和客户的关系
            Boolean flg = true;
            for (CustCorporateRelationship e : oldShips){
                if(null != e.getCorporateId() && null != ct.getInCode() && e.getCorporateId().equals(ct.getInCode())){
                    flg = false;
                    break;
                }
            }

            if(flg){
                CustCorporateRelationship newShip = new CustCorporateRelationship();
                newShip.setCustId(oldCt.getId());
                newShip.setCorporateType(ship.getCorporateType());
                newShip.setCorporateId(ct.getInCode());
                newShip.setCorporateName(ct.getCustName());
                newShip.setCreateUser(ct.getId());
                newShip.setActive(1);
                custCorporateRelationshipMapper.insertSelective(newShip);
            }
        }
    }

    @Transactional
    public void deleteShip(Integer custId){
        //找到客户的信息
        CustomerInfo customerInfo = customerInfoMapper.selectByPrimaryKey(custId);
        //找到客户的所有关系
        List<CustCorporateRelationship> oldShips = custCorporateRelationshipMapper.selectByCustId(custId);
        //遍历关系
        oldShips.forEach(e->{
            //找到每条关系对应的客户
            CustomerInfo shipCus = customerInfoMapper.selectByInCode(e.getCorporateId());
            BusinessUtil.assertFlase(null == shipCus,ErrorCodes.BusinessEnum.CUSTOMER_IS_EMPYT);
            //找到对应客户的关系是关联该客户的关系
            CustCorporateRelationship ship = custCorporateRelationshipMapper.selectByShipCus(shipCus.getId(), e.getCorporateType(), customerInfo.getInCode());
            //删除
            if(null != ship){
                custCorporateRelationshipMapper.deleteByPrimaryKey(ship.getShipId());
            }
        });

    }

    public void updateShip(Integer dealerId){
        //找到代理商的所有关系
        List<CustCorporateRelationship> oldShips = custCorporateRelationshipMapper.selectByCustId(dealerId);
        oldShips.forEach(e->{
            if(StringUtil.isEmpty(e.getCorporateId())){
                CustomerInfo customerInfo = customerInfoMapper.selectApproveCustomer(e.getCorporateName());
                if(null != customerInfo){
                    e.setCorporateId(customerInfo.getInCode());
                    custCorporateRelationshipMapper.updateByPrimaryKey(e);
                }
            }
        });
    }
}
