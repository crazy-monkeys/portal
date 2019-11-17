package com.crazy.portal.service.customer;

import com.crazy.portal.bean.customer.basic.FileVO;
import com.crazy.portal.dao.cusotmer.CustomerFileMapper;
import com.crazy.portal.entity.cusotmer.CustomerFile;
import com.crazy.portal.util.BusinessUtil;
import com.crazy.portal.util.ErrorCodes;
import com.crazy.portal.util.FileUtil;
import com.crazy.portal.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;

import static com.crazy.portal.util.FileUtil.upload;

/**
 * @ClassName: CustomerFileService
 * @Author: God Man Qiu~
 * @Date: 2019/8/29 19:40
 */
@Slf4j
@Service
public class CustomerFileService {
    @Resource
    private CustomerFileMapper customerFileMapper;
    @Value("${file.path.customer}")
    private String filePath;

    @Value("${file.path.reader-customer}")
    private String portalFileUrl;


    public List<CustomerFile> selectByCustId(Integer custId){
        List<CustomerFile> files = customerFileMapper.selectByCustId(custId);
        files.forEach(e->{
            e.setFilePath(portalFileUrl+e.getFileName());
        });
        return files;
    }

    @Transactional
    public void saveOrUpdate(List<CustomerFile> customerFiles, Integer custId){
        if(null == customerFiles || customerFiles.isEmpty()){
            return;
        }
        customerFiles.forEach(e->{
            if(null == e.getFileId()){
                FileVO fileVO = FileUtil.upload(e.getFile(), filePath);
                BusinessUtil.assertFlase(StringUtil.isEmpty(e.getType()), ErrorCodes.BusinessEnum.CUSTOMER_FILE_TYPE_IS_NOT_NULL);
                e.setType(e.getType());
                e.setFileName(fileVO.getFileName());
                e.setFilePath(fileVO.getFullPath());
                e.setCustId(custId);
                e.setActive(1);
                customerFileMapper.insertSelective(e);
            }else{
                e.setCustId(custId);
                customerFileMapper.updateByPrimaryKeySelective(e);
            }
        });
    }

    @Transactional
    public CustomerFile saveOrUpdate(MultipartFile files, Integer custId){
        FileVO fileVO = FileUtil.upload(files, filePath);
        CustomerFile file = new CustomerFile();
        file.setFileName(fileVO.getFileName());
        file.setFilePath(fileVO.getFilePath());
        file.setActive(1);
        customerFileMapper.insertSelective(file);
        return file;
    }

    @Transactional
    public void deleteByCustId(List<CustomerFile> customerFiles, List<CustomerFile> results, Integer custId){
        if(null != results && !results.isEmpty()){
            if(null == customerFiles || customerFiles.isEmpty()){
                customerFileMapper.deleteByCustId(custId);
            }else{
                for(CustomerFile r : results){
                    Boolean flg = true;
                    for(CustomerFile c : customerFiles){
                        if(r.getFileId().equals(c.getFileId())){
                            flg = false;
                            break;
                        }
                    }
                    if(flg){
                        customerFileMapper.deleteByPrimaryKey(r.getFileId());
                    }
                }
            }
        }
    }
}
