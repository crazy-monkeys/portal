package com.crazy.portal.task.quartz;

import com.crazy.portal.bean.customer.wsdl.employee.*;
import com.crazy.portal.annotation.Task;
import com.crazy.portal.dao.system.InternalUserMapper;
import com.crazy.portal.entity.system.InternalUser;
import com.crazy.portal.util.CallApiUtils;
import com.crazy.portal.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: InternalUserJob
 * @Author: God Man Qiu~
 * @Date: 2019/9/6 14:27
 */
@Task(value = "人员数据同步",scheduleCode = "internalUser_Sync")
@Slf4j
@DisallowConcurrentExecution
public class InternalUserJob implements Job{
    @Resource
    private InternalUserMapper internalUserMapper;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            EmployeeBasicDataResponseMessageSync responseMessageSync = CallApiUtils.querEmployee();
            if(null != responseMessageSync){
                List<EmployeeReponseBasicData>  employeeReponseBasicDatas =responseMessageSync.getBasicData()==null?new ArrayList<>():responseMessageSync.getBasicData();
                employeeReponseBasicDatas.forEach(e->{
                    InternalUser internalUser = internalUserMapper.selectByUserNo(e.getEmployeeID().getValue());
                    if(null == internalUser){
                        internalUser = new InternalUser();
                        internalUser.setActive(1);
                    }
                    internalUser.setUserNo(e.getEmployeeID().getValue());

                    List<EmployeeDataReponseBiograhicalData> biograhicalData = e.getBiographicalData()==null?new ArrayList<>():e.getBiographicalData();
                    for(EmployeeDataReponseBiograhicalData b : biograhicalData){
                        internalUser.setUserName(b.getFamilyName()+b.getGivenName());
                    }

                    EmployeeReponseEmployeeWorkplaceAddressInformation addressInformation = e.getWorkplaceAddressInformation();
                    if(null != addressInformation){
                        EmployeeReponseEmployeeWorkplaceAddress address = addressInformation.getAddress();
                        if(null != address){
                            if(null != address.getEmailURI()){
                                internalUser.setUserMail(address.getEmailURI().getValue());
                            }
                            if(StringUtil.isNotEmpty(address.getMobilePhone())){
                                internalUser.setUserMobile(address.getMobilePhone().length()>11?address.getMobilePhone().substring(address.getMobilePhone().length()-11):address.getMobilePhone());
                            }

                        }
                    }

                    List<EmployeeOrganisationalAssignment> organisationalAssignment  = e.getEmployeeOrganisationalAssignment()==null?new ArrayList<>():e.getEmployeeOrganisationalAssignment();
                    for(EmployeeOrganisationalAssignment o : organisationalAssignment){
                        CLOSEDDatePeriod period = o.getValidityPeriod();
                        if(null != period){
                            internalUser.setUserDepartmentCode(o.getOrganisationalCentreID());
                        }
                    }

                    if(null != internalUser.getInUserId()){
                        internalUserMapper.updateByPrimaryKeySelective(internalUser);
                    }else{
                        internalUserMapper.insertSelective(internalUser);
                    }
                });

            }
        }catch (Exception e){
            log.error("人员数据同步异常", e);
        }
    }
}
