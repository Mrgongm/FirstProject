<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
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
	<form id="main" action="Employee?type=addEmp" name="type"  method="post" enctype="multipart/form-data" >
		<table border="0" id="table">
			<tr>
				<th colspan="2">添加员工</th>
			</tr>
			<tr>
				<td>姓名:</td>
				<td><input type="text" name="empName" id="empName" class="form-control" value="" /></td>
			</tr>
			<tr>
				<td>性别:</td>
				<td><select name="empSex" id="empSex">
						<option>男</option>
						<option>女</option>
				</select></td>
			</tr>
			<tr>
				<td>年龄:</td>
				<td><input type="text" name="empAge" class="form-control" id="empAge" value="" /></td>
			</tr>
			<tr>
				<td>部门:</td>

				<td><select name="empDep" id="empDep">
					<option></option>
				<c:forEach items="${deplist}" var="dep">
						
						<option value="${dep.id }">
							${dep.name }
						</option>
				</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td>照片:</td>
				<td>
					<input type="file" value="选择文件" name="myFile"/>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" name="add"
					id="add" class="btn-info" value="确认添加" /></td>
			</tr>
		</table>
	</form>
</body>
</html>