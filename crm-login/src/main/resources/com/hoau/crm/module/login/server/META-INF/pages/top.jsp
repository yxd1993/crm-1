<%@page import="com.hoau.crm.module.bse.api.server.util.BseConstants"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.Set"%>
<%@page import="com.hoau.hbdp.framework.server.context.UserContext"%>
<%@page import="com.hoau.crm.module.bse.api.shared.domain.UserEntity"%>
<%@page language="java" pageEncoding="UTF-8"%>
<%
	//当前用户
	UserEntity currentUser = (UserEntity) UserContext.getCurrentUser();
	String userName = "";
	String deptName = "";
	if(currentUser != null && currentUser.getEmpEntity() != null){
		userName = currentUser.getEmpEntity().getEmpName();
		if(currentUser.getEmpEntity().getDeptEntity() != null){
			deptName = "（" + currentUser.getEmpEntity().getDeptEntity().getDeptName() + "）";
		}
	}
	// 用户权限信息
	Set<String> functionCodes = currentUser.getFunctionCodes();
	if(functionCodes == null){
		functionCodes = new HashSet<String>();
	}
	// 用户信息总数
	String countCustomer = request.getAttribute("countCustomer").toString();
%>
<!doctype html>
<html>
<head>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=utf-8">
<META HTTP-EQUIV="CACHE-CONTROL" CONTENT="NO-CACHE">
<script type="text/javascript" src="${resource}/ext/build/ext-all.js"></script>
<script type="text/javascript"
	src="${resource}/ext/build/packages/ext-theme-crisp/build/ext-theme-crisp.js"></script>
<script type="text/javascript"
	src="${resource}/ext/build/packages/ext-locale/build/ext-locale-zh_CN.js"></script>
<script type="text/javascript" src="../scripts/common/crm-util.js"></script>
<link href="${styles}/top.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	// 新增客户
	function insertCustomer(){
		window.parent.initTabpanel('101002', '新增客户', '/crm-web/customer/addCustomer.action', true);
	}
	// 客户列表
	function messageList(){
		window.parent.initTabpanel('104001', '消息列表', '/crm-web/bse/message.action', true);
	}
	
	function onkeydown() {
		if ((event.keyCode == 8))
		{
			var se = window.event.srcElement;
			var tName = se.tagName;
			if (tName.toUpperCase() != "INPUT"
					&& tName.toUpperCase() != "TEXTAREA"
					&& tName.toUpperCase() != "TEXT") {
				event.keyCode = 0;
				event.returnValue = false;
			} else if(se.readOnly){
				event.keyCode = 0;
				event.returnValue = false;
			}
		}
	}
	document.onkeydown = onkeydown;
</script>
</head>
<body>
	<table style="width:100%;" cellpading=0 cellspacing=0>
		<tr>
			<td style="background-color:#21232b;"><img src="${images}/logo.png"></td>
			<td class="top_back_td">
				<table cellpading=0 cellspacing=0 style="padding:0px;margin:0px;">
					<tr>
						<td style="width:50%;color:#8A8A8A;" align="left">&nbsp;&nbsp;&nbsp;当前客户总数：<span style="color:#000000"><%= countCustomer %></span>&nbsp;个</td>
						<td style="width:50%;color:#8A8A8A;" align="right">欢迎您：<span style="color:#000000"><%= userName %></span><%= deptName%>&nbsp;&nbsp;</td>
						<%
							if(functionCodes.contains(BseConstants.FUNCTION_INSERT_CUSTOMER)){%>
								<td class="top_td"><a href="javascript:insertCustomer()"><img title="新增客户" src="${images}/new.png" style="height:76px;"></a></td>
						<%	
							}
						%>
						<%
							if(functionCodes.contains(BseConstants.FUNCTION_MESSAGE_LIST)){%>
								<td class="top_td"><a href="javascript:messageList()"><img title="消息列表" src="${images}/messagelist.png" style="height:76px;"></a></td>
						<%	
							}
						%>
						<td class="top_td"><a href="javascript:parent.logout()"><img title="退出系统" src="${images}/quit.png" style="height:76px;"></a></td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</body>
</html>