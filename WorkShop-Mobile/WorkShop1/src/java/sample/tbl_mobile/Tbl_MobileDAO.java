/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.tbl_mobile;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sample.utilities.DBConnection;

/**
 *
 * @author CungNguyen
 */
public class Tbl_MobileDAO implements Serializable {

    private List<Tbl_MobileDTO> listDevice;

    public List<Tbl_MobileDTO> getListDevice() {
        return listDevice;
    }

    public void SearchDeviceByIDOrName(String searchValue) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBConnection.makeConnection();
            if (con != null) {
                String sql = "Select * From tbl_Mobile Where mobileId Like ? Or mobileName Like ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                stm.setString(2, "%" + searchValue + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String mobileId = rs.getString("mobileId");
                    String description = rs.getString("description");
                    float price = rs.getFloat("price");
                    String mobileName = rs.getString("mobileName");
                    int yearOfProduction = rs.getInt("yearOfProduction");
                    int quantity = rs.getInt("quantity");
                    boolean notSale = rs.getBoolean("notSale");
                    Tbl_MobileDTO record = new Tbl_MobileDTO(mobileId, description, price, mobileName, yearOfProduction, quantity, notSale);
                    if (this.listDevice == null) {
                        this.listDevice = new ArrayList<Tbl_MobileDTO>();
                    }
                    listDevice.add(record);
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
            if (rs != null) {
                rs.close();
            }
        }

    }

    public void SearchDeviceByRange(int max, int min) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBConnection.makeConnection();
            if (con != null) {
                String sql = "Select * From tbl_Mobile Where price >= ? And price <= ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, min);
                stm.setInt(2, max);
                rs = stm.executeQuery();
                while (rs.next()) {
                    boolean isNotsale = rs.getBoolean("notSale");
                    if (!isNotsale) {
                        String mobileID = rs.getString("mobileID");
                        String mobileName = rs.getString("mobileName");
                        String description = rs.getString("description");
                        int yearOfProduction = rs.getInt("yearOfProduction");
                        float price = rs.getFloat("price");
                        Tbl_MobileDTO dto = new Tbl_MobileDTO(mobileID, description, price, mobileName, yearOfProduction, isNotsale);
                        if (this.listDevice == null) {
                            this.listDevice = new ArrayList<Tbl_MobileDTO>();
                        }
                        listDevice.add(dto);
                    }
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public boolean DeleteRecord(String primaryKey) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBConnection.makeConnection();
            if (con != null) {
                String sql = "Delete From tbl_Mobile Where mobileId = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, primaryKey);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }

        } finally {
            if (con != null) {
                con.close();
            }
            if (stm != null) {
                stm.close();
            }
        }
        return false;
    }

    public boolean updateRecord(String mobileID,int quantity) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBConnection.makeConnection();
            if (con != null) {
                String sql = "Update tbl_Mobile Set quantity = ?"
                        + " Where mobileId = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, quantity);
                stm.setString(2, mobileID);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (con != null) {
                con.close();
            }
            if (stm != null) {
                stm.close();
            }
        }
        return false;
    }

    public boolean updateRecord(String mobileID, String description, float price, int quantity, boolean notsale) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBConnection.makeConnection();
            if (con != null) {
                String sql = "Update tbl_Mobile Set description = ?"
                        + ", price = ?"
                        + ", quantity = ?"
                        + ", notSale = ? Where mobileId = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, description);
                stm.setFloat(2, price);
                stm.setInt(3, quantity);
                stm.setBoolean(4, notsale);
                stm.setString(5, mobileID);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (con != null) {
                con.close();
            }
            if (stm != null) {
                stm.close();
            }
        }
        return false;
    }

    public boolean insertNewMobile(String mobileID, String description, float price, String mobileName, int year, int quantity, boolean notSale) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBConnection.makeConnection();
            if (con != null) {
                String sql = "Insert Into tbl_Mobile(mobileId,description,price,mobileName,yearOfProduction,quantity,notSale)"
                        + " Values(?,?,?,?,?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, mobileID);
                stm.setString(2, description);
                stm.setFloat(3, price);
                stm.setString(4, mobileName);
                stm.setInt(5, year);
                stm.setInt(6, quantity);
                stm.setBoolean(7, notSale);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (con != null) {
                con.close();
            }
            if (stm != null) {
                stm.close();
            }
        }
        return false;
    }

    public int getQuantityProduct(String idMobile) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBConnection.makeConnection();
            if (con != null) {
                String sql = "Select * From tbl_Mobile Where mobileId = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, idMobile);
                rs = stm.executeQuery();
                if (rs.next()) {
                    int quantityProduct = rs.getInt("quantity");
                    return quantityProduct;
                }
            }
        } finally {
            if (con != null) {
                con.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return -1;
    }
}
