jQuery(function($){
	NEWS.dataTableCommonEntity;
	NEWS.commonDataTable;
	//创建实力对象
	NEWS.LoadInstance = function(){
		NEWS.commonDataTable = $('#commonListTable');
		$('.contentHead').html(NEWS.contentHead);
	}
	/*面板JS*/
	NEWS.bindCommonListPanel = function() {
		var type;
		switch (NEWS.newsType) {
		case 'news':
			type = 1;
			break;
		case 'information':
			type = 2;	
			break;
		case 'help':
			type = 3;
			break;
		case 'faqs':
			type = 4;
			break;
		default:
			break;
		}
		/* 表格数据 */
		NEWS.dataTableCommonEntity = NEWS.commonDataTable.DataTable({
			isDisplayLength:8,
			ajax: {
				//url:"../scripts/news/jquerydatatable/data/test-template.txt",
				url:NEWS.URL+"news/queryNews.action",
				data: function(data) {
					var params = {};
					var jsonmaps = '{"draw":"'+data.draw+'","start":"'+data.start+'","length":"'+data.length+'","type":"'+type+'"}'
					params.jsonmaps = jsonmaps;
					return params;
				},
				dataType : "json",
				contentType : 'application/json;charset=UTF-8'
			}, 
			columns: [{
				data: 'articleType'
			}, {
				data: null
			}, {
				data: null
			}],
			columnDefs: [{
				targets:[1],
				render: function(data, type,
						full) {
					return '<a href="lookArticleContent.action?newsArticleEntity.id='+data.id+'"target="view_window">'+data.articleTitle+'</a>'
				}
			},{
				targets:[2],
				render: function(data, type,
						full) {
					return data.createUser + '<br/>'+moment(data.createDate).format('YYYY-MM-DD HH:mm:ss')+'</font>'
				}
			}],
			rowCallback:function(row,data){
			},
			drawCallback:function(a,b,c){
				$('body,html').animate({scrollTop:0},800);
			}
		});
	}
	
	$(function(){
		NEWS.LoadInstance();
		NEWS.bindCommonListPanel();
	})
});