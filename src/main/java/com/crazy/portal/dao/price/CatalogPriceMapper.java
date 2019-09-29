package com.crazy.portal.dao.price;

import com.crazy.portal.bean.price.CatalogPriceVO;
import com.crazy.portal.entity.price.CatalogPrice;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

public interface CatalogPriceMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(CatalogPrice record);

    CatalogPrice selectBySapCode(String sapCode);

    CatalogPrice selectByProductModelAndCustomerName(@Param("bu") String bu,@Param("productModel") String productModel,@Param("inCustomer") String inCustomer);

    Page<CatalogPrice> selectByParamsWithPage(CatalogPriceVO record);

    int updateByPrimaryKeySelective(CatalogPrice record);

}