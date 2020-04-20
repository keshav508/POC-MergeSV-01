package model.pojos;

import java.io.Serializable;

public class Customer implements Serializable{
    public Customer() {
        super();
    }
    
    private String customerName;
    private String customerAgreement;
    private String contractExpDate;

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerName() {
        return customerName;
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
}
