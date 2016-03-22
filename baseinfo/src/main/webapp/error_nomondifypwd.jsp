<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  import="com.wangcai.salary.user.vo.SysUser,com.wangcai.common.util.DateUtil,java.util.Date,com.wangcai.common.util.StringUtil"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://"
		+ request.getServerName() + ":" + request.getServerPort()
		+ path + "/";
%>
<head>
<jsp:include page="/include.jsp" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>请修改密码</title>
</head>

<body>
	<center style="font-size:35px;">请修改密码后再查询</center>
</body>
</html>
