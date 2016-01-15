<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String id = request.getParameter("newsArticleEntity.id");
%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>常见问题</title>
<script type="text/javascript">/* 创建对象*/var FAQS = window.FAQS || {};</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!--移动适配 -->
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport" />
<!--safari  -->
<meta name="apple-mobile-web-app-capable" content="yes"  />
<meta name="apple-mobile-web-app-status-bar-style" content="black-translucent" >
<!--jquery mobile  -->
<link rel="stylesheet" href="${styles}/jquerymobile/css/themes/default/jquery.mobile-1.4.5.min.css"/>
<link rel="stylesheet" href="${styles}/reset.css" />
<link rel="stylesheet" href="${styles}/view-css/faqs.css"/>
</head>
<body>
<div data-role="page" class="jqm-news">
	<!-- 加载主体 内容 main content -->
	<div data-role="main" class="ui-content jqm-content">
		<div id="faqsContainer">
		    <div style="width:100%">
				<!-- <span class="contentHead">常见问题</span> -->
				<p class="searchblock">
					<span class="search"></span>
					<input name="searchValue" placeholder="输入查询内容" value="">
				</p>
			</div>
			<div class="ui-grid-a ui-responsive">
				
			</div>
		</div>
	</div>
<%-- 	<!--底部-->
	<div data-role="footer" class="jqm-footer">
		<p>Copyright 2015 天地华宇</p>
	</div>
	<span id="elevator" class="go-to-top">
          	<img src="${images}/up.png" alt="回到顶部" title="回到顶部">
   	 </span>
 --%></div>
</body>

<!--jquery  -->
<script src="${scripts}/jquery/jquery-1.11.3.js"></script>
<!--jquery mobile -->
<script src="${scripts}/jquerymobile/jquery.mobile-1.4.5.min.js"></script>
<!--日期格式化插件  -->
<script src="${scripts}/moment/moment-2.10.6.js"></script>
<script src="${scripts}/view-js/faqs.js"></script>
