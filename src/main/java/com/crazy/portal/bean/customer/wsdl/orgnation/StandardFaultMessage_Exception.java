
package com.crazy.portal.bean.customer.wsdl.orgnation;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 3.1.6
 * 2019-09-09T13:59:04.852+08:00
 * Generated source version: 3.1.6
 */

@WebFault(name = "StandardFaultMessage", targetNamespace = "http://sap.com/xi/AP/Common/Global")
public class StandardFaultMessage_Exception extends Exception {
    
    private StandardFaultMessage standardFaultMessage;

    public StandardFaultMessage_Exception() {
        super();
    }
    
    public StandardFaultMessage_Exception(String message) {
        super(message);
    }
    
    public StandardFaultMessage_Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public StandardFaultMessage_Exception(String message, StandardFaultMessage standardFaultMessage) {
        super(message);
        this.standardFaultMessage = standardFaultMessage;
    }

    public StandardFaultMessage_Exception(String message, StandardFaultMessage standardFaultMessage, Throwable cause) {
        super(message, cause);
        this.standardFaultMessage = standardFaultMessage;
    }

    public StandardFaultMessage getFaultInfo() {
        return this.standardFaultMessage;
    }
}
