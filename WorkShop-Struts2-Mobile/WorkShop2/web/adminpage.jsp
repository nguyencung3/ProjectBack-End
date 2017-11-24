<%-- 
    Document   : adminpage
    Created on : Jul 2, 2017, 6:52:39 PM
    Author     : CungNguyen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
    </head>
    <body>
        <h1>Welcome Admin</h1>
    <s:form action="logOut" method="GET">
        <s:submit value="Log Out"/>
    </s:form>
</body>
</html>
