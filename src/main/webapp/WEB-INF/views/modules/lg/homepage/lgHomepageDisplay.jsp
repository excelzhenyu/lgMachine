<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户首页管理</title>
	<meta name="decorator" content="default"/>
	<style> 
		html,body{
		    height: 100%;
		    width: 100%;
		}
		.span_height {
			overflow-x:hidden;
			overflow-y:hidden;
			border:2px solid #EEE;	
		}
		.row-fluid {
			margin-top:10px;
		}
		.row-fluid [class*="span"] {
			margin-left:10px;
			margin-right:0;
		}
		.container-fluid {
		    padding-right: 0;
		}
	</style>
	<script>
	var dynamicRow = '${dynamicRow}';
	window.onresize = function() {
		$(".span_height").height(($("body").height()/dynamicRow)*0.95);
	}
	$(document).ready(function() {	
		var dynamicHtml = '${dynamicHtml}';
		if(dynamicHtml != "") {
			$("#containerArea").html(dynamicHtml);
		}
		if(dynamicRow == 0 || dynamicRow == null) {
			row = 1;
		}
		$(".span_height").height(($("body").height()/dynamicRow)*0.95);
	});
	</script>
</head>
<body>
	<div class="container-fluid" id="containerArea" >
		<div class="row-fluid" >
			<span class="span12"> 
				<div style="padding:50px 0 0 50px;"> 
				<h1>您还没有定制主页</h1>
				&nbsp;&nbsp;<a href="${ctx}/lg/lgUserHomepageRelationship/form">可以在【我的面板】->【个人信息】->【首页设置】进行设置</a>
				</div>
			</span>
		</div>
	</div>
</body>
</html>