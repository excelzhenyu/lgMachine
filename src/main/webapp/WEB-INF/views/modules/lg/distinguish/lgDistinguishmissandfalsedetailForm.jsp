<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>虚漏报方案配置详细管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				
				rules:{
					condition:{remote:"${ctx}/lg/distinguish/lgDistinguishmissandfalsedetail/checkCondition?id=${lgDistinguishmissandfalsedetail.distinguishId}"}
				},
				
				messages:{
					condition:{remote:"此条件已经存在不能重复添加"}
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
		<li><a href="${ctx}/lg/distinguish/lgDistinguishmissandfalsedetail/list?distinguishId=${lgDistinguishmissandfalsedetail.distinguishId}">虚漏报方案配置详细列表</a></li>
		<li class="active"><a href="${ctx}/lg/distinguish/lgDistinguishmissandfalsedetail/form?id=${lgDistinguishmissandfalsedetail.id}">虚漏报方案配置详细<shiro:hasPermission name="lg:distinguish:lgDistinguishmissandfalsedetail:edit">${not empty lgDistinguishmissandfalsedetail.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="lg:distinguish:lgDistinguishmissandfalsedetail:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="lgDistinguishmissandfalsedetail" action="${ctx}/lg/distinguish/lgDistinguishmissandfalsedetail/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group" style="display:none">
			<label class="control-label">配置ID：</label>
			<div class="controls">
				<form:input path="distinguishId" htmlEscape="false" class="input-medium"/>
			</div>
		</div>	
		<div class="control-group">
			<label class="control-label">筛选条件：</label>
			<div class="controls">
				<form:select path="condition" class="input-medium ">
					<form:options items="${fns:getDictList('distinguishCondition')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">选项：</label>
			<div class="controls">
				<form:radiobuttons path="option" items="${fns:getDictList('distinguishOption')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">值：</label>
			<div class="controls">
				<form:input path="value1" htmlEscape="false" class="input-medium  number"/>
			</div>
		</div>
		<div class="control-group" style="display: none">
			<label class="control-label">value2：</label>
			<div class="controls">
				<form:input path="value2" htmlEscape="false" maxlength="11" class="input-medium  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">有效标识：</label>
			<div class="controls">
				<form:radiobuttons path="isValid" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>