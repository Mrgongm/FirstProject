
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link href="bootstrap/css/bootstrap.css" rel="stylesheet">
<style type="text/css">
form {
	margin-top: 100px;
	font-size: 20px;
}

table {
	margin: auto;
	
} 
th{
text-align:center;
}
#table tr{
	margin-top:30px;
}

input {
height:34px;
width: 220px;
}

#login {
	margin-top: 30px;
}
</style>
</head>
<body>
<form action="user" method="post">
<!-- <input type="hidden" name="type" value="doLogin">
	账号:<input type="text" name="username"/>
	密码:<input type="password" name="password"/>
	<input type="submit"/> -->
	
		<input type="hidden" name="type" value="doLogin" />
		<table border="0" id="table">
			<tr>
				<th colspan="2">********</th>
			</tr>
			<tr>
				<td>账号:</td>
				<td><input type="text" name="username" id="username" class="form-control" value="" /></td>
			</tr>
	
			<tr>
				<td>密码:</td>
				<td><input type="text" name="password" class="form-control" id="password" value="" /></td>
			</tr>
			
			<tr>
				<td colspan="2" align="center"><button type="submit" name="login"
					id="login" class="btn btn-default" >登录</button></td>
			</tr>
		</table>
	</form>

</body>
</html>