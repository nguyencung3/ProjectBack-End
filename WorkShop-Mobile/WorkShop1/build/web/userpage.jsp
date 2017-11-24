<%-- 
    Document   : userpage
    Created on : May 31, 2017, 8:13:54 PM
    Author     : CungNguyen
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>USER</title>
    </head>
    <body>
        <font color ="red">
        <h2>Welcome,
            ${sessionScope.ROLE.userId}
        </h2>
        </font>
        <form action="ProcessServlet">
            <input type="submit" value="Log Out" name="btAction" /><br/>
            <font color="purple" size="5">
            Search products with prices: 
            <input type="submit" value="View Your Cart" name="btAction" />
            <br/>
            </font>  From:<input type="text" name="txtFrom" value="${param.txtFrom}" size="5"/>$- To: <input type="text" name="txtTo" value="${param.txtTo}" size="5" />$ 
            <input type="submit" value="Search" name="btAction" />
        </form>
        <c:if test="${not empty requestScope.ERRORINPUT}">
            <br/>
            <font color="red">
            ${requestScope.ERRORINPUT}  
            </font>
        </c:if>
        <c:if test="${empty requestScope.ERRORINPUT}">
            <c:set var="result" value="${requestScope.RESULT}"/>
            <c:if test="${not empty param.txtFrom and not empty param.txtTo}">
                <c:if test="${not empty result}">
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>Mobile Name</th>
                                <th>Description</th>
                                <th>Year Of Production</th>
                                <th>Price</th>
                                <th>Add</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="dto" items="${result}" varStatus="counter">
                            <form action="ProcessServlet">
                                <tr>
                                    <td>
                                        ${counter.count}
                                    </td> 
                                    <td>
                                        ${dto.mobileName}
                                        <input type="hidden" name="txtmobileName" value="${dto.mobileName}" />
                                    </td> 
                                    <td>
                                        ${dto.description}
                                    </td> 
                                    <td>
                                        ${dto.yearOfProduction}
                                    </td> 
                                    <td>
                                        ${dto.price}
                                    </td> 
                                    <td>
                                        <input type="hidden" name="txtLastSearchValue" value="${param.txtFrom},${param.txtTo}"/>
                                        <input type="hidden" name="txtIDDevice" value="${dto.mobileID}" />
                                        <input type="submit" value="Add To Cart" name="btAction" />
                                    </td>
                                </tr>
                            </form>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
            <c:if test="${empty result}">
                <h2>No Record is Matched!!!</h2>
            </c:if>
        </c:if>
    </c:if>

</body>
</html>
