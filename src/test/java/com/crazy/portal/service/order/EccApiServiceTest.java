package com.crazy.portal.service.order;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @Desc:
 * @Author: Bill
 * @Date: created in 14:56 2019-09-08
 * @Modified by:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class EccApiServiceTest {

    @Resource
    private OrderService eccApiService;

    @Test
    public void getCustomerRate() {
        eccApiService.getCustomerRate("100217");
    }
}