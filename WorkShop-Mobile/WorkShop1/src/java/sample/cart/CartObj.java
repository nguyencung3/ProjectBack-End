/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author CungNguyen
 */
public class CartObj implements Serializable {

    String userId;
    String mobileName;
    Map<String, QuantityObj> items;
    
    public String getMobileName() {
        return mobileName;
    }

    public void setMobileName(String mobileName) {
        this.mobileName = mobileName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    public Map<String, QuantityObj> getItems() {
        return items;
    }

    public void setItems(Map<String, QuantityObj> items) {
        this.items = items;
    }
    

    public void addItemToCart(String title) {
        QuantityObj quantity = new QuantityObj();
        if (this.items == null) {
            items = new HashMap<String, QuantityObj>();
        }
        int quantityUser = 1;
        if (this.items.containsKey(title)) {
            quantity = items.get(title);
            quantityUser = quantity.getQuantityUser() + 1;
        }
        quantity.setQuantityUser(quantityUser);
        this.items.put(title, quantity);
    }

    public void removeItemFromCart(String title) {
        if (this.items == null) {
            return;
        }
        if (this.items.containsKey(title)) {
            this.items.remove(title);
        }
        if (this.items.isEmpty()) {
            this.items = null;
        }
    }
}
