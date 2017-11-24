/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.struts2;

import com.opensymphony.xwork2.ActionSupport;
import sample.tbl_mobile.Tbl_MobileDAO;

/**
 *
 * @author CungNguyen
 */
public class InsertMobileAction extends ActionSupport {

    private String mobileId;
    private String description;
    private String price;
    private float priceFloat;
    private String mobileName;
    private String yearOfProduction;
    private int yearInt;
    private String quantity;
    private int quantityInt;
    private boolean notSale;
    private final String SUCCESS = "success";
    private final String FAIL = "fail";

    public String getMobileId() {
        return mobileId;
    }

    public void setMobileId(String mobileId) {
        this.mobileId = mobileId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMobileName() {
        return mobileName;
    }

    public void setMobileName(String mobileName) {
        this.mobileName = mobileName;
    }

    public String getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(String yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public boolean isNotSale() {
        return notSale;
    }

    public void setNotSale(boolean notSale) {
        this.notSale = notSale;
    }

    public InsertMobileAction() {
    }

    @Override
    public void validate() {
        super.validate(); //To change body of generated methods, choose Tools | Templates.
        if (yearOfProduction.trim().length() > 0) {
            try {
                yearInt = Integer.parseInt(yearOfProduction);
            } catch (NumberFormatException e) {
                addFieldError("yearOfProduction", "You must input right type");
            }
            if (yearInt < 2000 || yearInt > 2017) {
                addFieldError("yearOfProduction", "You must input number (2000-2017)");
            }
        } else {
            addFieldError("yearOfProduction", "Not allowed Empty");
        }
        if (quantity.trim().length() > 0) {
            try {
                quantityInt = Integer.parseInt(quantity);
            } catch (NumberFormatException e) {
                addFieldError("quantity", "You must input right type");
            }
            if (quantityInt < 0) {
                addFieldError("quantity", "You must input quantity > 0");
            }
        } else {
            addFieldError("quantity", "Not allowed Empty");
        }
        if (price.trim().length() > 0) {
            try {
                priceFloat = Float.parseFloat(price);
            } catch (NumberFormatException e) {
                addFieldError("price", "You must input right type");
            }
            if (priceFloat < 0) {
                addFieldError("price", "You must input price > 0");
            }
        } else {
            addFieldError("price", "Not allowed Empty");
        }

    }

    public String execute() throws Exception {
        String url = FAIL;
        Tbl_MobileDAO dao = new Tbl_MobileDAO();
        boolean result = dao.insertNewMobile(mobileId, description, priceFloat, mobileName, yearInt, quantityInt, notSale);
        if (result) {
            url = SUCCESS;
        }
        return url;
    }

}
