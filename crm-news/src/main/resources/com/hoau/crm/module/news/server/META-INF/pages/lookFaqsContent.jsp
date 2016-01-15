<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.hoau.crm.module.news.shared.domain.NewsArticleEntity"%>
<%@ page language="java"  import="java.text.*"%>
<%
	NewsArticleEntity newsArticleEntity = (NewsArticleEntity)request.getAttribute("newsArticleEntity");
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd H:m:s");
	String datetime = sdf.format(newsArticleEntity.getCreateDate());
%>
<!-- 内容 -->
<div id="faqsContent">
	<h3 class="classType"><%=newsArticleEntity.getNewsArticleClass().getArticleClass() %><span  class="datelabel datelabel-green">返回</span></h3>
		<div class="contendHead">
			<span class="datelabel datelabel-red">日&nbsp;期</span>
			<span class="datetime"><%=datetime %></span>
			<span class="datelabel datelabel-red">阅&nbsp;读</span>
			<span class="readHits"><%=newsArticleEntity.getArticleHits() %></span>
		</div>
		<!-- <section id="eidtor"> -->
		<div class="content"">
    	 	     <%--  <%=newsArticleEntity.getArticleContent() %> --%>
   		</div>
			<!-- </section> -->
</div>
<script>
	(function($){
		
		$('.content').load('<%=newsArticleEntity.getContentPath()%>');
		
		$('.classType > span').on({
			'click':function(){
				window.location.reload(); 
			}
		});
	})(jQuery);
</script>