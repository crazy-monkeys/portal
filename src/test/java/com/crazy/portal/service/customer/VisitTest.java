package com.crazy.portal.service.customer;

import com.crazy.portal.bean.customer.wsdl.visits.*;
import com.crazy.portal.util.CallApiUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName: VisitTest
 * @Author: God Man Qiu~
 * @Date: 2019/9/14 10:34
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class VisitTest {
    @Test
    public void createVisit() {
        try{
            VisitCreateBean visitBean = new VisitCreateBean();
            visitBean.setObjectNodeSenderTechnicalID("001");
            visitBean.setName("test");
            visitBean.setLifeCycleStatusCode("1");
            visitBean.setVisitTypeCode("Z01");

            visitBean.setStartDateTime(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSS'Z'").format(new Date()));
            visitBean.setEndDateTime(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSS'Z'").format(new Date()));
            visitBean.setZMeetingContent("test123");
            visitBean.setZFollowUpPlans("test456");
            visitBean.setZRequirementDescription("test789");

            OrganizerPartyBean organizerPartyBean = new OrganizerPartyBean();
            organizerPartyBean.setBusinessPartnerInternalID("1000042");
            visitBean.setOrganizerPartyBean(organizerPartyBean);

            MainActivityPartyBean mainActivityPartyBean = new MainActivityPartyBean();
            mainActivityPartyBean.setBusinessPartnerInternalID("1000042");
            visitBean.setMainActivityPartyBean(mainActivityPartyBean);

            OtherPartyBean otherPartyBean = new OtherPartyBean();
            otherPartyBean.setBusinessPartnerInternalID("1000042");
            visitBean.setOtherPartyBean(otherPartyBean);

            VisitCreateHeader header = new VisitCreateHeader();
            VisitCreateContent content = new VisitCreateContent(visitBean,header);

            VisitCreateBody body = new VisitCreateBody(content);
            VisitCreate create = new VisitCreate(body);
            CallApiUtils.callC4cVisits(create);
        }catch (Exception e){
            e.printStackTrace();
        }

    }


}
