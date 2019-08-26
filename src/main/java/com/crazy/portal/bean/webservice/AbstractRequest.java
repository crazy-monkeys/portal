package com.crazy.portal.bean.webservice;

import com.alibaba.fastjson.annotation.JSONField;
import com.crazy.portal.util.BeanUtils;
import com.crazy.portal.util.SHA1;
import com.crazy.portal.util.StringUtil;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.text.ParseException;
import java.util.*;


/**   
 * <p>
 * Description:<br />
 * </p>
 * @author Bill Chan
 * @date 2017年3月30日 下午2:51:51
 */
@Data
public abstract class AbstractRequest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractRequest.class);
	
	private static final long INTERVAL = 300000L;

	protected String signature;          //签名
	protected String reqCode;            //请求代码
	protected String apiUserId;          //接口用户
	protected String timestamp;          //时间戳(ms)
	protected String apiSecret;          //秘钥

	@JSONField(serialize = false)
	protected Map<String,String> paramsValue;
	@JSONField(serialize = false)
	private static String[] paramNames = {"signature","reqCode","apiUserId","timestamp"};

	
	
	/**
	 * 获取所有参数名称
	 * @return
	 */
	public String[] getParamNames(){
		return paramNames;
	}
	
	/**
	 * 设置请求参数值
	 */
	public void setParamsValue(Map<String,String> map){
		this.paramsValue = map;
		String[] paramsName = this.getParamNames();
		if( paramsName==null || paramsValue==null ) return;
		for(int i=0;i<paramsName.length;i++ ){
			Object value = paramsValue.get(paramsName[i]);
			if(value != null){
				ReflectionUtils.setFieldValue(this, paramsName[i], paramsValue.get(paramsName[i]));
			}
		}
	}

	/**
	 * 校验通用参数
	 * @return
	 * @throws ParseException
	 */
    public List<String> checkParams(){
		setParamsValue(BeanUtils.transBeanMapStr(this));
    	List<String>  list = new ArrayList<String>();
		if(StringUtil.isBlank(reqCode)){
			list.add(String.format("reqCode 不能为空"));
			return list;
		}
		if(StringUtil.isBlank(apiUserId)){
			list.add(String.format("apiUserId 不能为空"));
			return list;
		}
		if(StringUtil.isBlank(signature)){
			list.add(String.format("signature 不能为空"));
			return list;
		}
		if(StringUtil.isBlank(timestamp)){
			list.add(String.format("timestamp 不能为空"));
			return list;
		}
    	return list;
    }

	/**
	 * 验证令牌
	 * @return
	 */
	public boolean checkVerifyCode(){
		List<String> params = new ArrayList<String>();
		for(Map.Entry<String, String> entry : paramsValue.entrySet()){
			if(entry.getKey().equals("apiSecret") || entry.getKey().equals("signature") || entry.getKey().equals("paramsValue") || entry.getKey().equals("paramNames")){
				continue;
			}
			if(StringUtil.isNotBlank(entry.getValue())) {
				params.add(entry.getValue());
			}
		}
		//1. 参数进行字典序排序
		Collections.sort(params, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				if(o1==null){o1="";}
				if(o2==null){o2="";}
				return o1.compareTo(o2);
			}
		});
		StringBuffer sb = new StringBuffer();
		sb.append(apiSecret);
		for (int i = 0; i < params.size(); i++) {
			sb.append(params.get(i));
		}
		sb.append(apiSecret);
		LOGGER.info("-----加密前字符串--------"+sb.toString());
		String valid_signature = SHA1.encode(sb.toString());
		LOGGER.info("服务端加密后的token---------"+valid_signature);
		LOGGER.info("客户端加密后的token---------"+signature);
		return valid_signature.equals(signature);
	}

	/**
	 *
	 * <p>
	 * Description:校验接口连接时间是否有效<br />
	 * </p>
	 * @author Bill Chan
	 * @date 2017年3月31日 下午6:24:19
	 * @return
	 * boolean
	 * @throws InterruptedException
	 */
	public boolean checkVerifyTime(){
		Long timeInterval = new Date().getTime() - Long.parseLong(timestamp);
		return timeInterval<INTERVAL?true:false;
	}
}
