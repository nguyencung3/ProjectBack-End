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
public class Tbl_MobileCheckVariable implements Serializable {

    Tbl_MobileTypeError error = new Tbl_MobileTypeError();
    private float priceFloat = 0;
    private int quantityInt = 0;
    private int yearInt = 0;

    public int getYearInt() {
        return yearInt;
    }

    public void setYearInt(int yearInt) {
        this.yearInt = yearInt;
    }

    public Tbl_MobileTypeError getError() {
        return error;
    }

    public void setError(Tbl_MobileTypeError error) {
        this.error = error;
    }

    public float getPriceFloat() {
        return priceFloat;
    }

    public void setPriceFloat(float priceFloat) {
        this.priceFloat = priceFloat;
    }

    public int getQuantityInt() {
        return quantityInt;
    }

    public void setQuantityInt(int quantityInt) {
        this.quantityInt = quantityInt;
    }

    public boolean checkVariableUpdate(String description, String price, String quantity) {
        boolean errorfound = false;
        if (description.trim().length() < 3 || description.trim().length() > 250) {
            errorfound = true;
            error.setDescriptionLengthError("You must input description 3 - 250 chars");
        }

        if (price.trim().length() > 0) {
            try {
                priceFloat = Float.parseFloat(price);
            } catch (NumberFormatException e) {
                errorfound = true;
                error.setPriceNotRightTypeError("You must input right type");
            }
            if (priceFloat < 0) {
                errorfound = true;
                error.setPriceNotRightTypeError("You must input number>0");
            }
        } else {
            error.setPriceNotRightTypeError("You must input number");
        }
        if (quantity.trim().length() > 0) {
            try {
                quantityInt = Integer.parseInt(quantity);
            } catch (NumberFormatException e) {
                errorfound = true;
                error.setQuantityNotRightTypeError("You must input right type");
            }
            if (quantityInt < 0) {
                errorfound = true;
                error.setQuantityNotRightTypeError("You must input number>0");
            }
        } else {
            error.setQuantityNotRightTypeError("You must input number");
        }
        if (errorfound) {
            return true;
        }
        return false;
    }

    public boolean checkVariableInsert(String mobileID, String description, String price, String mobileName, String year, String quantity) {
        boolean errorFound = false;
        errorFound = checkVariableUpdate(description, price, quantity);
        if (mobileID.trim().length() < 3 || mobileID.trim().length() > 10) {
            error.setMobileIDlengthError("Input mobile ID 3 - 10 chars");
            errorFound = true;
        }
        if (mobileName.trim().length() < 3 || mobileName.trim().length() > 20) {
            error.setMobileNameLengthError("Input mobile Name 3 - 20 chars");
            errorFound = true;
        }
        if (year.trim().length() > 0) {
            try {
                yearInt = Integer.parseInt(year);
            } catch (NumberFormatException e) {
                errorFound = true;
                error.setYearNotRightTypeError("You must input right type");
            }
            if (yearInt < 2000 || yearInt > 2017) {
                errorFound = true;
                error.setYearNotRightTypeError("You must input number (2000-2017)");
            }
        }
        else{
            error.setYearNotRightTypeError("You must input number");
        }
        if (errorFound) {
            return true;
        }

        return false;
    }

}
