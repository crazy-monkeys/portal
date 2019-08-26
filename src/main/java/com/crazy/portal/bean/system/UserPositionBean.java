package com.crazy.portal.bean.system;

import lombok.Data;

/**
 * @ClassName: UserPositionBean
 * @Author: God Man Qiu~
 * @Date: 2019/8/26 16:30
 */
@Data
public class UserPositionBean {
    private Integer inUserId; //员工id
    private String userNo;   //员工工号
    private String userName;  //员工姓名
    private String userMail;  //员工邮箱
    private String position;  //员工职位
    private String orgName;   //所属部门
    private String pm;        //部门主管
}
