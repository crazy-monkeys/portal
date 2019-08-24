/**   
 * <p>
 * Description:<br />
 * </p>
 */
package com.crazy.portal.bean.webservice;

import java.util.List;
import java.util.Map;

/**   
 * <p>
 * Description:<br />
 * </p>
 * @author Bill Chan
 * @date 2017年3月30日 下午2:41:36
 */

public interface IRequest<T extends IRequest<T>> {
	/**
	 * 设置请求参数值
	 */
	public void setParamsValue(Map<String, String> map);
	/**
	 * 获得请求参数值
	 */
	public Map<String,String> getParamsValue();
	
	/**
	 * 获取请求参数名称，须与接口顺序保持一致,但请求参数中并未包括verifyCode
	 * @return
	 */
	public String[] getParamNames();
    /**
     * 对参数进行校验
     */
	public List<String> checkParams();

	/**
	 * 检查签名是否正确
	 * @return
	 */
	public boolean checkVerifyCode();

	/**
	 * <p>
	 * Description:<br />
	 * </p>
	 * @author Bill Chan
	 * @date 2017年3月31日 下午6:21:47
	 * @return
	 * boolean
	 */
	public boolean checkVerifyTime();

	/**
	 * 设置token
	 */
	public void setApiSecret(String apiSecret);
}
