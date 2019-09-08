package com.crazy.portal.bean.webservice.request;

import com.crazy.portal.bean.webservice.AbstractRequest;
import com.crazy.portal.bean.webservice.IRequest;
import com.crazy.portal.util.StringUtil;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.ArrayUtils;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: MemberInfoSyncRequest
 * @Author: God Man Qiu~
 * @Date: 2019/8/27 13:24
 */
@Getter
@Setter
public class MemberInfoSyncRequest extends AbstractRequest implements IRequest<MemberInfoSyncRequest>{
    protected static final String[] PARAM_NAMES = new String[] {"inCode","outCode","custName","custEName","abbreviation",
            "custType","isLicense","custRole","mobile","email","custWeb","isWhite","frozenSales","frozenDelivery","frozenOrder",
            "frozenInvoice","registTime","corportaeAssets","staffNumber","developersNumber","businessIntroduction","advantagesIntroduction",
            "customerAgents","assetsInformations","businessInformations","customerContacts","customerProducts","quotas","custBankInfo","relationships",
            "invoiceInfos","sales","addresses","accountTeams","ZRaccountTeams","custStructure"};
    private String inCode;
    private String outCode;
    private String custName;
    private String custEName;
    private String abbreviation;
    private String custType;
    private String isLicense;
    private String custRole;
    private String mobile;
    private String email;
    private String custWeb;
    private String isWhite;
    private String frozenSales;
    private String frozenDelivery;
    private String frozenOrder;
    private String frozenInvoice;
    private String registTime;
    private String corportaeAssets;
    private String staffNumber;
    private String developersNumber;
    private String businessIntroduction;
    private String advantagesIntroduction;
    private String customerAgents;
    private String assetsInformations;
    private String businessInformations;
    private String customerContacts;
    private String customerProducts;
    private String quotas;
    private String custBankInfo;
    private String relationships;
    private String invoiceInfos;
    private String sales;
    private String addresses;
    private String accountTeams;
    private String ZRaccountTeams;
    private String custStructure;

    @Override
    public List<String> checkParams(){
        //首先检查接口约定参数
        List<String> commonList = super.checkParams();
        if (commonList.size() > 0) {
            return commonList;
        }
        Map<String, String> paramsMaps = super.getParamsValue();
        return checkParam(paramsMaps);
    }

    private List<String> checkParam(Map<String, String> paramsMaps){
        List<String> errorMsgLists = new ArrayList<String>();
        if(StringUtil.isEmpty(paramsMaps.get("inCode"))){
            errorMsgLists.add("inCode 不能为空");
        }
        if(StringUtil.isEmpty(paramsMaps.get("outCode"))){
            errorMsgLists.add("outCode 不能为空");
        }
        if(StringUtil.isEmpty(paramsMaps.get("custName"))){
            errorMsgLists.add("custName 不能为空");
        }
        return errorMsgLists;
    }

    @Override
    public String[] getParamNames() {
        return (String[]) ArrayUtils.addAll(super.getParamNames(), PARAM_NAMES);
    }
}
