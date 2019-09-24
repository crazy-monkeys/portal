package com.crazy.portal.service.system;

import com.crazy.portal.bean.customer.CustomerOrgBean;
import com.crazy.portal.config.exception.BusinessException;
import com.crazy.portal.dao.system.InternalUserMapper;
import com.crazy.portal.dao.system.OrganizationalStructureMapper;
import com.crazy.portal.entity.cusotmer.CustZrAccountTeam;
import com.crazy.portal.entity.system.InternalUser;
import com.crazy.portal.entity.system.OrganizationalStructure;
import com.crazy.portal.service.customer.CustZrAccountTeamService;
import com.crazy.portal.util.BusinessUtil;
import com.crazy.portal.util.ErrorCodes;
import com.crazy.portal.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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
    private OrganizationalStructureMapper organizationalStructureMapper;
    @Resource
    private InternalUserMapper internalUserMapper;


    public InternalUser getUserPosition(Integer userId){
        return internalUserMapper.selectByUserId(userId);
    }
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
            //普通销售 看同一个战队的销售客户
            return internalUserMapper.selectUserbyOrg(salesOrg.getSeq().toString());
        }else{
            //非销售
            return null;
        }
        //OrganizationalStructure userOrg = organizationalStructureMapper.selectByOrgNo(Integer.valueOf(user.getUserDepartmentCode()));
    }


    public CustomerOrgBean getSalesInfo(Integer custId){
        CustomerOrgBean customerOrgBean = new CustomerOrgBean();

        CustZrAccountTeam custZrAccountTeam = custZrAccountTeamService.selectZRByCustId(custId);
        InternalUser sales = internalUserMapper.selectByUserNo(custZrAccountTeam.getEmployeeId());
        customerOrgBean.setSales(sales.getUserName());

        OrganizationalStructure ambOrg = organizationalStructureMapper.selectByOrgNo(Integer.valueOf(sales.getUserDepartmentCode()));
        InternalUser amb =  internalUserMapper.selectByUserNo(ambOrg.getPm());
        customerOrgBean.setAmb(amb.getUserName());

        OrganizationalStructure office = organizationalStructureMapper.selectByOrgNo(ambOrg.getParentOrg());
        customerOrgBean.setOffice(office.getOrgName());

        InternalUser pm =  internalUserMapper.selectByUserNo(office.getPm());
        customerOrgBean.setPm(pm.getUserName());

        return customerOrgBean;
    }
}
