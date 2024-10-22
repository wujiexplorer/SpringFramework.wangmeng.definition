<%@page import="com.jsh.util.Tools"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String clientIp = Tools.getLocalIp(request);
%>
<!DOCTYPE html>
<html>
  	<head>
    	<title>转账单</title>
        <meta charset="utf-8">
		<!-- 指定以IE8的方式来渲染 -->
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
    	<link rel="shortcut icon" href="<%=path%>/images/favicon.ico" type="image/x-icon" />    	
		<link rel="stylesheet" type="text/css" href="<%=path %>/js/easyui-1.3.5/themes/default/easyui.css"/>
		<link rel="stylesheet" type="text/css" href="<%=path %>/js/easyui-1.3.5/themes/icon.css"/>
		<link type="text/css" rel="stylesheet" href="<%=path %>/css/common.css" />
		<script src="<%=path %>/js/jquery-1.8.0.min.js"></script>
		<script src="<%=path %>/js/easyui-1.3.5/jquery.easyui.min.js"></script>
		<script src="<%=path %>/js/easyui-1.3.5/locale/easyui-lang-zh_CN.js"></script>
		<script src="<%=path %>/js/My97DatePicker/WdatePicker.js"></script>
		<script src="<%=path %>/js/common/common.js"></script>
		<script src="<%=path %>/js/pages/financial/financial_base.js"></script>
		<script>
			var kid = ${sessionScope.user.id};
			var path = "<%=path%>";
			var clientIp = "<%=clientIp%>";
		</script>
  	</head>
  	<body>
  		<!-- 查询 -->
		<div id = "searchPanel"	class="easyui-panel" style="padding:3px;" title="查询窗口" iconCls="icon-search" collapsible="true" closable="false">
			<table id="searchTable">
				<tr>
					<td>单据编号：</td>
					<td>
						<input type="text" name="searchBillNo" id="searchBillNo" style="width:100px;"/>
					</td>
					<td>单据日期：</td>
					<td>
						<input type="text" name="searchBeginTime" id="searchBeginTime" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="txt Wdate" style="width:100px;"/>
					</td>
					<td>-</td>
					<td>
						<input type="text" name="searchEndTime" id="searchEndTime" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="txt Wdate" style="width:100px;"/>
					</td>
					<td>&nbsp;</td>
					<td>
						<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" id="searchBtn">查询</a>&nbsp;
						<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-redo" id="searchResetBtn">重置</a> 
					</td>
				</tr>
			</table>
		</div>
		
		<!-- 数据显示table -->
		<div id = "tablePanel"	class="easyui-panel" style="padding:1px;top:300px;" title="转账单列表" iconCls="icon-list" collapsible="true" closable="false">
			<table id="tableData" style="top:300px;border-bottom-color:#FFFFFF"></table>
		</div>
		
	    <div id="accountHeadDlg" class="easyui-dialog" style="width:1200px;padding:10px 20px;top:20px"
	            closed="true" buttons="#dlg-buttons" modal="true" cache="false" collapsible="false" closable="true">
	        <form id="accountHeadFM" method="post"  novalidate>
	            <table>
					<tr>
						<td style="width:60px;">经手人：</td>
						<td style="padding:5px;width:170px;">
							<select name="HandsPersonId" id="HandsPersonId" style="width:110px;"></select>
						</td>
						<td style="width:70px;">单据日期：</td>
						<td style="padding:5px;width:170px;">
						<input type="text" name="BillTime" id="BillTime" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="txt Wdate" style="width:140px;"/>
						</td>
						<td style="width:80px;">单据编号：</td>
						<td style="padding:5px;width:170px;">
							<input name="BillNo" id="BillNo" class="easyui-validatebox" data-options="required:true,validType:'length[2,30]'" style="width: 140px;"/>
						</td>
						<td style="width:70px;"></td>
						<td style="padding:5px">
						</td>
						<td style="width:100px;"></td>
					</tr>
					<tr>
						<td colspan="9">
							<!-- 单据列表table -->
							<table id="accountData" style="top:100px;border-bottom-color:#FFFFFF"></table>
						</td>
					</tr>
					<tr>
						<td colspan="9">
							<textarea name="Remark" id="Remark" rows="2" cols="2" placeholder="暂无备注信息" style="width: 1130px; height:35px;"></textarea>
						</td>
					</tr>
					<tr>
						<td>付款账户：</td>
						<td style="padding:5px">
							<select id="AccountId" name="AccountId" style="width:110px;"></select>
						</td>
						<td>实付金额：</td>
						<td style="padding:5px">
							<input type="text" name="ChangeAmount" id="ChangeAmount" class="easyui-numberbox" data-options="min:0,precision:2" style="width: 110px;"></input>
						</td>
					</tr>
	            </table>
	            <input type="hidden" name="clientIp" id="clientIp" value="<%=clientIp %>"/>
	        </form>
	    </div>
	    <div id="dlg-buttons">
	        <a href="javascript:void(0)" id="saveAccountHead" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
	        <a href="javascript:void(0)" id="cancelAccountHead" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#accountHeadDlg').dialog('close')">取消</a>
	    </div>
	    <div id="accountHeadDlgShow" class="easyui-dialog" style="width:1200px;padding:10px 20px;top:20px"
	            closed="true" modal="true" cache="false" collapsible="false" closable="true">
			<table>
				<tr>
					<td style="width:60px;">经手人：</td>
					<td style="padding:5px;width:130px;">
						<span id="HandsPersonIdShow"></span>
					</td>
					<td style="width:70px;">单据日期：</td>
					<td style="padding:5px;width:130px;">
						<span id="BillTimeShow"></span>
					</td>
					<td style="width:80px;">单据编号：</td>
					<td style="padding:5px;width:130px;">
					<span id="BillNoShow"></span>
					</td>
					<td style="width:50px;"></td>
					<td style="padding:5px;width:110px;"></td>
					<td style="width:100px;"></td>
				</tr>
				<tr>
					<td colspan="9" style="width: 1130px;">
						<!-- 单据列表table -->
						<table id="accountDataShow" style="top:100px;border-bottom-color:#FFFFFF"></table>
					</td>
				</tr>
				<tr>
					<td style="width:60px;">单据备注：</td>
					<td colspan="8" style="height:35px;">
						<span id="RemarkShow" style="width: 1070px; height:35px;"></span>
					</td>
				</tr>
				<tr>
					<td>付款账户：</td>
					<td style="padding:5px">
						<span id="AccountIdShow"></span>
					</td>
					<td>实付金额：</td>
					<td style="padding:5px">
						<span id="ChangeAmountShow"></span>
					</td>
				</tr>
			</table>
	    </div>
	</body>
</html>
