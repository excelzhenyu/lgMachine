/**
 * 
 */
$(document).ready(function() {
	//绑定鼠标移动事件
	$(".div_RightMenu").find("li")
	.bind("mouseover",function(){$(this).addClass("RM_mouseover");})
	.bind("mouseout",function(){$(this).removeClass("RM_mouseover");});
	
});
//拖拽前执行  
function beforeDrag(treeId, treeNodes) {
	for (var i=0,l=treeNodes.length; i<l; i++) {
		if (treeNodes[i].drag === false) {
			return false;
		}
	}
	return true;
}
//移动节点
function moveNodeEvent(treeId, treeNodes, targetNode, moveType) {
	var data = {nodeId:treeNodes[0].id,targetId:targetNode.id,moveType:moveType,oldParentId:treeNodes[0].pId,
    		newParentId:targetNode.pId,nodeSort:treeNodes[0].isort,targetSort:targetNode.isort}; 
	$.ajax({  
		type: "post",  
		data:data,  
		dataType:'json',
		url:moveUrl,   
		success: function(json){ 
			if(json=="200" ){  
				location.reload();
				showTip('移动成功!', 'info',1500,0);
				//zTree.moveNode(targetNode, treeNodes[0], moveType, false);
			} else{  
				showTip('移动失败!', 'error',1500,0); 
			}  
 		},  
 		error: function(){  
 			showTip('移动失败!', 'error',1500,0);  
		}  
	});  
}
//拖拽释放之后执行  
function beforeDrop(treeId, treeNodes, targetNode, moveType) {
	if(treeNodes[0].pId != targetNode.pId) {
		confirmx('是否确定跨区域拖拽？', function () {moveNodeEvent(treeId, treeNodes, targetNode, moveType);}, function(){});
	} else if(treeNodes[0].pId == targetNode.pId && moveType == "inner") {
		confirmx('是否将[' + treeNodes[0].name +']作为[' + targetNode.name + ']的子节点？', function () {moveNodeEvent(treeId, treeNodes, targetNode, moveType);}, function(){});
	} else {
		moveNodeEvent(treeId, treeNodes, targetNode, moveType);
	}
	return false;
}
//点击事件
function onClick(event, treeId, treeNode) {
	if(treeNode) {
		zTree.checkNode(treeNode, !treeNode.checked, true, true);
	}
	//根节点
	if(treeNode && treeNode.isRoot) {
		$("#inputForm").css({"display": "none"});
		$("#doWhat").html("");
	} else {
		$("#inputForm").css({"display": "block"});
		$("#doWhat").html($("#m_mod").text());
		$("#parentName").val("");
		if(treeNode != null) {
			$("#id").val(treeNode.id);
			$("#parentId").val(treeNode.pId);
			$("#cityCode").val(treeNode.cityCode);
			$("#cityName").val(treeNode.name);
			$("#isort").val(treeNode.isort);
			var parentNode = treeNode.getParentNode();
			if(parentNode) {
				$("#parentName").val(parentNode.name);
			}
		}
	}
}
//右键菜单事件
function onRightClick(event, treeId, treeNode) {
	if(treeNode && treeNode.isRoot){
		//zTree.cancelSelectedNode();
		zTree.selectNode(treeNode);
		showRMenu("root", event.clientX, event.clientY);
	} else {
		zTree.selectNode(treeNode);
		showRMenu("node", event.clientX, event.clientY);
	}
}

//显示右键菜单
function showRMenu(type, x, y) {
	$("#rMenu ul").show();
	if (type=="root") {
		$("#m_mod").hide();
		$("#m_del").hide();
	} else {
		$("#m_del").show();
		$("#m_mod").show();
	}
	rMenu.css({"top":y+"px", "left":x+"px", "display":"block"});

	$("body").bind("mousedown", onBodyMouseDown);
}
//隐藏右键菜单
function hideRMenu() {
	if (rMenu) rMenu.css({"display": "none"});
	$("body").unbind("mousedown", onBodyMouseDown);
}
function onBodyMouseDown(event){
	if (!(event.target.id == "rMenu" || $(event.target).parents("#rMenu").length>0)) {
		rMenu.css({"display" : "none"});
	}
}
function beforeShowForm() {
	$("#inputForm").css({"display": "block"});
	$("#id").val("");
	$("#parentId").val("");
	$("#cityCode").val("");
	$("#cityName").val("");
	$("#parentName").val("");
	$("#isort").val("");
}
//新增节点
function addTreeNode() {
	$("#doWhat").html($("#m_add").text());
	beforeShowForm();
	hideRMenu();
	var selectNode = zTree.getSelectedNodes()[0];

	if (selectNode) {
		$("#parentId").val(selectNode.id);
		$("#parentName").val(selectNode.name);
	} 
}
//修改节点
function modTreeNode(checked) {
	$("#doWhat").html($("#m_mod").text());
	beforeShowForm();
	hideRMenu();
	var selectNode = zTree.getSelectedNodes()[0];
	if (selectNode) {
		$("#id").val(selectNode.id);
		$("#parentId").val(selectNode.pId);
		$("#cityCode").val(selectNode.cityCode);
		$("#cityName").val(selectNode.name);
		$("#isort").val(selectNode.isort);
		var parentNode = selectNode.getParentNode();
		if(parentNode) {
			$("#parentName").val(parentNode.name);
		}
	} 
}
//删除节点
function delTreeNode() {
	hideRMenu();
	var nodes = zTree.getSelectedNodes();
	var url = delUrl + "?id=";
	if (nodes && nodes.length>0) {
		url += nodes[0].id;
		if (nodes[0].children && nodes[0].children.length > 0) {
			if(confirmx('确定要删除所选节点及其包含的内容吗？', url, function(){}) == true) {
				zTree.removeNode(nodes[0]);
			}
		} else {
			if(confirmx('是否删除该城市节点？', url, function(){}) == true) {
				zTree.removeNode(nodes[0]);
			}
		}
	}
}


