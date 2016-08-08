<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>工作中心管理</title>
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
		<li class="active"><a href="${ctx}/workcenter/workcenter/">工作中心列表</a></li>
		<shiro:hasPermission name="workcenter:workcenter:edit"><li><a href="${ctx}/workcenter/workcenter/form">工作中心添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="workcenter" action="${ctx}/workcenter/workcenter/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>作业名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="40" class="input-small"/>
			</li>
			<li><label>访问路径：</label>
				<form:input path="path" htmlEscape="false" maxlength="100" class="input-small"/>
			</li>
			<li><label>父级作业：</label>
				<form:input path="parentId" htmlEscape="false" maxlength="64" class="input-small"/>
			</li>
			<li><label>作业状态：</label>
				<form:input path="status" htmlEscape="false" maxlength="1" class="input-small"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>作业所属组</th>
				<th>作业名称</th>
				<th>描述</th>
				<th>访问路径</th>
				<th>开始时间</th>
				<th>结束时间</th>
				<th>父级作业组</th>
				<th>父级作业名字</th>
				<th>作业状态</th>
				<th>运行总次数</th>
				<th>cron 表达式</th>
				<shiro:hasPermission name="workcenter:workcenter:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="workcenter">
			<tr>
				<td>
					${workcenter.group}
				</td>
				<td>
					${workcenter.name}
				</td>
				<td>
					${workcenter.nameCn}
				</td>
				<td>
					${workcenter.path}
				</td>
				<td>
					<fmt:formatDate value="${workcenter.start}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${workcenter.end}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>${workcenter.parentId != '0' ? workcenter.parentGroup : '根节点'}</td>
				<td>${workcenter.parentId != '0' ? workcenter.parentName : '根节点'}</td>
				<td>
					${fns:getDictLabel(workcenter.status, 'jobStatus', '')}
				</td>
				<td>
					${workcenter.runCount}
				</td>
				<td>
					${workcenter.cronExpression}
				</td>
				<shiro:hasPermission name="workcenter:workcenter:edit"><td>
    				<a href="${ctx}/workcenter/workcenter/form?id=${workcenter.id}">修改</a>
					<a href="${ctx}/workcenter/workcenter/delete?id=${workcenter.id}" onclick="return confirmx('确认要删除该工作中心吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>