/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.tbl_user;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import sample.utilities.DBConnection;

/**
 *
 * @author CungNguyen
 */
public class Tbl_UserDAO implements Serializable {

    public int CheckLogin(String userId, String password) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBConnection.MakeConnection();
            if (con != null) {
                String sql = "Select * From tbl_User Where userId = ? And password = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, userId);
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
}
