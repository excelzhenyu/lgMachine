<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>整机维度管理</title>
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
		<li><a href="${ctx}/lg/lgMachineDimension/">整机维度列表</a></li>
		<li class="active"><a href="${ctx}/lg/lgMachineDimension/form?id=${lgMachineDimension.id}">整机维度<shiro:hasPermission name="lg:lgMachineDimension:edit">${not empty lgMachineDimension.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="lg:lgMachineDimension:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="lgMachineDimension" action="${ctx}/lg/lgMachineDimension/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">维度类别：</label>
			<div class="controls">
				<form:select path="dimensionType" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('dimensionType')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">维度编号：</label>
			<div class="controls">
				<form:input path="dimensionCode" htmlEscape="false" maxlength="20" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">维度名称：</label>
			<div class="controls">
				<form:input path="dimensionName" htmlEscape="false" maxlength="120" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="lg:lgMachineDimension:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>