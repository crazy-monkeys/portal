package com.crazy.portal.service.handover;

import com.crazy.portal.bean.handover.HandoverUploadVO;
import com.crazy.portal.entity.handover.DeliverReceiveRecord;
import com.github.pagehelper.PageInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by lee on 2019/8/24.
 */
public interface IHandover<T> {

    HandoverUploadVO verificationData(List<T> data, Integer userId);

    HandoverUploadVO verificationDataByErrorData(List<?> data, Integer userId, Integer recordId);

    HandoverUploadVO saveData(Integer id, Integer userId);

    void downloadError(HttpServletResponse response, String fileName);

    PageInfo<T> getDetailList(Integer dealerId, Integer pageNum, Integer pageSize,
                              String uploadStartTime, String uploadEndTime,
                              String handoverStartTime, String handoverEndTime,
                              String customerFullName, String productModel, String deliveryType,
                              String orderMonth, String customerOrderNumber,
                              String warehouse, String deliveryCompany);

    void downloadRejectData(Integer recordId, HttpServletResponse response);

    PageInfo<T> getDetailInfo(Integer recordId, Integer pageNum, Integer pageSize);

    void downloadTemplate(HttpServletResponse response);

    List<?> uploadTemplateData(MultipartFile excel, Integer userId);

    void batchDeleteData(Integer[] ids, Integer userId);

    void downloadDataByUpdate(HttpServletResponse response, Integer[] ids);

    void uploadDataByUpdate(MultipartFile excel, Integer userId);
}
