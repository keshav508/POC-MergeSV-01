package model.pojos;

import java.util.List;

public class WarehouseDtls {
    public WarehouseDtls() {
        super();
    }
    
    private List<InventoryLoc> inventoryLocations;
    private String statusCode;
    private String statusMsg;

    public void setInventoryLocations(List<InventoryLoc> inventoryLocations) {
        this.inventoryLocations = inventoryLocations;
    }

    public List<InventoryLoc> getInventoryLocations() {
        return inventoryLocations;
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
