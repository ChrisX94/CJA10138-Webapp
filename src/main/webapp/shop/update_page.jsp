<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="idv.ykx.cja10138webapp.shop.model.*"%>
<%@ page import="idv.ykx.cja10138webapp.shop.service.*"%>


<%
    Product prod = (Product) request.getAttribute("product");
    request.setAttribute("prod", prod);
%>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update Product</title>
</head>
<body>
<table id="table-1">
    <tr><td>
        <h3>商品資料修改 - update_page.jsp</h3>
        <h4><a href="product_page.jsp">回首頁</a></h4>
    </td></tr>
</table>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
    <font style="color:red">請修正以下錯誤:</font>
    <ul>
        <c:forEach var="message" items="${errorMsgs}">
            <li style="color:red">${message}</li>
        </c:forEach>
    </ul>
</c:if>

<FORM METHOD="post" ACTION="<%= request.getContextPath() %>/shop/shop.do" name="form1">
    <table>
        <tr>
            <td>商品編號:<font color=red><b>*</b></font></td>
            <td>${param.prodId}</td>
        </tr>
        <tr>
            <td>商品名稱:</td>
            <td><input type="TEXT" name="prodName" value="${param.prodName}" size="45"/></td> <td>${errorMsgs.prodName}</td>
        </tr>
                <jsp:useBean id="prodService" scope="page" class="idv.ykx.cja10138webapp.shop.service.ProdService" />
                <tr>
                    <td>商品分類編號:<font color=red><b>*</b></font></td>
                    <td><select size="1" name="prodTypeId">
                        <c:forEach var="prodType" items="${prodService.allTypes}">
                        <option value="${prodType.prodTypeId}" ${(param.prodTypeId==prodType.prodTypeId)? 'selected':'' } >${prodType.prodTypeName}
                            </c:forEach>
                    </select></td>
                </tr>

        <tr>
            <td>商品內容:</td>
            <td><input type="TEXT" name="prodContent"   value="${param.prodContent}"   size="45"/></td> <td>${errorMsgs.prodContent}</td>
        </tr>

        <tr>
            <td>商品敘述:</td>
            <td><input type="TEXT" name="prodDesc"   value="${param.prodDesc}"   size="45"/></td> <td>${errorMsgs.prodDesc}</td>
        </tr>
        <tr>
            <td>商品價格:</td>
            <td><input type="TEXT" name="prodPrice"   value="${param.prodPrice}"   size="45"/></td> <td>${errorMsgs.prodPrice}</td>
        </tr>
        <tr>
            <td>商品品牌:</td>
            <td><input type="TEXT" name="prodBrand"   value="${param.prodBrand}"   size="45"/></td> <td>${errorMsgs.prodBrand}</td>
        </tr>
        <tr>
            <td>商品狀態:</td>
            <td><input type="TEXT" name="prodStatus"   value="${param.prodStatus}"   size="45"/></td>
        </tr>
        <tr>
            <td>商品狀態:</td>
            <td>
                <select size="1" name="prodStatus">
                    <option value="true">上架</option>
                    <option value="false">下架</option>
                </select>
            </td>
        </tr>


    </table>
    <br>
    <input type="hidden" name="action" value="update">
    <input type="hidden" name="prodId" value="${param.prodId}">
    <input type="submit" value="送出修改"></FORM>
</body>


</body>
</html>
