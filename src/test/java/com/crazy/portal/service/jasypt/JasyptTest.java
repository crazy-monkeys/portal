package com.crazy.portal.service.jasypt;

import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

/**
 * @Desc:
 * @Author: Bill
 * @Date: created in 00:26 2019-10-18
 * @Modified by:
 */
@SpringBootTest
@Slf4j
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class JasyptTest {

    @Resource
    private StringEncryptor encryptor;

    @Test
    public void encrypt(){
        String a = encryptor.encrypt("123");
        log.info(a);
        Assert.assertTrue(a.length() > 0);
    }
}
