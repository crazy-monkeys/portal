package com.crazy.portal.controller.product;

import com.crazy.portal.bean.BaseResponse;
import com.crazy.portal.controller.BaseController;
import com.crazy.portal.service.product.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by weiying on 2019/7/29.
 */
@Slf4j
@RestController
@RequestMapping("/product")
public class ProductController extends BaseController {
    @Resource
    private ProductService productService;

    @GetMapping("/getList")
    public BaseResponse getList(@RequestParam(required = false) Integer pModel,
                                @RequestParam(required = false) Integer pFunction,
                                @RequestParam(required = false) Integer active,
                                @RequestParam(required = false,defaultValue = "1") Integer pageNum,
                                @RequestParam(required = false,defaultValue = "10") Integer pageSize){
        productService.syscProduct();
        return successResult();
    }

}
