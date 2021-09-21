<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PurchaseOrderProductList</title>
<style type="text/css">
table { 
	margin: auto; width: 70% 
}
#ptitle { 
	background-color: orange; 
}
#productListTitle{
	margin: auto;
	font-size: 25px ;
	font-weight: bold;
	text-align: center;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript">
var indexPage = 1;
$(document).ready(function(){
load(indexPage);
});
function change(page){
indexPage = page;
load(indexPage);
}
function load(indexPage){
$.ajax({
type:'post',
url:'/product/queryallbypage/' + indexPage,
dataType:'JSON',
contentType:'application/json',
success: function(data) {
var json = JSON.stringify(data, null, 4);
console.log("SUCCESS : ", json);
var parsedObjinArray = JSON.parse(json);
$('#showproduct').empty("");
if(json=null){
$('table').prepend("<tr><td colspan='2'>暫無資料</td></tr>");
}else{
var table = $('#showproduct');
table.append("<tr id='ptitle'><th>ID</th><th>Product Name</th><th>Product Price</th><th>Note</th></tr>");
$.each(parsedObjinArray,function(i,n){
var tr = "<tr align='center'>" +
"<td><a href='/order/purchaseOrderProduct.controller?pid=" + n.id + "'>" + n.id +
"</a></td>" +
"<td>" + n.pname + "</td>" +
"<td>" + n.price + "</td>" +
"<td>" + n.note + "</td>" +
"</tr>";
table.append(tr);
});
}
},
error: function() {
//alert('error');
console.log("error");
}
});
}
</script>
</head>
<body>
	<div id="productListTitle">PurchaseOrderSystem</div>
	<table id="showproduct" border="1">
	</table>
	<table id="showpage">
		<tr>
			<td>Total Pages:${totalPages} TotalRecords:${totalElements}</td>
			<td colspan="3" align="right">Previous
			<c:forEach var="i" begin="1" end="${totalPages}" step="1">
			<button id="myPage" type="button" value="${i}" onclick="change(${i})">${i}</button>
			</c:forEach>Next
			</td>
		</tr>
	</table>
</body>
</html>