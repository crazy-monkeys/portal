package com.crazy.portal.bean.customer;

import com.crazy.portal.entity.customer.*;
import lombok.Data;

import java.util.List;

@Data
public class CustomerVO {

    private CustBasicInfo basicInfo;

    List<CustBankInfo> bankInfos;

    List<CustContactInfo> custContactInfos;

    List<CustEquityInfo> custEquityInfos;

    List<CustIntercourseInfo> custIntercourseInfos;

    List<CustVisitingRecord> custVisitingRecords;
}
