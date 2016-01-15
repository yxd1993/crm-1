<%@page language="java" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=utf-8">
<META HTTP-EQUIV="CACHE-CONTROL" CONTENT="NO-CACHE">
<link rel="stylesheet" type="text/css"
	href="${resource}/ext/build/packages/ext-theme-crisp/build/resources/ext-theme-crisp-all.css">
<link href="${styles}/left.css" rel="stylesheet" type="text/css" />
<link href="${styles}/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${resource}/ext/build/ext-all.js"></script>
<script type="text/javascript"
	src="${resource}/ext/build/packages/ext-theme-crisp/build/ext-theme-crisp.js"></script>
<script type="text/javascript"
	src="${resource}/ext/build/packages/ext-locale/build/ext-locale-zh_CN.js"></script>
<script type="text/javascript"
	src="${scripts}/crm-util.js"></script>

<script type="text/javascript" src="${scripts}/treepanel/NTreeColumn.js"></script>
<script type="text/javascript" src="${scripts}/treepanel/NViewTable.js"></script>
<script type="text/javascript" src="${scripts}/treepanel/NTreeView.js"></script>
<script type="text/javascript" src="${scripts}/treepanel/NTreePanel.js"></script>


<script type="text/javascript" src="${scripts}/model/MainModel.js"></script>
<script type="text/javascript" src="${scripts}/data/MainData.js"></script>
<script type="text/javascript" src="${scripts}/view/MainView.js"></script>
<script type="text/javascript" src="${scripts}/main.js"></script>

<script type="text/javascript">
	function onkeydown() {
		if ((event.keyCode == 8))
		{
			var se = window.event.srcElement;
			var tName = se.tagName;
			if (tName.toUpperCase() != "INPUT"
					&& tName.toUpperCase() != "TEXTAREA"
					&& tName.toUpperCase() != "TEXT") {
				event.keyCode = 0;
				event.returnValue = false;
			} else if(se.readOnly){
				event.keyCode = 0;
				event.returnValue = false;
			}
		}
	}
	document.onkeydown = onkeydown;
</script>
</head>
<body>

</body>
</html>
