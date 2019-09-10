package com.crazy.portal.service.product;

import com.crazy.portal.bean.product.BaseProResponseVO;
import com.crazy.portal.bean.product.ProductBean;
import com.crazy.portal.bean.product.ProductVO;
import com.crazy.portal.config.exception.BusinessException;
import com.crazy.portal.dao.product.ProductBatchDOMapper;
import com.crazy.portal.dao.product.ProductInfoDOMapper;
import com.crazy.portal.dao.product.ProductSubDOMapper;
import com.crazy.portal.entity.product.ProductBatchDO;
import com.crazy.portal.entity.product.ProductInfoDO;
import com.crazy.portal.entity.product.ProductSubDO;
import com.crazy.portal.util.*;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Resource
    private ProductSubDOMapper productSubDOMapper;
    @Resource
    private ProductBatchDOMapper productBatchDOMapper;

    public PageInfo<ProductInfoDO> selectProduct(ProductVO productVO){
        try{
            PortalUtil.defaultStartPage(productVO.getPageIndex(), productVO.getPageSize());
            ProductInfoDO record = new ProductInfoDO();
            BeanUtils.copyNotNullFields(productVO, record);
            List<ProductInfoDO> results = productInfoDOMapper.selectProductInfo(record);
            return new PageInfo<>(results);
        }catch (Exception e){
            log.error("产品查询异常！",e);
            throw new BusinessException(ErrorCodes.BusinessEnum.PRODUCT_QUERY_ERROR);
        }
    }

    /**
     * 同步MDM的产品信息
     */
    @Transactional
    public void syncProduct(){
        try {
            BaseProResponseVO responseVO = CallApiUtils.callProductApi();
            List<ProductBean> productVOS = responseVO.getContents();
            ProductBatchDO batchDO = saveBatch();
            for(ProductBean vo : productVOS){
                ProductInfoDO infoDO = new ProductInfoDO();
                mappingVO(infoDO,vo);
                infoDO.setBatchId(batchDO.getId());
                productInfoDOMapper.insertSelective(infoDO);
                saveProductSub(infoDO, vo);
            }
            productInfoDOMapper.updateProductMpq();
        }catch (Exception e){
            log.error("产品每日同步异常，MSG：",e);
            throw new BusinessException(ErrorCodes.BusinessEnum.PRODUCT_SYNC_ERROR);
        }
    }

    private void saveProductSub(ProductInfoDO infoDO, ProductBean vo){
        if(vo.getBOMCode().length>0){
            for(String bom : vo.getBOMCode()){
                ProductSubDO productSubDO = new ProductSubDO();
                productSubDO.setProductId(infoDO.getId());
                productSubDO.setSubMid(bom.substring(0,bom.indexOf("-")));
                productSubDO.setSubNumber(bom.substring(bom.indexOf("-")+1));
                productSubDO.setActive(1);
                productSubDO.setCreateTime(new Date());
                productSubDO.setUpdateTime(new Date());
                productSubDOMapper.insertSelective(productSubDO);
            }
        }
    }

    private ProductBatchDO saveBatch(){
        productBatchDOMapper.updateStatus();
        ProductBatchDO batchDO = new ProductBatchDO();
        batchDO.setBatchSeq(String.valueOf(new Date().getTime()));
        batchDO.setBatchStatus(Enums.ProductStatus.ACTIVE.getStatus());
        batchDO.setInsertTime(new Date());
        productBatchDOMapper.insertSelective(batchDO);
        return batchDO;
    }

    private void mappingVO(ProductInfoDO infoDO, ProductBean vo){
        try{
            infoDO.setBu(vo.getBU());
            infoDO.setBuHeader(vo.getBUHead());
            infoDO.setLifeCycle(vo.getLifeCycle());
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
