package com.crazy.portal.controller.rate;

import com.crazy.portal.bean.BaseResponse;
import com.crazy.portal.bean.rate.AgencyRateQueryBean;
import com.crazy.portal.controller.BaseController;
import com.crazy.portal.service.rate.AgencyRateService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

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
        return successResult(agencyRateService.uploadAgencyRateFile(files, this.getCurrentUser().getId()));
    }

    @GetMapping("/approve/{ids}")
    public BaseResponse approve(@PathVariable String ids){
        agencyRateService.approveRate(ids);
        return successResult();
    }

    @GetMapping("/download")
    public void download(HttpServletResponse response) throws Exception{
        agencyRateService.templateDownload(response);
    }
}
