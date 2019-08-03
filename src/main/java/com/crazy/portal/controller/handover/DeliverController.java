package com.crazy.portal.controller.handover;

import com.crazy.portal.bean.BaseResponse;
import com.crazy.portal.bean.handover.DeliverTemplateBean;
import com.crazy.portal.service.handover.DeliverService;
import com.crazy.portal.util.ExcelUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by lee on 2019/8/3.
 */

@RestController
public class DeliverController {

    @Resource
    private DeliverService deliverService;

    @GetMapping(value = "/deliver/template")
    public void downloadTemplate(HttpServletResponse response) {
        deliverService.downloadTemplate(response);
    }

    @PostMapping(value = "/deliver/template")
    public BaseResponse uploadTemplate(MultipartFile excel) {
        List<DeliverTemplateBean> data =  ExcelUtils.readExcel(excel, DeliverTemplateBean.class , 1);
        System.out.println(data);
        return null;
    }

    @GetMapping(value = "/deliver/error")
    public BaseResponse downloadError() {

        return null;
    }

    @PostMapping(value = "/deliver/verification")
    public BaseResponse verificationData() {

        return null;
    }

    @PostMapping(value = "/deliver/Verification")
    public BaseResponse submitData() {

        return null;
    }

}
