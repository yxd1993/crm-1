<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="${styles}/view-css/addArticleView.css"/>
<div id="addArticle">
	<h2 class="content_header"></h2>
	 <form id="addArticleForm">
		 <ul>
			<li>
				<span>文章分类：</span> 
				<select name="id">
					<option value="">请选择类型</option>
				</select>
			</li>
			<li>
				<span>文章类型：</span>
                <input type="text" name="articleType" placeholder="输入类型" autocomplete="off"/>
			</li>
			<li>
				 <span>文章标题：</span>
                 <input type="text" name="articleTitle" placeholder="输入标题" autocomplete="off"/>
			</li>
			<li>
				<label class="articleSummary">内容简介：</label> 
				<textarea class="" rows="3" name="articleSummary" placeholder="填写简介,不超过200字"/>
			</li>
		 </ul>
	</form>
	<section id="eidtor">
		<div id="edit">
    	 	      
   		</div>
	</section>
    <span class="submit">
		<a ref="#" class="ui-btn ui-btn-b ui-btn-inline ui-mini">发布文章</a>
	</span>
</div>     
<script src="${scripts}/view-js/addArticle.js"></script>

