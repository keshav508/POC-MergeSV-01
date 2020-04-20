package ManagedBeans;

import ejbModel.RentalToolWrapper;

import java.io.Serializable;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;

import java.util.Date;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import model.pojos.ActiveRentalDtls;
import model.pojos.ActiveRentalLines;

import model.pojos.ChangeSku;
import model.pojos.ChangeSkuDtls;
import model.pojos.InventoryLoc;
import model.pojos.PendingRentalDtls;
import model.pojos.PendingRentalLines;

import model.pojos.ReturnRentalDtls;
import model.pojos.ReturnRentalLines;
import model.pojos.WarehouseDtls;

import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;

import oracle.adf.view.rich.component.rich.layout.RichGridRow;
import oracle.adf.view.rich.context.AdfFacesContext;

import org.apache.myfaces.trinidad.model.RowKeySet;

@SuppressWarnings("oracle.jdeveloper.java.public-type-name-conflict")
public class ViewRentalsBean implements Serializable {
    private RichTable changeSkuTableBind;
    private List<ChangeSku> selectedChangeSkuLst = new ArrayList<ChangeSku>();
    private RichTable pendingRenatlLinesTableBind;
    private List<PendingRentalLines> selectedPendingRentalLinesLst = new ArrayList<PendingRentalLines>();
    private PendingRentalLines selectedChangeSkuRentalLinesLst = new PendingRentalLines();
    private RichPopup popUpBind;  

    private RichPopup renturnRentalsPopupBnd;
    private RichTable returnRentalsTblBnd;
    private RichGridRow popupRowsBnd;
    private String seletedLineItemId="";
    private String startDate = "";

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public ViewRentalsBean() {
        super();
        this.selectedOrder = "";
        this.seletedLineItemId = "";
        this.activeRentalDtlsLst = new ActiveRentalDtls();
    }
    public ActiveRentalDtls activeRentalDtlsLst = new ActiveRentalDtls();
    public PendingRentalDtls pendingRentalDtlsLst = new PendingRentalDtls();
    public WarehouseDtls InventoryLocationsList = new WarehouseDtls();
    public String selectedOrder = "";
    private RichTable activeRentalLinesTblBnd;
    private List<ActiveRentalLines> selectedRentalLinesLst = new ArrayList<ActiveRentalLines>();
    public ReturnRentalDtls returnRentalDtls = new ReturnRentalDtls();

    public String selectedSKU = "";

    public void setUpdateReturnRentalRec(ReturnRentalLines updateReturnRentalRec) {
        this.updateReturnRentalRec = updateReturnRentalRec;
    }

    public ReturnRentalLines getUpdateReturnRentalRec() {
        return updateReturnRentalRec;
    }

    public void setSelectedReturnRentalRec(ReturnRentalLines selectedReturnRentalRec) {
        this.selectedReturnRentalRec = selectedReturnRentalRec;
    }

    public ReturnRentalLines getSelectedReturnRentalRec() {
        return selectedReturnRentalRec;
    }

    private ReturnRentalLines updateReturnRentalRec = new ReturnRentalLines();
    private ReturnRentalLines selectedReturnRentalRec = new ReturnRentalLines();

    public void setSelectedSKU(String selectedSKU) {
        this.selectedSKU = selectedSKU;
    }

    public String getSelectedSKU() {
        return selectedSKU;
    }

    public void setReturnRentalDtls(ReturnRentalDtls returnRentalDtlsLst) {
        this.returnRentalDtls = returnRentalDtls;
    }

    public ReturnRentalDtls getReturnRentalDtls() {
        return returnRentalDtls;
    }
    RentalToolWrapper rentalToolWrapper = new RentalToolWrapper();
    public ChangeSkuDtls changeSkuDtlsList = new ChangeSkuDtls();

    public void setActiveRentalLinesTblBnd(RichTable activeRentalLinesTblBnd) {
        this.activeRentalLinesTblBnd = activeRentalLinesTblBnd;
    }

    public RichTable getActiveRentalLinesTblBnd() {
        return activeRentalLinesTblBnd;
    }

    public void setSelectedRentalLinesLst(List<ActiveRentalLines> selectedRentalLinesLst) {
        this.selectedRentalLinesLst = selectedRentalLinesLst;
    }

    public List<ActiveRentalLines> getSelectedRentalLinesLst() {
        return selectedRentalLinesLst;
    }

