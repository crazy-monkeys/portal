package com.crazy.portal.bean.customer.info;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;
import lombok.ToString;
/**
 * @Description 拜访记录
 * @Author Jingang.yuan
 * @Date 2020-02-21 
 * @Modify by
 */
@Data
@ToString
public class CustomerInfoEO extends BaseRowModel{
	    @ExcelProperty(value = "客户名称", index = 0)
	    private String custName;
	    @ExcelProperty(value = "客户外部编号", index = 1)
	    private String outCode;
	    @ExcelProperty(value = "License客户", index = 2)
	    private Integer isLicense;
	    @ExcelProperty(value = "客户类型", index = 3)
	    private String businessType;
	    @ExcelProperty(value = "负责代理商", index = 4)
	    private String reportDealerName;
	    @ExcelProperty(value = "负责销售", index = 5)
	    private String reportSalesName;
	    @ExcelProperty(value = "客户状态", index = 6)
	    private Integer custType;
	    @ExcelProperty(value = "报备日期", index = 7)
	    @JSONField(format="yyyy-MM-dd HH:mm:ss")
	    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	    private Date updateTime;

}
