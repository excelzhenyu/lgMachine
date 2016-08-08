<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>工作中心管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			cronReadonlyByParentId();
			
			$.validator.addMethod("checkExist", function() {
				var flag = true;
				
				if ($("#id").val() == null || $("#id").val() == '') {
					$.ajax({
						type: "POST",
						url: '${ctx}/workcenter/workcenter/checkExist',
						async: false,
						data:{
							'name': $("#jobName").val(),
							'group': $("#jobGroup").val()
						},
						success: function(msg){
							flag = !msg;
						}  
					})
				}
				
				return flag;
			}, "此 JOB 已存在");
			
			$.validator.addMethod("checkName", function(value, element) {
				return this.optional(element) || (/^([a-zA-Z0-9_]+)$/.test(value));
			}, "只能包括英文字母、数字和下划线");
			
			$("#inputForm").validate({
				rules: {
					group: {checkExist: true},
					name: {checkName: true}
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
			
			$("#parentIdInput").change(function() {
				cronReadonlyByParentId();
			});
			
			$('#chooseParentJobBtn').click(function() {
				chooseParentJob();
				$('#chooseParentModal').modal();
			});
			$('#parentJobBtnSubmit').click(function() {
				chooseParentJob();
			});
			
			$('#chooseGroupBtn').click(function() {
				chooseGroup();
				$('#chooseGroupModal').modal();
			});
			$('#groupBtnSubmit').click(function() {
				chooseGroup();
			});
		});
		
		// 根据是否有 parentId　来确定是否能输入 Cron 表达式
		function cronReadonlyByParentId() {
			var thisVal = $("#parentIdInput").val();
			
			if (thisVal != '' && thisVal != null && thisVal != 'root') {
				$("#cronInput").attr("readonly", true);
				$("#cronBtn").attr("disabled", true);
			} else {
				$("#cronInput").attr("readonly", false);
				$("#cronBtn").attr("disabled", false);
			}
		}
		
		// 选择父级 job
		function chooseParentJob() {
			$.getJSON('${ctx}/workcenter/workcenter/workCenterList', 
					{name: $('#name').val(), group: $('#group').val(), state: $('#state').val(), 
					 pageNo: '', pageSize: ''},
				function(data) {
					if (data) {
						$("#contentTable tbody").empty();
						$("#contentTable tbody").append(data.data.toString());
						$('.pagination').empty();
						$('.pagination').append(data.page.toString());
						
						// 点击后选择父级job
						$("#contentTable tbody tr td a").click(function() {
							var parentTr = $(this).parent("td").parent("tr");
							
							$("#parentId").val(parentTr.find("input[name='jobId']").val());
							$("#parentGroupInput").val(parentTr.find("td[name='jobGroup']").text());
							$("#parentNameInput").val(parentTr.find("td[name='jobName']").text());
							
							$('#chooseParentModal').modal('hide');
						});
					}
			});
		}
		
		// 选择组
		function chooseGroup() {
			$.getJSON('${ctx}/workcenter/workcenterGenJob/groupListDom', 
					{name: $('#modalGroupName').val(), description: $('#modalGroupDescription').val(),
					 pageNo: '', pageSize: ''},
				function(data) {
					if (data) {
						$("#contentTable tbody").empty();
						$("#contentTable tbody").append(data.data.toString());
						$('.pagination').empty();
						$('.pagination').append(data.page.toString());
						
						// 点击后选择父级job
						$("#contentTable tbody tr td a").click(function() {
							$("#jobGroup").val($(this).text());
							
							$('#chooseGroupModal').modal('hide');
						});
					}
			});
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/workcenter/workcenter/">工作中心列表</a></li>
		<li class="active"><a href="${ctx}/workcenter/workcenter/form?id=${workcenter.id}">工作中心<shiro:hasPermission name="workcenter:workcenter:edit">${not empty workcenter.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="workcenter:workcenter:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="workcenter" action="${ctx}/workcenter/workcenter/save" method="post" class="form-horizontal">
		<form:hidden id="id" path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">中文名称：</label>
			<div class="controls">
				<form:input path="nameCn" htmlEscape="false" maxlength="40" class="input-xlarge required" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">名称：</label>
			<div class="controls">
				<form:input id="jobName" path="name" htmlEscape="false" maxlength="40" class="input-xlarge required" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">所属组：</label>
			<div class="controls">
				<form:input id="jobGroup" path="group" htmlEscape="false" maxlength="40" class="input-xlarge required" readonly="true"/>
				<input id="chooseGroupBtn" class="btn" type="button" value="选 择" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">访问路径：</label>
			<div class="controls">
				<form:input path="path" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">父级作业组：</label>
			<div class="controls">
				<form:input path="parentGroup" id="parentGroupInput" htmlEscape="false" maxlength="300" class="input-xlarge " readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">父级作业名字：</label>
			<div class="controls">
				<form:input path="parentName" id="parentNameInput" htmlEscape="false" maxlength="300" class="input-xlarge " readonly="true"/>
				<form:hidden path="parentId"/>
				<input id="chooseParentJobBtn" class="btn" type="button" value="选 择" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">作业状态：</label>
			<div class="controls">
				<form:select id="statusSelect" path="status" class="input-xlarge">
					<form:options items="${fns:getDictList('jobStatus')}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">cron 表达式：</label>
			<div class="controls">
				<form:input id="cronInput" path="cronExpression" htmlEscape="false" maxlength="40" class="input-xlarge "/>
				<input id="cronBtn" class="btn" type="button" value="生成工具" onclick=""/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="workcenter:workcenter:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
	
	<!-- 选择父级 Job Modal -->
	<div id="chooseParentModal" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div id="searchForm" class="breadcrumb form-search">
					<input id="pageNo" name="pageNo" type="hidden" value=""/>
					<input id="pageSize" name="pageSize" type="hidden" value=""/>
					<ul class="ul-form">
						<li><label>作业名称：</label>
							<input id="name" maxlength="40" class="input-medium"/>
						</li>
						<li><label>作业组：</label>
							<input id="group" maxlength="40" class="input-medium"/>
						</li>
						<li><label>作业状态：</label>
							<input id="status" maxlength="1" class="input-medium"/>
						</li>
						<li class="btns"><input id="parentJobBtnSubmit" class="btn btn-primary" type="button" value="查询"/></li>
						<li class="clearfix"></li>
					</ul>
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th>作业名称</th>
								<th>作业所属组</th>
								<th>作业状态</th>
							</tr>
						</thead>
						<tbody></tbody>
					</table>
					<div class="pagination"></div>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 选择组 Modal -->
	<div id="chooseGroupModal" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div id="searchForm" class="breadcrumb form-search">
					<input id="pageNo" name="pageNo" type="hidden" value=""/>
					<input id="pageSize" name="pageSize" type="hidden" value=""/>
					<ul class="ul-form">
						<li><label>组名称：</label>
							<input id="modalGroupName" maxlength="40" class="input-medium"/>
						</li>
						<li><label>组描述：</label>
							<input id="modalGroupDescription" maxlength="40" class="input-medium"/>
						</li>
						<li class="btns"><input id="groupBtnSubmit" class="btn btn-primary" type="button" value="查询"/></li>
						<li class="clearfix"></li>
					</ul>
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th>组名称</th>
								<th>组描述</th>
							</tr>
						</thead>
						<tbody></tbody>
					</table>
					<div class="pagination"></div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>