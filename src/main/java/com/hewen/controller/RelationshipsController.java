package com.hewen.controller;

import java.util.List;
import java.util.Map;

import com.hewen.entity.query.RelationshipsQuery;
import com.hewen.entity.po.Relationships;
import com.hewen.entity.vo.ResponseVO;
import com.hewen.service.RelationshipsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 *  Controller
 */
@RestController("relationshipsController")
@RequestMapping("/relationships")
public class RelationshipsController extends ABaseController{

	@Resource
	private RelationshipsService relationshipsService;
	/**
	 * 根据条件分页查询
	 */
	@RequestMapping("/loadDataList")
	public ResponseVO loadDataList(RelationshipsQuery query){
		return getSuccessResponseVO(relationshipsService.findListByPage(query));
	}

	/**
	 * 新增
	 */
	@RequestMapping("/add")
	public ResponseVO add(Relationships bean) {
		relationshipsService.add(bean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 批量新增
	 */
	@RequestMapping("/addBatch")
	public ResponseVO addBatch(@RequestBody List<Relationships> listBean) {
		relationshipsService.addBatch(listBean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 批量新增/修改
	 */
	@RequestMapping("/addOrUpdateBatch")
	public ResponseVO addOrUpdateBatch(@RequestBody List<Relationships> listBean) {
		relationshipsService.addBatch(listBean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据Id查询对象
	 */
	@RequestMapping("/getRelationshipsById")
	public ResponseVO getRelationshipsById(Integer id) {
		return getSuccessResponseVO(relationshipsService.getRelationshipsById(id));
	}

	/**
	 * 根据Id修改对象
	 */
	@RequestMapping("/updateRelationshipsById")
	public ResponseVO updateRelationshipsById(Relationships bean,Integer id) {
		relationshipsService.updateRelationshipsById(bean,id);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据Id删除
	 */
	@RequestMapping("/deleteRelationshipsById")
	public ResponseVO deleteRelationshipsById(Integer id) {
		relationshipsService.deleteRelationshipsById(id);
		return getSuccessResponseVO(null);
	}

	@GetMapping("/countRelationships")
	public ResponseVO countRelationships(){
		return getSuccessResponseVO(relationshipsService.countRelationships());
	}

	@GetMapping("/countRelationshipType")
	public ResponseVO countRelationshipType(){
		List<Map<String, Long>> countRelationshipType = relationshipsService.countRelationshipType();
		return getSuccessResponseVO(countRelationshipType);
	}
}