<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	
	String newsArticleEntity = request.getParameter("newsArticleEntity");
%>
<!DOCTYPE html>
<html lang="zh-cn">
	<head>
	<script type="text/javascript">/* 创建对象*/var NEWS = window.NEWS || {};NEWS.URL="<%=basePath%>";</script>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1,user-scalable=no">
	<link rel="stylesheet" href="${styles}/jquerymobile/css/themes/default/jquery.mobile-1.4.5.min.css"/>
	<link rel="stylesheet" href="${styles}/view-css/articleContent.css" />
	<!--自定义样式  -->
	<link rel="stylesheet" href="${styles}/reset.css" />
	<link rel="stylesheet" href="${styles}/main.css"/>
	</head>
	<body>
	<div data-role="page" class="jqm-news">
		<input type="hidden" value="${newsArticleEntity.id}">
		<!-- header -->
		<div data-role="header" class="jqm-header">
			<h2>华宇新闻(News)</h2>
		</div>
		<!-- 内容 -->
		<div class="ui-content jqm-content">
			<div class="ui-grid-a ui-responsive">
			<div class="ui-grid-solo">
				<div class="ui-grid-a">
					<div class="jqm-block-content">
						<h3 class="contentHead"></h3>
						<p><strong class="title"></strong><span class="createDate"></span></p>
						<p class="type"></p>
						<p class="summary"></p>
						<div class="content"></div>
					</div>
				</div>
			</div>
			</div>
		</div>
		<div class="ui-content jqm-content">
		<!--高速版-->
		<div id="SOHUCS"></div>
		<script charset="utf-8" type="text/javascript" src="http://changyan.sohu.com/upload/changyan.js" ></script>
		<script type="text/javascript">
		    window.changyan.api.config({
		        appid: 'cys2QTtHe',
		        conf: 'prod_8beb495f3c9a24f65e566928e24e882a'
		    });
		</script>  
		</div>
		<!--底部-->
		<div data-role="footer" class="jqm-footer">
			<p>Copyright 2015 天地华宇</p>
		</div>
	</body>
	<!-- jquery  -->
	<script src="${scripts}/jquery/jquery-1.11.3.js"></script>
	<!--jquerymobile 响应式  -->
	<script src="${scripts}/jquerymobile/jquery.mobile-1.4.5.min.js"></script>
	<!--日期格式化插件  -->
	<script src="${scripts}/moment/moment-2.10.6.js" c></script>
	<script src="${scripts}/view-js/articleContent.js"></script>
</html>
