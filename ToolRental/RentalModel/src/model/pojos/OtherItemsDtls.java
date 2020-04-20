package model.pojos;

public class OtherItemsDtls {
    public OtherItemsDtls() {
        super();
    }
    
    private String itemSku;
    private String needBYDate;
    private String qty;
    private String lineID;
    private String itemID;
    private String headerID;

    public void setLineID(String lineID) {
        this.lineID = lineID;
    }

    public String getLineID() {
        return lineID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getItemID() {
        return itemID;
    }

    public void setHeaderID(String headerID) {
        this.headerID = headerID;
    }

    public String getHeaderID() {
        return headerID;
    }


    public void setItemSku(String itemSku) {
        this.itemSku = itemSku;
    }

    public String getItemSku() {
        return itemSku;
    }

    public void setNeedBYDate(String needBYDate) {
        this.needBYDate = needBYDate;
    }

    public String getNeedBYDate() {
        return needBYDate;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getQty() {
        return qty;
    }
}
