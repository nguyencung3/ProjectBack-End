<%-- 
    Document   : staffpage
    Created on : May 31, 2017, 8:15:40 PM
    Author     : CungNguyen
--%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Staff</title>
    </head>
    <body>
        <h1>Welcome ,
            <c:set var="name" value="${sessionScope.ROLE}"/>
            <c:if test="${not empty name}">
                ${name.userId}
            </c:if>
        </h1>
        <form action="ProcessServlet">
            <input type="submit" value="Log Out" name="btAction" /><br/>
            Search Device : <input type="text" name="txtSearchValueByStaff" value="${param.txtSearchValueByStaff}" />
            <input type="submit" value="Search" name="btAction" /><br/>
        </form>
        <a href="createNewMobile.html"><font color="Brown">Insert New Mobile</font></a>
            <c:set var="result" value="${requestScope.RESULT}"/>
            <c:if test="${not empty param.txtSearchValueByStaff}">
                <c:if test="${not empty result}" >
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Mobile ID</th>
                            <th>Mobile Name</th>
                            <th>Description</th>
                            <th>Price</th>
                            <th>Year Of Production</th>
                            <th>Quantity</th>
                            <th>Not Sale</th>   
                            <th>Delete</th>
                            <th>Update</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="device" items="${result}" varStatus="counter">
                        <form action="ProcessServlet">
                            <tr>
                                <c:set var="errors" value="${requestScope.ERRORUPDATE}"/>
                                <c:if test="${not empty errors}">
                                    <c:set var="id" value="${errors.mobileIDerror}"/>
                                </c:if>
                                <td>${counter.count}</td>
                                <td>
                                    ${device.mobileID}
                                    <input type="hidden" name="txtmobileID" value="${device.mobileID}" />
                                </td>
                                <td>${device.mobileName}</td>
                                <td>
                                    <input type="text" name="txtdescription" value="${device.description}" />
                                    <c:if test="${id eq device.mobileID}">
                                        <c:if test="${not empty errors.descriptionLengthError}">
                                            ${errors.descriptionLengthError}
                                        </c:if>
                                    </c:if>
                                </td>
                                <td>
                                    <input type="text" name="txtPrice" value="${device.price}" />
                                    <c:if test="${id eq device.mobileID}">
                                        <c:if test="${not empty errors.priceNotRightTypeError}">
                                            ${errors.priceNotRightTypeError}
                                        </c:if>
                                    </c:if>
                                </td>
                                <td>${device.yearOfProduction}</td>
                                <td>
                                    <input type="text" name="txtQuantity" value="${device.quantity}" />
                                    <c:if test="${id eq device.mobileID}">
                                        <c:if test="${not empty errors.quantityNotRightTypeError}">
                                            ${errors.quantityNotRightTypeError}
                                        </c:if>
                                    </c:if>
                                </td>
                                <td>
                                    <input type="checkbox" name="ckbIsNotSale" value="ON" 
                                           <c:if test="${device.notSale eq true}">
                                               checked="checked"
                                           </c:if>
                                           />
                                </td>
                                <td>
                                    <c:url  var="urlRewriting" value="ProcessServlet">
                                        <c:param name="btAction" value="Delete"/>
                                        <c:param name="pk" value="${device.mobileID}"/>
                                        <c:param name="lastsearchValue" value="${param.txtSearchValueByStaff}"/>
                                    </c:url>
                                    <a href="${urlRewriting}">Delete</a>
                                </td>
                                <td>
                                    <input type="hidden" name="txtLastSearchValue" value="${param.txtSearchValueByStaff}" />
                                    <input type="submit" value="Update" name="btAction" />
                                </td>
                            </tr>
                        </form>
                    </tbody>
                </c:forEach>
            </table>
        </c:if>
        <c:if test="${empty result}">
            <h2>No record is matched</h2>
        </c:if>
    </c:if>
</body>
</html>
