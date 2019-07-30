package com.crazy.portal.bean.product;

import lombok.Data;

import java.util.List;

/**
 * Created by weiying on 2019/7/29.
 */
@Data
public class BaseProResponseVO {
    private String msg;
    private Boolean result;
    private List<ProductVO> contents;
}
