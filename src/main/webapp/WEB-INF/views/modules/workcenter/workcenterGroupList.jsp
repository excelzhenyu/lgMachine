<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>工作中心作业组管理</title>
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
		<li class="active"><a href="${ctx}/workcenter/workcenterGroup/">工作中心作业组列表</a></li>
		<shiro:hasPermission name="workcenter:workcenterGroup:edit"><li><a href="${ctx}/workcenter/workcenterGroup/form">工作中心作业组添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="workcenterGroup" action="${ctx}/workcenter/workcenterGroup/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>英文名：</label>
				<form:input path="name" htmlEscape="false" maxlength="30" class="input-small"/>
			</li>
			<li><label>中文描述：</label>
				<form:input path="description" htmlEscape="false" maxlength="50" class="input-small"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>英文名</th>
				<th>中文描述</th>
				<th>备注</th>
				<shiro:hasPermission name="workcenter:workcenterGroup:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="workcenterGroup">
			<tr>
				<td><a href="${ctx}/workcenter/workcenterGroup/form?id=${workcenterGroup.id}">
					${workcenterGroup.name}
				</a></td>
				<td>
					${workcenterGroup.description}
				</td>
				<td>
					${workcenterGroup.remark}
				</td>
				<shiro:hasPermission name="workcenter:workcenterGroup:edit"><td>
    				<a href="${ctx}/workcenter/workcenterGroup/form?id=${workcenterGroup.id}">修改</a>
					<a href="${ctx}/workcenter/workcenterGroup/delete?id=${workcenterGroup.id}" onclick="return confirmx('确认要删除该工作中心作业组吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>