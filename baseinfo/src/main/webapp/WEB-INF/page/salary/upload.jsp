<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  import="com.wangcai.salary.user.vo.SysUser,com.wangcai.common.util.DateUtil,java.util.Date"%>
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
	} else {
		
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
		$("#yearmonth").datebox('setValue', "<%=request.getParameter("yearmonth")%>");
		$("#username").val("<%=request.getParameter("username")%>"=="null"?"":"<%=request.getParameter("username")%>");
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
	        queryParams: {yearmonth:"<%=yearmonth%>", username:"<%=request.getParameter("username")%>"},
	        toolbar:'#toolbar',
	        singleSelect:true,
	        pagination:true,
	        //height:$(window).outerHeight(true)-$("#toolbar").outerHeight(true),
	        fit:true,
	        pageSize:20
	    });
	    $("#toolbar>a").unbind().bind('click', function() {
	    	window.location.replace("<%=basePath%>salary/to.do?page=upload&yearmonth=" +  $("#yearmonth").datebox('getValue') + "&username=" +  encodeURIComponent($("#username").val()));
	    } )
	})
	
	
	function valite() {
		$("#ff>input[name='yearmonth']").val($("#yearmonth").datebox('getValue'));
		$("#ff>input[name='username']").val($("#username").val());
		var val = $("#uploadfile").val();
		if (!val || val==null || val==undefined || val=='') {
			return false;
		}
		return true;
	}
</script>
</head>
<body>  
	<table  id="dg" style="margin:0px;padding:0px;fit:true;"></table>
	<div id='toolbar'>
			日期: 
			<input name="date" id="yearmonth" class="easyui-datebox" style="width:80px" data-options="formatter:function(date){var y = date.getFullYear();var m = date.getMonth()+1;var d = date.getDate();return y+'-'+(m<10?('0'+m):m);},parser:function(s){if (!s) return new Date();var ss = s.split('-');var y = parseInt(ss[0],10);var m = parseInt(ss[1],10);var d = 1;if (!isNaN(y) && !isNaN(m) && !isNaN(d)){return new Date(y,m-1,d);} else {return new Date();}}"  value="<%=yearmonth%>"/>
			&nbsp
			姓名: <input type="text" style="width:80px" name="username" id="username"  value="<%=username%>"></input>
			<a class="easyui-linkbutton" iconCls="icon-search">查询</a>
			&nbsp
			<form id="ff" method="post" enctype="multipart/form-data" action="<%=basePath%>salary/upload.do" style="float: right;margin-right:50px;" onsubmit="return valite()">  
		            <input type="hidden" name="yearmonth"/>
		            <input type="hidden" name="username"/>
		            <input  type="file" name="files" id="uploadfile"/>  
		            <input type="submit" value="上传"/>
		    </form>
	</div>
</body>
</html>
