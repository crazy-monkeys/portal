package com.crazy.portal.controller.business;

import com.crazy.portal.bean.BaseResponse;
import com.crazy.portal.service.business.IpreService;
import org.springframework.web.bind.annotation.*;

/**
 * Created by lee on 2019/7/30.
 * I: 保价
 * P: 差价
 * R: 退货
 * E: 换货
 */
@RestController
public class IpreController {

    private IpreService ipreService;

    /**
     * ipre分页列表
     * @return
     */
    @GetMapping("/ipre")
    public BaseResponse getPagelist() {

        return null;
    }

    /**
     * ipre信息保存
     * @return
     */
    @PostMapping("/ipre")
    public BaseResponse create() {

        return null;
    }

    /**
     * ipre明细查询
     * @param id
     * @param type
     * @return
     */
    @GetMapping("/ipre/{id}/{type}")
    public BaseResponse getDetail(@PathVariable Integer id, @PathVariable Integer type) {

        return null;
    }

    /**
     * 列表数据CR设置 & 文件上传
     * @return
     */
    @PostMapping("/ipre/cr/upload")
    public BaseResponse uploadListCr() {

        return null;
    }

    /**
     * ipre文件模版下载
     * @return
     */
    @GetMapping("/ipre/template/download")
    public BaseResponse downloadTemplate() {

        return null;
    }

    /**
     * ipre文件数据解析
     * @return
     */
    @PostMapping("/ipre/template/upload")
    public BaseResponse uploadTemplate() {

        return null;
    }

    /**
     * ipre信息 关联附件上传
     * @return
     */
    @PostMapping("/ipre/enclosure/upload")
    public BaseResponse uploadEnclosure() {

        return null;
    }

}
