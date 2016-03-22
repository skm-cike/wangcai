<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/include.jsp" %>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<title>Insert title here</title>
<style type="text/css">

</style>
<script type="text/javascript">
	
$(document).ready(function() {
	/*
	$.ajax({
	     type: 'POST',
	     url: "<%=basePath%>sysinit/base/authority/moduleTree",
	     data: {a:1} ,
	     success: function(data, textStatus, jqXHR) {
	    	alert(data)
	     }
	});
	*/
	//==========定义模块表的列==========
	$('#modulegrid').datagrid({
		pagePosition:'bottom',
		pageNumber:1,
		pageSize:20,
		pagination:true,
		rownumbers:true,
		loadMsg:'数据努力加载中...',
		url:'<%=basePath%>sysinit/base/authority/getModuleList',
	    columns:[[    
	        {title:'ID',field:'authorityid',width:100, hidden:true},    
	        {title:'模块名称',field:'authorityname',width:100,align:'left'},    
	        {title:'模块地址',field:'modelurl',width:100,align:'left'},    
	        {title:'是否公共',field:'ispublic',width:100,align:'left'},    
	        {title:'模块排序',field:'orderindex',width:100,align:'right'},    
	        {title:'备注',field:'authorityremark',width:100,align:'left'},    
	        {title:'是否使用',field:'isuse',width:100,align:'left'}
	    ]]    
	});
	
	//==========加载模块树=============
	$('#moduletree').tree({   
        checkbox: false,   
        url: '<%=basePath%>sysinit/base/authority/moduleTree',   
        onBeforeExpand:function(node,param){
            $('#moduletree').tree('options').url = "<%=basePath%>sysinit/base/authority/moduleTree?parentauthorityid="+node.id;
        },
       onClick:function(node){
    	   $('#modulegrid').datagrid({queryParams:{parentauthorityid:node.id}});
           var state=node.state;
             if(!state){                                   //判断当前选中的节点是否为根节点
                 currentId=node.id;
               $("#chooseOk").attr( "disabled" , false );   //如果为根节点 则OK按钮可用
               }else{
               $("#chooseOk").attr( "disabled" , true );    //如果不为根节点 则OK按钮不可用
               }
           } 
   });
	
	//========上级模块selectmoku=========
	$('#cct').combotree({   
        checkbox: false,   
        url: '<%=basePath%>sysinit/base/authority/moduleTree',   
        onBeforeExpand:function(node,param){
        	$('#cct').combotree("tree").tree("options").url  = "<%=basePath%>sysinit/base/authority/moduleTree?parentauthorityid="+node.id;
        }
   });


	
	//==================事件======================
	$('#tab').tabs({
			onSelect: function(title,index) {
				if (title == '详细') {
					var selected = $('#modulegrid').datagrid('getSelected');
					if (!selected) {
						return;
					}
					var authorityid = selected.authorityid;
					$('#ff').form('load', 
				        "<%=basePath%>sysinit/base/authority/getModule?authorityid=" + authorityid  
				        );
				}
			}
	})
})

//================按钮action====================
function add() {
	
}
function save() {
	$('#ff').form('submit', {
		url: "<%=basePath%>sysinit/base/authority/savModule",
		onSubmit: function(){
			var isValid = $(this).form('validate');
			if (!isValid){
				$.messager.progress('close');	// 如果表单是无效的则隐藏进度条
			}
			return isValid;	// 返回false终止表单提交
		},
		success: function(data){
			$.messager.progress('close');	// 如果提交成功则隐藏进度条
			
			$.messager.alert('提示','保存成功！');
			$('#modulegrid').datagrid('reload');
		}
	});
}
function remove() {
	
}



</script>
</head>
<body>
	<div id="cc" class="easyui-layout" style="width:auto;height:400px;overflow:auto;">   
	    <div data-options="region:'west',title:'模块树',split:true" style="width:480px;overflow:auto;">
	    	<ul class="easyui-tree" id="moduletree">
	    	</ul>
	    </div>
	   
	   	<div id="tab" class="easyui-tabs" style="width:auto;height:300px;overflow:auto;" data-options="region:'center'">
	   		<div id="lb" title="列表" class="easyui-layout" style="width:auto;height:100%;padding:20px;overflow:auto;" >
	   			<div data-options="region:'center',title:'模块',split:true">
	   				<table class="easyui-datagrid" id="modulegrid"  data-options="region:'center',fit:true,singleSelect:true,method:'get'"></table>
	   			</div>
	   			<div data-options="region:'south',title:'权限',split:true" style="height:200px;width:auto;"></div>  
	   		</div>
		    <div id="xx" title="详细" class="easyui-layout" style="width:auto;height:100%;padding:20px;overflow:auto;">
		    	 <div data-options="region:'center',title:'模块',split:true,fit:true" style="width:auto;overflow:auto;padding:3px 2px;border-bottom:1px solid #ccc">
		    	 	<div id="toolbar">
						<a href="#" class="easyui-linkbutton easyui-tooltip" onclick="add()" title="Add" data-options="iconCls:'icon-add',plain:true">重置/增加</a>
						<a href="#" class="easyui-linkbutton easyui-tooltip" onclick="save()" title="save" data-options="iconCls:'icon-save',plain:true">保存</a>
						<a href="#" class="easyui-linkbutton easyui-tooltip" onclick="remove()" title="Remove" data-options="iconCls:'icon-remove',plain:true">删除</a>
					</div>
		    	 	<form id="ff" method="post" data-options="region:'center'" style="overflow:auto;width:auto;">
		    	 		<input type="hidden" name="authorityid"></input>
		    	 		<table>
			    	 		<tr>
				    			<td>模块名称:</td>
				    			<td><input class="easyui-validatebox textbox" type="text" name="authorityname" data-options="required:true"></input></td>
				    			<td>地址:</td>
				    			<td><input class="easyui-validatebox textbox" type="text" name="modelurl" style="width:220px;" data-options="required:true"></input></td>
				    		</tr>
				    		<tr>
				    			<td>上级模块:</td>
				    			<td><select id="cct" class="easyui-combotree" style="width:131px;" name="parentauthorityid"></select></td>
				    			<td>模块排序:</td>
				    			<td><input class="easyui-validatebox textbox" type="text" name="orderindex" data-options="min:0,precision:0"></input></td>
				    		</tr>
				    		<tr>
				    			<td>是否公共:</td>
				    			<td><select class="easyui-combobox" name="ispublic" style="width:131px;"><option value="1" selected="selected">公有</option><option value="0">私有</option></select></td>
				    			<td>是否使用:</td>
				    			<td><select class="easyui-combobox" name="isuse"  style="width:131px;"><option value="1" selected="selected">使用</option><option value="0">停用</option></select></td>
				    		</tr>
				    		<tr>
				    			<td>备注:</td>
				    			<td><input class="textbox" name="authorityremark"></input></td>
				    			<!-- 
				    			<td>日期:</td>
				    			<td><input class="easyui-validatebox easyui-datetimebox" type="text" name="subject" data-options="required:true"></input></td>
				    			 -->
				    		</tr>
			    		</table>
			   	    </form>
		    	 </div> 
		    </div> 
		</div>  
	</div>  
</body>
</html>