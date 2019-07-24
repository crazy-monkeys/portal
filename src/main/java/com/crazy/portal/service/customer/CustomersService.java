package com.crazy.portal.service.customer;

import com.crazy.portal.bean.customer.CustomerQueryBean;
import com.crazy.portal.bean.customer.CustomerVO;
import com.crazy.portal.dao.customer.*;
import com.crazy.portal.entity.customer.CustBasicInfo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class CustomersService {

    @Resource
    private CustBasicInfoMapper custBasicInfoMapper;
    @Resource
    private CustBankInfoMapper custBankInfoMapper;
    @Resource
    private CustContactInfoMapper custContactInfoMapper;
    @Resource
    private CustEquityInfoMapper custEquityInfoMapper;
    @Resource
    private CustIntercourseInfoMapper custIntercourseInfoMapper;
    @Resource
    private CustVisitingRecordMapper custVisitingRecordMapper;


    /**
     * 分页查询客户列表
     * @param bean
     * @return
     */
    public PageInfo<CustBasicInfo> queryCustByPage(CustomerQueryBean bean){
        PageHelper.startPage(bean.getPageIndex(), bean.getPageSize());
        List<CustBasicInfo> list = custBasicInfoMapper.selectCustByPage(bean);
        return new PageInfo<>(list);
    }

    public CustomerVO queryCustDetail(Long id){
        CustomerVO vo = new CustomerVO();
        vo.setBasicInfo(custBasicInfoMapper.selectByPrimaryKey(id));
        return vo;
    }

    public boolean update(CustomerVO vo){
        CustBasicInfo basicInfo = vo.getBasicInfo();
        if(basicInfo.getId() == null){
            custBasicInfoMapper.insertSelective(basicInfo);
        }else{
            custBasicInfoMapper.updateByPrimaryKeySelective(basicInfo);
        }
        return true;
    }
}
