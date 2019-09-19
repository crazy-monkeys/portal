package com.crazy.portal.bean.customer.wsdl.customer.ws;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;

/**
 * This class was generated by Apache CXF 3.1.6
 * 2019-09-18T16:12:54.803+08:00
 * Generated source version: 3.1.6
 * 
 */
@WebServiceClient(name = "service", 
                  wsdlLocation = "file:/Users/weiying/Documents/project/zr_portal/wsdl2java/npwsdl/HCI_ManageAccountIn.wsdl",
                  targetNamespace = "http://sap.com/xi/A1S/Global") 
public class Service extends javax.xml.ws.Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://sap.com/xi/A1S/Global", "service");
    public final static QName BindingSOAP12 = new QName("http://sap.com/xi/A1S/Global", "binding_SOAP12");
    static {
        URL url = null;
        try {
            url = new URL("file:/Users/weiying/Documents/project/zr_portal/wsdl2java/npwsdl/HCI_ManageAccountIn.wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(Service.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "file:/Users/weiying/Documents/project/zr_portal/wsdl2java/npwsdl/HCI_ManageAccountIn.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public Service(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public Service() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    public Service(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public Service(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public Service(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }    




    /**
     *
     * @return
     *     returns ManageCustomerIn
     */
    @WebEndpoint(name = "binding_SOAP12")
    public ManageCustomerIn getBindingSOAP12() {
        return super.getPort(BindingSOAP12, ManageCustomerIn.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ManageCustomerIn
     */
    @WebEndpoint(name = "binding_SOAP12")
    public ManageCustomerIn getBindingSOAP12(WebServiceFeature... features) {
        return super.getPort(BindingSOAP12, ManageCustomerIn.class, features);
    }

}