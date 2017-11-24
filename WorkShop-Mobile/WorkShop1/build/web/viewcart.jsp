<%-- 
    Document   : viewcart
    Created on : Jun 10, 2017, 10:13:31 AM
    Author     : CungNguyen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping Cart Page</title>
    </head>
    <body>
        <font color ="red">
        <h2>Information about Your Orders</h2>
        </font>
        <c:set var="cart" value="${sessionScope.CART}"/>
        <c:if test="${not empty cart}">
            <c:set var="listorder" value="${cart.items}"/>
            <c:if test="${not empty listorder}">
                <font color="violet" size="4">
                Mrs/Miss:
                </font>
                <font color="blue" size="5">
                ${cart.userId}<br/>
                </font>         
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>ID</th>
                            <th>Mobile Name</th>
                            <th>Quantity</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                    <form action="ProcessServlet">
                        <c:forEach var="device" items="${listorder}" varStatus="counter">
                            <tr>
                                <td>
                                    ${counter.count}
                                </td>
                                <c:forTokens var="mobile" items="${device.key}" delims=",">
                                    <td>
                                        ${mobile}
                                    </td>
                                </c:forTokens>
                                <td>                              
                                    <c:set var="quantity" value="${device.value}"/>
                                    <c:if test="${quantity.quantityUser eq 0}">
                                        Sorry, The device you selected is no longer available in Stock!!!
                                    </c:if>
                                    <c:if test="${quantity.quantityUser ne 0 and quantity.quantityinStock ge 0}">
                                        ${quantity.quantityUser}
                                    </c:if>
                                    <c:if test="${quantity.quantityinStock lt 0}">
                                        Sorry, We just have ${quantity.quantityinStock + quantity.quantityUser} in Stock!!!
                                    </c:if>
                                </td>
                                <td>
                                    <input type="checkbox" name="chkbox" value="${device.key}" />
                                </td>
                            </tr> 
                        </c:forEach>
                        <tr>
                            <td colspan="2">
                                <input type="submit" value="Pay Cart" name="btAction"/>
                            </td>
                            <td colspan="3">
                                <input type="submit" value="Remove Your Items Selected" name="btAction" />
                            </td>
                        </tr>
                    </form>
                </tbody>
            </table>
        </c:if>
    </c:if>
    <c:if test="${empty cart or empty listorder}">
        <h2>
            You have not added anything yet!!!<br/>
            Your order is empty!!
        </h2>
    </c:if>
    <a href="userpage.jsp">Click here to buy more</a>
</body>
</html>
