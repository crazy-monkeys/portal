package com.crazy.portal.service.customer;

import com.crazy.portal.bean.customer.basic.FileVO;
import com.crazy.portal.dao.cusotmer.CustomerFileMapper;
import com.crazy.portal.entity.cusotmer.CustomerFile;
import com.crazy.portal.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
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
    @Value("${file.path.root}")
    private String filePath;
    private static final String CUST_FILE_PATH = "custfile";

    public List<CustomerFile> selectByCustId(Integer custId){
        return customerFileMapper.selectByCustId(custId);
    }

    public void saveOrUpdate(List<CustomerFile> customerFiles, Integer custId){
        if(null == customerFiles || customerFiles.isEmpty()){
            return;
        }
        customerFiles.forEach(e->{
            if(null == e.getFileId()){
                FileVO fileVO = FileUtil.upload(e.getFile(), getCustFilePath());
                e.setType(e.getFileType());
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

    public CustomerFile saveOrUpdate(MultipartFile files, Integer custId){
        FileVO fileVO = FileUtil.upload(files, getCustFilePath());
        CustomerFile file = new CustomerFile();
        file.setFileName(fileVO.getFileName());
        file.setFilePath(fileVO.getFilePath());
        file.setActive(1);
        customerFileMapper.insertSelective(file);
        return file;
    }

    public String getCustFilePath(){
        return filePath.concat(File.separator).concat(CUST_FILE_PATH);
    }

    public void deleteByCustId(List<CustomerFile> customerFiles, List<CustomerFile> results, Integer custId){
        if(null != results && !results.isEmpty()){
            if(null == customerFiles || customerFiles.isEmpty()){
                customerFileMapper.deleteByCustId(custId);
            }else{
                for(CustomerFile r : results){
                    Boolean flg = true;
                    for(CustomerFile c : customerFiles){
                        if(r.getFileId() == c.getFileId()){
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
