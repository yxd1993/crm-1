(function($){
	//容器
	FAQS.faqsContainer;
	//实体
	FAQS.faqsEntity;
	//集合
	FAQS.faqsEntityList;
	
	FAQS.loadFaqsInstance = function(){
		FAQS.faqsContainer = $('#faqsContainer');
		//回到顶部
		var $elevator = $( "#elevator" );
		$( document ).on( "scroll", function () {
		    var scrollTop = document.body.scrollTop || document.documentElement.scrollTop;
		    if ( scrollTop > 1 ) {
		        $elevator.fadeIn();
		    } else {
		        $elevator.fadeOut();
		    }
		} );
		$elevator.on('click',function(){
			$('body,html').animate({scrollTop:0},400);
		})
	};
	
	FAQS.bindFaqsPanel = function(){
		
		function appendRes(value,type){
			var faqsContenetTemplate = '<div class="'+type+'">'
				+'<div class="jqm-block-content">'
				+'<h3>'+value.newsArticleClass.articleClass+'</h3>'
					+'<div class="news-list">'
						+'<div class="news-list-detail">'
							+'<span class="news-list-title">'
								+'<a class="linkto" href="#"data-id ='+value.id+'>'+value.articleTitle+'</a>'
							+'</span>'
							+'<ul>'
								+'<li><label>阅读数：'+value.articleHits+'</label></li>'
								+'<li><label>日期：'+moment(value.createDate).format('YYYY-MM-DD HH:mm:ss')+'</label></li>'
							+'</ul>'
						+'</div>'
					+'</div>'
				+'</div>'
				+'</div>';
			FAQS.faqsContainer.find('.ui-grid-a').slideDown('2000',function(){
				$(this).append(faqsContenetTemplate);
			});
			
		}
		var $value = FAQS.faqsContainer.find('input[name="searchValue"]').val();
		$.post('newsAction!queryFaqsArticle.action',{'newsArticleVo.searchValue':$value},function(data){
			FAQS.faqsEntityList = data.newsArticleList;
		}).done(function(data){
			if(FAQS.faqsEntityList.length==0){
				alert("无结果");
				return ;
			}
			$.each(FAQS.faqsEntityList,function(index,value){
				if(index%2==0){
					appendRes(value,"ui-block-a")
				}else{
					appendRes(value,"ui-block-b")
				}
			});
			FAQS.faqsContainer.find('.news-list').each(function(){
				$(this).on('click',function(){
					var $id = $(this).find('.news-list-title > a').attr('data-id');
					$.mobile.loading("show");
					FAQS.faqsContainer.fadeIn('3000',function() {
						FAQS.faqsContainer.load('lookFaqsContent.action',{'newsArticleEntity.id':$id},function(){
							FAQS.faqsContainer.fadeIn('3000',function() {
								$.mobile.loading( "hide");
							})
						});
					});
				});
			})
		}).fail(function(data){
			
		})
	};
	FAQS.bindFaqsSearch = function(){
		FAQS.faqsContainer.find('.search').on('click',function(){
			FAQS.faqsContainer.find('.ui-grid-a').empty();
			FAQS.bindFaqsPanel();
		});
		
	};
	
	
	$(function(){
		FAQS.loadFaqsInstance();
		FAQS.bindFaqsPanel();
		FAQS.bindFaqsSearch();
	});
})(jQuery);