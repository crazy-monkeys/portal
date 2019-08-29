package com.crazy.portal.service.customer;

import com.crazy.portal.dao.cusotmer.CustBankInfoMapper;
import com.crazy.portal.entity.cusotmer.CustBankInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName: CustBankInfoService
 * @Author: God Man Qiu~
 * @Date: 2019/8/29 23:20
 */
@Slf4j
@Service
public class CustBankInfoService {
    @Resource
    private CustBankInfoMapper custBankInfoMapper;


}
