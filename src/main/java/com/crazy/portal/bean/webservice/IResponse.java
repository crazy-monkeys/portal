/**   
 * <p>
 * Description:<br />
 * </p>
 */
package com.crazy.portal.bean.webservice;

/**   
 * <p>
 * Description:<br />
 * </p>
 * @author Bill Chan
 * @date 2017年3月30日 下午2:40:23
 */

public interface IResponse<U extends IResponse<U>> {
	
	/**
	 * 处理结果
	 * @return
	 */
	public int getResultCode();
	
	/**
	 * 描述信息
	 * @return
	 */
	public String getResultDesc();
	
}
