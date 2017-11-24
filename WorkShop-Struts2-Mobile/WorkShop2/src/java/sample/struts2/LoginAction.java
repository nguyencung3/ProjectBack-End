/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.struts2;

import com.opensymphony.xwork2.ActionContext;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import sample.tbl_user.Tbl_UserDAO;

/**
 *
 * @author CungNguyen
 */
public class LoginAction {

    private String username;
    private String password;
    private final String FAIL = "fail";
    private final String USER = "userpage";
    private final String STAFF = "staffpage";
    private final String ADMIN = "adminpage";

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LoginAction() {
    }

    public String execute() throws Exception {
        Tbl_UserDAO dao = new Tbl_UserDAO();
        int role = dao.CheckLogin(username, password);
        String url = FAIL;
        if (role != -1) {
            Map session = ActionContext.getContext().getSession();
            session.put("USERNAME", username);
            if (role == 0) {
                url = USER;
            } else if (role == 2) {
                url = STAFF;
            } else if (role == 1) {
                url = ADMIN;

            }
        }
        return url;
    }

}
