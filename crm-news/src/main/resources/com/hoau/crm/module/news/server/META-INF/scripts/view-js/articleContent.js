jQuery(function($){
	//创建实力对象
	NEWS.LoadInstance = function(){
		NEWS.articleContent = $('.jqm-news');
	}
	NEWS.initContent = function(){
		var id = NEWS.articleContent.find('input[type="hidden"]').val();
		$.get(NEWS.URL+"news/newsAction!queryNewsContent.action?newsArticleEntity.id="+id,function(data){
			NEWS.articleEntity = data.newsArticleEntity;
			NEWS.newsArticleClass = data.newsArticleEntity.newsArticleClass; 
		},'json').done(function(data){
			NEWS.articleContent.find('p strong.title').html(NEWS.articleEntity.articleTitle);
			NEWS.articleContent.find('p span.createDate').html("("+moment(NEWS.articleEntity.createDate).format('YYYY-MM-DD HH:mm:ss')+")");
			NEWS.articleContent.find('.type').html("作者："+NEWS.articleEntity.createUser+"   分类：  "+NEWS.articleEntity.articleType);
			NEWS.articleContent.find('.summary').html("简介："+NEWS.articleEntity.articleSummary);
			NEWS.articleContent.find('.contentHead').html(NEWS.newsArticleClass.articleClass);
			NEWS.articleContent.find('.content').html(NEWS.articleEntity.articleContent);
		}).fail(function(data){
			
		})
	}
	
	$(function(){
		NEWS.LoadInstance();
		NEWS.initContent();
	});
});