<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>虚漏报方案配置管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				rules:{
					solutionName:{remote:"${ctx}/lg/distinguish/lgDistinguishmissandfalse/checkSolutionName?id=${lgDistinguishmissandfalse.id}"}
				},
				messages:{
					solutionName:{remote:"配置名称已经存在"}
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
		<li><a href="${ctx}/lg/distinguish/lgDistinguishmissandfalse/">虚漏报方案配置列表</a></li>
		<li class="active"><a href="${ctx}/lg/distinguish/lgDistinguishmissandfalse/form?id=${lgDistinguishmissandfalse.id}">虚漏报方案配置<shiro:hasPermission name="lg:distinguish:lgDistinguishmissandfalse:edit">${not empty lgDistinguishmissandfalse.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="lg:distinguish:lgDistinguishmissandfalse:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="lgDistinguishmissandfalse" action="${ctx}/lg/distinguish/lgDistinguishmissandfalse/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">类型：</label>
			<div class="controls">
				<form:radiobuttons path="solutionType" items="${fns:getDictList('solutionType')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">方案名称：</label>
			<div class="controls">
				<form:input path="solutionName" htmlEscape="false" maxlength="40" class="input-medium "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">有效标识：</label>
			<div class="controls">
				<form:radiobuttons path="isValid" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remark" htmlEscape="false" maxlength="255" class="input-medium "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="lg:distinguish:lgDistinguishmissandfalse:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>