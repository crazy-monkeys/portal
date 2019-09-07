package com.crazy.portal.controller.forecast;

import com.crazy.portal.bean.BaseResponse;
import com.crazy.portal.bean.forecast.ForecastParam;
import com.crazy.portal.controller.BaseController;
import com.crazy.portal.entity.forecast.ForecastLine;
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
        return super.successResult(saleForecastService.queryAgencyForecastData(pageNum, pageSize, 1, 0));
    }

    /**
     * 代理商批量删除数据
     * @param biIds
     */
    @GetMapping(value = "/forecast/agency/data/delete")
    public BaseResponse deleteAgencyForecastData(Integer[] biIds) {
        saleForecastService.deleteAgencyForecastData(biIds);
        return super.successResult();
    }

    /**
     * 代理商批量修改数据
     * @param list
     */
    @PostMapping(value = "/forecast/agency/data/update")
    public BaseResponse updateAgencyForecastData(@RequestBody List<ForecastParam> list) {
        saleForecastService.updateAgencyForecastData(list, 1);
        return super.successResult();
    }

    /**
     * 驳回记录查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping(value = "/forecast/agency/data/reject")
    public BaseResponse queryRejectForecastData(Integer pageNum, Integer pageSize) {
        return super.successResult(saleForecastService.queryAgencyForecastData(pageNum, pageSize, 1, 1));
    }

    /**
     * 驳回记录下载
     * @param response
     * @param forecastIds
     * @return
     */
    @GetMapping(value = "/forecast/agency/reject/download")
    public BaseResponse downloadRejectData(HttpServletResponse response, Integer[] forecastIds) {
        saleForecastService.downloadRejectData(response, forecastIds, 1);
        return super.successResult();
    }

    /**
     * 审批数据查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping(value = "/forecast/approval/data/query")
    public BaseResponse queryApprovalForecastData(Integer pageNum, Integer pageSize) {
        return super.successResult(saleForecastService.queryApprovalForecastData(pageNum, pageSize, 1));
    }

    /**
     * 审批数据批量通过
     * @param forecastIds
     * @param passMsg
     * @return
     */
    @GetMapping(value = "/forecast/approval/data/pass")
    public BaseResponse passApprovalForecastData(Integer[] forecastIds, String passMsg) {
        saleForecastService.passApprovalForecastData(forecastIds, passMsg);
        return super.successResult();
    }

    /**
     * 审批数据批量驳回
     * @param forecastIds
     * @param rejectMsg
     * @return
     */
    @GetMapping(value = "/forecast/approval/data/reject")
    public BaseResponse rejectApprovalForecastData(Integer[] forecastIds, String rejectMsg) {
        saleForecastService.rejectApprovalForecastData(forecastIds, rejectMsg);
        return super.successResult();
    }

    /**
     * 阿米巴下载数据
     * @param response
     * @param forecastIds
     * @return
     */
    @GetMapping(value = "/forecast/amb/reject/download")
    public BaseResponse downloadDataByAmb(HttpServletResponse response, Integer[] forecastIds) {
        saleForecastService.downloadRejectData(response, forecastIds, 1);
        return super.successResult();
    }

    /**
     * 首代下载数据
     * @param response
     * @param forecastIds
     * @return
     */
    @GetMapping(value = "/forecast/sd/reject/download")
    public BaseResponse downloadDataBySd(HttpServletResponse response, Integer[] forecastIds) {
        saleForecastService.downloadRejectData(response, forecastIds, 1);
        return super.successResult();
    }

    //审批数据批量上传（ID）

    /**
     * 审批数据单条修改
     * @param param
     * @return
     */
    @PostMapping(value = "/forecast/approval/single/update")
    public BaseResponse updateSingleForecastData(@RequestBody ForecastParam param) {
        saleForecastService.updateSingleForecastData(param);
        return super.successResult();
    }

    /**
     * 预测查询（按年）|| 预测查询（按月）
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping(value = "/forecast/data/query")
    public BaseResponse queryForecastData(Integer pageNum, Integer pageSize) {
        return super.successResult(saleForecastService.queryAgencyForecastData(pageNum, pageSize, 1, 0));
    }

    //预测查询（首代Buffer）
}
