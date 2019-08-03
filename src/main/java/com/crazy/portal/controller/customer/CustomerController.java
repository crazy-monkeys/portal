package com.crazy.portal.controller.customer;

import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.crazy.portal.bean.BaseResponse;
import com.crazy.portal.bean.customer.CustomerQueryBean;
import com.crazy.portal.bean.customer.visitRecord.VisitRecordQueryBean;
import com.crazy.portal.controller.BaseController;
import com.crazy.portal.entity.customer.CustomerInfo;
import com.crazy.portal.service.customer.CustomersService;
import com.crazy.portal.util.ExcelUtils;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @Description 客户管理
 * @Author Shawn
 * @Date 2019-07-11 22:00
 * @Modify by
 */
@Slf4j
@RestController
@RequestMapping("/customer")
public class CustomerController extends BaseController{

    @Resource
    private CustomersService customersService;

    @GetMapping("/list")
    public BaseResponse customers(CustomerQueryBean bean){
        bean.setUserId(this.getCurrentUser().getId());
        PageInfo page = customersService.queryCustByPage(bean);
        return successResult(page);
    }

    @GetMapping(value="/info/{id}")
    public BaseResponse getCustomer(@PathVariable Integer id){
        return successResult(customersService.queryCustDetail(id));
    }

    @PostMapping("/info")
    public BaseResponse customerAdd(@RequestBody CustomerInfo vo){
        customersService.addOrUpdate(vo, this.getCurrentUser().getId());
        return successResult();
    }

    @DeleteMapping("/info/{id}")
    public BaseResponse customerDelete(@PathVariable Integer id){
        customersService.delete(id, this.getCurrentUser().getId());
        return successResult();
    }

    @GetMapping("/againReport/{id}")
    public BaseResponse againReport(@PathVariable Integer id){
        customersService.againReport(id, this.getCurrentUser().getId());
        return successResult();
    }

    @GetMapping("/approval/{id}")
    public BaseResponse approval(@PathVariable Integer id){
        customersService.approval(id, this.getCurrentUser().getId());
        return successResult();
    }

    @GetMapping("/reject/{id}")
    public BaseResponse reject(@PathVariable Integer id){
        customersService.reject(id, this.getCurrentUser().getId());
        return successResult();
    }

    @GetMapping("/checkName")
    public BaseResponse checkName(String custName){
        customersService.checkCustName(custName);
        return successResult();
    }

    @GetMapping("/visitRecord/list")
    public BaseResponse visitRecordList(VisitRecordQueryBean bean){
        return successResult(customersService.selectVisitRecordPage(bean));
    }

    @GetMapping("/visitRecord/download")
    public BaseResponse visitRecordDownload(HttpServletResponse response){
        try {
            Map<String, List<? extends BaseRowModel>> resultMap = customersService.downloadTemplate(this.getCurrentUser().getId());
            ExcelUtils.createExcelStreamMutilByEaysExcel(response, resultMap, ExcelTypeEnum.XLSX);
            return successResult();
        }catch (Exception ex){
            log.error("下载模板异常", ex);
            return errorResult();
        }
    }

    @PostMapping("visitRecord/upload")
    public BaseResponse visitRecordUpload(MultipartFile[] files) throws Exception{
        customersService.uploadVisitRecord(files, this.getCurrentUser().getId());
        return successResult();
    }
}
