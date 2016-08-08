package com.k2data.platform.modules.lg.entity.citylist;

/**
 * 树拖拽排序Entity
 * 
 * @author wangshengguo
 * @version Jun 3, 2016
 */
public class LgTreeSortEntity {
	
	private String nodeId; //节点ID
	private String targetId; //目标节点ID
	private String moveType; //相对于目标节点的拖动类型 prev next inner
	private String oldParentId; //旧的父节点
	private String newParentId;	//新的父节点
	
	private Integer nodeSort; //节点isort
	private Integer targetSort;	//目标节点isort
	
	public String getNodeId() {
		return nodeId;
	}
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	public String getTargetId() {
		return targetId;
	}
	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}
	public String getMoveType() {
		return moveType;
	}
	public void setMoveType(String moveType) {
		this.moveType = moveType;
	}
	public String getOldParentId() {
		return oldParentId;
	}
	public void setOldParentId(String oldParentId) {
		this.oldParentId = oldParentId;
	}
	public String getNewParentId() {
		return newParentId;
	}
	public void setNewParentId(String newParentId) {
		this.newParentId = newParentId;
	}
	public Integer getNodeSort() {
		return nodeSort;
	}
	public void setNodeSort(Integer nodeSort) {
		this.nodeSort = nodeSort;
	}
	public Integer getTargetSort() {
		return targetSort;
	}
	public void setTargetSort(Integer targetSort) {
		this.targetSort = targetSort;
	}
	
}
