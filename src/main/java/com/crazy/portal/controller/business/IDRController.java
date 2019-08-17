package com.crazy.portal.controller.business;

import com.crazy.portal.bean.BaseResponse;
import com.crazy.portal.bean.business.idr.BusinessIdrQueryBean;
import com.crazy.portal.controller.BaseController;
import com.crazy.portal.entity.business.idr.BusinessIdrInfo;
import com.crazy.portal.service.business.IDRService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.math.BigDecimal;

/**
 * Created by bill on 2019/7/30.
 * I: insurance 保价
 * D: diff 差价
 * R: returns 退货
 */
@Slf4j
@RestController
@RequestMapping("/business/idr")
public class IDRController extends BaseController {

    @Resource
    private IDRService idrService;


    /**
     * 列表
     * @return
     */
    @GetMapping("/list")
    public BaseResponse list(BusinessIdrQueryBean bean){
        return super.successResult(idrService.selectByPage(bean));
    }

    /**
     * 明细
     * @param id
     * @return
     */
    @GetMapping("/find/{id}")
    public BaseResponse find(@PathVariable Integer id){
        return super.successResult(idrService.selectIdrDetail(id));
    }

    /**
     * 模板下载
     * @param type 1.保价 2.差价补偿 3.退换货
     * @param response
     */
    @GetMapping("/templateDownload")
    public void templateDownload(Integer type, HttpServletResponse response) throws Exception{
        idrService.templateDownload(type, response);
    }
    /**
     * 上传附件
     * @param id 保差退ID
     * @param type 1.保价 2.差价补偿 3.退换货
     * @param fileType 1：普通附件 2：保差退附件 3：财务完结附件
     * @param crAmount CR金额
     * @param file 文件
     * @return
     */
    @PostMapping("/upload")
    public BaseResponse upload(MultipartFile file,
                               @RequestParam("id") Integer id,
                               @RequestParam("idrType") Integer type,
                               @RequestParam("fileType") Integer fileType,
                               @RequestParam("crAmount") BigDecimal crAmount) throws Exception {
        return super.successResult(idrService.upload(id, type, fileType, crAmount, file, this.getCurrentUser().getId()));
    }

    /**
     * 提交
     * @param bean 信息
     * @return
     */
    @PostMapping("/submit")
    public BaseResponse submit(@RequestBody @Valid BusinessIdrInfo bean){
        idrService.save(bean, this.getCurrentUser().getId());
        return super.successResult();
    }


}
