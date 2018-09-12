<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title></title>
    <meta charset="utf-8" />
    <title>用户登录</title>
<script type="text/javascript" src="js/jquery.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
<link href="bootstrap/css/bootstrap.css" rel="stylesheet">
    <style type="text/css">
        #from
        {
            background:url(./img/d5ec4baf75c30b45625179600bc4d88c.jpg);
        }
        html,body{
			width: 100%;
			height: 100%;
			}
        .mycenter
        {
           
            margin-left: auto;
            margin-right: auto;
            height: 750px;
            width: 500px;
            padding: 5%;
            padding-left: 5%;
            padding-right: 5%;
        }
        .mycenter mysign
        {
            width: 440px;
        }
        .mycenter input, checkbox, button
        {
            margin-top: 2%;
            margin-left: 10%;
            margin-right: 10%;
        }
        .mycheckbox
        {
            margin-top: 10px;
            margin-left: 40px;
            margin-bottom: 10px;
            height: 10px;
        }
        #from,.text-info{
        color:#fff;
        }
        #image{
        position:relative;
        top:-32px;
        left:204px;
        }
    </style>
    <script type="text/javascript">
    
    if(self!=top){
    	top.location="User?type=showLogin"
    }
    
    
    
    
    $().ready(function(){
    	$("#image").click(function(){
    		$(this).attr("src","User?type=randomImage&"+Math.random())
    	})
    	
		setTimeout(function() {
			$("#mes").html("");
		}, 1500)

		$("#register").click(function(){
			location.href="User?type=showRegister"
		})
		
		
					
    }) 
    </script>
</head>
<body>
    <form id="from" action="User" method="post">
    <input type="hidden" name="type" value="doLogin">
    <div class="mycenter">
        <div class="mysign">
            <div class="col-lg-11 text-center text-info">
                <h2>
                    请登录</h2>
            </div>
            <div class="col-lg-10">
                <input type="text" class="form-control" name="username" id="username" placeholder="请输入账户名" required
                    autofocus value="${name }" />    <!-- autofocus获得焦点 。      required 属性规定必需在提交之前填写输入字段 -->
            </div>
            <div class="col-lg-10">
            </div>
            <div class="col-lg-10">
                <input type="password" class="form-control" name="password" id="password" placeholder="请输入密码" required
                    autofocus />
            </div>
            
            <div class="form-group">
            	
            	<div class="col-lg-10">
            		<input type="text" name="random" class="form-control" placeholder="请输入验证码">
            	</div>
            	<div class="col-lg-10">
            		<img id="image" src="User?type=randomImage">
            	</div>
            </div>
            
             <div class="col-lg-10" id="mes" style="text-align:center; height:40px;color:red;">${mes }</div>
            
            <div class="col-lg-10">
            </div>
            <div class="col-lg-10 mycheckbox checkbox">
                <input type="checkbox" class="col-lg-1">记住密码</input>
            </div>
            <div class="col-lg-10">
            </div>
            <div class="col-lg-10">
                <button type="submit" id="btn" class="btn btn-success col-lg-12">
                    登录</button>
                     <button type="button" id="register" class="btn btn-success col-lg-12">
                    注册</button>
            </div>
            <div></div> 
        </div>
    </div>
    </form>
</body>
</html>