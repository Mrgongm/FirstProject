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
margin-left:30px;
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

#div-table #maintable{
height:${pageVo.pageSize*40+40}px;
}

</style>


<script type="text/javascript" src="js/jquery.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript">


	$().ready(function(){
		
		$("#showAdd").click(function(){
			location.href="Department?type=showAdd";
		});
		
			$("#showModify").click(function(){
				
				var becheckbox = "";
				var size=0;
			    $("input[name=delId]").each(function(){ //遍历table里的全部checkbox
			    	
		            if($(this).prop("checked")) //如果被选中
		            { becheckbox += $(this).data("id") + ","; 
		            	size++;
		            }//获取被选中的值
		        });
				
					        if(becheckbox.length > 0){//如果获取到
					        	becheckbox = becheckbox.substring(0, becheckbox.length - 1);//把最后一个逗号去掉
					       		location.href="Department?type=showModity&delId="+becheckbox;
					        }else{
					     	   alert("请先选择一条数据");
							}
					
			});
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
			        location.href="Department?type=delDep&delId="+becheckbox;
			        //把最后一个逗号去掉
					}
			    }else{
			    	alert("未选择要删除的数据")
			    }
			});
	
		/* $(":checkbox").click(function(){
			
			delId=$(this).data("id");
		}) */
		
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
		
				var str="";
				var tdNods =  $("tr:not(#table-head)");
			    tdNods.dblclick(tdClick);
			    
				function tdClick(){//点击事件，把相应的值给input和select，然后把原来的数据清除
					 var tr = $(this);
				  	 var td = $(this).children().eq(1);   //方便后面使用
				  	
				     
				     var id=$(this).children().eq(0).children().data("id");
				     var tdText = td.text();//保存单元格中的数据  用于赋值到input
			
				     
				   //  str=str+id+","+tdText+","+sex+","+tdAge+";";
				     
				     td.html(""); //清空单元格数据
				  
				     var input = $("<input type='text' class='dbck'/>");//定义一个待添加的input
				 
				     
				     input.attr("value",tdText);   //用attr函数为待添加的input设置value属性 并把原来的数据赋值给它
			
				  
				     td.append(input);			//将刚定义的含值的input添加到单元格中
				   	
				     tr.unbind("dblclick");//6.移除tr上的点击事件
				}
				
				function getStr(){
					$(".dbck").parent().parent().each(function(){
						var td = $(this).children().eq(1);   //方便后面使用
					 
					     var id=$(this).children().eq(0).children().data("id");
					     var tdText = td.children().val();//保存单元格中的数据  用于赋值到input
					  
					     str=str+id+","+tdText+";";
					
					})
					str=str.substring(0,str.length-1);
				}
		
		
			$("#save").click(function(){
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
				   location.href="Department?type=updateTable&str="+str+"&page="+${pageVo.pageNo};
			   }else{
				   alert("没有需要修改的数据");
				 location.href="Department?pageNo="+${pageVo.pageNo}/*如果没有条语句，就会出现弹框后取不到数据的问题  */
			   }
		})
		

		
		$("#search-btn").click(function(){
			var name=$("#search-name").val();
		
			var empCount=$("#search-empCount").val();
			location.href="Department?type=searchDep&name="+name+"&empCount="+empCount;
		})
		
		
		$("#managePro").click(function(){
			var becheckbox = "";
			var size=0;
		    $("input[name=delId]").each(function(){ //遍历table里的全部checkbox
		    	
	            if($(this).prop("checked")) //如果被选中
	            { becheckbox += $(this).data("id") + ","; 
	            	size++;
	            }//获取被选中的值
	        });
			
				        if(becheckbox.length > 0){//如果获取到
				        	becheckbox = becheckbox.substring(0, becheckbox.length - 1);//把最后一个逗号去掉
				       		location.href="Department?type=showManagePro&delId="+becheckbox;
				        }else{
				     	   alert("请先选择一条数据");
						}
		})
		
			$("#managePro2").click(function(){
			var becheckbox = "";
			var size=0;
		    $("input[name=delId]").each(function(){ //遍历table里的全部checkbox
		    	
	            if($(this).prop("checked")) //如果被选中
	            { becheckbox += $(this).data("id") + ","; 
	            	size++;
	            }//获取被选中的值
	        });
			
				        if(becheckbox.length > 0){//如果获取到
				        	becheckbox = becheckbox.substring(0, becheckbox.length - 1);//把最后一个逗号去掉
				       		location.href="Department?type=showManagePro2&delId="+becheckbox;
				        }else{
				     	   alert("请先选择一条数据");
						}
		})
			$("#managePro3").click(function(){
			var becheckbox = "";
			var size=0;
		    $("input[name=delId]").each(function(){ //遍历table里的全部checkbox
		    	
	            if($(this).prop("checked")) //如果被选中
	            { becheckbox += $(this).data("id") + ","; 
	            	size++;
	            }//获取被选中的值
	        });
			
				        if(becheckbox.length > 0){//如果获取到
				        	becheckbox = becheckbox.substring(0, becheckbox.length - 1);//把最后一个逗号去掉
				       		location.href="Department?type=showManagePro3&delId="+becheckbox;
				        }else{
				     	   alert("请先选择一条数据");
						}
		})
		
			$("#managePro4").click(function(){
			var becheckbox = "";
			var size=0;
		    $("input[name=delId]").each(function(){ //遍历table里的全部checkbox
		    	
	            if($(this).prop("checked")) //如果被选中
	            { becheckbox += $(this).data("id") + ","; 
	            	size++;
	            }//获取被选中的值
	        });
			
				        if(becheckbox.length > 0){//如果获取到
				        	becheckbox = becheckbox.substring(0, becheckbox.length - 1);//把最后一个逗号去掉
				       		
				       	 $("#myModal").modal({ 
				        		 remote:"Department?type=showManagePro4&delId="+becheckbox
				        		}); 
				        	 
				      	 /* 	 $("#myModal").html("");
				        	var url="Department?type=showManagePro3&delId="+becheckbox ;
				        	$("#myModal").load(url);
				        	$("#myModal").modal('show'); */
				        	 
				        	
				        }else{
				     	   alert("请先选择一条数据");
						}
		})
		
	})

