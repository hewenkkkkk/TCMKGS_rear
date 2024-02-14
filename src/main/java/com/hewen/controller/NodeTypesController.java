package com.hewen.controller;

import java.util.List;

import com.hewen.entity.query.NodeTypesQuery;
import com.hewen.entity.po.NodeTypes;
import com.hewen.entity.vo.ResponseVO;
import com.hewen.service.NodeTypesService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 *  Controller
 */
@RestController("nodeTypesController")
@RequestMapping("/nodeTypes")
public class NodeTypesController extends ABaseController{

	@Resource
	private NodeTypesService nodeTypesService;
	/**
	 * 根据条件分页查询
	 */
	@RequestMapping("/loadDataList")
	public ResponseVO loadDataList(NodeTypesQuery query){
		return getSuccessResponseVO(nodeTypesService.findListByPage(query));
	}

	/**
	 * 新增
	 */
	@RequestMapping("/add")
	public ResponseVO add(NodeTypes bean) {
		nodeTypesService.add(bean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 批量新增
	 */
	@RequestMapping("/addBatch")
	public ResponseVO addBatch(@RequestBody List<NodeTypes> listBean) {
		nodeTypesService.addBatch(listBean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 批量新增/修改
	 */
	@RequestMapping("/addOrUpdateBatch")
	public ResponseVO addOrUpdateBatch(@RequestBody List<NodeTypes> listBean) {
		nodeTypesService.addBatch(listBean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据TypeId查询对象
	 */
	@RequestMapping("/getNodeTypesByTypeId")
	public ResponseVO getNodeTypesByTypeId(Integer typeId) {
		return getSuccessResponseVO(nodeTypesService.getNodeTypesByTypeId(typeId));
	}

	/**
	 * 根据TypeId修改对象
	 */
	@RequestMapping("/updateNodeTypesByTypeId")
	public ResponseVO updateNodeTypesByTypeId(NodeTypes bean,Integer typeId) {
		nodeTypesService.updateNodeTypesByTypeId(bean,typeId);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据TypeId删除
	 */
	@RequestMapping("/deleteNodeTypesByTypeId")
	public ResponseVO deleteNodeTypesByTypeId(Integer typeId) {
		nodeTypesService.deleteNodeTypesByTypeId(typeId);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据TypeName查询对象
	 */
	@RequestMapping("/getNodeTypesByTypeName")
	public ResponseVO getNodeTypesByTypeName(String typeName) {
		return getSuccessResponseVO(nodeTypesService.getNodeTypesByTypeName(typeName));
	}

	/**
	 * 根据TypeName修改对象
	 */
	@RequestMapping("/updateNodeTypesByTypeName")
	public ResponseVO updateNodeTypesByTypeName(NodeTypes bean,String typeName) {
		nodeTypesService.updateNodeTypesByTypeName(bean,typeName);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据TypeName删除
	 */
	@RequestMapping("/deleteNodeTypesByTypeName")
	public ResponseVO deleteNodeTypesByTypeName(String typeName) {
		nodeTypesService.deleteNodeTypesByTypeName(typeName);
		return getSuccessResponseVO(null);
	}
}