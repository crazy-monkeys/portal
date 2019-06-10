package com.crazy.portal.controller.system;

import com.crazy.portal.bean.BaseResponse;
import com.crazy.portal.entity.company.TCompanyFile;
import com.crazy.portal.entity.company.TCompanyInfoVO;
import com.crazy.portal.entity.company.TContactInformationVO;
import com.crazy.portal.entity.company.TSolutionInfoVO;
import com.crazy.portal.service.company.CompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: CompanyController
 * @Author: God Man Qiu~
 * @Date: 2019/4/19 14:22
 */
@Slf4j
@RestController
@RequestMapping("/company")
public class CompanyController {
    @Resource
    private CompanyService companyService;

    @RequestMapping("/getCompany")
    public TCompanyInfoVO getCompany(String companyName, String region){
        return companyService.selectByName(companyName, region);
    }

    @RequestMapping("/getUserCompany")
    public TCompanyInfoVO getUserCompany(Integer userId){
        return companyService.getUserCompany(userId);
    }

    @RequestMapping("/create")
    public BaseResponse create(@RequestBody TCompanyInfoVO companyBean){
        BaseResponse resp = new BaseResponse();
        try{
            return companyService.createCompany(companyBean);
        }catch (Exception e){
            log.error("",e);
            return resp;
        }
    }

    @RequestMapping("/saveContactInformation")
    public BaseResponse saveContactInformation(@RequestBody List<TContactInformationVO> contactBeans){
        BaseResponse resp = new BaseResponse();
        try{
            companyService.saveContactInformation(contactBeans);
            resp.success();
        }catch (Exception e){
            log.error("",e);
        }
        return resp;
    }

    @RequestMapping("/saveSolutionBean")
    public BaseResponse saveSolutionBean(@RequestBody TSolutionInfoVO solutionBean){
        BaseResponse resp = new BaseResponse();
        try{
            companyService.saveSolutionBean(solutionBean);
            resp.success();
        }catch (Exception e){
            log.error("",e);
        }
        return resp;
    }
}
