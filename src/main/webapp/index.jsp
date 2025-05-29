<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Home Page" %>
</h1>
<br>
<a href="${pageContext.request.contextPath}/login/login.jsp">Login</a>
<br/>
<a href="hello-servlet">Hello Servlet</a>
<br>
<a href="${pageContext.request.contextPath}/shop/product_page.jsp">Product</a>
<br>
<a href="${pageContext.request.contextPath}/shop/addProd.jsp">Add Product</a>
<br>
<a href='${pageContext.request.contextPath}/shop/productAll.jsp'> List All Products. </a>
</body>
</html>