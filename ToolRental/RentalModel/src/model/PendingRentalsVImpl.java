package model;

import java.sql.Timestamp;

import oracle.jbo.Key;
import oracle.jbo.server.EntityDefImpl;
import oracle.jbo.server.EntityImpl;
import oracle.jbo.server.TransactionEvent;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Fri May 11 13:29:01 IST 2018
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class PendingRentalsVImpl extends EntityImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    public enum AttributesEnum {
        OrderHeader,
        CustomerName,
        Sku,
        NeedByDate,
        EstimatedShipDate,
        CityState,
        Salesrep,
        Valid,
        Rental;
        private static AttributesEnum[] vals = null;
        private static final int firstIndex = 0;

        public int index() {
            return AttributesEnum.firstIndex() + ordinal();
        }

        public static final int firstIndex() {
            return firstIndex;
        }

        public static int count() {
            return AttributesEnum.firstIndex() + AttributesEnum.staticValues().length;
        }

        public static final AttributesEnum[] staticValues() {
            if (vals == null) {
                vals = AttributesEnum.values();
            }
            return vals;
        }
    }
    public static final int ORDERHEADER = AttributesEnum.OrderHeader.index();
    public static final int CUSTOMERNAME = AttributesEnum.CustomerName.index();
    public static final int SKU = AttributesEnum.Sku.index();
    public static final int NEEDBYDATE = AttributesEnum.NeedByDate.index();
    public static final int ESTIMATEDSHIPDATE = AttributesEnum.EstimatedShipDate.index();
    public static final int CITYSTATE = AttributesEnum.CityState.index();
    public static final int SALESREP = AttributesEnum.Salesrep.index();
    public static final int VALID = AttributesEnum.Valid.index();
    public static final int RENTAL = AttributesEnum.Rental.index();

    /**
     * This is the default constructor (do not remove).
     */
    public PendingRentalsVImpl() {
    }

    /**
     * Gets the attribute value for OrderHeader, using the alias name OrderHeader.
     * @return the value of OrderHeader
     */
    public String getOrderHeader() {
        return (String) getAttributeInternal(ORDERHEADER);
    }

    /**
     * Sets <code>value</code> as the attribute value for OrderHeader.
     * @param value value to set the OrderHeader
     */
    public void setOrderHeader(String value) {
        setAttributeInternal(ORDERHEADER, value);
    }

    /**
     * Gets the attribute value for CustomerName, using the alias name CustomerName.
     * @return the value of CustomerName
     */
    public String getCustomerName() {
        return (String) getAttributeInternal(CUSTOMERNAME);
    }

    /**
     * Sets <code>value</code> as the attribute value for CustomerName.
     * @param value value to set the CustomerName
     */
    public void setCustomerName(String value) {
        setAttributeInternal(CUSTOMERNAME, value);
    }

    /**
     * Gets the attribute value for Sku, using the alias name Sku.
     * @return the value of Sku
     */
    public String getSku() {
        return (String) getAttributeInternal(SKU);
    }

    /**
     * Sets <code>value</code> as the attribute value for Sku.
     * @param value value to set the Sku
     */
    public void setSku(String value) {
        setAttributeInternal(SKU, value);
    }

    /**
     * Gets the attribute value for NeedByDate, using the alias name NeedByDate.
     * @return the value of NeedByDate
     */
    public Timestamp getNeedByDate() {
        return (Timestamp) getAttributeInternal(NEEDBYDATE);
    }

    /**
     * Sets <code>value</code> as the attribute value for NeedByDate.
     * @param value value to set the NeedByDate
     */
    public void setNeedByDate(Timestamp value) {
        setAttributeInternal(NEEDBYDATE, value);
    }

    /**
     * Gets the attribute value for EstimatedShipDate, using the alias name EstimatedShipDate.
     * @return the value of EstimatedShipDate
     */
    public Timestamp getEstimatedShipDate() {
        return (Timestamp) getAttributeInternal(ESTIMATEDSHIPDATE);
    }

    /**
     * Sets <code>value</code> as the attribute value for EstimatedShipDate.
     * @param value value to set the EstimatedShipDate
     */
    public void setEstimatedShipDate(Timestamp value) {
        setAttributeInternal(ESTIMATEDSHIPDATE, value);
    }

    /**
     * Gets the attribute value for CityState, using the alias name CityState.
     * @return the value of CityState
     */
    public String getCityState() {
        return (String) getAttributeInternal(CITYSTATE);
    }

    /**
     * Sets <code>value</code> as the attribute value for CityState.
     * @param value value to set the CityState
     */
    public void setCityState(String value) {
        setAttributeInternal(CITYSTATE, value);
    }

    /**
     * Gets the attribute value for Salesrep, using the alias name Salesrep.
     * @return the value of Salesrep
     */
    public String getSalesrep() {
        return (String) getAttributeInternal(SALESREP);
    }

    /**
     * Sets <code>value</code> as the attribute value for Salesrep.
     * @param value value to set the Salesrep
     */
    public void setSalesrep(String value) {
        setAttributeInternal(SALESREP, value);
    }

    /**
     * Gets the attribute value for Valid, using the alias name Valid.
     * @return the value of Valid
     */
    public String getValid() {
        return (String) getAttributeInternal(VALID);
    }

    /**
     * Sets <code>value</code> as the attribute value for Valid.
     * @param value value to set the Valid
     */
    public void setValid(String value) {
        setAttributeInternal(VALID, value);
    }

    /**
     * Gets the attribute value for Rental, using the alias name Rental.
     * @return the value of Rental
     */
    public String getRental() {
        return (String) getAttributeInternal(RENTAL);
    }

    /**
     * Sets <code>value</code> as the attribute value for Rental.
     * @param value value to set the Rental
     */
    public void setRental(String value) {
        setAttributeInternal(RENTAL, value);
    }

    /**
     * @param orderHeader key constituent
     * @param customerName key constituent
     * @param sku key constituent
     * @param needByDate key constituent
     * @param estimatedShipDate key constituent
     * @param cityState key constituent
     * @param salesrep key constituent
     * @param valid key constituent
     * @param rental key constituent

     * @return a Key object based on given key constituents.
     */
    public static Key createPrimaryKey(String orderHeader, String customerName, String sku, Timestamp needByDate,
                                       Timestamp estimatedShipDate, String cityState, String salesrep, String valid,
                                       String rental) {
        return new Key(new Object[] {
                       orderHeader, customerName, sku, needByDate, estimatedShipDate, cityState, salesrep, valid, rental
        });
    }

    /**
     * @return the definition object for this instance class.
     */
    public static synchronized EntityDefImpl getDefinitionObject() {
        return EntityDefImpl.findDefObject("model.PendingRentalsV");
    }

    /**
     * Add locking logic here.
     */
    public void lock() {
        super.lock();
    }

    /**
     * Custom DML update/insert/delete logic here.
     * @param operation the operation type
     * @param e the transaction event
     */
    protected void doDML(int operation, TransactionEvent e) {
        super.doDML(operation, e);
    }
}
