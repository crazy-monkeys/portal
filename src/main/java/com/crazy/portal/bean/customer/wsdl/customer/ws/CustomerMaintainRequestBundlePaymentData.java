
package com.crazy.portal.bean.customer.wsdl.customer.ws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>CustomerMaintainRequestBundlePaymentData complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="CustomerMaintainRequestBundlePaymentData"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ObjectNodeSenderTechnicalID" type="{http://sap.com/xi/AP/Common/GDT}ObjectNodePartyTechnicalID" minOccurs="0"/&gt;
 *         &lt;element name="CompanyID" type="{http://sap.com/xi/AP/Common/GDT}OrganisationalCentreID"/&gt;
 *         &lt;element name="UUID" type="{http://sap.com/xi/AP/Common/GDT}UUID" minOccurs="0"/&gt;
 *         &lt;element name="AccountDeterminationDebtorGroupCode" type="{http://sap.com/xi/AP/FinancialAccounting/Global}AccountDeterminationDebtorGroupCode" minOccurs="0"/&gt;
 *         &lt;element name="AccountDebtorExternalID" type="{http://sap.com/xi/AP/Common/GDT}PartyPartyID" minOccurs="0"/&gt;
 *         &lt;element name="PaymentBlockingReasonCode" type="{http://sap.com/xi/AP/Common/GDT}PaymentBlockingReasonCode" minOccurs="0"/&gt;
 *         &lt;element name="PaymentBlockExpirationDateTime" type="{http://sap.com/xi/BASIS/Global}GLOBAL_DateTime" minOccurs="0"/&gt;
 *         &lt;element name="PaymentForm" type="{http://sap.com/xi/A1S/Global}CustomerMaintainRequestBundlePaymentDataPaymentForm" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="CreditLimitAmount" type="{http://sap.com/xi/AP/Common/GDT}Amount" minOccurs="0"/&gt;
 *         &lt;element name="DirectDebitBankDetailsID" type="{http://sap.com/xi/AP/Common/GDT}BusinessPartnerBankDetailsID" minOccurs="0"/&gt;
 *         &lt;element name="DirectDebitValidityPeriod" type="{http://sap.com/xi/AP/Common/GDT}CLOSED_DatePeriod" minOccurs="0"/&gt;
 *         &lt;element name="PaymentCardDetailsID" type="{http://sap.com/xi/AP/Common/GDT}BusinessPartnerPaymentCardDetailsID" minOccurs="0"/&gt;
 *         &lt;element name="PaymentCardValidityPeriod" type="{http://sap.com/xi/AP/Common/GDT}CLOSED_DatePeriod" minOccurs="0"/&gt;
 *         &lt;element name="BillOfExchangeBankDetailsID" type="{http://sap.com/xi/AP/Common/GDT}BusinessPartnerBankDetailsID" minOccurs="0"/&gt;
 *         &lt;element name="PaymentAdviceRequiredIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" minOccurs="0"/&gt;
 *         &lt;element name="FirstPaymentInstructionCode" type="{http://sap.com/xi/AP/Common/GDT}PaymentInstructionTypeCode" minOccurs="0"/&gt;
 *         &lt;element name="SecondPaymentInstructionCode" type="{http://sap.com/xi/AP/Common/GDT}PaymentInstructionTypeCode" minOccurs="0"/&gt;
 *         &lt;element name="ThirdPaymentInstructionCode" type="{http://sap.com/xi/AP/Common/GDT}PaymentInstructionTypeCode" minOccurs="0"/&gt;
 *         &lt;element name="FourthPaymentInstructionCode" type="{http://sap.com/xi/AP/Common/GDT}PaymentInstructionTypeCode" minOccurs="0"/&gt;
 *         &lt;element name="BankChargeBearerCode" type="{http://sap.com/xi/AP/Common/GDT}BankChargeBearerCode" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="actionCode" type="{http://sap.com/xi/AP/Common/GDT}ActionCode" /&gt;
 *       &lt;attribute name="paymentFormListCompleteTransmissionIndicator" type="{http://sap.com/xi/AP/Common/GDT}Indicator" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CustomerMaintainRequestBundlePaymentData", namespace = "http://sap.com/xi/A1S/Global", propOrder = {
    "objectNodeSenderTechnicalID",
    "companyID",
    "uuid",
    "accountDeterminationDebtorGroupCode",
    "accountDebtorExternalID",
    "paymentBlockingReasonCode",
    "paymentBlockExpirationDateTime",
    "paymentForm",
    "creditLimitAmount",
    "directDebitBankDetailsID",
    "directDebitValidityPeriod",
    "paymentCardDetailsID",
    "paymentCardValidityPeriod",
    "billOfExchangeBankDetailsID",
    "paymentAdviceRequiredIndicator",
    "firstPaymentInstructionCode",
    "secondPaymentInstructionCode",
    "thirdPaymentInstructionCode",
    "fourthPaymentInstructionCode",
    "bankChargeBearerCode"
})
public class CustomerMaintainRequestBundlePaymentData {

