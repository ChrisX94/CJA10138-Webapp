<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product: Home</title>
</head>

<style>
    table#table-1 {
        width: 100%;
        background-color: #CCCCFF;
        margin-top: 5px;
        margin-bottom: 10px;
        border: 3px ridge Gray;
        height: 80px;
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

<body>


<table id="table-1">
    <tr>
        <td><h3>ShakeMate Product: Home</h3><h4>( MVC )</h4></td>
    </tr>
</table>

<p>Home page for ShakeMate Product: Home</p>

<h3>資料查詢:</h3>

<%-- 錯誤表列--%>
<c:if test="${not empty errorMsgs}">
    <font style="color:red">請修正以下錯誤:</font>
    <ul>
        <c:forEach var="message" items="${errorMsgs}">
            <li style="color:red">${message}</li>
        </c:forEach>
    </ul>
</c:if>

<ul>
    <li><a href='productAll.jsp'>List All Products. </a> <br><br></li>


    <li>
        <FORM METHOD="post" ACTION="<%= request.getContextPath() %>/shop/shop.do">
            <b>輸入商品編號 (如1):</b>
            <input type="text" name="prodId">
            <input type="hidden" name="action" value="getOne_For_Display">
            <input type="submit" value="送出">
        </FORM>
    </li>

        <jsp:useBean id="ProdService" scope="page" class="idv.ykx.cja10138webapp.shop.service.ProdService" />

        <li>
            <FORM METHOD="post" ACTION="<%= request.getContextPath() %>/shop/shop.do" >
                <b>選擇產品:</b>
                <select size="1" name="prodId">
                    <c:forEach var="ProdVO" items="${ProdService.allProd}" >
                    <option value="${ProdVO.prodId}">${ProdVO.prodId}
                        </c:forEach>
                </select>
                <input type="hidden" name="action" value="getOne_For_Display">
                <input type="submit" value="送出">
            </FORM>
        </li>

        <li>
            <FORM METHOD="post" ACTION="<%= request.getContextPath() %>/shop/shop.do"  >
                <b>選擇商品:</b>
                <select size="1" name="prodId">
                    <c:forEach var="ProdVO" items="${ProdService.allProd}" >
                    <option value="${ProdVO.prodId}">${ProdVO.prodName}
                        </c:forEach>
                </select>
                <input type="hidden" name="action" value="getOne_For_Display">
                <input type="submit" value="送出">
            </FORM>
        </li>
    </ul>


    <h3>商品管理</h3>

    <ul>
        <li><a href='addProd.jsp'>Add</a> a new Product.</li>
</ul>


</body>
</html>
