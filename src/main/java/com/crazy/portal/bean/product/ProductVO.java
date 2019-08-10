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
    private String product;
    private String bu;
    private String pdt;
    private String category;
    private String subCategory;
    private String platform;
    private String sapMid;
    private String subMid;
}
