<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" href="iconfont/iconfont.css" />
<style type="text/css">
*{
	padding:0px;
	margin:0px;
}
#left {
	width: 200px;
	height: 600px;
	float: left;
	padding-left: 20px;
	background:#363636;
	border:10px solid #444444;

}
a {
	color: #fff;
	text-decoration: none;
}

#right {
	width: 1000px;
	height: 600px;
	float: left;
}

#top, #bottom {
	height: 80px;
	background: #363636;
	clear: both;
}
#bottom-text{
	color:#fff;
    height: 25px;
    font-size:13px;
    text-align:center;
    width: 180px;
       top: 660px;  
    position: absolute;

}
#top-text{
	color:#fff;
    height: 25px;
    font-size:13px;
    text-align:center;
    width: 180px;
       top: 60px;  
    position: absolute;

}

.yi {
cursor:pointer;
	width: 180px;
	height: 40px;
	background: #414141;
	color: #fff;
	margin-top: 10px;
	border-radius: 5px;
	text-align: center;
	line-height: 40px;
}
.er{
cursor:pointer;
list-style:none;
width:160px;


}
.er li{
padding:0 10px;
margin-top:5px;
text-align:center;
height:30px;

line-height:30px;
font-size:14px;
}
.er a{
color:#fff;
}
.select{
background:#3eb5ed;
}
.iconfont{
	margin-right:5px;
}
</style>

<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
var websocket = null;

//判断当前浏览器是否支持WebSocket
if ('WebSocket' in window) {
	websocket = new WebSocket("ws://192.168.0.120:8080/EmployeeWeb/websocket");
} else {
	alert('没有建立websocket连接')
}

//连接发生错误的回调方法
websocket.onerror = function() {
	setMessage("错误");
};

//连接成功建立的回调方法
websocket.onopen = function(event) {
	//setMessage("建立连接");
}

//接收到消息的回调方法
websocket.onmessage = function(event) {
	 $("#top-text").html("当前在线："+event.data+"人");
}

//连接关闭的回调方法
websocket.onclose = function() {
	//setMessage("close");
}

//监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口。
window.onbeforeunload = function() {
	websocket.close();
}

//将消息显示在网页上
function setMessage(text) {
	
	
}

//关闭连接
function closeWebSocket() {
	websocket.close();
}

//发送消息
function send() {
	//var message = $("#text").val();
	//websocket.send(message);
}


	$().ready(function(){
		$(".yi:not(#yi-f)").click(function(){
			$(this).toggleClass("select");
			$(this).next().slideToggle(600);
		})
		$(".er a").click(function(){
			$("a").removeClass("select");
			$("#yi-4").removeClass("select");
			$(this).toggleClass("select");
		})
		$(".yi:not(#yi-f)").click(function(){
			$("#yi-f").removeClass("select");
			$("#yi-4").removeClass("select");
			
		})
		$("#yi-f").click(function(){
			$(this).addClass("select");
			$(".yi:not(#yi-f)").removeClass("select");
			$(".yi:not(#yi-f)").next().slideUp(600);
			$(".er a").removeClass("select");
		})
		$("#yi-4").click(function(){
			$(this).addClass("select");
		})
		
		
		
	})
</script>


</head>
<body>
	<div id="container">
		<div id="top"><div id="top-text">当前在线：${applicationScope.num }人</div></div>
		<div id="main">
			<div id="left">
				
				<div class="yi select" id="yi-f" >
					<i class="iconfont">&#xe600;</i>系统首页
				</div>
				<div class="yi">
					<i class="iconfont">&#xe61c;</i>员工管理
				</div>
				<ul class="er" style="display:none;" >
					<li><a href="Employee" target="rightFrame">员工管理</a></li>
					<li><a href="Employee?type=showAdd" target="rightFrame">添加员工</a></li>
					<li><a href="Employee?type=empPro" target="rightFrame">员工项目管理</a></li>
				</ul>
				<div class="yi">
					<i class="iconfont">&#xe602;</i>部门管理
				</div>
				<ul class="er" style="display:none;">
					<li><a href="Department" target="rightFrame">部门管理</a></li>
					<li><a href="Department?type=showAdd" target="rightFrame">添加部门</a></li>
				</ul>
				<div class="yi">
					<i class="iconfont">&#xe60e;</i>项目管理
				</div>
				<ul class="er" style="display:none;">
					<li><a href="Project" target="rightFrame">项目管理</a></li>
					<li><a href="Project?type=showAdd" target="rightFrame">添加项目</a></li>
				</ul>
				<div class="yi" id="yi-4">
					<i class="iconfont">&#xe735;</i><a  href="Score" target="rightFrame">绩效管理</a>
				</div>
				<ul class="er" style="display:none;">
					<li><a href="Score?type=search" target="rightFrame">绩效查看</a></li>
					<li><a href="Score?type=input" target="rightFrame">添加</a></li>
				</ul>
				<div id="bottom-text">本网站共有<%=application.getAttribute("allnum") %>人访问</div>

			</div>
			<iframe id="right" name="rightFrame" scrolling="no" frameborder="0"
				src="Employee"></iframe>

		</div>
		<div id="bottom"></div>

	</div>
</body>
</html>