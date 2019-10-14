package com.crazy.portal.service.business;

import com.alibaba.fastjson.JSON;
import com.crazy.portal.entity.business.rebate.BusinessPriceRoleAO;
import com.crazy.portal.entity.business.rebate.BusinessSalesDetailAO;
import com.crazy.portal.util.CallApiUtils;
import com.crazy.portal.util.HttpClientUtils;
import com.crazy.portal.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
public class RebateApiService {

    private static final String REBATE_PRICE_ROLE_URL = "/http/BI/PORTAL/GET_CALCULATED_ALL_REBATE_DATA_PRICE_ROLE";

    private static final String REBATE_SALES_DETAILS_URL = "/http/BI/PORTAL/GET_CALCULATED_ALL_REBATE_DATA_SALES_DETAILS";

    /**
     * 同步rebate金额信息
     * @param startDate 起始年月 yyyyMM
     * @param endDate 结束年月 yyyyMM
     * @return
     * @throws IOException
     */
    public List<BusinessPriceRoleAO> syncRebatePriceRoleData(String startDate, String endDate) throws IOException{
        String url = CallApiUtils.ECC_API_URL.concat(REBATE_PRICE_ROLE_URL).concat("?sStartYearMonth=").concat(startDate).concat("&sEndYearMonth=").concat(endDate);
        String result =  StringUtil.getJsonMessage(HttpClientUtils.get(url));
        List<BusinessPriceRoleAO> priceRoleResult = JSON.parseArray(result, BusinessPriceRoleAO.class);
        return priceRoleResult;
    }

    /**
     * 同步rebate商品明细
     * @param startDate 起始年月 yyyyMM
     * @param endDate 结束年月 yyyyMM
     * @return
     * @throws IOException
     */
    public List<BusinessSalesDetailAO> syncRebatePriceSalesDetails(String startDate, String endDate) throws IOException{
        String url = CallApiUtils.ECC_API_URL.concat(REBATE_SALES_DETAILS_URL).concat("?sStartYearMonth=").concat(startDate).concat("&sEndYearMonth=").concat(endDate);
        String result =  StringUtil.getJsonMessage(HttpClientUtils.get(url));
        List<BusinessSalesDetailAO> salesDetailResult = JSON.parseArray(result, BusinessSalesDetailAO.class);
        return salesDetailResult;
    }
}
