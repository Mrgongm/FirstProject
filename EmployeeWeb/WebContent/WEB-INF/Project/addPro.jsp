<%@page import="java.util.ArrayList"%>
<%@page import="entity.Project"%>
<%@page import="java.util.List"%>
<%@page import="dao.ProjectDao"%>
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

input, select {
	height:34px;
width: 220px;
}

#add {
	margin-top: 30px;
}
</style>
</head>
<body>
	<form id="main" action=Project method="post">
		<input type="hidden" name="type" value="addPro" />
		<table border="0" id="table">
			<tr>
				<th colspan="2">添加项目</th>
			</tr>
			<tr>
				<td>名称:</td>
				<td><input type="text" name="proName" class="form-control" id="proName" value="" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" name="add"
					id="add" class="btn-info" value="确认添加" /></td>
			</tr>
		</table>
	</form>
</body>
</html>