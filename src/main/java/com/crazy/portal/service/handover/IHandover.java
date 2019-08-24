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

    void submitData(Integer id, String type);

    void downloadError(HttpServletResponse response, String fileName);

    PageInfo<T> getDetailList(Integer dealerId, Integer pageNum, Integer pageSize);

    void downloadRejectData(Integer recordId, HttpServletResponse response);

    PageInfo<T> getDetailInfo(Integer recordId, Integer pageNum, Integer pageSize);

    void downloadTemplate(HttpServletResponse response);

    List<?> uploadTemplateData(MultipartFile excel, Integer userId);
}
