package com.crazy.portal.service.company;

import com.crazy.portal.bean.BaseResponse;
import com.crazy.portal.dao.company.*;
import com.crazy.portal.dao.system.UserDOMapper;
import com.crazy.portal.entity.company.*;
import com.crazy.portal.entity.system.UserDO;
import com.crazy.portal.util.OssUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.net.URL;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: CompanyService
 * @Author: God Man Qiu~
 * @Date: 2019/4/19 14:23
 */
@Slf4j
@Service
public class CompanyService {
    @Resource
    private TCompanyInfoVOMapper tCompanyInfoVOMapper;
    @Resource
    private TCompanyFileMapper tCompanyFileMapper;
    @Resource
    private TContactInformationVOMapper tContactInformationVOMapper;
    @Resource
    private TSolutionInfoVOMapper tSolutionInfoVOMapper;
    @Resource
    private TUserCompanyMapper tUserCompanyMapper;
    @Resource
    private UserDOMapper userDOMapper;


    public TCompanyInfoVO getUserCompany(Integer userId){
        return tCompanyInfoVOMapper.getUserCompany(userId);
    }

    public TCompanyInfoVO selectByName(String name, String region){
        return tCompanyInfoVOMapper.selectByName(name, region);
    }

    public TCompanyInfoVO selectByUserIdAndCompanyName(Integer userId, String companyName){
        return tCompanyInfoVOMapper.selectByUserIdAndCompanyName(userId, companyName);
    }

    public BaseResponse createCompany(TCompanyInfoVO companyBean){
        companyBean.setCreateUserId(1);
        companyBean.setActive((short)1);
        tCompanyInfoVOMapper.insertSelective(companyBean);

        UserDO user = userDOMapper.selectById(companyBean.getUserId());
        user.setUserStatus(3);
        userDOMapper.updateByPrimaryKeySelective(user);

        TUserCompany userCompany = new TUserCompany();
        userCompany.setUserId(companyBean.getUserId());
        userCompany.setCompanyId(companyBean.getCompanyId());
        userCompany.setCreateTime(new Date());
        userCompany.setActive((short)1);
        tUserCompanyMapper.insertSelective(userCompany);

        BaseResponse resp = new BaseResponse();
        resp.success(companyBean.getCompanyId());
        return resp;
    }

    @Transactional
    public void createOrUpdateCompany(TCompanyInfoVO companyBean, TContactInformationVO contactBean, TSolutionInfoVO solutionBean, List<TCompanyFile> fileBeans){
        String telePhone = String.format("%s%s%s%s",companyBean.getCountryCode(),companyBean.getStateNumber(),companyBean.getPhoneNumber(),companyBean.getExtension());
        String officePhone = String.format("%s%s%s%s",contactBean.getCountryCode(),contactBean.getStateNumber(),contactBean.getPhoneNumber(),contactBean.getExtension());
        companyBean.setFixedLineTelephone(telePhone);
        if(null != companyBean.getCompanyId()){

        }else{
            companyBean.setCreateUserId(1);
            companyBean.setActive((short)1);
            tCompanyInfoVOMapper.insertSelective(companyBean);

            contactBean.setCompanyId(companyBean.getCompanyId());
            contactBean.setOfficePhone(officePhone);
            contactBean.setIsDefault((short)1);
            contactBean.setCreateTime(new Date());
            contactBean.setCreateUserId(1);
            contactBean.setActive((short)1);
            tContactInformationVOMapper.insertSelective(contactBean);

            solutionBean.setCompanyId(companyBean.getCompanyId());
            solutionBean.setCreateTime(new Date());
            solutionBean.setCreateUserId(1);
            solutionBean.setActive((short)1);
            tSolutionInfoVOMapper.insertSelective(solutionBean);

            TUserCompany userCompany = new TUserCompany();
            //TODO
            userCompany.setUserId(1);
            userCompany.setCompanyId(companyBean.getCompanyId());
            userCompany.setCreateTime(new Date());
            userCompany.setActive((short)1);
            tUserCompanyMapper.insertSelective(userCompany);

            for(TCompanyFile file : fileBeans){
                file.setCompanyId(companyBean.getCompanyId());
                tCompanyFileMapper.updateByPrimaryKeySelective(file);
            }
        }
    }

    public TCompanyFile uploadFile(TCompanyFile companyFile, String filePath){
        String ossName = companyFile.getFileName()+new Date().getTime();
        OssUtil.uploadFile(ossName, filePath);
        URL ossPath = OssUtil.getObjectUrl(ossName);

        companyFile.setOssName(ossName);
        companyFile.setOssPath(ossPath.toString());
        tCompanyFileMapper.insertSelective(companyFile);
        return companyFile;
    }

    public void saveContactInformation(List<TContactInformationVO> contactBeans){
        for(TContactInformationVO contactBean : contactBeans) {
            //String officePhone = String.format("%s%s%s%s", contactBean.getCountryCode(), contactBean.getStateNumber(), contactBean.getPhoneNumber(), contactBean.getExtension());
            //contactBean.setOfficePhone(officePhone);
            contactBean.setIsDefault((short) 1);
            contactBean.setCreateTime(new Date());
            contactBean.setCreateUserId(1);
            contactBean.setActive((short) 1);
            tContactInformationVOMapper.insertSelective(contactBean);
        }
    }
    public void saveSolutionBean(TSolutionInfoVO solutionBean){
        solutionBean.setCreateTime(new Date());
        solutionBean.setCreateUserId(1);
        solutionBean.setActive((short)1);
        tSolutionInfoVOMapper.insertSelective(solutionBean);
    }
}
