/*
package com.crazy.portal.service.customer;

import com.crazy.portal.bean.customer.wsdl.customer.detail.ProductInfo;
import com.crazy.portal.bean.customer.wsdl.customer.info.*;
import com.crazy.portal.bean.customer.wsdl.visits.VisitCreateHeader;
import com.crazy.portal.entity.cusotmer.*;
import com.crazy.portal.util.JaxbXmlUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

*/
/**
 * @ClassName: createCustomer
 * @Author: God Man Qiu~
 * @Date: 2019/9/15 16:21
 *//*

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class createCustomer {
    @Test
    public void test(){
        Customer customer = new Customer();
        customer.setActionCode("02");
        //customer.setInternalID("B100605");

        Organisation organisation = new Organisation();
        organisation.setFirstLineName("portal_test");
        customer.setOrganisation(organisation);

        customer.setAbbreviation("test");
        customer.setType("A03");
        customer.setIsLicenseAccount("Y");
        customer.setAccountRole("Z001");



        BlockingReasons blockingReasons = new BlockingReasons();
        blockingReasons.setOrderBlockingReasonCode("01");
        blockingReasons.setDeliveryBlockingReasonCode("01");
        blockingReasons.setBillingBlockingReasonCode("02");
        blockingReasons.setSalesSupportBlockingIndicator("true");
        customer.setBlockingReasons(blockingReasons);

        customer.setIsInWhiteList("Y");
        customer.setRegistrationDate("2019-09-09");
        customer.setAdvantagesIntroduction("AdvantagesIntroduction");
        customer.setCorporateAssets("100");
        customer.setStaffNumber("1");
        customer.setDevelopersNumber("10");
        customer.setBusinessintroduction("test");

        //联系人
        getContanct(customer);
        //开户行
        getBank(customer);
        //地址信息
       // getAddress(customer);
        //关系
        getShip(customer);
        //开票信息
        getInvoice(customer);
        //客户团队
        getAccountTeam(customer);


        VisitCreateHeader header = new VisitCreateHeader();
        CustomerInfoContent customerContent = new CustomerInfoContent(header, customer);

        CustomerInfoBody customerInfoBody = new CustomerInfoBody(customerContent);
        CustomerInfoCreate create = new CustomerInfoCreate(customerInfoBody);
        try{
            String xml = JaxbXmlUtil.convertToXml(create);
            System.out.println(xml.replace("</y4r:CorporateAssets currencyCode=\"CNY\">","</y4r:CorporateAssets>"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void getAccountTeam(Customer customer){
        CustZrAccountTeam accountTeam = new CustZrAccountTeam();
        accountTeam.setEmployeeId("6999991");
        accountTeam.setRoleType("142");

        DirectResponsibility directResponsibility = new DirectResponsibility();
        directResponsibility.setEmployeeID(accountTeam.getEmployeeId());
        directResponsibility.setPartyRoleCode(accountTeam.getRoleType());

        customer.setDirectResponsibility(directResponsibility);
    }

    private void getInvoice(Customer customer){
        CustInvoiceInfo custInvoiceInfo = new CustInvoiceInfo();
        custInvoiceInfo.setPurchasingUnit("test");
        custInvoiceInfo.setShippingAddress("testaddress");
        custInvoiceInfo.setShippingMobile("+86 15200000000");
        custInvoiceInfo.setTaxpayerRegistrationNumber("12312");
        custInvoiceInfo.setCurrency("CNY");

        customer.setPurchasingUnit(custInvoiceInfo.getPurchasingUnit());
        customer.setShippingAddress(custInvoiceInfo.getShippingAddress());
        customer.setPhone(custInvoiceInfo.getShippingMobile());
        customer.setTaxpayerRegistrationNumber(custInvoiceInfo.getTaxpayerRegistrationNumber());
        customer.setCurrency(custInvoiceInfo.getCurrency());
    }

    private void getShip(Customer customer){
        CustCorporateRelationship ship = new CustCorporateRelationship();
        ship.setCorporateId(1000083);
        ship.setCorporateType("BUR001-1");

        Relationship relationship = new Relationship();
        relationship.setRelationshipBusinessPartnerInternalID(ship.getCorporateId().toString());
        relationship.setRoleCode(ship.getCorporateType());

        customer.setRelationship(relationship);
    }

   */
/* private void getAddress(Customer customer){
        CustomerAddress customerAddress = new CustomerAddress();
        customerAddress.setAddressType("A01");
        customerAddress.setContact("安道尔,卡尼略");
        customerAddress.setAddressDetail("1");
        customerAddress.setMobile("15200000000");
        customerAddress.setEamil("hjg@qq.com");

        AddressInformation addressInformation = new AddressInformation();
        addressInformation.setAddressType(customerAddress.getAddressType());

        Address address = new Address();
        PostalAddress postalAddress = new PostalAddress();
        postalAddress.setCountryCode("AD");
        postalAddress.setCityName("卡尼略");
        postalAddress.setStreetName(customerAddress.getAddressDetail());
        address.setPostalAddress(postalAddress);

        Telephone telephone = new Telephone();
        telephone.setFormattedNumberDescription(customerAddress.getMobile());
        address.setTelephone(telephone);

        Email email = new Email();
        email.setURI(customerAddress.getEamil());
        address.setEmail(email);

        customer.setAddressInformation(addressInformation);
    }*//*


    private void getBank(Customer customer){
        CustBankInfo bankInfo = new CustBankInfo();
        bankInfo.setBankName("中国建设银行");
        bankInfo.setBankAddress("shanghai");
        bankInfo.setBankAccount("10000001");
        bankInfo.setBankBic("123132");

        customer.setBankName(bankInfo.getBankName());
        customer.setBankAddress(bankInfo.getBankAddress());
        customer.setAccount(bankInfo.getBankAccount());
        customer.setBIC(bankInfo.getBankBic());
    }
    private void getContanct(Customer customer){
        List<CustomerContact> contacts = new ArrayList<>();
        CustomerContact c1 = new CustomerContact();
        c1.setType("C01");
        c1.setContactName("test1");
        c1.setMobile("15200000000");
        c1.setEmail("test1@qq.com");
        c1.setDepartment("003");
        c1.setSubDepartment("test1");
        c1.setPosition("0002");

        CustomerContact c2 = new CustomerContact();
        c2.setType("C02");
        c2.setContactName("test2");
        c2.setMobile("15220000000");
        c2.setEmail("test2@qq.com");
        c2.setDepartment("003");
        c2.setSubDepartment("test2");
        c2.setPosition("0002");

        contacts.add(c1);
        contacts.add(c2);

        List<ContactPerson> cs = new ArrayList<>();
        for(CustomerContact c : contacts){
            ContactPerson person = new ContactPerson();
            person.setBusinessPartnerFunctionTypeCode(c.getType());
            person.setFamilyName(c.getContactName());

            WorkplaceTelephone workplaceTelephone = new WorkplaceTelephone();
            workplaceTelephone.setFormattedNumberDescription("+86 "+c.getMobile());
            person.setWorkplaceTelephone(workplaceTelephone);

            WorkplaceEmail email1 = new WorkplaceEmail();
            email1.setURI(c.getEmail());
            person.setWorkplaceEmail(email1);

            person.setWorkplaceDepartmentName(c.getDepartment());
            person.setSecondaryDept(c.getSubDepartment());
            person.setBusinessPartnerFunctionTypeCode(c.getPosition());
            cs.add(person);
        }
        customer.setContactPerson(cs);
    }
}
*/
