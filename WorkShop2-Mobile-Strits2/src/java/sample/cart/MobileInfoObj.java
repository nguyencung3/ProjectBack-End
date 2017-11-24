/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.cart;

import java.io.Serializable;

/**
 *
 * @author Nguen Cung
 */
public class MobileInfoObj implements Serializable {

    private String mobileId;
    private String mobileName;

    public String getMobileId() {
        return mobileId;
    }

    public String getMobileName() {
        return mobileName;
    }

    public void setMobileId(String mobileId) {
        this.mobileId = mobileId;
    }

    public void setMobileName(String mobileName) {
        this.mobileName = mobileName;
    }

    public MobileInfoObj() {
    }

}
