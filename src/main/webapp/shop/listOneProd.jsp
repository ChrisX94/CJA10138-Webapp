<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="idv.ykx.cja10138webapp.shop.model.Product"%>


<%
  Product prod = (Product) request.getAttribute("product");
  request.setAttribute("prod", prod); 
%>

<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>商品資料 - listOneProd.jsp</title>

  <style>
    table#table-1 {
      background-color: #CCCCFF;
      border: 2px solid black;
      text-align: center;
    }
    table#table-1 h4 {
      color: red;
      display: block;
      margin-bottom: 1px;
    }
    h4 {
      color: blue;
      display: inline;
    }
  </style>

  <style>
    table {
      width: 100%;
      background-color: white;
      margin-top: 5px;
      margin-bottom: 5px;
    }
    table, th, td {
      border: 1px solid #CCCCFF;
    }
    th, td {
      padding: 5px;
      text-align: center;
    }
  </style>

</head>
<body bgcolor='white'>

<table id="table-1">
  <tr><td>
    <h3>商品資料 - listOneProd.jsp</h3>
    <h4><a href="product_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
  </td></tr>
</table>

<table>
  <tr>
    <th>商品編號</th>
    <th>商品名稱</th>
    <th>商品分類編號</th>
    <th>商品內容</th>
    <th>商品敘述</th>
    <th>商品價格</th>
    <th>商品品牌</th>
    <th>商品銷售數量</th>
    <th>商品評價總分數</th>
    <th>商品評價總次數</th>
    <th>商品瀏覽次數</th>
    <th>商品建立日期</th>
    <th>商品更新日期</th>
    <th>商品狀態</th>

  </tr>
  <tr>
    <td>${prod.prodId}</td>
    <td>${prod.prodName}</td>
    <td>${prod.prodType.prodTypeName}</td>
    <td>${prod.prodContent}</td>
    <td>${prod.prodDesc}</td>
    <td>${prod.prodPrice}</td>
    <td>${prod.prodBrand}</td>
    <td>${prod.prodSold}</td>
    <td>${prod.prodRateSum}</td>
    <td>${prod.prodRateCountSum}</td>
    <td>${prod.prodViews}</td>
    <td>${prod.prodCreTime}</td>
    <td>${prod.prodUpdTime}</td>
    <td>${prod.prodStatus}</td>
    <td>
    <form action="<%= request.getContextPath() %>/shop/shop.do" method="POST">
      <input type="hidden" name="prodId" value="${prod.prodId}">
      <input type="hidden" name="action" value="getOne_For_Update">
      <button type="submit">修改商品資料</button>
    </form>
    </td>
  </tr>
</table>

</body>
</html>