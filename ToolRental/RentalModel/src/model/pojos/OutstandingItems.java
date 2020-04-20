package model.pojos;

public class OutstandingItems {
    public OutstandingItems() {
        super();
    }
    private String orderId;
    private String orderNumber;
    private String rentalHeaderId;
    private String rentalLineId;
    private String itemId;
    private String installBaseId;
    private String itemSku;
    private String serialNumber;
    private String shipDate;

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setRentalHeaderId(String rentalHeaderId) {
        this.rentalHeaderId = rentalHeaderId;
    }

    public String getRentalHeaderId() {
        return rentalHeaderId;
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

    public void setInstallBaseId(String installBaseId) {
        this.installBaseId = installBaseId;
    }

    public String getInstallBaseId() {
        return installBaseId;
    }

    public void setItemSku(String itemSku) {
        this.itemSku = itemSku;
    }

    public String getItemSku() {
        return itemSku;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setShipDate(String shipDate) {
        this.shipDate = shipDate;
    }

    public String getShipDate() {
        return shipDate;
    }
}
