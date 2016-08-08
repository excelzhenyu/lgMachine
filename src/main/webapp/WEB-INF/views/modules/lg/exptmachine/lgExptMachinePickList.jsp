<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>试验机器选取管理</title>
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
		<li class="active"><a href="${ctx}/lg/exptmachine/lgExptMachinePick/">试验机器选取列表</a></li>
		<shiro:hasPermission name="lg:exptmachine:lgExptMachinePick:edit"><li><a href="${ctx}/lg/exptmachine/lgExptMachinePick/form">试验机器选取添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="lgExptMachinePick" action="${ctx}/lg/exptmachine/lgExptMachinePick/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>试验批次号：</label>
				<form:input path="batchNumber" htmlEscape="false" maxlength="20" class="input-small"/>
			</li>
			 
			<li><label>试验目的：</label>
				<form:input path="exptGoal" htmlEscape="false" maxlength="255" class="input-small"/>
			</li>
			<li><label>试验内容：</label>
				<form:input path="exptContent" htmlEscape="false" maxlength="255" class="input-small"/>
			</li>
			<!-- 
			<li><label>选取时间：</label>
				<input name="pickTime" type="text" readonly="readonly" maxlength="20" class="input-small Wdate"
					value="<fmt:formatDate value="${lgExptMachinePick.pickTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>选取人：</label>
				<form:input path="pickStaff" htmlEscape="false" maxlength="100" class="input-small"/>
			</li>
			<li><label>有效码：</label>
				<form:radiobuttons path="isEffective" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</li>
			<li><label>试验开始时间：</label>
				<input name="exptBeginTime" type="text" readonly="readonly" maxlength="20" class="input-small Wdate"
					value="<fmt:formatDate value="${lgExptMachinePick.exptBeginTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>试验结束时间：</label>
				<input name="exptEndTime" type="text" readonly="readonly" maxlength="20" class="input-small Wdate"
					value="<fmt:formatDate value="${lgExptMachinePick.exptEndTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			 -->
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>试验批次号</th>
				<th>试验目的</th>
				<th>试验内容</th>
				<th>选取时间</th>
				<th>选取人</th>
				<th>有效码</th>
				<th>试验开始时间</th>
				<th>试验结束时间</th>
				<shiro:hasPermission name="lg:exptmachine:lgExptMachinePick:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="lgExptMachinePick">
			<tr>
				<td><a href="${ctx}/lg/exptmachine/lgExptMachinePick/form?id=${lgExptMachinePick.id}">
					${lgExptMachinePick.batchNumber}
				</a></td>
				<td>
					${lgExptMachinePick.exptGoal}
				</td>
				<td>
					${lgExptMachinePick.exptContent}
				</td>
				<td>
					<fmt:formatDate value="${lgExptMachinePick.pickTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${lgExptMachinePick.pickStaffName}
				</td>
				<td>
					${fns:getDictLabel(lgExptMachinePick.isEffective, 'yes_no', '')}
				</td>
				<td>
					<fmt:formatDate value="${lgExptMachinePick.exptBeginTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${lgExptMachinePick.exptEndTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="lg:exptmachine:lgExptMachinePick:edit"><td>
    				<a href="${ctx}/lg/exptmachine/lgExptMachinePick/form?id=${lgExptMachinePick.id}">修改</a>
					<a href="${ctx}/lg/exptmachine/lgExptMachinePick/delete?id=${lgExptMachinePick.id}" onclick="return confirmx('确认要删除该试验机器选取吗？', this.href)">删除</a>
					<a href="${ctx}/lg/exptmachine/lgExptMachinePick/info?id=${lgExptMachinePick.id}">选取明细</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>