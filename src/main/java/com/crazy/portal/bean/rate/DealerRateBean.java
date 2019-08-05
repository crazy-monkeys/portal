package com.crazy.portal.bean.rate;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import com.crazy.portal.util.DateUtil;
import lombok.Data;
import lombok.ToString;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName: DealerRateBean
 * @Author: God Man Qiu~
 * @Date: 2019/8/4 11:31
 */
@ToString
public class DealerRateBean extends BaseRowModel{

    @ExcelProperty(value = "客户分类", index = 0)
    private String custType;

    @ExcelProperty(value = "内部客户", index = 1)
    private String custInCode;

    @ExcelProperty(value = "产品层次一", index = 2)
    private String productTypeOne;

    @ExcelProperty(value = "产品层次二", index = 3)
    private String productTypeTwo;

    @ExcelProperty(value = "产品层次三", index = 4)
    private String productTypeThree;

    @ExcelProperty(value = "Product Type", index = 5)
    private String productType;

    @ExcelProperty(value = "基准代理费率%", index = 6)
    private BigDecimal basicRate;

    @ExcelProperty(value = "浮动代理费率%", index = 7)
    private BigDecimal floatRate;

    @ExcelProperty(value = "有效开始日期", index = 8)
    private String validStart;

    public String getCustType() {
        return custType;
    }

    public void setCustType(String custType) {
        this.custType = custType;
    }

    public String getCustInCode() {
        return custInCode;
    }

    public void setCustInCode(String custInCode) {
        this.custInCode = custInCode;
    }

    public String getProductTypeOne() {
        return productTypeOne;
    }

    public void setProductTypeOne(String productTypeOne) {
        this.productTypeOne = productTypeOne;
    }

    public String getProductTypeTwo() {
        return productTypeTwo;
    }

    public void setProductTypeTwo(String productTypeTwo) {
        this.productTypeTwo = productTypeTwo;
    }

    public String getProductTypeThree() {
        return productTypeThree;
    }

    public void setProductTypeThree(String productTypeThree) {
        this.productTypeThree = productTypeThree;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public BigDecimal getBasicRate() {
        return basicRate;
    }

    public void setBasicRate(BigDecimal basicRate) {
        this.basicRate = basicRate;
    }

    public BigDecimal getFloatRate() {
        return floatRate;
    }

    public void setFloatRate(BigDecimal floatRate) {
        this.floatRate = floatRate;
    }

    public String getValidStart() {
        return validStart;
    }

    public void setValidStart(String validStart) {
        this.validStart = validStart;
    }
}
