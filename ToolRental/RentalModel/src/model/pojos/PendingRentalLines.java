package model.pojos;

public class PendingRentalLines {
    public PendingRentalLines() {
        super();
    }

    private String rentalLineId;
    private String itemId;
    private String itemSku;
    private String itemDesciption;
    private String inventoryLocation;
    private String qty;
    private String needByDate;
    private String estShipDate;
    private String orderId;
    private String orderNumber;
    private Boolean isSelected;

    public void setIsSelected(Boolean isSelected) {
        this.isSelected = isSelected;
    }

    public Boolean getIsSelected() {
        return isSelected;
    }


    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setRentalLineId(String rentalLineId) {
        this.rentalLineId = rentalLineId;
    }

    public String getRentalLineId() {
        return rentalLineId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemSku(String itemSku) {
        this.itemSku = itemSku;
    }

    public String getItemSku() {
        return itemSku;
    }

    public void setItemDesciption(String itemDesciption) {
        this.itemDesciption = itemDesciption;
    }

    public String getItemDesciption() {
        return itemDesciption;
    }

    public void setInventoryLocation(String inventoryLocation) {
        this.inventoryLocation = inventoryLocation;
    }

    public String getInventoryLocation() {
        return inventoryLocation;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getQty() {
        return qty;
    }

    public void setNeedByDate(String needByDate) {
        this.needByDate = needByDate;
    }

    public String getNeedByDate() {
        return needByDate;
    }

    public void setEstShipDate(String estShipDate) {
        this.estShipDate = estShipDate;
    }

    public String getEstShipDate() {
        return estShipDate;
    }

    public void setFreight(String freight) {
        this.freight = freight;
    }

    public String getFreight() {
        return freight;
    }
    private String freight;
}
