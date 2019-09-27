<%--
  Created by IntelliJ IDEA.
  User: Filip
  Date: 2019-09-19
  Time: 19:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <link rel="stylesheet" href="style.css">
    <title>INVOICE ADD</title>
</head>
<body>
<jsp:include page="navigator.jsp"/>

<form action="${requestScope.invoiceId == null ? '/invoiceAdd' : '/invoiceEdit'}" method="post">
    <input type="hidden" name="invoiceId" value="${requestScope.invoiceId}">
    <input type="hidden" name="dateOfCreation" value="${requestScope.dateOfCreation}">
    <br/>
    Client Name: <input type="text" name="clientName" value="${requestScope.invoiceClientName}">
    <br/>
    Client Nip: <input type="text" name="clientNip" value="${requestScope.invoiceClientNip}" maxlength="10"
                       minlength="10">
    <br/>
    Client Address: <input type="text" name="clientAddress" value="${requestScope.invoiceClientAddress}">
    <br/>
    Dodaj: <input type="submit">
</form>
</body>
</html>
