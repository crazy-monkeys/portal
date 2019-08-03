package com.crazy.portal.service.handover;

import com.crazy.portal.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by lee on 2019/8/3.
 */

@Slf4j
@Service
public class DeliverService {

    @Value("${deliver.template-path}")
    private String templatePath;

    public void downloadTemplate(HttpServletResponse response) {
        FileUtil.download(response, templatePath, "deliver_template.xlsx");
    }

}
