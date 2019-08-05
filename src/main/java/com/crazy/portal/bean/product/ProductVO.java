package com.crazy.portal.bean.product;

import com.crazy.portal.bean.common.PageBean;
import lombok.Data;

/**
 * @ClassName: ProductVO
 * @Author: God Man Qiu~
 * @Date: 2019/8/3 13:06
 */
@Data
public class ProductVO extends PageBean{
    private Integer pModel;
    private Integer pFunction;
    private Integer active;
}
