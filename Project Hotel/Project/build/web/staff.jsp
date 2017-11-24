<%-- 
    Document   : staff
    Created on : Jun 22, 2017, 10:58:36 AM
    Author     : CungNguyen
--%>

<%@page import="java.util.List"%>
<%@page import="sample.tbl_room.Tbl_roomDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="sample.role.RoleObj"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Staff Page</title>
    </head>
    <body>
        <h1>
            Hotel NSC
        </h1>
        <h2>Welcome
            <%
                HttpSession ss = request.getSession(false);
                RoleObj roleObj = (RoleObj) ss.getAttribute("ROLE");
            %>
            <font color="red">  <%= roleObj.getUserId()%></font>
        </h2>
        <form action="logOut">
            <input type="submit" value="Log Out" name="btAction" /><br/>
        </form>
        <form action="filterDate">
            Date: <input type="date" name="txtDate" min="1970-01-01" value="${param.txtDate}"/>*yyyy-mm-dd*
            <input type="submit" value="Filter" />
        </form>
        <%
            String error = request.getParameter("txtError");
            if (error != null) {
        %>
        <%= error%>
        <%
        } else {
            String search = request.getParameter("txtDate");
            if (search.trim().length() > 0) {
                List<Tbl_roomDTO> result = (List<Tbl_roomDTO>) request.getAttribute("RESULT");
                if (result != null) {
        %>
        Damage Report Information<br/>
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Room Id</th>
                    <th>Reason</th>
                </tr>
            </thead>
            <tbody>

                <%
                    int count = 0;
                    for (Tbl_roomDTO dto : result) {
                %>
                <tr>
                    <td><%= ++count%></td>
                    <td><%= dto.getRoomId()%></td>
                    <td><%= dto.getReason()%></td>
                </tr>
                <%
                    }
                %>

            </tbody>
        </table>
        <%
        } else {
        %>
        <h2> No Record About Damage Information</h2>
        <%
                    }
                }
            }
        %>



    </body>
</html>
