package com.crazy.portal.bean.webservice;

/**
 * <p>
 * Description:<br />
 * </p>
 * @author Bill Chan
 * @date 2017年3月30日 下午3:35:39
 */
public enum INTERFACE_CODE {
	
	SUCCESS(1,"操作成功","success"),
	
	SYSTEM_ERROR(-1,"系统错误","System error"),
	
	NO_ENTITY(-2,"业务参数校验不通过，为空或不符合规则",""),

	CODE1001(1001,"请输入reqCode,请参考接口文档","req_code is require"),
	
	CODE1002(1002,"令牌验证失败","secret key validation is not through"),
	
	CODE1003(1003,"没有找到对应的请求代码（reqCode）实例,请参考接口文档","req_code not found entity"),

	CODE1005(1005,"用户不存在","user doesn't exist"),

	CODE1006(1006,"接口调用有效认证时间已到期","Interface call effective certificate has expired"),
	
	CODE1007(1007,"非法访问","illegal access"),

	CODE1008(1008,"参数映射异常","")


	;

    private final int value;          //code值
    private final String zh_desc;     //中文描述
    private final String en_desc;     //英文描述
    
    private INTERFACE_CODE(int value,String zh_desc,String en_desc) {
        this.value = value;
        this.zh_desc  = zh_desc;
        this.en_desc  = en_desc;
    }
    public int getValue() {
		return value;
	}

	public String getZh_desc() {
		return zh_desc;
	}

	public String getEn_desc() {
		return en_desc;
	}

}
