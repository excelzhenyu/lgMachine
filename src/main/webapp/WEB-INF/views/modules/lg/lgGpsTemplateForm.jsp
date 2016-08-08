<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>GPS传感器模板管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				
				rules:{
					gpsTemplateNo:{remote:"${ctx}/lg/lgGpsTemplate/checkTemplateNo?id=${lgGpsTemplate.id}"},
					gpsTemplateName:{remote:"${ctx}/lg/lgGpsTemplate/checkTemplateName?id=${lgGpsTemplate.id}"}
				},
				messages:{
					gpsTemplateNo:{remote:"模板编号已经存在"},
					gpsTemplateName:{remote:"模板名称已经存在"}
				},
				
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
		<li><a href="${ctx}/lg/lgGpsTemplate/">控制器模板列表</a></li>
		<li class="active"><a href="${ctx}/lg/lgGpsTemplate/form?id=${lgGpsTemplate.id}">控制器模板<shiro:hasPermission name="lg:lgGpsTemplate:edit">${not empty lgGpsTemplate.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="lg:lgGpsTemplate:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="lgGpsTemplate" action="${ctx}/lg/lgGpsTemplate/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">GPS传感器模板编号：</label>
			<div class="controls">
				<form:input path="gpsTemplateNo" htmlEscape="false" maxlength="40" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">模版名称：</label>
			<div class="controls">
				<form:input path="gpsTemplateName" htmlEscape="false" maxlength="30" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">停用标志：</label>
			<div class="controls">
				<form:radiobuttons path="stopMark" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="lg:lgGpsTemplate:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>