package model.pojos;

import java.util.List;

public class PendingRentalDtls {
    public PendingRentalDtls() {
        super();
    }

    private String orderId;
    private String customerAgreement;
    private String contractExpDate;
    private String salesRepName;
    private Address billToAddress;
    private Address shipToAddress;
    private List<PendingRentalLines> pendingRentalLineDtls;
    private List<OtherItemsDtls> otherItemDtls;
    private String statusCode;
    private String statusMsg;
    private String customerName;

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerName() {
        return customerName;
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

    public void setPendingRentalLineDtls(List<PendingRentalLines> pendingRentalLineDtls) {
        this.pendingRentalLineDtls = pendingRentalLineDtls;
    }

    public List<PendingRentalLines> getPendingRentalLineDtls() {
        return pendingRentalLineDtls;
    }

    public void setOtherItemDtls(List<OtherItemsDtls> otherItemDtls) {
        this.otherItemDtls = otherItemDtls;
    }

    public List<OtherItemsDtls> getOtherItemDtls() {
        return otherItemDtls;
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
}
