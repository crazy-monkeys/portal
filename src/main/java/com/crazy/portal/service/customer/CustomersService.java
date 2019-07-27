package com.crazy.portal.service.customer;

import com.crazy.portal.bean.customer.CustomerQueryBean;
import com.crazy.portal.config.exception.BusinessException;
import com.crazy.portal.dao.basic.*;
import com.crazy.portal.dao.customer.*;
import com.crazy.portal.entity.basic.BaseEntity;
import com.crazy.portal.entity.basic.TBasicBankInfoDO;
import com.crazy.portal.entity.basic.TBasicInvoiceInfoDO;
import com.crazy.portal.entity.customer.TCustomerInfoDO;
import com.crazy.portal.util.DateUtil;
import com.crazy.portal.util.Enums;
import com.crazy.portal.util.PortalUtil;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 客户管理
 * @Author Shawn
 * @Date 2019-07-27
 */
@Slf4j
@Service
public class CustomersService {

    @Resource
    private TCustomerInfoDOMapper tCustomerInfoDOMapper;
    @Resource
    private TBasicInvoiceInfoDOMapper tBasicInvoiceInfoDOMapper;
    @Resource
    private TBasicCorporateRelationshipDOMapper tBasicCorporateRelationshipDOMapper;
    @Resource
    private TBasicCorporateStructureDOMapper tBasicCorporateStructureDOMapper;
    @Resource
    private TBasicFileDOMapper tBasicFileDOMapper;
    @Resource
    private TBasicSalesDOMapper tBasicSalesDOMapper;
    @Resource
    private TBasicSalesTeamDOMapper tBasicSalesTeamDOMapper;
    @Resource
    private TBasicContactDOMapper tBasicContactDOMapper;
    @Resource
    private TBasicBankInfoDOMapper tBasicBankInfoDOMapper;
    @Resource
    private TBasicAddressDOMapper tBasicAddressDOMapper;
    /**
     * 分页查询客户列表
     * @param bean
     * @return
     */
    public PageInfo<TCustomerInfoDO> queryCustByPage(CustomerQueryBean bean){
        PortalUtil.defaultStartPage(bean.getPageIndex(), bean.getPageSize());
        List<TCustomerInfoDO> list = tCustomerInfoDOMapper.selectCustByPage(bean);
        return new PageInfo<>(list);
    }

    /**
     * 查询客户详情
     * @param id
     * @return
     */
    public TCustomerInfoDO queryCustDetail(Integer id){
        TCustomerInfoDO customerInfo = tCustomerInfoDOMapper.selectByPrimaryKey(id);
        customerInfo.setBasicInvoice(tBasicInvoiceInfoDOMapper.selectByCustId(id));
        customerInfo.setBasicContact(tBasicContactDOMapper.selectByCustId(id));
        customerInfo.setBasicStructure(tBasicCorporateStructureDOMapper.selectByCustId(id));
        customerInfo.setSalesTeam(tBasicSalesTeamDOMapper.selectByCustId(id));
        customerInfo.setBasicShip(tBasicCorporateRelationshipDOMapper.selectByCustId(id));
        customerInfo.setSales(tBasicSalesDOMapper.selectByCustId(id));
        customerInfo.setBasicFile(tBasicFileDOMapper.selectByCustId(id));
        customerInfo.setBasicBank(tBasicBankInfoDOMapper.selectByCustId(id));
        customerInfo.setBasicAddress(tBasicAddressDOMapper.selectByCustId(id));
        return customerInfo;
    }

