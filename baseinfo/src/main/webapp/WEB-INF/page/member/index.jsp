<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://"
		+ request.getServerName() + ":" + request.getServerPort()
		+ path + "/";
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<jsp:include page="/include.jsp" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>主页</title>
<script type="text/javascript">
    function logout() {
        window.location.href="<%=basePath%>logout";
    }

</script>
</head>
    <body class="easyui-layout">  
       <div data-options="region:'north',split:false" style="height:50px;background:lavender;vertical-align:middle;">
        	<div style=""><img style="margin:5px;cursor:pointer;border:1px outset  lightskyblue;" width="60" height="40" title="会员管理" alt="会员管理"></img><img style="margin:5px;cursor:pointer;border:1px outset  lightskyblue;" width="60" height="40" title="库存管理"></img></div>
        	<div style="float:right;margin-top:-35px;"><a style="padding-right:10px;text-decoration: none;cursor:pointer;" onclick="#">修改密码</a><a style="margin-right:0px;text-decoration: none;cursor:pointer;" onclick="logout()">退出登录</a></div>
        </div> 
        <div data-options="region:'center',title:'123'" style="padding:5px;background:#eee;">
        	<iframe name="gongnengpage" src=""  frameborder="0" scrolling="no"  width="100%"  height="100%" style="" onload=""></iframe>
        </div>
        <div data-options="region:'south',split:false" style="height:40px;background:lavender;"><font>版本:1.0</font></div>  
        
        <div data-options="region:'west',title:'菜单',split:true" style="width:160px;"　class="easyui-accordion">
        	<center>
        	   <div style="border:1px outset  lightskyblue;cursor:pointer;margin:3px;">商品种类</div>
        	   <div style="border:1px outset  lightskyblue;cursor:pointer;margin:3px;">入库</div>
        	   <div style="border:1px outset  lightskyblue;cursor:pointer;margin:3px;">出库</div>
        	 </center>
        </div>  
    </body>  
</html>
