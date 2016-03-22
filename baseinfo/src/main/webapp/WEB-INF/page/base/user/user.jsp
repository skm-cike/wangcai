<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/include.jsp" %>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#include_head {
		background-color:red;
	}
	#include_logo {
		background-color:gray;
	}
	#include_body {
		background-color:yellow;
	}
	#include_root {
		background-color:blue;
	}
	body {
		margin:0px;
	}
</style>
<script type="text/javascript">
	//===================调整格式======================
	var headheight = 100;		 //页头高度
	var rootheight = 27;         //页脚高度
	var logoheight = 73;         //LOGO高度
	var offset = 0;              //偏移量
	$(function() {
		var height = $(this).height();
		$('#include_body').height(height - headheight - rootheight + offset);
		$('#include_head').height(headheight);
		$('#include_logo').height(logoheight);
		$('#include_root').height(rootheight);
		if(!/msie/.test(navigator.userAgent.toLowerCase())) {
			$('#include_menu').css("margin-top",-13);
			$('#model_position').css("margin-top", 17);
		} else {
			$('#model_position').css("margin-top", 5);
		};
	});
	$(window).resize(function() {
		var height = $(this).height();
		$('#include_body').height(height - headheight - rootheight + offset);
	});
	
</script>
</head>
<body>
	<div id='include_head'>
			<div id='include_logo'></div>
			<div id='include_menu'>
				<div style='float:left;' style="vertical-align:bottom;">
					<div id='model_position'>${user.username}</div>
				</div>
				<div style='float:right;'>
					<a href="#" class="easyui-linkbutton" data-options="plain:true">Home</a>
					<a href="#" class="easyui-menubutton" data-options="menu:'#mm1',iconCls:'icon-edit'">Edit</a>
					<a href="#" class="easyui-menubutton" data-options="menu:'#mm2',iconCls:'icon-help'">Help</a> 
					<a href="#" class="easyui-menubutton" data-options="menu:'#mm3'" >About</a>
				</div>
				<div id="mm1" style="width: 150px;">
					<div data-options="iconCls:'icon-undo'">Undo</div>
					<div data-options="iconCls:'icon-redo'">Redo</div>
					<div class="menu-sep"></div>
					<div>Cut</div>
					<div>Copy</div>
					<div>Paste</div>
					<div class="menu-sep"></div>
					<div>
						<span>Toolbar</span>
						<div style="width: 150px;">
						<div>
							<span>Toolbar2</span>
							<div style="width: 150px;">
								<div>Address2</div>
								<div>Link</div>
								<div>Navigation Toolbar</div>
								<div>Bookmark Toolbar</div>
								<div class="menu-sep"></div>
								<div>New Toolbar...</div>
							</div>
						</div>
						<div>Link</div>
							<div>Navigation Toolbar</div>
							<div>Bookmark Toolbar</div>
							<div class="menu-sep"></div>
							<div>New Toolbar...</div>
						</div>
					</div>
					<div data-options="iconCls:'icon-remove'">Delete</div>
					<div>Select All</div>
				</div>
				<div id="mm2" style="width: 100px;">
					<div>Help</div>
					<div>Update</div>
					<div>About</div>
				</div>
				<div id="mm3" class="menu-content"
					style="background: #f0f0f0; padding: 10px; text-align: left">
					<img src="http://www.jeasyui.com/images/logo1.png"
						style="width: 150px; height: 50px">
					<p style="font-size: 14px; color: #444;">Try jQuery EasyUI to
						build your modern, interactive, javascript applications.</p>
				</div>
			</div>
		</div>
	<iframe id='include_body' name="include_body" src="<%=basePath%>/sysinit/portal/welcome" width='100%' frameborder="0" scrolling="no"></iframe>
	<div id='include_root'>尾</div>
</body>
</html>