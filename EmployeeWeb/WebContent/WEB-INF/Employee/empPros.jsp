<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	  
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
margin-left:10px;
float:left;
}


#search .form-control{
display:inline;
width:100px
}
#search-select{
	height:34px;
	width:100px;
	text-align:center;
}
#search-selectdep{
	height:34px;
	width:100px;
	text-align:center;
}
#search-btn{
	margin-left:5px;
}
#div-table #maintable{
height:${pageVo.pageSize*40+40}px;
}

</style>

<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">

	$().ready(function(){
		
		
		$("#selectAll").click(function() {
			var ifcheck = this.checked;
			$("input[name='delId']").each(function() {
				this.checked = ifcheck;
				$(this).parent().parent().toggleClass("select");
			});

		})
		var allcheck;
		$("input[name='delId']").click(function() {
			allcheck = true;
			$("input[name='delId']").each(function() {
				allcheck = allcheck && this.checked;
			});
			$("#selectAll").prop("checked",allcheck)
		})
		
		 $(":checkbox").click(function(){
			$(this).parent().parent().toggleClass("select");
		})
		

		
		
	/*	$(".pages").click(function(){
			location.href="Employee?type=getPage&page="+$(this).html();
		})
			$("#prev").click(function(){
				if($(this).html()-1>0){
					location.href="Employee?type=getPage&page="+$(this).html()-1;
				}
		})
			$("#next").click(function(){
			if($(this).html()+1<6){
				location.href="Employee?type=getPage&page="+$(this).html()+1;
			}
		})*/
		
		$("#search-btn").click(function(){
			var name=$("#search-name").val();
			var sex=$("#search-select").val();
			var age=$("#search-age").val();
		    var dep=$("#search-selectdep").val();
			location.href="Employee?type=empPro&name="+name+"&sex="+sex+"&age="+age+"&dep="+dep;
		})
	})

</script>
</head>
<body>

	
	<div id="div-table">
		<div><h1>员工项目管理</h1></div>
		<div id="search">
			<div >
				姓名:<input  type="text" id="search-name" class="form-control" value="${condition.name }"/>
			</div>
			<div >
				性别:<select id="search-select" class="btn btn-default dropdown-toggle" >
				    <option>请选择性别</option>
					<option value="男" <c:if test="${condition.sex=='男' }">selected</c:if> >男</option>
					<option value="女"  <c:if test="${condition.sex=='女' }">selected</c:if>>女</option>
				</select>
			</div>
			<div >
				年龄:<input type="text" id="search-age" class="form-control" value=${condition.age!=-1?condition.age:'' }>
			</div>
			<div >
			
			
			<div >
				部门:<select id="search-selectdep" class="btn btn-default dropdown-toggle">
				    <option>请选择部门</option>
				<c:forEach items="${deplist}" var="dep">                                                       <!--未改  -->
				 <option <c:if test="${dep.id==condition.dep.id}">selected</c:if> >${dep.name }</option>
				</c:forEach>
				</select>
			</div>
			
			
				<button class="btn btn-info" id="search-btn">搜索</button>
			</div>

		</div>



<div id="maintable">
		<table  class="table table-striped table-bordered table-hover">
			<tr id="table-head">
				<td><input type="checkbox" id="selectAll" class="chec" />全选</td>

				<td>姓名</td>
				<td>性别</td>
				<td>年龄</td>
				<td>部门</td>

			</tr>
		<c:forEach items="${pageVo.records}" var="emp">
			<tr class="ttttt" >
				<td><input class="chec" type="checkbox" name="delId"
					value="${emp.id }"
					data-id="${emp.id }" /></td>

				<td>${emp.name }</td>
				<td>${emp.sex }</td>
				<td>${emp.age }</td>
				<td data-name="${emp.dep.name }">${emp.dep.name }</td>

			</tr>

					</c:forEach>

		</table>
</div>
		<div>
			第${pageVo.pageNo }页,共${pageVo.totalPageSize}页
		</div>
		<nav>
		<ul class="pagination">
		<li><a href="${pageVo.url}1&name=${condition.name}&sex=${condition.sex}&age=${condition.age!=-1?condition.age:''}&dep=${condition.dep.name}" id="prev">首页</a></li>
			<li><a href="${pageVo.url}${pageVo.pageNo-1}&name=${condition.name}&sex=${condition.sex}&age=${condition.age!=-1?condition.age:''}&dep=${condition.dep.name}" id="prev">&laquo;</a></li>

	
			<c:if test="${pageVo.totalPageSize>pageVo.pagination}">
					
				<c:if test="${pageVo.pageNo>=1 &&pageVo.pageNo<=(pageVo.pagination/2+1)}">
					   <c:forEach begin="1" end="${pageVo.pagination}" varStatus="status">
						<li    <c:if test="${pageVo.pageNo==status.index}"> class="active"</c:if>><a href="${pageVo.url}${status.index}&name=${condition.name}&sex=${condition.sex}&age=${condition.age!=-1?condition.age:''}&dep=${condition.dep.name}" class="pages">${status.index }</a></li>
					</c:forEach>
				</c:if>
				
			
			<c:if test="${pageVo.pageNo>(pageVo.pagination/2+1) && pageVo.pageNo<=pageVo.totalPageSize-(pageVo.pagination/2) }">
					<c:forEach begin="${pageVo.pageNo-pageVo.pagination/2+1}" end="${pageVo.pageNo+pageVo.pagination/2 }" varStatus="status">
					
					<li      <c:if test="${pageVo.pageNo==status.index }">class="active"</c:if>><a href="${pageVo.url}${status.index }&name=${condition.name}&sex=${condition.sex}&age=${condition.age!=-1?condition.age:''}&dep=${condition.dep.name}" class="pages">${status.index }</a></li>
			</c:forEach>
			</c:if>
	
				<c:if  test="${ pageVo.pageNo>pageVo.totalPageSize-(pageVo.pagination/2) && pageVo.pageNo<=pageVo.totalPageSize}">
					<c:forEach begin="${pageVo.totalPageSize-(pageVo.pagination-1)}" end="${pageVo.totalPageSize }" varStatus="status">
						<li      <c:if test="${pageVo.pageNo==status.index }">class="active"</c:if>><a href="${pageVo.url }${status.index }&name=${condition.name}&sex=${condition.sex}&age=${condition.age!=-1?condition.age:''}&dep=${condition.dep.name}" class="pages">${status.index }</a></li>
								
					</c:forEach>
					
					
				</c:if>
			</c:if>
     <c:if test="${pageVo.totalPageSize<=pageVo.pagination}">
				<c:forEach begin="1" end="${pageVo.totalPageSize }" varStatus="status" >
				<li <c:if test="${ pageVo.pageNo==status.index}">class="active"</c:if>><a href="${pageVo.url }${status.index }&name=${condition.name}&sex=${condition.sex}&age=${condition.age!=-1?condition.age:''}&dep=${condition.dep.name}"    class="pages">${status.index }</a></li>
				</c:forEach>
				
		
</c:if>

			<li><a href="${pageVo.url}${pageVo.pageNo+1}&name=${condition.name}&sex=${condition.sex}&age=${condition.age!=-1?condition.age:''}&dep=${condition.dep.name}" id="next">&raquo;</a></li>
			<li><a href="${pageVo.url}${pageVo.totalPageSize }&name=${condition.name}&sex=${condition.sex}&age=${condition.age!=-1?condition.age:''}&dep=${condition.dep.name}" id="prev">尾页</a></li>
		</ul>
		</nav>

		<button type="button" class="btn btn-default" id="save">管理项目</button>
	</div>

</body>
</html>