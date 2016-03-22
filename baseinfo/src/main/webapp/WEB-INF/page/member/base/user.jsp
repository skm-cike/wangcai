<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  import="com.wangcai.common.util.DateUtil,java.util.Date"%>
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
<title>用户管理</title>
<script type="text/javascript">
	var colTyp = [];
	$(function() {
	    $('#dg').datagrid({   
	        toolbar:'#toolbar',
	        url:"<%=basePath%>user/getUsers.do",
	        singleSelect:true,
	        pagination:true,
	        fit:true,
	        pageSize:20,
	        onDblClickCell:onClickCell
	    });
	    
	    
	    $('#rolescheckbox').panel({
	    	iconCls:'icon-ok',
	    	tools:[{
				iconCls:'icon-save',
				handler:function(){
					var selected = $('#dg').datagrid('getSelected');
					if (!selected) {
						return;
					}
					if (!selected.id) {
						return;
					}
					var rolenames = [];
					var userroles = $('input[name="userroles"]')
					for (var c in userroles) {
						if (userroles[c].checked) {
							rolenames.push(userroles[c].value);
						}
					}
					$.ajax({
					    type: 'POST',
					    url: "<%=basePath%>user/savUserRoles.do" ,
					   data: {userid:selected.id,userroles:rolenames.join()} ,
					   dataType:'json',
					   success: function(data) {
						   if (data.success) {
							   alert("保存成功");
						   } else {
							   alert(data.msg);
						   }
					   } 
					});
				}
			}]
	    });
	    
	    $('#dg').datagrid({'onDblClickRow':function(rowIndex, rowData) {
			$.ajax({
			    type: 'POST',
			    url: "<%=basePath%>user/getRoles.do" ,
			   data: {userid:rowData.id} ,
			   dataType:'json',
			   //async:false,
			   success: function(data) {
				   $('#rolescheckbox').panel({
					   content:data.data
				   })
			   } 
			});
	    
	    }});
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
	
	function adduser() {
		$('#dg').datagrid('insertRow', {index:0, row:{
			cardid:'',
			username:'',
			login:'',
			id:''
		}});
	}
	
	
	function save() {
		endEditing();
		var rows = $('#dg').datagrid('getChanges');
        var rowstr = JSON.stringify(rows);
        $.post('<%=basePath%>user/savUsers.do', {data:rowstr}, function (data) {
        	data = $.parseJSON(data);
        	if (data.success) {
        		alert("保存成功");
        		query();
        	} else {
        		alert("保存失败");
        	}
        });
	}
	
	function delCol() {
		var selected = $('#dg').datagrid('getSelected');
		if (!selected) {
			return;
		}
		if (!selected.id) {
			$('#dg').datagrid('deleteRow', $('#dg').datagrid('getRowIndex', selected));
			return;
		}
		$.post('<%=basePath%>urlresource/delUrl.do', {id:selected.id}, function (data) {
        	data = eval(data);
        	if (data) {
        		$('#dg').datagrid('deleteRow', rowIndex);  
        		query();
        		alert("删除成功");
        	} else {
        		alert("删除失败");
        	}
        });
	}
	
	
	function query(toolbar) {
		var login = $('#toolbar>input[name="login"]').val();
		var username = $('#toolbar>input[name="username"]').val();
		var cardid = $('#toolbar>input[name="cardid"]').val();
		$('#dg').datagrid({queryParams:{login:login,username:username,cardid:cardid}}).datagrid('reload');
	}
	
</script>
</head>
<body class="easyui-layout">  
	<div data-options="region:'west',split:false,title:'url'"  style="width:800px;height:250px">
			<table id="dg" class="easyui-datagrid"    
			        data-options="singleSelect:true">   
			    <thead>   
			        <tr>   
			            <th data-options="field:'id',width:100,hidden:true">id</th>   
			            <th data-options="field:'login',width:100,editor:'text'">登录名</th>   
			            <th data-options="field:'username',width:100,editor:'text'">姓名</th>   
			            <th data-options="field:'cardid',width:100,editor:'text'">身份证号</th>   
			        </tr>   
			    </thead>   
			</table>  
			<div id='toolbar'>
					登录名
					<input type="text" style="width:80px" name="login"  ></input>
					&nbsp
					姓名
					<input type="text" style="width:80px" name="username"  ></input>
					&nbsp
					身份证号
					<input type="text" style="width:80px" name="cardid"  ></input>
					&nbsp
					<a class="easyui-linkbutton" iconCls="icon-search"  onclick="query()">查询</a>
					&nbsp
				    <div style="float:right;margin-right:50px;">
				    	<a  class="easyui-linkbutton" iconCls="icon-add" onclick="adduser()">增加</a>&nbsp|&nbsp
				    	<a class="easyui-linkbutton" iconCls="icon-save" onclick="save()">保存</a>&nbsp|&nbsp
				    	<a class="easyui-linkbutton" iconCls="icon-remove" onclick="delCol()">删除</a>
				    </div>
			</div>
	</div>
	<div data-options="region:'center',title:'角色'" id="rolescheckbox">
		
	</div>
	
	
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
	
	var allowEdit = true;
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
		if (allowEdit && endEditing()){
			$('#dg').datagrid('selectRow', index)
					.datagrid('editCell', {index:index,field:field});
			editIndex = index;
		}
	}
	</script>
</body>
</html>
