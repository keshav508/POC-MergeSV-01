package model.pojos;

public class ChangeSku {
    public ChangeSku() {
        super();
    }
    
    private String lineItemId;
    private String replacementItemId;
    private String sku;
    private String itemDesc;
    private String qty;
    private String warehouseId;
    private String orderHeaderId;
    private String orderLineid;

    public void setOrderHeaderId(String orderHeaderId) {
        this.orderHeaderId = orderHeaderId;
    }

    public String getOrderHeaderId() {
        return orderHeaderId;
    }

    public void setOrderLineid(String orderLineid) {
        this.orderLineid = orderLineid;
    }

    public String getOrderLineid() {
        return orderLineid;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setLineItemId(String lineItemId) {
        this.lineItemId = lineItemId;
    }

    public String getLineItemId() {
        return lineItemId;
    }

    public void setReplacementItemId(String replacementItemId) {
        this.replacementItemId = replacementItemId;
    }

    public String getReplacementItemId() {
        return replacementItemId;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getSku() {
        return sku;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getQty() {
        return qty;
    }
}
