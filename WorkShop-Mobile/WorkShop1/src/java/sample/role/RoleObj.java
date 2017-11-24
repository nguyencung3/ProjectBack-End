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

    private String userId;
    private int role;
    String url = "invalidRole.html";

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void transferRole(String funtion) {
        if (role == 0) {
            roleUser(funtion);
        } else if (role == 1) {
            roleAdmin(funtion);
        } else if (role == 2) {
            roleStaff(funtion);
        }
    }

    public void roleAdmin(String function) {
        if (function.equals("Search")) {
            url = "SearchByAdminServlet";
        }
        if (function.equals("Update")) {
            url = "UpdateUserServlet";
        }
        if (function.equals("Delete")) {
            url = "DeleteUserServlet";
        }
    }

    public void roleUser(String function) {
        if (function.equals("Search")) {
            url = "SearchByUserServlet";
        }
    }

    public void roleStaff(String function) {
        if (function.equals("Search")) {
            url = "SearchByStaffServlet";
        }
        if (function.equals("Update")) {
            url = "UpdateRecordServlet";
        }
        if (function.equals("Delete")) {
            url = "DeleteRecordServlet";
        }
    }
}
