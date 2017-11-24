/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.role;

import java.io.Serializable;

/**
 *
 * @author CungNguyen
 */
public class RoleObj implements Serializable {

    String userId;
    int role;

    public RoleObj() {
    }

    public void setRole(int role) {
        this.role = role;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getRole() {
        return role;
    }

    public String getUserId() {
        return userId;
    }

}
