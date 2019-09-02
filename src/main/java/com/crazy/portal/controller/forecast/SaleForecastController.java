package com.crazy.portal.controller.forecast;

import com.crazy.portal.bean.BaseResponse;
import com.crazy.portal.bean.forecast.ForecastParam;
import com.crazy.portal.controller.BaseController;
import com.crazy.portal.service.forecast.SaleForecastService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by lee on 2019/8/18.
 */

@RestController
public class SaleForecastController extends BaseController {

    @Resource
    private SaleForecastService saleForecastService;

    /**
     * 代理商预测模版下载
     * @param response
     */
    @GetMapping(value = "/forecast/agency/template/download")
    public BaseResponse downloadAgencyTemplate(HttpServletResponse response, String yearMonth) {
        saleForecastService.downloadAgencyTemplate(response, yearMonth, 1);
        return super.successResult();
    }

    /**
     * 代理商预测数据上传
     * @param excel
     */
    @PostMapping(value = "/forecast/agency/template/upload")
    public BaseResponse uploadAgencyTemplate(MultipartFile excel) {
        return super.successResult(saleForecastService.uploadAgencyTemplate(excel, 1));
    }

    /**
     * 代理商错误数据下载
     * @param response
     * @param batchNo
     */
    @GetMapping(value = "/forecast/agency/error/download")
    public void downloadAgencyError(HttpServletResponse response, String batchNo) {
        saleForecastService.downloadAgencyError(response, batchNo, 1);
    }

    /**
     * 错误数据修正 上传
     * @param excel
     */
    @PostMapping(value = "/forecast/agency/error/upload")
    public BaseResponse modifyErrorData(MultipartFile excel, String batchNo) {
        return super.successResult(saleForecastService.modifyErrorData(excel, batchNo, 1));
    }

    /**
     * 代理商预测数据提交
     * @param batchNo
     */
    @GetMapping(value = "/forecast/agency/data/submit")
    public BaseResponse commitAgencyForecastData(String batchNo) {
        saleForecastService.commitAgencyForecastData(batchNo, 1);
        return super.successResult();
    }

    /**
     * 代理商预测数据查询
     * @param pageNum
     * @param pageSize
     */
    @GetMapping(value = "/forecast/agency/data/query")
    public BaseResponse queryAgencyForecastData(Integer pageNum, Integer pageSize) {
        return super.successResult(saleForecastService.queryAgencyForecastData(pageNum, pageSize, 1));
    }

    /**
     * 代理商批量删除数据
     * @param AgencyForecastData
     */
    @GetMapping
    public void deleteAgencyForecastData(Integer[] AgencyForecastData) {
        saleForecastService.deleteAgencyForecastData(AgencyForecastData);
    }

    /**
     * 代理商批量修改数据
     * @param list
     */
    @PostMapping
    public void updateAgencyForecastData(@RequestBody List<ForecastParam> list) {
        saleForecastService.updateAgencyForecastData(list, getCurrentUser().getId());
    }

    //驳回记录查询

    //驳回记录下载



    //审批数据查询

    //审批数据批量通过

    //审批数据批量驳回

    //审批数据批量下载

    //审批数据批量上传（ID）

    //审批数据单条修改

    //预测查询（按年）

    //预测查询（按月）

    //预测查询（首代Buffer）
}
