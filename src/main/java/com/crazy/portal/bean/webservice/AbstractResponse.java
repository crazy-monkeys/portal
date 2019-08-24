package com.crazy.portal.bean.webservice;


import com.crazy.portal.config.exception.BusinessException;
import lombok.Data;

/**
 * <p>
 * Description:<br />
 * </p>
 * @author Bill Chan
 * @date 2017年3月30日 下午3:44:20
 */
@Data
public class AbstractResponse{
	
	protected int resultCode ;
	protected String resultDesc;

	public AbstractResponse() {
	}

	public AbstractResponse(int resultCode, String resultDesc) {
		this.resultCode = resultCode;
		this.resultDesc = resultDesc;
	}

	public void success(){
		this.resultCode = INTERFACE_CODE.SUCCESS.getValue();
		this.resultDesc = INTERFACE_CODE.SUCCESS.getZh_desc();
	}

	public void exception(BusinessException e){
		this.resultCode = e.getErrorCode();
		this.resultDesc = e.getMessage();
	}

	public void exception(int resultCode, String resultDesc){
		this.resultCode = resultCode;
		this.resultDesc = resultDesc;
	}

	public void exception(INTERFACE_CODE type){
		this.resultCode = type.getValue();
		this.resultDesc = type.getZh_desc();
	}

}
