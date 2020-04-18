package com.crazy.portal.bean.customer.visitRecord;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @Description 代理商经营部导出拜访记录模板
 * @Author Jingang
 * @Date 2020-03-05
 * @Modify by
 */
@Data
public class AgentManExportVisitRecordEO{

	@ExcelProperty(value = "代理", index = 0)
    private String agentAbbreviate;
	@ExcelProperty(value = "年周", index = 1)
    private String yearWeek;
    @ExcelProperty(value = "拜访日期", index = 2)
    private String visitDate;
    @ExcelProperty(value = "客户所在地", index = 3)
    private String customerLocation;
    @ExcelProperty(value = "客户类别", index = 4)
    private String businessType;
    @ExcelProperty(value = "客户名称", index = 5)
    private String customerName;
    @ExcelProperty(value = "阿米巴队长", index = 6)
    private String ambTeamLeaderName;
    @ExcelProperty(value = "拜访目的", index = 7)
    private String visitPurpose;
    @ExcelProperty(value = "项目名称", index = 8)
    private String projectName;
    @ExcelProperty(value = "项目状态", index = 9)
    private String projectStatus;
    @ExcelProperty(value = "项目背景及市场信息", index = 10)
    private String backInformation;
    @ExcelProperty(value = "项目所属展锐事业部", index = 11)
    private String projectDepartment;
    @ExcelProperty(value = "产品线", index = 12)
    private String productLine;
    @ExcelProperty(value = "会谈内容简述", index = 13)
    private String talkContent;
    @ExcelProperty(value = "后续行动计划", index = 14)
    private String followPlan;
    @ExcelProperty(value = "诉求描述", index = 15)
    private String claimDescription;
    @ExcelProperty(value = "展锐接口回复", index = 16)
    private String interfaceRecovery;
    @ExcelProperty(value = "参与人员：展锐", index = 17)
    private String participantsZr;
    @ExcelProperty(value = "参与人员：客户", index = 18)
    private String participantsCt;
    @ExcelProperty(value = "参与人员：代理", index = 19)
    private String participantsDl;

}
