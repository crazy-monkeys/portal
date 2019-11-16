package com.crazy.portal.service.inventory;

import com.crazy.portal.entity.inventory.InventoryTransferDO;
import com.crazy.portal.entity.system.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Desc:
 * @Author: Bill
 * @Date: created in 17:01 2019-11-16
 * @Modified by:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class InventoryServiceTest {

    @Resource
    private InventoryService inventoryService;


    @Test
    public void transfer() {
        List<InventoryTransferDO> transferDOS = new ArrayList<>();

        InventoryTransferDO inventoryTransferDO = new InventoryTransferDO();
        inventoryTransferDO.setYearMonth("setYearMonth");
        inventoryTransferDO.setAgencyShortName("setAgencyShortName");
        inventoryTransferDO.setProductLine("setProductLine");
        inventoryTransferDO.setSubProductLine("setSubProductLine");
        inventoryTransferDO.setClass2("setClass2");
        inventoryTransferDO.setClass3("setClass3");
        inventoryTransferDO.setProduct("setProduct");
        inventoryTransferDO.setCustomerType("setCustomerType");
        inventoryTransferDO.setInventoryType("setInventoryType");
        inventoryTransferDO.setInventoryPrice(new BigDecimal(1));
        inventoryTransferDO.setCompany("setCompany");
        inventoryTransferDO.setSalesOrg("setSalesOrg");
        inventoryTransferDO.setMonthlyInitQty(2);
        inventoryTransferDO.setAgencyPickUpDate("setAgencyPickUpDate");
        inventoryTransferDO.setAgencyPickUpQty(3);
        inventoryTransferDO.setSalesQty(4);
        inventoryTransferDO.setMonthlyEndQty(5);
        inventoryTransferDO.setMonthlyEndInventoryPeriod("setMonthlyEndInventoryPeriod");
        inventoryTransferDO.setInventoryTotalAmount(new BigDecimal(6));
        inventoryTransferDO.setTransferOutCustomer("setTransferOutCustomer");
        inventoryTransferDO.setTransferIntoCustomer("setTransferIntoCustomer");
        inventoryTransferDO.setTransferYearMonth("setTransferYearMonth");
        transferDOS.add(inventoryTransferDO);
        inventoryService.transfer(new User(),transferDOS);
    }
}