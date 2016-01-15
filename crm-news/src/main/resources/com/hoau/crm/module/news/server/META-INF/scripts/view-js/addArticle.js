jQuery(function($){
	NEWS.addArticleEdit;
	NEWS.addArticleEditEntity;
	NEWS.addArticleContent;
	NEWS.addArticleDataForm;
	//创建实力对象
	NEWS.loadArticleInstance = function(){
		$('.content_header').html(NEWS.contentHead);
		NEWS.addArticleContent = $('#addArticle');
		NEWS.addArticleDataForm = $('#addArticleForm');
	}
	NEWS.bindAddArticlePanel = function(){
//		$.post('newsAction!queryArticeleClass.action',{'newsArticleClassEntity.id':""},function(data){
//			
//		}).done(function(data){
//			$.each(data.returnRes,function(idx,value){
//				NEWS.addArticleDataForm.find('select[name="id"]').append('<option value="'+value.classId+'">'+value.articleClass+'</option>')
//			})
//		}).fail(function(){
//			
//		})
		//$('#edit').editable({inlineMode: false})
		NEWS.addArticleEditEntity = $('#edit').editable({
			inlineMode: false,
			theme: 'dark',
			placeholder: '输入内容',
			language: 'zh_cn',
			height: 190,
			direction: "ltr",
            allowedImageTypes: ["jpeg", "jpg", "png", "gif"],
            autosave: false,
            autosaveInterval: 2500,
            saveURL: 'newsAction!addArticle.action',
            saveRequestType: 'post',
            spellcheck: true,
            plainPaste: true,
            pasteImage: true,
            pastedImagesUploadRequestType: 'post',
           // pastedImagesUploadURL: NEWS.URL+'news/newsAction!addArticleImage.action',
           // imageUploadURL:  NEWS.URL+'news/newsAction!addArticleImage.action',
            imageButtons: ["removeImage", "replaceImage"],
            maxFileSize: 10485760,
            enableScript: false
		}).on('editable.afterSave', function (e, editor, data) {
			if(data.success){
				alert('成功')
				NEWS.addArticleEditEntity.editable('setHTML', '', false);
			}else{
				alert('失败')
			}
		}).on('editable.imageError', function (e, editor, error) {
			
		});
		
		NEWS.addArticleContent.find('.submit a').on('click',function(){
			NEWS.addArticleEditEntity.editable('option', 'saveParams',{
				'newsArticleEntity.newsArticleClass.id':NEWS.addArticleDataForm.find('select[name="id"]').val(),
    			'newsArticleEntity.articleTitle':NEWS.addArticleDataForm.find('input[name="articleTitle"]').val(),
    			'newsArticleEntity.articleType':NEWS.addArticleDataForm.find('input[name="articleType"]').val(),
    			'newsArticleEntity.articleSummary':NEWS.addArticleDataForm.find('textarea[name="articleSummary"]').val()
			});
			NEWS.addArticleEditEntity.editable('save');
		});
	}
	
	$(function(){
		NEWS.loadArticleInstance();
		NEWS.bindAddArticlePanel();
	})
});