    @XmlElement(name = "ObjectNodeSenderTechnicalID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String objectNodeSenderTechnicalID;
    @XmlElement(name = "CompanyID", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String companyID;
    @XmlElement(name = "UUID")
    protected UUID uuid;
    @XmlElement(name = "AccountDeterminationDebtorGroupCode")
    protected AccountDeterminationDebtorGroupCode accountDeterminationDebtorGroupCode;
    @XmlElement(name = "AccountDebtorExternalID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String accountDebtorExternalID;
    @XmlElement(name = "PaymentBlockingReasonCode")
    protected PaymentBlockingReasonCode paymentBlockingReasonCode;
    @XmlElement(name = "PaymentBlockExpirationDateTime")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar paymentBlockExpirationDateTime;
    @XmlElement(name = "PaymentForm")
    protected List<CustomerMaintainRequestBundlePaymentDataPaymentForm> paymentForm;
    @XmlElement(name = "CreditLimitAmount")
    protected Amount creditLimitAmount;
    @XmlElement(name = "DirectDebitBankDetailsID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String directDebitBankDetailsID;
    @XmlElement(name = "DirectDebitValidityPeriod")
    protected CLOSEDDatePeriod directDebitValidityPeriod;
    @XmlElement(name = "PaymentCardDetailsID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String paymentCardDetailsID;
    @XmlElement(name = "PaymentCardValidityPeriod")
    protected CLOSEDDatePeriod paymentCardValidityPeriod;
    @XmlElement(name = "BillOfExchangeBankDetailsID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String billOfExchangeBankDetailsID;
    @XmlElement(name = "PaymentAdviceRequiredIndicator")
    protected Boolean paymentAdviceRequiredIndicator;
    @XmlElement(name = "FirstPaymentInstructionCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String firstPaymentInstructionCode;
    @XmlElement(name = "SecondPaymentInstructionCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String secondPaymentInstructionCode;
    @XmlElement(name = "ThirdPaymentInstructionCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String thirdPaymentInstructionCode;
    @XmlElement(name = "FourthPaymentInstructionCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String fourthPaymentInstructionCode;
    @XmlElement(name = "BankChargeBearerCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String bankChargeBearerCode;
    @XmlAttribute(name = "actionCode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String actionCode;
    @XmlAttribute(name = "paymentFormListCompleteTransmissionIndicator")
    protected Boolean paymentFormListCompleteTransmissionIndicator;

    /**
     * 获取objectNodeSenderTechnicalID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObjectNodeSenderTechnicalID() {
        return objectNodeSenderTechnicalID;
    }

    /**
     * 设置objectNodeSenderTechnicalID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObjectNodeSenderTechnicalID(String value) {
        this.objectNodeSenderTechnicalID = value;
    }

    /**
     * 获取companyID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCompanyID() {
        return companyID;
    }

    /**
     * 设置companyID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCompanyID(String value) {
        this.companyID = value;
    }

    /**
     * 获取uuid属性的值。
     * 
     * @return
     *     possible object is
     *     {@link UUID }
     *     
     */
    public UUID getUUID() {
        return uuid;
    }

    /**
     * 设置uuid属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link UUID }
     *     
     */
    public void setUUID(UUID value) {
        this.uuid = value;
    }

    /**
     * 获取accountDeterminationDebtorGroupCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link AccountDeterminationDebtorGroupCode }
     *     
     */
    public AccountDeterminationDebtorGroupCode getAccountDeterminationDebtorGroupCode() {
        return accountDeterminationDebtorGroupCode;
    }

    /**
     * 设置accountDeterminationDebtorGroupCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link AccountDeterminationDebtorGroupCode }
     *     
     */
    public void setAccountDeterminationDebtorGroupCode(AccountDeterminationDebtorGroupCode value) {
        this.accountDeterminationDebtorGroupCode = value;
    }

    /**
     * 获取accountDebtorExternalID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountDebtorExternalID() {
        return accountDebtorExternalID;
    }

    /**
     * 设置accountDebtorExternalID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountDebtorExternalID(String value) {
        this.accountDebtorExternalID = value;
    }

    /**
     * 获取paymentBlockingReasonCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PaymentBlockingReasonCode }
     *     
     */
    public PaymentBlockingReasonCode getPaymentBlockingReasonCode() {
        return paymentBlockingReasonCode;
    }

    /**
     * 设置paymentBlockingReasonCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PaymentBlockingReasonCode }
     *     
     */
    public void setPaymentBlockingReasonCode(PaymentBlockingReasonCode value) {
        this.paymentBlockingReasonCode = value;
    }

    /**
     * 获取paymentBlockExpirationDateTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPaymentBlockExpirationDateTime() {
        return paymentBlockExpirationDateTime;
    }

    /**
     * 设置paymentBlockExpirationDateTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPaymentBlockExpirationDateTime(XMLGregorianCalendar value) {
        this.paymentBlockExpirationDateTime = value;
    }

    /**
     * Gets the value of the paymentForm property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the paymentForm property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPaymentForm().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CustomerMaintainRequestBundlePaymentDataPaymentForm }
     * 
     * 
     */
    public List<CustomerMaintainRequestBundlePaymentDataPaymentForm> getPaymentForm() {
        if (paymentForm == null) {
            paymentForm = new ArrayList<CustomerMaintainRequestBundlePaymentDataPaymentForm>();
        }
        return this.paymentForm;
    }

    /**
     * 获取creditLimitAmount属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Amount }
     *     
     */
    public Amount getCreditLimitAmount() {
        return creditLimitAmount;
    }

    /**
     * 设置creditLimitAmount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Amount }
     *     
     */
    public void setCreditLimitAmount(Amount value) {
        this.creditLimitAmount = value;
    }

    /**
     * 获取directDebitBankDetailsID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDirectDebitBankDetailsID() {
        return directDebitBankDetailsID;
    }

    /**
     * 设置directDebitBankDetailsID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDirectDebitBankDetailsID(String value) {
        this.directDebitBankDetailsID = value;
    }

    /**
     * 获取directDebitValidityPeriod属性的值。
     * 
     * @return
     *     possible object is
     *     {@link CLOSEDDatePeriod }
     *     
     */
    public CLOSEDDatePeriod getDirectDebitValidityPeriod() {
        return directDebitValidityPeriod;
    }

    /**
     * 设置directDebitValidityPeriod属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link CLOSEDDatePeriod }
     *     
     */
    public void setDirectDebitValidityPeriod(CLOSEDDatePeriod value) {
        this.directDebitValidityPeriod = value;
    }

    /**
     * 获取paymentCardDetailsID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentCardDetailsID() {
        return paymentCardDetailsID;
    }

    /**
     * 设置paymentCardDetailsID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentCardDetailsID(String value) {
        this.paymentCardDetailsID = value;
    }

    /**
     * 获取paymentCardValidityPeriod属性的值。
     * 
     * @return
     *     possible object is
     *     {@link CLOSEDDatePeriod }
     *     
     */
    public CLOSEDDatePeriod getPaymentCardValidityPeriod() {
        return paymentCardValidityPeriod;
    }

    /**
     * 设置paymentCardValidityPeriod属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link CLOSEDDatePeriod }
     *     
     */
    public void setPaymentCardValidityPeriod(CLOSEDDatePeriod value) {
        this.paymentCardValidityPeriod = value;
    }

    /**
     * 获取billOfExchangeBankDetailsID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillOfExchangeBankDetailsID() {
        return billOfExchangeBankDetailsID;
    }

    /**
     * 设置billOfExchangeBankDetailsID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillOfExchangeBankDetailsID(String value) {
        this.billOfExchangeBankDetailsID = value;
    }

    /**
     * 获取paymentAdviceRequiredIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPaymentAdviceRequiredIndicator() {
        return paymentAdviceRequiredIndicator;
    }

    /**
     * 设置paymentAdviceRequiredIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPaymentAdviceRequiredIndicator(Boolean value) {
        this.paymentAdviceRequiredIndicator = value;
    }

    /**
     * 获取firstPaymentInstructionCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstPaymentInstructionCode() {
        return firstPaymentInstructionCode;
    }

    /**
     * 设置firstPaymentInstructionCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstPaymentInstructionCode(String value) {
        this.firstPaymentInstructionCode = value;
    }

    /**
     * 获取secondPaymentInstructionCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSecondPaymentInstructionCode() {
        return secondPaymentInstructionCode;
    }

    /**
     * 设置secondPaymentInstructionCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSecondPaymentInstructionCode(String value) {
        this.secondPaymentInstructionCode = value;
    }

    /**
     * 获取thirdPaymentInstructionCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getThirdPaymentInstructionCode() {
        return thirdPaymentInstructionCode;
    }

    /**
     * 设置thirdPaymentInstructionCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setThirdPaymentInstructionCode(String value) {
        this.thirdPaymentInstructionCode = value;
    }

    /**
     * 获取fourthPaymentInstructionCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFourthPaymentInstructionCode() {
        return fourthPaymentInstructionCode;
    }

    /**
     * 设置fourthPaymentInstructionCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFourthPaymentInstructionCode(String value) {
        this.fourthPaymentInstructionCode = value;
    }

    /**
     * 获取bankChargeBearerCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBankChargeBearerCode() {
        return bankChargeBearerCode;
    }

    /**
     * 设置bankChargeBearerCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBankChargeBearerCode(String value) {
        this.bankChargeBearerCode = value;
    }

    /**
     * 获取actionCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActionCode() {
        return actionCode;
    }

    /**
     * 设置actionCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActionCode(String value) {
        this.actionCode = value;
    }

    /**
     * 获取paymentFormListCompleteTransmissionIndicator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPaymentFormListCompleteTransmissionIndicator() {
        return paymentFormListCompleteTransmissionIndicator;
    }

    /**
     * 设置paymentFormListCompleteTransmissionIndicator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPaymentFormListCompleteTransmissionIndicator(Boolean value) {
        this.paymentFormListCompleteTransmissionIndicator = value;
    }

}
