<%-- 
    Document   : errorpage
    Created on : Jun 15, 2017, 2:59:19 PM
    Author     : Nguen Cung
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error Page</title>
    </head>
    <body>
        <h1>
            <font color="red">
            <c:set var="error" value="${requestScope.ERRORPAGE}"/>    
            <c:if test="${not empty error}">
                ${requestScope.ERRORPAGE}
            </c:if>
            </font>
        </h1>
    </body>
</html>
