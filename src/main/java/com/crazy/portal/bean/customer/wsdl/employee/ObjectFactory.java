
package com.crazy.portal.bean.customer.wsdl.employee;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the employee package.
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _EmployeeBasicDataByIdentificationQuerySync_QNAME = new QName("http://sap.com/xi/SAPGlobal20/Global", "EmployeeBasicDataByIdentificationQuery_sync");
    private final static QName _EmployeeBasicDataByIdentificationResponseSync_QNAME = new QName("http://sap.com/xi/SAPGlobal20/Global", "EmployeeBasicDataByIdentificationResponse_sync");
    private final static QName _EmployeeBiographicalDataByIdentificationQuerySync_QNAME = new QName("http://sap.com/xi/SAPGlobal20/Global", "EmployeeBiographicalDataByIdentificationQuery_sync");
    private final static QName _EmployeeBiographicalDataByIdentificationResponseSync_QNAME = new QName("http://sap.com/xi/SAPGlobal20/Global", "EmployeeBiographicalDataByIdentificationResponse_sync");
    private final static QName _EmployeeDataByOrganisationIDResponseSync_QNAME = new QName("http://sap.com/xi/SAPGlobal20/Global", "EmployeeDataByOrganisationIDResponse_sync");
    private final static QName _EmployeeDataByOrganisationalIDQuerySync_QNAME = new QName("http://sap.com/xi/SAPGlobal20/Global", "EmployeeDataByOrganisationalIDQuery_sync");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: employee
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link EmployeeBasicDataByIdentificationQueryMessageSync }
     * 
     */
    public EmployeeBasicDataByIdentificationQueryMessageSync createEmployeeBasicDataByIdentificationQueryMessageSync() {
        return new EmployeeBasicDataByIdentificationQueryMessageSync();
    }

    /**
     * Create an instance of {@link EmployeeBasicDataResponseMessageSync }
     * 
     */
    public EmployeeBasicDataResponseMessageSync createEmployeeBasicDataResponseMessageSync() {
        return new EmployeeBasicDataResponseMessageSync();
    }

    /**
     * Create an instance of {@link EmployeeBiographicalDataByIdentificationQueryMessageSync }
     * 
     */
    public EmployeeBiographicalDataByIdentificationQueryMessageSync createEmployeeBiographicalDataByIdentificationQueryMessageSync() {
        return new EmployeeBiographicalDataByIdentificationQueryMessageSync();
    }

    /**
     * Create an instance of {@link EmployeeBiographicalDataResponseMessageSync }
     * 
     */
    public EmployeeBiographicalDataResponseMessageSync createEmployeeBiographicalDataResponseMessageSync() {
        return new EmployeeBiographicalDataResponseMessageSync();
    }

    /**
     * Create an instance of {@link EmployeeOrgIDDataResponseMessageSync }
     * 
     */
    public EmployeeOrgIDDataResponseMessageSync createEmployeeOrgIDDataResponseMessageSync() {
        return new EmployeeOrgIDDataResponseMessageSync();
    }

    /**
     * Create an instance of {@link EmployeeDataByOrganisationalIDQueryMessageSync }
     * 
     */
    public EmployeeDataByOrganisationalIDQueryMessageSync createEmployeeDataByOrganisationalIDQueryMessageSync() {
        return new EmployeeDataByOrganisationalIDQueryMessageSync();
    }

    /**
     * Create an instance of {@link StandardFaultMessage }
     * 
     */
    public StandardFaultMessage createStandardFaultMessage() {
        return new StandardFaultMessage();
    }

    /**
     * Create an instance of {@link ExchangeFaultData }
     * 
     */
    public ExchangeFaultData createExchangeFaultData() {
        return new ExchangeFaultData();
    }

    /**
     * Create an instance of {@link StandardFaultMessageExtension }
     * 
     */
    public StandardFaultMessageExtension createStandardFaultMessageExtension() {
        return new StandardFaultMessageExtension();
    }

    /**
     * Create an instance of {@link ExchangeLogData }
     * 
     */
    public ExchangeLogData createExchangeLogData() {
        return new ExchangeLogData();
    }

    /**
     * Create an instance of {@link EmployeeBasicDataSelectionByIdentification }
     * 
     */
    public EmployeeBasicDataSelectionByIdentification createEmployeeBasicDataSelectionByIdentification() {
        return new EmployeeBasicDataSelectionByIdentification();
    }

    /**
     * Create an instance of {@link EmployeeBiographicalDataSelectionByIdentification }
     * 
     */
    public EmployeeBiographicalDataSelectionByIdentification createEmployeeBiographicalDataSelectionByIdentification() {
        return new EmployeeBiographicalDataSelectionByIdentification();
    }

    /**
     * Create an instance of {@link EmployeeDataReponseBiograhicalData }
     * 
     */
    public EmployeeDataReponseBiograhicalData createEmployeeDataReponseBiograhicalData() {
        return new EmployeeDataReponseBiograhicalData();
    }

    /**
     * Create an instance of {@link EmployeeDataResponseEmployeeType }
     * 
     */
    public EmployeeDataResponseEmployeeType createEmployeeDataResponseEmployeeType() {
        return new EmployeeDataResponseEmployeeType();
    }

    /**
     * Create an instance of {@link EmployeeDataResponseJobAssignment }
     * 
     */
    public EmployeeDataResponseJobAssignment createEmployeeDataResponseJobAssignment() {
        return new EmployeeDataResponseJobAssignment();
    }

    /**
     * Create an instance of {@link EmployeeDataSelectionByOrganisationalCentreID }
     * 
     */
    public EmployeeDataSelectionByOrganisationalCentreID createEmployeeDataSelectionByOrganisationalCentreID() {
        return new EmployeeDataSelectionByOrganisationalCentreID();
    }

    /**
     * Create an instance of {@link EmployeeOrganisationalAssignment }
     * 
     */
    public EmployeeOrganisationalAssignment createEmployeeOrganisationalAssignment() {
        return new EmployeeOrganisationalAssignment();
    }

    /**
     * Create an instance of {@link EmployeeReponseBasicData }
     * 
     */
    public EmployeeReponseBasicData createEmployeeReponseBasicData() {
        return new EmployeeReponseBasicData();
    }

    /**
     * Create an instance of {@link EmployeeReponseBiograhicalData }
     * 
     */
    public EmployeeReponseBiograhicalData createEmployeeReponseBiograhicalData() {
        return new EmployeeReponseBiograhicalData();
    }

    /**
     * Create an instance of {@link EmployeeReponseEmployeeFormattedAddress }
     * 
     */
    public EmployeeReponseEmployeeFormattedAddress createEmployeeReponseEmployeeFormattedAddress() {
        return new EmployeeReponseEmployeeFormattedAddress();
    }

    /**
     * Create an instance of {@link EmployeeReponseEmployeeOrganisationalAddressPostalAddress }
     * 
     */
    public EmployeeReponseEmployeeOrganisationalAddressPostalAddress createEmployeeReponseEmployeeOrganisationalAddressPostalAddress() {
        return new EmployeeReponseEmployeeOrganisationalAddressPostalAddress();
    }

    /**
     * Create an instance of {@link EmployeeReponseEmployeeWorkplaceAddress }
     * 
     */
    public EmployeeReponseEmployeeWorkplaceAddress createEmployeeReponseEmployeeWorkplaceAddress() {
        return new EmployeeReponseEmployeeWorkplaceAddress();
    }

    /**
     * Create an instance of {@link EmployeeReponseEmployeeWorkplaceAddressInformation }
     * 
     */
    public EmployeeReponseEmployeeWorkplaceAddressInformation createEmployeeReponseEmployeeWorkplaceAddressInformation() {
        return new EmployeeReponseEmployeeWorkplaceAddressInformation();
    }

    /**
     * Create an instance of {@link EmployeeReponseEmployeeWorkplaceAddressWorkplace }
     * 
     */
    public EmployeeReponseEmployeeWorkplaceAddressWorkplace createEmployeeReponseEmployeeWorkplaceAddressWorkplace() {
        return new EmployeeReponseEmployeeWorkplaceAddressWorkplace();
    }

    /**
     * Create an instance of {@link EmployeeReponseOrgIDData }
     * 
     */
    public EmployeeReponseOrgIDData createEmployeeReponseOrgIDData() {
        return new EmployeeReponseOrgIDData();
    }

    /**
     * Create an instance of {@link EmployeeRequestedElements }
     * 
     */
    public EmployeeRequestedElements createEmployeeRequestedElements() {
        return new EmployeeRequestedElements();
    }

    /**
     * Create an instance of {@link EmployeeRequestedElementsEmployee }
     * 
     */
    public EmployeeRequestedElementsEmployee createEmployeeRequestedElementsEmployee() {
        return new EmployeeRequestedElementsEmployee();
    }

    /**
     * Create an instance of {@link EmployeeSelectionByChangedSinceDate }
     * 
     */
    public EmployeeSelectionByChangedSinceDate createEmployeeSelectionByChangedSinceDate() {
        return new EmployeeSelectionByChangedSinceDate();
    }

    /**
     * Create an instance of {@link EmployeeSelectionByCreatedSinceDate }
     * 
     */
    public EmployeeSelectionByCreatedSinceDate createEmployeeSelectionByCreatedSinceDate() {
        return new EmployeeSelectionByCreatedSinceDate();
    }

    /**
     * Create an instance of {@link EmployeeSelectionByCreationIdentityID }
     * 
     */
    public EmployeeSelectionByCreationIdentityID createEmployeeSelectionByCreationIdentityID() {
        return new EmployeeSelectionByCreationIdentityID();
    }

    /**
     * Create an instance of {@link EmployeeSelectionByEmployeeFamilyName }
     * 
     */
    public EmployeeSelectionByEmployeeFamilyName createEmployeeSelectionByEmployeeFamilyName() {
        return new EmployeeSelectionByEmployeeFamilyName();
    }

    /**
     * Create an instance of {@link EmployeeSelectionByEmployeeGivenName }
     * 
     */
    public EmployeeSelectionByEmployeeGivenName createEmployeeSelectionByEmployeeGivenName() {
        return new EmployeeSelectionByEmployeeGivenName();
    }

    /**
     * Create an instance of {@link EmployeeSelectionByEmployeeID }
     * 
     */
    public EmployeeSelectionByEmployeeID createEmployeeSelectionByEmployeeID() {
        return new EmployeeSelectionByEmployeeID();
    }

    /**
     * Create an instance of {@link EmployeeSelectionByLastChangeIdentityID }
     * 
     */
    public EmployeeSelectionByLastChangeIdentityID createEmployeeSelectionByLastChangeIdentityID() {
        return new EmployeeSelectionByLastChangeIdentityID();
    }

    /**
     * Create an instance of {@link EmployeeSelectionByOrganisationalCentreID }
     * 
     */
    public EmployeeSelectionByOrganisationalCentreID createEmployeeSelectionByOrganisationalCentreID() {
        return new EmployeeSelectionByOrganisationalCentreID();
    }

    /**
     * Create an instance of {@link AcademicTitleCode }
     * 
     */
    public AcademicTitleCode createAcademicTitleCode() {
        return new AcademicTitleCode();
    }

    /**
     * Create an instance of {@link CLOSEDDatePeriod }
     * 
     */
    public CLOSEDDatePeriod createCLOSEDDatePeriod() {
        return new CLOSEDDatePeriod();
    }

    /**
     * Create an instance of {@link DistributionChannelCode }
     * 
     */
    public DistributionChannelCode createDistributionChannelCode() {
        return new DistributionChannelCode();
    }

    /**
     * Create an instance of {@link DivisionCode }
     * 
     */
    public DivisionCode createDivisionCode() {
        return new DivisionCode();
    }

    /**
     * Create an instance of {@link ENCRYPTEDMEDIUMEmailURI }
     * 
     */
    public ENCRYPTEDMEDIUMEmailURI createENCRYPTEDMEDIUMEmailURI() {
        return new ENCRYPTEDMEDIUMEmailURI();
    }

    /**
     * Create an instance of {@link EmployeeID }
     * 
     */
    public EmployeeID createEmployeeID() {
        return new EmployeeID();
    }

    /**
     * Create an instance of {@link EncryptedFormattedAddress }
     * 
     */
    public EncryptedFormattedAddress createEncryptedFormattedAddress() {
        return new EncryptedFormattedAddress();
    }

    /**
     * Create an instance of {@link EncryptedFormattedPostalAddress }
     * 
     */
    public EncryptedFormattedPostalAddress createEncryptedFormattedPostalAddress() {
        return new EncryptedFormattedPostalAddress();
    }

    /**
     * Create an instance of {@link FormOfAddressCode }
     * 
     */
    public FormOfAddressCode createFormOfAddressCode() {
        return new FormOfAddressCode();
    }

    /**
     * Create an instance of {@link IdentityID }
     * 
     */
    public IdentityID createIdentityID() {
        return new IdentityID();
    }

    /**
     * Create an instance of {@link Log }
     * 
     */
    public Log createLog() {
        return new Log();
    }

    /**
     * Create an instance of {@link LogItem }
     * 
     */
    public LogItem createLogItem() {
        return new LogItem();
    }

    /**
     * Create an instance of {@link LogItemCategoryCode }
     * 
     */
    public LogItemCategoryCode createLogItemCategoryCode() {
        return new LogItemCategoryCode();
    }

    /**
     * Create an instance of {@link LogItemNotePlaceholderSubstitutionList }
     * 
     */
    public LogItemNotePlaceholderSubstitutionList createLogItemNotePlaceholderSubstitutionList() {
        return new LogItemNotePlaceholderSubstitutionList();
    }

    /**
     * Create an instance of {@link MEDIUMName }
     * 
     */
    public MEDIUMName createMEDIUMName() {
        return new MEDIUMName();
    }

    /**
     * Create an instance of {@link MaritalStatusCode }
     * 
     */
    public MaritalStatusCode createMaritalStatusCode() {
        return new MaritalStatusCode();
    }

    /**
     * Create an instance of {@link ObjectID }
     * 
     */
    public ObjectID createObjectID() {
        return new ObjectID();
    }

    /**
     * Create an instance of {@link PositionID }
     * 
     */
    public PositionID createPositionID() {
        return new PositionID();
    }

    /**
     * Create an instance of {@link QueryProcessingConditions }
     * 
     */
    public QueryProcessingConditions createQueryProcessingConditions() {
        return new QueryProcessingConditions();
    }

    /**
     * Create an instance of {@link RegionCode }
     * 
     */
    public RegionCode createRegionCode() {
        return new RegionCode();
    }

    /**
     * Create an instance of {@link ResponseProcessingConditions }
     * 
     */
    public ResponseProcessingConditions createResponseProcessingConditions() {
        return new ResponseProcessingConditions();
    }

    /**
     * Create an instance of {@link SystemAdministrativeData }
     * 
     */
    public SystemAdministrativeData createSystemAdministrativeData() {
        return new SystemAdministrativeData();
    }

    /**
     * Create an instance of {@link UUID }
     * 
     */
    public UUID createUUID() {
        return new UUID();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EmployeeBasicDataByIdentificationQueryMessageSync }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sap.com/xi/SAPGlobal20/Global", name = "EmployeeBasicDataByIdentificationQuery_sync")
    public JAXBElement<EmployeeBasicDataByIdentificationQueryMessageSync> createEmployeeBasicDataByIdentificationQuerySync(EmployeeBasicDataByIdentificationQueryMessageSync value) {
        return new JAXBElement<EmployeeBasicDataByIdentificationQueryMessageSync>(_EmployeeBasicDataByIdentificationQuerySync_QNAME, EmployeeBasicDataByIdentificationQueryMessageSync.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EmployeeBasicDataResponseMessageSync }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sap.com/xi/SAPGlobal20/Global", name = "EmployeeBasicDataByIdentificationResponse_sync")
    public JAXBElement<EmployeeBasicDataResponseMessageSync> createEmployeeBasicDataByIdentificationResponseSync(EmployeeBasicDataResponseMessageSync value) {
        return new JAXBElement<EmployeeBasicDataResponseMessageSync>(_EmployeeBasicDataByIdentificationResponseSync_QNAME, EmployeeBasicDataResponseMessageSync.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EmployeeBiographicalDataByIdentificationQueryMessageSync }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sap.com/xi/SAPGlobal20/Global", name = "EmployeeBiographicalDataByIdentificationQuery_sync")
    public JAXBElement<EmployeeBiographicalDataByIdentificationQueryMessageSync> createEmployeeBiographicalDataByIdentificationQuerySync(EmployeeBiographicalDataByIdentificationQueryMessageSync value) {
        return new JAXBElement<EmployeeBiographicalDataByIdentificationQueryMessageSync>(_EmployeeBiographicalDataByIdentificationQuerySync_QNAME, EmployeeBiographicalDataByIdentificationQueryMessageSync.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EmployeeBiographicalDataResponseMessageSync }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sap.com/xi/SAPGlobal20/Global", name = "EmployeeBiographicalDataByIdentificationResponse_sync")
    public JAXBElement<EmployeeBiographicalDataResponseMessageSync> createEmployeeBiographicalDataByIdentificationResponseSync(EmployeeBiographicalDataResponseMessageSync value) {
        return new JAXBElement<EmployeeBiographicalDataResponseMessageSync>(_EmployeeBiographicalDataByIdentificationResponseSync_QNAME, EmployeeBiographicalDataResponseMessageSync.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EmployeeOrgIDDataResponseMessageSync }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sap.com/xi/SAPGlobal20/Global", name = "EmployeeDataByOrganisationIDResponse_sync")
    public JAXBElement<EmployeeOrgIDDataResponseMessageSync> createEmployeeDataByOrganisationIDResponseSync(EmployeeOrgIDDataResponseMessageSync value) {
        return new JAXBElement<EmployeeOrgIDDataResponseMessageSync>(_EmployeeDataByOrganisationIDResponseSync_QNAME, EmployeeOrgIDDataResponseMessageSync.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EmployeeDataByOrganisationalIDQueryMessageSync }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sap.com/xi/SAPGlobal20/Global", name = "EmployeeDataByOrganisationalIDQuery_sync")
    public JAXBElement<EmployeeDataByOrganisationalIDQueryMessageSync> createEmployeeDataByOrganisationalIDQuerySync(EmployeeDataByOrganisationalIDQueryMessageSync value) {
        return new JAXBElement<EmployeeDataByOrganisationalIDQueryMessageSync>(_EmployeeDataByOrganisationalIDQuerySync_QNAME, EmployeeDataByOrganisationalIDQueryMessageSync.class, null, value);
    }

}
