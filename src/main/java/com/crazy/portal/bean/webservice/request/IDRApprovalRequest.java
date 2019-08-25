package com.crazy.portal.bean.webservice.request;

import com.crazy.portal.bean.webservice.AbstractRequest;
import com.crazy.portal.bean.webservice.IRequest;
import com.crazy.portal.util.StringUtil;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description TODO
 * @Author Shawn
 * @Date 2019-08-23 23:15
 * @Modify by
 */
@Getter
@Setter
public class IDRApprovalRequest extends AbstractRequest implements IRequest<IDRApprovalRequest> {

    protected static final String[] PARAM_NAMES = new String[] {"type", "orderNumber", "reviewedPeople", "reviewStatus", "comment", "currentReviewer"};

    //单据类型,KP(报价),CP（差价）,TH（退货）,HH（换货）
    private String type;
    //单号编码
    private String orderNumber;
    //审批人员，返回用户名称
    private String reviewedPeople;
    //审批状态Agree(同意)，Reject(驳回)
    private String reviewStatus;
    //审批意见comment
    private String comment;
    //下一环节审批人状态，返回用户名称，返回人员为空，表示审批结束或驳回。
    private String currentReviewer;

    @Override
    public List<String> checkParams(){
        //首先检查接口约定参数
        List<String> commonList = super.checkParams();
        if (commonList.size() > 0) {
            return commonList;
        }
        Map<String, String> paramsMaps = super.getParamsValue();
        List<String> errorMsgLists = new ArrayList<String>();
        if(StringUtil.isBlank(paramsMaps.get("type"))){
            errorMsgLists.add("type 不能为空");
            return errorMsgLists;
        }
        if(StringUtil.isBlank(paramsMaps.get("orderNumber"))){
            errorMsgLists.add("orderNumber 不能为空");
            return errorMsgLists;
        }
        if(StringUtil.isBlank(paramsMaps.get("reviewedPeople"))){
            errorMsgLists.add("reviewedPeople 不能为空");
            return errorMsgLists;
        }
        if(StringUtil.isBlank(paramsMaps.get("reviewStatus"))){
            errorMsgLists.add("reviewStatus 不能为空");
            return errorMsgLists;
        }
        return errorMsgLists;
    }

    @Override
    public String[] getParamNames() {
        return (String[]) ArrayUtils.addAll(super.getParamNames(), PARAM_NAMES);
    }

}
