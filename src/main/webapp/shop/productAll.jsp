<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="idv.ykx.cja10138webapp.shop.model.*"%>
<%@ page import="idv.ykx.cja10138webapp.shop.service.*"%>

<%
    ProdService PS = new ProdService();
    List<Product> list = PS.getAllProd();
    pageContext.setAttribute("list",list);
    List<ProdType> typeList = PS.getAllTypes();
    pageContext.setAttribute("typeList", typeList);
%>



<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>All Product List</title>

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
            font-size: 10px;
            border: 1px solid #CCCCFF;
        }
        th, td {
            padding: 5px;
            text-align: center;
        }
    </style>
</head>
<body>
<c:if test="${not empty success}">
    <div style="color:green">${success}</div>
</c:if>

<table id="table-1">
    <tr><td>
        <h3>所有產品資料 - productAll.jsp</h3>

        <h4><a href="product_page.jsp">回首頁</a></h4>
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
        <th>更新</th>
        <th>刪除</th>

    </tr>
<c:forEach var="prod" items="${list}" varStatus="status">
    <tr>
        <td>${prod.prodId}</td>
        <td>${prod.prodName}</td>
        <td>${prod.prodTypeId}:
            <c:forEach var="type" items="${typeList}">
                <c:if test="${prod.prodTypeId == type.prodTypeId}">
                    ${type.prodTypeName}
                </c:if>
            </c:forEach>
        </td>
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
            <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/shop/shop.do" style="margin-bottom: 0px;">
                <input type="submit" value="修改">
                <%--  在這裡送出prodId &  getOne_For_Update --%>
                <input type="hidden" name="prodId"  value="${prod.prodId}">
                <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
        </td>
        <td>
            <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/shop/shop.do" style="margin-bottom: 0px;">
                <input type="submit" value="刪除">
                <input type="hidden" name="prodId"  value="${prod.prodId}">
                <input type="hidden" name="action"	value="delete"></FORM>
        </td>

    </tr>
</c:forEach>
</table>
</body>
</html>