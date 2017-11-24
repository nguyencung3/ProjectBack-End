/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.tbl_mobile;

import java.io.Serializable;

/**
 *
 * @author CungNguyen
 */
public class Tbl_MobileDTO implements Serializable{
    private String mobileID;
    private String description;
    private float price;
    private String mobileName;
    private int yearOfProduction;
    private int quantity;
    private boolean notSale;

    /**
     * @return the mobileID
     */
    
    public Tbl_MobileDTO() {
    }

    public Tbl_MobileDTO(String mobileID, String description, float price, String mobileName, int yearOfProduction, boolean notSale) {
        this.mobileID = mobileID;
        this.description = description;
        this.price = price;
        this.mobileName = mobileName;
        this.yearOfProduction = yearOfProduction;
        this.notSale = notSale;
    }

  
    
    public Tbl_MobileDTO(String mobileID, String description, float price, String mobileName, int yearOfProduction, int quantity, boolean notSale) {
        this.mobileID = mobileID;
        this.description = description;
        this.price = price;
        this.mobileName = mobileName;
        this.yearOfProduction = yearOfProduction;
        this.quantity = quantity;
        this.notSale = notSale;
    }

    public String getMobileID() {
        return mobileID;
    }

    /**
     * @param mobileID the mobileID to set
     */
    public void setMobileID(String mobileID) {
        this.mobileID = mobileID;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the price
     */
    public float getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * @return the mobileName
     */
    public String getMobileName() {
        return mobileName;
    }

    /**
     * @param mobileName the mobileName to set
     */
    public void setMobileName(String mobileName) {
        this.mobileName = mobileName;
    }

    /**
     * @return the yearOfProduction
     */
    public int getYearOfProduction() {
        return yearOfProduction;
    }

    /**
     * @param yearOfProduction the yearOfProduction to set
     */
    public void setYearOfProduction(int yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the notSale
     */
    public boolean isNotSale() {
        return notSale;
    }

    /**
     * @param notSale the notSale to set
     */
    public void setNotSale(boolean notSale) {
        this.notSale = notSale;
    }
    
    
}
