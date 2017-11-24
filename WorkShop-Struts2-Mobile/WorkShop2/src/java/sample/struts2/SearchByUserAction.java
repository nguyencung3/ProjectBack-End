/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.struts2;

import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import sample.tbl_mobile.Tbl_MobileDAO;
import sample.tbl_mobile.Tbl_MobileDTO;

/**
 *
 * @author Nguen Cung
 */
public class SearchByUserAction extends ActionSupport {

    private String from;
    private float fromFloat;
    private String to;
    private float toFloat;
    private final String SUCCESS = "success";
    private List<Tbl_MobileDTO> list;

    public List<Tbl_MobileDTO> getList() {
        return list;
    }

    public SearchByUserAction() {
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public void validate() {
        super.validate(); //To change body of generated methods, choose Tools | Templates.
        if (from.trim().length() > 0) {
            try {
                fromFloat = Float.parseFloat(from);
            } catch (NumberFormatException e) {
                addFieldError("from", "You must input right type");
            }
            if (fromFloat < 0) {
                addFieldError("from", "You must input number > 0");
            }
        } else {
            addFieldError("from", "Not allowed Empty");
        }
        if (to.trim().length() > 0) {
            try {
                toFloat = Float.parseFloat(to);
            } catch (NumberFormatException e) {
                addFieldError("to", "You must input right type");
            }
            if (toFloat < 0) {
                addFieldError("to", "You must input number > 0");
            }
        } else {
            addFieldError("to", "Not allowed Empty");
        }
    }

    public String execute() throws Exception {
        Tbl_MobileDAO dao = new Tbl_MobileDAO();
        dao.SearchDeviceByRange(fromFloat, toFloat);
        this.list = dao.getListDevice();
        return SUCCESS;
    }

}