    public void addOrUpdate(TCustomerInfoDO bean, Integer userId){
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
    public void add(TCustomerInfoDO bean, Integer userId){
        if(bean.getId() != null){
            throw new BusinessException(1001, "客户已存在");
        }
        bean.setCreateUser(userId);
        bean.setCreateTime(DateUtil.getCurrentTS());
        tCustomerInfoDOMapper.insertSelective(bean);

        TBasicBankInfoDO bankInfo = bean.getBasicBank();
        bankInfo.setCreateUser(userId);
        bankInfo.setCreateTime(DateUtil.getCurrentTS());
        tBasicBankInfoDOMapper.insertSelective(bean.getBasicBank());

        bean.getBasicInvoice().stream().forEach(e->tBasicInvoiceInfoDOMapper.insertSelective(e));
        bean.getBasicContact().stream().forEach(e->tBasicContactDOMapper.insertSelective(e));
        bean.getBasicStructure().stream().forEach(e->tBasicCorporateStructureDOMapper.insertSelective(e));
        bean.getSalesTeam().stream().forEach(e->tBasicSalesTeamDOMapper.insertSelective(e));
        bean.getBasicShip().stream().forEach(e->tBasicCorporateRelationshipDOMapper.insertSelective(e));
        bean.getSales().stream().forEach(e->tBasicSalesDOMapper.insertSelective(e));
        bean.getBasicFile().stream().forEach(e->tBasicFileDOMapper.insertSelective(e));
        bean.getBasicAddress().stream().forEach(e->tBasicAddressDOMapper.insertSelective(e));
    }

    /**
     * 更新客户信息
     * @param bean
     */
    public void update(TCustomerInfoDO bean, Integer userId){
        if(bean.getId() == null){
            throw new BusinessException(1002, "客户不存在");
        }
        bean.setUpdateUser(userId);
        bean.setUpdateTime(DateUtil.getCurrentTS());
        tCustomerInfoDOMapper.updateByPrimaryKeySelective(bean);

        TBasicBankInfoDO bankInfo = bean.getBasicBank();
        if(bankInfo != null && bankInfo.getId() != null){
            bankInfo.setUpdateUser(userId);
            bankInfo.setUpdateTime(DateUtil.getCurrentTS());
            tBasicBankInfoDOMapper.updateByPrimaryKeySelective(bankInfo);
        }

        List<Integer> addressIds = tBasicAddressDOMapper.selectIdsByCustId(bean.getId());
        updateExtendInfo(addressIds, bean.getBasicAddress(), tBasicAddressDOMapper);

        List<Integer> invoiceInfoIds = tBasicInvoiceInfoDOMapper.selectIdsByCustId(bean.getId());
        updateExtendInfo(invoiceInfoIds, bean.getBasicInvoice(), tBasicInvoiceInfoDOMapper);

        List<Integer> contactIds = tBasicContactDOMapper.selectIdsByCustId(bean.getId());
        updateExtendInfo(contactIds, bean.getBasicContact(), tBasicContactDOMapper);

        List<Integer> structureIds = tBasicCorporateStructureDOMapper.selectIdsByCustId(bean.getId());
        updateExtendInfo(structureIds, bean.getBasicContact(), tBasicCorporateStructureDOMapper);

        List<Integer> relationIds = tBasicCorporateRelationshipDOMapper.selectIdsByCustId(bean.getId());
        updateExtendInfo(relationIds, bean.getBasicShip(), tBasicCorporateRelationshipDOMapper);

        List<Integer> fileIds = tBasicFileDOMapper.selectIdsByCustId(bean.getId());
        updateExtendInfo(fileIds, bean.getBasicFile(), tBasicFileDOMapper);

        List<Integer> teamIds = tBasicSalesTeamDOMapper.selectIdsByCustId(bean.getId());
        updateExtendInfo(teamIds, bean.getSalesTeam(), tBasicSalesTeamDOMapper);

        List<Integer> salesIds = tBasicSalesDOMapper.selectIdsByCustId(bean.getId());
        updateExtendInfo(salesIds, bean.getSales(), tBasicSalesDOMapper);
    }

    public void updateExtendInfo(List<Integer> sourceList, List<? extends BaseEntity> newList, BaseMapper mapper){
        newList.stream().filter(e->sourceList.contains(e.getId()))
                .forEach(e->mapper.updateByPrimaryKeySelective(e));
        newList.stream().filter(e->!sourceList.contains(e.getId())).forEach(e->mapper.insertSelective(e));
        List<Integer> newIds = newList.stream().map(e-> e.getId()).collect(Collectors.toList());
        sourceList.stream().filter(e->!newIds.contains(e)).forEach(e->mapper.deleteByPrimaryKey(e));
    }

    /**
     * 删除客户
     * @param id
     * @param userId
     */
    public void delete(Integer id, Integer userId){
        tCustomerInfoDOMapper.deleteByPrimaryKey(id, userId);
        tBasicBankInfoDOMapper.deleteByCustId(id, userId);
        tBasicAddressDOMapper.deleteByCustId(id, userId);
        tBasicInvoiceInfoDOMapper.deleteByCustId(id, userId);
        tBasicFileDOMapper.deleteByCustId(id, userId);
        tBasicCorporateRelationshipDOMapper.deleteByCustId(id, userId);
        tBasicCorporateStructureDOMapper.deleteByCustId(id, userId);
        tBasicContactDOMapper.deleteByCustId(id, userId);
        tBasicSalesDOMapper.deleteByCustId(id, userId);
        tBasicSalesTeamDOMapper.deleteByCustId(id, userId);
    }

    /**
     * 重新报备
     */
    public void againReport(Integer id){
        TCustomerInfoDO record = new TCustomerInfoDO();
        record.setId(id);
        record.setCustomerStatus(Enums.CustomerStatus.WAIT_APPROVAL.getCode());
        tCustomerInfoDOMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 审批
     */
    public void approval(Integer id){
        TCustomerInfoDO record = new TCustomerInfoDO();
        record.setId(id);
        record.setCustomerStatus(Enums.CustomerStatus.NORMAL.getCode());
        tCustomerInfoDOMapper.updateByPrimaryKeySelective(record);
    }
    /**
     * 驳回
     */
    public void reject(Integer id){
        TCustomerInfoDO record = new TCustomerInfoDO();
        record.setId(id);
        record.setCustomerStatus(Enums.CustomerStatus.REJECT.getCode());
        tCustomerInfoDOMapper.updateByPrimaryKeySelective(record);
    }
}
