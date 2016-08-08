<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户首页关系管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treeview.jsp" %>
	<script src="${ctxStatic}/custom-js/linkedList-0.1.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					var ids = [];//, nodes = tree.getCheckedNodes(true);
					if(list.size() > 6) {
						showTip('为了页面展示效果，请不要设置超过6个页面！', 'error',1500,0);
					} else {
						for(var index=0; index < list.size();index++) {
							if(!list.get(index).isParent) { //叶子节点
								ids.push(list.get(index).id+"-"+(index+1));
							}
						}
						$("#menuIds").val(ids);
						loading('正在提交，请稍等...');
						form.submit(); 
					}
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
			
			var SPAN_COUNT = 3;
			//创建链表
			var list = new LinkedList();
			
			var setting = {
					check:{
						enable:true,
						nocheckInherit:true					
					},
					view:{selectedMulti:false},
					data:{simpleData:{enable:true}},callback:{
						beforeClick:function(id, node){
							tree.checkNode(node, !node.checked, true, true);
							return false;
						}
					},
					callback:{ 
						beforeCheck:function(id, node){ 
							if(node.isParent) {
								showTip('请选择页面，不要选择文件夹', 'error',1500,0);
							}
						},
						onClick: funcForOnClickEvent,
						onCheck: funcForOnCheckEvent
					}
				};
			
			// 用户-菜单
			var zNodes=[
					<c:forEach items="${menuList}" var="menu">{id:"${menu.id}", pId:"${not empty menu.parent.id?menu.parent.id:0}", name:"${not empty menu.parent.id?menu.name:'权限列表'}"},
		            </c:forEach>];
			// 初始化树结构
			var tree = $.fn.zTree.init($("#menuTree"), setting, zNodes);	
			// 不选择父节点
			tree.setting.check.chkboxType = { "Y" : "", "N" : "" };
			// 默认选择节点
			var ids = "${userHomepage.menuIds}".split(",");
			for(var i=0; i<ids.length; i++) {
				var idss = ids[i].split("-");
				var node = tree.getNodeByParam("id", idss[0]);
				if(node) {
					list.addLast(node);
				}
				try{tree.checkNode(node, true, false);}catch(e){}
			}
			//加载用户设置
			showUserSetting();
			// 默认展开全部节点
			tree.expandAll(true);
			//选择事件
			function funcForOnCheckEvent(event, id, node) {
				if(!node.isParent) {				
					if(node.checked) {
				    	list.addLast(node);
					} else {
						list.remove(node);
					}
					showUserSetting();
				}
			};
			//点击事件
			function funcForOnClickEvent(event, id, node) {
				if(!node.isParent) {
					tree.checkNode(node, !node.checked, true, true);
				} 
			}
			//显示用户设置
			function showUserSetting() {
				$("#menuContainer").html("");
				for (var index=0; index < list.size();index++) {
					if(index%SPAN_COUNT == 0) {
						var rowid = "rowId" +  Math.floor(index/SPAN_COUNT);
						var row_html = "<div class=\"row-fluid show-grid\" id=\""+ rowid + "\"> </div>";
						$("#menuContainer").append(row_html);
					}
					var menu_html = "<div class=\"span3\" ><h5 class=\"hClass\">"+list.get(index).name+ "</h5></div>"
					$("#"+rowid).append(menu_html);
				}
			}
		});
	</script>
	
	<style> 
		.show-grid [class*="span"] {
		    background-color: #eee;
		    text-align: center;
		    -webkit-border-radius: 3px;
		    -moz-border-radius: 3px;
		    border-radius: 3px;
		    min-height: 40px;
		    line-height: 40px;
		    height:40px;
		    margin-top:10px;
		}
		.hClass {
			line-height: 40px;
		    height:40px;
		}
	</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/lg/lgUserHomepageRelationship/form">首页设置</a></li>
	</ul><br/>
	
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span6">
				<sys:message content="${message}" />
				<div class="container-fluid">
					<div class="row-fluid">
						<div class="span12" style="margin-left:30px;">
							<h3>选择菜单页面:</h3>	
						</div>
					</div>
				</div>
				<form:form id="inputForm" modelAttribute="lgUserHomepageRelationship" action="${ctx}/lg/lgUserHomepageRelationship/save" method="post" class="form-horizontal">
					<form:hidden path="id" />
					<div class="controls">
						<div id="menuTree" class="ztree" style="margin-top: 3px; float: left;"></div>
						<form:hidden path="menuIds" />
					</div>
					<div class="form-actions">
						<shiro:hasPermission name="lg:lgUserHomepageRelationship:edit"> <input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存" />&nbsp;</shiro:hasPermission>
						<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)" />
					</div>
				</form:form>
			</div>
			<div class="span6">
				<div class="container-fluid">
					<div class="row-fluid">
						<div class="span12" style="margin-left:30px;">
							<h3>您当前已设置首页:</h3>
						</div>
					</div>
				</div>
				<div class="container-fluid" id="menuContainer"></div>
			</div>
			
		</div>
	</div>
</body>
</html>