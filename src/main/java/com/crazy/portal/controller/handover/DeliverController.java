package com.crazy.portal.controller.handover;

import com.crazy.portal.bean.BaseResponse;
import com.crazy.portal.bean.handover.DeliverTemplateBean;
import com.crazy.portal.controller.BaseController;
import com.crazy.portal.service.handover.DeliverService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by lee on 2019/8/3.
 */

@RestController
public class DeliverController extends BaseController {

    @Resource
    private DeliverService deliverService;

    /**
     * 出货模版下载
     * @param response
     */
    @GetMapping(value = "/deliver/template")
    public void downloadTemplate(HttpServletResponse response) {
        deliverService.downloadTemplate(response);
    }

    /**
     * 出货数据上传 并 校验
     * @param excel
     * @return
     */
    @PostMapping(value = "/deliver/template")
    public BaseResponse uploadTemplateData(MultipartFile excel) {
        List<DeliverTemplateBean> deliverData = deliverService.uploadTemplateData(excel, getCurrentUser().getId());
        return super.successResult(deliverService.verificationData(deliverData, getCurrentUser().getId()));
    }

    /**
     * 出货数据校验失败信息下载
     * @param response
     */
    @GetMapping(value = "/deliver/error")
    public void downloadError(HttpServletResponse response, String fileName) {
        deliverService.downloadError(response, fileName);
    }

    /**
     * 出货数据校验
     * @param ids
     * @return
     */
/*    @GetMapping(value = "/deliver/verification")
    public BaseResponse verificationData(Integer[] ids) {
//        deliverService.verificationData(ids);
        return super.successResult();
    }*/

    /**
     * 出货数据提交
     * @return
     */
    @PostMapping(value = "/deliver")
    public BaseResponse submitData(Integer id) {
        deliverService.submitData(id);
        return super.successResult();
    }

}
