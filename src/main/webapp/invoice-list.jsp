<%--
  Created by IntelliJ IDEA.
  User: Filip
  Date: 2019-09-19
  Time: 19:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <link rel="stylesheet" href="/style.css">
    <title>INVOICE LIST</title>
</head>
<body>
<jsp:include page="navigator.jsp"/>

<table style="width: 100%">
    <tr>
        <th>Id.</th>
        <th>Date of Creation</th>
        <th>Client Name</th>
        <th>Client NIP</th>
        <th>Client Address</th>
        <th>Is Paid</th>
        <th>Date of Release</th>
        <th>Date of Payment</th>
        <th>Bill Value</th>
        <th>Amount of Product</th>
    </tr>
    <c:forEach var="invoice" items="${requestScope.invoiceList}">
        <tr>
            <td>${invoice.getId()}</td>
            <td>${invoice.getDateOfCreation()}</td>
            <td>${invoice.getClientName()}</td>
            <td>${invoice.getClientNip()}</td>
            <td>${invoice.getClientAddress()}</td>
            <td>${invoice.isIfPaid()}</td>
            <td>${invoice.getDateOfRelease()}</td>
            <td>${invoice.getDateOfPayment()}</td>
            <td>${invoice.getBillValue()}</td>
            <td>${invoice.getAmountOfProduct()}</td>
            <td>
                <table>
                    <tr>
                        <td>
                            <a href="/productList?invoiceId=${invoice.getId()}">Products</a>
                        </td>
                        <td>
                            <a href="/invoiceDelete?invoiceId=${invoice.getId()}">Delete Invoice</a>
                        </td>
                        <c:if test="${invoice.dateOfRelease == null}">
                            <td>
                                <a href="/invoiceMarkAsReleased?invoiceId=${invoice.getId()}">Set Released</a>
                            </td>
                            <td>
                                <a href="/invoiceEdit?invoiceId=${invoice.getId()}">Edit</a>
                            </td>
                        </c:if>
                        <c:if test="${invoice.dateOfPayment == null && invoice.ifPaid == false}">
                            <td>
                                <a href="/invoiceMarkAsPaid?invoiceId=${invoice.getId()}">Set Paid</a>
                            </td>
                        </c:if>
                    </tr>
                </table>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
