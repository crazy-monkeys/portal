package com.crazy.portal.service.customer;

import com.crazy.portal.bean.customer.CustomerQueryBean;
import com.crazy.portal.dao.basic.*;
import com.crazy.portal.dao.customer.CustomerInfoMapper;
import com.crazy.portal.entity.basic.BaseEntity;
import com.crazy.portal.entity.basic.BasicBankInfo;
import com.crazy.portal.entity.customer.CustomerInfo;
import com.crazy.portal.util.*;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 客户管理
 * @Author Shawn
 * @Date 2019-07-27
 */
@Transactional
@Slf4j
@Service
public class CustomersService {

    @Resource
    private CustomerInfoMapper customerInfoMapper;
    @Resource
    private BasicInvoiceInfoMapper basicInvoiceInfoMapper;
    @Resource
    private BasicCorporateRelationshipMapper basicCorporateRelationshipMapper;
    @Resource
    private BasicCorporateStructureMapper basicCorporateStructureMapper;
    @Resource
    private BasicFileMapper basicFileMapper;
    @Resource
    private BasicSalesMapper basicSalesMapper;
    @Resource
    private BasicSalesTeamMapper basicSalesTeamMapper;
    @Resource
    private BasicContactMapper basicContactMapper;
    @Resource
    private BasicBankInfoMapper basicBankInfoMapper;
    @Resource
    private BasicAddressMapper basicAddressMapper;
    /**
     * 分页查询客户列表
     * @param bean
     * @return
     */
    public PageInfo<CustomerInfo> queryCustByPage(CustomerQueryBean bean){
        PortalUtil.defaultStartPage(bean.getPageIndex(), bean.getPageSize());
        List<CustomerInfo> list = customerInfoMapper.selectCustByPage(bean);
        return new PageInfo<>(list);
    }

    /**
     * 查询客户详情
     * @param id
     * @return
     */
    public CustomerInfo queryCustDetail(Integer id){
        CustomerInfo customerInfo = customerInfoMapper.selectByPrimaryKey(id);
        BusinessUtil.assertIsNull(customerInfo, ErrorCodes.BusinessEnum.CUSTOMER_IS_EMPYT);
        customerInfo.setBasicInvoice(basicInvoiceInfoMapper.selectByCustId(id));
        customerInfo.setBasicContact(basicContactMapper.selectByCustId(id));
        customerInfo.setBasicStructure(basicCorporateStructureMapper.selectByCustId(id));
        customerInfo.setSalesTeam(basicSalesTeamMapper.selectByCustId(id));
        customerInfo.setBasicShip(basicCorporateRelationshipMapper.selectByCustId(id));
        customerInfo.setSales(basicSalesMapper.selectByCustId(id));
        customerInfo.setBasicFile(basicFileMapper.selectByCustId(id));
        customerInfo.setBasicBank(basicBankInfoMapper.selectByCustId(id));
        customerInfo.setBasicAddress(basicAddressMapper.selectByCustId(id));
        return customerInfo;
    }

