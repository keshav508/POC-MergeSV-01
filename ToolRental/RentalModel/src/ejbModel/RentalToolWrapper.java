package ejbModel;

import java.util.List;

import model.pojos.ActiveRentalDtls;
import model.pojos.ActiveRentalLines;
import model.pojos.ChangeSku;
import model.pojos.ChangeSkuDtls;
import model.pojos.PendingRentalDtls;
import model.pojos.PendingRentalLines;
import model.pojos.ReturnRentalDtls;
import model.pojos.ReturnRentalLines;
import model.pojos.WarehouseDtls;

public class RentalToolWrapper {
    public RentalToolWrapper() {
        super();
    }
    
    private RentalToolDAO dao;
    
    private RentalToolDAO getDao() {
           if (dao == null) {
               dao = new RentalToolDAO();
           }
           return dao;
       }
    
    public ActiveRentalDtls getActiveRentalDtls(String orderNumber){
        try{
            return getDao().getActiveRentalDtls(orderNumber);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    public PendingRentalDtls getPendingRentalDtls(String orderNumber){
            try{
                return getDao().getPendingRentalDtls(orderNumber);
            }catch(Exception e){
                e.printStackTrace();
            }
            return null;
        }
    
    public WarehouseDtls getInventoryLocations() {
        
        try{
            return getDao().getInventoryLocations();
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return null;
    }
    
    public String replaceSelectedRentalLines(List<ActiveRentalLines> selectedRentalLinesList){
        try{
            return getDao().replaceSelectedRentalLines(selectedRentalLinesList);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    public ChangeSkuDtls getChangeSkuDtls(int inventoryID, String lineItemId) {
        
        try{
            return getDao().getChangeSkuDtls(inventoryID,lineItemId);
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return null;
    }
    
    public String pickSelectedRentalLines(List<PendingRentalLines> selectedRentalLinesList){
        try{
            return getDao().pickSelectedRentalLines(selectedRentalLinesList);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    public String updateSelectedSKU(List<ChangeSku> selectedChangeSkuList){
        try{
            return getDao().updateSelectedSKU(selectedChangeSkuList);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    public ReturnRentalDtls getReturnRentalDtls(String orderNumber){
        try{
            return getDao().getReturnRentalDtls(orderNumber);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public String applyUpdReturnedRentals(ReturnRentalLines applyUpdForReturnRec){
        try{
            return getDao().applyUpdReturnedRentals(applyUpdForReturnRec);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public String billReturnedRentals(String rentalLineId){
        try{
            return getDao().billReturnedRentals(rentalLineId);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
