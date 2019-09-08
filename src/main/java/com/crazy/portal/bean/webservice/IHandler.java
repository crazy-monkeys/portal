/**   
 * <p>
 * Description:<br />
 * </p>
 */
package com.crazy.portal.bean.webservice;


import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * Description:<br />
 * </p>
 * @author Bill Chan
 * @date 2017年3月30日 下午2:40:59
 */

public interface IHandler<T extends IRequest<T>,U extends IResponse> {

	/**
	 * 处理过程
	 * @param request
	 * @return
	 */
	@Transactional
	public U process(T request);
	
}
