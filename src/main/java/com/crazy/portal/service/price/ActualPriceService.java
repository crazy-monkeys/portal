package com.crazy.portal.service.price;

import com.alibaba.fastjson.JSON;
import com.crazy.portal.bean.price.ActualPriceVO;
import com.crazy.portal.bean.price.BIActualPrice;
import com.crazy.portal.dao.price.ActualPriceMapper;
import com.crazy.portal.entity.price.ActualPrice;
import com.crazy.portal.util.CallApiUtils;
import com.crazy.portal.util.HttpClientUtils;
import com.crazy.portal.util.PortalUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * @Desc:
 * @Author: Bill
 * @Date: created in 16:15 2019-08-18
 * @Modified by:
 */
@Service
@Slf4j
public class ActualPriceService {

    @Resource
    private ActualPriceMapper actualPriceMapper;



    public PageInfo<ActualPrice> selectWithPage(ActualPriceVO actualPriceVO){
        PortalUtil.defaultStartPage(actualPriceVO.getPageIndex(), actualPriceVO.getPageSize());
        Page<ActualPrice> actualPrices = actualPriceMapper.selectByParamsWithPage(actualPriceVO);
        return new PageInfo<>(actualPrices);
    }

    /**
     * 获取BI 目录价格列表
     * @return
     */
    public List<BIActualPrice> getBIActualPrices(){
        String url = String.format("%s%s", CallApiUtils.ECC_API_URL,"/http/BI/PORTAL/GET_PRODUCT_PRICE_BOM_PRICE");
        try {
            String responce = StringEscapeUtils.unescapeEcmaScript(HttpClientUtils.get(url));
            responce = responce.replaceAll("^\"*|\"*$", "");
            return JSON.parseArray(responce, BIActualPrice.class);
        } catch (IOException e) {
            log.error("",e);
        }
        return Collections.EMPTY_LIST;
    }
}
