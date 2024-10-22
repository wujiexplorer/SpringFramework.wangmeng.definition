<%@page import="com.jsh.util.Tools"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String clientIp = Tools.getLocalIp(request);
%>
<!DOCTYPE html>
<html>
  	<head>
    	<title>库存状况</title>
        <meta charset="utf-8">
		<!-- 指定以IE8的方式来渲染 -->
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
    	<link rel="shortcut icon" href="<%=path%>/images/favicon.ico" type="image/x-icon" />
    	<script type="text/javascript" src="<%=path %>/js/jquery-1.8.0.min.js"></script>
		<script type="text/javascript" src="<%=path %>/js/print/print.js"></script>
		<link rel="stylesheet" type="text/css" href="<%=path %>/js/easyui-1.3.5/themes/default/easyui.css"/>
		<link rel="stylesheet" type="text/css" href="<%=path %>/js/easyui-1.3.5/themes/icon.css"/>
		<link type="text/css" rel="stylesheet" href="<%=path %>/css/common.css" />
		<script type="text/javascript" src="<%=path %>/js/easyui-1.3.5/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="<%=path %>/js/easyui-1.3.5/locale/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript" src="<%=path %>/js/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="<%=path %>/js/common/common.js"></script>
		<script>
			var kid = ${sessionScope.user.id};
		</script>
  	</head>
  	<body>
  		<!-- 查询 -->
		<div id = "searchPanel"	class="easyui-panel" style="padding:10px;" title="查询窗口" iconCls="icon-search" collapsible="true" closable="false">
			<table id="searchTable">
				<tr>
					<td>仓库：</td>
					<td>
						<select name="searchProjectId" id="searchProjectId"  style="width:80px;"></select>
					</td>
					<td>&nbsp;</td>
			    	<td>月份：</td>
					<td>
						<input type="text" name="searchMonth" id="searchMonth" onClick="WdatePicker({dateFmt:'yyyy-MM'})" class="txt Wdate" style="width:180px;"/>
					</td>
					<td>&nbsp;</td>
					<td>
						<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" id="searchBtn">查询</a>
						&nbsp;&nbsp;
						<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-redo" id="exprotBtn">导出</a>
						&nbsp;&nbsp;
						<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-print" id="printBtn">打印</a>
						&nbsp;&nbsp;<span class="total-count"></span>
					</td>
				</tr>
			</table>
		</div>
		
		<!-- 数据显示table -->
		<div id = "tablePanel"	class="easyui-panel" style="padding:1px;top:300px;" title="库存状况列表" iconCls="icon-list" collapsible="true" closable="false">
			<table id="tableData" style="top:300px;border-bottom-color:#FFFFFF"></table>
		</div>
			    
		<script type="text/javascript">
			var depotList = null;
			var depotID = null;
			var mPropertyList = ""; //商品属性列表
			//初始化界面
			$(function()
			{
				var thisDate = getNowFormatMonth(); //当前月份
				$("#searchMonth").val(thisDate);
				var userBusinessList=null;
				var userdepot=null;
				initSystemData_UB();
				initSelectInfo_UB();
				initSystemData_depot();
				initSelectInfo_depot();
				initMProperty(); //初始化商品属性
				initTableData();
				ininPager();
				search();
				exportExcel();
				print();
			});	
			
			//导出EXCEL
			function exportExcel() {
				$("#exprotBtn").off("click").on("click",function(){
					if(!$("#searchPanel .total-count").text()) {
						$.messager.alert('导出提示','请先选择月份再进行查询！','error');
					}
					else {
						showEachDetails(1,3000);
						//此处直接去做get请求，用下面的查询每月统计的方法，去获取list，参数长度虽长，但还是可以用get
						//window.location.href = "<%=path%>/depotItem/exportExcel.action?browserType=" + getOs();
					}
				});				
			}

			//初始化系统基础信息
			function initSystemData_UB(){
				$.ajax({
					type:"post",
					url: "<%=path %>/userBusiness/getBasicData.action",
					data: ({
						KeyId:kid,
						Type:"UserDepot"
					}),
					//设置为同步
					async:false,
					dataType: "json",
					success: function (systemInfo)
					{
						if(systemInfo)
						{
							userBusinessList = systemInfo.showModel.map.userBusinessList;
							var msgTip = systemInfo.showModel.msgTip;
							if(msgTip == "exceptoin")
							{
								$.messager.alert('提示','查找UserBusiness异常,请与管理员联系！','error');
								return;
							}
						}
						else
						{
							userBusinessList=null;
						}
					}
				});

			}
			//初始化页面选项卡
			function initSelectInfo_UB(){

				if(userBusinessList !=null)
				{
					if(userBusinessList.length>0)
					{
						//用户对应的仓库列表 [1][2][3]...
						userdepot =userBusinessList[0].value;
					}
				}
			}


			//初始化系统基础信息
			function initSystemData_depot(){
				$.ajax({
					type:"post",
					url: "<%=path %>/depot/getBasicData.action",
					//设置为同步
					async:false,
					dataType: "json",
					success: function (systemInfo)
					{
						depotList = systemInfo.showModel.map.depotList;
						var msgTip = systemInfo.showModel.msgTip;
						if(msgTip == "exceptoin")
						{
							$.messager.alert('提示','查找系统基础信息异常,请与管理员联系！','error');
							return;
						}
					}
				});
			}
			//初始化页面选项卡
			function initSelectInfo_depot(){
				var options = "";

				if(depotList !=null)
				{
					options = "";
					for(var i = 0 ;i < depotList.length;i++)
					{
						var depot = depotList[i];

						if(userdepot!=null)
						{
							if(userdepot.indexOf("["+depot.id+"]")!=-1)
							{
								options += '<option value="' + depot.id + '">' + depot.name + '</option>';
							}
						}
					}
					$("#searchProjectId").empty().append(options);
				}
			}

			//初始化商品属性
			function initMProperty(){
				$.ajax({
					type: "post",
					url: "<%=path %>/materialProperty/findBy.action",
					dataType: "json",
					success: function (res) {
						if (res && res.rows) {
							var thisRows = res.rows;
							for(var i=0; i < thisRows.length; i++) {
								if(thisRows[i].enabled){
									mPropertyList += thisRows[i].nativeName +",";
								}
							}
							if(mPropertyList){
								mPropertyList = mPropertyList.substring(0,mPropertyList.length-1);
							}
						}
					},
					//此处添加错误处理
					error:function() {
						$.messager.alert('查询提示','查询信息异常，请稍后再试！','error');
						return;
					}
				});
			}
			
			//初始化表格数据
			function initTableData()
			{
				$('#tableData').datagrid({
					height:heightInfo,
					nowrap: false,
					rownumbers: true,
					//动画效果
					animate:false,
					//选中单行
					singleSelect : true,
					pagination: true,
					//交替出现背景
					striped : true,
					//loadFilter: pagerFilter,
					pageSize: 10,
					pageList: [10,50,100],
					columns:[[
			          { title: '名称',field: 'MaterialName',width:60},
			          { title: '型号',field: 'MaterialModel',width:80},
					  { title: '扩展信息',field: 'MaterialOther',width:150},
					  { title: '单位',field: 'MaterialUnit',width:80},
			          { title: '单价',field: 'UnitPrice',width:60,formatter: function(value,row,index){
							return value.toFixed(2);
						}
					  },
			          { title: '上月结存数量',field: 'prevSum',width:80},
			          { title: '入库数量',field: 'InSum',width:60},
			          { title: '出库数量',field: 'OutSum',width:60},
			          { title: '本月结存数量',field: 'thisSum',width:80},
			          { title: '结存金额',field: 'thisAllPrice',width:60,
						formatter: function(value,row,index){
							return value.toFixed(2);
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
			$(document).keydown(function(event)
			{  
			   	//兼容 IE和firefox 事件 
			    var e = window.event || event;  
			    var k = e.keyCode||e.which||e.charCode;  
			    //兼容 IE,firefox 兼容  
			    var obj = e.srcElement ? e.srcElement : e.target;  
			    //绑定键盘事件为 id是指定的输入框才可以触发键盘事件 13键盘事件 ---遗留问题 enter键效验 对话框会关闭问题
			    if(k == "13"&&(obj.id=="Type"||obj.id=="Name"))
			    {  
			        $("#savePerson").click();
			    }
			    //搜索按钮添加快捷键
			    if(k == "13"&&(obj.id=="searchType"))
			    {  
			        $("#searchBtn").click();
			    }  
			}); 
			//分页信息处理
			function ininPager()
			{
				try
				{
					var opts = $("#tableData").datagrid('options');  
					var pager = $("#tableData").datagrid('getPager'); 
					pager.pagination({  
						onSelectPage:function(pageNum, pageSize)
						{  
							opts.pageNumber = pageNum;  
							opts.pageSize = pageSize;  
							pager.pagination('refresh',
							{  
								pageNumber:pageNum,  
								pageSize:pageSize  
							});
							showEachDetails(pageNum,pageSize);
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
			var personID = 0;
			//保存编辑前的名称
			var orgPerson = "";

			//搜索处理
			function search() {
				showEachDetails(1,initPageSize);
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
			$("#searchBtn").unbind().bind({
				click:function()
				{
					search();
				}
			});
			
			function showEachDetails(pageNo,pageSize)
			{
				$.ajax({
					type:"post",
					url: "<%=path %>/depotHead/findByMonth.action",
					dataType: "json",
					data: ({
						MonthTime:$("#searchMonth").val()
					}),
					success: function (res)
					{
						var HeadIds = res.HeadIds;
						if(HeadIds) {
							//获取排序后的产品ID
							$.ajax({
								type:"post",
								url: "<%=path %>/material/findByOrder.action",
								dataType: "json",
								success: function (resNew)
								{
									var MIds = resNew.mIds;
									if(MIds) {
										if(pageSize === 3000) {
											window.location.href = "<%=path%>/depotItem/exportExcel.action?browserType=" + getOs() + "&pageNo=" + pageNo + "&pageSize=" + pageSize + "&ProjectId="+ $.trim($("#searchProjectId").val()) +"&MonthTime=" + $("#searchMonth").val() + "&HeadIds=" + HeadIds + "&MaterialIds=" + MIds;
										}
										else {
											$.ajax({
												type:"post",
												url: "<%=path %>/depotItem/findByAll.action",
												dataType: "json",
												data: ({
													pageNo:pageNo,
													pageSize:pageSize,
													ProjectId: $.trim($("#searchProjectId").val()),
													MonthTime:$("#searchMonth").val(),
													HeadIds:HeadIds,
													MaterialIds:MIds,
													mpList: mPropertyList
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
																					
											//总金额
											$.ajax({
												type:"post",
												url: "<%=path %>/depotItem/totalCountMoney.action",
												dataType: "json",
												data: ({
													ProjectId: $.trim($("#searchProjectId").val()),
													MonthTime:$("#searchMonth").val(),
													HeadIds:HeadIds,
													MaterialIds:MIds
												}),
												success: function (data)
												{
													if(data && data.totalCount) {
														var count = data.totalCount.toString();
														if(count.lastIndexOf('.')>-1){
															count = count.substring(0,count.lastIndexOf('.')+3);
														}
														$("#searchPanel .total-count").text("本月合计金额:" + count + "元");//本月合计金额
													}
												},
												//此处添加错误处理
									    		error:function()
									    		{
									    			$.messager.alert('查询提示','查询数据后台异常，请稍后再试！','error');
													return;
												}
											});
										}										
									}
									else {
										$.messager.alert('查询提示','本月无数据！','error');
									}
								},
								//此处添加错误处理							
					    		error:function()
					    		{
					    			$.messager.alert('查询提示','查询数据后台异常，请稍后再试！','error');
									return;
								}
							});
						}
						else {
							$.messager.alert('查询提示','本月无数据！','error');
						}
					},
					//此处添加错误处理
		    		error:function()
		    		{
		    			$.messager.alert('查询提示','查询数据后台异常，请稍后再试！','error');
						return;
					}
				});
			}
			//报表打印
			function print() {
				$("#printBtn").off("click").on("click",function(){
					var path = "<%=path %>";
					CreateFormPage('打印报表', $('#tableData'), path);
				});
			}
		</script>
	</body>
</html>