/**
 * 展开树
 * @param treeId  
 */
function expand_ztree(treeId){
    var treeObj = $.fn.zTree.getZTreeObj(treeId);
    treeObj.expandAll(true);
}

/**
 * 收起树：只展开根节点下的一级节点
 * @param treeId
 */
function close_ztree(treeId){
    var treeObj = $.fn.zTree.getZTreeObj(treeId);
    var nodes = treeObj.transformToArray(treeObj.getNodes());
    var nodeLength = nodes.length;
    for (var i = 0; i < nodeLength; i++) {
        //if (nodes[i].id == 'aa985d39ec174e30a925b3a0a0327424') {
        //if (nodes[i].pId == '0') {
        if (nodes[i].id == '0') {
            //根节点：展开
            treeObj.expandNode(nodes[i], true, true, false);
        } else {
            //非根节点：收起
            treeObj.expandNode(nodes[i], false, true, false);
        }
    }
}

/**
 * 搜索树，高亮显示并展示【模糊匹配搜索条件的节点s】
 * @param treeId
 * @param searchConditionId 文本框的id
 */
function search_ztree(treeId, searchConditionId){
	searchByFlag_ztree(treeId, searchConditionId, "");
}

/**
 * 搜索树，高亮显示并展示【模糊匹配搜索条件的节点s】
 * @param treeId
 * @param searchConditionId		搜索条件Id
 * @param flag 					需要高亮显示的节点标识
 */
function searchByFlag_ztree(treeId, searchConditionId, flag){
	//<1>.搜索条件
	var searchCondition = $('#' + searchConditionId).val();
	//<2>.得到模糊匹配搜索条件的节点数组集合
	var highlightNodes = new Array();
	if (searchCondition != "") {
		var treeObj = $.fn.zTree.getZTreeObj(treeId);
		highlightNodes = treeObj.getNodesByParamFuzzy("name", searchCondition, null);
		if(highlightNodes.length > 0) {
			//选择第一个
			treeObj.selectNode(highlightNodes[0]);
			onClick(event, treeId, highlightNodes[0]);
		}
	}
	//<3>.高亮显示并展示【指定节点s】
	highlightAndExpand_ztree(treeId, highlightNodes, flag);
}

/**
 * 高亮显示并展示【指定节点s】
 * @param treeId
 * @param highlightNodes 需要高亮显示的节点数组
 * @param flag			 需要高亮显示的节点标识
 */
function highlightAndExpand_ztree(treeId, highlightNodes, flag){
	var treeObj = $.fn.zTree.getZTreeObj(treeId);
	//<1>. 先把全部节点更新为普通样式
	var treeNodes = treeObj.transformToArray(treeObj.getNodes());
	for (var i = 0; i < treeNodes.length; i++) {
		treeNodes[i].highlight = false;
		treeObj.updateNode(treeNodes[i]);
	}
	//<2>.收起树, 只展开根节点下的一级节点
	//close_ztree(treeId);
	//<3>.把指定节点的样式更新为高亮显示，并展开
	if (highlightNodes != null) {
		for (var i = 0; i < highlightNodes.length; i++) {
			if (flag != null && flag != "") {
				if (highlightNodes[i].flag == flag) {
					//高亮显示节点，并展开
					highlightNodes[i].highlight = true;
					treeObj.updateNode(highlightNodes[i]);
					//高亮显示节点的父节点的父节点....直到根节点，并展示
					var parentNode = highlightNodes[i].getParentNode();
					var parentNodes = getParentNodes_ztree(treeId, parentNode);
					treeObj.expandNode(parentNodes, true, false, true);
					treeObj.expandNode(parentNode, true, false, true);
				}
			} else {
				//高亮显示节点，并展开
				highlightNodes[i].highlight = true;
				treeObj.updateNode(highlightNodes[i]);
				//高亮显示节点的父节点的父节点....直到根节点，并展示
				var parentNode = highlightNodes[i].getParentNode();
				var parentNodes = getParentNodes_ztree(treeId, parentNode);
				treeObj.expandNode(parentNodes, true, false, true);
				treeObj.expandNode(parentNode, true, false, true);
			}
		}
	}
}

/**
 * 递归得到指定节点的父节点的父节点....直到根节点
 */
function getParentNodes_ztree(treeId, node){
	if (node != null) {
		var treeObj = $.fn.zTree.getZTreeObj(treeId);
		var parentNode = node.getParentNode();
		return getParentNodes_ztree(treeId, parentNode);
	} else {
		return node;
	}
}

/**
 * 设置树节点字体样式
 */
function setFontCss_ztree(treeId, treeNode) {
	//if (treeNode.id == 'aa985d39ec174e30a925b3a0a0327424') {
	//if (treeNode.pId == '0') {
	if (treeNode.id == '0') {
		//根节点
		return {color:"#333", "font-weight":"normal"};
	} else if (treeNode.isParent == false){
		//叶子节点
		return (!!treeNode.highlight) ? {color:"#FCA530", "font-weight":"bold"} : {color:"#333", "font-weight":"normal"};
	} else {
		//父节点
		return (!!treeNode.highlight) ? {color:"#FCA530", "font-weight":"bold"} : {color:"#333", "font-weight":"normal"};
	}
}