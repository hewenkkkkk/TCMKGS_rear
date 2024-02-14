package com.hewen.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hewen.entity.vo.ResponseVO;
import com.hewen.service.GraphDataService;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 *  Controller
 */
@RestController("neo4jController")
@RequestMapping("/neo4j")
public class Neo4jController extends ABaseController{

    @Resource
    private GraphDataService GraphDataService;

    /**
     * 导入完整数据
     * @param jsonData
     * @return
     * @throws JsonProcessingException
     */
    @PostMapping("/uploadData")
    public ResponseVO uploadData(@RequestBody String jsonData) throws JsonProcessingException {
        boolean is = GraphDataService.uploadGraphData(jsonData);
        if (is) {
            return getSuccessResponseVO("导入成功");
        } else {
            return getFailResponseVO("导入失败");
        }
    }

    /**
     * 添加节点
     * @param jsonNode
     * @return
     * @throws JsonProcessingException
     */
    @PostMapping("/addNode")
    public ResponseVO addNode(@RequestBody String jsonNode) throws JsonProcessingException {
        String result = GraphDataService.addNode(jsonNode);
        return getSuccessResponseVO(result);
    }

    /**
     * 添加关系：连接节点与节点
     * @param jsonRelationship
     * @return
     * @throws JsonProcessingException
     */
    @PostMapping("/addRelationship")
    public ResponseVO addRelationship(@RequestBody String jsonRelationship) throws JsonProcessingException {
        String result = GraphDataService.createSingleRelationship(jsonRelationship);
        return getSuccessResponseVO(result);
    }

    /**
     * 最短路径查询
     * @param jsonPath
     * @return
     * @throws JsonProcessingException
     */
    @PostMapping("/findShortestPath")
    public ResponseVO findShortestPath(@RequestBody String jsonPath) throws JsonProcessingException {
        String result = GraphDataService.findShortestPath(jsonPath);
        return getSuccessResponseVO(result);
    }

    /**
     * 更新节点
     * @param jsonNodeData
     * @return
     * @throws JsonProcessingException
     */
    @PostMapping("/updateNodeId")
    public ResponseVO updateNodeById(@RequestBody String jsonNodeData) throws JsonProcessingException {
        boolean result = GraphDataService.updateNodeById(jsonNodeData);
        if (result) {
            return getSuccessResponseVO("更新成功");
        } else {
            return getFailResponseVO("更新失败");
        }
    }


    /**
     * 删除节点
     * @param nodeId
     * @return
     * @throws JsonProcessingException
     */
    @PostMapping("/deleteNodeById")
    public ResponseVO deleteNodeById(@RequestBody String nodeId) throws JsonProcessingException {
        boolean result = GraphDataService.deleteNodeById(nodeId);
        if (result) {
            return getSuccessResponseVO("删除关系成功");
        } else {
            return getFailResponseVO("删除关系失败");
        }
    }

    /**
     * 获取图数据
     * @return
     */
    @GetMapping("/getGraphData")
    public Map<String, Object> getGraphData(@RequestParam(value = "limit") int limit){
        return GraphDataService.getGraphData(limit);
    }

    /**
     * 删除节点之间的关系
     * @param relationshipId
     * @return
     * @throws JsonProcessingException
     */
    @PostMapping("/deleteRelationshipById")
    public ResponseVO deleteRelationshipById(@RequestBody String relationshipId) throws JsonProcessingException {
        Boolean result = GraphDataService.deleteRelationship(relationshipId);
        if (result) {
            return getSuccessResponseVO("删除成功");
        } else {
            return getSuccessResponseVO("删除失败");
        }
    }

    @PostMapping("/updateRelationshipById")
    public ResponseVO updateRelationshipById(@RequestBody String jsonRelationshipData) throws JsonProcessingException {
        boolean result = GraphDataService.updateRelationship(jsonRelationshipData);
        if (result) {
            return getSuccessResponseVO("更新关系成功");
        } else {
            return getFailResponseVO("更新关系失败");
        }
    }

    /**
     * 获取节点的关系
     * @param
     * @return
     */
    @PostMapping("/searchNode")
    public ResponseVO getRelatedNodesAndRelationships(@RequestBody String requestData) throws JsonProcessingException {
        Map<String, Object> relatedNodesAndRelationships = GraphDataService.getRelatedNodesAndRelationships(requestData);
        return getSuccessResponseVO(relatedNodesAndRelationships);
    }

    /**
     * 执行cypher语句查询
     * @param jsonCypherQuery
     * @return
     */
    @PostMapping("/executeCypherQuery")
    public ResponseVO executeCypherQuery (@RequestBody String jsonCypherQuery) throws JsonProcessingException {
        Map<String, Object> result = GraphDataService.executeCypherQuery(jsonCypherQuery);
        return getSuccessResponseVO(result);
    }
}
