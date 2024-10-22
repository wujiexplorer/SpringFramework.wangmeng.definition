<%@page import="com.jsh.util.Tools"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String clientIp = Tools.getLocalIp(request);
%>
<!DOCTYPE html>
<html>
  	<head>
    	<title>计量单位管理</title>
        <meta charset="utf-8">
		<!-- 指定以IE8的方式来渲染 -->
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
    	<link rel="shortcut icon" href="<%=path%>/images/favicon.ico" type="image/x-icon" />
		<link rel="stylesheet" type="text/css" href="<%=path %>/js/easyui-1.3.5/themes/default/easyui.css"/>
		<link rel="stylesheet" type="text/css" href="<%=path %>/js/easyui-1.3.5/themes/icon.css"/>
		<link type="text/css" rel="stylesheet" href="<%=path %>/css/common.css" />
		<script type="text/javascript" src="<%=path %>/js/jquery-1.8.0.min.js"></script>
		<script type="text/javascript" src="<%=path %>/js/easyui-1.3.5/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="<%=path %>/js/easyui-1.3.5/locale/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript" src="<%=path %>/js/common/common.js"></script>
  	</head>
  	<body>
  		<!-- 查询 -->
		<div id = "searchPanel"	class="easyui-panel" style="padding:10px;" title="查询窗口" iconCls="icon-search" collapsible="true" closable="false">
			<table id="searchTable">
				<tr>
					<td>计量单位：</td>
					<td>
						<input type="text" name="searchName" id="searchName"  style="width:100px;"/>
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
		<div id = "tablePanel"	class="easyui-panel" style="padding:1px;top:300px;" title="计量单位列表" iconCls="icon-list" collapsible="true" closable="false">
			<table id="tableData" style="top:300px;border-bottom-color:#FFFFFF"></table>
		</div>
		
	    <div id="unitDlg" class="easyui-dialog" style="width:380px;padding:10px 20px"
	            closed="true" buttons="#dlg-buttons" modal="true" cache="false" collapsible="false" closable="true">
	        <form id="unitFM" method="post"  novalidate>
	            <table>
	            <tr>
					<td>基本单位</td>
					<td style="padding:5px">
						<input name="basicName" id="basicName" class="easyui-validatebox" data-options="required:true,validType:'length[1,10]'" style="width: 100px;height: 20px"/>
						基本单位应为最小度量单位
					</td>
	            </tr>
				<tr>
					<td>副单位</td>
					<td style="padding:5px">
						<input name="otherName" id="otherName" class="easyui-validatebox" data-options="required:true,validType:'length[1,5]'" style="width: 100px;height: 20px"/>
						=
						<input name="otherNum" id="otherNum" class="easyui-validatebox" data-options="required:true,validType:'length[1,5]'" style="width: 50px;height: 20px"/>
						<span id="unitName"></span>
					</td>
				</tr>
	            </table>
	            <input type="hidden" name="clientIp" id="clientIp" value="<%=clientIp %>"/>
	        </form>
	    </div>
	    <div id="dlg-buttons">
	        <a href="javascript:void(0)" id="saveUnit" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
	        <a href="javascript:void(0)" id="cancelUnit" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#unitDlg').dialog('close')">取消</a>
	    </div>
	    
		<script type="text/javascript">
			//初始化界面
			$(function()
			{
				initTableData();
				ininPager();
				initForm();
			});
			
			//防止表单提交重复
			function initForm()
			{
				$('#unitFM').form({
				    onSubmit: function(){
				        return false;
				    }
				});
			}
			
			//初始化表格数据
			function initTableData() {
				$('#tableData').datagrid({
					//title:'计量单位列表',
					//iconCls:'icon-save',
					//width:700,
					height:heightInfo,
					nowrap: false,
					rownumbers: false,
					//动画效果
					animate:false,
					//选中单行
					singleSelect : true,
					collapsible:false,
					selectOnCheck:false,
					//fitColumns:true,
					//单击行是否选中
					checkOnSelect : false,
					url:'<%=path %>/unit/findBy.action?pageSize=' + initPageSize,
					pagination: true,
					//交替出现背景
					striped : true,
					//loadFilter: pagerFilter,
					pageSize: initPageSize,
					pageList: initPageNum,
					columns:[[
						{ field: 'id',width:35,align:"center",checkbox:true},
						{ title: '操作',field: 'op',align:"center",width:60,
							formatter:function(value,rec) {
								var str = '';
								var rowInfo = rec.id + 'AaBb' + rec.UName;
								if(1 == value)
								{
									str += '<img title="编辑" src="<%=path%>/js/easyui-1.3.5/themes/icons/pencil.png" style="cursor: pointer;" onclick="editUnit(\'' + rowInfo + '\');"/>&nbsp;&nbsp;&nbsp;';
									str += '<img title="删除" src="<%=path%>/js/easyui-1.3.5/themes/icons/edit_remove.png" style="cursor: pointer;" onclick="deleteUnit('+ rec.id +');"/>';
								}
								return str;
							}
						},
						{ title: '计量单位',field: 'UName',width:200}
					]],
					toolbar:[
						{
							id:'addUnit',
							text:'增加',
							iconCls:'icon-add',
							handler:function() {
								addUnit();
							}
						},'-',
						{
							id:'deleteUnit',
							text:'删除',
							iconCls:'icon-remove',
							handler:function() {
								batDeleteUnit();
							}
						}
					],
					onLoadError:function() {
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
			    if(k == "13"&&(obj.id=="name")) {
			        $("#saveUnit").click();
			    }
			    //搜索按钮添加快捷键
			    if(k == "13"&&(obj.id=="searchName")) {
			        $("#searchBtn").click();
			    }  
			}); 
			//分页信息处理
			function ininPager() {
				try {
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
							showUnitDetails(pageNum,pageSize);
						}  
					}); 
				}
				catch (e) {
					$.messager.alert('异常处理提示',"分页信息异常 :  " + e.name + ": " + e.message,'error');
				}
			}
			
			//删除计量单位
			function deleteUnit(unitID) {
				$.messager.confirm('删除确认','确定要删除此计量单位吗？',function(r) {
                    if (r) {
						$.ajax({
							type:"post",
							url: "<%=path %>/unit/delete.action",
							dataType: "json",
							data: ({
								unitID : unitID,
								clientIp:'<%=clientIp %>'
							}),
							success: function (tipInfo) {
								var msg = tipInfo.showModel.msgTip;
								if(msg == '成功')
								{
									//加载完以后重新初始化
									$("#searchBtn").click();
								}
								else
									$.messager.alert('删除提示','删除计量单位失败，请稍后再试！','error');
							},
							//此处添加错误处理
				    		error:function() {
				    			$.messager.alert('删除提示','删除计量单位异常，请稍后再试！','error');
								return;
							}
						});			
                    }
                });
			}
			
			//批量删除计量单位
			function batDeleteUnit() {
				var row = $('#tableData').datagrid('getChecked');	
				if(row.length == 0) {
					$.messager.alert('删除提示','没有记录被选中！','info');				
					return;	
				}
				if(row.length > 0) {
					$.messager.confirm('删除确认','确定要删除选中的' + row.length + '条计量单位信息吗？',function(r) {
	                    if (r)
	                    {
	                    	var ids = "";
	                        for(var i = 0;i < row.length; i ++)
	                        {
	                        	if(i == row.length-1)
	                        	{
	                        		ids += row[i].id;
	                        		break;
	                        	}
	                        	//alert(row[i].id);
	                        	ids += row[i].id + ",";
	                        }
	                        $.ajax({
								type:"post",
								url: "<%=path %>/unit/batchDelete.action",
								dataType: "json",
								async :  false,
								data: ({
									unitIDs : ids,
									clientIp:'<%=clientIp %>'
								}),
								success: function (tipInfo) {
									var msg = tipInfo.showModel.msgTip;
									if(msg == '成功'){
										//加载完以后重新初始化
										$("#searchBtn").click();
										$(":checkbox").attr("checked",false);
									}
									else
										$.messager.alert('删除提示','删除计量单位失败，请稍后再试！','error');
								},
								//此处添加错误处理
					    		error:function(){
					    			$.messager.alert('删除提示','删除计量单位异常，请稍后再试！','error');
									return;
								}
							});	
	                    }
	                });
				 }
			}
			
			//增加
			var url;
			var unitID = 0;
			//保存编辑前的名称
			var orgUnit = "";
			
			function addUnit(){
				$("#clientIp").val('<%=clientIp %>');
				$('#unitDlg').dialog('open').dialog('setTitle','<img src="<%=path%>/js/easyui-1.3.5/themes/icons/edit_add.png"/>&nbsp;增加计量单位');
				$(".window-mask").css({ width: webW ,height: webH});
	            $('#unitFM').form('clear');
				$("#name").focus();
	            $("#unitName").text("");
	            orgUnit = "";
	            unitID = 0;
	            url = '<%=path %>/unit/create.action';
			}
			
			//保存信息
			$("#saveUnit").unbind().bind({
				click:function()
				{
					if(!$('#unitFM').form('validate'))
						return;
					else if(checkUnitName())
						return;
					else 
					{
						var basicName = $.trim($("#basicName").val());
						var otherName = $.trim($("#otherName").val());
						var otherNum = $.trim($("#otherNum").val());
						var name = basicName + "," + otherName + "(1:" + otherNum + ")";
						$.ajax({
							type:"post",
							url: url,
							dataType: "json",
							async :  false,
							data: ({
								UName : name,
								clientIp:'<%=clientIp %>'
							}),
							success: function (tipInfo)
							{
								if(tipInfo)
								{
									$('#unitDlg').dialog('close');
			                        
									var opts = $("#tableData").datagrid('options'); 
									showUnitDetails(opts.pageNumber,opts.pageSize);
								}
								else
								{
									$.messager.show({
			                            title: '错误提示',
			                            msg: '保存计量单位失败，请稍后重试!'
			                        });
								}
							},
							//此处添加错误处理
				    		error:function()
				    		{
				    			$.messager.alert('提示','保存计量单位异常，请稍后再试！','error');
								return;
							}
						});	
					}
				}
			});
			
			//编辑信息
	        function editUnit(unitTotalInfo) {
	        	var unitInfo = unitTotalInfo.split("AaBb");
	            
	            $("#clientIp").val('<%=clientIp %>');
	            
	            orgUnit = unitInfo[1];
                $('#unitDlg').dialog('open').dialog('setTitle','<img src="<%=path%>/js/easyui-1.3.5/themes/icons/pencil.png"/>&nbsp;编辑计量单位');
                $(".window-mask").css({ width: webW ,height: webH});
                unitID = unitInfo[0];
                //焦点在名称输入框==定焦在输入文字后面 
                var name = unitInfo[1];
				var basicName = name.substring(0, name.indexOf(",")); //基础单位
				$("#basicName").val(basicName);
				var otherItem = name.substring(name.indexOf(",")+1);
				var otherName = otherItem.substring(0,otherItem.indexOf("("));
				$("#otherName").val(otherName);
				var lastNum =  otherItem.substring(otherItem.indexOf(":")+1);
				lastNum = lastNum.replace(")","");
				$("#otherNum").val(lastNum);
				$("#unitName").text(basicName);
                url = '<%=path %>/unit/update.action?unitID=' + unitInfo[0];
	        }
	        
	        //检查名称是否存在 ++ 重名无法提示问题需要跟进
	        function checkUnitName()
	        {
	        	var name = $.trim($("#name").val());
	        	//表示是否存在 true == 存在 false = 不存在
	        	var flag = false;
        		//开始ajax名称检验，不能重名
        		if(name.length > 0 &&( orgUnit.length ==0 || name != orgUnit))
        		{
        			$.ajax({
						type:"post",
						url: "<%=path %>/unit/checkIsNameExist.action",
						dataType: "json",
						async :  false,
						data: ({
							unitID : unitID,
							UName : name
						}),
						success: function (tipInfo){
							flag = tipInfo;
							if(tipInfo)
							{
								$.messager.alert('提示','计量单位名称已经存在','info');
								return;
							}
						},
						//此处添加错误处理
			    		error:function()
			    		{
			    			$.messager.alert('提示','检查计量单位名称是否存在异常，请稍后再试！','error');
							return;
						}
					});	
        		}
        		return flag;
	        }
			
			//搜索处理
			$("#searchBtn").unbind().bind({
				click:function()
				{
					showUnitDetails(1,initPageSize);
					var opts = $("#tableData").datagrid('options');  
					var pager = $("#tableData").datagrid('getPager'); 
					opts.pageNumber = 1;  
					opts.pageSize = initPageSize;  
					pager.pagination('refresh',
					{  
						pageNumber:1,  
						pageSize:initPageSize  
					});  
				}
			});
			
			function showUnitDetails(pageNo,pageSize)
			{
				$.ajax({
					type:"post",
					url: "<%=path %>/unit/findBy.action",
					dataType: "json",
					data: ({
						UName:$.trim($("#searchName").val()),
						pageNo:pageNo,
						pageSize:pageSize
					}),
					success: function (data)
					{
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
			$("#searchResetBtn").unbind().bind({
				click:function(){
					$("#searchName").val("");
					//加载完以后重新初始化
					$("#searchBtn").click();
			    }	
			});

			//单位名称事件
			$("#basicName").off("keyup").on("keyup", function(){
				$("#unitName").text($(this).val());
			});
		</script>
	</body>
</html>