<%--
  Created by IntelliJ IDEA.
  User: Filip
  Date: 2019-09-26
  Time: 13:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <link rel="stylesheet" href="/style.css">

    <title>PRODUCTS LIST</title>
</head>
<body>
<jsp:include page="navigator.jsp"/>

<c:set var="invoiceObj" scope="page" value="${requestScope.invoiceObj}"/>
<c:set var="invoiceId" scope="request" value="${requestScope.invoiceId}"/>

<c:if test="${invoiceObj.dateOfRelease == null || (invoiceObj.dateOfPayment == null && invoiceObj.ifPaid == false)}">
    <a href="/productAdd?invoiceId=${invoiceObj.getId()}"> + Add Product</a>
</c:if>

<table width="100%">
    <tr>
        <th>Id.</th>
        <th>Name</th>
        <th>Price</th>
        <th>Stock</th>
        <th>Tax Value</th>
        <th>Tax Type</th>
    </tr>
    <c:forEach var="product" items="${requestScope.productList}">

        <tr>
            <td>${product.getId()}</td>
            <td>${product.getName()}</td>
            <td>${product.getPrice()}</td>
            <td>${product.getStock()}</td>
            <td>${product.getTaxValue()}</td>
            <td>${product.getTaxType()}</td>
            <td>${invoiceObj.getId()}</td>
            <td>
                <c:if test="${invoiceObj.dateOfRelease == null || (invoiceObj.dateOfPayment == null && invoiceObj.ifPaid == false)}">
                    <table>
                        <tr>
                            <td>
                                <a href="/productDelete?productId=${product.getId()}">Delete</a>
                            </td>
                            <td>
                                <a href="/productEdit?productId=${product.getId()}">Edit</a>
                            </td>
                        </tr>
                    </table>
                </c:if>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>