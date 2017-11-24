<%-- 
    Document   : staffpage
    Created on : Jul 2, 2017, 6:52:24 PM
    Author     : CungNguyen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>STAFF</title>
        <s:head/>
    </head>
    <body>
        <h2>
            <font color="red">
            Welcome,
            <s:property value="%{#session.USERNAME}"/>
            </font>
        </h2>
        <s:form action="logOut" method="GET">
            <s:submit value="Log Out"/>
        </s:form>
        <s:form action="searchByStaff" method="GET">
            <s:textfield name="searchValue" label="Search Device By Id or Name"/>
            <s:submit value="Search"/>
        </s:form>
        <s:if test="%{searchValue != null and searchValue != ''}">
            <s:if test="%{listDevice != null}">
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
                        <s:iterator var="device" value="listDevice" status="counter" >
                            <s:form action="updateDeviceByStaff" theme="simple" method="GET">
                                <tr>
                                    <s:set name="errors" value="%{#request.ERRORUPDATE}"/>
                                    <s:if test="%{#errors != null}">
                                        <s:set name="id" value="%{#errors.mobileIDerror}"/>
                                    </s:if>
                                    <td>
                                        <s:property value="%{#counter.count}"/>
                                    </td>
                                    <td>
                                        <s:property value="%{#device.mobileID}"/>
                                        <s:textfield type="hidden" name="mobileId" value="%{#device.mobileID}"/>
                                    </td>
                                    <td>
                                        <s:property value="%{#device.mobileName}"/>
                                    </td>
                                    <td>
                                        <s:textfield name="description" value="%{#device.description}"/>
                                        <s:if test="%{#id == #device.mobileID}">
                                            <s:property value="%{#errors.descriptionLengthError}"/>
                                        </s:if>
                                    </td>
                                    <td>
                                        <s:textfield name="price" value="%{#device.price}"/>
                                        <s:if test="%{#id == #device.mobileID}">
                                            <s:property value="%{#errors.priceNotRightTypeError}"/>
                                        </s:if>
                                    </td>
                                    <td>
                                        <s:property value="%{#device.yearOfProduction}"/>
                                    </td>
                                    <td>
                                        <s:textfield name="quantity" value="%{#device.quantity}"/>
                                        <s:if test="%{#id == #device.mobileID}">
                                            <s:property value="%{#errors.quantityNotRightTypeError}"/>
                                        </s:if>
                                    </td>
                                    <td>
                                        <s:checkbox name="notSale" value="%{#device.notSale}"/>
                                    </td>
                                    <td>
                                        <s:url var="linkDel" value="DeleteRecord">
                                            <s:param name="pk" value="%{#device.mobileID}"/>
                                            <s:param name="lastSearchValue" value="%{searchValue}"/>
                                        </s:url>
                                        <s:a href="%{linkDel}">Delete</s:a>
                                        </td>
                                        <td>
                                        <s:hidden name="searchValue" value="%{searchValue}"/>
                                        <s:submit value="Update"/>
                                    </td>
                                </tr>
                            </s:form>
                        </s:iterator>
                    </tbody>
                </table>
            </s:if>
            <s:if test="%{listDevice==null}">
                <h2>No Record matched!!!</h2>
            </s:if>              
        </s:if>
        <a href="signUp">Insert New Mobile</a>
    </body>
</html>
