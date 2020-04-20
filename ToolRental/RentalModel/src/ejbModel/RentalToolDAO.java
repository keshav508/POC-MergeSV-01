package ejbModel;

import java.io.FileInputStream;
import java.io.InputStream;

import java.sql.CallableStatement;
import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

import java.util.Vector;

import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;

import javax.sql.DataSource;

import model.pojos.ActiveRentalDtls;

import model.pojos.ActiveRentalLines;
import model.pojos.Address;

import model.pojos.ChangeSku;
import model.pojos.ChangeSkuDtls;
import model.pojos.InventoryLoc;

import oracle.jdbc.OracleCallableStatement;
import model.pojos.OtherItemsDtls;
import model.pojos.OutstandingItems;
import model.pojos.PendingRentalDtls;

import model.pojos.PendingRentalLines;

import model.pojos.ReturnRentalDtls;
import model.pojos.ReturnRentalLines;
import model.pojos.WarehouseDtls;

import oracle.jdbc.OracleTypes;
import oracle.jdbc.driver.OracleConnection;

import oracle.security.xmlsec.saml2.metadata.Contact;

import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;

public class RentalToolDAO {
    public RentalToolDAO() {
        super();
        //this.dataSourceName = getDataSourceFromProp();
    }
    private String dataSourceName = "";
    Logger log =
        Logger.getLogger(this.getClass().getSimpleName()); // define log for all the classes that needs to be logged

    public void setDataSourceName(String dataSourceName) {
        this.dataSourceName = dataSourceName;
    }

    public String getDataSourceName() {
        return dataSourceName;
    }

    public String getDataSourceFromProp() {
        java.util.Properties props = new java.util.Properties();
        String dataSourceName = "";

        try {

            InputStream inputStream = new FileInputStream("./EmpirixWebLicensorProperties/WLDatasource.properties");

            props.load(inputStream);
            dataSourceName = props.getProperty("DatasourceName");
            System.out.println("propertyValue.." + dataSourceName);
            props.clear();
            inputStream.close();
        } catch (Exception e) {
            log.error(e.toString());
            System.out.println("Failed to get DatasourceName value from properties file.");
            e.printStackTrace();
        }
        return dataSourceName;
    }

    public Connection getDbConnection() {
        log.info("Entered method getDbConnection of WebLicensorDAO");
        Connection L_con;
        try {

            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            L_con =
                (OracleConnection) DriverManager.getConnection("jdbc:oracle:thin:@129.150.208.53:1521:ORCL", "system",
                                                               "Welcome_123");
            return L_con;


            //              if(dataSourceName!=null){
            //                     Context ctx = new InitialContext();
            //                     DataSource ds = (DataSource)ctx.lookup(dataSourceName);
            //                     System.out.println("ds.."+ds);
            //
            //                    L_con = ds.getConnection();
            //                    System.out.println("con..."+L_con);
            //                    log.info("Successfully retrieved Database Connection details from getDbConnection..");
            //                    return L_con;
            //                 }else{
            //                     log.error("Couldn't find the Datasource.");
            //                     return null;
            //                 }


        } catch (Exception e) {
            log.error("Error getDbConnection method:");
            log.error(e.toString());
            e.printStackTrace();
            return null;
        }
    }


