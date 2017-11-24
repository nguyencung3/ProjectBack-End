/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.struts2;

import java.util.List;
import sample.tbl_mobile.Tbl_MobileDAO;
import sample.tbl_mobile.Tbl_MobileDTO;

/**
 *
 * @author CungNguyen
 */
public class SearchByStaffAction {

    private String searchValue;
    private final String SUCCESS = "success";
    private List<Tbl_MobileDTO> listDevice;

    public List<Tbl_MobileDTO> getListDevice() {
        return listDevice;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public SearchByStaffAction() {
    }

    public String execute() throws Exception {
        Tbl_MobileDAO dao = new Tbl_MobileDAO();
        dao.SearchDeviceByIDOrName(searchValue);
        listDevice = dao.getListDevice();
        return SUCCESS;
    }

}
