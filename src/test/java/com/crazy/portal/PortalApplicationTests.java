package com.crazy.portal;

import com.alibaba.fastjson.JSON;
import com.crazy.portal.bean.customer.wsdl.orgnation.OrganisationalUnitByElementsResponseSync;
import com.crazy.portal.bean.customer.wsdl.orgnation.OrganisationalUnitByIDResponseMessageSync;
import com.crazy.portal.bean.customer.wsdl.orgnation.OrganisationalUnitQueryRepsonseNameAndAddress;
import com.crazy.portal.bean.customer.wsdl.orgnation.OrganisationalUnitQueryResponseEmployeeAssignment;
import com.crazy.portal.bean.order.wsdl.delivery.update.ZrfcsddeliverychangeResponse;
import com.crazy.portal.config.email.EmailHelper;
import com.crazy.portal.dao.system.InternalUserMapper;
import com.crazy.portal.dao.system.OrganizationalStructureMapper;
import com.crazy.portal.entity.order.DeliverOrder;
import com.crazy.portal.entity.order.DeliverOrderLine;
import com.crazy.portal.entity.system.InternalUser;
import com.crazy.portal.entity.system.OrganizationalStructure;
import com.crazy.portal.service.order.OrderService;
import com.crazy.portal.util.CallApiUtils;
import com.crazy.portal.util.FTPClientUtil;
import org.hibernate.validator.constraints.pl.REGON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PortalApplicationTests {

	@Resource
	private EmailHelper emailHelper;

	@Resource
	private FTPClientUtil ftpClientUtil;

	@Resource
	private OrganizationalStructureMapper organizationalStructureMapper;
	@Resource
	private InternalUserMapper internalUserMapper;
	@Resource
	private OrderService orderService;

	@Test
	public void createUser(){
		//System.out.println(JSON.toJSONString(CallApiUtils.queryOrganisation()));
		//CallApiUtils.callECCCreditApi("10217");
	}

	@Test
	public void test(){
		DeliverOrder order = new DeliverOrder();
		order.setDeliverDate(new Date());
		order.setSapDeliverOrderNo("84632");
		List<DeliverOrderLine > deliverOrderLineList  = new ArrayList<>();
		DeliverOrderLine line = new DeliverOrderLine();
		line.setSapSalesOrderLineNo("10");
		line.setReceiveQuantity(10);

		String type = "D";

//		ZrfcsddeliverychangeResponse response = orderService.eccDeliveryUpdate(order,deliverOrderLineList,type);
		/*try{
			OrganisationalUnitByIDResponseMessageSync responseMessageSync = CallApiUtils.queryOrganisation();
			List<OrganisationalUnitByElementsResponseSync> results = responseMessageSync.getOrganisationalUnit();
			for(OrganisationalUnitByElementsResponseSync org : results){
				OrganizationalStructure organizational = organizationalStructureMapper.selectByOrgNo(org.getID());
				if(null == organizational){
					organizational = new OrganizationalStructure();
				}
				organizational.setActive(1);
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
						organizational.setPm(internalUser==null?"":internalUser.getUserName());
					}
				}
				if(null != organizational.getId()){
					organizationalStructureMapper.updateByPrimaryKeySelective(organizational);
				}else{
					organizationalStructureMapper.insertSelective(organizational);
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}*/
	}
}
