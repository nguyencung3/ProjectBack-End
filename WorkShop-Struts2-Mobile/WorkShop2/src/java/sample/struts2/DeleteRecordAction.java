/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.struts2;

import sample.tbl_mobile.Tbl_MobileDAO;

/**
 *
 * @author Nguen Cung
 */
public class DeleteRecordAction {

    private String lastSearchValue;
    private String pk;
    private final String SUCCESS = "success";
    private final String FAIL = "fail";

    public String getLastSearchValue() {
        return lastSearchValue;
    }

    public void setLastSearchValue(String lastSearchValue) {
        this.lastSearchValue = lastSearchValue;
    }

    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }

    public DeleteRecordAction() {
    }

    public String execute() throws Exception {
        String url = FAIL;
        Tbl_MobileDAO dao = new Tbl_MobileDAO();
        boolean result = dao.DeleteRecord(pk);
        if (result) {
            url = SUCCESS;
        }
        return url;
    }

}
