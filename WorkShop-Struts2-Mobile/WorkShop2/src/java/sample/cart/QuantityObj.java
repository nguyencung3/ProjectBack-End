/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.cart;

import java.io.Serializable;

/**
 *
 * @author CungNguyen
 */
public class QuantityObj implements Serializable {

    private int quantityUser;
    private int quantityinStock;

    public int getQuantityUser() {
        return quantityUser;
    }

    public void setQuantityUser(int quantityUser) {
        this.quantityUser = quantityUser;
    }

    public int getQuantityinStock() {
        return quantityinStock;
    }

    public void setQuantityinStock(int quantityinStock) {
        this.quantityinStock = quantityinStock;
    }

    public QuantityObj() {
    }
}
