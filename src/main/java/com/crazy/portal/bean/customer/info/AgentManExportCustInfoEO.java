package com.crazy.portal.bean.customer.info;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;
import lombok.ToString;

/**
 * @Description 客户信息导出表头
 * @Author Jingang.yuan
 * @Date 2020-03-5
 * @Modify by
 */
@Data
@ToString
public class AgentManExportCustInfoEO extends BaseRowModel{
	
	
	@ExcelProperty(value = "序号", index = 0)
    private Integer serial;
	@ExcelProperty(value = "渠道", index = 1)
    private String channel;
	@ExcelProperty(value = "代理商名称", index = 2)
    private String agencyName;
	@ExcelProperty(value = "代理商外部号", index = 3)
    private Integer dealerId;
	@ExcelProperty(value = "内部客户", index = 4)
    private String custAbbreviation;
	@ExcelProperty(value = "内部客户号", index = 5)
    private String inCode;
	@ExcelProperty(value = "外部客户", index = 6)
    private String custName;
	@ExcelProperty(value = "外部客户号", index = 7)
    private String outCode;
	@ExcelProperty(value = "外部客户关联关系", index = 8)
    private String businessType;
	@ExcelProperty(value = "客户属性", index = 9)
    private String custType;
	@ExcelProperty(value = "代表处", index = 10)
    private String representative;
	@ExcelProperty(value = "队长", index = 11)
    private String ambName;
	@ExcelProperty(value = "销售", index = 12)
	private String reportSalesName;
}
