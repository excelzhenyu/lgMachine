<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>地图城市列表管理</title>
	<meta name="decorator" content="default"/>
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
		<script>
	window.onresize = function() {
		$(".page").height(($("body").height()-100)*0.95);
	}
	$(document).ready(function() {	
		$(".page").height(($("body").height()-100)*0.95);

	});
	</script>
	<style> 
		html,body {
		  margin: 0;
		  padding:0;
		  height: 100%;
		}
		.footer {
			position: absolute;
			bottom: 0;
			width: 100%;
			clear:both;
		}
		.containerdiv {
		  min-height:100%;
		  height: auto !important;
		  height: 100%; /*IE6不识别min-height*/
		  position: relative;
		}
		.page {
			width: 100%;
			margin: 0 auto;
			padding-bottom: 60px;/*等于footer的高度*/
		}
	</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/lg/citylist/lgMapCity/">地图城市列表列表</a></li>
		<shiro:hasPermission name="lg:citylist:lgMapCity:edit"><li><a href="${ctx}/lg/citylist/lgMapCity/form">地图城市列表添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="lgMapCity" action="${ctx}/lg/citylist/lgMapCity/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>父级编号</th>
				<th>城市编号</th>
				<th>城市名称</th>
				<th>排序字段</th>
				<shiro:hasPermission name="lg:citylist:lgMapCity:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="lgMapCity">
			<tr>
				<td><a href="${ctx}/lg/citylist/lgMapCity/form?id=${lgMapCity.id}">
					${lgMapCity.parentId}
				</a></td>
				<td>
					${lgMapCity.cityCode}
				</td>
				<td>
					${lgMapCity.cityName}
				</td>
				<td>
					${lgMapCity.isort}
				</td>
				<shiro:hasPermission name="lg:citylist:lgMapCity:edit"><td>
    				<a href="${ctx}/lg/citylist/lgMapCity/form?id=${lgMapCity.id}">修改</a>
					<a href="${ctx}/lg/citylist/lgMapCity/delete?id=${lgMapCity.id}" onclick="return confirmx('确认要删除该地图城市列表吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
		</table>
	<div class="pagination">${page}</div>
	</div>
</body>
</html>