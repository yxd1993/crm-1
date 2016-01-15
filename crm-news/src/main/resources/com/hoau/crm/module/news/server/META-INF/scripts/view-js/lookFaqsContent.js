(function($){
	NEWS.content; 
	
	NEWS.loadInstance = function(){
		NEWS.faqsContent = $('#faqsContent');
		NEWS.content = $('.content');
	}
	NEWS.initContent = function(){
		var $id = NEWS.faqsContent.find('input[type="hidden"]').val();
		$.post(NEWS.URL+'news/newsAction!queryNewsContent.action',{'newsArticleEntity.id':$id},function(data){
			NEWS.articleEntity = data.newsArticleEntity;
			NEWS.newsArticleClass = data.newsArticleEntity.newsArticleClass;
		}).done(function(data){
			NEWS.faqsContent.find('.classType').html(NEWS.newsArticleClass.articleClass);
			NEWS.faqsContent.find('span.readHits').html(NEWS.articleEntity.articleHits)
			NEWS.faqsContent.find('span.datetime').html(moment(NEWS.articleEntity.createDate).format('YYYY-MM-DD HH:mm:ss'));
//			NEWS.content.editable('disable');
//			NEWS.content.editable('focus');
			NEWS.content.load('../staticpages/news/html/operationType.html',function(responseText, textStatus, xhr) {
				if (textStatus == "error") {} else if (textStatus == "timeout") {} else {
				}
			});
		}).fail(function(data){
			
		})
		
		NEWS.faqsContent.find('.classType').on({
		'click':function(){
			NEWS.loadContentMethod();
		}
	})
	};
	$(function(){
		NEWS.loadInstance();
		NEWS.initContent();
	});
})(jQuery);
