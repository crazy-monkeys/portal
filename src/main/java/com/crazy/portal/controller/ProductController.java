package com.crazy.portal.controller;

import com.crazy.portal.bean.BaseResponse;
import com.crazy.portal.service.product.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by weiying on 2019/7/29.
 */
@Slf4j
@RestController
@RequestMapping("/product")
public class ProductController extends BaseController{
    @Resource
    private ProductService productService;

    @GetMapping("/getList")
    public BaseResponse getList(){
        productService.syscProduct();
        return successResult();
    }

}
