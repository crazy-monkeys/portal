package com.crazy.portal.dao.product;

import com.crazy.portal.entity.product.ProductBatchDO;

public interface ProductBatchDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductBatchDO record);

    int insertSelective(ProductBatchDO record);

    ProductBatchDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductBatchDO record);

    int updateByPrimaryKey(ProductBatchDO record);

    int updateStatus();
}