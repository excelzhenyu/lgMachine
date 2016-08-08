<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>地图城市列表管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treeview.jsp" %>
	<script src="${ctxStatic}/custom-js/ztreeHandle.js" type="text/javascript"></script>
	<script type="text/javascript">
		var zTree, rMenu, setting, setting, zNodes;
		
		var moveUrl = "${ctx}/lg/citylist/lgMapCity/move";
		var delUrl = "${ctx}/lg/citylist/lgMapCity/delete";

		$(document).ready(function() {
			$("#search_condition").keyup(function (e) {
			    if (e.keyCode == 13) {
			    	search_ztree('cityTree', 'search_condition');
			    }
			});
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
			rMenu = $("#rMenu");
			setting = {
				check:{
					/*enable:true,*/
					nocheckInherit:true,
					chkboxType : { "Y" : "ps", "N" : "s" }
				},
				edit: {
					drag: {
						isCopy : false,
						isMove : true
					},
					enable: true,
					showRenameBtn: false,
					showRemoveBtn: false
				},
				view:{
					selectedMulti:false,
					fontCss: setFontCss_ztree, //启用字体颜色
					dblClickExpand: function(treeId, treeNode) {
						return treeNode.level > 0;
					}
				},
				data:{simpleData:{enable:true}},callback:{
					beforeClick:function(treeId, treeNode){
						tree.checkNode(treeNode, !treeNode.checked, true, true);
						return false;
					}
				},
				callback: {
					onClick: onClick,
					beforeDrag: beforeDrag,
					beforeDrop: beforeDrop, 
					onRightClick: onRightClick
				}
			};
			/*
			zNodes=[
		            {id:"0",pId:"-1",name:"城市列表",cityCode:"cityCode",isRoot:true, open:true},
					<c:forEach items="${cityList}" var="city">{id:"${city.id}", pId:"${not empty city.parentId?city.parentId:0}", name:"${not empty city.cityName?city.cityName:'城市列表'}", 
						cityCode:"${not empty city.cityCode?city.cityCode:'cityCode'}",isort:"${not empty city.isort?city.isort:'isort'}"<c:if test="${city.parentId == '0'}">, open:true </c:if>
					},</c:forEach>];
			*/
			//删除该句即可 不展开根节点0的子节点 <c:if test="${city.parentId == '0'}">, open:true </c:if>
			zNodes=[
		            {id:"0",pId:"-1",name:"城市列表",cityCode:"cityCode",isRoot:true, open:true},
					<c:forEach items="${cityList}" var="city">{id:"${city.id}", pId:"${not empty city.parentId?city.parentId:0}", name:"${not empty city.cityName?city.cityName:'城市列表'}", 
						cityCode:"${not empty city.cityCode?city.cityCode:'cityCode'}",isort:"${not empty city.isort?city.isort:'isort'}" 
						<c:if test="${city.parentId == '0'}">, isParent:true </c:if>
					},</c:forEach>]; 
			
			// 初始化树结构
			var tree = $.fn.zTree.init($("#cityTree"), setting, zNodes);	
			// 默认展开全部节点
			//tree.expandAll(true);
			zTree = $.fn.zTree.getZTreeObj("cityTree");

		});
	</script>
	<style>
	/*
		.ztree li span.button.switch.level0 {
			visibility: hidden;
			width: 1px;
		}
		
		.ztree li ul.level0 {
			padding: 0;
			background: none;
		}*/
		.div_RightMenu {
			text-align: left;
			padding: 2px;
			width: 100px;
			position: absolute;
			display: none;
		}
		
		.div_RightMenu div {
			background: #ddd;
			position: relative;
		}
		
		.div_RightMenu ul {
			position: relative;
			background: #fff;
			border: 1px solid #999;
			margin: 1px 0;
			padding: 0 5px;
		}
		
		.div_RightMenu ul li {
			list-style: none;
			padding: 1px 8px;
			line-height: 25px;
			height: 25px;
			cursor: pointer;
			text-align: center;
		}
		.div_RightMenu div {
			background: #ddd;
			position: relative;
		}
		.div_RightMenu ul li.RM_mouseover {
			background-color: #f5f5f5;
		}
</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/lg/citylist/lgMapCity/tree">地图城市</a></li>
	</ul><br/>
	<sys:message content="${message}" />
	<div class="container-fluid" >
		<div class="row-fluid" >
			<span class="span5"> 
				<div style="margin-left:30px;">
					<h3>城市列表</h3>
					<div>
				        <div class="input-append row-fluid" style="margin-bottom: 0px;">
							<input id="search_condition" type="text" placeholder="请输入城市名称" class="span6" />
							<button type="button" class="btn btn-primary" onclick="search_ztree('cityTree', 'search_condition')">搜索</button>
				        </div>
				    </div>
					<div id="cityTree" class="ztree" style="margin-top: 3px; float: left;"></div>
				</div>
			</span>
			<span class="span7"> 
				<h3 id="doWhat"></h3>
				<form:form id="inputForm" modelAttribute="lgMapCity" action="${ctx}/lg/citylist/lgMapCity/save" method="post" class="form-horizontal" style="display: none;">
					<form:hidden path="id"/>
					<form:hidden path="parentId"/>
					<form:hidden path="isort"/>
					<div class="control-group">
						<label class="control-label">上级城市：</label>
						<div class="controls">
							<input id="parentName" class="input-large " type="text" value="" maxlength="20" readonly/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">城市编号：</label>
						<div class="controls">
							<form:input path="cityCode" htmlEscape="false" maxlength="20" class="input-large required"/>
							<span class="help-inline"><font color="red">*</font> </span>
							
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">城市名称：</label>
						<div class="controls">
							<form:input path="cityName" htmlEscape="false" maxlength="40" class="input-large required"/>
							<span class="help-inline"><font color="red">*</font> </span>
						</div>
					</div>
					<div class="form-actions">
						<shiro:hasPermission name="lg:citylist:lgMapCity:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
						<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
					</div>
				</form:form>
			</span>
		</div>
	</div>
    <div id="rMenu" class="div_RightMenu">
	<ul>
		<li id="m_add" onclick="addTreeNode();">新增城市</li>
		<li id="m_mod" onclick="modTreeNode();">修改城市</li>
		<li id="m_del" onclick="delTreeNode();">删除城市</li>
	</ul>
</div>
</body>
</html>