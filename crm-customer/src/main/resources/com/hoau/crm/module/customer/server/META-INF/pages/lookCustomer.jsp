<%@page language="java" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=utf-8">
<META HTTP-EQUIV="CACHE-CONTROL" CONTENT="NO-CACHE">
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=utf-8">
<META HTTP-EQUIV="CACHE-CONTROL" CONTENT="NO-CACHE">
<%@include file="../bse/common.jsp"%>
<%
	String cId = (request.getParameter("cId") == null) ? "" : request.getParameter("cId");
%>
<script type="text/javascript">
	var cId = '<%= cId%>';
</script>
<script type="text/javascript" src="../scripts/common/ext-hoau.js"></script>
<script type="text/javascript" src="../scripts/common/common.js"></script>
<script type="text/javascript" src="../scripts/common/crm-util.js"></script>
<script type="text/javascript" src="../scripts/common/commonSelector.js"></script>
<script type="text/javascript" src="../scripts/common/datetime/DateTimePicker.js"></script>
<script type="text/javascript" src="../scripts/common/datetime/DateTime.js"></script>
<script type="text/javascript" src="${scripts}/LookCustomer/LookCustomer.js"></script>
<link href="${styles}/customer.css" rel="stylesheet" type="text/css" /> 
</head>
<body>
</body>
</html>
