<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"  isELIgnored="false"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="css/style.css" />
<script src="js/jquery-3.1.0.min.js"></script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<script type="text/javascript">

$(document).ready(function(){ 
	
	$.ajax({
		type:"post",
		url:"getAddresses",
		dataType:"json",
		success:function(data){
			$.each(data,function(i,item){
				var option = $("<option>").text(item.address).val(item.address_id);
				$("#addresses").append(option);
			});
		}
	});
	
});
</script>

<body>
<form class="form-horizontal" role="form" action="saveUpdate" method="post">
    <input type="hidden" name="customer_id" value="${customer.customer_id}">
	<div class="form-group">
		<label for="firstname" class="col-sm-2 control-label">first_name</label>
		<div class="col-sm-10">
			<input type="text" class="form-control col-sm-3" id="firstname" name="first_name" value="${customer.first_name}">
		</div>
	</div>
	
	<div class="form-group">
		<label for="firstname" class="col-sm-2 control-label">last_name</label>
		<div class="col-sm-10">
			<input type="text" class="form-control col-sm-3" id="lastname" name="last_name" value="${customer.last_name}">
		</div>
	</div>
	
	<div class="form-group">
		<label for="firstname" class="col-sm-2 control-label">email</label>
		<div class="col-sm-10">
			<input type="text" class="form-control col-sm-3" id="email" name="email" value="${customer.email}">
		</div>
	</div>
	
	<div class="form-group">
		<label for="firstname" class="col-sm-2 control-label">address</label>
		<div class="col-sm-10">
			<select name="address_id" class="form-control col-sm-3" id="addresses"> 
               
           </select>
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-10">
			<button type="submit" class="btn btn-default">提交</button>
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-10">
			<a href="index" class="btn btn-default">取消</a>
		</div>
	</div>
</form>
</body>
</html>