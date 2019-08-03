package com.crazy.portal.bean.customer.visitRecord;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;
import lombok.ToString;

/**
 * @Description 拜访记录
 * @Author Shawn
 * @Date 2019-08-01 22:04
 * @Modify by
 */
@Data
@ToString
public class VisitRecordEO  extends BaseRowModel {

    @ExcelProperty(value = "拜访日期", index = 0)
    private String visitDate;
    @ExcelProperty(value = "客户所在地", index = 1)
    private String customerLocation;
    @ExcelProperty(value = "客户编号", index = 2)
    private String customerCode;
    @ExcelProperty(value = "拜访次数", index = 3)
    private Integer visitNumber;
    @ExcelProperty(value = "拜访目的", index = 4)
    private String visitPurpose;
    @ExcelProperty(value = "项目名称", index = 5)
    private String projectName;
    @ExcelProperty(value = "项目状态", index = 6)
    private String projectStatus;
    @ExcelProperty(value = "项目所属展锐事业部", index = 7)
    private String projectDepartment;
    @ExcelProperty(value = "会谈内容简述", index = 8)
    private String talkContent;
    @ExcelProperty(value = "后续行动计划", index = 9)
    private String followPlan;
    @ExcelProperty(value = "诉求描述", index = 10)
    private String claimDescription;
    @ExcelProperty(value = "参与人员：展锐", index = 11)
    private String participantsZr;
    @ExcelProperty(value = "参与人员：客户", index = 12)
    private String participantsCt;
    @ExcelProperty(value = "参与人员：代理", index = 13)
    private String participantsDl;

}
