<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar"%>
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
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	Calendar c1 = Calendar.getInstance();  
	c1.setTime(new Date());  
	c1.set(Calendar.DATE, c1.get(Calendar.DATE) - 31); 
	String startDate = sdf.format(c1.getTime());
	Calendar c2 = Calendar.getInstance();  
	c2.setTime(new Date());  
	c2.set(Calendar.DATE, c2.get(Calendar.DATE)); 
	String endDate = sdf.format(c2.getTime());
%>
<script type="text/javascript">
	var startDate = '<%= startDate %>';
	var endDate = '<%= endDate %>';
</script>
<script type="text/javascript" src="../scripts/common/ext-hoau.js"></script>
<script type="text/javascript" src="../scripts/common/common.js"></script>
<script type="text/javascript" src="../scripts/common/crm-util.js"></script>
<script type="text/javascript" src="../scripts/common/commonSelector.js"></script>
<script type="text/javascript" src="../scripts/common/datetime/DateTimePicker.js"></script>
<script type="text/javascript" src="../scripts/common/datetime/DateTime.js"></script>
<script type="text/javascript" src="${scripts}/resourcePool/resourcePool.js"></script>
<link href="${styles}/customer.css" rel="stylesheet" type="text/css" /> 
</head>
<body>
</body>
</html>
