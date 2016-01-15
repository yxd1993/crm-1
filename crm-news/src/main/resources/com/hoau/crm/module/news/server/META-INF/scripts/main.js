jQuery(function($) {
	/*加载内容对象*/
	NEWS.ArticleEntity;
	NEWS.$LoadContent;
	NEWS.contentHead;
	NEWS.newsType;
	/*实例化加载内容对象*/
	NEWS.createPublicInstances = function(){
		NEWS.$LoadContent = $('.jqm-content');
		NEWS.NavMenu = $('.jqm-navmenu-panel');
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
			$('body,html').animate({scrollTop:0},800);
		})
	}
	/* 设置插件等全局默认参数 */
	NEWS.setGlobalDefaultsParams = function() {
		$.ajaxSetup({
            cache: false,
            dataType:'json',
        });
		/* DataTable全局参数设置 */
		$.extend($.fn.dataTable.defaults, {
			language: {
				url: "../scripts/news/jquerydatatable/data/Chinese.json"
			},
			filter: false, //是否启用过滤功能
			ordering: false, //是否启用排序功能
			autoWidth: false,
			lengthChange: false,
			iDisplayLength: 10,
			processing: false,
			serverSide: true,
			columnDefs: [{
				"defaultContent": "",
				"targets": "_all"
			}]
		});
	}
	//菜单点击事件
	NEWS.dropMenuClick = function(){
		NEWS.NavMenu.find(".jqm-list a:not(:first)").each(function() {
			$(this).click(function() {
				var $this = $(this);
				NEWS.newsType = $this.attr('data-type');
				NEWS.contentHead = $this.text()
				NEWS.$LoadContent.fadeOut('fast',function() {
					if(NEWS.newsType!=undefined){
						NEWS.$LoadContent.load('commonList.action', function(responseText, textStatus, xhr) {
							$( ".jqm-navmenu-panel:not(.jqm-panel-page-nav)" ).panel( "close" );
							$.mobile.loading("show");
							NEWS.$LoadContent.fadeIn('slow',function(){
								$.mobile.loading( "hide");
							});
						});
					}else{
						var url = $this.attr('data-href');
						NEWS.$LoadContent.load(url, function(responseText, textStatus, xhr) {
							$( ".jqm-navmenu-panel:not(.jqm-panel-page-nav)" ).panel( "close" );
							$.mobile.loading("show");
							NEWS.$LoadContent.fadeIn('slow',function(){
								$.mobile.loading( "hide");
							});
						});
					}
				});
			});
		});
		NEWS.NavMenu.find(".jqm-list a:first").on('click',function(){
			NEWS.$LoadContent.fadeOut('fast',function() {
				NEWS.$LoadContent.load('index.action', function(responseText, textStatus, xhr) {
					$( ".jqm-navmenu-panel:not(.jqm-panel-page-nav)" ).panel( "close" );
					$.mobile.loading("show");
					NEWS.$LoadContent.fadeIn('slow',function(){
						$.mobile.loading( "hide");
					});
				});
			});
			
		})
	};
	
	NEWS.loadContentMethod = function(){
		
	};
	/* dom加载完成 */
	$(function(){
		NEWS.createPublicInstances();
		NEWS.setGlobalDefaultsParams();
		NEWS.dropMenuClick();
		NEWS.$LoadContent.load('index.action');
	})
	
	/* 界面加载完成 */
	$(window).load(function() {
		$("body").show();
	})
});

$( document ).on( "pagecreate", ".jqm-news", function( event ) {
	var search,
		page = $( this ),
		that = this;
	// 全局菜单
	$( ".jqm-navmenu-panel ul" ).listview();

	$( document ).on( "panelopen", ".jqm-search-panel", function() {
		$( this ).find( "input" ).focus();
	})
	//菜单打开
	$( ".jqm-navmenu-link" ).on( "click", function() {
		page.find( ".jqm-navmenu-panel" ).panel( "open" );
	});


	// 全局搜索
	$( ".jqm-search-link" ).on( "click", function() {
		page.find( ".jqm-search-panel" ).panel( "open" );
	});

	// 初始化 搜索面板列表中，并过滤除去collapsibles
	$( this ).find( ".jqm-search ul.jqm-list" ).listview({
		inset: false,
		theme: null,
		dividerTheme: null,
		icon: false,
		autodividers: true,
		autodividersSelector: function ( li ) {
			return "";
		},
		arrowKeyNav: true,
		enterToNav: true,
		highlight: true
	}).filterable();
});
// 关闭ajax本地文件浏览
//if ( location.protocol.substr(0,4)  === 'file' ||
//   location.protocol.substr(0,11) === '*-extension' ||
//   location.protocol.substr(0,6)  === 'widget' ) {
//
//
//	// 修正了初始页面的链接
//	$( fixLinks );
//
//	// 修正了随后的AJAX页面加载的链接
//	$( document ).on( "pagecreate", fixLinks );

	// 检查是否可以使用AJAx
//	$.ajax({
//		url: '.',
//		async: false,
//		isLocal: true
//	}).error(function() {
//		// ajax 不工作则关闭
//		$( document ).on( "mobileinit", function() {
//			$.mobile.ajaxEnabled = false;
//
//			var message = $( '<div>' , {
//				'class': "jqm-content",
//				style: "border:none; padding: 10px 15px; overflow: auto;",
//				'data-ajax-warning': true
//			});
//
//			message
//			.append( "<h3>Note: Navigation may not work if viewed locally</h3>" )
//			.append( "<p>The Ajax-based navigation used throughout the jQuery Mobile docs may need to be viewed on a web server to work in certain browsers. If you see an error message when you click a link, please try a different browser.</p>" );
//
//			$( document ).on( "pagecreate", function( event ) {
//				$( event.target ).append( message );
//			});
//		});
//	});
//}
	// 用于搜索的关键字高亮功能突出显示文本
//jQuery.fn.highlight = function( pat ) {
//	function innerHighlight( node, pat ) {
//		var skip = 0;
//		if ( node.nodeType == 3 ) {
//			var pos = node.data.toUpperCase().indexOf( pat );
//			if ( pos >= 0 ) {
//				var spannode = document.createElement( "span" );
//				spannode.className = "jqm-search-results-highlight";
//				var middlebit = node.splitText( pos );
//				var endbit = middlebit.splitText( pat.length );
//				var middleclone = middlebit.cloneNode( true );
//				spannode.appendChild( middleclone );
//				middlebit.parentNode.replaceChild( spannode, middlebit );
//				skip = 1;
//			}
//		} else if ( node.nodeType == 1 && node.childNodes && !/(script|style)/i.test( node.tagName ) ) {
//			for ( var i = 0; i < node.childNodes.length; ++i ) {
//				i += innerHighlight( node.childNodes[i], pat );
//			}
//		}
//		return skip;
//	}
//	return this.length && pat && pat.length ? this.each(function() {
//		innerHighlight( this, pat.toUpperCase() );
//	}) : this;
//};

// 删除高亮文本
//jQuery.fn.removeHighlight = function() {
//	console.log(123)
//	return this.find( "span.jqm-search-results-highlight" ).each(function() {
//		this.parentNode.firstChild.nodeName;
//		with ( this.parentNode ) {
//			replaceChild( this.firstChild, this );
//			normalize();
//		}
//	}).end();
//};
