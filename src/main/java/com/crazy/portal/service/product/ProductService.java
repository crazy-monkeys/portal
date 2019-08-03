package com.crazy.portal.service.product;

import com.alibaba.fastjson.JSON;
import com.crazy.portal.bean.product.BaseProResponseVO;
import com.crazy.portal.bean.product.ProductVO;
import com.crazy.portal.dao.product.ProductInfoDOMapper;
import com.crazy.portal.entity.product.ProductInfoDO;
import com.crazy.portal.util.HttpClientUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by weiying on 2019/7/29.
 */
@Slf4j
@Service
public class ProductService {
    @Resource
    private ProductInfoDOMapper productInfoDOMapper;

    private static final String PRODUCT_URL = "https://e600180-hcioem.hcisbt.cn1.hana.ondemand.com/http/MDMProductMaster";

    /**
     * 同步MDM的产品信息
     */
    public void syscProduct(){
        try {
            String jsonStr = HttpClientUtils.get(PRODUCT_URL);
            BaseProResponseVO responseVO = JSON.parseObject(jsonStr, BaseProResponseVO.class);
            List<ProductVO> productVOS = responseVO.getContents();
            for(ProductVO vo : productVOS){
                ProductInfoDO infoDO = new ProductInfoDO();
                mappingVO(infoDO,vo);
                productInfoDOMapper.insertSelective(infoDO);
            }
        }catch (Exception e){
            log.error("产品每日同步异常，MSG：",e);
        }
    }

    private void mappingVO(ProductInfoDO infoDO, ProductVO vo){
        try{
            infoDO.setBu(vo.getBU());
            infoDO.setBuHeader(vo.getBUHead());
            infoDO.setCategory(vo.getCategory());
            infoDO.setSubCategory(vo.getSubCategory());
            infoDO.setManager(vo.getManager());
            infoDO.setPdt(vo.getPDT());
            infoDO.setPlatform(vo.getPlatform());
            infoDO.setProduct(vo.getProduct());
            infoDO.setSapMid(vo.getSAPMID());
            infoDO.setSn(vo.getSN());
            infoDO.setSubCategory(vo.getSubCategory());

            infoDO.setActive(1);
            infoDO.setInsertTime(new Date());
            infoDO.setUpdateTime(new Date());
        }catch (Exception e){
            log.error("复制对象异常，MSG：",e);
        }
    }
}
