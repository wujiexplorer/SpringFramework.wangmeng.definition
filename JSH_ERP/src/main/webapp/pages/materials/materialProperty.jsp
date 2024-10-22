<%@page import="com.jsh.util.Tools"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String clientIp = Tools.getLocalIp(request);
%>
<!DOCTYPE html>
<html>
<head>
	<title>商品属性</title>
	<meta charset="utf-8">
	<!-- 指定以IE8的方式来渲染 -->
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
	<link rel="shortcut icon" href="<%=path%>/images/favicon.ico" type="image/x-icon" />
	<script type="text/javascript" src="<%=path %>/js/jquery-1.8.0.min.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=path %>/js/easyui-1.3.5/themes/default/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="<%=path %>/js/easyui-1.3.5/themes/icon.css"/>
	<link type="text/css" rel="stylesheet" href="<%=path %>/css/common.css" />
	<script type="text/javascript" src="<%=path %>/js/easyui-1.3.5/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=path %>/js/easyui-1.3.5/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="<%=path %>/js/common/common.js"></script>
</head>
<body>
<!-- 查询 -->
<div id = "searchPanel"	class="easyui-panel" style="padding:10px;" title="查询窗口" iconCls="icon-search" collapsible="true" closable="false">
	<table id="searchTable">
		<tr>
			<td>名称：</td>
			<td>
				<input name="searchNativeName" id="searchNativeName" style="width:120px;"/>
			</td>
			<td>&nbsp;</td>
			<td>
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" id="searchBtn">查询</a>&nbsp;&nbsp;
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-redo" id="searchResetBtn">重置</a>
			</td>
		</tr>
	</table>
</div>

<!-- 数据显示table -->
<div id = "tablePanel"	class="easyui-panel" style="padding:1px;top:300px;" title="商品属性列表" iconCls="icon-list" collapsible="true" closable="false">
	<table id="tableData" style="top:300px;border-bottom-color:#FFFFFF"></table>
</div>

<div id="materialPropertyDlg" class="easyui-dialog" style="width:380px;padding:10px 20px"
	 closed="true" buttons="#dlg-buttons" modal="true" cache="false" collapsible="false" closable="true">
	<form id="materialPropertyFM" method="post"  novalidate>
		<table>
			<tr>
				<td>名称</td>
				<td style="padding:5px">
					<span id="nativeName"  />
				</td>
			</tr>
			<tr>
				<td>是否启用</td>
				<td style="padding:5px">
					<input name="enabled" id="enabled" type="checkbox" style="width: 50px;height: 20px"/>
				</td>
			</tr>
			<tr>
				<td>排序</td>
				<td style="padding:5px">
					<input name="sort" id="sort" class="easyui-validatebox" data-options="required:true,validType:'length[2,30]'" style="width: 230px;height: 20px"/>
				</td>
			</tr>
			<tr>
				<td>别名</td>
				<td style="padding:5px">
					<input name="anotherName" id="anotherName" class="easyui-validatebox" data-options="validType:'length[2,30]'" style="width: 230px;height: 20px"/>
				</td>
			</tr>
		</table>
		<input type="hidden" name="clientIp" id="clientIp" value="<%=clientIp %>"/>
	</form>
</div>
<div id="dlg-buttons">
	<a href="javascript:void(0)" id="saveMaterialProperty" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
	<a href="javascript:void(0)" id="cancelMaterialProperty" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#materialPropertyDlg').dialog('close')">取消</a>
</div>

