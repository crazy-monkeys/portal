package com.crazy.portal.service.webservice;

import com.crazy.portal.dao.webservice.ApiLogMapper;
import com.crazy.portal.dao.webservice.ApiUsersMapper;
import com.crazy.portal.entity.webservice.ApiLog;
import com.crazy.portal.entity.webservice.ApiUsers;
import com.crazy.portal.util.DateUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Description: TODO
 * @Author Shawn
 * @Date 2019-08-24 18:03
 * @Modify by
 */
@Service
public class ApiUsersService {

    @Resource
    private ApiUsersMapper apiUsersMapper;
    @Resource
    private ApiLogMapper apiLogMapper;

    public ApiUsers selectApiUserById(Integer id){
        return apiUsersMapper.selectByPrimaryKey(id);
    }

    public void saveLog(String apiUserId, String requestMessage, String responseMessage, Date startTime, String reqCode, String errorMessage){
        ApiLog log = new ApiLog();
        log.setReqCode(reqCode);
        log.setRequestMessage(requestMessage);
        log.setResponseMessage(responseMessage);
        log.setErrorMessage(errorMessage);
        log.setReqTime(Integer.valueOf(new Date().getTime()-startTime.getTime()+""));
        log.setCreateId(Integer.parseInt(apiUserId));
        log.setCreateTime(DateUtil.getCurrentTS());
        apiLogMapper.insertSelective(log);
    }
}
