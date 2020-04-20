package model.pojos;

import java.io.Serializable;

import java.util.List;

public class ReturnRentalDtls implements Serializable{
    public ReturnRentalDtls() {
        super();
    }
    
    private String orderId;
    private String customerName;
    private String customerAgreement;
    private String contractExpDate;
    private String salesRepName;
    private Address billToAddress;
    private Address shipToAddress;
    private List<ReturnRentalLines> returnRentalLineDtls;
    private List<OutstandingItems> outstandingItemsLst;
    private String statusCode;
    private String statusMsg;
    
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerName() {
        return customerName;
    }
    
    public void setSalesRepName(String salesRepName) {
        this.salesRepName = salesRepName;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setCustomerAgreement(String customerAgreement) {
        this.customerAgreement = customerAgreement;
    }

    public String getCustomerAgreement() {
        return customerAgreement;
    }

    public void setContractExpDate(String contractExpDate) {
        this.contractExpDate = contractExpDate;
    }

    public String getContractExpDate() {
        return contractExpDate;
    }

    public void setBillToAddress(Address billToAddress) {
        this.billToAddress = billToAddress;
    }

    public Address getBillToAddress() {
        return billToAddress;
    }

    public void setShipToAddress(Address shipToAddress) {
        this.shipToAddress = shipToAddress;
    }

    public Address getShipToAddress() {
        return shipToAddress;
    }

    public void setReturnRentalLineDtls(List<ReturnRentalLines> returnRentalLineDtls) {
        this.returnRentalLineDtls = returnRentalLineDtls;
    }

    public List<ReturnRentalLines> getReturnRentalLineDtls() {
        return returnRentalLineDtls;
    }

    public void setOutstandingItemsLst(List<OutstandingItems> outstandingItemsLst) {
        this.outstandingItemsLst = outstandingItemsLst;
    }

    public List<OutstandingItems> getOutstandingItemsLst() {
        return outstandingItemsLst;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusMsg(String statusMsg) {
        this.statusMsg = statusMsg;
    }

    public String getStatusMsg() {
        return statusMsg;
    }

    public String getSalesRepName() {
        return salesRepName;
    }

}
