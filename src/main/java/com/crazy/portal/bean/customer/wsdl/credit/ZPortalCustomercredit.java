package com.crazy.portal.bean.customer.wsdl.credit;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.3.2
 * 2019-08-01T23:58:35.095+08:00
 * Generated source version: 3.3.2
 *
 */
@WebService(targetNamespace = "urn:sap-com:document:sap:soap:functions:mc-style", name = "ZPortal_customercredit")
@XmlSeeAlso({ObjectFactory.class})
public interface ZPortalCustomercredit {

    @WebMethod(operationName = "Zrfcsdcustomercredit")
    @RequestWrapper(localName = "Zrfcsdcustomercredit", targetNamespace = "urn:sap-com:document:sap:soap:functions:mc-style", className = "com.test.Zrfcsdcustomercredit")
    @ResponseWrapper(localName = "ZrfcsdcustomercreditResponse", targetNamespace = "urn:sap-com:document:sap:soap:functions:mc-style", className = "com.test.ZrfcsdcustomercreditResponse")
    public void zrfcsdcustomercredit(

            @WebParam(name = "Ikunnr", targetNamespace = "")
                    String ikunnr,
            @WebParam(mode = WebParam.Mode.OUT, name = "Ocredit", targetNamespace = "")
                    javax.xml.ws.Holder<Zsdscredit> ocredit,
            @WebParam(mode = WebParam.Mode.OUT, name = "Omessag", targetNamespace = "")
                    javax.xml.ws.Holder<String> omessag,
            @WebParam(mode = WebParam.Mode.OUT, name = "Oreturn", targetNamespace = "")
                    javax.xml.ws.Holder<String> oreturn
    );
}