<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 7/30/2024
  Time: 1:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>hello</h1>

<table>
    <tr>
        <th>id</th>
        <th>name</th>
        <th>price</th>
    </tr>
    <c:forEach items="${products}" var="product">
    <tr>

            <td>${product.id}</td>
            <td>${product.name}</td>
            <td>${product.price}</td>
        <td><a href="/products?action=edit&id=${product.id}">edit</a></td>
       <td><a href="/products?action=delete&id=${product.id}">delete</a></td>
    </tr>
    </c:forEach>

</table>
<a href="/products?action=create">create</a>


</body>
</html>