    /**
     * 新增或更新客户信息
     * @param bean
     * @param userId
     */
    public void addOrUpdate(CustomerInfo bean, Integer userId){
        if(bean.getId() == null){
            add(bean, userId);
        }else{
            update(bean, userId);
        }
    }
    /**
     * 新增客户信息
     * @param bean
     * @return
     */
    public void add(CustomerInfo bean, Integer userId){
        checkCustName(bean.getCustZhName());
        Date currDate = DateUtil.getCurrentTS();
        bean.setBusinessType(bean.getBusinessType() == null ? Enums.CUSTOMER_BUSINESS_TYPE.mass_market.getCode() : bean.getBusinessType());
        bean.setCustomerStatus(bean.getCustomerStatus() == null ? Enums.CustomerStatus.WAIT_SUBMIT.getCode() : bean.getCustomerStatus());
        try {
            bean.setRegisterTime(bean.getRegisterTimeStr() != null ? DateUtil.parseDate(bean.getRegisterTimeStr(), DateUtil.WEB_FORMAT) : null);
        }catch (ParseException e){
            log.error("日期转换异常", e);
        }

        bean.setActive(1);
        bean.setCreateUser(userId);
        bean.setCreateTime(currDate);
        customerInfoMapper.insertSelective(bean);

        BasicBankInfo bankInfo = bean.getBasicBank();
        bankInfo.setCustId(bean.getId());
        bankInfo.setActive(1);
        bankInfo.setCreateUser(userId);
        bankInfo.setCreateTime(currDate);
        basicBankInfoMapper.insertSelective(bean.getBasicBank());


        addExtendInfo(bean.getBasicInvoice(), basicInvoiceInfoMapper, bean);

        addExtendInfo(bean.getBasicContact(), basicContactMapper, bean);

        addExtendInfo(bean.getBasicStructure(), basicCorporateStructureMapper, bean);

        addExtendInfo(bean.getSalesTeam(), basicSalesTeamMapper, bean);

        addExtendInfo(bean.getBasicShip(), basicCorporateRelationshipMapper, bean);

        addExtendInfo(bean.getSales(), basicSalesMapper, bean);

        addExtendInfo(bean.getBasicFile(), basicFileMapper, bean);

        addExtendInfo(bean.getBasicAddress(), basicAddressMapper, bean);
    }

    public void addExtendInfo(List<? extends BaseEntity> list, BaseMapper mapper, CustomerInfo bean){
        list.stream().forEach((e)-> {
            e.setCustId(bean.getId());
            e.setActive(1);
            e.setCreateUser(bean.getCreateUser());
            e.setCreateTime(bean.getCreateTime());
            mapper.insertSelective(e);
        });
    }

    /**
     * 更新客户信息
     * @param bean
     */
    public void update(CustomerInfo bean, Integer userId){
        BusinessUtil.assertIsNull(bean.getId(), ErrorCodes.BusinessEnum.CUSTOMER_IS_EMPYT);
        bean.setUpdateUser(userId);
        bean.setUpdateTime(DateUtil.getCurrentTS());
        try {
            bean.setRegisterTime(bean.getRegisterTimeStr() != null ? DateUtil.parseDate(bean.getRegisterTimeStr(), DateUtil.NEW_FORMAT) : null);
        }catch (ParseException e){
            log.error("日期转换异常", e);
        }
        customerInfoMapper.updateByPrimaryKeySelective(bean);

        BasicBankInfo bankInfo = bean.getBasicBank();
        if(bankInfo != null && bankInfo.getId() != null){
            bankInfo.setCustId(bean.getId());
            bankInfo.setActive(1);
            bankInfo.setUpdateUser(userId);
            bankInfo.setUpdateTime(DateUtil.getCurrentTS());
            basicBankInfoMapper.updateByPrimaryKeySelective(bankInfo);
        }

        Date currTime = DateUtil.getCurrentTS();

        List<Integer> addressIds = basicAddressMapper.selectIdsByCustId(bean.getId());
        updateExtendInfo(addressIds, bean.getBasicAddress(), basicAddressMapper, userId, currTime, bean.getId());

        List<Integer> invoiceInfoIds = basicInvoiceInfoMapper.selectIdsByCustId(bean.getId());
        updateExtendInfo(invoiceInfoIds, bean.getBasicInvoice(), basicInvoiceInfoMapper, userId, currTime, bean.getId());

        List<Integer> contactIds = basicContactMapper.selectIdsByCustId(bean.getId());
        updateExtendInfo(contactIds, bean.getBasicContact(), basicContactMapper, userId, currTime, bean.getId());

        List<Integer> structureIds = basicCorporateStructureMapper.selectIdsByCustId(bean.getId());
        updateExtendInfo(structureIds, bean.getBasicStructure(), basicCorporateStructureMapper, userId, currTime, bean.getId());

        List<Integer> relationIds = basicCorporateRelationshipMapper.selectIdsByCustId(bean.getId());
        updateExtendInfo(relationIds, bean.getBasicShip(), basicCorporateRelationshipMapper, userId, currTime, bean.getId());

        List<Integer> fileIds = basicFileMapper.selectIdsByCustId(bean.getId());
        updateExtendInfo(fileIds, bean.getBasicFile(), basicFileMapper, userId, currTime, bean.getId());

        List<Integer> teamIds = basicSalesTeamMapper.selectIdsByCustId(bean.getId());
        updateExtendInfo(teamIds, bean.getSalesTeam(), basicSalesTeamMapper, userId, currTime, bean.getId());

        List<Integer> salesIds = basicSalesMapper.selectIdsByCustId(bean.getId());
        updateExtendInfo(salesIds, bean.getSales(), basicSalesMapper, userId, currTime, bean.getId());
    }

