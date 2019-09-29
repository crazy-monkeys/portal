package com.crazy.portal.service.price;

import com.alibaba.fastjson.JSON;
import com.crazy.portal.bean.price.BICatalogPrice;
import com.crazy.portal.bean.price.CatalogPriceVO;
import com.crazy.portal.dao.price.CatalogPriceMapper;
import com.crazy.portal.entity.price.CatalogPrice;
import com.crazy.portal.util.CallApiUtils;
import com.crazy.portal.util.HttpClientUtils;
import com.crazy.portal.util.PortalUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class CatalogPriceService {

    @Resource
    private CatalogPriceMapper catalogPriceMapper;

    /**
     * 分页查询目录价
     * @param catalogPriceVO
     * @return
     */
    public PageInfo<CatalogPrice> selectWithPage(CatalogPriceVO catalogPriceVO){
        PortalUtil.defaultStartPage(catalogPriceVO.getPageIndex(), catalogPriceVO.getPageSize());
        Page<CatalogPrice> catalogPrices = catalogPriceMapper.selectByParamsWithPage(catalogPriceVO);
        return new PageInfo<>(catalogPrices);
    }

    /**
     * 详情
     * @param productModel
     * @param inCustomer
     * @return
     */
    public CatalogPrice selectByProductModelAndCustomerName(String productModel,String bu,String inCustomer){
        return catalogPriceMapper.selectByProductModelAndCustomerName(productModel,bu,inCustomer);
    }

    /**
     * 根据sapCode获取目录价格信息
     * @param sapCode
     * @return
     */
    public CatalogPrice selectBySapCode(String sapCode){
        return catalogPriceMapper.selectBySapCode(sapCode);
    }

    @Transactional
    public int delete(Integer id){
        return catalogPriceMapper.deleteByPrimaryKey(id);
    }


    @Transactional
    public int update(CatalogPrice catalogPrice){
        return catalogPriceMapper.updateByPrimaryKeySelective(catalogPrice);
    }

    /**
     * 新增目录价
     * @param catalogPrice
     * @return
     */
    @Transactional
    public int insertCatalogPrice(CatalogPrice catalogPrice){
        return catalogPriceMapper.insertSelective(catalogPrice);
    }

    /**
     * 获取BI 目录价格列表
     * @return
     */
    public List<BICatalogPrice> getBIActualPrices(){
        String url = String.format("%s%s", CallApiUtils.ECC_API_URL,"/http/BI/PORTAL/GET_PRODUCT_PRICE_BOM_PRICE");
        try {
            String responce = StringEscapeUtils.unescapeEcmaScript(HttpClientUtils.get(url));
            responce = responce.replaceAll("^\"*|\"*$", "");
            return JSON.parseArray(responce, BICatalogPrice.class);
        } catch (IOException e) {
            log.error("",e);
        }
        return Collections.EMPTY_LIST;
    }
}
