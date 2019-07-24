package com.crazy.portal.bean.common;

import lombok.Builder;
import lombok.Data;

@Data
public class PageBean {

    protected Integer pageIndex = PageConstant.DEFAULT_PAGE_INDEX;

    protected Integer pageSize = PageConstant.DEFAULT_PAGE_SIZE;
}
