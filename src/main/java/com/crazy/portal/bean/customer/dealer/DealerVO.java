package com.crazy.portal.bean.customer.dealer;

import com.crazy.portal.bean.customer.basic.ContactVO;
import com.crazy.portal.bean.customer.basic.FileVO;
import com.crazy.portal.bean.customer.basic.InvoiceVO;
import com.crazy.portal.bean.customer.basic.ShipVO;
import com.crazy.portal.entity.basic.BasicAddress;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: DealerVO
 * @Author: God Man Qiu~
 * @Date: 2019/7/23 12:16
 */
@Data
public class DealerVO {
    //编码
    private String dealerCode;
    //名称
    private String dealerName;
    //简称
    private String dealerSName;
    //电话
    private String mobile;
    //邮箱
    private String email;
    //网站
    private String dealerWeb;
    //公司资产
    private BigDecimal corporateAssets;
    //注册日期
    private Date registerTime;
    //公司人数
    private Integer corporateNumber;
    //母公司
    private String corporateParents;
    //银行名称
    private String bankName;
    //银行地址
    private String bankAddress;
    //账号
    private String bankAccount;
    //银行识别码
    private String bankBIC;
    //授信额度初始值
    private BigDecimal credit;
    //授信额度占用值
    private BigDecimal creditUSE;
    //授信额度剩余值
    private BigDecimal creditUnUSE;
    //优势价值
    private String advantageValue;
    //优势介绍
    private String advantageIntroduction;
    //业务介绍
    private String businessIntroduction;
    //关系
    private List<ShipVO> ships;
    //联系人
    private List<ContactVO> contacts;
    //开票信息
    private List<InvoiceVO> invoices;
    //附件
    private List<FileVO> files;
    /**
     * 地址信息
     */
    private List<BasicAddress> basicAddress;
}
