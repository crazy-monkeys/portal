package com.crazy.portal.service.customer;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.crazy.portal.bean.common.Constant;
import com.crazy.portal.bean.customer.CustomerQueryBean;
import com.crazy.portal.bean.customer.basic.FileVO;
import com.crazy.portal.bean.customer.visitRecord.CustomerCodeEO;
import com.crazy.portal.bean.customer.visitRecord.VisitRecordEO;
import com.crazy.portal.bean.customer.visitRecord.VisitRecordQueryBean;
import com.crazy.portal.dao.basic.*;
import com.crazy.portal.dao.customer.CustomerInfoMapper;
import com.crazy.portal.dao.customer.DealerReportMapper;
import com.crazy.portal.dao.customer.VisitRecordMapper;
import com.crazy.portal.entity.basic.BaseEntity;
import com.crazy.portal.entity.basic.BasicBankInfo;
import com.crazy.portal.entity.basic.BasicFile;
import com.crazy.portal.entity.customer.CustomerInfo;
import com.crazy.portal.entity.customer.DealerReport;
import com.crazy.portal.entity.customer.VisitRecord;
import com.crazy.portal.entity.system.User;
import com.crazy.portal.util.*;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.ParseException;
import java.util.*;
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
    @Resource
    private VisitRecordMapper visitRecordMapper;
    @Resource
    private DealerReportMapper dealerReportMapper;
    @Value("${file.path.root}")
    private String filePath;

    private static final String CUST_FILE_PATH = "custfile";
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
     * @param user
     */
    public void addOrUpdate(CustomerInfo bean, User user){
        if(bean.getId() == null){
            add(bean, user);
        }else{
            update(bean, user);
        }
    }
    /**
     * 新增客户信息
     * @param bean
     * @return
     */
    public void add(CustomerInfo bean, User user){
        checkCustName(bean.getCustName());
        Date currDate = DateUtil.getCurrentTS();
        bean.setBusinessType(bean.getBusinessType() == null ? Enums.CUSTOMER_BUSINESS_TYPE.mass_market.getCode() : bean.getBusinessType());
        bean.setCustomerStatus(bean.getCustomerStatus() == null ? Enums.CustomerStatus.WAIT_SUBMIT.getCode() : bean.getCustomerStatus());
        try {
            bean.setRegisterTime(bean.getRegisterTimeStr() != null ? DateUtil.parseDate(bean.getRegisterTimeStr(), DateUtil.WEB_FORMAT) : null);
        }catch (ParseException e){
            log.error("日期转换异常", e);
        }

        bean.setActive(Constant.ACTIVE);
        bean.setCreateUser(user.getId());
        bean.setCreateTime(currDate);
        customerInfoMapper.insertSelective(bean);

        BasicBankInfo bankInfo = bean.getBasicBank();
        bankInfo.setCustId(bean.getId());
        bankInfo.setActive(Constant.ACTIVE);
        bankInfo.setCreateUser(user.getId());
        bankInfo.setCreateTime(currDate);
        basicBankInfoMapper.insertSelective(bean.getBasicBank());

        DealerReport dealerReport = new DealerReport();
        dealerReport.setDealerId(user.getDealerId());
        dealerReport.setCustId(bean.getId());
        dealerReport.setDealerStatus(Enums.DealerStatus.TAKE_EFFECT.getCode());
        dealerReport.setActive(Constant.ACTIVE);
        dealerReport.setCreateUserId(user.getId());
        dealerReport.setCreateTime(DateUtil.getCurrentTS());
        dealerReportMapper.insertSelective(dealerReport);

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
        list.forEach((e)-> {
            e.setCustId(bean.getId());
            e.setActive(Constant.ACTIVE);
            e.setCreateUser(bean.getCreateUser());
            e.setCreateTime(bean.getCreateTime());
            mapper.insertSelective(e);
        });
    }

    /**
     * 更新客户信息
     * @param bean
     */
    public void update(CustomerInfo bean, User user){
        BusinessUtil.assertIsNull(bean.getId(), ErrorCodes.BusinessEnum.CUSTOMER_IS_EMPYT);
        bean.setUpdateUser(user.getId());
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
            bankInfo.setActive(Constant.ACTIVE);
            bankInfo.setUpdateUser(user.getId());
            bankInfo.setUpdateTime(DateUtil.getCurrentTS());
            basicBankInfoMapper.updateByPrimaryKeySelective(bankInfo);
        }

        Date currTime = DateUtil.getCurrentTS();

        List<Integer> addressIds = basicAddressMapper.selectIdsByCustId(bean.getId());
        updateExtendInfo(addressIds, bean.getBasicAddress(), basicAddressMapper, user.getId(), currTime, bean.getId());

        List<Integer> invoiceInfoIds = basicInvoiceInfoMapper.selectIdsByCustId(bean.getId());
        updateExtendInfo(invoiceInfoIds, bean.getBasicInvoice(), basicInvoiceInfoMapper, user.getId(), currTime, bean.getId());

        List<Integer> contactIds = basicContactMapper.selectIdsByCustId(bean.getId());
        updateExtendInfo(contactIds, bean.getBasicContact(), basicContactMapper, user.getId(), currTime, bean.getId());

        List<Integer> structureIds = basicCorporateStructureMapper.selectIdsByCustId(bean.getId());
        updateExtendInfo(structureIds, bean.getBasicStructure(), basicCorporateStructureMapper, user.getId(), currTime, bean.getId());

        List<Integer> relationIds = basicCorporateRelationshipMapper.selectIdsByCustId(bean.getId());
        updateExtendInfo(relationIds, bean.getBasicShip(), basicCorporateRelationshipMapper, user.getId(), currTime, bean.getId());

        List<Integer> fileIds = basicFileMapper.selectIdsByCustId(bean.getId());
        updateExtendInfo(fileIds, bean.getBasicFile(), basicFileMapper, user.getId(), currTime, bean.getId());

        List<Integer> teamIds = basicSalesTeamMapper.selectIdsByCustId(bean.getId());
        updateExtendInfo(teamIds, bean.getSalesTeam(), basicSalesTeamMapper, user.getId(), currTime, bean.getId());

        List<Integer> salesIds = basicSalesMapper.selectIdsByCustId(bean.getId());
        updateExtendInfo(salesIds, bean.getSales(), basicSalesMapper, user.getId(), currTime, bean.getId());
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
            e.setActive(Constant.ACTIVE);
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

    /**
     * 下载客户拜访模板
     * @param userId
     * @return
     */
    public Map<String, List<? extends BaseRowModel>> downloadTemplate(Integer userId){
        Map<String, List<? extends BaseRowModel>> resultMap = new HashMap<>();
        List<CustomerInfo> custList = customerInfoMapper.selectNameAndCodeByUserId(userId);
        List<CustomerCodeEO> custCodeList = new ArrayList<>();
        custList.forEach(cust -> {
            CustomerCodeEO eo = new CustomerCodeEO();
            eo.setCustomerName(cust.getCustName());
            eo.setCustomerCode(cust.getCustInCode());
            custCodeList.add(eo);
        });
        List<VisitRecordEO> visitRecordList = new ArrayList<>();
        visitRecordList.add(new VisitRecordEO());
        resultMap.put("模板", visitRecordList);
        resultMap.put("客户", custCodeList);
        return resultMap;
    }

    /**
     * 查询客户拜访记录
     * @param bean
     * @return
     */
    public PageInfo<VisitRecord> selectVisitRecordPage(VisitRecordQueryBean bean){
        PortalUtil.defaultStartPage(bean.getPageIndex(), bean.getPageSize());
        List<VisitRecord> records = visitRecordMapper.selectByPage(bean);
        return new PageInfo<>(records);
    }

    /**
     * 上传客户拜访记录
     * @param files
     * @param userId
     * @throws Exception
     */
    public void uploadVisitRecord(MultipartFile[] files, Integer userId) throws Exception{
        for (MultipartFile file : files) {
            List<Object> records = EasyExcelFactory.read(file.getInputStream(), new Sheet(1, 1,VisitRecordEO.class));
            records.forEach(e->{
                try {
                    VisitRecord record = new VisitRecord();
                    BeanUtils.copyNotNullFields(e , record);
                    String excelVisitDate = BeanUtils.getFieldValueByName("visitDate", e).toString();
                    record.setVisitDate(DateUtil.getFlexibleDate(excelVisitDate));
                    record.setActive(Constant.ACTIVE);
                    record.setCreateUserId(userId);
                    record.setCreateTime(DateUtil.getCurrentTS());
                    visitRecordMapper.insertSelective(record);
                } catch (Exception ex) {
                    log.error("保存拜访记录异常", ex);
                }
            });

        }
    }

    /**
     * 文件上传
     * @param files
     * @return
     */
    public List<FileVO> fileUpload(MultipartFile[] files){
        List<FileVO> fileList = FileUtil.upload(files, getCustFilePath());
        return fileList;
    }

    /**
     * 文件下载
     * @param response
     */
    public void fileDownload(HttpServletResponse response, Integer id){
        BasicFile file = basicFileMapper.selectByPrimaryKey(id);
        FileUtil.download(response, getCustFilePath(), file.getFileName());
    }

    public String getCustFilePath(){
        return filePath.concat(File.separator).concat(CUST_FILE_PATH);
    }
}
