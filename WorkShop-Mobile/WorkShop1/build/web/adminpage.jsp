<%-- 
    Document   : adminpage
    Created on : Jun 14, 2017, 9:44:05 AM
    Author     : Nguen Cung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
    </head>
    <body>
        <h1>   Welcome, 
            <font color ="red">
            <c:set var="name" value="${sessionScope.ROLE}"/>
            <c:if test="${not empty name}">
                ${name.userId}
            </c:if>
            </font>
        </h1>

        <form action="ProcessServlet">
            <input type="submit" value="Log Out" name="btAction" /><br/>
            Search User With ID or Name: <input type="text" name="txtSearchValueByAdmin" value="${param.txtSearchValueByAdmin}"/>
            <input type="submit" value="Search" name="btAction"/>
        </form>
        <br/>

        <c:set var="searchValue" value="${param.txtSearchValueByAdmin}"/>
        <c:if test="${not empty searchValue}">
            <c:set var="result" value="${requestScope.SEARCHRESULT}"/>
            <c:if test="${not empty result}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>User ID</th>
                            <th>Password</th>
                            <th>Full Name</th>
                            <th>Role</th>
                            <th>Delete</th>
                            <th>Update</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${result}" varStatus="counter">
                        <form action="ProcessServlet">
                            <tr>
                                <c:set var="errors" value="${requestScope.ERRORUPDATE}"/>
                                <c:if test="${not empty errors}">
                                    <c:set var="id" value="${errors.userIDerror}"/>
                                </c:if>
                                <td>
                                    ${counter.count}

                                </td>
                                <td>
                                    ${dto.userId}
                                    <input type="hidden" name="txtUserId" value="${dto.userId}" />
                                </td>
                                <td>
                                    <input type="text" name="txtPassWord" value="${dto.password}" />
                                    <c:if test="${id eq dto.userId}">
                                        <c:if test="${not empty errors.passwordNotRightTypeError}">
                                            ${errors.passwordNotRightTypeError}
                                        </c:if>
                                    </c:if>
                                </td>
                                <td>
                                    <input type="text" name="txtFullname" value="${dto.fullName}" />
                                    <c:if test="${id eq dto.userId}">
                                        <c:if test="${not empty errors.fullnameLengthError}">
                                            ${errors.fullnameLengthError}
                                        </c:if>
                                    </c:if>
                                </td>
                                <td>
                                    <input type="text" name="txtRole" value="${dto.role}" />
                                    <c:if test="${id eq dto.userId}">
                                        <c:if test="${not empty errors.roleNotRightTypeError}">
                                            ${errors.roleNotRightTypeError}
                                        </c:if>
                                    </c:if>
                                </td>
                                <td>
                                    <c:url var="urlRewriting" value="ProcessServlet">
                                        <c:param name="btAction" value="Delete"/>
                                        <c:param name="pk" value="${dto.userId}"/>
                                        <c:param name="lastSearchValue" value="${param.txtSearchValueByAdmin}"/>
                                    </c:url>
                                    <a href="${urlRewriting}">Delete</a>
                                </td>
                                <td>
                                    <input type="hidden" name="lastSearchValue" value="${param.txtSearchValueByAdmin}" />
                                    <input type="submit" value="Update" name="btAction" />
                                </td>
                            </tr> 
                        </form>
                    </c:forEach>

                </tbody>
            </table>

        </c:if>
        <c:if test="${empty result}">
            <h2>
                No Record is Matched!!!
            </h2>
        </c:if>
    </c:if>
</body>

</html>
