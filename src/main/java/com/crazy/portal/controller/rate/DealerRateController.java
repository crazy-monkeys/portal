package com.crazy.portal.controller.rate;

import com.crazy.portal.bean.BaseResponse;
import com.crazy.portal.controller.BaseController;
import com.crazy.portal.entity.rate.DealerRateDO;
import com.crazy.portal.service.rate.DealerRateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @ClassName: DealerRateController
 * @Author: God Man Qiu~
 * @Date: 2019/8/4 11:47
 */
@Slf4j
@RestController
@RequestMapping("/rate")
public class DealerRateController extends BaseController{
    @Resource
    private DealerRateService dealerRateService;

    @PostMapping("/upload/{batchSeq}")
    public BaseResponse uploadRate(MultipartFile file, @PathVariable String batchSeq){
        dealerRateService.uploadRate(batchSeq, file, super.getCurrentUser().getId());
        return successResult();
    }

    @PostMapping("/list")
    public BaseResponse selectList(@RequestBody DealerRateDO dealerRateDO){
        return super.successResult(dealerRateService.selectRate(dealerRateDO));
    }
}