    public void setSelectedOrder(String selectedOrder) {
        this.selectedOrder = selectedOrder;
    }

    public String getSelectedOrder() {
        return selectedOrder;
    }
    public List<SelectItem> invLocDropdownList = null;

    public void setInvLocDropdownList(List<SelectItem> invLocDropdownList) {
        this.invLocDropdownList = invLocDropdownList;
    }

    public List<SelectItem> getInvLocDropdownList() {
        if (InventoryLocationsList.getInventoryLocations().size() > 0 && invLocDropdownList == null) {
            invLocDropdownList = new ArrayList<SelectItem>();
            List<InventoryLoc> invLoc = InventoryLocationsList.getInventoryLocations();

            System.out.println("invLoc List size in getInvLocDropdownList: " + invLoc.size());

            for (int i = 0; i < invLoc.size(); i++) {
                invLocDropdownList.add(new SelectItem(invLoc.get(i).getId(), invLoc.get(i).getName()));
            }

        }
        return invLocDropdownList;
    }

    public void setInventoryLocationsList(WarehouseDtls InventoryLocationsList) {
        this.InventoryLocationsList = InventoryLocationsList;
    }

    public WarehouseDtls getInventoryLocationsList() {
        return InventoryLocationsList;
    }


    public void setPendingRentalDtlsLst(PendingRentalDtls pendingRentalDtlsLst) {
        this.pendingRentalDtlsLst = pendingRentalDtlsLst;
    }

    public PendingRentalDtls getPendingRentalDtlsLst() {
        return pendingRentalDtlsLst;
    }

    public String moveToPickOrderPage() {
        // Add event code here...

        try {
//            if (this.getPendingRentalsViewTableBnd() != null) {
//                RowKeySet selection = this.getPendingRentalsViewTableBnd().getSelectedRowKeys();
//
//                if (selection != null && selection.getSize() > 0) {
//                    for (Object key : selection) {
//                        this.getActiveRentalsViewTableBnd().setRowKey(key);
//                        System.out.println(this.getPendingRentalsViewTableBnd().getRowData().toString());
//                        String[] rowData = this.getPendingRentalsViewTableBnd().getRowData().toString().split(" ");
//                        this.selectedOrder = rowData[0];
//                    }
//                }
//            }
            pendingRentalDtlsLst = rentalToolWrapper.getPendingRentalDtls(this.selectedOrder);
            InventoryLocationsList = rentalToolWrapper.getInventoryLocations();
            System.out.println("order nuimber:"+this.selectedOrder);
            System.out.println("pendingRentalDtlsLst:" + pendingRentalDtlsLst);
            //            FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "ActiveRentals.jsf");

        } catch (Exception e) {
            e.printStackTrace();
        }

        //FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "Pages/PickOrder.jsf");
        return "toPickOrder";
    }

    public String moveToCompleteRentalspage() {
        //        FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "Pages/CompleteRental.jsf");
        return "toCompleteRentals";
    }

    public void setChangeSkuTableBind(RichTable changeSkuTableBind) {
        this.changeSkuTableBind = changeSkuTableBind;
    }

    public RichTable getChangeSkuTableBind() {
        return changeSkuTableBind;
    }

    public void setSelectedChangeSkuLst(List<ChangeSku> selectedChangeSkuLst) {
        this.selectedChangeSkuLst = selectedChangeSkuLst;
    }

    public List<ChangeSku> getSelectedChangeSkuLst() {
        return selectedChangeSkuLst;
    }

    public void setSelectedPendingRentalLinesLst(List<PendingRentalLines> selectedPendingRentalLinesLst) {
        this.selectedPendingRentalLinesLst = selectedPendingRentalLinesLst;
    }

    public List<PendingRentalLines> getSelectedPendingRentalLinesLst() {
        return selectedPendingRentalLinesLst;
    }

    public void setSelectedChangeSkuRentalLinesLst(PendingRentalLines selectedChangeSkuRentalLinesLst) {
        this.selectedChangeSkuRentalLinesLst = selectedChangeSkuRentalLinesLst;
    }

    public PendingRentalLines getSelectedChangeSkuRentalLinesLst() {
        return selectedChangeSkuRentalLinesLst;
    }

    public void setChangeSkuDtlsList(ChangeSkuDtls changeSkuDtlsList) {
        this.changeSkuDtlsList = changeSkuDtlsList;
    }

