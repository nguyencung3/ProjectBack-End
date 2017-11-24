/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.tbl_OrderDetail;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import sample.utilities.DBConnection;

/**
 *
 * @author CungNguyen
 */
public class Tbl_OrderDetailDAO implements Serializable{
    public boolean insertNewOrder(int orderId,String userId,String mobileId,int quantity)throws ClassNotFoundException,SQLException
    {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBConnection.makeConnection();
            if(con!=null)
            {
                String sql = "Insert Into tbl_OrderDetail(orderId,userId,mobileId,quantity) Values(?,?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setInt(1,orderId);
                stm.setString(2,userId);
                stm.setString(3,mobileId);
                stm.setInt(4,quantity);
                int row = stm.executeUpdate();
                if(row>0)
                {
                    return true;
                }
            }
        }finally{
            if(con!=null)
            {
                con.close();
            }
            if(stm!=null)
            {
                stm.close();
            }
        }
        return  false;
    }
      public int getOrderId()throws ClassNotFoundException,SQLException
    {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBConnection.makeConnection();
            if(con!=null)
            {
                String sql = "SELECT TOP 1 * From tbl_OrderDetail ORDER BY orderId DESC";
                stm = con.prepareStatement(sql);
                 rs = stm.executeQuery();
                if(rs.next())
                {
                    int orderId= rs.getInt("orderId");
                    return orderId;
                }
            }
        }finally{
            if(con!=null)
            {
                con.close();
            }
            if(stm!=null)
            {
                stm.close();
            }
        }
        return  -1;
    }
}
