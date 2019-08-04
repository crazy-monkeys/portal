package com.crazy.portal.controller.business;

import com.crazy.portal.bean.BaseResponse;
import com.crazy.portal.controller.BaseController;
import com.crazy.portal.service.business.IDRService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * Created by bill on 2019/7/30.
 * I: insurance 保价
 * D: diff 差价
 * R: returns 退货
 */
@RestController
@RequestMapping("/business/")
public class IDRController extends BaseController {

    private IDRService ipreService;

    /**
     * 上传保价附件
     * @param file
     * @return
     */
    @PostMapping("/upIsurance")
    public BaseResponse upIsurance(@RequestParam("file") MultipartFile file) throws IOException {

        return super.successResult();
    }

    /**
     * 上传差价附件
     * @param file
     * @return
     */
    @PostMapping("/upDiffPrice")
    public BaseResponse upDiffPrice(@RequestParam("file") MultipartFile file){
        return super.successResult();
    }

    /**
     * 上传退换货附件
     * @param file
     * @return
     */
    @PostMapping("/upReturns")
    public BaseResponse upReturns(@RequestParam("file") MultipartFile file){
        return super.successResult();
    }

    /**
     * 提交
     * @return
     */
    @PostMapping("/submit")
    public BaseResponse submit(){
        return super.successResult();
    }

    /**
     * 明细
     * @param id
     * @return
     */
    @GetMapping("/find/{id}")
    public BaseResponse find(@PathVariable Integer id){
        return super.successResult();
    }

    /**
     * 代理商在申请列表的上传
     * @param crAmount
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public BaseResponse upload(
            @RequestParam("crAmount") BigDecimal crAmount,
            @RequestParam("file") MultipartFile file){

        return super.successResult();
    }

    /**
     * 申请列表
     * @return
     */
    @GetMapping("/list")
    public BaseResponse list(){

        return super.successResult();
    }
}
