<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>工作中心管理</title>
	<meta name="decorator" content="default"/>
	<style type="text/css">
	.indexul li {
		float: left;
		list-style-type: none;
		margin-right: 20px;
		margin-bottom: 32px;
	}
	.listbox {
		width: 300px; 
		height: 300px; 
		border: 2px solid #f0f0f0;
	}
	.listbox-head {
		width:100%; 
		height: 75%;
		color: white;
		line-height: 25px;
		overflow: hidden;
	}
	.listbox-pause {
		background-color: #dbdbdb;
	}
	.listbox-running {
		background-color: steelBlue;
	}
	.listbox-failure {
		background-color: #c00;
	}
	.listbox-bottom {
		padding:16px;
		position:relative;
		font-size: 16px;
	}
	.space {
		padding: 8px;
	}
	.listbox i {
		font-size: 120px;
	}
	.listbox-head-icon {
		position: absolute;
		top: 10px;
		left: 130px;
		margin: 20px 20px;
		text-align: center;
	}
	.listbox-head-icon:hover {
		cursor: pointer;
	}
	.listbox-head-text {
		position: relative;
		width: 100%;
		font-size: 12px;
		padding: 13px 6px;
	}
	</style>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active">工作中心</li>
	</ul>
	<sys:message content="${message}"/>
	
	<div class="container-fluid">
		<ul class="indexul"></ul>
	</div>
	
	<script type="text/javascript">
	// 动态加载 ul 数据
	$.post('${ctx}/workcenter/workcenter/jobindexul', function(data, status) {
		if (data) {
			$('.indexul').empty();
			$('.indexul').append(data);
			
			// 点击图标后控制 job 开启或暂停，只控制根节点 job
			$(".listbox-head-icon").click(function() {
				$.post('${ctx}/workcenter/workcenter/index', {
					name: $(this).find('#rootJobName').val(),
					group: $(this).find('#rootJobGroup').val(),
					status: $(this).find('i').attr('class') == 'icon-play' ? '1' : '2'
				}, function() {
					history.go(0);
				});
			});
		}
	});
	</script>
</body>
</html>