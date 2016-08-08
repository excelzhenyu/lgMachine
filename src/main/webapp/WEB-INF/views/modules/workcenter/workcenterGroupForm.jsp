<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>工作中心作业组管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$.validator.addMethod("checkExist", function() {
				var flag = true;
				
				if ($("#oldName").val() != $("#name").val()) {
					$.ajax({
						type: "POST",
						url: '${ctx}/workcenter/workcenterGroup/checkExist',
						async: false,
						data:{
							'name': $("#name").val()
						},
						success: function(msg){
							flag = !msg;
						}  
					})
				}
				
				return flag;
			}, "此组已存在");
			
			$.validator.addMethod("checkName", function(value, element) {
				return this.optional(element) || (/^([a-zA-Z0-9_]+)$/.test(value));
			}, "只能包括英文字母、数字和下划线");
			
			$("#inputForm").validate({
				rules: {
					name: {checkExist: true, checkName: true}
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
		<li><a href="${ctx}/workcenter/workcenterGroup/">工作中心作业组列表</a></li>
		<li class="active"><a href="${ctx}/workcenter/workcenterGroup/form?id=${workcenterGroup.id}">工作中心作业组<shiro:hasPermission name="workcenter:workcenterGroup:edit">${not empty workcenterGroup.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="workcenter:workcenterGroup:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="workcenterGroup" action="${ctx}/workcenter/workcenterGroup/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">英文名：</label>
			<div class="controls">
				<input type="hidden" id="oldName" value="${workcenterGroup.name}" />
				<form:input path="name" htmlEscape="false" maxlength="30" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">中文描述：</label>
			<div class="controls">
				<form:input path="description" htmlEscape="false" maxlength="50" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:input path="remark" htmlEscape="false" maxlength="40" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="workcenter:workcenterGroup:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>