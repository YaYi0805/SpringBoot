<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product Query List</title>
<style type="text/css">
table {
	margin: auto;
	width: 70%
}

#ptitle {
	background-color: orange;
}

#productListTitle {
	margin: auto;
	font-size: 25px;
	font-weight: bold;
	text-align: center;
}
</style>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
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
	   dataType:'JSON', //送回JSON
	   contentType:'application/json', //送出JSON
	   success: function(data) { //成功呼叫function帶入data
	     var json = JSON.stringify(data, null, 4); //JSON.stringify()將data物件轉變為JSON字串, 每一資料佔 4 bytes
	     console.log("SUCCESS : ", json);
	     var parsedObjinArray = JSON.parse(json);  //JSON.parse()接收JSON字串，轉為Javascript物件陣列
	     
	     $('#showproduct').empty(""); //清空table內的資料
	     
	       if(json=null){
	         $('table').prepend("<tr><td colspan='2'>暫無資料</td></tr>");
	       }else{
	         var table = $('#showproduct');
	         table.append("<tr id='ptitle'><th>ID</th><th>Product Name</th><th>Product Price</th><th>Date</th><th>Note</th></tr>");
	         $.each(parsedObjinArray,function(i,n){
	            var tr = "<tr align='center'>" + "<td>" + n.id + "</td>" +
	                     "<td>" + n.pname + "</td>" + "<td>" + n.price + "</td>" +
	                     "<td>" + n.pdate + "</td>" + "<td>" + n.note + "</td>" +
	                     "</tr>";
	           table.append(tr);
	       });
	         
	    }
	},
		error: function() {
		    console.log("error");
		}
	});
}
</script>
</head>
<body>
	<div id="productListTitle">ProductQuery</div>
	<table id="showproduct" border="1">
	</table>
	<table id="showpage">
		<tr>
			<td>Total Pages:${totalPages} Total Records:${totalElements}</td>
			<td colspan="3" align="right">Previous 
				<c:forEach var="i" begin="1" end="${totalPages}" step="1">
				<button id="myPage" value="${i}" onclick="change(${i})">${i}</button>
				</c:forEach>Next
			</td>
		</tr>
	</table>
</body>
</html>