/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.struts2;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import sample.tbl_mobile.Tbl_MobileCheckVariable;
import sample.tbl_mobile.Tbl_MobileDAO;
import sample.tbl_mobile.Tbl_MobileTypeError;

/**
 *
 * @author CungNguyen
 */
public class UpdateDeviceByStaffAction extends ActionSupport {

    private String mobileId;
    private String description;
    private String price;
    private float priceFloat;
    private String quantity;
    private int quantityInt;
    private boolean notSale;
    private String searchValue;
    private final String SUCCESS = "success";
    private final String FAIL = "fail";

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

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

    public boolean myValidate() {
        boolean errorFound = false;
        Tbl_MobileCheckVariable check = new Tbl_MobileCheckVariable();
        if (check.checkVariableUpdate(description, price, quantity)) {
            Tbl_MobileTypeError error = new Tbl_MobileTypeError();
            error = check.getError();
            error.setMobileIDerror(mobileId);
            HttpServletRequest request = ServletActionContext.getRequest();
            request.setAttribute("ERRORUPDATE", error);
            errorFound = true;
        }
        if (!errorFound) {
            priceFloat = check.getPriceFloat();
            quantityInt = check.getQuantityInt();
        }
        return errorFound;
    }

    public UpdateDeviceByStaffAction() {
    }

    public String execute() throws Exception {
        String url = FAIL;
        Tbl_MobileDAO dao = new Tbl_MobileDAO();
        if (myValidate()) {
            url = "input";
            return url;
        }
        boolean result = dao.updateRecord(mobileId, description, priceFloat, quantityInt, notSale);
        if (result) {
            url = SUCCESS;
        }
        return url;
    }

}
