<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  import="com.wangcai.salary.user.vo.SysUser"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://"
		+ request.getServerName() + ":" + request.getServerPort()
		+ path + "/";
SysUser user = ((SysUser) session.getAttribute("SysUser"));
int ismanager = user.getIsManager();
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<jsp:include page="/include.jsp" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>主页</title>
<script type="text/javascript">
	function jumpto(mark) {
		subpage.location.replace("<%=basePath%>salary/to.do?page=" + mark);
	}
	$(function() {
		if (<%=ismanager%> == 1) {
			$("div[name='usermanager']").show();
			$("div[name='upload']").show();
		}
		$('#win').window('close');
	})
	
	function mondify() {
		$("#win").window('open');
		$("input[name='username']").val("<%=user.getUsername()%>");
		$("input[name='employeenum']").val("<%=user.getEmployeenum()%>");
		$("input[name='idcardnum']").val("<%=user.getIdcardnum()%>");
		$("input[name='password1']").val(null);
		$("input[name='password2']").val(null);
		$("input[name='userid']").val("<%=user.getUserid()%>");
	}
	function cancle() {
		$('#win').window('close');
	}
	
	function submit() {
		if (!validate()) {
			return;
		}
		$.post('<%=basePath%>user/updateuser.do', {userid:$("input[name='userid']").val(), password:$("input[name='password1']").val()}, function (data) {
        	data = eval(data);
        	if (data) {
        		alert("修改成功");
        		cancle();
        		window.top.location.reload(true);
        	} else {
        		alert("修改失败");
        	}
        });
	}
	
	function validate() {
		var userid = $("input[name='userid']").val();
		if (!userid || userid=='') {
			alert("未登录");
			return false;
		}
		var p1 = $("input[name='password1']").val();
		var p2 = $("input[name='password2']").val();
		if (p1 == null || p1 ==  '') {
			alert("密码不能为空");
			return false;
		}
		if (p1 == p2) {
			if (p1=='123456') {
				alert("请不要使用简单的密码!");
				return false;
			}
			return true;
		} else {
			alert("两次密码不一样");
			return false;
		}
		
	}
</script>
</head>

	
    <body class="easyui-layout">  
        <div data-options="region:'north',split:false" style="height:80px;background:lavender;">
        	<div style="margin-top:20px;"><h1 style="font-size:28px;color:blue;">江油发电厂工资查询</h1></div>
        	<div style="float:right;margin-top:-35px;"><a style="padding-right:10px;text-decoration: none;cursor:pointer;" onclick="mondify()">修改密码</a><a style="margin-right:0px;text-decoration: none;cursor:pointer;" href="<%=basePath%>user/logout.do">退出登录</a></div>
        </div>  
        <div data-options="region:'center',title:'欢迎你 ' + '<%=((SysUser)session.getAttribute("SysUser")).getUsername()%>'" style="padding:5px;background:#eee;">
        	<iframe name="subpage" src=""  frameborder="0" scrolling="no"  width="100%"  height="100%" style="" onload=""></iframe>
        </div>
        <div data-options="region:'south',split:false" style="height:40px;background:lavender;"><font>版本:1.0</font></div>  
        <div data-options="region:'west',title:'菜单',split:true" style="width:160px;"　class="easyui-accordion">
        	   <div style="overflow:auto;padding:10px;font-size:12pt;display:none;border:1px outset    lightskyblue;margin:3px;cursor:pointer;" name="usermanager" onclick="jumpto('user')">  
			       	人员管理
			    </div>  
			    <div  style="overflow:auto;padding:10px;font-size:12pt;display:none;border:1px outset    lightskyblue;margin:3px;cursor:pointer;" name="upload"  onclick="jumpto('upload')">  
			        上传   
			    </div>  
			    <div style="overflow:auto;padding:10px;font-size:12pt;border:1px outset    lightskyblue;margin:3px;cursor:pointer;" name="query"  onclick="jumpto('query')">  
			        查询  
			    </div> 
        </div>  
        
            <div id="win" class="easyui-window" title="My Window" style="width:320px;height:210px;"  
		            data-options="iconCls:'icon-save',modal:true,collapsible:false,minimizable:false,maximizable:false,closable:false,closed:false">  
		            <center>
						<table>
							<tr style="display:none;"><td>id</td><td><input type="text" name="userid" style="width:150px;"></input></td></tr>
							<tr><td>姓名:</td><td><input type="text" name="username" style="width:150px;" readonly="readonly"></input></td></tr>
							<tr style="display:none;"><td>员工编码:</td><td><input type="text" name="employeenum" style="width:150px;" readonly="readonly"></input></td></tr>
							<tr style="display:none;"><td>身份证号:</td><td><input type="text" name="idcardnum" style="width:150px;" readonly="readonly"></input></td></tr>
							<tr><td>密码: </td><td><input type="password" name="password1" style="width:150px;"></input></td></tr>
							<tr><td>再次输入密码:</td><td><input type="password" name="password2" style="width:150px;"></input></td></tr>
							<tr><td><button type="button" onclick="submit()">修改</button></td><td><button type="button" onclick="cancle()">取消</button></td></tr>
						</table>
				</center>
		    </div>
    </body>  
</html>
