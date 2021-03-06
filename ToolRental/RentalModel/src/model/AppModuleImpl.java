package model;

import model.common.AppModule;

import oracle.jbo.server.ApplicationModuleImpl;
import oracle.jbo.server.ViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Fri May 11 13:28:30 IST 2018
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class AppModuleImpl extends ApplicationModuleImpl implements AppModule {
    /**
     * This is the default constructor (do not remove).
     */
    public AppModuleImpl() {
    }

    /**
     * Container's getter for PendingRentalsVView1.
     * @return PendingRentalsVView1
     */
    public PendingRentalsVViewImpl getPendingRentalsVView1() {
        return (PendingRentalsVViewImpl) findViewObject("PendingRentalsVView1");
    }

    /**
     * Container's getter for ReturnedRentalsVView1.
     * @return ReturnedRentalsVView1
     */
    public ReturnedRentalsVViewImpl getReturnedRentalsVView1() {
        return (ReturnedRentalsVViewImpl) findViewObject("ReturnedRentalsVView1");
    }

    /**
     * Container's getter for ActiveRentalsVView1.
     * @return ActiveRentalsVView1
     */
    public ViewObjectImpl getActiveRentalsVView1() {
        return (ViewObjectImpl) findViewObject("ActiveRentalsVView1");
    }
    public void refreshPendingRentalView(){
        PendingRentalsVViewImpl rental = getPendingRentalsVView1();
        ReturnedRentalsVViewImpl rental1 = getReturnedRentalsVView1();
                rental.executeQuery(); 
                rental1.executeQuery();
    }
}

