/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.utilities;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author CungNguyen
 */
public class DBConnection implements Serializable {

    public static Connection MakeConnection() throws SQLException, NamingException {
        Context context = new InitialContext();
        Context tomcateContext = (Context) context.lookup("java:comp/env");
        DataSource ds = (DataSource) tomcateContext.lookup("SE1167DS");
        Connection con = ds.getConnection();
        return con;
    }
}
