package com.crazy.portal.controller.rate;

import com.crazy.portal.bean.BaseResponse;
import com.crazy.portal.bean.rate.AgencyRateQueryBean;
import com.crazy.portal.controller.BaseController;
import com.crazy.portal.service.rate.AgencyRateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @Describe 代理费率
 * @Author Shawn
 * @Date 2019-08-10
 */
@RestController
@RequestMapping(value="/agencyRate")
public class AgencyRateController extends BaseController {

    @Resource
    private AgencyRateService agencyRateService;

    @GetMapping("/list")
    public BaseResponse list(AgencyRateQueryBean bean){
        return successResult(agencyRateService.selectByPage(bean));
    }

    @PostMapping("/upload")
    public BaseResponse upload(MultipartFile[] files) throws Exception{
        agencyRateService.uploadAgencyRateFile(files, this.getCurrentUser().getId());
        return successResult();
    }
}
