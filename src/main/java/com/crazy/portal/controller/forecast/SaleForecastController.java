package com.crazy.portal.controller.forecast;

import com.crazy.portal.annotation.OperationLog;
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
    @OperationLog
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
    public BaseResponse queryAgencyForecastData(Integer pageNum, Integer pageSize,
                                                String customerAbbreviation, Integer status, String salePeople,
                                                String uploadStartTime, String uploadEndTime) {
        return super.successResult(saleForecastService.queryAgencyForecastData(pageNum, pageSize, 1,
                customerAbbreviation, status, salePeople, uploadStartTime, uploadEndTime));
    }

    /**
     * 代理商批量删除数据
     * @param forecastIds
     */
    @OperationLog
    @GetMapping(value = "/forecast/agency/data/delete")
    public BaseResponse deleteAgencyForecastData(Integer[] forecastIds) {
        saleForecastService.deleteAgencyForecastData(forecastIds);
        return super.successResult();
    }

    /**
     * 代理商批量修改数据
     * @param list
     */
    @OperationLog
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
        return super.successResult(saleForecastService.queryAgencyRejectForecastData(pageNum, pageSize, 1));
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
    public BaseResponse queryApprovalForecastData(Integer pageNum, Integer pageSize,
                                                  String customerAbbreviation, Integer isUpdate, String salePeople,
                                                  String uploadStartTime, String uploadEndTime,
                                                  String ambPeople, String sdPeople, String agencyAbbreviation,
                                                  String channel) {
        return super.successResult(saleForecastService.queryApprovalForecastData(pageNum, pageSize, 1,
                customerAbbreviation, null, salePeople, uploadStartTime, uploadEndTime, ambPeople, sdPeople,
                agencyAbbreviation, channel));
    }

    /**
     * 审批数据批量通过
     * @param forecastIds
     * @param passMsg
     * @return
     */
    @OperationLog
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
    @OperationLog
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
    @GetMapping(value = "/forecast/amb/data/download")
    public BaseResponse downloadDataByAmb(HttpServletResponse response, Integer[] forecastIds) {
        saleForecastService.downloadDataByAmb(response, forecastIds);
        return super.successResult();
    }

    /**
     * 阿米巴数据
     * @param excel
     * @return
     */
    @PostMapping(value = "/forecast/amb/data/upload")
    public BaseResponse uploadDataByAmb(MultipartFile excel) {
        saleForecastService.uploadDataByAmb(excel, 1);
        return super.successResult();
    }

    /**
     * 首代下载数据
     * @param response
     * @param forecastIds
     * @return
     */
    @GetMapping(value = "/forecast/sd/data/download")
    public BaseResponse downloadDataBySd(HttpServletResponse response, Integer[] forecastIds) {
        saleForecastService.downloadDataBySd(response, forecastIds);
        return super.successResult();
    }

    @PostMapping(value = "/forecast/sd/data/upload")
    public BaseResponse uploadDataBySd(MultipartFile excel) {
        saleForecastService.uploadDataBySd(excel, 1);
        return super.successResult();
    }

    /**
     * 审批数据单条修改
     * @param param
     * @return
     */
    @OperationLog
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
    @GetMapping(value = "/forecast/data/leader/query")
    public BaseResponse queryForecastData(Integer pageNum, Integer pageSize,
                                          String customerAbbreviation, String agencyAbbreviation, String salePeople,
                                          String uploadStartTime, String uploadEndTime, String channel) {
        return super.successResult(saleForecastService.queryApprovalForecastData(pageNum, pageSize, 1,
                customerAbbreviation, 2, salePeople, uploadStartTime, uploadEndTime, null, null,
                agencyAbbreviation, channel));
    }

    //预测查询（首代Buffer）
}