    public ChangeSkuDtls getChangeSkuDtlsList() {
        return changeSkuDtlsList;
    }


    public void setActiveRentalDtlsLst(ActiveRentalDtls activeRentalDtlsLst) {
        this.activeRentalDtlsLst = activeRentalDtlsLst;
    }

    public ActiveRentalDtls getActiveRentalDtlsLst() {
        return activeRentalDtlsLst;
    }

    public void setRentalToolWrapper(RentalToolWrapper rentalToolWrapper) {
        this.rentalToolWrapper = rentalToolWrapper;
    }

    public RentalToolWrapper getRentalToolWrapper() {
        return rentalToolWrapper;
    }

    public String getActiveRentalsAction() {
        // Add event code here...
        try {
            activeRentalDtlsLst = rentalToolWrapper.getActiveRentalDtls(this.selectedOrder);
            System.out.println("activeRentalDtlsLst:" + activeRentalDtlsLst);
            System.out.println("activeRentalDtlsLst:" + activeRentalDtlsLst.getCustomerName());
            //            FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "ActiveRentals.jsf");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ToActiveRentals";
    }

    public void inventoryLocValueChangelis(ValueChangeEvent valueChangeEvent) {
        // Add event code here...

        String selectedInvLoc = valueChangeEvent.getNewValue().toString();

        int inventoryID = Integer.parseInt(selectedInvLoc);

        System.out.println("selectedInvLoc is:" + selectedInvLoc);

        changeSkuDtlsList = rentalToolWrapper.getChangeSkuDtls(inventoryID,seletedLineItemId);

        AdfFacesContext.getCurrentInstance().addPartialTarget(changeSkuTableBind);

    }

    public String replaceSelectedRentalLinesAction() {
        // Add event code here...
        try {

            assignSelectedRentalLinesLst();

            System.out.println("entered replaceSelectedRentalLinesAction method...");
            if (selectedRentalLinesLst.size() > 0) {
                String status = rentalToolWrapper.replaceSelectedRentalLines(this.selectedRentalLinesLst);
                System.out.println(status);
                return "goBack";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void assignSelectedRentalLinesLst() {
        List<ActiveRentalLines> allActiveRentalLines = new ArrayList<ActiveRentalLines>();
        allActiveRentalLines = this.activeRentalDtlsLst.getActiveRentalLineDtls();
        this.selectedRentalLinesLst.clear();
        for (ActiveRentalLines row : allActiveRentalLines) {
            if (row.getIsSelected()) {
                this.selectedRentalLinesLst.add(row);
            }
            System.out.println("selectedRentalLinesLst size:" + selectedRentalLinesLst.size());
        }
        System.out.println(allActiveRentalLines.size());
    }

    public void assignSelectedPendingRentalLinesLst() {
        List<PendingRentalLines> allPendingRentalLines = new ArrayList<PendingRentalLines>();
        allPendingRentalLines = this.pendingRentalDtlsLst.getPendingRentalLineDtls();
        this.selectedPendingRentalLinesLst.clear();
        for (PendingRentalLines row : allPendingRentalLines) {
            if (row.getIsSelected()) {
                this.selectedPendingRentalLinesLst.add(row);
                System.out.println("Selected SKU:"+row.getItemSku());
            }
            System.out.println("selectedPendingRentalLinesLst size:" + selectedPendingRentalLinesLst.size());
        }
        System.out.println(allPendingRentalLines.size());
    }

    public String changeSkuSelectButtonAction() {
        // Add event code here...
        System.out.println("Entered changeSkuSelectButtonAction..");
        if (this.getChangeSkuTableBind() != null) {
            RowKeySet selection = this.getChangeSkuTableBind().getSelectedRowKeys();

            ChangeSku currentRow = null;
            if (selection != null && selection.getSize() > 0) {
                for (Object key : selection) {
                    this.getChangeSkuTableBind().setRowKey(key);
                    System.out.println(this.getChangeSkuTableBind().getRowData().toString());
                    currentRow = (ChangeSku) this.getChangeSkuTableBind().getRowData();
                    currentRow.setOrderLineid(selectedChangeSkuRentalLinesLst.getRentalLineId());
                    currentRow.setOrderHeaderId(selectedChangeSkuRentalLinesLst.getOrderId());
                    selectedChangeSkuLst.add(currentRow);
                }
            }
        }

        String status = rentalToolWrapper.updateSelectedSKU(this.selectedChangeSkuLst);
        System.out.println(status);
        if (status.equalsIgnoreCase("Success")) {
            pendingRentalDtlsLst = rentalToolWrapper.getPendingRentalDtls(this.selectedOrder);
            AdfFacesContext.getCurrentInstance().addPartialTarget(pendingRenatlLinesTableBind);
        }
        this.popUpBind.hide();

        return null;
    }

    public String changeSkuButtonAction() {
        // Add event code here...
        System.out.println("Entered changeSkuButtonAction..");
        // Add event code here...
        if (this.getPendingRenatlLinesTableBind() != null) {
            RowKeySet selection = this.getPendingRenatlLinesTableBind().getSelectedRowKeys();

            PendingRentalLines currentRow = null;
            if (selection != null && selection.getSize() > 0) {
                for (Object key : selection) {
                    this.getPendingRenatlLinesTableBind().setRowKey(key);
                    System.out.println(this.getPendingRenatlLinesTableBind().getRowData().toString());
                    currentRow = (PendingRentalLines) this.getPendingRenatlLinesTableBind().getRowData();
                    selectedSKU = currentRow.getItemSku();

                    System.out.println("selectedSKU:" + selectedSKU);

                    selectedChangeSkuRentalLinesLst.setOrderId(currentRow.getOrderId());
                    selectedChangeSkuRentalLinesLst.setRentalLineId(currentRow.getRentalLineId());
                    this.seletedLineItemId = currentRow.getItemId();
                    System.out.println("Seleccted Line Item ID is:"+seletedLineItemId);
                }
            }
        }

        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        this.getPopUpBind().show(hints);

        return null;
    }


    public void setPendingRenatlLinesTableBind(RichTable pendingRenatlLinesTableBind) {
        this.pendingRenatlLinesTableBind = pendingRenatlLinesTableBind;
    }

    public RichTable getPendingRenatlLinesTableBind() {
        return pendingRenatlLinesTableBind;
    }

    public String pickSelectedAction() {
        // Add event code here...

        try {

            assignSelectedPendingRentalLinesLst();

            System.out.println("entered pickSelectedAction method...");
            if (selectedPendingRentalLinesLst.size() > 0) {
                String status = rentalToolWrapper.pickSelectedRentalLines(this.selectedPendingRentalLinesLst);
                System.out.println(status);
                return "goToViewRentals";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void selectRentalPickSelectedVcl(ValueChangeEvent valueChangeEvent) {
        System.out.println("Entered selectRentalPickSelectedVcl..");
        // Add event code here...
        System.out.println(valueChangeEvent.getNewValue());
        if (this.getPendingRenatlLinesTableBind() != null) {
            RowKeySet selection = this.getPendingRenatlLinesTableBind().getSelectedRowKeys();

            PendingRentalLines currentRow = null;
            if (selection != null && selection.getSize() > 0) {
                for (Object key : selection) {
                    this.getPendingRenatlLinesTableBind().setRowKey(key);
                    System.out.println(this.getPendingRenatlLinesTableBind().getRowData().toString());
                    currentRow = (PendingRentalLines) this.getPendingRenatlLinesTableBind().getRowData();

                    if (valueChangeEvent.getNewValue().equals(true)) {
                        System.out.println("true");

                        selectedPendingRentalLinesLst.add(currentRow);

                    } else {
                        System.out.println("false");
                        selectedPendingRentalLinesLst.remove(currentRow);
                    }
                }
            }
        }
    }

    public void setPopUpBind(RichPopup popUpBind) {
        this.popUpBind = popUpBind;
    }

    public RichPopup getPopUpBind() {
        return popUpBind;
    }

    public String toCompleteRentalsAction() {
        // Add event code here...
        try {
//            if (this.getCompleteRentalsListTblBnd() != null) {
//                RowKeySet selection = this.getCompleteRentalsListTblBnd().getSelectedRowKeys();
//
//                if (selection != null && selection.getSize() > 0) {
//                    for (Object key : selection) {
//                        this.getCompleteRentalsListTblBnd().setRowKey(key);
//                        System.out.println(this.getCompleteRentalsListTblBnd().getRowData().toString());
//                        String[] rowData = this.getCompleteRentalsListTblBnd().getRowData().toString().split(" ");
//                        this.selectedOrder = rowData[0];
//                    }
//                }
//            }
            returnRentalDtls = rentalToolWrapper.getReturnRentalDtls(this.selectedOrder);
            System.out.println("returnRentalDtls:" + returnRentalDtls);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "toRental";
    }

    public void OrderNumberActionListener(ActionEvent actionEvent) {
        // Add event code here...
        System.out.println("Link ID:" + actionEvent.getComponent().getId());

        System.out.println("Link text" + actionEvent.getComponent().getAttributes().get("text"));

        this.selectedOrder = actionEvent.getComponent().getAttributes().get("text").toString();
    }
    
//    public void updateReturnRentalVcl(ValueChangeEvent valueChangeEvent) {
//        // Add event code here...
//        try{
//            System.out.println("updateReturnRentalVcl called..");
//            
//            System.out.println("start date in vcl:"+this.startDate);
//            
//            String dateStart = this.updateReturnRentalRec.getStartDate();
//            
//            System.out.println("dateStart is:"+dateStart);
//            
//            calculateReturnValues();
//            AdfFacesContext.getCurrentInstance().addPartialTarget(this.popupRowsBnd);
//            
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//        
//    }
    
    public void calculateReturnValues(){
        try{
            System.out.println("Inside calculateReturnValues");
            String dateStart = this.updateReturnRentalRec.getStartDate();
                String dateStop = this.updateReturnRentalRec.getReturnDate();
            
            System.out.println("dateStart is:"+dateStart);
            System.out.println("dateStop is:"+dateStop);
                if(dateStart != null && dateStop != null){
                    SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

                    Date d1 = null;
                    Date d2 = null;
                    try {
                        d1 = format.parse(dateStart);
                        d2 = format.parse(dateStop);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    // Get msec from each, and subtract.
                    long diff = d2.getTime() - d1.getTime();
                    long diffSeconds = diff / 1000 % 60;
                    long diffMinutes = diff / (60 * 1000) % 60;
                    long diffHours = diff / (60 * 60 * 1000);
                    long diffDays = diff / (60 * 60 * 1000 * 24);
                    System.out.println("Time in seconds: " + diffSeconds + " seconds.");
                    System.out.println("Time in minutes: " + diffMinutes + " minutes.");
                    System.out.println("Time in hours: " + diffHours + " hours.");
                    this.updateReturnRentalRec.setNoOfDays(String.valueOf(diffDays));
                    
                    long rental = 0;
                    String unbilledFr = (String) AdfFacesContext.getCurrentInstance().getViewScope().get("unbilledFreight");
                    long unbilledFreight = 0;
                    if(unbilledFr != null)
                       unbilledFreight = Long.parseLong(unbilledFr);
                    long damage = 0;
                    if(this.updateReturnRentalRec.getDamage()!=null)
                        damage = Long.parseLong(this.updateReturnRentalRec.getDamage());
                   if(this.updateReturnRentalRec.getDailyRate() != null){
                        long dailyRt = Long.parseLong(updateReturnRentalRec.getDailyRate());
                        rental = dailyRt * diffDays;
                        this.updateReturnRentalRec.setRental(String.valueOf(rental));
                    }else{
                        this.updateReturnRentalRec.setRental("0");
                    }
                    
                    this.updateReturnRentalRec.setUnbilledFreight((String) AdfFacesContext.getCurrentInstance().getViewScope().get("unbilledFreight"));
                    
                    System.out.println("Rental:"+String.valueOf(rental));
                    System.out.println("Damage:"+String.valueOf(damage));
                    System.out.println("unbilledFreight:"+String.valueOf(unbilledFreight));
                    
                    this.updateReturnRentalRec.setTotal(String.valueOf(rental + unbilledFreight + damage));
                }
        }catch(Exception e){
            e.printStackTrace();
        }
       
        
        
            
    }
    
    public String billReturnRentalAction() {
        // Add event code here...
        try{
            System.out.println("entered billReturnRentalAction method..");
            String rentalLineId =
                            (String) AdfFacesContext.getCurrentInstance().getPageFlowScope().get("rentalLineId");
            String status = rentalToolWrapper.billReturnedRentals(rentalLineId);
            System.out.println("status:"+status);
            this.activeRentalDtlsLst = null;
            returnRentalDtls = rentalToolWrapper.getReturnRentalDtls(this.selectedOrder);
            AdfFacesContext.getCurrentInstance().addPartialTarget(returnRentalsTblBnd);
            System.out.println("end billReturnRentalAction method");
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

        public String applyBillReturnRentalsUpdAction() {
            // Add event code here...
            try{
                System.out.println("entered applyReturnRentalsUpdAction method..");
                String rentalLineId =
                                (String) AdfFacesContext.getCurrentInstance().getPageFlowScope().get("rentalLineId");
                
                this.updateReturnRentalRec.setRentalLineId(rentalLineId);
                
                this.updateReturnRentalRec.setStatus("Billed");
                String status = rentalToolWrapper.applyUpdReturnedRentals(this.updateReturnRentalRec);
                System.out.println("status:"+status);
                this.activeRentalDtlsLst = null;
                returnRentalDtls = rentalToolWrapper.getReturnRentalDtls(this.selectedOrder);

                this.updateReturnRentalRec = new ReturnRentalLines();
                AdfFacesContext.getCurrentInstance().addPartialTarget(renturnRentalsPopupBnd);
                AdfFacesContext.getCurrentInstance().addPartialTarget(returnRentalsTblBnd);
                System.out.println("end applyBillReturnRentalsUpdAction method");
                this.renturnRentalsPopupBnd.hide();
                
                
            }catch(Exception e){
                e.printStackTrace();
            }
            return null;
        }

        public String applyReturnRentalsUpdAction(){
            try{
                System.out.println("entered applyReturnRentalsUpdAction method..");
                String rentalLineId =
                                (String) AdfFacesContext.getCurrentInstance().getViewScope().get("rentalLineId");
                
                this.updateReturnRentalRec.setRentalLineId(rentalLineId);
                String returnStatus = 
                                (String) AdfFacesContext.getCurrentInstance().getViewScope().get("status");
                
                this.updateReturnRentalRec.setStatus(returnStatus);
                String status = rentalToolWrapper.applyUpdReturnedRentals(this.updateReturnRentalRec);
                System.out.println("status:"+status);
                this.activeRentalDtlsLst = null;
                returnRentalDtls = rentalToolWrapper.getReturnRentalDtls(this.selectedOrder);

                AdfFacesContext.getCurrentInstance().addPartialTarget(returnRentalsTblBnd);
                System.out.println("end applyBillReturnRentalsUpdAction method");
                this.updateReturnRentalRec = new ReturnRentalLines();
                AdfFacesContext.getCurrentInstance().addPartialTarget(renturnRentalsPopupBnd);
                
                this.renturnRentalsPopupBnd.hide();
                
                
            }catch(Exception e){
                e.printStackTrace();
            }
            return null;
        }

        public void setRenturnRentalsPopupBnd(RichPopup renturnRentalsPopupBnd) {
            this.renturnRentalsPopupBnd = renturnRentalsPopupBnd;
        }

        public RichPopup getRenturnRentalsPopupBnd() {
            return renturnRentalsPopupBnd;
        }

        public void setReturnRentalsTblBnd(RichTable returnRentalsTblBnd) {
            this.returnRentalsTblBnd = returnRentalsTblBnd;
        }

        public RichTable getReturnRentalsTblBnd() {
            return returnRentalsTblBnd;
        }

        public void setPopupRowsBnd(RichGridRow popupRowsBnd) {
            this.popupRowsBnd = popupRowsBnd;
        }

        public RichGridRow getPopupRowsBnd() {
            return popupRowsBnd;
        }

    public void updateStartDatelVcl(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        this.updateReturnRentalRec.setStartDate(valueChangeEvent.getNewValue().toString());
        
        calculateReturnValues();
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.popupRowsBnd);
    }

    public void updateReturnDateVcl(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        this.updateReturnRentalRec.setReturnDate(valueChangeEvent.getNewValue().toString());
        
        calculateReturnValues();
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.popupRowsBnd);
    }

    public void updateDailyRateVcl(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        this.updateReturnRentalRec.setDailyRate(valueChangeEvent.getNewValue().toString());
        
        calculateReturnValues();
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.popupRowsBnd);
    }

    public void updateDamageVcl(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        
        this.updateReturnRentalRec.setDamage(valueChangeEvent.getNewValue().toString());
        
        calculateReturnValues();
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.popupRowsBnd);
    }
}
