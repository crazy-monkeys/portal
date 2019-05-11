package com.crazy.portal.service.customer;

import com.crazy.portal.bean.customer.SaveCustomerVO;
import com.crazy.portal.dao.customer.*;
import com.crazy.portal.entity.customer.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @ClassName: CustomerService
 * @Author: God Man Qiu~
 * @Date: 2019/4/19 22:41
 */
@Slf4j
@Service
public class CustomerService {
    @Resource
    private TCustomerRegionMapper tCustomerRegionMapper;
    @Resource
    private TCustomerInfoMapper tCustomerInfoMapper;
    @Resource
    private TCustomerAddressMapper tCustomerAddressMapper;
    @Resource
    private TCustomerContactsMapper tCustomerContactsMapper;
    @Resource
    private TCustomerProjectMapper tCustomerProjectMapper;

    public List<TCustomerInfo> selectAllCustomer(){
        return tCustomerInfoMapper.selectAllCustomer();
    }

    public List<TCustomerInfo> approvalList(){
        return tCustomerInfoMapper.approvalList();
    }

    public TCustomerInfo selectByName(String customerName){
        return tCustomerInfoMapper.selectByName(customerName);
    }

    public TCustomerInfo selectByRegist(Integer registId){
        return tCustomerInfoMapper.selectByRegistId(registId);
    }

    @Transactional
    public void saveCustomer(SaveCustomerVO info){
        int customerId = saveOrUpdateCustomer(info.getCustomerInfo());
        //TODO
        int userId=1;
        TCustomerRegion region = tCustomerRegionMapper.selectByRegion(customerId, userId, info.getCustomerInfo().getRegion());
        int regId = saveCustomerRegion(region, customerId, userId, info.getCustomerInfo().getRegion());
        saveOrUpdateAddress(info.getAddresses(), regId);
        saveOrUpdateContact(info.getCustomerContacts(), regId);
        saveOrUpdateProject(info.getCustomerProjects(), regId);
    }

    private int saveOrUpdateCustomer(TCustomerInfo customerInfo){
        TCustomerInfo customer = selectByName(customerInfo.getCustomerName());
        if(null == customer){
            customerInfo.setCustomerCode(UUID.randomUUID().toString());
            customerInfo.setCreateUserId(1);
            customerInfo.setCreateTime(new Date());
            customerInfo.setActive((short)1);
            tCustomerInfoMapper.insertSelective(customerInfo);
        }else{
            return customer.getCustomerId();
        }
        return customerInfo.getCustomerId();
    }

    private int saveCustomerRegion(TCustomerRegion customerRegion, int customerId, int userId, String region){
        if(null == customerRegion){
            customerRegion = new TCustomerRegion();
            customerRegion.setRegistStatus(0);
            customerRegion.setCustomerId(customerId);
            customerRegion.setUserId(userId);
            customerRegion.setRegion(region);
            customerRegion.setCreateTime(new Date());
            customerRegion.setCreateUserId(userId);
            customerRegion.setActive((short)1);
            tCustomerRegionMapper.insertSelective(customerRegion);
        }
        return customerRegion.getId();
    }

    private void saveOrUpdateAddress(List<TCustomerAddress> addresses, int regId){
        for(TCustomerAddress address : addresses){
            if(null == address.getId()){
                //TODO
                address.setRegionId(regId);
                address.setCreateUserId(1);
                address.setCreateTime(new Date());
                tCustomerAddressMapper.insertSelective(address);
            }else{
                address.setUpdateUserId(1);
                address.setUpdateTime(new Date());
                tCustomerAddressMapper.updateByPrimaryKeySelective(address);
            }
        }
    }

    private void saveOrUpdateContact(List<TCustomerContacts> contacts, int regId){
        for(TCustomerContacts contact : contacts){
            if(null == contact.getId()){
                //TODO
                contact.setRegionId(regId);
                contact.setCreateUserId(1);
                contact.setCreateTime(new Date());
                tCustomerContactsMapper.insertSelective(contact);
            }else{
                contact.setUpdateUsrId(1);
                contact.setUpdateTime(new Date());
                tCustomerContactsMapper.updateByPrimaryKeySelective(contact);
            }
        }
    }

    private void saveOrUpdateProject(List<TCustomerProject> projects, int regId){
        for(TCustomerProject project : projects){
            if(null == project.getId()){
                //TODO
                project.setRegionId(regId);
                project.setCreateUserId(1);
                project.setCreateTime(new Date());
                tCustomerProjectMapper.insertSelective(project);
            }else{
                project.setUpdateUserId(1);
                project.setUpdateTime(new Date());
                tCustomerProjectMapper.updateByPrimaryKeySelective(project);
            }
        }
    }

    public void approval(Integer registId){
        TCustomerRegion region = tCustomerRegionMapper.selectByPrimaryKey(registId);
        region.setRegistStatus(1);
        tCustomerRegionMapper.updateByPrimaryKeySelective(region);
    }
}