<script type="text/javascript">
	//初始化界面
	$(function() {
		initTableData();
		ininPager();
		initForm();
	});

	//防止表单提交重复
	function initForm() {
		$('#materialPropertyFM').form({
			onSubmit: function(){
				return false;
			}
		});
	}

	//初始化表格数据
	function initTableData() {
		$('#tableData').datagrid({
			height:heightInfo,
			nowrap: false,
			rownumbers: true,
			//动画效果
			animate:false,
			//选中单行
			singleSelect : true,
			collapsible:false,
			selectOnCheck:false,
			//fitColumns:true,
			//单击行是否选中
			//checkOnSelect : false,
			url:'<%=path %>/materialProperty/findBy.action?pageSize=' + initPageSize,
			pagination: false,
			//交替出现背景
			striped : true,
			//loadFilter: pagerFilter,
			columns:[[
				{ field: 'id',width:10,align:"center",hidden:true},
				{ title: '名称',field: 'nativeName',width:100},
				{ title: '是否启用',field: 'enabled',width:100,formatter:function(value,rec){
					if(rec.enabled){
						return "启用";
					}
					else {
						return "禁用";
					}
				}},
				{ title: '排序',field: 'sort',width:100},
				{ title: '别名',field: 'anotherName',width:100},
				{ title: '操作',field: 'op',align:"center",width:80,formatter:function(value,rec)
					{
						var str = '';
						var rowInfo = rec.id + 'AaBb' + rec.nativeName + 'AaBb' + rec.enabled + 'AaBb' + rec.sort + 'AaBb' + rec.anotherName;
						str += '<img src="<%=path%>/js/easyui-1.3.5/themes/icons/pencil.png" style="cursor: pointer;" onclick="editMaterialProperty(\'' + rowInfo + '\');"/>&nbsp;<a onclick="editMaterialProperty(\'' + rowInfo + '\');" style="text-decoration:none;color:black;" href="javascript:void(0)">编辑</a>&nbsp;&nbsp;';
						return str;
					}
				}
			]],
			onLoadError:function()
			{
				$.messager.alert('页面加载提示','页面加载异常，请稍后再试！','error');
				return;
			}
		});
	}

	//初始化键盘enter事件
	$(document).keydown(function(event) {
		//兼容 IE和firefox 事件
		var e = window.event || event;
		var k = e.keyCode||e.which||e.charCode;
		//兼容 IE,firefox 兼容
		var obj = e.srcElement ? e.srcElement : e.target;
		//绑定键盘事件为 id是指定的输入框才可以触发键盘事件 13键盘事件 ---遗留问题 enter键效验 对话框会关闭问题
		if(k == "13"&&(obj.id=="nativeName")) {
			$("#saveMaterialProperty").click();
		}
		//搜索按钮添加快捷键
		if(k == "13"&&(obj.id=="searchNativeName")) {
			$("#searchBtn").click();
		}
	});
	//分页信息处理
	function ininPager() {
		try
		{
			var opts = $("#tableData").datagrid('options');
			var pager = $("#tableData").datagrid('getPager');
			pager.pagination({
				onSelectPage:function(pageNum, pageSize) {
					opts.pageNumber = pageNum;
					opts.pageSize = pageSize;
					pager.pagination('refresh', {
						pageNumber:pageNum,
						pageSize:pageSize
					});
					showMaterialPropertyDetails(pageNum,pageSize);
				}
			});
		}
		catch (e)
		{
			$.messager.alert('异常处理提示',"分页信息异常 :  " + e.name + ": " + e.message,'error');
		}
	}

	//增加
	var url;

	//保存信息
	$("#saveMaterialProperty").unbind().bind({
		click:function() {
			if(!$('#materialPropertyFM').form('validate'))
				return;
			else {
				$.ajax({
					type:"post",
					url: url,
					dataType: "json",
					async :  false,
					data: ({
						nativeName : $.trim($("#nativeName").val()),
						enabled : $("#enabled").is(':checked'),
						sort : $.trim($("#sort").val()),
						anotherName : $.trim($("#anotherName").val()),
						clientIp:'<%=clientIp %>'
					}),
					success: function (tipInfo) {
						if(tipInfo) {
							$('#materialPropertyDlg').dialog('close');
							var opts = $("#tableData").datagrid('options');
							showMaterialPropertyDetails(opts.pageNumber,opts.pageSize);
						}
						else {
							$.messager.show({
								title: '错误提示',
								msg: '保存商品属性失败，请稍后重试!'
							});
						}
					},
					//此处添加错误处理
					error:function() {
						$.messager.alert('提示','保存商品属性异常，请稍后再试！','error');
						return;
					}
				});
			}
		}
	});

	//编辑信息
	function editMaterialProperty(totalInfo) {
		var materialPropertyInfo = totalInfo.split("AaBb");

		$("#clientIp").val('<%=clientIp %>');
		$("#nativeName").text(materialPropertyInfo[1]);
		$("#enabled").attr("checked",materialPropertyInfo[2]=='true'?true:false);
		$("#sort").val(materialPropertyInfo[3]);
		$("#anotherName").val(materialPropertyInfo[4]);

		$('#materialPropertyDlg').dialog('open').dialog('setTitle','<img src="<%=path%>/js/easyui-1.3.5/themes/icons/pencil.png"/>&nbsp;编辑商品属性');
		$(".window-mask").css({ width: webW ,height: webH});
		//焦点在名称输入框==定焦在输入文字后面
		$("#nativeName").val("").focus().val(materialPropertyInfo[1]);
		url = '<%=path %>/materialProperty/update.action?id=' + materialPropertyInfo[0];
	}

	//搜索处理
	$("#searchBtn").unbind().bind({
		click:function()
		{
			showMaterialPropertyDetails(1,initPageSize);
			var opts = $("#tableData").datagrid('options');
			var pager = $("#tableData").datagrid('getPager');
			opts.pageNumber = 1;
			opts.pageSize = initPageSize;
			pager.pagination('refresh', {
				pageNumber:1,
				pageSize:initPageSize
			});
		}
	});

	function showMaterialPropertyDetails(pageNo,pageSize) {
		$.ajax({
			type:"post",
			url: "<%=path %>/materialProperty/findBy.action",
			dataType: "json",
			data: ({
				nativeName:$.trim($("#searchNativeName").val()),
				pageNo:pageNo,
				pageSize:pageSize
			}),
			success: function (data) {
				$("#tableData").datagrid('loadData',data);
			},
			//此处添加错误处理
			error:function()
			{
				$.messager.alert('查询提示','查询数据后台异常，请稍后再试！','error');
				return;
			}
		});
	}

	//重置按钮
	$("#searchResetBtn").off().on("click",function(){
		$("#searchNativeName").val("");
		//加载完以后重新初始化
		$("#searchBtn").click();
	});

</script>
</body>
</html>