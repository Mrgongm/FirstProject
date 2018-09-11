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

#div-table h1{
text-align:center;
}
#div-table .select{
	background:#337ab7;
}
#div-table #table-head{
	background:#f9f9f9
}
.leftfloat{ 
	float:left;
}
#search{
margin:20px 20px;

overflow:hidden;
}
#search div{
margin-left:30px;
float:left;
}


#search .form-control{
display:inline;
width:100px
}
#search-select{
	height:34px;
	width:220px;
	text-align:center;
}

#div-table #maintable{
height:${pageVo.pageSize*40+40}px;
}
.bottom-last{
margin-left:10px;
}
</style>


<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">

 
	$().ready(function(){
			$("#showAdd").click(function(){
				var pid= $("#search-select").val(); 
		
				//location.href="Department?type=addPro&delId=${id }&pId="+pid; 
				$.ajax({
					url:"Department",
					type:"post",
					data:{type:"addPro",delId:${id },pId:pid},
					dataType:"text",
					success:function(data){
						alert(data); 
					}
				}) 
		
			})  
	
		<c:if test="${f:length(noPro)==0}">
			$("#showAdd").unbind("click");
			$("#showAdd").addClass("disabled");
		</c:if>
			$("#showDel").click(function(){
					
				var becheckbox = "";
				var size=0;
			    $("input[name=delId]").each(function(){ //遍历table里的全部checkbox
			    	
		            if($(this).prop("checked")) //如果被选中
		            { becheckbox += $(this).data("id") + ","; 
		            	size++;
		            }//获取被选中的值
		        });
			    
			        if(becheckbox.length > 0){//如果获取到
			        	 var msg ="您确定要删除这"+size+"条数据吗？";   
						 if (confirm(msg)==true){  
			        	
			        	becheckbox = becheckbox.substring(0, becheckbox.length - 1); 
			        location.href="Department?type=delPro&delId=${id }&pId="+becheckbox;
			        //把最后一个逗号去掉
					}
			    }else{
			    	alert("未选择要删除的数据")
			    }
			});
			$("#back").click(function(){
				location.href="Department";
			});
			
			
	
	})
</script>
</head>
<body>

	
	<div id="div-table">
<div><h1>${depName }</h1></div>
		


<div id="maintable">
		<table  class="table table-striped table-bordered table-hover">
			<tr id="table-head">
				<td><input type="checkbox" id="selectAll" class="chec" />全选</td>
	             <td>id</td>
				<td>名称</td>
			

			</tr>
		<c:forEach items="${pageVo.records}" var="pro">
			<tr>
				<td><input class="chec" type="checkbox" name="delId"
					value="${pro.pId }"
					data-id="${pro.pId }" /></td>

				
				<td>${pro.pId }</td>
				<td>${pro.pName }</td>
				
				

			</tr>

					</c:forEach>

		</table>
</div>
		<div>
			第${pageVo.pageNo }页,共${pageVo.totalPageSize}页
		</div>
		<nav>
		<ul class="pagination">
		<li><a href="${pageVo.url}1&delId=${id}" id="prev">首页</a></li>
			<li><a href="${pageVo.url}${pageVo.pageNo-1}&delId=${id}" id="prev">&laquo;</a></li>

	
			<c:if test="${pageVo.totalPageSize>pageVo.pagination}">
					
				<c:if test="${pageVo.pageNo>=1 &&pageVo.pageNo<=(pageVo.pagination/2+1)}">
					   <c:forEach begin="1" end="${pageVo.pagination}" varStatus="status">
						<li    <c:if test="${pageVo.pageNo==status.index}"> class="active"</c:if>><a href="${pageVo.url}${status.index}&delId=${id}" class="pages">${status.index }</a></li>
					</c:forEach>
				</c:if>
				
			
			<c:if test="${pageVo.pageNo>(pageVo.pagination/2+1) && pageVo.pageNo<=pageVo.totalPageSize-(pageVo.pagination/2) }">
					<c:forEach begin="${pageVo.pageNo-pageVo.pagination/2+1}" end="${pageVo.pageNo+pageVo.pagination/2 }" varStatus="status">
					
					<li      <c:if test="${pageVo.pageNo==status.index }">class="active"</c:if>><a href="${pageVo.url}${status.index }&delId=${id}" class="pages">${status.index }</a></li>
			</c:forEach>
			</c:if>
	
				<c:if  test="${ pageVo.pageNo>pageVo.totalPageSize-(pageVo.pagination/2) && pageVo.pageNo<=pageVo.totalPageSize}">
					<c:forEach begin="${pageVo.totalPageSize-(pageVo.pagination-1)}" end="${pageVo.totalPageSize }" varStatus="status">
						<li      <c:if test="${pageVo.pageNo==status.index }">class="active"</c:if>><a href="${pageVo.url }${status.index }&delId=${id}" class="pages">${status.index }</a></li>
								
					</c:forEach>
					
					
				</c:if>
			</c:if>
     <c:if test="${pageVo.totalPageSize<=pageVo.pagination}">
				<c:forEach begin="1" end="${pageVo.totalPageSize }" varStatus="status" >
				<li <c:if test="${ pageVo.pageNo==status.index}">class="active"</c:if>><a href="${pageVo.url }${status.index }&delId=${id}"    class="pages">${status.index }</a></li>
				</c:forEach>
				
		
</c:if>


			<li><a href="${pageVo.url}${pageVo.pageNo+1}&delId=${id}" id="next">&raquo;</a></li>
						<li><a href="${pageVo.url}${pageVo.totalPageSize }&delId=${id}" id="prev">尾页</a></li>
			
		</ul>
		</nav>


		未添加项目:
		<select class="bottom-last" id="search-select"><c:forEach items="${noPro }" var="nopro"><option value="${nopro.pId }">${nopro.pName }</option></c:forEach></select>
		<button class="bottom-last btn btn-primary" type="button" class="btn btn-default" id="showAdd">添加</button>
		<button class="bottom-last btn btn-primary" type="button" class="btn btn-default" id="showDel">删除已选项目</button>
		<button class="bottom-last btn btn-primary" type="button" class="btn btn-default" id="back">返回</button>
	</div>

</body>
</html>