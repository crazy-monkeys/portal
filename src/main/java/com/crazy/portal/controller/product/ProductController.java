package com.crazy.portal.controller.product;

import com.crazy.portal.bean.BaseResponse;
import com.crazy.portal.bean.product.ProductVO;
import com.crazy.portal.controller.BaseController;
import com.crazy.portal.service.product.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/getList")
    public BaseResponse getList(@RequestBody ProductVO productVO){
        return successResult(productService.selectProduct(productVO));
    }

}
