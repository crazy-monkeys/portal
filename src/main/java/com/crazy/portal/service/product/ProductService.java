package com.crazy.portal.service.product;

import com.crazy.portal.bean.product.BaseProResponseVO;
import com.crazy.portal.bean.product.ProductBean;
import com.crazy.portal.bean.product.ProductVO;
import com.crazy.portal.dao.product.ProductInfoDOMapper;
import com.crazy.portal.entity.product.ProductInfoDO;
import com.crazy.portal.util.CallApiUtils;
import com.crazy.portal.util.PortalUtil;
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

    public void selectProduct(ProductVO vo){
        PortalUtil.defaultStartPage(vo.getPageIndex(), vo.getPageSize());

        return;
    }

    /**
     * 同步MDM的产品信息
     */
    public void syscProduct(){
        try {
            BaseProResponseVO responseVO = CallApiUtils.callProductApi();
            List<ProductBean> productVOS = responseVO.getContents();
            for(ProductBean vo : productVOS){
                ProductInfoDO infoDO = new ProductInfoDO();
                mappingVO(infoDO,vo);
                productInfoDOMapper.insertSelective(infoDO);
            }
        }catch (Exception e){
            log.error("产品每日同步异常，MSG：",e);
        }
    }

    private void mappingVO(ProductInfoDO infoDO, ProductBean vo){
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
