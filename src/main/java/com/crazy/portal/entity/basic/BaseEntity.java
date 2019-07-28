package com.crazy.portal.entity.basic;

import java.util.Date;

public interface BaseEntity {

    public Integer getId();

    public void setCustId(Integer custId);

    public void setCreateUser(Integer userId);

    public void setCreateTime(Date time);
}
