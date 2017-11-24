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
public class Tbl_MobileTypeError implements Serializable {
    private String mobileIDerror;
    private String descriptionLengthError;
    private String priceNotRightTypeError;
    private String quantityNotRightTypeError;
    private String mobileIDlengthError;
    private String mobileIDExisted;
    private String mobileNameLengthError;
    private String yearNotRightTypeError;

    public String getMobileIDerror() {
        return mobileIDerror;
    }

    public void setMobileIDerror(String mobileIDerror) {
        this.mobileIDerror = mobileIDerror;
    }

    
    public Tbl_MobileTypeError() {
    }

    public String getMobileIDlengthError() {
        return mobileIDlengthError;
    }

    public void setMobileIDlengthError(String mobileIDlengthError) {
        this.mobileIDlengthError = mobileIDlengthError;
    }

    public String getMobileIDExisted() {
        return mobileIDExisted;
    }

    public void setMobileIDExisted(String mobileIDExisted) {
        this.mobileIDExisted = mobileIDExisted;
    }

    public String getMobileNameLengthError() {
        return mobileNameLengthError;
    }

    public void setMobileNameLengthError(String mobileNameLengthError) {
        this.mobileNameLengthError = mobileNameLengthError;
    }

    public String getYearNotRightTypeError() {
        return yearNotRightTypeError;
    }

    public void setYearNotRightTypeError(String yearNotRightTypeError) {
        this.yearNotRightTypeError = yearNotRightTypeError;
    }

    public String getDescriptionLengthError() {
        return descriptionLengthError;
    }

    public void setDescriptionLengthError(String descriptionLengthError) {
        this.descriptionLengthError = descriptionLengthError;
    }

    public String getPriceNotRightTypeError() {
        return priceNotRightTypeError;
    }

    public void setPriceNotRightTypeError(String priceNotRightTypeError) {
        this.priceNotRightTypeError = priceNotRightTypeError;
    }

    public String getQuantityNotRightTypeError() {
        return quantityNotRightTypeError;
    }

    public void setQuantityNotRightTypeError(String quantityNotRightTypeError) {
        this.quantityNotRightTypeError = quantityNotRightTypeError;
    }

}
