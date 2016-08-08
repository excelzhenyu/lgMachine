<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>业务系统城市列表管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treeview.jsp" %>
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
		<li><a href="${ctx}/lg/citylist/lgSystemCity/">业务系统城市列表列表</a></li>
		<li class="active"><a href="${ctx}/lg/citylist/lgSystemCity/form?id=${lgSystemCity.id}">业务系统城市列表<shiro:hasPermission name="lg:citylist:lgSystemCity:edit">${not empty lgSystemCity.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="lg:citylist:lgSystemCity:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="lgSystemCity" action="${ctx}/lg/citylist/lgSystemCity/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">父级编号：</label>
			<div class="controls">
				<form:input path="parentId" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">城市编号：</label>
			<div class="controls">
				<form:input path="cityCode" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">城市名称：</label>
			<div class="controls">
				<form:input path="cityName" htmlEscape="false" maxlength="40" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">排序字段：</label>
			<div class="controls">
				<form:input path="isort" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="lg:citylist:lgSystemCity:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>