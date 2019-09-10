package com.crazy.portal.task.quartz;

import com.crazy.portal.bean.customer.wsdl.orgnation.OrganisationalUnitByElementsResponseSync;
import com.crazy.portal.bean.customer.wsdl.orgnation.OrganisationalUnitByIDResponseMessageSync;
import com.crazy.portal.bean.customer.wsdl.orgnation.OrganisationalUnitQueryRepsonseNameAndAddress;
import com.crazy.portal.bean.customer.wsdl.orgnation.OrganisationalUnitQueryResponseEmployeeAssignment;
import com.crazy.portal.config.quartz.annotation.Task;
import com.crazy.portal.dao.system.InternalUserMapper;
import com.crazy.portal.dao.system.OrganizationalStructureMapper;
import com.crazy.portal.entity.system.InternalUser;
import com.crazy.portal.entity.system.OrganizationalStructure;
import com.crazy.portal.util.CallApiUtils;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: OrganisationalJob
 * @Author: God Man Qiu~
 * @Date: 2019/9/9 22:32
 */
@Task(value = "部门信息同步",scheduleCode = "organisational_Sync")
@Slf4j
@DisallowConcurrentExecution
public class OrganisationalJob implements Job {
    @Resource
    private OrganizationalStructureMapper organizationalStructureMapper;
    @Resource
    private InternalUserMapper internalUserMapper;
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try{
            OrganisationalUnitByIDResponseMessageSync responseMessageSync = CallApiUtils.queryOrganisation();
            List<OrganisationalUnitByElementsResponseSync> results = responseMessageSync.getOrganisationalUnit();
            for(OrganisationalUnitByElementsResponseSync org : results){
                OrganizationalStructure organizational = organizationalStructureMapper.selectByOrgNo(org.getID());
                if(null == organizational){
                    organizational = new OrganizationalStructure();
                    organizational.setActive(1);
                }
                organizational.setSeq(Integer.valueOf(org.getID()));
                if(null != org.getValidityPeriod()){
                    String startDate = org.getValidityPeriod().getStartDate().getYear()+"-"+org.getValidityPeriod().getStartDate().getMonth()+"-"+org.getValidityPeriod().getStartDate().getDay();
                    String endDate = org.getValidityPeriod().getEndDate().getYear()+"-"+org.getValidityPeriod().getEndDate().getMonth()+"-"+org.getValidityPeriod().getEndDate().getDay();
                    organizational.setValidTime(startDate);
                    organizational.setInvalidTime(endDate);
                }
                List<OrganisationalUnitQueryRepsonseNameAndAddress> names = org.getNameAndAddress();
                for(OrganisationalUnitQueryRepsonseNameAndAddress name : names){
                    organizational.setOrgName(name.getName());
                }
                List<OrganisationalUnitQueryResponseEmployeeAssignment> emps = org.getEmployeeAssignment();
                for(OrganisationalUnitQueryResponseEmployeeAssignment emp : emps){
                    if(null != emp.getRoleCode()){
                        organizational.setParentOrg(Integer.valueOf(emp.getRoleCode()));
                    }
                    if(null != emp.getEmployeeInternalID()){
                        InternalUser internalUser = internalUserMapper.selectByUserNo(emp.getEmployeeInternalID());
                        organizational.setPm(internalUser.getUserName());
                    }
                }
                if(null != organizational.getId()){
                    organizationalStructureMapper.updateByPrimaryKeySelective(organizational);
                }else{
                    organizationalStructureMapper.insertSelective(organizational);
                }
            }
        }catch (Exception e){
            log.error("部门信息同步");
        }
    }
}
