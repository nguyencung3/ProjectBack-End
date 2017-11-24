/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.tbl_room;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import sample.utilities.DBConnection;

/**
 *
 * @author CungNguyen
 */
public class Tbl_roomDAO implements Serializable {

    private List<Tbl_roomDTO> listRoomsMaintance;
    private List<Tbl_roomDTO> listRoomsInfo;

    public List<Tbl_roomDTO> getListRoomsInfo() {
        return listRoomsInfo;
    }

    public List<Tbl_roomDTO> getListRoomsMaintance() {
        return listRoomsMaintance;
    }

    public void filterDate(Timestamp requestDate) throws ParseException, ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBConnection.makeConnection();
            if (con != null) {
                String sql = "Select * From tbl_maintenance Where Cast(requestDate AS Date) = ?";
                stm = con.prepareStatement(sql);
                stm.setTimestamp(1, requestDate);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String reason = rs.getString("reason");
                    String roomId = rs.getString("roomId");
                    if (listRoomsMaintance == null) {
                        listRoomsMaintance = new ArrayList<Tbl_roomDTO>();
                    }
                    Tbl_roomDTO dto = new Tbl_roomDTO(roomId, reason);
                    listRoomsMaintance.add(dto);
                }
            }//end if con
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
    public boolean updateSolveProblem(Timestamp repairedDate, String mend, float cost, int id) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBConnection.makeConnection();
            if (con != null) {
                String sql = "Update tbl_maintenance Set repairedDate = ?, mend = ?, cost = ? Where id = ?";
                stm = con.prepareStatement(sql);
                stm.setTimestamp(1, repairedDate);
                stm.setString(2, mend);
                stm.setFloat(3, cost);
                stm.setInt(4, id);
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

    public void getRoomInfoFollowWithCategory(String categoryID) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBConnection.makeConnection();
            if (con != null) {
                String sql = "Select * From tbl_room Where categoryID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, categoryID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String roomID = rs.getString("roomID");
                    String description = rs.getString("description");
                    float hourPrice = rs.getFloat("hourPrice");
                    float dayPrice = rs.getFloat("dayPrice");
                    Tbl_roomDTO dto = new Tbl_roomDTO(roomID, description, hourPrice, dayPrice);
                    if (listRoomsInfo == null) {
                        listRoomsInfo = new ArrayList<Tbl_roomDTO>();
                    }
                    listRoomsInfo.add(dto);
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

    public int getLastIdOfmaintenance() throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBConnection.makeConnection();
            if (con != null) {
                String sql = "SELECT TOP 1 * From tbl_maintenance ORDER BY id DESC";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                if (rs.next()) {
                    int idMaintenance = rs.getInt("id");
                    return idMaintenance;
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
        return -1;
    }

    public boolean InsertReportRoom(int id, String reportInfo, Timestamp datetime, String roomId) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBConnection.makeConnection();
            if (con != null) {
                String sql = "Insert Into tbl_maintenance(id,requestDate,reason,roomID) Values(?, ?, ?, ?)";
                stm = con.prepareStatement(sql);
                stm.setInt(1, id);
                stm.setTimestamp(2, datetime);
                stm.setString(3, reportInfo);
                stm.setString(4, roomId);
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

    public boolean UpdateInfoRoom(String roomId, String description, float hourPrice, float dayPrice) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBConnection.makeConnection();
            if (con != null) {
                String sql = "Update tbl_room Set description = ?, hourPrice = ?, dayPrice = ? Where roomID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, description);
                stm.setFloat(2, hourPrice);
                stm.setFloat(3, dayPrice);
                stm.setString(4, roomId);
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

    public void SearchRoom(String searchValue) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBConnection.makeConnection();
            if (con != null) {
                String sql = "Select * From tbl_room Where roomID Like ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String description = rs.getString("description");
                    float hourPrice = rs.getFloat("hourPrice");
                    String roomId = rs.getString("roomID");
                    float dayPrice = rs.getFloat("dayPrice");
                    if (listRoomsInfo == null) {
                        listRoomsInfo = new ArrayList<Tbl_roomDTO>();
                    }
                    Tbl_roomDTO dto = new Tbl_roomDTO(roomId, description, hourPrice, dayPrice);
                    listRoomsInfo.add(dto);
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

    public void ShowAllRoom() throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBConnection.makeConnection();
            if (con != null) {
                String sql = "Select * From tbl_room";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String description = rs.getString("description");
                    float hourPrice = rs.getFloat("hourPrice");
                    String roomId = rs.getString("roomID");
                    float dayPrice = rs.getFloat("dayPrice");
                    if (listRoomsInfo == null) {
                        listRoomsInfo = new ArrayList<Tbl_roomDTO>();
                    }
                    Tbl_roomDTO dto = new Tbl_roomDTO(roomId, description, hourPrice, dayPrice);
                    listRoomsInfo.add(dto);
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
}
