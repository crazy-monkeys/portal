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
 * @date 2017年3月30日 下午4:49:26
 */

public class CommonResponse extends AbstractResponse implements IResponse<CommonResponse>{
    
	private int resultCode;
	private String resultDesc;
	
	public int getResultCode() {
		return resultCode;
	}
	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
	public String getResultDesc() {
		return resultDesc;
	}
	public void setResultDesc(String resultDesc) {
		this.resultDesc = resultDesc;
	}
	
	
	
	
	
}
