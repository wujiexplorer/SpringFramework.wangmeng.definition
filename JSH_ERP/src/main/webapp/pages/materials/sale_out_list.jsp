<%@page import="com.jsh.util.Tools"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String clientIp = Tools.getLocalIp(request);
%>
<!DOCTYPE html>
<html>
  	<head>
    	<title>销售出库</title>
        <meta charset="utf-8">
		<!-- 指定以IE8的方式来渲染 -->
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
    	<link rel="shortcut icon" href="<%=path%>/images/favicon.ico" type="image/x-icon" />
		<link rel="stylesheet" type="text/css" href="<%=path %>/js/easyui-1.3.5/themes/default/easyui.css"/>
		<link rel="stylesheet" type="text/css" href="<%=path %>/js/easyui-1.3.5/themes/icon.css"/>
		<link type="text/css" rel="stylesheet" href="<%=path %>/css/common.css" />
		<link type="text/css" rel="stylesheet" href="<%=path %>/css/in_out.css" />
		<script src="<%=path %>/js/jquery-1.8.0.min.js"></script>
		<script src="<%=path %>/js/easyui-1.3.5/jquery.easyui.min.js"></script>
		<script src="<%=path %>/js/easyui-1.3.5/locale/easyui-lang-zh_CN.js"></script>
		<script src="<%=path %>/js/My97DatePicker/WdatePicker.js"></script>
		<script src="<%=path %>/js/common/common.js"></script>		
		<script src="<%=path %>/js/pages/materials/in_out.js"></script>
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
						<input type="text" name="searchNumber" id="searchNumber" style="width:100px;"/>
					</td>
					<td>商品信息：</td>
					<td>
						<input type="text" name="searchMaterial" id="searchMaterial" placeholder="名称，型号" style="width:100px;"/>
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
		<div id="tablePanel" class="easyui-panel" style="padding:1px; top:300px;" title="销售出库列表" iconCls="icon-list" collapsible="true" closable="false">
			<table id="tableData" style="top:300px;border-bottom-color:#FFFFFF"></table>
		</div>
		
	    <div id="depotHeadDlg" class="easyui-dialog" style="width:1200px;padding:10px 20px;top:20px"
	            closed="true" buttons="#dlg-buttons" modal="true" cache="false" collapsible="false" closable="true">
	        <form id="depotHeadFM" method="post"  novalidate>
	            <table>
					<tr>
						<td style="width:60px;">客户：</td>
						<td style="padding:5px">
							<input id="OrganId" name="OrganId" style="width:130px;" />
						</td>
						<td style="width:70px;">销售人员：</td>
						<td style="padding:5px">
							<input id="Salesman" name="Salesman" style="width:130px;" />
						</td>
						<td style="width:80px;">单据日期：</td>
						<td style="padding:5px">
							<input type="text" name="OperTime" id="OperTime" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="txt Wdate" style="width:140px;"/>
						</td>
						<td style="width:70px;">单据编号：</td>
						<td style="padding:5px">
							<input name="Number" id="Number" class="easyui-validatebox" data-options="required:true,validType:'length[2,30]'" style="width: 140px;"/>
						</td>
						<td style="width:100px;"></td>
					</tr>
					<tr>
						<td colspan="9">
							<!-- 商品列表table -->
							<table id="materialData" style="top:100px;border-bottom-color:#FFFFFF"></table>
						</td>
					</tr>
					<tr>
						<td colspan="9">
							<textarea name="Remark" id="Remark" rows="2" cols="2" placeholder="暂无备注信息" style="width: 1130px; height:35px;"></textarea>
						</td>
					</tr>
					<tr>
						<td>优惠率：</td>
						<td style="padding:5px">
							<input id="Discount" name="Discount" style="width:110px;" />
							%
						</td>
						<td>收款优惠：</td>
						<td style="padding:5px">
							<input id="DiscountMoney" name="DiscountMoney" style="width:120px;" />
						</td>
						<td>优惠后金额：</td>
						<td style="padding:5px">
							<input id="DiscountLastMoney" name="DiscountLastMoney" readonly="readonly" style="width:120px;" />
						</td>
						<td>本次收款：</td>
						<td style="padding:5px">
							<input id="ChangeAmount" name="ChangeAmount" data-changeamount="0" style="width:120px;" />
						</td>
						<td style="width:100px;"></td>
					</tr>
					<tr>
						<td>结算账户：</td>
						<td style="padding:5px">
							<select name="AccountId" id="AccountId" style="width:120px;"></select>
							<img class="many-account-ico" src="<%=path%>/js/easyui-1.3.5/themes/icons/filelist.jpg"  style="display: none;"/>
						</td>
						<td>本次欠款：</td>
						<td style="padding:5px">
							<input id="Debt" name="Debt" readonly="readonly" style="width:120px;" />
						</td>
						<td>销售费用：</td>
						<td style="padding:5px">
							<input id="OtherMoney" name="OtherMoney" style="width:120px;" readonly="readonly" />
							<img class="other-money-ico" src="<%=path%>/js/easyui-1.3.5/themes/icons/filelist.jpg" />
						</td>
						<td>结算天数：</td>
						<td style="padding:5px">
							<input id="AccountDay" name="AccountDay" class="easyui-numberbox" data-options="validType:'length[1,3]'" style="width:120px;" />
						</td>
						<td style="width:100px;"></td>
					</tr>
	            </table>
	            <input type="hidden" name="clientIp" id="clientIp" value="<%=clientIp %>"/>
	        </form>
	    </div>
	    <div id="dlg-buttons">
	        <a href="javascript:void(0)" id="saveDepotHead" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
	        <a href="javascript:void(0)" id="cancelDepotHead" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#depotHeadDlg').dialog('close')">取消</a>
	    </div>
	    <div id="depotHeadDlgShow" class="easyui-dialog" style="width:1200px;padding:10px 20px;top:20px"
	            closed="true" modal="true" cache="false" collapsible="false" closable="true">
			<table>
				<tr>
					<td style="width:60px;">客户：</td>
					<td style="padding:5px;width:130px;">
						<span id="OrganIdShow"></span>
					</td>
					<td style="width:70px;">销售人员：</td>
					<td style="padding:5px;width:130px;">
						<span id="SalesmanShow"></span>
					</td>
					<td style="width:80px;">单据日期：</td>
					<td style="padding:5px;width:140px;">
						<span id="OperTimeShow"></span>
					</td>
					<td style="width:70px;">单据编号：</td>
					<td style="padding:5px;width:140px;">
						<span id="NumberShow"></span>
					</td>
					<td style="width:100px;"></td>
				</tr>
				<tr>
					<td colspan="9" style="width: 1130px;">
						<!-- 商品列表table -->
						<table id="materialDataShow" style="top:100px;border-bottom-color:#FFFFFF"></table>
					</td>
				</tr>
				<tr>
					<td style="width:60px;">单据备注：</td>
					<td colspan="8" style="height:35px;">
						<span id="RemarkShow" style="width: 1070px; height:35px;"></span>
					</td>
				</tr>
				<tr>
					<td>优惠率：</td>
					<td>
						<span id="DiscountShow" style="width: 110px;"></span>
						%
					</td>
					<td>收款优惠：</td>
					<td>
						<span id="DiscountMoneyShow" style="width: 120px;"></span>
					</td>
					<td>优惠后金额：</td>
					<td>
						<span id="DiscountLastMoneyShow" style="width: 120px;"></span>
					</td>
					<td>本次收款：</td>
					<td style="padding:5px">
						<span id="ChangeAmountShow"></span>
					</td>
					<td style="width:100px;"></td>
				</tr>
				<tr>
					<td>结算账户：</td>
					<td style="padding:5px">
						<span id="AccountIdShow"></span>
					</td>
					<td>本次欠款：</td>
					<td style="padding:5px">
						<span id="DebtShow"></span>
					</td>
					<td>销售费用：</td>
					<td style="padding:5px">
						<span id="OtherMoneyShow"></span>
					</td>
					<td>结算天数：</td>
					<td style="padding:5px">
						<span id="AccountDayShow"></span>
					</td>
					<td style="width:100px;"></td>
				</tr>
			</table>
	    </div>
		<div id="depotHeadAccountDlg" class="easyui-dialog" style="width:380px;padding:10px 20px;top:80px"
			 closed="true" modal="true" buttons="#accountDlgButtons" cache="false" collapsible="false" closable="true">
			<table class="account-dlg">
				<tr class="account-head-tmp">
					<td style="width:30px;"></td>
					<td style="width:140px; padding:5px;">结算账户</td>
					<td style="width:100px; padding:5px;">金额</td>
				</tr>
				<tr>
					<td style="width:30px;"></td>
					<td style="width:140px;text-align: center;">合计：</td>
					<td style="width:100px;"><span id="accountMoneyTotalDlg"></span></td>
				</tr>
			</table>
			<table class="tabs-tmp">
				<tr class="account-content-tmp">
					<td style="width:30px;"></td>
					<td style="width:140px; padding:3px;"><select class="account-id-dlg" style="width:140px;"></select></td>
					<td style="width:100px; padding:3px;"><input class="account-money-dlg" style="width:100px;" /></td>
				</tr>
			</table>
		</div>
		<div id="accountDlgButtons">
			<a href="javascript:void(0)" id="saveDepotHeadAccountDlg" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
			<a href="javascript:void(0)" id="cancelDepotHeadAccountDlg" class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
		</div>
		<div id="otherMoneyDlg" class="easyui-dialog" style="width:380px;padding:10px 20px;top:80px"
			 closed="true" modal="true" buttons="#otherMoneyDlgBtn" cache="false" collapsible="false" closable="true">
			<table class="money-dlg">
				<tr class="money-head-tmp">
					<td style="width:30px;"></td>
					<td style="width:140px; padding:5px;">结算账户</td>
					<td style="width:100px; padding:5px;">金额</td>
				</tr>
				<tr>
					<td style="width:30px;"></td>
					<td style="width:140px;text-align: center;">合计：</td>
					<td style="width:100px;"><span id="otherMoneyTotalDlg"></span></td>
				</tr>
			</table>
			<table class="tabs-tmp">
				<tr class="money-content-tmp">
					<td style="width:30px;"></td>
					<td style="width:140px; padding:3px;"><select class="money-id-dlg" style="width:140px;"></select></td>
					<td style="width:100px; padding:3px;"><input class="other-money-dlg" style="width:100px;" /></td>
				</tr>
			</table>
		</div>
		<div id="otherMoneyDlgBtn">
			<a href="javascript:void(0)" id="saveOtherMoneyDlg" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
			<a href="javascript:void(0)" id="cancelOtherMoneyDlg" class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
		</div>

	</body>
</html>