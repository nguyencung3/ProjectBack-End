<%-- 
    Document   : createNewAccount
    Created on : May 31, 2017, 4:12:45 PM
    Author     : CungNguyen
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>New Account</title>
    </head>
    <body>
        <h1>Create New Account</h1>
        <form action="ProcessServlet" method="POST">
            <c:set var="errors" value="${requestScope.CREATEERROR}" />
            Username : <input type="text" name="txtUserId" value="${param.txtUserId}" />(3-15 chars)<br/>
            <c:if test="${not empty errors.userNameLengthError}">
                <font color="red">
                ${errors.userNameLengthError}
                <br/>
                </font>         
            </c:if>
            PassWord : <input type="password" name="txtPassWord" value="${param.txtPassWord}" />(3-15 digit)<br/>
            <c:if test="${not empty errors.passwordNotRightTypeError}">
                <font color="red">
                ${errors.passwordNotRightTypeError}
                <br/>
                </font>         
            </c:if>
            Confirm : <input type="password" name="txtConfirm" value="" /><br/>
            <c:if test="${not empty errors.confirmNotMatch}">
                <font color="red">
                ${errors.confirmNotMatch}
                <br/>
                </font>         
            </c:if>
            Full Name : <input type="text" name="txtFullname" value="" />(5-15 chars)<br/>
            <c:if test="${not empty errors.fullnameLengthError}">
                <font color="red">
                ${errors.fullnameLengthError}
                <br/>
                </font>         
            </c:if>
            <input type="submit" value="Create New Account" name="btAction" />
            <input type="reset" value="reset" />
        </form>
        <c:if test="${not empty errors.usernameIsExisted}">
            <font color="red">
            ${errors.usernameIsExisted}
            </font>         
        </c:if>
        </font>
    </body>
</html>
