<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>业务销售城市与地图城市对照管理</title>
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
		<li class="active"><a href="${ctx}/lg/citylist/lgCityComparison/">业务销售城市与地图城市对照列表</a></li>
		<shiro:hasPermission name="lg:citylist:lgCityComparison:edit">
			<li><a href="${ctx}/lg/citylist/lgCityComparison/setting">业务销售城市与地图城市对照添加</a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="lgCityComparison" action="${ctx}/lg/citylist/lgCityComparison/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>业务城市编号：</label>
				<form:input path="systemCityCode" htmlEscape="false" maxlength="20" class="input-small"/>
			</li>
			<li><label>业务城市名称：</label>
				<form:input path="systemCityName" htmlEscape="false" maxlength="20" class="input-small"/>
			</li>
			<li><label>地图城市编号：</label>
				<form:input path="mapCityCode" htmlEscape="false" maxlength="20" class="input-small"/>
			</li>
			<li><label>地图城市名称：</label>
				<form:input path="mapCityName" htmlEscape="false" maxlength="20" class="input-small"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="display:none;">地图城市ID</th>
				<th style="display:none;">业务城市ID</th>
				<th>业务城市编码</th>
				<th>业务城市名称</th>
				<th>地图城市编码</th>
				<th>地图城市名称</th>
				<th>有效码</th>
				<shiro:hasPermission name="lg:citylist:lgCityComparison:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="lgCityComparison">
			<tr>
				<td style="display:none;"><a href="${ctx}/lg/citylist/lgCityComparison/form?id=${lgCityComparison.id}">
					${lgCityComparison.mapCityId}
				</a></td>
				<td style="display:none;">
					${lgCityComparison.systemCityId}
				</td>
				<td>
					${lgCityComparison.systemCityCode}
				</td>
				<td>
					${lgCityComparison.systemCityName}
				</td>
				<td>
					${lgCityComparison.mapCityCode}
				</td>
				<td>
					${lgCityComparison.mapCityName}
				</td>
				<td>
					${fns:getDictLabel(lgCityComparison.isEffective, 'yes_no', '')}
				</td>
				<shiro:hasPermission name="lg:citylist:lgCityComparison:edit"><td>
    				<a href="${ctx}/lg/citylist/lgCityComparison/form?id=${lgCityComparison.id}">修改</a>
					<a href="${ctx}/lg/citylist/lgCityComparison/delete?id=${lgCityComparison.id}" onclick="return confirmx('确认要删除该业务销售城市与地图城市对照吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>