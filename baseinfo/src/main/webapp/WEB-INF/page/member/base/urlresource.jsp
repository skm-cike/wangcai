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
	function initUrl() {
		$.post('<%=basePath%>urlresource/init.do', {}, function (data) {
        	data = eval(data);
        	if (data.success) {
        		query();
        		alert("更新成功");
        	} else {
        		alert("更新失败:" + data.msg);
        	}
        });
	}
</script>
</head>
<body class="easyui-layout">  
<div data-options="region:'center'">
	<table id="tg" class="easyui-treegrid" title="URL资源管理""
			data-options="
				fit:'true',
				toolbar:'#toolbar',
				iconCls: 'icon-ok',
				rownumbers: true,
				animate: true,
				collapsible: true,
				fitColumns: true,
				url: '<%=basePath%>urlresource/getTreeGrid.do',
				method: 'post',
				idField: 'id',
				treeField: 'urlname',
				showFooter: false
			">
		<thead>
			<tr>
				<th data-options="field:'id',width:80,editor:'text',hidden:true">id</th>
				<th data-options="field:'urlname',width:80,editor:'text'">名称</th>
				<th data-options="field:'url',width:160,align:'right',editor:'text'">地址</th>
				<th data-options="field:'metthodname',width:180,editor:'text'">方法名称</th>
				<th data-options="field:'urldesc',width:180,editor:'text'">描述</th>
			</tr>
		</thead>
	</table>
		<div  id="toolbar">
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="edit()">Edit</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="save()">Save</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="cancel()">Cancel</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="reload()">重新加载</a>
		</div>
	<div id="mm" class="easyui-menu" data-options="onClick:menuHandler" style="width:120px;">
		<div data-options="name:'new', iconCls:'icon-add'">新建</div>
		<div data-options="name:'modify',iconCls:'icon-edit'">修改</div>
		<div data-options="name:'del',iconCls:'icon-remove'">删除</div>
	</div>
</div>

<div>

<div id="win" class="easyui-window" title="修改资源" style="width:600px;height:400px;"   data-options="closed:true,iconCls:'icon-save',modal:true">   
    <form id="resourceform" method="post"   data-options="fit:true">
			<input  type="hidden" name="id" />   
			<input  type="hidden" name="enable" />   
			<input  type="hidden" name="parentid"  />   
			<center>
				<div>   
			        <label for="urlname">名称:</label>   
			        <input class="easyui-validatebox" type="text" name="url" data-options="required:true" />   
			    </div>   
			    <br/>
			    <div>   
			        <label for="roledesc">描述:</label>   
			        <input  type="text" name="roledesc"  />   
			    </div>  
			    <br/> 
			    <div>   
			        <label for="parentname">上级资源:</label>   
			        <input class="easyui-validatebox" type="text" name="parentname"  readonly="readonly" onclick=""/>   
			    </div>
			    <br/> 
		    </center>
		</form>
		 <br/>
			<div style="text-align:center;padding:5px">
		    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitResource();">提交</a>
		    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="$('#resourceform').form('clear');">重置</a>
		    </div>  
</div>

</div>

	<script type="text/javascript">
	var resourceid;
	function menuHandler(item){
		//新建
		if (item.name == 'new') {
			resourceid = undefined;
			
		}
		
		//修改
		if (item.name == 'modify') {
			if (!resourceid) {
				return;
			}
			$('#wins').window('open', function() {
				$('#createRole').form('load',{
					resourceid:window.resourceid
				});
			});
		}
		
		//删除
		if (item.name == 'del') {
			if (!resourceid) {
				return;
			}
		}
	}
	
	
	
	$(function(){
		$('#tg').datagrid({onRowContextMenu: function(e, index, data) {
			e.preventDefault();
			window.resourceid = data.id;
			$('#mm').menu('show', {
				left: e.pageX,
				top: e.pageY
			});
		}})
	});
		var editingId;
		function edit(){
			if (editingId != undefined){
				$('#tg').treegrid('select', editingId);
				return;
			}
			var row = $('#tg').treegrid('getSelected');
			if (row){
				editingId = row.id
				$('#tg').treegrid('beginEdit', editingId);
			}
		}
		function save(){
			if (editingId != undefined){
				var t = $('#tg');
				t.treegrid('endEdit', editingId);
				editingId = undefined;
				var persons = 0;
				var rows = t.treegrid('getChildren');
				for(var i=0; i<rows.length; i++){
					var p = parseInt(rows[i].persons);
					if (!isNaN(p)){
						persons += p;
					}
				}
				var frow = t.treegrid('getFooterRows')[0];
				frow.persons = persons;
				t.treegrid('reloadFooter');
			}
		}
		function cancel(){
			if (editingId != undefined){
				$('#tg').treegrid('cancelEdit', editingId);
				editingId = undefined;
			}
		}
		
		function reload() {
			$('#tg').treegrid('reload');
		}
	</script>
</body>
</html>
