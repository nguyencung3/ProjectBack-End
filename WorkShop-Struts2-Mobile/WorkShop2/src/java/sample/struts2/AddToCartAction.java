/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.struts2;

import com.opensymphony.xwork2.ActionContext;
import java.util.Map;
import java.util.StringTokenizer;
import sample.cart.CartObj;
import sample.cart.MobileInfoObj;
import sample.cart.QuantityObj;

/**
 *
 * @author Nguen Cung
 */
public class AddToCartAction {

    private float lastFrom;
    private float lastTo;
    private String userId;
    private String mobileName;
    private String mobileId;
    private final String SUCCESS = "success";

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public float getLastFrom() {
        return lastFrom;
    }

    public void setLastFrom(float lastFrom) {
        this.lastFrom = lastFrom;
    }

    public float getLastTo() {
        return lastTo;
    }

    public void setLastTo(float lastTo) {
        this.lastTo = lastTo;
    }

    public String getMobileName() {
        return mobileName;
    }

    public void setMobileName(String mobileName) {
        this.mobileName = mobileName;
    }

    public String getMobileId() {
        return mobileId;
    }

    public void setMobileId(String mobileId) {
        this.mobileId = mobileId;
    }

    public AddToCartAction() {
    }

    public String execute() throws Exception {
        CartObj cart = null;
        Map session = ActionContext.getContext().getSession();
        if (!session.containsKey("CART")) {
            cart = new CartObj();
        } else {
            cart = (CartObj) session.get("CART");
        }
        cart.addItemToCart(mobileId + "," + mobileName);
        cart.setUserId((String) session.get("USERNAME"));
        session.put("CART", cart);
//        for (Map.Entry<String, QuantityObj> item : cart.getItems().entrySet()) {
//            System.out.println(item.getKey());
//            QuantityObj quantity = item.getValue();
//            System.out.println(quantity.getQuantityUser() + ":USER");
//            System.out.println(quantity.getQuantityinStock() + ":STtock");
//        }
        return SUCCESS;
    }

}
