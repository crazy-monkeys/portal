package com.crazy.portal.service.system;

import com.crazy.portal.bean.customer.CustomerOrgBean;
import com.crazy.portal.dao.cusotmer.CustomerInfoMapper;
import com.crazy.portal.dao.system.InternalUserMapper;
import com.crazy.portal.dao.system.OrganizationalStructureMapper;
import com.crazy.portal.entity.cusotmer.CustZrAccountTeam;
import com.crazy.portal.entity.cusotmer.CustomerInfo;
import com.crazy.portal.entity.system.InternalUser;
import com.crazy.portal.entity.system.OrganizationalStructure;
import com.crazy.portal.service.customer.CustZrAccountTeamService;
import com.crazy.portal.util.BusinessUtil;
import com.crazy.portal.util.ErrorCodes;
import com.crazy.portal.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @ClassName: InternalUserService
 * @Author: God Man Qiu~
 * @Date: 2019/9/14 14:57
 */
@Slf4j
@Service
public class InternalUserService {
    @Resource
    private CustZrAccountTeamService custZrAccountTeamService;
    @Resource
    private CustomerInfoMapper customerInfoMapper;
    @Resource
    private OrganizationalStructureMapper organizationalStructureMapper;
    @Resource
    private InternalUserMapper internalUserMapper;

    /**
     * 获取用户的权限职位信息
     * @param userName
     * @return
     */
    public OrganizationalStructure getUserOrg(String userName){
        InternalUser user = internalUserMapper.selectUserByName(userName);
        BusinessUtil.assertFlase(null == user,ErrorCodes.SystemManagerEnum.USER_NOT_EXISTS);
        return organizationalStructureMapper.selectByOrgNo(StringUtil.isEmpty(user.getUserDepartmentCode())?0:Integer.valueOf(user.getUserDepartmentCode()));
    }

    /**
     * 获取销售的团队人员
     * @param
     * @return
     */
    public List<String> getSalesTeam(InternalUser internalUser){
        OrganizationalStructure salesOrg = organizationalStructureMapper.selectSalesOrg(internalUser.getUserDepartmentCode());
        if(null != salesOrg && salesOrg.getPm().equals(internalUser.getUserNo())){
            if(salesOrg.getSeq()==1001013 || salesOrg.getSeq()==1001014){
                return internalUserMapper.selectUserbyParenOrg(salesOrg.getSeq().toString());
            }else{
                return internalUserMapper.selectUserbyOrg(salesOrg.getSeq().toString());
            }
        }else if(null != salesOrg){
            /*//普通销售 看同一个战队的销售客户
            return internalUserMapper.selectUserbyOrg(salesOrg.getSeq().toString());*/
            return Arrays.asList(internalUser.getUserNo());
        }else{
            //非销售
            return null;
        }
    }

    //获取销售组织
    public CustomerOrgBean getSalesInfo(Integer custId){
        CustomerOrgBean customerOrgBean = new CustomerOrgBean();

        //获取销售
        CustZrAccountTeam custZrAccountTeam = custZrAccountTeamService.selectZRByCustId(custId);
        InternalUser sales = internalUserMapper.selectByUserNo(custZrAccountTeam.getEmployeeId());

        customerOrgBean.setSales(sales.getUserName());
        customerOrgBean.setOffice(sales.getUserDepartmentName());

        OrganizationalStructure org = organizationalStructureMapper.selectByOrgNo(Integer.valueOf(sales.getUserDepartmentCode()));
        if(org.getSeq().equals(1001013) || org.getSeq().equals(1001014)){
            customerOrgBean.setAmb(sales.getUserName());
            customerOrgBean.setPm(sales.getUserName());
        }else{
            InternalUser amb =  internalUserMapper.selectByUserNo(org.getPm());
            customerOrgBean.setAmb(amb.getUserName());
            customerOrgBean.setOffice(org.getParentOrgName());

            OrganizationalStructure office = organizationalStructureMapper.selectByOrgNo(org.getParentOrg());
            InternalUser pm =  internalUserMapper.selectByUserNo(office.getPm());
            customerOrgBean.setPm(pm.getUserName());
        }

        return customerOrgBean;
    }

    //获取销售负责的客户id 集合
    public List<CustomerInfo> getSalesCustomer(Integer userId){
        InternalUser internalUser = internalUserMapper.selectByUserId(userId);
        BusinessUtil.assertFlase(null == internalUser,ErrorCodes.SystemManagerEnum.SYS_IN_USER_ERROR);

        List<String> userNos = new ArrayList<>();
        OrganizationalStructure salesOrg = organizationalStructureMapper.selectSalesOrg(internalUser.getUserDepartmentCode());
        if(null != salesOrg && salesOrg.getPm().equals(internalUser.getUserNo())){
            if(salesOrg.getSeq()==1001013 || salesOrg.getSeq()==1001014){
                userNos = internalUserMapper.selectUserbyParenOrg(salesOrg.getSeq().toString());
            }else{
                userNos = internalUserMapper.selectUserbyOrg(salesOrg.getSeq().toString());
            }
        }else if(null != salesOrg){
            //普通销售 只能看自己的客户
            userNos.add(internalUser.getUserNo());
        }else{
            //非销售
            return null;
        }

        List<CustomerInfo> customerInfos = new ArrayList<>();
        userNos.forEach(e->{
            List<CustomerInfo> salesTeams = customerInfoMapper.selectByEmp(e);
            customerInfos.addAll(salesTeams);
        });

        return customerInfos;
    }

    //获取销售信息
    public Map<String,String> getSales(Integer userId){
        Map<String,String> salesMap = new HashMap<>();

        InternalUser internalUser = internalUserMapper.selectByUserId(userId);
        BusinessUtil.assertFlase(null == internalUser,ErrorCodes.SystemManagerEnum.SYS_IN_USER_ERROR);

        OrganizationalStructure salesOrg = organizationalStructureMapper.selectSalesOrg(internalUser.getUserDepartmentCode());
        if(null != salesOrg && salesOrg.getPm().equals(internalUser.getUserNo())){
            if(salesOrg.getSeq()==1001013 || salesOrg.getSeq()==1001014){
                salesMap.put("sd",internalUser.getUserName());
            }else{
                salesMap.put("amb",internalUser.getUserName());
            }
        }else if(null != salesOrg){
            //普通销售 只能看自己的客户
            salesMap.put("sales",internalUser.getUserName());
        }else{
            //非销售
            return null;
        }
        return salesMap;
    }
}
