<%-- 
    Document   : createNewMobile
    Created on : Jun 3, 2017, 11:09:03 AM
    Author     : CungNguyen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <font color="red">
        <h1>Mobile Information</h1>
        </font>
        <c:set var="errors" value="${requestScope.ERRORINSERT}"/>
        <form action="ProcessServlet">
            Mobile ID  : <input type="text" name="txtmobileID" value="${param.txtmobileID}" /><br/>
            <c:if test="${not empty errors}">
                <c:if test="${not empty errors.mobileIDlengthError}">
                    <font color="red">
                    ${errors.mobileIDlengthError}
                    <br/>
                    </font>
                </c:if>
            </c:if>
            Description: <input type="text" name="txtDescription" value="${param.txtDescription}" /><br/>
            <c:if test="${not empty errors}">
                <c:if test="${not empty errors.descriptionLengthError}">
                    <font color="red">
                    ${errors.descriptionLengthError}
                    <br/>
                    </font>
                </c:if>
            </c:if>
            Price      : <input type="text" name="txtPrice" value="${param.txtPrice}" /><br/>
            <c:if test="${not empty errors}">
                <c:if test="${not empty errors.priceNotRightTypeError}">
                    <font color="red">
                    ${errors.priceNotRightTypeError}
                    <br/>
                    </font>
                </c:if>
            </c:if>
            Mobile Name: <input type="text" name="txtMobilename" value="${param.txtMobilename}" />(3-20chars)<br/>
            <c:if test="${not empty errors}">
                <c:if test="${not empty errors.mobileNameLengthError}">
                    <font color="red">
                    ${errors.mobileNameLengthError}
                    <br/>
                    </font>
                </c:if>
            </c:if>
            Year Of    : <input type="text" name="txtyearOfProduction" value="${param.txtyearOfProduction}" />(Number(2000-2017))<br/>
            Production <br/>
            <c:if test="${not empty errors}">
                <c:if test="${not empty errors.yearNotRightTypeError}">
                    <font color="red">
                    ${errors.yearNotRightTypeError}
                    <br/>
                    </font>
                </c:if>
            </c:if>
            Quantity   : <input type="text" name="txtQuantity" value="${param.txtQuantity}" />(Number>0)<br/>
            <c:if test="${not empty errors}">
                <c:if test="${not empty errors.priceNotRightTypeError}">
                    <font color="red">
                    ${errors.priceNotRightTypeError}
                    <br/>
                    </font>
                </c:if>
            </c:if>
            Not sale   : <input type="checkbox" name="ckbNotsale" value="ON" /><br/>
            <input type="submit" value="Insert New Mobile" name="btAction"/>
        </form>
        <c:if test="${not empty errors}">
            <c:if test="${not empty errors.mobileIDExisted}">
                <font color="red">
                ${errors.mobileIDExisted}
                <br/>
                </font>
            </c:if>
        </c:if>
    </body>
</html>