    /**
     * 更新客户扩展信息
     * @param sourceList
     * @param newList
     * @param mapper
     */
    public void updateExtendInfo(List<Integer> sourceList, List<? extends BaseEntity> newList, BaseMapper mapper, Integer userId, Date currTime, Integer custId){
        if(null == newList){
            return;
        }
        newList.stream().filter(e->e.getId()!=null && sourceList.contains(e.getId())).forEach(e->{
            e.setUpdateUser(userId);
            e.setUpdateTime(currTime);
            mapper.updateByPrimaryKeySelective(e);
        });
        newList.stream().filter(e->null == e.getId() || !sourceList.contains(e.getId())).forEach(e->{
            e.setCustId(custId);
            e.setActive(1);
            e.setCreateUser(userId);
            e.setCreateTime(currTime);
            mapper.insertSelective(e);
        });
        List<Integer> newIds = newList.stream().map(e-> e.getId()).collect(Collectors.toList());
        sourceList.stream().filter(e->!newIds.contains(e)).forEach(e->{
            mapper.deleteByPrimaryKey(e);
        });
    }

    /**
     * 删除客户
     * @param id
     * @param userId
     */
    public void delete(Integer id, Integer userId){
        CustomerInfo customerInfo = customerInfoMapper.selectByPrimaryKey(id);
        BusinessUtil.assertIsNull(customerInfo, ErrorCodes.BusinessEnum.CUSTOMER_IS_EMPYT);
        customerInfoMapper.deleteByPrimaryKey(id, userId);
        basicBankInfoMapper.deleteByCustId(id, userId);
        basicAddressMapper.deleteByCustId(id, userId);
        basicInvoiceInfoMapper.deleteByCustId(id, userId);
        basicFileMapper.deleteByCustId(id, userId);
        basicCorporateRelationshipMapper.deleteByCustId(id, userId);
        basicCorporateStructureMapper.deleteByCustId(id, userId);
        basicContactMapper.deleteByCustId(id, userId);
        basicSalesMapper.deleteByCustId(id, userId);
        basicSalesTeamMapper.deleteByCustId(id, userId);
    }

    /**
     * 重新报备
     */
    public void againReport(Integer id, Integer userId){
        CustomerInfo record = new CustomerInfo();
        record.setId(id);
        record.setUpdateUser(userId);
        record.setUpdateTime(DateUtil.getCurrentTS());
        record.setCustomerStatus(Enums.CustomerStatus.WAIT_APPROVAL.getCode());
        customerInfoMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 审批
     */
    public void approval(Integer id, Integer userId){
        CustomerInfo record = new CustomerInfo();
        record.setId(id);
        record.setUpdateUser(userId);
        record.setUpdateTime(DateUtil.getCurrentTS());
        record.setCustomerStatus(Enums.CustomerStatus.NORMAL.getCode());
        customerInfoMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 驳回
     */
    public void reject(Integer id, Integer userId){
        CustomerInfo record = new CustomerInfo();
        record.setId(id);
        record.setUpdateUser(userId);
        record.setUpdateTime(DateUtil.getCurrentTS());
        record.setCustomerStatus(Enums.CustomerStatus.REJECT.getCode());
        customerInfoMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 校验客户名是否存在
     * @param custName
     */
    public void checkCustName(String custName){
        CustomerInfo customerInfo = customerInfoMapper.selectByCustName(custName);
        BusinessUtil.assertIsNotNull(customerInfo, ErrorCodes.BusinessEnum.CUSTOMER_NAME_EXISTS);
    }
}
