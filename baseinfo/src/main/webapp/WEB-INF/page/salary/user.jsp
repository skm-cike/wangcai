<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  import="com.wangcai.salary.user.vo.SysUser"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://"
		+ request.getServerName() + ":" + request.getServerPort()
		+ path + "/";
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<jsp:include page="/include.jsp" />
<meta http-equiv="Content-Type" content="text/html; charse	
t=utf-8" />
<title>用户管理</title>
<script type="text/javascript">

$.extend($.fn.datagrid.methods, {
	editCell: function(jq,param){
		return jq.each(function(){
			var opts = $(this).datagrid('options');
			var fields = $(this).datagrid('getColumnFields',true).concat($(this).datagrid('getColumnFields'));
			for(var i=0; i<fields.length; i++){
				var col = $(this).datagrid('getColumnOption', fields[i]);
				col.editor1 = col.editor;
				if (fields[i] != param.field){
					col.editor = null;
				}
			}
			$(this).datagrid('beginEdit', param.index);
			for(var i=0; i<fields.length; i++){
				var col = $(this).datagrid('getColumnOption', fields[i]);
				col.editor = col.editor1;
			}
		});
	}
});

var editIndex = undefined;
function endEditing(){
	if (editIndex == undefined){return true}
	if ($('#dg').datagrid('validateRow', editIndex)){
		$('#dg').datagrid('endEdit', editIndex);
		editIndex = undefined;
		return true;
	} else {
		return false;
	}
}
function onClickCell(index, field){
	if (endEditing()){
		$('#dg').datagrid('selectRow', index)
				.datagrid('editCell', {index:index,field:field});
		editIndex = index;
		$('#dg').datagrid('getEditors', editIndex)[0].target.bind("blur", function() {
			endEditing();
		});
	}
}

	$(function() {
	    $('#dg').datagrid({   
	        url:'<%=basePath%>user/userspage.do', 
	        idField:'userid',
	        iconCls:'icon-edit',
	        loadMsg:'数据加载中,请稍后......',
	        columns:[[{field:'userid',title:'userid',width:0, hidden:true},
	                  {field:'employeenum',title:'员工编号', width:100, editor:'text'},
	                  {field:'idcardnum',title:'身份证号',width:160, editor:'text'},
	                  {field:'tsequenct',title:'序号',width:0, hidden:true},
	                  {field:'username',title:'姓名',width:100, editor:'text'},
	                  {field:'isManager',title:'是否管理员',width:90,editor:{type:'combobox',options:{valueField:'val',textField:'text',data:[{val:1,text:'是'},{val:0, text:'否'}]}}}
					]],
	        toolbar:'#toolbar',
	        singleSelect:true,
	        pagination:true,
	        //height:$(window).outerHeight(true)-$("#toolbar").outerHeight(true),
	        onDblClickCell: onClickCell,
	        fit:true,
	        pageSize:20,
	        onAfterEdit:function(rowIndex, rowData, changes) {
	        	
	        }
	    });
	    $("#toolbar>form>a[name='query']").unbind().bind('click', function() {
	    	$('#dg').datagrid('reload', {   
	    		username: $("input[name='username']").val(),   
	    		employeenum: $("input[name='employeenum']").val(),
	    		idcardnum:$("input[name='idcardnum']").val()
	    	});
	    } )
	    
	    //重置
	    $("#toolbar>form>a[name='reset']").unbind().bind('click', function() {
	    	$("#toolbar>form")[0].reset();
	    } )
	    
	    //删除
	    $("#toolbar>form>a[name='remove']").unbind().bind('click', function() {
	    	var row = $('#dg').datagrid('getSelected');
	    	if (!row) {
	    		alert("你没有选中行!");
	    		return;
	    	}
	    	var username = row.username;
	    	$.messager.confirm("提示", "您确定要删除员工<font color='red'>" + username + "</font>吗？", function (data) {
	            if (data) {
	            	if (row) {
		    	         var rowIndex = $('#dg').datagrid('getRowIndex', row);
		    	         $.post('<%=basePath%>user/deluser.do', {userid:row.userid}, function (data) {
		    	            	data = eval(data);
		    	            	if (data) {
		    	            		$('#dg').datagrid('deleteRow', rowIndex);  
		    	            		alert("删除成功");
		    	            	} else {
		    	            		alert("删除失败");
		    	            	}
		    	            });
		    	         
		    	 }
	            }
	        });
	    } )
	    
	    //增加
	    $("#toolbar>form>a[name='add']").unbind().bind('click', function() {
	    	$('#dg').datagrid('insertRow',{index: 0, row: {tsequenct:0, isManager:0}});
	    } )
	    
	    //保存
	    $("#toolbar>form>a[name='save']").unbind().bind('click', function() {
	    	var rows = $("#dg").datagrid('getChanges');
	    	 
            var rowstr = JSON.stringify(rows);
            $.post('<%=basePath%>user/savusers.do', {data:rowstr}, function (data) {
            	data = eval(data);
            	if (data) {
            		alert("保存成功");
            		$("#toolbar>form>a[name='query']").click();
            	} else {
            		alert("保存失败");
            	}
            });
	    } )
	    
	    //重置密码
	    $("#toolbar>form>a[name='resetpwd']").unbind().bind('click', function() {
	    	var selected = $("#dg").datagrid('getSelected');
			if (!selected) {
				return;
			}
			
			$.messager.confirm("提示", "您确定要重置员工<font color='red'>" + selected.username + "</font>的密码为123456吗？", function (data) {
	            if (data) {
	            		  $.post('<%=basePath%>user/resetpwd.do', {userid:selected.userid}, function (data) {
	                      	data = eval(data);
	                      	if (data) {
	                      		alert("保存成功");
	                      		$("#toolbar>form>a[name='query']").click();
	                      	} else {
	                      		alert("保存失败");
	                      	}
	                      });
		    	         
	            }
	        });

	    } )
	})
	
</script>
</head>

<body>  
	<table  id="dg" style="margin:20px 0;"></table>
	<div id='toolbar'>
			<form id="queryform">
			姓名:<input type="text" name="username" style="width:80px"/> &nbsp&nbsp
			员工编号:<input type="text" name="employeenum" style="width:100px"/> &nbsp&nbsp
			身份证号:<input type="text" name="idcardnum" style="width:160px"/> &nbsp&nbsp
			<a class="easyui-linkbutton" iconCls="icon-search" name="query">查询</a>
			<a class="easyui-linkbutton" iconCls="icon-back" name="reset">重置</a>&nbsp&nbsp&nbsp&nbsp |
			<a class="easyui-linkbutton" iconCls="icon-remove" name="remove">删除</a>
			<a class="easyui-linkbutton" iconCls="icon-add" name="add">增加</a>
			<a class="easyui-linkbutton" iconCls="icon-save" name="save">保存</a>&nbsp&nbsp&nbsp |
			<a class="easyui-linkbutton" iconCls="icon-reload" name="resetpwd">重置密码</a>
			</form>
	</div>
</body>
</html>
