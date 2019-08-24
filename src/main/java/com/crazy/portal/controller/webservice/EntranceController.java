package com.crazy.portal.controller.webservice;

import com.alibaba.fastjson.JSON;
import com.crazy.portal.bean.webservice.*;
import com.crazy.portal.config.exception.BusinessException;
import com.crazy.portal.controller.BaseController;
import com.crazy.portal.entity.webservice.ApiUsers;
import com.crazy.portal.service.webservice.ApiUsersService;
import com.crazy.portal.util.AopTargetUtils;
import com.crazy.portal.util.SHA1;
import com.crazy.portal.util.SpringBeanTool;
import com.crazy.portal.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by weiying on 2019/7/29.
 */
@Slf4j
@RestController
@RequestMapping("/webservice")
public class EntranceController extends BaseController{

    @Resource
    private ApiUsersService apiUsersService;

    @RequestMapping("/getAccessToken.html")
    @ResponseBody
    public String getAccessToken(@RequestBody Map<String,String> parameterMap){
        String apiSecret = null;
        ArrayList<String> list = new ArrayList<String>();
        for (Map.Entry<String,String> entry : parameterMap.entrySet()) {
            if(entry.getKey().equals("apiSecret")){
                apiSecret = entry.getValue();
                continue;
            }
            list.add((String)entry.getValue());
        }
        if(apiSecret == null){
            throw new IllegalArgumentException("apiSecret is require!");
        }
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if(o1==null){o1="";}
                if(o2==null){o2="";}
                return o1.compareTo(o2);
            }
        });
        StringBuffer sb = new StringBuffer();
        sb.append(apiSecret);
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
        }
        sb.append(apiSecret);
        log.info("-----加密前字符串--------"+sb.toString());
        return SHA1.encode(sb.toString());
    }

    @SuppressWarnings({ "rawtypes", "unchecked"})
    @RequestMapping(value="/invoke.html",method= RequestMethod.POST)
    public @ResponseBody IResponse authentication(@RequestBody Map<String, String> paramJson){
        Date startDate = new Date();
        String errorMessage = "";
        String responseMessage = "";
        String requestCode = paramJson.get("reqCode");
        try {
            if(StringUtil.isEmpty(requestCode)){
                responseMessage = INTERFACE_CODE.CODE1001.getZh_desc();
                return setResponse(INTERFACE_CODE.CODE1001.getValue(),INTERFACE_CODE.CODE1001.getZh_desc());
            }
            //反射获得具体实现类和具体请求类
            IHandler handler  = getHandlerInstance(requestCode);
            Object obj = AopTargetUtils.getTarget(handler);
            Class c = ReflectionUtils.getInterfaceClassGenricType(obj.getClass(),0);
            IRequest request = (IRequest) c.newInstance();

            CommonResponse checkResult = checkCommonRequest(paramJson, request);

            //验证通过委托实现类调用接口
            IResponse iResponse = checkResult.getResultCode() != 1?checkResult:handler.process(request);
            responseMessage = JSON.toJSONString(iResponse);
            return iResponse;
        } catch (BeansException e) {
            log.error("该代码没有找到对应的实现类",e);
            responseMessage = e.getMessage();
            errorMessage = JSON.toJSONString(e);
            return setResponse(INTERFACE_CODE.CODE1003.getValue(),INTERFACE_CODE.CODE1003.getZh_desc());
        } catch(BusinessException e){
            log.error(e.getMessage(),e);
            responseMessage = e.getMessage();
            errorMessage = JSON.toJSONString(e);
            return setResponse(e.getErrorCode(), e.getMessage());
        }catch (Exception e) {
            log.error("系统错误！拦截所有未确定错误类型",e);
            errorMessage = JSON.toJSONString(e);
            responseMessage = e.getMessage();
            return setResponse(INTERFACE_CODE.SYSTEM_ERROR.getValue(),INTERFACE_CODE.SYSTEM_ERROR.getZh_desc());
        }finally {
            apiUsersService.saveLog(paramJson.get("apiUserId"), JSON.toJSONString(paramJson), responseMessage, startDate, requestCode, errorMessage);
        }
    }

    /**
     * <p>
     * Description:<br />
     * </p>
     * @author Bill Chan
     * @date 2017年3月31日 下午7:36:13
     * @param paramJson
     * @param request
     * void
     * @throws Exception
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private CommonResponse checkCommonRequest(Map<String, String> paramJson,
                                              IRequest request) throws Exception {

        CommonResponse commonResp = new CommonResponse();

        ApiUsers apiUser = apiUsersService.selectApiUserById(Integer.valueOf(paramJson.get("apiUserId")));
        if(apiUser == null){
            return setResponse(INTERFACE_CODE.CODE1005.getValue(),INTERFACE_CODE.CODE1005.getZh_desc());
        }

        String apiSecret = apiUser.getApiSecret();
        request.setApiSecret(apiSecret);
        request.setParamsValue(paramJson);

        //校验请求参数  如有错误 则添加错误信息到list中
        List<String> checkParamList = request.checkParams();
        if(!checkParamList.isEmpty()){
            return setResponse(INTERFACE_CODE.NO_ENTITY.getValue(),checkParamList.toString());
        }

        //检查签名是否正确
        if(!request.checkVerifyCode()){
            return setResponse(INTERFACE_CODE.CODE1002.getValue(),INTERFACE_CODE.CODE1002.getZh_desc());
        }
        //超过5分钟视为非法访问
//		if(!request.checkVerifyTime()){
//			return setResponse(INTERFACE_CODE.CODE1007.getValue(),INTERFACE_CODE.CODE1007.getZh_desc());
//		}
        commonResp.setResultCode(INTERFACE_CODE.SUCCESS.getValue());
        return commonResp;
    }

    /**
     * 根据编码获得具体实现类实例
     */
    @SuppressWarnings("rawtypes")
    public IHandler getHandlerInstance(String code) throws BeansException {
        IHandler handler = (IHandler) SpringBeanTool.getBean(code);
        return handler;
    }

    /**
     * 设置错误信息
     */
    public CommonResponse setResponse(int resCode,String resMsg) {
        CommonResponse response = new CommonResponse();
        response.setResultCode(resCode);
        response.setResultDesc(resMsg);
        return response;
    }

}
