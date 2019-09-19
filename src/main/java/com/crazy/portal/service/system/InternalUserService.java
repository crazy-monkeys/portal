package com.crazy.portal.service.system;

import com.crazy.portal.bean.customer.CustomerOrgBean;
import com.crazy.portal.dao.system.InternalUserMapper;
import com.crazy.portal.dao.system.OrganizationalStructureMapper;
import com.crazy.portal.entity.cusotmer.CustZrAccountTeam;
import com.crazy.portal.entity.system.InternalUser;
import com.crazy.portal.entity.system.OrganizationalStructure;
import com.crazy.portal.service.customer.CustZrAccountTeamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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

    /**
     * 获取用户的权限职位信息
     * @param userId
     * @return
     */
    public InternalUser getUserPosition(Integer userId){
        return internalUserMapper.selectByUserId(userId);
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