</script>
</head>
<body>

	
	<div id="div-table">
	<div><h1>部门信息</h1></div>
		<div id="search">
			<div >
				部门:<input  type="text" id="search-name" class="form-control" value="${condition.name }"/>
			</div>
			
			<div >
				人数:<input type="text" id="search-empCount" class="form-control" value=${condition.empCount!=-1?condition.empCount:''}>
			</div>
			<div >
				<button class="btn btn-info" id="search-btn">搜索</button>
			</div>

		</div>



<div id="maintable">
		<table  class="table table-striped table-bordered table-hover">
			<tr id="table-head">
				<td><input type="checkbox" id="selectAll" class="chec" />全选</td>

				<td>名称</td>
				<td>员工数量</td>

			</tr>
		<c:forEach items="${pageVo.records}" var="dep">
			<tr>
				<td><input class="chec" type="checkbox" name="delId"
					value="${dep.id }"
					data-id="${dep.id }" /></td>

				<td>${dep.name }</td>
				
				<td>${dep.empCount }</td>

			</tr>

					</c:forEach>

		</table>
</div>
		<div>
			第${pageVo.pageNo }页,共${pageVo.totalPageSize}页
		</div>
		<nav>
		<ul class="pagination">
				<li><a href="${pageVo.url}1&name=${condition.name}&empCount=${condition.empCount!=-1?condition.empCount:''}" id="prev">首页</a></li>
		
			<li><a href="${pageVo.url}${pageVo.pageNo-1}&name=${condition.name}&empCount=${condition.empCount!=-1?condition.empCount:''}" id="prev">&laquo;</a></li>

	
			<c:if test="${pageVo.totalPageSize>pageVo.pagination}">
					
				<c:if test="${pageVo.pageNo>=1 &&pageVo.pageNo<=(pageVo.pagination/2+1)}">
					   <c:forEach begin="1" end="${pageVo.pagination}" varStatus="status">
						<li    <c:if test="${pageVo.pageNo==status.index}"> class="active"</c:if>><a href="${pageVo.url}${status.index}&name=${condition.name}&empCount=${condition.empCount!=-1?condition.empCount:''}" class="pages">${status.index }</a></li>
					</c:forEach>
				</c:if>
				
			
			<c:if test="${pageVo.pageNo>(pageVo.pagination/2+1) && pageVo.pageNo<=pageVo.totalPageSize-(pageVo.pagination/2) }">
					<c:forEach begin="${pageVo.pageNo-pageVo.pagination/2+1}" end="${pageVo.pageNo+pageVo.pagination/2 }" varStatus="status">
					
					<li      <c:if test="${pageVo.pageNo==status.index }">class="active"</c:if>><a href="${pageVo.url}${status.index }&name=${condition.name}&empCount=${condition.empCount!=-1?condition.empCount:''}" class="pages">${status.index }</a></li>
			</c:forEach>
			</c:if>
	
				<c:if  test="${ pageVo.pageNo>pageVo.totalPageSize-(pageVo.pagination/2) && pageVo.pageNo<=pageVo.totalPageSize}">
					<c:forEach begin="${pageVo.totalPageSize-(pageVo.pagination-1)}" end="${pageVo.totalPageSize }" varStatus="status">
						<li      <c:if test="${pageVo.pageNo==status.index }">class="active"</c:if>><a href="${pageVo.url }${status.index }&name=${condition.name}&empCount=${condition.empCount!=-1?condition.empCount:''}" class="pages">${status.index }</a></li>
								
					</c:forEach>
					
					
				</c:if>
			</c:if>
     <c:if test="${pageVo.totalPageSize<=pageVo.pagination}">
				<c:forEach begin="1" end="${pageVo.totalPageSize }" varStatus="status" >
				<li <c:if test="${ pageVo.pageNo==status.index}">class="active"</c:if>><a href="${pageVo.url }${status.index }&name=${condition.name}&empCount=${condition.empCount!=-1?condition.empCount:''}"    class="pages">${status.index }</a></li>
				</c:forEach>
				
		
</c:if>


			<li><a href="${pageVo.url}${pageVo.pageNo+1}&name=${condition.name}&empCount=${condition.empCount!=-1?condition.empCount:''}" id="next">&raquo;</a></li>
			<li><a href="${pageVo.url}${pageVo.totalPageSize }&name=${condition.name}&empCount=${condition.empCount!=-1?condition.empCount:''}" id="prev">尾页</a></li>
			
		</ul>
		</nav>

		<button type="button" class="btn btn-default" id="showAdd">添加</button>
		<button type="button" class="btn btn-default" id="showModify">修改</button>
		<button type="button" class="btn btn-default" id="showDel">删除</button>
		<button type="button" class="btn btn-default" id="save">保存修改</button>
		<button type="button" class="btn btn-default" id="managePro">管理项目</button>
		<button type="button" class="btn btn-default" id="managePro2">管理项目2</button>
		<button type="button" class="btn btn-default" id="managePro3">管理项目3</button>
		<button type="button" class="btn btn-default" id="managePro4">管理项目4</button>
	</div>
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
     
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
</body>
</html>