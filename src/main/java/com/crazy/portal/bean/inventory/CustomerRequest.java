package com.crazy.portal.bean.inventory;

import com.crazy.portal.util.StringUtil;
import lombok.Data;

/**
 * @ClassName: CustomerRequest
 * @Author: God Man Qiu~
 * @Date: 2019/12/14 18:03
 */
@Data
public class CustomerRequest {
    private String sAgencyIncode;
    private String sCustomerIncode;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("?1");
        if(StringUtil.isNotEmpty(sAgencyIncode)){
            sb.append("&sAgencyIncode=").append(sAgencyIncode);
        }
        if(StringUtil.isNotEmpty(sCustomerIncode)){
            sb.append("&sCustomerIncode=").append(sCustomerIncode);
        }
        return sb.toString();
    }
}
