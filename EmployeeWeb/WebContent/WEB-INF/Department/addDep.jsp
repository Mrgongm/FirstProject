<%@page import="java.util.ArrayList"%>
<%@page import="entity.Department"%>
<%@page import="java.util.List"%>
<%@page import="dao.DepartmentDao"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link href="bootstrap/css/bootstrap.css" rel="stylesheet">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
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
	<form id="main" action=Department method="post">
		<input type="hidden" name="type" value="addDep" />
		<table border="0" id="table">
			<tr>
				<th colspan="2">添加部门</th>
			</tr>
			<tr>
				<td>名称:</td>
				<td><input type="text" name="depName" class="form-control" id="depName" value="" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" name="add"
					id="add"  class="btn-info" value="确认添加" /></td>
			</tr>
		</table>
	</form>
</body>
</html>