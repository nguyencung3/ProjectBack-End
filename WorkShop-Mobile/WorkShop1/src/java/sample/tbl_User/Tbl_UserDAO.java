/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.tbl_User;

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
public class Tbl_UserDAO implements Serializable {

    public int checkLogin(String username, String password) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBConnection.makeConnection();
            if (con != null) {
                String sql = "Select * From tbl_User Where userId = ? And password = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                rs = stm.executeQuery();
                if (rs.next()) {
                    int role = rs.getInt("role");
                    return role;
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
    List<Tbl_UserDTO> listUser;

    public List<Tbl_UserDTO> getListUser() {
        return listUser;
    }

    public boolean insertRecord(String username, int password, String fullname, int role) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBConnection.makeConnection();
            if (con != null) {
                String sql = "Insert Into tbl_User(userId,password,fullName,role)"
                        + " Values(?, ?, ?, ?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setInt(2, password);
                stm.setString(3, fullname);
                stm.setInt(4, role);
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

    public void searchUserByIDorName(String searchValue) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBConnection.makeConnection();
            if (con != null) {
                String sql = "Select * From tbl_User Where userId Like ? Or fullName Like ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                stm.setString(2, "%" + searchValue + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String userId = rs.getString("userId");
                    int password = rs.getInt("password");
                    String lastname = rs.getString("fullName");
                    int role = rs.getInt("role");
                    Tbl_UserDTO dto = new Tbl_UserDTO(userId, password, lastname, role);
                    if (this.listUser == null) {
                        this.listUser = new ArrayList<Tbl_UserDTO>();
                    }
                    this.listUser.add(dto);
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
    }

    public boolean DeleteUser(String userId) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBConnection.makeConnection();
            if (con != null) {
                String sql = "Delete From tbl_User Where userId = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, userId);
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
            if (rs != null) {
                rs.close();
            }
        }
        return false;
    }

    public boolean UpdateUser(String userId, int password, String fullname, int role) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBConnection.makeConnection();
            if (con != null) {
                String sql = "Update tbl_User Set password = ?, fullName = ?, role = ? "
                        + "Where userId = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, password);
                stm.setString(2, fullname);
                stm.setInt(3, role);
                stm.setString(4, userId);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } catch (Exception e) {
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
}
