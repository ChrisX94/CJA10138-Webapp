<%@ page import="idv.ykx.cja10138webapp.shop.model.*" %>
<%@ page import="idv.ykx.cja10138webapp.shop.service.ProdService" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%
    Product prod = (Product) request.getAttribute("product");
    request.setAttribute("prod", prod);
    List<ProdType> prodType = new ProdService().getAllTypes();
    request.setAttribute("prodType", prodType);

%>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Product</title>
</head>
<body>
<table id="table-1">
    <tr><td>
        <h3>商品新增 - addProd.jsp</h3></td><td>
        <h4><a href="product_page.jsp">回首頁</a></h4>
    </td></tr>
</table>

<h3>資料新增:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
    <font style="color:red">請修正以下錯誤:</font>
    <ul>
        <c:forEach var="message" items="${errorMsgs}">
            <li style="color:red">${message}</li>
        </c:forEach>
    </ul>
</c:if>

<c:if test="${not empty success}">
    <div style="color:green">${success}</div>
</c:if>


<FORM METHOD="post" ACTION="<%= request.getContextPath() %>/shop/shop.do" name="form1" enctype="multipart/form-data">
    <table>

        <tr>
            <td>商品名稱:</td>
            <td><input type="TEXT" name="prodName" value="商品名稱" size="45"/></td> <td>${errorMsgs.prodName}</td>
        </tr>
        <tr>
            <td>商品類型:</td>
            <td>
            <select size="1" name="prodTypeId">
                <c:forEach var="ProdType" items="${prodType}" >
                <option value="${ProdType.prodTypeId}">${ProdType.prodTypeName}
                    </c:forEach>
            </select>
            </td>
            <td>${errorMsgs.prodTypeId}</td>
        </tr>
        <tr>
            <td>商品內容:</td>
            <td><input type="TEXT" name="prodContent"   value="商品內容"  size="45"/></td> <td>${errorMsgs.prodContent}</td>
        </tr>

        <tr>
            <td>商品敘述:</td>
            <td><input type="TEXT" name="prodDesc"   value="商品敘述"  size="45"/></td> <td>${errorMsgs.prodDesc}</td>
        </tr>

        <tr>
            <td>商品價格:</td>
            <td><input type="TEXT" name="prodPrice"   value="商品價格"   size="45"/></td> <td>${errorMsgs.prodPrice}</td>
        </tr>
        <tr>
            <td>商品品牌:</td>
            <td><input type="TEXT" name="prodBrand"   value="商品品牌"   size="45"/></td> <td>${errorMsgs.prodBrand}</td>
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
    <div>
        <label for="prodPic">商品圖片 </label>
        <input id="prodPic" name="prodPic" type="file" onclick="priviewImg()" onchange="hideContent('upFiles.errors')" />
        <span id="upFiles.errors" class="error">${errorMsgs.prodPic}</span>
        <div id="blob_holder"></div>
    </div>

    <br>
    <input type="hidden" name="action" value="insert">
    <input id="submit" type="submit" value="送出新增"></FORM>


</body>
<script>
    function hideContent(d) {
        document.getElementById(d).style.display = "none";
    }

    var filereader_support = typeof FileReader != 'undefined';
    if (!filereader_support) {
        alert("No FileReader support");
    }
    acceptedTypes = {
        'image/png' : true,
        'image/jpeg' : true,
        'image/gif' : true
    };
    function priviewImg() {
        var upfile1 = document.getElementById("prodPic");
        upfile1.addEventListener("change", function(event) {
            var files = event.target.files || event.dataTransfer.files;
            for (var i = 0; i < files.length; i++) {
                previewfile(files[i])
            }
        }, false);
    }
    function previewfile(file) {
        if (filereader_support === true && acceptedTypes[file.type] === true) {
            var reader = new FileReader();
            reader.onload = function(event) {
                var image = new Image();
                image.src = event.target.result;
                image.width = 100;
                image.height = 75;
                image.border = 2;
                if (blob_holder.hasChildNodes()) {
                    blob_holder.removeChild(blob_holder.childNodes[0]);
                }
                blob_holder.appendChild(image);
            };
            reader.readAsDataURL(file);
            document.getElementById('submit').disabled = false;
        } else {
            blob_holder.innerHTML = "<div  style='text-align: left;'>" + "● filename: " + file.name
                + "<br>" + "● ContentTyp: " + file.type
                + "<br>" + "● size: " + file.size + "bytes"
                + "<br>" + "● 上傳ContentType限制: <b> <font color=red>image/png、image/jpeg、image/gif </font></b></div>";
            document.getElementById('submit').disabled = true;
        }
    }
</script>
</html>
