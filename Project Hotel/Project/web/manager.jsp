<%-- 
    Document   : manager
    Created on : Jun 22, 2017, 10:59:10 AM
    Author     : CungNguyen
--%>

<%@page import="java.util.Map"%>
<%@page import="sample.validate.ValidateTypeError"%>
<%@page import="java.util.List"%>
<%@page import="sample.tbl_room.Tbl_roomDTO"%>
<%@page import="sample.role.RoleObj"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manager Page</title>
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
        <form action="roomInfo">
            Room : <input type="text" name="txtRoomSearch" value="${param.txtRoomSearch}" />
            <input type="submit" value="Search" name="btAction"/>
            <input type="submit" value="Show All" name="btAction"/>            
        </form>
        <%
            String searchValue = request.getParameter("txtRoomSearch");
            if (searchValue != null) {
                List<Tbl_roomDTO> result = (List<Tbl_roomDTO>) request.getAttribute("RESULT");
                if (result != null) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Room</th>
                    <th>Description</th>
                    <th>Hour Price</th>
                    <th>Day Price</th>
                    <th>Update</th>
                    <th>Damage Report</th>
                </tr>
            </thead>
            <form action="updateByManager">
                <tbody>
                    <%
                        int count = 0;
                        Map<String, String> listError = (Map<String, String>) request.getAttribute("LISTERROR");
                        for (Tbl_roomDTO dto : result) {
                    %>
                    <tr>
                        <td><%= ++count%></td>
                        <td><%= dto.getRoomId()%></td>
                        <td>
                            <input type="text" name="<%= "txtDescription" + dto.getRoomId()%>" value="<%= dto.getDescription()%>" />
                            <%
                                ValidateTypeError error = (ValidateTypeError) request.getAttribute("ERROR");
                                if (error != null) {
                                    if (error.getDescriptionLengthError() != null && error.getRoomId().equals(dto.getRoomId())) {
                            %>
                            <%= error.getDescriptionLengthError()%>
                            <%
                                    }
                                }
                            %>

                        </td>
                        <td>
                            <input type="text" name="<%= "txtHourPrice" + dto.getRoomId()%>" value="<%= dto.getHourPrice()%>" />
                            <%
                                if (error != null) {
                                    if (error.getHourPirceNotRightType() != null && error.getRoomId().equals(dto.getRoomId())) {
                            %>
                            <%= error.getHourPirceNotRightType()%>
                            <%
                                    }
                                }
                            %>
                        </td>
                        <td>
                            <input type="text" name="<%= "txtDayPrice" + dto.getRoomId()%>" value="<%= dto.getDayPrice()%>"/>
                            <%
                                if (error != null) {
                                    if (error.getDayPriceNotRightType() != null && error.getRoomId().equals(dto.getRoomId())) {
                            %>
                            <%= error.getDayPriceNotRightType()%>
                            <%
                                    }
                                }
                            %>
                        </td>
                        <td>
                            <input type="hidden" name="txtLastSearch" value="${param.txtRoomSearch}" />
                            <input type="submit" value="<%= "Update Room:"+dto.getRoomId()%>" name="btAction" />
                        </td>

                        <td>
                            <%
                                String nameReport = "txtReport" + dto.getRoomId();
                            %>
                            <input type="text" name="<%= nameReport%>"
                                   <%
                                       if (request.getParameter(nameReport) != null) {
                                   %>
                                   value="<%= request.getParameter(nameReport)%>"
                                   <%
                                       }
                                   %>
                                   />
                            <input type="checkbox" name="ckbReport" value="<%= dto.getRoomId()%>" />
                            <%
                                if (listError != null) {
                                    if (listError.get("ERROR" + dto.getRoomId()) != null) {
                            %>
                            <%= listError.get("ERROR" + dto.getRoomId())%>
                            <%
                                    }
                                }
                            %>
                        </td>
                    </tr>
                    <%                        }
                    %>
                </tbody>
                <input type="submit" value="Report" name="btAction" />
            </form>
        </table>

        <%
        } else {
        %>
        <h2> No Information About Room</h2>
        <%
                }
            }
        %>
    </body>
</html>
