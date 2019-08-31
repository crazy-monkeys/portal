package com.crazy.portal.entity.system;

import lombok.Data;
import java.util.Date;

@Data
public class SysParameter {
    private Integer id;

    private Integer pModel;

    private Integer pFunction;

    private String pValue;

    private String zhName;

    private String enName;

    private Integer active;

    private Integer insertUser;

    private Date insertTime;

    private Integer updateUser;

    private Date updateTime;
}