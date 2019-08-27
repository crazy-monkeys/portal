package com.crazy.portal.bean.system;

import com.crazy.portal.bean.common.PageBean;
import lombok.Data;

/**
 * @ClassName: UserCustomerMappingBean
 * @Author: God Man Qiu~
 * @Date: 2019/8/26 23:15
 */
@Data
public class UserCustomerMappingBean extends PageBean{
    private Integer mappingModel;
    /**
     * 营销运作部
     */
    private Integer cdUserId;

    /**
     * 代理商经营部
     */
    private Integer dealerUserId;

    /**
     * 负责的客户id
     */
    private Integer custId;
}
