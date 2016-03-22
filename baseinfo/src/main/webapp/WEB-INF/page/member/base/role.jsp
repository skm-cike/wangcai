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
	        url:"<%=basePath%>role/getRoles.do",
	        singleSelect:true,
	        pagination:true,
	        fit:true,
	        pageSize:20
	    });
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
	
	function modify() {
			var selected = $('#dg').datagrid('getSelected');
			if (!selected) {
				return;
			}
			if (!selected.roleid) {
				return;
			}
			$('#wins').window('open', function() {
				$('#createRole').form('load',{
					roleid:selected.roleid
				});
			});
	}
	function delCol() {
		var selected = $('#dg').datagrid('getSelected');
		if (!selected) {
			return;
		}
		if (!selected.roleid) {
			$('#dg').datagrid('deleteRow', $('#dg').datagrid('getRowIndex', selected));
			return;
		}
		$.post('<%=basePath%>role/delRole.do', {roleid:selected.roleid}, function (data) {
        	data = eval(data);
        	if (data) {
        		$('#dg').datagrid('deleteRow', rowIndex);  
        		alert("删除成功");
        	} else {
        		alert("删除失败");
        	}
        });
	}
	
	function query(toolbar) {
		var rolename = $('#toolbar>input[name="rolename"]').val();
		$('#dg').datagrid({queryParams:{rolename:rolename}}).datagrid('reload');
	}
</script>
</head>
<body>  
	<table id="dg" class="easyui-datagrid" style="width:400px;height:250px"   
	        data-options="singleSelect:true">   
	    <thead>   
	        <tr>   
	            <th data-options="field:'roleid',width:100,hidden:true">角色id</th>   
	            <th data-options="field:'rolename',width:100">角色名称</th>   
	            <th data-options="field:'roledesc',width:100">角色描述</th>   
	            <th data-options="field:'enable',width:100">使用角色</th>   
	        </tr>   
	    </thead>   
	</table>  
	<div id='toolbar'>
			名称: 
			<input type="text" style="width:80px" name="rolename" id="rolename" ></input>
			&nbsp
			<a class="easyui-linkbutton" iconCls="icon-search"  onclick="query()">查询</a>
			&nbsp
		    <div style="float:right;margin-right:50px;">
		    	<a  class="easyui-linkbutton" iconCls="icon-add" onclick="$('#wins').window('open');">增加</a>&nbsp|&nbsp
		    	<a class="easyui-linkbutton" iconCls="icon-edit" onclick="modify()">修改</a>&nbsp|&nbsp
		    	<a class="easyui-linkbutton" iconCls="icon-remove" onclick="delCol()">删除</a>
		    </div>
	</div>
	
	<div id="wins" class="easyui-window" title="新建角色" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width:280px;height:200px;padding:10px;">
		<form id="createRole" method="post"   data-options="fit:true">
			<input  type="hidden" name="roleid" />   
			<input  type="hidden" name="enable" />   
			<center>
			    <div>   
			        <label for="rolename">名称:</label>   
			        <input class="easyui-validatebox" type="text" name="rolename" data-options="required:true" />   
			    </div>   
			    <br/>
			    <div>   
			        <label for="roledesc">描述:</label>   
			        <input class="easyui-validatebox" type="text" name="roledesc"  />   
			    </div>   
		    </center>
		</form>
		 <br/>
			   <div style="text-align:center;padding:5px">
		    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitRole();">提交</a>
		    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="$('#createRole').form('clear');">重置</a>
		    </div>
	</div>
	
	<script type="text/javascript">
			function submitRole() {
				$('#createRole').form('submit', {    
				    url:'<%=basePath%>role/savRoles.do',    
				    onSubmit: function(){    
				        
				    },    
				    success:function(data){
				    	if (data.success) {
				    		query();
				    	 	$('#wins').window('close', function() {
				    	 		$('#createRole').form('clear');
				    	 	});
				    	}
				    }    
				});
			}
	</script>
</body>
</html>
