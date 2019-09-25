<%--
  Created by IntelliJ IDEA.
  User: Filip
  Date: 2019-09-24
  Time: 12:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <link rel="stylesheet" href="/style.css">
    <title>PRODUCT ADD</title>
</head>
<body>
<jsp:include page="navigator.jsp"/>
<form action="/productAdd" method="post">

    <input type="hidden" name="invoiceIdToAddProduct" value="${requestScope.invoiceIdAttribiute}">
    <br/>
    Name: <input type="text" name="productName">
    <br/>
    Price:<input type="number" name="productPrice" step="0.01">
    <br/>
    Stock: <input type="number" name="productStock">
    <br/>
    Tax type: <select name="taxType">
    <option value="PRODUCT">PRODUCT</option>
    <option value="SERVICES">SERVICES</option>
</select>
    <br/>
    <br/>
    <input type="submit">

</form>
</body>
</html>
