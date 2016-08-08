<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>业务系统城市列表管理</title>
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
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/lg/citylist/lgSystemCity/">业务系统城市列表列表</a></li>
		<shiro:hasPermission name="lg:citylist:lgSystemCity:edit"><li><a href="${ctx}/lg/citylist/lgSystemCity/form">业务系统城市列表添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="lgSystemCity" action="${ctx}/lg/citylist/lgSystemCity/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>城市编号：</label>
				<form:input path="cityCode" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
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
				<shiro:hasPermission name="lg:citylist:lgSystemCity:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="lgSystemCity">
			<tr>
				<td><a href="${ctx}/lg/citylist/lgSystemCity/form?id=${lgSystemCity.id}">
					${lgSystemCity.parentId}
				</a></td>
				<td>
					${lgSystemCity.cityCode}
				</td>
				<td>
					${lgSystemCity.cityName}
				</td>
				<td>
					${lgSystemCity.isort}
				</td>
				<shiro:hasPermission name="lg:citylist:lgSystemCity:edit"><td>
    				<a href="${ctx}/lg/citylist/lgSystemCity/form?id=${lgSystemCity.id}">修改</a>
					<a href="${ctx}/lg/citylist/lgSystemCity/delete?id=${lgSystemCity.id}" onclick="return confirmx('确认要删除该业务系统城市列表吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>