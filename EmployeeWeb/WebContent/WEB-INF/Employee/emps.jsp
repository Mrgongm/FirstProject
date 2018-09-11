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
height:${pageVo.pageSize*50+40}px;
}
.empImg{
width:30px;
height:30px;
transition:all 0.6s;
}
.empImg:hover{
transform:scale(10);
}
</style>

<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">

	$().ready(function(){
		
		$("#showAdd").click(function(){
			location.href="Employee?type=showAdd";
		});
		$("#showAdd2").click(function(){
			location.href="Employee?type=showAdd2";
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
					       		location.href="Employee?type=showModity&delId="+becheckbox;
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
			        location.href="Employee?type=delEmp&delId="+becheckbox;
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
		
		
		
			//双击触发修改事件
				var str="";
				var tdNods =  $("tr:not(#table-head)");
			    tdNods.dblclick(tdClick);
			  //点击事件，把相应的值给input和select，然后把原来的数据清除	    
				function tdClick(){
					 var tr = $(this);
				  	 var td = $(this).children().eq(1);   //方便后面使用
				  	 var td2 = $(this).children().eq(2);
				     var td3 = $(this).children().eq(3);
				     var td4 = $(this).children().eq(4);
				     
				     var id=$(this).children().eq(0).children().data("id");
				     var tdText = td.text();//保存单元格中的数据  用于赋值到input
				     var sex = td2.text();    
				     var tdSex="";
				   if(sex=="男"){ //判断原来数据是男，则下拉框中那个为女
					   tdSex="女";
				   }else{
					   tdSex="男";
				   }
				     var tdAge = td3.text();
				     var tdDep = td4.text();
				   //  str=str+id+","+tdText+","+sex+","+tdAge+";";
				     
				     td.html(""); //清空单元格数据
				     td2.html("");
				     td3.html(""); 
				     td4.html("");
				     
				     var selectDepName=   $(this).children().eq(4).data("name");
			
				     var input = $("<input type='text' class='dbck'/>");//定义一个待添加的input
				     var select= $("<select></select>")	   //定义一个待添加的select
				     var option=$("<option>"+sex+"</option><option>"+tdSex+"</option>") //定义一个select中的option
				     var input3 = $("<input type='text'/>");		//同理
				     var select4 = $("<select></select>");
				     var option4=$("<option>"+selectDepName+"</option>")
				     
				     
				     input.attr("value",tdText);   //用attr函数为待添加的input设置value属性 并把原来的数据赋值给它
				     select.html(option);			//用html函数把select的值  也就是子元素 option添加到select中
				     input3.attr("value",tdAge);		//同理
				     select4.html(option4)
				     
				     td.append(input);			//将刚定义的含值的input添加到单元格中
				     td2.append(select);		//同理添加select
				     td3.append(input3);
				     td4.append(select4);        //同理
				     tr.unbind("dblclick");//6.移除tr上的点击事件
				}
				
				function getStr(){
					$(".dbck").parent().parent().each(function(){
						var td = $(this).children().eq(1);   //方便后面使用
					  	 var td2 = $(this).children().eq(2);
					     var td3 = $(this).children().eq(3);
					     var td4 = $(this).children().eq(4);
					     var id=$(this).children().eq(0).children().data("id");
					     var tdText = td.children().val();//保存单元格中的数据  用于赋值到input
					     var sex = td2.children().val();
					     var tdAge = td3.children().val();
					     var tdDep= td4.children().val();
					     str=str+id+","+tdText+","+sex+","+tdAge+","+tdDep+";";
					
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
			   $("select").each(function(){
			         //将输入框的文本保存
			         var select = $(this);
			         var selectText = select.val();
			         //将td的内容，即输入框去掉,然后给td赋值
			         var td = select.parent("td");
			         td.html(selectText);
			     });
			   $("tr:not(#table-head)").dblclick(tdClick); //让td重新拥有点击事件
			   
		
			   if(str!=""){
				   location.href="Employee?type=updateTable&str="+str+"&page="+${pageVo.pageNo};
			   }else{
				   alert("没有需要修改的数据");
				 location.href="Employee?pageNo="+${pageVo.pageNo}/*如果没有条语句，就会出现弹框后取不到数据的问题  */
			   }
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
			location.href="Employee?type=searchEmp&name="+name+"&sex="+sex+"&age="+age+"&dep="+dep;
		})
		
		
	
		
	})

</script>
</head>
<body>

	
	<div id="div-table">
		<div><h1>员工信息</h1></div>
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
				<td>照片</td>
				

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
				<td><img class="empImg" src="img/${emp.image }"></td>
			</tr>

					</c:forEach>

		</table>
</div>
<div id="div-img"></div>
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


		<button type="button" class="btn btn-default" id="showAdd">添加</button>
		<button type="button" class="btn btn-default" id="showAdd2">添加2</button>
		<button type="button" class="btn btn-default" id="showModify">修改</button>
		<button type="button" class="btn btn-default" id="showDel">删除</button>
		<button type="button" class="btn btn-default" id="save">保存修改</button>
	</div>

</body>
</html>