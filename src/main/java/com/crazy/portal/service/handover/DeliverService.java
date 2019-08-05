package com.crazy.portal.service.handover;

import com.crazy.portal.bean.handover.DeliverTemplateBean;
import com.crazy.portal.dao.handover.DeliverRecordMapper;
import com.crazy.portal.entity.handover.DeliverRecord;
import com.crazy.portal.util.BusinessUtil;
import com.crazy.portal.util.ExcelUtils;
import com.crazy.portal.util.FileUtil;
import com.crazy.portal.util.PortalUtil;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static com.crazy.portal.util.ErrorCodes.BusinessEnum.*;

/**
 * Created by lee on 2019/8/3.
 */

@Slf4j
@Service
public class DeliverService {

    @Resource
    private DeliverRecordMapper deliverRecordMapper;

    @Value("${file.path.deliver.template}")
    private String templatePath;
    @Value("${file.path.deliver.push}")
    private String pushPath;
    @Value("${file.path.deliver.pull}")
    private String errorDataPath;

    public void downloadTemplate(HttpServletResponse response) {
        FileUtil.download(response, templatePath, "deliver_template.xlsx");
    }

    public List<DeliverTemplateBean> uploadTemplate(MultipartFile excel, Integer userId) {
        String dealerName = "Mock dealer";
        if(log.isDebugEnabled()){
            log.debug("Current user identity as [{}]", userId);
        }
        //判断当前用户是否为 代理商
        BusinessUtil.assertFlase(false, HANDOVER_DELIVER_NOT_DEALER);
        DeliverRecord record = new DeliverRecord();
        record.setDealerId(userId);
        record.setDealerName(dealerName);
        record.setDeliveryDate(new Date());
        record.setUploadTime(new Date());
        record.setCreateTime(new Date());
        deliverRecordMapper.insertSelective(record);
        List<DeliverTemplateBean> data =  ExcelUtils.readExcel(excel, DeliverTemplateBean.class , 1);
        if(null == data || data.isEmpty()){
            return Collections.emptyList();
        }
        //TODO 对数据进行保存
        return data;
    }

    public void downloadError(HttpServletResponse response) {
        FileUtil.download(response, errorDataPath, "deliver_error.xlsx");
    }

    public void verificationData(Integer[] ids) {
        //TODO 根据ID查询数据库信息

        //TODO 根据信息生产Excel文件

        //TODO 传输给第三方系统

        //TODO 更新校验结果到数据库
    }

    public void submitData(Integer id) {
        //TODO 根据id校验是否过了系统设定时间
        if(true){
            //TODO 更新状态为 待确认 并中止提交
        }
        //TODO 根据id找到校验成功的Excel文件

        //TODO 将Excel 传输给第三方

        //TODO 更新记录状态 为已经提交
    }

    public PageInfo<DeliverRecord> getPageList(String dealerName, Integer status,
                                               String deliveryStartDate, String deliveryEndDate,
                                               String uploadStartTime, String uploadEndTime,
                                               Integer pageNum, Integer pageSize) {
        PortalUtil.defaultStartPage(pageNum,pageSize);
        List<DeliverRecord> result = deliverRecordMapper.selectPageInfo(dealerName, status, deliveryStartDate, deliveryEndDate,
                uploadStartTime, uploadEndTime);
        return new PageInfo<>(result);
    }

    public List<?> getDetailInfo(Integer id) {
        //TODO 查询数据库记录并返回
        return null;
    }

    public void approvalDeliverInfo(Integer id, Integer userId) {
        DeliverRecord record = deliverRecordMapper.selectByPrimaryKey(id);
        if(null == record){
            return;
        }
        if(record.getStatus() != 0){
            //TODO 数据不符合 待确认 状态
        }
        if(false){
            //TODO 用户不是 销售运作部 不允许操作
        }
        //TODO 更新记录状态 待确认 -> 已确认
    }

}
