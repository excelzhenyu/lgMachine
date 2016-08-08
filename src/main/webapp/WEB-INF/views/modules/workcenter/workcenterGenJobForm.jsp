<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>Quartz Job 生成方案管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$.validator.addMethod("checkExist", function() {
				var flag = true;
				
				if ($("#oldJobName").val() != $("#jobName").val()
						|| $("#oldJobGroup").val() != $("#jobGroup").val()) {
					$.ajax({
						type: "POST",
						url: '${ctx}/workcenter/workcenterGenJob/checkExist',
						async: false,
						data:{
							'jobName': $("#jobName").val(),
							'jobGroup': $("#jobGroup").val()
						},
						success: function(msg){
							flag = !msg;
						}  
					})
				}
				
				return flag;
			}, "此 JOB 已存在");
			
			$.validator.addMethod("checkFunctionNameExist", function() {
				var flag = true;
				
				if ($("#oldFunctionName").val() != $("#functionName").val()) {
					$.ajax({
						type: "POST",
						url: '${ctx}/workcenter/workcenterGenJob/checkFunctionNameExist',
						async: false,
						data:{
							'functionName': $("#functionName").val()
						},
						success: function(msg){
							flag = !msg;
						}  
					})
				}
				
				return flag;
			}, "此功能名已存在");
			
			$.validator.addMethod("checkName", function(value, element) {
				return this.optional(element) || (/^([a-zA-Z0-9_]+)$/.test(value));
			}, "只能包括英文字母、数字和下划线");
			
			$("#inputForm").validate({
				rules: {
					jobName: {checkName: true},
					jobGroup: {checkExist: true},
					functionName: {checkFunctionNameExist: true}
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
			
			$('#rootJob').change(function() {
				showCronByRootJob();
			});
			
			showCronByRootJob();
			
			if ($("#id").val() == '') {
				$('#deleteOldGenCon').hide();
			}
			
			$('#chooseGroupBtn').click(function() {
				chooseGroup();
				$('#chooseGroupModal').modal();
			});
			
			$('#groupBtnSubmit').click(function() {
				chooseGroup();
			});
		});
		
		/**
		 * 根据是否是根节点控制是否显示 Cron 表达式输入框
		 */
		function showCronByRootJob() {
			if ($('#rootJob').find('option:selected').val() == '1') {
				$('#cronDiv').show();
			} else {
				$('#cronDiv').hide();
			}
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
		<li><a href="${ctx}/workcenter/workcenterGenJob/">Quartz Job 生成方案列表</a></li>
		<li class="active"><a href="${ctx}/workcenter/workcenterGenJob/form?id=${workcenterGenJob.id}">Quartz Job 生成方案<shiro:hasPermission name="workcenter:workcenterGenJob:edit">${not empty workcenterGenJob.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="workcenter:workcenterGenJob:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="workcenterGenJob" action="${ctx}/workcenter/workcenterGenJob/save" method="post" class="form-horizontal">
		<form:hidden id="id" path="id"/>
		<form:hidden path="genFlag"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">生成功能名：</label>
			<div class="controls">
				<input type="hidden" id="oldFunctionName" value="${workcenterGenJob.functionName}" />
				<form:input path="functionName" htmlEscape="false" maxlength="500" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
				<span class="help-inline">功能中文名</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">包路径：</label>
			<div class="controls">
				<form:input path="packageName" htmlEscape="false" maxlength="200" class="input-xlarge " readonly="true" 
					value="com.k2data.platform.modules.workcenter.quartz.job"/>
				<span class="help-inline">固定路径，不能修改</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">生成 Job 名：</label>
			<div class="controls">
				<input type="hidden" id="oldJobName" value="${workcenterGenJob.jobName}" />
				<form:input path="jobName" htmlEscape="false" maxlength="20" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
				<span class="help-inline">e.g. example</span>
			</div>
		</div>
		<div class="control-group ">
			<label class="control-label">所属组：</label>
			<div class="controls">
				<input type="hidden" id="oldJobGroup" value="${workcenterGenJob.jobGroup}" />
				<form:input id="jobGroup" path="jobGroup" htmlEscape="false" maxlength="100" class="input-xlarge required" readonly="true"/>
				<input id="chooseGroupBtn" class="btn" type="button" value="选 择" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group ">
			<label class="control-label">生成功能作者：</label>
			<div class="controls">
				<form:input path="functionAuthor" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">是否根节点：</label>
			<div class="controls">
				<form:select id="rootJob" path="rootJob" class="input-xlarge">
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div id="cronDiv" class="control-group ">
			<label class="control-label">Cron 表达式：</label>
			<div class="controls">
				<form:input path="cronExpression" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">生成选项:</label>
			<div class="controls">
				<form:checkbox path="replaceJavaFile" label="是否替换 Java 文件"/>
				<span class="help-inline">勾选后会删除原文件</span>
			</div>
		</div>
		
		<div class="form-actions">
			<shiro:hasPermission name="workcenter:workcenterGenJob:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
				<input id="btnSubmit" class="btn btn-danger" type="submit" value="保存并生成代码" onclick="$('#genFlag').val('1');"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
	
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