package model.pojos;

import java.io.Serializable;

import java.util.List;

public class ActiveRentalDtls implements Serializable {
    public ActiveRentalDtls() {
        super();
    }
    
    private String orderId;
    private String customerAgreement;
    private String contractExpDate;
    private String salesRepName;
    private Address billToAddress;
    private Address shipToAddress;
    private List<ActiveRentalLines> activeRentalLineDtls;
    private String statusCode;
    private String statusMsg;
    private String customerName;

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerName() {
        return customerName;
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

    public void setSalesRepName(String salesRepName) {
        this.salesRepName = salesRepName;
    }

    public String getSalesRepName() {
        return salesRepName;
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

    public void setActiveRentalLineDtls(List<ActiveRentalLines> activeRentalLineDtls) {
        this.activeRentalLineDtls = activeRentalLineDtls;
    }

    public List<ActiveRentalLines> getActiveRentalLineDtls() {
        return activeRentalLineDtls;
    }
}