    public ActiveRentalDtls getActiveRentalDtls(String orderNumber) {

        System.out.println("Entered getActiveRentalDtls method...");
        ActiveRentalDtls activeRentalsObj = new ActiveRentalDtls();
        try {

            System.out.println("OrderNumber:" + orderNumber);
            ResultSet shipToAddressRS = null;
            ResultSet billToAddressRS = null;
            ResultSet rentalLinesRS = null;
            Connection conn = getDbConnection();
            CallableStatement cstmt =
                conn.prepareCall("{call SYSTEM.APPS_RENTAL_TOOL_PKG.GET_ACTIVE_RENTAL_ORDER_DTLS(?,?,?,?,?,?,?,?,?,?)}");
            System.out.println("procedure called");
            cstmt.setString(1, orderNumber);
            cstmt.registerOutParameter(2, OracleTypes.VARCHAR);
            cstmt.registerOutParameter(3, OracleTypes.VARCHAR);
            cstmt.registerOutParameter(4, OracleTypes.VARCHAR);
            cstmt.registerOutParameter(5, OracleTypes.VARCHAR);
            cstmt.registerOutParameter(6, OracleTypes.CURSOR);
            cstmt.registerOutParameter(7, OracleTypes.CURSOR);
            cstmt.registerOutParameter(8, OracleTypes.CURSOR);
            cstmt.registerOutParameter(9, OracleTypes.VARCHAR);
            cstmt.registerOutParameter(10, OracleTypes.VARCHAR);

            cstmt.executeQuery();
            activeRentalsObj.setCustomerAgreement(cstmt.getString(2));
            activeRentalsObj.setCustomerName(cstmt.getString(3));
            activeRentalsObj.setContractExpDate(cstmt.getString(4).substring(0,10));
            activeRentalsObj.setSalesRepName(cstmt.getString(5));
            activeRentalsObj.setStatusCode(cstmt.getString(9));
            activeRentalsObj.setStatusMsg(cstmt.getString(10));
            shipToAddressRS = (ResultSet) cstmt.getObject(6);
            if (activeRentalsObj.getStatusCode().equalsIgnoreCase("000")) {
                System.out.println(shipToAddressRS);
                Address shipToAddress = new Address();
                while (shipToAddressRS.next()) {
                    shipToAddress.setAddressLine1(shipToAddressRS.getString("ADDRESS_LINE1"));
                    shipToAddress.setAddressLine2(shipToAddressRS.getString("ADDRESS_LINE2"));
                    shipToAddress.setCity(shipToAddressRS.getString("CITY"));
                    shipToAddress.setState(shipToAddressRS.getString("STATE"));
                    shipToAddress.setZipCode(shipToAddressRS.getString("ZIP"));
                    shipToAddress.setCountry(shipToAddressRS.getString("COUNTRY"));
                    shipToAddress.setUsageType(shipToAddressRS.getString("USAGE_TYPE"));
                }
                activeRentalsObj.setShipToAddress(shipToAddress);

                billToAddressRS = (ResultSet) cstmt.getObject(7);
                System.out.println(billToAddressRS);
                Address billToAddress = new Address();
                while (billToAddressRS.next()) {
                    billToAddress.setAddressLine1(billToAddressRS.getString("ADDRESS_LINE1"));
                    billToAddress.setAddressLine2(billToAddressRS.getString("ADDRESS_LINE2"));
                    billToAddress.setCity(billToAddressRS.getString("CITY"));
                    billToAddress.setState(billToAddressRS.getString("STATE"));
                    billToAddress.setZipCode(billToAddressRS.getString("ZIP"));
                    billToAddress.setCountry(billToAddressRS.getString("COUNTRY"));
                    billToAddress.setUsageType(billToAddressRS.getString("USAGE_TYPE"));
                }
                activeRentalsObj.setBillToAddress(billToAddress);

                rentalLinesRS = (ResultSet) cstmt.getObject(8);
                System.out.println(billToAddressRS);
                List<ActiveRentalLines> activeRentalLinesLst = new ArrayList<ActiveRentalLines>();
                while (rentalLinesRS.next()) {
                    ActiveRentalLines activeRentalLinesRow = new ActiveRentalLines();
                    activeRentalLinesRow.setIsSelected(false);
                    activeRentalLinesRow.setOrderId(rentalLinesRS.getString("ORDER_HEADER_ID"));
                    activeRentalLinesRow.setOrderNumber(rentalLinesRS.getString("ORDER_NUMBER"));
                    activeRentalLinesRow.setRentalHeaderId(rentalLinesRS.getString("RENTAL_HEADER_ID"));
                    activeRentalLinesRow.setRentalLineId(rentalLinesRS.getString("RENTAL_LINE_ID"));
                    activeRentalLinesRow.setItemId(rentalLinesRS.getString("ITEM_ID"));
                    activeRentalLinesRow.setInstallBaseId(rentalLinesRS.getString("INSTALL_BASE_ID"));
                    activeRentalLinesRow.setItemSku(rentalLinesRS.getString("ITEM_SKU"));
                    activeRentalLinesRow.setSerialNumber(rentalLinesRS.getString("SERIAL_NUMBER"));
                    activeRentalLinesRow.setItemDesciption(rentalLinesRS.getString("ITEM_DESCRIPTION"));
                    activeRentalLinesLst.add(activeRentalLinesRow);
                }
                activeRentalsObj.setActiveRentalLineDtls(activeRentalLinesLst);
            }


            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


        return activeRentalsObj;
    }

    public PendingRentalDtls getPendingRentalDtls(String orderNumber) {

        System.out.println("Entered getPendingRentalDtls method...");
        PendingRentalDtls pendingRentalsObj = new PendingRentalDtls();
        try {

            System.out.println("OrderNumber:" + orderNumber);
            ResultSet shipToAddressRS = null;
            ResultSet billToAddressRS = null;
            ResultSet rentalLinesRS = null;
            Connection conn = getDbConnection();
            CallableStatement cstmt =
                conn.prepareCall("{call SYSTEM.APPS_RENTAL_TOOL_PKG.GET_ORDER_DTLS(?,?,?,?,?,?,?,?,?,?,?)}");
            System.out.println("procedure called");
            cstmt.setString(1, orderNumber);
            cstmt.registerOutParameter(2, OracleTypes.VARCHAR);
            cstmt.registerOutParameter(3, OracleTypes.VARCHAR);
            cstmt.registerOutParameter(4, OracleTypes.VARCHAR);
            cstmt.registerOutParameter(5, OracleTypes.VARCHAR);
            cstmt.registerOutParameter(6, OracleTypes.CURSOR);
            cstmt.registerOutParameter(7, OracleTypes.CURSOR);
            cstmt.registerOutParameter(8, OracleTypes.CURSOR);
            cstmt.registerOutParameter(9, OracleTypes.CURSOR);
            cstmt.registerOutParameter(10, OracleTypes.VARCHAR);
            cstmt.registerOutParameter(11, OracleTypes.VARCHAR);

            cstmt.executeQuery();
            pendingRentalsObj.setCustomerName(cstmt.getString(2));
            pendingRentalsObj.setCustomerAgreement(cstmt.getString(3));
            if(cstmt.getString(4) != null){
            pendingRentalsObj.setContractExpDate(cstmt.getString(4).substring(0,10));
            }
            //System.out.println("contact Expire date is:"+pendingRentalsObj.getContractExpDate());
            pendingRentalsObj.setSalesRepName(cstmt.getString(5));
            pendingRentalsObj.setStatusCode(cstmt.getString(10));
            pendingRentalsObj.setStatusMsg(cstmt.getString(11));
            shipToAddressRS = (ResultSet) cstmt.getObject(6);
            if (pendingRentalsObj.getStatusCode().equalsIgnoreCase("000")) {
                System.out.println(shipToAddressRS);
                Address shipToAddress = new Address();
                while (shipToAddressRS.next()) {
                    shipToAddress.setAddressLine1(shipToAddressRS.getString("ADDRESS_LINE1"));
                    shipToAddress.setAddressLine2(shipToAddressRS.getString("ADDRESS_LINE2"));
                    shipToAddress.setCity(shipToAddressRS.getString("CITY"));
                    shipToAddress.setState(shipToAddressRS.getString("STATE"));
                    shipToAddress.setZipCode(shipToAddressRS.getString("ZIP"));
                    shipToAddress.setCountry(shipToAddressRS.getString("COUNTRY"));
                    shipToAddress.setUsageType(shipToAddressRS.getString("USAGE_TYPE"));
                }
                pendingRentalsObj.setShipToAddress(shipToAddress);

                billToAddressRS = (ResultSet) cstmt.getObject(7);
                System.out.println(billToAddressRS);
                Address billToAddress = new Address();
                while (billToAddressRS.next()) {
                    billToAddress.setAddressLine1(billToAddressRS.getString("ADDRESS_LINE1"));
                    billToAddress.setAddressLine2(billToAddressRS.getString("ADDRESS_LINE2"));
                    billToAddress.setCity(billToAddressRS.getString("CITY"));
                    billToAddress.setState(billToAddressRS.getString("STATE"));
                    billToAddress.setZipCode(billToAddressRS.getString("ZIP"));
                    billToAddress.setCountry(billToAddressRS.getString("COUNTRY"));
                    billToAddress.setUsageType(billToAddressRS.getString("USAGE_TYPE"));
                }
                pendingRentalsObj.setBillToAddress(billToAddress);

                rentalLinesRS = (ResultSet) cstmt.getObject(8);
                System.out.println(billToAddressRS);
                List<PendingRentalLines> pendingRentalLinesLst = new ArrayList<PendingRentalLines>();
                while (rentalLinesRS.next()) {
                    PendingRentalLines pendingRentalLinesRow = new PendingRentalLines();

                    pendingRentalLinesRow.setRentalLineId(rentalLinesRS.getString("Line_ID"));
                    pendingRentalLinesRow.setItemId(rentalLinesRS.getString("Item_ID"));
                    pendingRentalLinesRow.setItemSku(rentalLinesRS.getString("sku"));
                    pendingRentalLinesRow.setItemDesciption(rentalLinesRS.getString("description"));
                    pendingRentalLinesRow.setEstShipDate(rentalLinesRS.getString("estimate_ship_date").substring(0,10));
                    pendingRentalLinesRow.setFreight(rentalLinesRS.getString("freight"));
                    pendingRentalLinesRow.setInventoryLocation(rentalLinesRS.getString("Org_Name"));
                    pendingRentalLinesRow.setNeedByDate(rentalLinesRS.getString("need_by_date").substring(0,10));
                    pendingRentalLinesRow.setQty(rentalLinesRS.getString("quantity"));
                    pendingRentalLinesRow.setOrderId(rentalLinesRS.getString("Order_Header_ID"));
                    pendingRentalLinesRow.setOrderNumber(rentalLinesRS.getString("order_number"));

                    pendingRentalLinesLst.add(pendingRentalLinesRow);
                }
                pendingRentalsObj.setPendingRentalLineDtls(pendingRentalLinesLst);

                rentalLinesRS = (ResultSet) cstmt.getObject(9);
                List<OtherItemsDtls> otherItemDtlsLst = new ArrayList<OtherItemsDtls>();

                while (rentalLinesRS.next()) {
                    OtherItemsDtls otherItemDtlsRow = new OtherItemsDtls();

                    otherItemDtlsRow.setLineID(rentalLinesRS.getString("Line_ID"));
                    otherItemDtlsRow.setItemID(rentalLinesRS.getString("Item_ID"));
                    otherItemDtlsRow.setHeaderID(rentalLinesRS.getString("Order_Header_ID"));
                    otherItemDtlsRow.setItemSku(rentalLinesRS.getString("sku"));
                    otherItemDtlsRow.setNeedBYDate(rentalLinesRS.getString("need_by_date").substring(0,10));
                    otherItemDtlsRow.setQty(rentalLinesRS.getString("quantity"));
                    

                    otherItemDtlsLst.add(otherItemDtlsRow);
                }
                pendingRentalsObj.setOtherItemDtls(otherItemDtlsLst);


            }


            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


        return pendingRentalsObj;
    }

    public WarehouseDtls getInventoryLocations() {
        System.out.println("Entered getInventoryLocations method...");
        WarehouseDtls inventoryLocObj = new WarehouseDtls();

        try {

            ResultSet inventoryLocRS = null;
            Connection conn = getDbConnection();
            CallableStatement cstmt = conn.prepareCall("{call SYSTEM.APPS_RENTAL_TOOL_PKG.GET_WAREHOUSE_DTLS(?,?,?)}");
            System.out.println("procedure called");
            cstmt.registerOutParameter(1, OracleTypes.CURSOR);
            cstmt.registerOutParameter(2, OracleTypes.VARCHAR);
            cstmt.registerOutParameter(3, OracleTypes.VARCHAR);

            cstmt.executeQuery();

            inventoryLocObj.setStatusCode(cstmt.getString(2));
            inventoryLocObj.setStatusMsg(cstmt.getString(3));
            
            inventoryLocRS = (ResultSet) cstmt.getObject(1);

            if (inventoryLocObj.getStatusCode().equalsIgnoreCase("000")) {
                System.out.println(inventoryLocRS);
                
                List<InventoryLoc> inventoryLocList = new ArrayList<InventoryLoc>();
                while (inventoryLocRS.next()) {
                    InventoryLoc inventoryLocRow = new InventoryLoc();
                    
                    inventoryLocRow.setId(inventoryLocRS.getString("inventory_id"));
                    inventoryLocRow.setName(inventoryLocRS.getString("warehouse_name"));
                    
                    inventoryLocList.add(inventoryLocRow);
                    
                }
                inventoryLocObj.setInventoryLocations(inventoryLocList);
            }
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return inventoryLocObj;

    }
    
    public String replaceSelectedRentalLines(List<ActiveRentalLines> selectedRentalLinesList){
        String status = "";
        try{
            Connection conn = getDbConnection();
            CallableStatement cstmt = conn.prepareCall("{call SYSTEM.APPS_RENTAL_TOOL_PKG.REPLACE_RENTAL_ORDER_ITEMS(?,?,?,?)}");

//            OracleCallableStatement cstmt = (OracleCallableStatement) conn.prepareCall("SYSTEM.APPS_RENTAL_TOOL_PKG.REPLACE_RENTAL_ORDER_ITEM(?,?,?,?}");
            
            List structList = new ArrayList();
            
            Vector vector = new Vector();

            StructDescriptor sdesc = StructDescriptor.createDescriptor ("SYSTEM.RENTAL_LINES_DTLS_OBJECT", conn);
            
            
            
            try{
                
                for (ActiveRentalLines c : selectedRentalLinesList) {
                    Object[] attributes = {c.getOrderId(),c.getOrderNumber(),c.getRentalLineId(),c.getRentalHeaderId(),c.getItemId(),c.getInstallBaseId(),c.getItemSku(),c.getItemDesciption(),c.getSerialNumber()};
                       
                    Object[] attr = new Object[9];
                    attr[0] = c.getOrderId();
                    attr[1] = c.getOrderNumber();
                    attr[2] = c.getRentalLineId();
                    attr[3] = c.getRentalHeaderId();
                    attr[4] = c.getItemId();
                    attr[5] = c.getInstallBaseId();
                    attr[6] = c.getItemSku();
                    attr[7] = c.getItemDesciption();
                    attr[8] = c.getSerialNumber();
                    
                    vector.add((Object)new STRUCT(sdesc, conn, attr));
                    
                }
                      
            }catch(Exception e){
                e.printStackTrace();
            }
              
                    ArrayDescriptor arrDescriptor = ArrayDescriptor.createDescriptor("RENTAL_LINES_DTLS_TAB",conn);
                    
                    
        
        
                    ArrayDescriptor arraydesc = ArrayDescriptor.createDescriptor("SYSTEM.RENTAL_LINES_DTLS_TAB",conn);
        
                    Object obj_array[] = vector.toArray();
                    ARRAY array = new ARRAY(arraydesc,conn,obj_array);

                    
                    Object[] o = selectedRentalLinesList.toArray();
                    System.out.println("procedure called");
                    cstmt.setArray(1, array);
                    cstmt.registerOutParameter(2, OracleTypes.VARCHAR); 
                    cstmt.registerOutParameter(3, OracleTypes.VARCHAR);
                    cstmt.registerOutParameter(4, OracleTypes.VARCHAR);

                    cstmt.execute();
                    String statusCode = cstmt.getString(3);
                    String statusMsg=cstmt.getString(4);
            if(statusCode.equals("001")){
                status="Failure";
            }else{
                status="Success";
            }
        }catch(Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
        }
        
        return status;
    }
    
    public ChangeSkuDtls getChangeSkuDtls(int inventoryID,String lineItemId) {
        System.out.println("Entered getChangeSkuDtls method...");
        ChangeSkuDtls changeSkuDtlsObj = new ChangeSkuDtls();

        try {

            ResultSet changeSkuRS = null;
            Connection conn = getDbConnection();
            CallableStatement cstmt = conn.prepareCall("{call SYSTEM.APPS_RENTAL_TOOL_PKG.GET_ITEM_DTLS(?,?,?,?,?)}");
            System.out.println("procedure called");
            cstmt.setInt(1, inventoryID);
            cstmt.registerOutParameter(2, OracleTypes.CURSOR);
            cstmt.registerOutParameter(3, OracleTypes.VARCHAR);
            cstmt.registerOutParameter(4, OracleTypes.VARCHAR);
            cstmt.setString(5, lineItemId);

            cstmt.executeQuery();

            changeSkuDtlsObj.setStatusCode(cstmt.getString(3));
            changeSkuDtlsObj.setStatusMsg(cstmt.getString(4));
            
            changeSkuRS = (ResultSet) cstmt.getObject(2);

            if (changeSkuDtlsObj.getStatusCode().equalsIgnoreCase("000")) {
                System.out.println(changeSkuRS);
                
                List<ChangeSku> changeSkuList = new ArrayList<ChangeSku>();
                while (changeSkuRS.next()) {
                    ChangeSku changeSkuRow = new ChangeSku();
                    
                    changeSkuRow.setItemDesc(changeSkuRS.getString("PART_DESCRIPTON"));
                    changeSkuRow.setLineItemId(changeSkuRS.getString("LINE_ITEM_ID"));
                    changeSkuRow.setQty(changeSkuRS.getString("QTY"));
                    changeSkuRow.setReplacementItemId(changeSkuRS.getString("REPLACEMENT_ITEM_ID"));
                    changeSkuRow.setSku(changeSkuRS.getString("SKU"));
                    String warehouseId = String.valueOf(inventoryID);
                    changeSkuRow.setWarehouseId(warehouseId);
                    
                    changeSkuList.add(changeSkuRow);
                    
                }
                changeSkuDtlsObj.setChnageSkuDtls(changeSkuList);
            }
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return changeSkuDtlsObj;

    }
    
    public String pickSelectedRentalLines(List<PendingRentalLines> selectedRentalLinesList){
        String status = "";
        try{
            Connection conn = getDbConnection();
            CallableStatement cstmt = conn.prepareCall("{call SYSTEM.APPS_RENTAL_TOOL_PKG.CREATE_RENTAL_ORDERS(?,?,?,?)}");

       List structList = new ArrayList();
            
            Vector vector = new Vector();

            StructDescriptor sdesc = StructDescriptor.createDescriptor ("SYSTEM.ORDER_HEADER_LINE_DTLS_OBJECT", conn);
            
            
            
            try{
                
                for (PendingRentalLines c : selectedRentalLinesList) {
                    //Object[] attributes = {c.getOrderId(),c.getOrderNumber(),c.getItemDailyRate(),c.getRentalLineId(),c.getFreight(),c.getItemId()};
                       
                    Object[] attr = new Object[5];
                    attr[0] = c.getOrderId();
                    attr[1] = c.getOrderNumber();
                    attr[2] = c.getRentalLineId();
                    attr[3] = c.getFreight();
                    attr[4] = c.getItemId();
                    
                    vector.add((Object)new STRUCT(sdesc, conn, attr));
                    
                }
                      
            }catch(Exception e){
                e.printStackTrace();
                }try{
                    ArrayDescriptor arraydesc = ArrayDescriptor.createDescriptor("SYSTEM.ORDER_HEADER_LINE_DTLS_TAB",conn);
        
                    Object obj_array[] = vector.toArray();
                    ARRAY array = new ARRAY(arraydesc,conn,obj_array);

                    
                    Object[] o = selectedRentalLinesList.toArray();
                    System.out.println("procedure called");
                    cstmt.setArray(1, array);
                    cstmt.registerOutParameter(2, OracleTypes.VARCHAR); 
                    cstmt.registerOutParameter(3, OracleTypes.VARCHAR);
                    cstmt.registerOutParameter(4, OracleTypes.VARCHAR);

                    cstmt.execute();
                    String statusCode = cstmt.getString(3);
                    String statusMsg=cstmt.getString(4);
            if(statusCode.equals("001")){
                status="Failure";
            }else{
                status="Success";
            }
                }
                
            catch(Exception e){
                        e.printStackTrace();
                        log.error(e.getMessage());
                    }
        }catch(Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
        }
        
        return status;
    }
    
    public String updateSelectedSKU(List<ChangeSku> selectedChangeSkuList){
        String status = "";
        try{
            Connection conn = getDbConnection();
            CallableStatement cstmt = conn.prepareCall("{call SYSTEM.APPS_RENTAL_TOOL_PKG.UPDATE_RENTAL_ORDER_LINES(?,?,?)}");
            
            Vector vector = new Vector();

            StructDescriptor sdesc = StructDescriptor.createDescriptor ("SYSTEM.ITEM_INFO_DTLS_OBJECT", conn);
            
            try{
                
                for (ChangeSku c : selectedChangeSkuList) {
   
                    Object[] attr = new Object[4];
                    attr[0] = c.getOrderHeaderId();
                    attr[1] = c.getOrderLineid();
                    attr[2] = c.getWarehouseId();
                    attr[3] = c.getReplacementItemId();
                    
                    System.out.println("order header id:"+c.getOrderHeaderId());
                    System.out.println("order line id:"+c.getOrderLineid());
                    System.out.println("warehouse id:"+c.getWarehouseId());
                    System.out.println("line item id:"+c.getLineItemId());
                    System.out.println("quantity:"+c.getQty());
                    
                    vector.add((Object)new STRUCT(sdesc, conn, attr));
                    
                }
                      
            }catch(Exception e){
                e.printStackTrace();
                }try{
                    ArrayDescriptor arraydesc = ArrayDescriptor.createDescriptor("SYSTEM.ITEM_INFO_DTLS_TAB",conn);
        
                    Object obj_array[] = vector.toArray();
                    ARRAY array = new ARRAY(arraydesc,conn,obj_array);

                    
                    Object[] o = selectedChangeSkuList.toArray();
                    System.out.println("procedure called");
                    cstmt.setArray(1, array);
                    cstmt.registerOutParameter(2, OracleTypes.VARCHAR); 
                    cstmt.registerOutParameter(3, OracleTypes.VARCHAR);

                    cstmt.execute();
                    String statusCode = cstmt.getString(2);
                    String statusMsg=cstmt.getString(3);
            if(statusCode.equals("001")){
                status="Failure";
            }else{
                status="Success";
            }
                }
                
            catch(Exception e){
                        e.printStackTrace();
                        log.error(e.getMessage());
                    }
        }catch(Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
        }
        
        return status;
    }
    
//    public ReturnRentalDtls getReturnRentalDtls(String orderNumber) {
//
//    System.out.println("Entered getReturnRentalDtls method...");
//    ReturnRentalDtls returnRentalDtls = new ReturnRentalDtls();
//    try {
//
//        System.out.println("OrderNumber:" + orderNumber);
//        ResultSet shipToAddressRS = null;
//        ResultSet billToAddressRS = null;
//        ResultSet rentalLinesRS = null;
//        ResultSet outstandingOrdersRS = null;
//        ResultSet customerDtlsRS = null;
//        Connection conn = getDbConnection();
//        CallableStatement cstmt =
//            conn.prepareCall("{call SYSTEM.APPS_RENTAL_TOOL_PKG.GET_RETURN_DETAILS_DTLS(?,?,?,?,?,?,?,?,?)}");
//        System.out.println("procedure called");
//        cstmt.setString(1, orderNumber);
//        cstmt.registerOutParameter(2, OracleTypes.VARCHAR);
//        cstmt.registerOutParameter(3, OracleTypes.CURSOR);
//        cstmt.registerOutParameter(4, OracleTypes.CURSOR);
//        cstmt.registerOutParameter(5, OracleTypes.CURSOR);
//        cstmt.registerOutParameter(6, OracleTypes.CURSOR);
//        cstmt.registerOutParameter(7, OracleTypes.CURSOR);
//        cstmt.registerOutParameter(8, OracleTypes.VARCHAR);
//        cstmt.registerOutParameter(9, OracleTypes.VARCHAR);
//
//        cstmt.executeQuery();
//        System.out.println("Executed Successfully");
//        System.out.println(cstmt.getString(6));
//         
//
//            returnRentalDtls.setSalesRepName(cstmt.getString(2));;
//            returnRentalDtls.setStatusCode(cstmt.getString(8));
//            returnRentalDtls.setStatusMsg(cstmt.getString(9));
//            
//            outstandingOrdersRS = (ResultSet) cstmt.getObject(3);
//            List<OutstandingItems> outstandingItemsList = new ArrayList<OutstandingItems>();
//            while (outstandingOrdersRS.next()) {
//                OutstandingItems outstandingItemsRow = new OutstandingItems();
//                outstandingItemsRow.setOrderId(outstandingOrdersRS.getString("ORDER_HEADER_ID"));
//                outstandingItemsRow.setOrderNumber(outstandingOrdersRS.getString("ORDER_NUMBER"));
//                outstandingItemsRow.setRentalHeaderId(outstandingOrdersRS.getString("RENTAL_HEADER_ID"));
//                outstandingItemsRow.setRentalLineId(outstandingOrdersRS.getString("RL_LINE_ID"));
//                outstandingItemsRow.setItemId(outstandingOrdersRS.getString("RL_ITEM_ID"));
//                outstandingItemsRow.setInstallBaseId(outstandingOrdersRS.getString("INSTALL_BASE_ID"));
//                outstandingItemsRow.setItemSku(outstandingOrdersRS.getString("ITEM_SKU"));
//                outstandingItemsRow.setSerialNumber(outstandingOrdersRS.getString("SERIAL_NUMBER"));
//                outstandingItemsRow.setShipDate(outstandingOrdersRS.getString("SHIP_DATE"));
//                
//                outstandingItemsList.add(outstandingItemsRow);
//            }
//            returnRentalDtls.setOutstandingItemsLst(outstandingItemsList);
//            
//            
//            
//            shipToAddressRS = (ResultSet) cstmt.getObject(4);
//            System.out.println(shipToAddressRS);
//            Address shipToAddress = new Address();
//            while (shipToAddressRS.next()) {
//                shipToAddress.setAddressLine1(shipToAddressRS.getString("ADDRESS_LINE1"));
//                shipToAddress.setAddressLine2(shipToAddressRS.getString("ADDRESS_LINE2"));
//                shipToAddress.setCity(shipToAddressRS.getString("CITY"));
//                shipToAddress.setState(shipToAddressRS.getString("STATE"));
//                shipToAddress.setZipCode(shipToAddressRS.getString("ZIP"));
//                shipToAddress.setCountry(shipToAddressRS.getString("COUNTRY"));
//                shipToAddress.setUsageType(shipToAddressRS.getString("USAGE_TYPE"));
//            }
//            returnRentalDtls.setShipToAddress(shipToAddress);
//
//            billToAddressRS = (ResultSet) cstmt.getObject(5);
//            System.out.println(billToAddressRS);
//            Address billToAddress = new Address();
//            while (billToAddressRS.next()) {
//                billToAddress.setAddressLine1(billToAddressRS.getString("ADDRESS_LINE1"));
//                billToAddress.setAddressLine2(billToAddressRS.getString("ADDRESS_LINE2"));
//                billToAddress.setCity(billToAddressRS.getString("CITY"));
//                billToAddress.setState(billToAddressRS.getString("STATE"));
//                billToAddress.setZipCode(billToAddressRS.getString("ZIP"));
//                billToAddress.setCountry(billToAddressRS.getString("COUNTRY"));
//                billToAddress.setUsageType(billToAddressRS.getString("USAGE_TYPE"));
//            }
//            returnRentalDtls.setBillToAddress(billToAddress);
//            
//            customerDtlsRS = (ResultSet) cstmt.getObject(6);
//        while (customerDtlsRS.next()) {
//            returnRentalDtls.setCustomerName(customerDtlsRS.getString("CUST_NAME"));
//            returnRentalDtls.setContractExpDate(customerDtlsRS.getString("CUST_EXPIRE_DATE"));
//            returnRentalDtls.setCustomerAgreement(customerDtlsRS.getString("CUST_AGGR"));
//        }
//        
//            rentalLinesRS = (ResultSet) cstmt.getObject(7);
//            System.out.println(billToAddressRS);
//            List<ReturnRentalLines> returnRentalLinesLst = new ArrayList<ReturnRentalLines>();
//            while (rentalLinesRS.next()) {
//                ReturnRentalLines ReturnRentalLinesRow = new ReturnRentalLines();
//                ReturnRentalLinesRow.setIsSelected(false);
//                ReturnRentalLinesRow.setOrderHeaderId(rentalLinesRS.getString("ORDER_HEADER_ID"));
//                ReturnRentalLinesRow.setOrderNumber(rentalLinesRS.getString("ORDER_NUMBER"));
//                ReturnRentalLinesRow.setInstallBaseId(rentalLinesRS.getString("RENTAL_IB_ID"));
//                ReturnRentalLinesRow.setItemId(rentalLinesRS.getString("RENTAL_ITEM_ID"));
//                ReturnRentalLinesRow.setItemSku(rentalLinesRS.getString("ITEM_SKU"));
//                ReturnRentalLinesRow.setSerialNumber(rentalLinesRS.getString("SERIAL_NUMBER"));
//                ReturnRentalLinesRow.setRentalHeaderId(rentalLinesRS.getString("RENTAL_HEADER_ID"));
//                ReturnRentalLinesRow.setStartDate(rentalLinesRS.getString("START_DATE"));
//                ReturnRentalLinesRow.setStatus(rentalLinesRS.getString("STATUS"));
//                ReturnRentalLinesRow.setRentalLineId(rentalLinesRS.getString("RENTAL_LINE_ID"));
//                ReturnRentalLinesRow.setReturnDate(rentalLinesRS.getString("RETURN_DATE"));
//                ReturnRentalLinesRow.setDailyRate(rentalLinesRS.getString("DAILY_RATE"));
//                ReturnRentalLinesRow.setNoOfDays(rentalLinesRS.getString("NO_OF_DAYS"));
//                ReturnRentalLinesRow.setRental(rentalLinesRS.getString("RENTAL"));
//                ReturnRentalLinesRow.setUnbilledFreight(rentalLinesRS.getString("UNBILLED_FREIGHT"));
//                ReturnRentalLinesRow.setDamage(rentalLinesRS.getString("DAMAGE"));
//                ReturnRentalLinesRow.setTotal(rentalLinesRS.getString("TOTAL"));
//                
//                returnRentalLinesLst.add(ReturnRentalLinesRow);
//            }
//            returnRentalDtls.setReturnRentalLineDtls(returnRentalLinesLst);
//
//
//
//        conn.close();
//
//    } catch (Exception e) {
//        e.printStackTrace();
//    }
//
//        System.out.println("customer name"+returnRentalDtls.getCustomerName());
//    return returnRentalDtls;
//    }

 public ReturnRentalDtls getReturnRentalDtls(String orderNumber) {

    System.out.println("Entered getReturnRentalDtls method...");
    ReturnRentalDtls returnRentalDtls = new ReturnRentalDtls();
    try {

        System.out.println("OrderNumber:" + orderNumber);
        ResultSet shipToAddressRS = null;
        ResultSet billToAddressRS = null;
        ResultSet rentalLinesRS = null;
        ResultSet outstandingOrdersRS = null;
        ResultSet customerDtlsRS = null;
        Connection conn = getDbConnection();
        CallableStatement cstmt =
            conn.prepareCall("{call SYSTEM.APPS_RENTAL_TOOL_PKG.GET_RETURN_DETAILS_DTLS(?,?,?,?,?,?,?,?,?)}");
        System.out.println("procedure called");
        cstmt.setString(1, orderNumber);
        cstmt.registerOutParameter(2, OracleTypes.VARCHAR);
        cstmt.registerOutParameter(3, OracleTypes.CURSOR);
        cstmt.registerOutParameter(4, OracleTypes.CURSOR);
        cstmt.registerOutParameter(5, OracleTypes.CURSOR);
        cstmt.registerOutParameter(6, OracleTypes.CURSOR);
        cstmt.registerOutParameter(7, OracleTypes.CURSOR);
        cstmt.registerOutParameter(8, OracleTypes.VARCHAR);
        cstmt.registerOutParameter(9, OracleTypes.VARCHAR);

        cstmt.executeQuery();
         

            returnRentalDtls.setSalesRepName(cstmt.getString(2));;
            returnRentalDtls.setStatusCode(cstmt.getString(8));
            returnRentalDtls.setStatusMsg(cstmt.getString(9));
            
            outstandingOrdersRS = (ResultSet) cstmt.getObject(3);
            List<OutstandingItems> outstandingItemsList = new ArrayList<OutstandingItems>();
            while (outstandingOrdersRS.next()) {
                OutstandingItems outstandingItemsRow = new OutstandingItems();
                outstandingItemsRow.setOrderId(outstandingOrdersRS.getString("ORDER_HEADER_ID"));
                outstandingItemsRow.setOrderNumber(outstandingOrdersRS.getString("ORDER_NUMBER"));
                outstandingItemsRow.setRentalHeaderId(outstandingOrdersRS.getString("RENTAL_HEADER_ID"));
                outstandingItemsRow.setRentalLineId(outstandingOrdersRS.getString("RL_LINE_ID"));
                outstandingItemsRow.setItemId(outstandingOrdersRS.getString("RL_ITEM_ID"));
                outstandingItemsRow.setInstallBaseId(outstandingOrdersRS.getString("INSTALL_BASE_ID"));
                outstandingItemsRow.setItemSku(outstandingOrdersRS.getString("ITEM_SKU"));
                outstandingItemsRow.setSerialNumber(outstandingOrdersRS.getString("SERIAL_NUMBER"));
                outstandingItemsRow.setShipDate(outstandingOrdersRS.getString("SHIP_DATE").substring(0,10));
                
                outstandingItemsList.add(outstandingItemsRow);
            }
            returnRentalDtls.setOutstandingItemsLst(outstandingItemsList);
            
            
            
            shipToAddressRS = (ResultSet) cstmt.getObject(4);
            System.out.println(shipToAddressRS);
            Address shipToAddress = new Address();
            while (shipToAddressRS.next()) {
                shipToAddress.setAddressLine1(shipToAddressRS.getString("ADDRESS_LINE1"));
                shipToAddress.setAddressLine2(shipToAddressRS.getString("ADDRESS_LINE2"));
                shipToAddress.setCity(shipToAddressRS.getString("CITY"));
                shipToAddress.setState(shipToAddressRS.getString("STATE"));
                shipToAddress.setZipCode(shipToAddressRS.getString("ZIP"));
                shipToAddress.setCountry(shipToAddressRS.getString("COUNTRY"));
                shipToAddress.setUsageType(shipToAddressRS.getString("USAGE_TYPE"));
            }
            returnRentalDtls.setShipToAddress(shipToAddress);

            billToAddressRS = (ResultSet) cstmt.getObject(5);
            System.out.println(billToAddressRS);
            Address billToAddress = new Address();
            while (billToAddressRS.next()) {
                billToAddress.setAddressLine1(billToAddressRS.getString("ADDRESS_LINE1"));
                billToAddress.setAddressLine2(billToAddressRS.getString("ADDRESS_LINE2"));
                billToAddress.setCity(billToAddressRS.getString("CITY"));
                billToAddress.setState(billToAddressRS.getString("STATE"));
                billToAddress.setZipCode(billToAddressRS.getString("ZIP"));
                billToAddress.setCountry(billToAddressRS.getString("COUNTRY"));
                billToAddress.setUsageType(billToAddressRS.getString("USAGE_TYPE"));
            }
            returnRentalDtls.setBillToAddress(billToAddress);
            
            customerDtlsRS = (ResultSet) cstmt.getObject(6);
        while (customerDtlsRS.next()) {
            returnRentalDtls.setCustomerName(customerDtlsRS.getString("CUST_NAME"));
            returnRentalDtls.setContractExpDate(customerDtlsRS.getString("CUST_EXPIRE_DATE"));
            returnRentalDtls.setCustomerAgreement(customerDtlsRS.getString("CUST_AGGR"));
        }
        
            rentalLinesRS = (ResultSet) cstmt.getObject(7);
            System.out.println(billToAddressRS);
            List<ReturnRentalLines> returnRentalLinesLst = new ArrayList<ReturnRentalLines>();
            while (rentalLinesRS.next()) {
                ReturnRentalLines ReturnRentalLinesRow = new ReturnRentalLines();
                ReturnRentalLinesRow.setIsSelected(false);
                ReturnRentalLinesRow.setOrderHeaderId(rentalLinesRS.getString("ORDER_HEADER_ID"));
                ReturnRentalLinesRow.setOrderNumber(rentalLinesRS.getString("ORDER_NUMBER"));
                ReturnRentalLinesRow.setInstallBaseId(rentalLinesRS.getString("RENTAL_IB_ID"));
                ReturnRentalLinesRow.setItemId(rentalLinesRS.getString("RENTAL_ITEM_ID"));
                ReturnRentalLinesRow.setItemSku(rentalLinesRS.getString("ITEM_SKU"));
                ReturnRentalLinesRow.setSerialNumber(rentalLinesRS.getString("SERIAL_NUMBER"));
                ReturnRentalLinesRow.setRentalHeaderId(rentalLinesRS.getString("RENTAL_HEADER_ID"));
                ReturnRentalLinesRow.setStartDate(rentalLinesRS.getString("START_DATE").substring(0,10));
                ReturnRentalLinesRow.setStatus(rentalLinesRS.getString("STATUS"));
                ReturnRentalLinesRow.setRentalLineId(rentalLinesRS.getString("RENTAL_LINE_ID"));
                ReturnRentalLinesRow.setReturnDate(rentalLinesRS.getString("RETURN_DATE").substring(0,10));
                ReturnRentalLinesRow.setDailyRate(rentalLinesRS.getString("DAILY_RATE"));
                ReturnRentalLinesRow.setNoOfDays(rentalLinesRS.getString("NO_OF_DAYS"));
                ReturnRentalLinesRow.setRental(rentalLinesRS.getString("RENTAL"));
                ReturnRentalLinesRow.setUnbilledFreight(rentalLinesRS.getString("UNBILLED_FREIGHT"));
                ReturnRentalLinesRow.setDamage(rentalLinesRS.getString("DAMAGE"));
                ReturnRentalLinesRow.setTotal(rentalLinesRS.getString("TOTAL"));
                
                returnRentalLinesLst.add(ReturnRentalLinesRow);
            }
            returnRentalDtls.setReturnRentalLineDtls(returnRentalLinesLst);



        conn.close();

    } catch (Exception e) {
        e.printStackTrace();
    }


    return returnRentalDtls;
    }

    

    public String applyUpdReturnedRentals(ReturnRentalLines applyUpdForReturnRec){
        String status = "";
        try{
            Connection conn = getDbConnection();
            CallableStatement cstmt = conn.prepareCall("{call SYSTEM.APPS_RENTAL_TOOL_PKG.UPDATE_RENTAL_LINES(?,?,?,?)}");
            
            Vector vector = new Vector();

            StructDescriptor sdesc = StructDescriptor.createDescriptor ("SYSTEM.RENTAL_LINE_DTLS_OBJECT", conn);
            
            try{
                
    
                    Object[] attr = new Object[7];
                    attr[0] = applyUpdForReturnRec.getRentalLineId();
                    attr[1] = applyUpdForReturnRec.getStartDate();
                    attr[2] = applyUpdForReturnRec.getReturnDate();
                    attr[3] = applyUpdForReturnRec.getUnbilledFreight();
                    attr[4] = applyUpdForReturnRec.getDamage();
                    attr[5] = applyUpdForReturnRec.getStatus();
                
                attr[6] = applyUpdForReturnRec.getDailyRate();
                
                System.out.println("Daily Rate in applyUpdReturnedRentals method:"+applyUpdForReturnRec.getDailyRate());
                    
                    vector.add((Object)new STRUCT(sdesc, conn, attr));
                    
            
                      
            }catch(Exception e){
                e.printStackTrace();
                }try{
                    ArrayDescriptor arraydesc = ArrayDescriptor.createDescriptor("SYSTEM.RENTAL_LINE_DTLS_TAB",conn);
        
                    Object obj_array[] = vector.toArray();
                    ARRAY array = new ARRAY(arraydesc,conn,obj_array);

                    
                    System.out.println("procedure called");
                    cstmt.setArray(1, array);
                    cstmt.registerOutParameter(2, OracleTypes.VARCHAR); 
                    cstmt.registerOutParameter(3, OracleTypes.VARCHAR);
                    cstmt.registerOutParameter(4, OracleTypes.VARCHAR);

                    cstmt.execute();
                    String rentalLineId =  cstmt.getString(2);
                    String statusCode = cstmt.getString(3);
                    String statusMsg=cstmt.getString(4);
            if(statusCode.equals("001")){
                status="Failure";
            }else{
                status="Success";
            }
                }
                
            catch(Exception e){
                        e.printStackTrace();
                        log.error(e.getMessage());
                    }
        }catch(Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
        }
        
        return status;
    }
    
    public String billReturnedRentals(String rentalLineId){
        String status = "";
        try{
            Connection conn = getDbConnection();
            CallableStatement cstmt = conn.prepareCall("{call SYSTEM.APPS_RENTAL_TOOL_PKG.BILL_RENTAL_LINES(?,?,?)}");
            
           try{
                    cstmt.setString(1, rentalLineId);
                    cstmt.registerOutParameter(2, OracleTypes.VARCHAR); 
                    cstmt.registerOutParameter(3, OracleTypes.VARCHAR);

                    cstmt.executeQuery();
                    String statusCode = cstmt.getString(2);
                    String statusMsg=cstmt.getString(3);
            if(statusCode.equals("001")){
                status="Failure";
            }else{
                status="Success";
            }
                }
                
            catch(Exception e){
                        e.printStackTrace();
                        log.error(e.getMessage());
                    }
        }catch(Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
        }
        
        return status;
    }
    
     
}
