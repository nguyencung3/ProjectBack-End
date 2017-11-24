/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.struts2;

import com.opensymphony.xwork2.ActionContext;
import java.util.Map;

/**
 *
 * @author Nguen Cung
 */
public class LogOutAction {

    private final String SUCCESS = "success";

    public LogOutAction() {
    }

    public String execute() throws Exception {
        Map session = ActionContext.getContext().getSession();
        session.clear();
        return SUCCESS;
    }

}
