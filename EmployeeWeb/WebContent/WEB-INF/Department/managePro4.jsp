<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="f" %>
	  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link href="bootstrap/css/bootstrap.css" rel="stylesheet">

<style type="text/css">

#div-table {
	width:700px;
	margin: 20px auto;
}
#pro,#noPro{
width:700px;
height:200px;
border:1px solid #337ab7;
border-radius:8px;
}
#btn{
width:140px;
margin: 20px auto;
}
#add{
margin-right:50px;
}
.pro,.noPro{
cursor: pointer;
background:#337ab7;
height:40px;
line-height:40px;
float:left;
margin-left:10px;
color:#fff;
padding: 0 15px;
margin-top:10px;
border-radius:6px;
}
.select{
cursor: pointer;
background:#d9534f;
}


</style>

<script type="text/javascript" src="jquery-ui-1.12.1.custom/jquery-ui.min.js"></script>
<script type="text/javascript">

 $().ready(function(){
	 $(".pro").click(function(){
		 $(this).toggleClass("select");
	 })
	 $("#add").click(function(){
		 if($("#noPro").find(".select").length>0){
			 var pid="";
			 $("#noPro").find(".select").each(function(){
				 pid+=$(this).data("id")+",";
			 })
			pid=pid.substring(0,pid.length-1);
			 
			 
			 $.ajax({
					url:"Department",  
					type:"post", 
					data:{"type":"addPro3",delId:${id },pId:pid},  
					dataType:"text",
					success:function(data){
						if(data="true"){
						var pro =$("#noPro").find(".select");
						pro.removeClass("select");
						$("#pro").append(pro);
						}
					}
				})  
			
		 }else{
			 alert("请选择数据");
		 }
	 })
	 
	 $("#delete").click(function(){
		 if($("#pro").find(".select").length>0){
			 var pid="";
			 $("#pro").find(".select").each(function(){
				 pid+=$(this).data("id")+",";
			 })
			pid=pid.substring(0,pid.length-1);
			 
			 $.ajax({
					url:"Department",  
					type:"post",  
					data:{"type":"delPro3",delId:${id },pId:pid}, 
					dataType:"text",  
					success:function(data){  
						if(data="true"){
						var pro =$("#pro").find(".select");
						pro.removeClass("select");
						$("#noPro").append(pro);
						}
					}
				})  
			
		 }else{
			 alert("请选择数据");
		 }
	 })
	 

	 
	 $("#pro .pro").draggable({
			 
		  start: function() {
	     
	      },
	      drag: function() {
	       
	      },
	      stop: function() {
	     if(($(this).offset().left>150&&$(this).offset().left<=850)&&( $(this).offset().top>=344&&$(this).offset().top<=544 )){
		   			
		   			
		   		
				 
					var pid=$(this).data("id")+",";
				
				pid=pid.substring(0,pid.length-1);
				 
				 $.ajax({
						url:"Department",  
						type:"post",  
						data:{"type":"delPro3",delId:${id },pId:pid}, 
						dataType:"text",  
						success:function(data){  
							if(data="true"){
							//	location.reload(true);
							}
							
						}
					})  
		   			
		   		}else{
		   		 $("#noPro").droppable({
		   			 accept:"#pro > .pro"
		   		 });
		   		  
		   		}
	     
	      },
	      revert:"invalid"
	    });
	 $("#noPro .pro").draggable({
		 
		  start: function() {
	     
	      },
	      drag: function() {
	       
	      },
	      stop: function() {
	    	
	          if(($(this).offset().left>150&&$(this).offset().left<=850)&&( $(this).offset().top>=70&&$(this).offset().top<=270 )){
		   			
		   		
			
				var	 pid=$(this).data("id")+",";
				 
				pid=pid.substring(0,pid.length-1);
				 
				 
				 $.ajax({
						url:"Department",  
						type:"post", 
						data:{"type":"addPro3",delId:${id },pId:pid},  
						dataType:"text",
						success:function(data){
							if(data="true"){
							//location.reload(true);    //刷新页面
							}
							
						}
					})  
		   	}else{
		   	 $("#pro").droppable({
				 accept:"#noPro > .pro"
			 })
		   	}
	      },
	      revert:"invalid"
	    });
	 
	 
	 
	 $("#noPro").droppable({
		 accept:"#pro > .pro"
	 });
	  
	 $("#pro").droppable({
		 accept:"#noPro > .pro"
	 })
	 
	 	
 })
</script>
</head>
<body>

	<div id="div-table">
		<div><h1>${depName }</h1></div>
		<div id="pro" >
		
		<c:forEach items="${list }" var ="pro">
		<div   class="pro" data-id="${pro.pId }">${pro.pName }</div>
		</c:forEach>
		
		</div>
		<div id="btn">
			<button type="button" class="btn btn-success" id="add">⇧</button>
			<button type="button" class="btn btn-danger" id="delete">⇩</button>
		</div>
		<div id="noPro"  >
		<c:forEach items="${nolist }" var ="nopro">
		<div class="pro" data-id="${nopro.pId }">${nopro.pName }</div>
		</c:forEach>
		</div>
	</div>

</body>
</html>