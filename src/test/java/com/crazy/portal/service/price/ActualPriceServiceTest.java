package com.crazy.portal.service.price;

import com.crazy.portal.bean.price.BIActualPrice;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Desc:
 * @Author: Bill
 * @Date: created in 23:09 2019-09-16
 * @Modified by:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
//@Ignore
public class ActualPriceServiceTest {

    @Resource
    private ActualPriceService actualPriceService;

    @Test
    public void getBIActualPrices() {
        List<BIActualPrice> biActualPrices = this.actualPriceService.getBIActualPrices();
        Assert.assertNotNull(biActualPrices);
    }
}