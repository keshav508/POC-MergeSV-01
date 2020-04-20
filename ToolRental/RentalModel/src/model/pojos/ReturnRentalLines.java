package model.pojos;

import java.io.Serializable;

public class ReturnRentalLines implements Serializable{
    public ReturnRentalLines() {
        super();
    }
    private Boolean isSelected;
    private String startDate;
    private String status;
    private String itemSku;
    private String serialNumber;
    private String returnDate;
    private String dailyRate;
    private String noOfDays;
    private String rental;
    private String unbilledFreight;
    private String damage;
    private String total;
    private String orderHeaderId;
    private String orderNumber;
    private String rentalHeaderId;
    private String rentalLineId;
    private String installBaseId;
    private String itemId;

    public void setItemSku(String itemSku) {
        this.itemSku = itemSku;
    }

    public String getItemSku() {
        return itemSku;
    }

    public void setOrderHeaderId(String orderHeaderId) {
        this.orderHeaderId = orderHeaderId;
    }

    public String getOrderHeaderId() {
        return orderHeaderId;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setRentalHeaderId(String rentalHeaderId) {
        this.rentalHeaderId = rentalHeaderId;
    }

    public String getRentalHeaderId() {
        return rentalHeaderId;
    }

    public void setInstallBaseId(String installBaseId) {
        this.installBaseId = installBaseId;
    }

    public String getInstallBaseId() {
        return installBaseId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemId() {
        return itemId;
    }

    

    public void setIsSelected(Boolean isSelected) {
        this.isSelected = isSelected;
    }

    public Boolean getIsSelected() {
        return isSelected;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setRentalLineId(String rentalLineId) {
        this.rentalLineId = rentalLineId;
    }

    public String getRentalLineId() {
        return rentalLineId;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setDailyRate(String dailyRate) {
        this.dailyRate = dailyRate;
    }

    public String getDailyRate() {
        return dailyRate;
    }

    public void setNoOfDays(String noOfDays) {
        this.noOfDays = noOfDays;
    }

    public String getNoOfDays() {
        return noOfDays;
    }

    public void setRental(String rental) {
        this.rental = rental;
    }

    public String getRental() {
        return rental;
    }

    public void setUnbilledFreight(String unbilledFreight) {
        this.unbilledFreight = unbilledFreight;
    }

    public String getUnbilledFreight() {
        return unbilledFreight;
    }

    public void setDamage(String damage) {
        this.damage = damage;
    }

    public String getDamage() {
        return damage;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTotal() {
        return total;
    }
}
