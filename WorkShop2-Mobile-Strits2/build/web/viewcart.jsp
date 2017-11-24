<%-- 
    Document   : viewcart
    Created on : Jun 10, 2017, 10:13:31 AM
    Author     : CungNguyen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/struts-tags" prefix="s"%>
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
        <s:if test="%{#session.CART != null}">
            <s:if test="%{#session.CART.items != null}">
                <font color="violet" size="4">
                Mrs/Miss:
                </font>
                <font color="blue" size="5">
                <s:property value="%{#session.CART.userId}"/><br/>
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
                        <s:form action="payCart" theme="simple">
                            <s:iterator var="device" value="%{#session.CART.items}" status="counter">
                                <tr>
                                    <td>
                                        <s:property value="%{#counter.count}"/>
                                    </td>

                                    <c:forTokens var="mobile" items="${device.key}" delims=",">
                                        <td>
                                            ${mobile}
                                        </td>
                                    </c:forTokens>
                                    <td>                              
                                        <s:if test="%{#device.value.quantityUser == 0}">
                                            Sorry, The device you selected is no longer available in Stock!!!
                                        </s:if>
                                        <s:if test="%{#device.value.quantityUser != 0 and #device.value.quantityinStock >= 0}">
                                            <s:property value="%{#device.value.quantityUser}"/>
                                        </s:if>
                                        <s:if test="%{#device.value.quantityinStock < 0}">
                                            Sorry, We just have 
                                            <s:property value="%{#device.value.quantityinStock + #device.value.quantityUser}"/>
                                            in Stock!!!
                                        </s:if>
                                    </td>
                                    <td>
                                        <input type="checkbox" name="chkbox" value="${device.key}"/>
                                    </td>
                                </tr> 
                            </s:iterator>>
                            <tr>
                                <td colspan="2">
                                    <s:submit value="Pay Cart"/>
                                </td>
                                <td colspan="3">
                                    <s:submit value="Remove Your Items Selected" method="RemoveItems"/>
                                </td>
                            </tr>
                        </s:form>
                    </tbody>
                </table>
            </s:if>
        </s:if>
        <s:if test="%{#session.CART == null}">
            <h2>
                You have not added anything yet!!!<br/>
                Your order is empty!!
            </h2>
        </s:if>
        <a href="user">Click here to buy more</a>
    </body>
</html>
