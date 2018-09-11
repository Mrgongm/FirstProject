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
	width:900px;
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
		
			//双击触发修改事件
				var str="";
				var tdNods =  $("tr:not(#table-head)");
			    tdNods.dblclick(tdClick);
			  //点击事件，把相应的值给input和select，然后把原来的数据清除	    
				function tdClick(){
					 var tr = $(this);
				   //方便后面使用
				     var td3 = $(this).children().eq(3);
				     var id=$(this).children().eq(0).data("id");
				     var tdAge = td3.text();
				     td3.html(""); 
				     var input3 = $("<input class='dbck' type='text'/>");		//同理
				
				     input3.attr("value",tdAge);		//同理
				  
				     td3.append(input3);
				     tr.unbind("dblclick");//6.移除tr上的点击事件
				     
				   
				
					
				}
			 	
				
				function getStr(){
					$(".dbck").parent().parent().each(function(){
						
					     var td3 = $(this).children().eq(3);
					
					     var id=$(this).children().eq(0).data("id");
					 
					
					     var tdAge = td3.children().val();
					  
					     str=str+id+","+tdAge+";";
					
					})
					str=str.substring(0,str.length-1);
				}
		
		
		
				$(".dbck").blur(function(){
						getStr();
					   $("input:not(.chec)").each(function(){
					         //将输入框的文本保存
					         var input = $(this);
					         var inputText = input.val();
					         //将td的内容，即输入框去掉,然后给td赋值
					         var td = input.parent("td");
					         td.html(inputText);
					     }); 
					 
					   $("tr:not(#table-head)").dblclick(tdClick); //让td重新拥有点击事件
					   
				
					   if(str!=""){
						   location.href="Score?type=updateTable&str="+str+"&page="+${pageVo.pageNo};
					   }else{
						   alert("没有需要修改的数据");
						 location.href="Score?pageNo="+${pageVo.pageNo}/*如果没有条语句，就会出现弹框后取不到数据的问题  */
					   }
				})
		
	
		
		$("#search-btn").click(function(){
			var name=$("#search-name").val();
			var dName=$("#search-selectDep").val();
			var pName=$("#search-selectPro").val();
		    var grade=$("#search-selectGrade").val();
			location.href="Score?type=searchSco&name="+name+"&dName="+dName+"&pName="+pName+"&grade="+grade;
		})
	})

</script>
</head>
<body>

	<!-- 搜索 -->
	<div id="div-table">
		<div><h1>绩效管理</h1></div>
		<div id="search">
			<div >
				姓名:<input  type="text" id="search-name" class="form-control" value="${condition.emp.name }"/>
			</div>
			<div >
				部门:<select id="search-selectDep" class="btn btn-default dropdown-toggle" >
				    <option>选择查找部门</option>
				<c:forEach items="${deplist}" var="dep">                                                       <!--未改  -->
				 <option <c:if test="${dep.name==condition.dep.name}">selected</c:if> >${dep.name }</option>
				</c:forEach>
				</select>
			</div>
			<div >
				项目:<select id="search-selectPro" class="btn btn-default dropdown-toggle" >
				    <option>选择查找项目</option>
				<c:forEach items="${prolist}" var="pro">                                                       <!--未改  -->
				 <option <c:if test="${pro.name==condition.pro.name}">selected</c:if> >${pro.name }</option>
				</c:forEach>
				</select>
			</div>
			
			
			
			<div >
				等级:<select id="search-selectGrade"  class="btn btn-default dropdown-toggle">
				    <option>选择查找等级</option>
			                                                    <!--未改  -->
				 <option <c:if test="${condition.grade=='优秀'}">selected</c:if> >优秀</option>
				  <option <c:if test="${condition.grade=='良好'}">selected</c:if> >良好</option>
				   <option <c:if test="${condition.grade=='一般'}">selected</c:if> >一般</option>
				    <option <c:if test="${condition.grade=='及格'}">selected</c:if> >及格</option>
				     <option <c:if test="${condition.grade=='不及格'}">selected</c:if> >不及格</option>
				
				</select>
			</div>
			<div >
			
				<button class="btn btn-info" id="search-btn">搜索</button>
			</div>

		</div> 



