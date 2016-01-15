<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<script type="text/javascript">/* 创建对象*/var NEWS = window.NEWS || {};NEWS.URL="<%=basePath%>";</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!--移动适配 -->
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<!--safari  -->
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black-translucent" >
<link rel="stylesheet" href="${styles}/jquerymobile/css/themes/default/jquery.mobile-1.4.5.min.css"/>
<!--富文本框插件样式  -->
<link rel="stylesheet" href="${styles}/froalaeditor/css/font-awesome.min.css">
<link rel="stylesheet" href="${styles}/froalaeditor/css/froala_editor.min.css">
<!--表格样式  -->
<link rel="stylesheet" href="${styles}/jquerydatatable/jquery.dataTables.min.css"/>
<link rel="stylesheet" href="${styles}/jquerydatatable/dataTables.responsive.css"/>
<link rel="stylesheet" href="${styles}/reset.css" />
<link rel="stylesheet" href="${styles}/main.css"/>
<body>
	<div data-role="page" class="jqm-news">
		<!-- header -->
		<div data-role="header" class="jqm-header">
			<h2>HOAU NEWS </h2>
			<a href="#" class="jqm-navmenu-link ui-btn ui-btn-icon-notext ui-corner-all ui-icon-bars ui-nodisc-icon ui-alt-icon ui-btn-left">菜单</a>
			<a href="#" class="jqm-search-link ui-btn ui-btn-icon-notext ui-corner-all ui-icon-search ui-nodisc-icon ui-alt-icon ui-btn-right">搜索</a>
		</div>
		<!--菜单面板  panel navmenu -->
		<div data-role="panel" class="jqm-navmenu-panel" data-position="left" data-display="overlay" >
    		<ul class="jqm-list ui-alt-icon ui-nodisc-icon">
				<li data-icon="home"><a data-type="index">首页</a></li>
				<li data-icon="info"><a data-type="news">新闻</a></li>
				<li data-icon="comment"><a data-type="information">知识</a></li>
				<li data-icon="gear"><a data-type="help">帮助</a></li>
				<li data-icon="search"><a data-type="faqs">FAQS</a></li>
				<li data-role="collapsible" data-enhanced="true" data-collapsed-icon="carat-d" data-expanded-icon="carat-u" data-iconpos="right"class="ui-collapsible ui-collapsible-themed-content ui-collapsible-collapsed">
					<h3 class="ui-collapsible-heading ui-collapsible-heading-collapsed">
						<span class="ui-collapsible-heading-toggle ui-btn ui-btn-icon-right ui-btn-inherit ui-icon-carat-d">
						   更多<span class="ui-collapsible-heading-status"></span>
						</span>
					</h3>
					<div class="ui-collapsible-content ui-body-inherit ui-collapsible-content-collapsed" aria-hidden="true">
						<ul>
							<li><a data-href="addArticleView.action">添加文章</a></li>
							<li><a data-href="" >设置</a></li>
						</ul>
					</div>
				</li>
			</ul>
		</div>
		<!-- 加载主体 内容 main content -->
		<div data-role="main" class="ui-content jqm-content">
		</div>
		<!--底部-->
		<div data-role="footer" class="jqm-footer">
			<p>Copyright 2015 天地华宇</p>
		</div>
		<!--搜索面板-->
		 <div data-role="panel" class="jqm-search-panel" data-position="right" data-display="overlay" data-theme="a">
		 	<div class="jqm-search">
		 		<ul class="jqm-list" data-filter-placeholder="搜索内容">
				</ul>
		 	</div>
		 </div>
		 <span id="elevator" class="go-to-top">
          	<img src="${images}/up.png">
    	 </span>
	</div>
</body>
<!--jquery  -->
<script src="${scripts}/jquery/jquery-1.11.3.js"></script>
<!--jquerymobile 响应式  -->
<script src="${scripts}/jquerymobile/jquery.mobile-1.4.5.min.js"></script>
<!--jquery响应式表格  -->
<script src="${scripts}/jquerydatatable/jquery.dataTables.min.js"></script>
<script src="${scripts}/jquerydatatable/dataTables.responsive.min.js"></script>
<!--富文本框插件 -->
<script src="${scripts}/froalaeditor/froala_editor.min.js"></script>
<script src="${scripts}/froalaeditor/plugins/tables.min.js"></script>
<script src="${scripts}/froalaeditor/plugins/lists.min.js"></script>
<script src="${scripts}/froalaeditor/plugins/colors.min.js"></script>
<script src="${scripts}/froalaeditor/plugins/media_manager.min.js"></script>
<script src="${scripts}/froalaeditor/plugins/file_upload.min.js"></script>
<script src="${scripts}/froalaeditor/plugins/font_family.min.js"></script>
<script src="${scripts}/froalaeditor/plugins/font_size.min.js"></script>
<script src="${scripts}/froalaeditor/plugins/block_styles.min.js"></script>
<script src="${scripts}/froalaeditor/plugins/video.min.js"></script>
<script src="${scripts}/froalaeditor/langs/zh_cn.js"></script>
<!--日期格式化插件  -->
<script src="${scripts}/moment/moment-2.10.6.js"></script>
<!--自定义  -->
<script src="${scripts}/main.js"></script>
</html>