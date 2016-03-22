<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  import="com.wangcai.salary.user.vo.SysUser,com.wangcai.common.util.DateUtil,java.util.Date,com.wangcai.common.util.StringUtil"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String thisdate= DateUtil.format(new Date(), "yyyy-MM");
	String yearmonth = request.getParameter("yearmonth");
	if (yearmonth == null || "".equals(yearmonth)) {
		yearmonth = thisdate;
	}
	String username = request.getParameter("username");
	if (username == null) {
		username = "";
	}
	SysUser user = ((SysUser) session.getAttribute("SysUser"));
	if (user != null) {
		String pwd = user.getPassword();
		String md5pwd = StringUtil.getMD5("123456");
		if (StringUtil.getMD5("123456").equals(pwd)) {
			request.getRequestDispatcher("/error_nomondifypwd.jsp").forward(request, response);
		}
	}
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<jsp:include page="/include.jsp" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户管理</title>
<script type="text/javascript">
	var colTyp = [];
	$(function() {
		var temp1 = {field:"序号", title: "序号", width:30};
		var temp2 = {field:"人员编号", title:"人员编号", width:80};
		var temp3 = {field:"身份证", title: "身份证", width:150};
		colTyp.push(temp1);
		colTyp.push(temp2);
		colTyp.push(temp3);
		$.ajax({
		    type: 'POST',
		    url: "<%=basePath%>salary/salaryheadforpage.do" ,
		   data: {yearmonth:"<%=yearmonth%>", username:"<%=request.getParameter("username")%>"} ,
		   dataType:'json',
		   async:false,
		   success: function(data) {
			   for (var i = 0; i < data.length; i++) {
				   var vl = {field:data[i], title:data[i], width:100};
				   colTyp.push(vl);
			   }
		   } 
		});
		
	    $('#dg').datagrid({   
	        url:'<%=basePath%>salary/query.do',   
	        columns:[colTyp],
	        queryParams: {yearmonth:"<%=yearmonth%>", idcardnum:"<%=user.getIdcardnum()%>"},
	        toolbar:'#toolbar',
	        singleSelect:true,
	        pagination:true,
	        fit:true,
	        pageSize:20
	    });
	    $("#toolbar>a").unbind().bind('click', function() {
	    	window.location.replace("<%=basePath%>salary/to.do?page=query&yearmonth=" +  $("#yearmonth").datebox('getValue') + "&idcardnum:<%=user.getIdcardnum()%>");
	    } )
	})
	
	
	function valite() {
		var val = $("#uploadfile").val();
		if (!val || val==null || val==undefined || val=='') {
			return false
		}
		return true;
	}
</script>
</head>
<body>  
	<table  id="dg" style="margin:20px 0;"></table>
	<div id='toolbar'>
			日期: <input name="date" id="yearmonth" class="easyui-datebox" style="width:80px" data-options="formatter:function(date){var y = date.getFullYear();var m = date.getMonth()+1;var d = date.getDate();return y+'-'+(m<10?('0'+m):m);},parser:function(s){if (!s) return new Date();var ss = s.split('-');var y = parseInt(ss[0],10);var m = parseInt(ss[1],10);var d = 1;if (!isNaN(y) && !isNaN(m) && !isNaN(d)){return new Date(y,m-1,d);} else {return new Date();}}"  value="<%=yearmonth%>"/>
			<a class="easyui-linkbutton" iconCls="icon-search">查询</a>
	</div>
</body>
</html>
