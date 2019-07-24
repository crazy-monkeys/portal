package com.crazy.portal.bean.customer;

import com.crazy.portal.entity.customer.TCustomerAddress;
import com.crazy.portal.entity.customer.TCustomerContacts;
import com.crazy.portal.entity.customer.TCustomerInfo;
import com.crazy.portal.entity.customer.TCustomerProject;
import lombok.Data;

import java.util.List;

@Data
public class SaveCustomerVO{

    TCustomerInfo customerInfo;

    List<TCustomerAddress> addresses;

    List<TCustomerContacts> customerContacts;

    List<TCustomerProject> customerProjects;
}
