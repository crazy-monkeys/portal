package com.crazy.portal.bean.customer.wsdl.employee;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * 
 *         
 *
 * This class was generated by Apache CXF 3.1.6
 * 2019-09-05T10:57:02.410+08:00
 * Generated source version: 3.1.6
 * 
 */
@WebService(targetNamespace = "http://sap.com/xi/A1S/Global", name = "QueryEmployeeIn")
@XmlSeeAlso({ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface QueryEmployeeIn {

    @WebMethod(operationName = "FindDataByOrganisationalID", action = "http://sap.com/xi/A1S/Global/QueryEmployeeIn/FindDataByOrganisationalIDRequest")
    @WebResult(name = "EmployeeDataByOrganisationIDResponse_sync", targetNamespace = "http://sap.com/xi/SAPGlobal20/Global", partName = "EmployeeDataByOrganisationIDResponse_sync")
    public EmployeeOrgIDDataResponseMessageSync findDataByOrganisationalID(
            @WebParam(partName = "EmployeeDataByOrganisationalIDQuery_sync", name = "EmployeeDataByOrganisationalIDQuery_sync", targetNamespace = "http://sap.com/xi/SAPGlobal20/Global")
                    EmployeeDataByOrganisationalIDQueryMessageSync employeeDataByOrganisationalIDQuerySync
    ) throws StandardFaultMessage_Exception;

    @WebMethod(operationName = "FindBasicDataByIdentification", action = "http://sap.com/xi/A1S/Global/QueryEmployeeIn/FindBasicDataByIdentificationRequest")
    @WebResult(name = "EmployeeBasicDataByIdentificationResponse_sync", targetNamespace = "http://sap.com/xi/SAPGlobal20/Global", partName = "EmployeeBasicDataByIdentificationResponse_sync")
    public EmployeeBasicDataResponseMessageSync findBasicDataByIdentification(
            @WebParam(partName = "EmployeeBasicDataByIdentificationQuery_sync", name = "EmployeeBasicDataByIdentificationQuery_sync", targetNamespace = "http://sap.com/xi/SAPGlobal20/Global")
                    EmployeeBasicDataByIdentificationQueryMessageSync employeeBasicDataByIdentificationQuerySync
    ) throws StandardFaultMessage_Exception;

    @WebMethod(operationName = "FindBiographicalDataByIdentification", action = "http://sap.com/xi/A1S/Global/QueryEmployeeIn/FindBiographicalDataByIdentificationRequest")
    @WebResult(name = "EmployeeBiographicalDataByIdentificationResponse_sync", targetNamespace = "http://sap.com/xi/SAPGlobal20/Global", partName = "EmployeeBiographicalDataByIdentificationResponse_sync")
    public EmployeeBiographicalDataResponseMessageSync findBiographicalDataByIdentification(
            @WebParam(partName = "EmployeeBiographicalDataByIdentificationQuery_sync", name = "EmployeeBiographicalDataByIdentificationQuery_sync", targetNamespace = "http://sap.com/xi/SAPGlobal20/Global")
                    EmployeeBiographicalDataByIdentificationQueryMessageSync employeeBiographicalDataByIdentificationQuerySync
    ) throws StandardFaultMessage_Exception;
}