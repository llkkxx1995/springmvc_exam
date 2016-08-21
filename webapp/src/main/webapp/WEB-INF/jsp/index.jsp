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
<body>
<ul class="nav nav-tabs col-md-5">
  <li role="presentation" class="active"><a href="#">Customer管理</a></li>
  <li role="presentation"><a href="#">Film设置</a></li>
  <li role="presentation"><a href="logOut" class="btn btn-danger" id="load">退出系统</a></li>
    <li role="presentation"><a href="insert" class="btn btn-success">新建用户</a></li>
</ul>

<table class="table table-hover">
   <thead>
   <tr>
       <th>操作</th>
      <th>first_name</th>
      <th>last_name</th>
      <th>address</th>
      <th>email</th>
      <th>customer_id</th>
      <th>last_update</th>
   </tr>      
   </thead >
   <tbody  id="info">
      
   </tbody>
</table>

<nav>
  <ul class="pagination" id="pages">
  
  </ul>
</nav>


<script type="text/javascript">

$(document).ready(function(){ 

	 $.ajax({
		   type:"post",
		   url:"getCustomers",
		   data:{page:'${pageIndex!=null ? pageIndex : 1}'},
		   dataType:"json",
		   success:function(data,status){
			   listCustomer(data);
		   }
	   });
	   
	   
	   $.ajax({
		   type:"post",
		   url:"getPages",
		   dataType:"json",
		   success:function(data,status){
			   listPage(data);
		   }
	   });
	   
	   
	   
	   function listCustomer(data){
		   $("#info").empty();
		   $.each(data,function(i,item){
				var option =  "<tr><td><a class='edit' id='"+item.customer_id+"' href='editItem?customer_id="+item.customer_id+"'>编辑</a>|<a class='delete' id='"+item.customer_id+"'>删除</a></td>"+
				              "<td>"+item.first_name+"</td>"+
				              "<td>"+item.last_name+"</td>"+
				              "<td>"+item.address+"</td>"+
				              "<td>"+item.email+"</td>"+
				              "<td>"+item.customer_id+"</td>"+
				              "<td>"+new Date(item.last_update)+"</td></tr>"   
			     $("#info").append(option);
			});
		   
		   
		   $(".delete").bind("click",function(event){
			   event.preventDefault();
			   if(window.confirm('你确定要删除吗？')){
				   $.ajax({
		               type:"post",
		               url:"deleteCustomer",
		               data:{customer_id:$(this).attr('id')},
		               dataType:"json",
		               success:function(data){
		            	   if(data.result){
		            		   alert("删除成功");
				                  $.ajax({
				           		   type:"post",
				           		   url:"getCustomers",
				           		   data:{page:data.page},
				           		   dataType:"json",
				           		   success:function(data,status){
				           			   listCustomer(data);
				           		   }
				           	   });
		            	   }else{
		            		   alert("此数据与外表相关联，无法删除");
		            	   }
		             }  
		           });
	            };
		   });
		   
	   };
	   
	   
	   function listPage(data){
		   for(var i=1; i<=data; i++){
			  var item =  "<li><a class='changePage'>"+i+"</a></li>";
			  $("#pages").append(item);
		   }
		   
		   $(".changePage").bind("click",function(event){
	           event.preventDefault();
	           $.ajax({
	               type:"post",
	               url:"getCustomers",
	               data:{page:$(this).text()},
	               dataType:"json",
	               success:function(data,status){
	                   listCustomer(data);
	               }
	           })
	       });
		   
	   };
	   
	  

}); 

</script>

<div class="modal fade" id="myModal" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" 
               data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
               模态框（Modal）标题
            </h4>
         </div>
         <div class="modal-body">
            在这里添加一些文本
         </div>
         <div class="modal-footer">
            <button type="button" class="btn btn-default" 
               data-dismiss="modal">关闭
            </button>
            <button type="button" class="btn btn-primary">
               提交更改
            </button>
         </div>
      </div><!-- /.modal-content -->
</div><!-- /.modal -->
</div>
</body>
</html>