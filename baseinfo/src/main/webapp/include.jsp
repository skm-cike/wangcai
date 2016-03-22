<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html  xmlns="http://www.w3.org/1999/xhtml">
<head>
	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>js/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>js/easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>js/easyui/demo/demo.css">
	<script src="<%=basePath%>/js/jquery/jquery-1.9.1.js"></script>
	<script type="text/javascript" src="<%=basePath%>/js/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>/js/easyui/locale/easyui-lang-zh_CN.js" charset="utf-8"></script>
	<script type="text/javascript">
	</script>
</head> 
<body> 
</body> 
</html> 