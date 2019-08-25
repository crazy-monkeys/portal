package com.crazy.portal.bean.customer;

import com.crazy.portal.entity.cusotmer.CustCorporateRelationship;
import lombok.Data;

import java.util.List;

/**
 * @ClassName: CustomerShipBean
 * @Author: God Man Qiu~
 * @Date: 2019/8/25 14:08
 */
@Data
public class CustomerShipBean {
    private CustCorporateRelationship inShip;
    private List<CustCorporateRelationship> outShips;
}
