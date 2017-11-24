<%-- 
    Document   : userpage
    Created on : Jul 2, 2017, 6:52:03 PM
    Author     : CungNguyen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User</title>
        <s:head/>
    </head>
    <body>
        <font color ="red">
        <h2>Welcome,
            <s:property value="%{#session.USERNAME}"/>
        </h2>
        <s:form action="logOut" method="GET">
            <s:submit value="Log Out"/>
        </s:form>
        </font>
        <font color="purple" size="5">
        Search products with prices: 
        <a href="viewcart.jsp">View Your Cart</a>
        </font>  
        <s:form action="searchByUser" method="GET">
            <s:textfield name="from" value="%{#parameters.from}" size="5" label="From"/>
            <s:textfield name="to" value="%{#parameters.to}" size="5" label="To"/>
            <s:submit value="Search"/>
        </s:form>
        <s:if test="%{!from.isEmpty() and !to.isEmpty()}">
            <s:if test="%{list!=null}">
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
                        <s:iterator var="dto" value="list" status="counter">
                            <s:form action="addToCart" theme="simple" method="GET">
                                <tr>
                                    <td>
                                        <s:property value="%{#counter.count}"/>
                                    </td> 
                                    <td>
                                        <s:property value="%{#dto.mobileName}"/>
                                        <s:hidden name="mobileName" value="%{#dto.mobileName}" />
                                    </td> 
                                    <td>
                                        <s:property value="%{#dto.description}"/>
                                    </td> 
                                    <td>
                                        <s:property value="%{#dto.yearOfProduction}"/>
                                    </td> 
                                    <td>
                                        <s:property value="%{#dto.price}"/>
                                    </td> 
                                    <td>
                                        <s:hidden name="lastFrom" value="%{#parameters.from}"/>
                                        <s:hidden name="lastTo" value="%{#parameters.to}" />
                                        <s:hidden name="mobileId" value="%{#dto.mobileID}" />
                                        <s:submit value="Add To Cart"/>
                                    </td>
                                </tr>
                            </s:form>
                        </s:iterator>
                    </tbody>
                </table>
            </s:if>
            <s:if test="%{list==null}">
                <h2>No Record is Matched!!!</h2>
            </s:if>
        </s:if>

    </body>
</html>
