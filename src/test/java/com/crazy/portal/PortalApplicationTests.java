package com.crazy.portal;

import com.crazy.portal.config.email.EmailHelper;
import com.crazy.portal.dao.system.TemAddressMapper;
import com.crazy.portal.util.CallApiUtils;
import com.crazy.portal.util.FTPClientUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PortalApplicationTests {

	@Resource
	private EmailHelper emailHelper;

	@Resource
	private TemAddressMapper temAddressMapper;


	@Resource
	private FTPClientUtil ftpClientUtil;

	@Test
	public void createUser(){
		CallApiUtils.queryOrganisation();
		//CallApiUtils.callECCCreditApi("10217");
	}
}
