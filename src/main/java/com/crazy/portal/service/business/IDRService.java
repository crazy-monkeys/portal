package com.crazy.portal.service.business;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.crazy.portal.bean.BaseResponse;
import com.crazy.portal.bean.business.*;
import com.crazy.portal.bean.common.Constant;
import com.crazy.portal.bean.customer.basic.FileVO;
import com.crazy.portal.bean.customer.visitRecord.VisitRecordEO;
import com.crazy.portal.config.exception.ErrorInfo;
import com.crazy.portal.dao.business.*;
import com.crazy.portal.entity.base.BaseEntity;
import com.crazy.portal.entity.business.BusinessFile;
import com.crazy.portal.entity.business.BusinessIdrInfo;
import com.crazy.portal.entity.business.BusinessInsuranceInfo;
import com.crazy.portal.entity.customer.VisitRecord;
import com.crazy.portal.util.*;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bill on 2019/7/30.
 *  * I: insurance 保价
 *  * D: diff 差价
 *  * R: returns 退货
 */
@Slf4j
@Service
public class IDRService {

    @Resource
    private BusinessIdrInfoMapper businessIdrInfoMapper;
    @Resource
    private BusinessDiffPriceInfoMapper businessDiffPriceInfoMapper;
    @Resource
    private BusinessInsuranceInfoMapper businessInsuranceInfoMapper;
    @Resource
    private BusinessReturnsInfoMapper businessReturnsInfoMapper;
    @Resource
    private BusinessFileMapper businessFileMapper;

    @Value("${file.path.root}")
    private String filePath;


}
