<%-- 
    Document   : createNewMobile
    Created on : Jun 3, 2017, 11:09:03 AM
    Author     : CungNguyen
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Mobile</title>
        <s:head/>
    </head>
    <body>
        <font color="red">
        <h1>Mobile Information</h1>
        </font>
        <s:form action="insertMobile" method="POST">
            <s:textfield name="mobileId" label="Mobile ID"/>
            <s:textfield name="description" label="Description"/>
            <s:textfield name="price" label="Price"/>
            <s:textfield name="mobileName" label="Mobile Name"/>
            <s:textfield name="yearOfProduction" label="Year Of Production"/>
            <s:textfield name="quantity" label="Quantity"/>
            <s:checkbox name="notSale" label="Not Sale"/>
            <s:submit value="Create New Mobile"/>
            <s:reset value="Reset"/>
        </s:form>
        <s:if test="%{exception.message.contains('duplicate')}">
            <font color="red">
            <s:property value="mobileId"/> is existed!!!
            </font>
        </s:if>
        <a href="staff">Click here to return Search Page</a>
    </body>

</html>
