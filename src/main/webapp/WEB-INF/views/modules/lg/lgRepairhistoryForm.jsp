<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>报修记录管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/lg/lgRepairhistory/">报修记录列表</a></li>
		<li class="active"><a href="${ctx}/lg/lgRepairhistory/form?id=${lgRepairhistory.id}">报修记录<shiro:hasPermission name="lg:lgRepairhistory:edit">${not empty lgRepairhistory.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="lg:lgRepairhistory:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="lgRepairhistory" action="${ctx}/lg/lgRepairhistory/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">整机ID：</label>
			<div class="controls">
				<form:input path="machineId" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">报修时间：</label>
			<div class="controls">
				<input name="repairTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${lgRepairhistory.repairTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">报修单号：</label>
			<div class="controls">
				<form:input path="orderNo" htmlEscape="false" maxlength="40" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">报修人：</label>
			<div class="controls">
				<form:input path="orderHuman" htmlEscape="false" maxlength="60" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">故障说明：</label>
			<div class="controls">
				<form:input path="troubleInfo" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">报修反馈：</label>
			<div class="controls">
				<form:input path="feedBack" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="lg:lgRepairhistory:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>