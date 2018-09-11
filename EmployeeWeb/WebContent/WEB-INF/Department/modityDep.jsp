
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
	width: 1100px;
	margin: 20px auto;
}

.div-dep {
	float: left;
	margin: 10px 50px 10px 0;
	width: 300px;
	background: #fae6fb;
	border: 1px dashed #ccc;
	padding: 20px 20px 10px 0;
	border-radius: 10px;
}

.form-group label {
	padding-right: 0px
}

.div-auto {
	
}

#btnSave {
	clear: both;
	text-align: center;
}
</style>
</head>
<body>


	<form action="Department" class="form-horizontal" role="form"
		method="post">
		<div id="div-table">
		
				<c:forEach items="${list }" var="dep" varStatus="status">
				
			
			<div class="div-dep">
				<div class="div-auto">
					<input type="hidden" name="type" value="modityDep" /> <input
						type="hidden" name="depId" value="${dep.id }" />

					<div class="form-group">
						<label for="firstname" class="col-sm-2 control-label ">名称:</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" name="depName"
								value=${dep.name }>
						</div>
					</div>

					
				</div>
			</div>
				</c:forEach>	

			<div id="btnSave">

				<button type="submit" class="btn btn-primary">保存</button>

			</div>
		</div>
	</form>

</body>
</html>