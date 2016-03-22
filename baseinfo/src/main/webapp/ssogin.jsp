<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<center>
		<form action="user/login.do"  style="width: 260px; text-align: center;"  method="POST">
			<fieldset>
				<legend>登陆</legend>
				用户： <input type="text" name="username " style="width:150px;" /><br />
				密码： <input type="password" name="password " style="width: 150px;" /><br />
				<input type="submit" value="登陆" /> <input type="reset" value="重置" />
			</fieldset>
		</form>
	</center>
</body>
</html>