<div id="maintable">
		<table  class="table table-striped table-bordered table-hover">
			<tr id="table-head">
			

				<td>姓名</td>
				<td>部门</td>
				<td>项目</td>
				<td>成绩</td>
				<td>等级</td>

			</tr>
		<c:forEach items="${pageVo.records}" var="sco">
			<tr class="ttttt" >
				

				<td data-id="${sco.id }" >${sco.emp.name }</td>
				<td>${sco.dep.name }</td>
				<td>${sco.pro.name }</td>
				<td data-name="${sco.value }">${sco.value }</td>
				<td>${sco.grade }</td> 
     
			</tr> 

					</c:forEach>

		</table>
</div>
		<div>
			第${pageVo.pageNo }页,共${pageVo.totalPageSize}页
		</div>
		<nav>
		<ul class="pagination">
		<li><a href="${pageVo.url}1&name=${condition.emp.name}&dName=${condition.dep.name}&pName=${condition.pro.name}&grade=${condition.grade}" id="prev">首页</a></li>
			<li><a href="${pageVo.url}${pageVo.pageNo-1}&name=${condition.emp.name}&dName=${condition.dep.name}&pName=${condition.pro.name}&grade=${condition.grade}" id="prev">&laquo;</a></li>

	
			<c:if test="${pageVo.totalPageSize>pageVo.pagination}">
					
				<c:if test="${pageVo.pageNo>=1 &&pageVo.pageNo<=(pageVo.pagination/2+1)}">
					   <c:forEach begin="1" end="${pageVo.pagination}" varStatus="status">
						<li    <c:if test="${pageVo.pageNo==status.index}"> class="active"</c:if>><a href="${pageVo.url}${status.index}&name=${condition.emp.name}&dName=${condition.dep.name}&pName=${condition.pro.name}&grade=${condition.grade}" class="pages">${status.index }</a></li>
					</c:forEach>
				</c:if>
				
			
			<c:if test="${pageVo.pageNo>(pageVo.pagination/2+1) && pageVo.pageNo<=pageVo.totalPageSize-(pageVo.pagination/2) }">
					<c:forEach begin="${pageVo.pageNo-pageVo.pagination/2+1}" end="${pageVo.pageNo+pageVo.pagination/2 }" varStatus="status">
					
					<li      <c:if test="${pageVo.pageNo==status.index }">class="active"</c:if>><a href="${pageVo.url}${status.index }&name=${condition.emp.name}&dName=${condition.dep.name}&pName=${condition.pro.name}&grade=${condition.grade}" class="pages">${status.index }</a></li>
			</c:forEach>
			</c:if>
	
				<c:if  test="${ pageVo.pageNo>pageVo.totalPageSize-(pageVo.pagination/2) && pageVo.pageNo<=pageVo.totalPageSize}">
					<c:forEach begin="${pageVo.totalPageSize-(pageVo.pagination-1)}" end="${pageVo.totalPageSize }" varStatus="status">
						<li      <c:if test="${pageVo.pageNo==status.index }">class="active"</c:if>><a href="${pageVo.url }${status.index }&name=${condition.emp.name}&dName=${condition.dep.name}&pName=${condition.pro.name}&grade=${condition.grade}" class="pages">${status.index }</a></li>
								
					</c:forEach>
					
					
				</c:if>
			</c:if>
     <c:if test="${pageVo.totalPageSize<=pageVo.pagination}">
				<c:forEach begin="1" end="${pageVo.totalPageSize }" varStatus="status" >
				<li <c:if test="${ pageVo.pageNo==status.index}">class="active"</c:if>><a href="${pageVo.url }${status.index }&name=${condition.emp.name}&dName=${condition.dep.name}&pName=${condition.pro.name}&grade=${condition.grade}"    class="pages">${status.index }</a></li>
				</c:forEach>
				
		
</c:if>


			<li><a href="${pageVo.url}${pageVo.pageNo+1}&name=${condition.emp.name}&dName=${condition.dep.name}&pName=${condition.pro.name}&grade=${condition.grade}" id="next">&raquo;</a></li>
			<li><a href="${pageVo.url}${pageVo.totalPageSize }&name=${condition.emp.name}&dName=${condition.dep.name}&pName=${condition.pro.name}&grade=${condition.grade}" id="prev">尾页</a></li>
		</ul>
		</nav>


	</div>

</body>
</html>