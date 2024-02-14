package com.hewen.controller;

import java.util.List;
import java.util.Map;

import com.hewen.entity.query.NodesQuery;
import com.hewen.entity.po.Nodes;
import com.hewen.entity.vo.ResponseVO;
import com.hewen.service.NodesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 *  Controller
 */
@RestController("nodesController")
@RequestMapping("/nodes")
public class NodesController extends ABaseController{

	@Resource
	private NodesService nodesService;
	/**
	 * 根据条件分页查询
	 */
	@RequestMapping("/loadDataList")
	public ResponseVO loadDataList(NodesQuery query){
		return getSuccessResponseVO(nodesService.findListByPage(query));
	}

	/**
	 * 新增
	 */
	@RequestMapping("/add")
	public ResponseVO add(Nodes bean) {
		nodesService.add(bean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 批量新增
	 */
	@RequestMapping("/addBatch")
	public ResponseVO addBatch(@RequestBody List<Nodes> listBean) {
		nodesService.addBatch(listBean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 批量新增/修改
	 */
	@RequestMapping("/addOrUpdateBatch")
	public ResponseVO addOrUpdateBatch(@RequestBody List<Nodes> listBean) {
		nodesService.addBatch(listBean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据Id查询对象
	 */
	@RequestMapping("/getNodesById")
	public ResponseVO getNodesById(String id) {
		return getSuccessResponseVO(nodesService.getNodesById(id));
	}

	/**
	 * 根据Id修改对象
	 */
	@RequestMapping("/updateNodesById")
	public ResponseVO updateNodesById(Nodes bean,String id) {
		nodesService.updateNodesById(bean,id);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据Id删除
	 */
	@RequestMapping("/deleteNodesById")
	public ResponseVO deleteNodesById(String id) {
		nodesService.deleteNodesById(id);
		return getSuccessResponseVO(null);
	}

	@RequestMapping("/countNodesByType")
	public ResponseVO countNodesByType() {
		List<Map<String, Long>> countNodesByType = nodesService.countNodesByType();
		//System.out.println(1);
		return getSuccessResponseVO(countNodesByType);
	}

	@GetMapping("/getCountNodes")
	public ResponseVO getCountNodes() {
		Long countNodes = nodesService.countNodes();
		return getSuccessResponseVO(countNodes);
	}

	@GetMapping("/findAllType")
	public ResponseVO findAllType() {
		List<String> findAllType = nodesService.findAllType();
		return getSuccessResponseVO(findAllType);
	}
}