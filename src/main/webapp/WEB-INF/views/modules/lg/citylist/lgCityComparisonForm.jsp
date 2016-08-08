<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>业务销售城市与地图城市对照管理</title>
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
		<li><a href="${ctx}/lg/citylist/lgCityComparison/">业务销售城市与地图城市对照列表</a></li>
		<li class="active">
		<c:choose>
			<c:when test="${not empty lgCityComparison.id}">
				<a href="${ctx}/lg/citylist/lgCityComparison/form?id=${lgCityComparison.id}">
				<shiro:hasPermission name="lg:citylist:lgCityComparison:edit">业务销售城市与地图城市对照修改</shiro:hasPermission><shiro:lacksPermission name="lg:citylist:lgCityComparison:edit">查看</shiro:lacksPermission></a>
			</c:when>
			<c:otherwise>
				<a href="${ctx}/lg/citylist/lgCityComparison/setting">
				<shiro:hasPermission name="lg:citylist:lgCityComparison:edit">业务销售城市与地图城市对照添加</shiro:hasPermission><shiro:lacksPermission name="lg:citylist:lgCityComparison:edit">查看</shiro:lacksPermission></a>
			</c:otherwise>
			</c:choose>
		</li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="lgCityComparison" action="${ctx}/lg/citylist/lgCityComparison/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		
		<div class="control-group">
			<label class="control-label">地图城市：</label>
			<div class="controls">
				<form:select path="mapCityId" class="input-xlarge">
					<form:options items="${allMapCityList}" itemLabel="cityName" itemValue="id" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">业务城市：</label>
			<div class="controls">
				<form:select path="systemCityId" class="input-xlarge">
					<form:options items="${allSystemCityList}" itemLabel="cityName" itemValue="id" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
			

		<div class="control-group">
			<label class="control-label">有效标志：</label>
			<div class="controls">
				<form:radiobuttons path="isEffective" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="lg:citylist:lgCityComparison:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>

</body>
</html>