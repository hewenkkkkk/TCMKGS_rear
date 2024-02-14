package com.hewen.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hewen.entity.po.NodeTypes;
import com.hewen.entity.po.Nodes;
import com.hewen.entity.po.Relationships;
import com.hewen.entity.vo.GraphDataVo;
import com.hewen.entity.vo.NodeVo;
import com.hewen.entity.vo.RelationshipVo;
import com.hewen.exception.BusinessException;
import com.hewen.utils.ReadQuery;
import org.neo4j.driver.*;
import org.neo4j.driver.Record;
import org.neo4j.driver.types.Node;
import org.neo4j.driver.types.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;

import static org.neo4j.driver.Values.parameters;

@Service
public class GraphDataService {

    private final Driver driver;

    @Resource
    private NodesService nodesService;
    @Resource
    private RelationshipsService relationshipsService;
    @Resource
    private NodeTypesService nodeTypesService;

    private static final Logger logger = LoggerFactory.getLogger(GraphDataService.class);


    @Autowired
    public GraphDataService(Driver driver) {
        this.driver = driver;
    }

    public boolean uploadGraphData(String jsonString) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        GraphDataVo graphData;

        try {
            graphData = objectMapper.readValue(jsonString, GraphDataVo.class);
        } catch (JsonProcessingException e) {
            logger.error("JSON 解析失败", e);
            return false; // JSON解析失败
        }

        JsonNode jsonNode;
        JsonNode nodesArray;
        try {
            jsonNode = objectMapper.readTree(jsonString);
            nodesArray = jsonNode.get("nodes");
        } catch (IOException e) {
            logger.error("JSON 解析失败", e);
            return false; // JSON解析失败
        }

        Set<String> processedTypes = new HashSet<>();

        try (Session session = driver.session()) {
            for (int i = 0; i < graphData.getNodes().size(); i++) {
                NodeVo node = graphData.getNodes().get(i);
                JsonNode nodeJson = nodesArray.get(i);
                try {
                    // 插入节点到 Neo4j
                    String nodeCypher = String.format("MERGE (n:%s {id: $id}) ON CREATE SET n.name = $name, n.attributes = $attributes", node.getType());
                    Result nodeResult = session.writeTransaction(tx ->
                            tx.run(nodeCypher, parameters("id", node.getId(), "name", node.getName(), "attributes", node.getAttributes())));
                    if (nodeResult.consume().counters().nodesCreated() == 0) {
                        logger.error("创建新节点失败");
                        return false; // 创建新节点失败
                    }

                    // 检查并插入新的 node type
                    String nodeType = node.getType();
                    if (!processedTypes.contains(nodeType)) {
                        if (!nodeTypesService.exists(nodeType)) {
                            nodeTypesService.add(new NodeTypes(nodeType));
                        }
                        processedTypes.add(nodeType);
                    }

                    // 插入节点到 SQL
                    Nodes newNode = new Nodes();
                    newNode.setId(node.getId());
                    newNode.setName(node.getName());
                    newNode.setAttributes(node.getAttributes());
                    newNode.setType(node.getType());

                    // 从 JSON 提取 imgUrl
                    String imgUrl = null;
                    if (nodeJson.has("imgUrl")) {
                        imgUrl = nodeJson.get("imgUrl").asText();
                    }
                    newNode.setImgurl(imgUrl);

                    nodesService.add(newNode);

                } catch (Exception e) {
                    logger.error("插入节点失败", e);
                    return false; // 插入节点失败
                }
            }

            for (RelationshipVo relationship : graphData.getRelationships()) {
                try {
                    // 插入关系到 Neo4j
                    Result relationshipResult = session.writeTransaction(tx ->
                            tx.run("MATCH (a), (b) WHERE a.id = $sourceId AND b.id = $targetId " +
                                            "MERGE (a)-[r:" + relationship.getType() + " {attributes: $attributes}]->(b)",
                                    parameters("sourceId", relationship.getSource(), "targetId", relationship.getTarget(), "attributes", relationship.getAttributes())));
                    if (relationshipResult.consume().counters().relationshipsCreated() == 0) {
                        logger.error("创建新关系失败");
                        return false; // 创建新关系失败
                    }

                    // 插入关系到 SQL
                    Relationships newRelationship = new Relationships();
                    newRelationship.setSource(relationship.getSource());
                    newRelationship.setTarget(relationship.getTarget());
                    newRelationship.setType(relationship.getType());
                    newRelationship.setAttributes(relationship.getAttributes());
                    relationshipsService.add(newRelationship);

                } catch (Exception e) {
                    logger.error("插入关系失败", e);
                    return false; // 插入关系失败
                }
            }

            return true; // 数据导入成功
        } catch (Exception e) {
            logger.error("数据库会话出错", e);
            return false; // 数据库会话出错
        }
    }






    public String addNode(String jsonNode) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        NodeVo node = objectMapper.readValue(jsonNode, NodeVo.class);

        try (Session session = driver.session()) {
            String nodeCypher = String.format("CREATE (n:%s {id: $id, name: $name, attributes: $attributes})", node.getType());
            Result nodeResult = session.writeTransaction(tx ->
                    tx.run(nodeCypher,
                            parameters("id", node.getId(), "name", node.getName(), "attributes", node.getAttributes())));
            if (nodeResult.consume().counters().nodesCreated() == 0) {
                return "创建节点失败";
            }
            return "节点创建成功";
        }
    }


    public String createSingleRelationship(String jsonRelationship) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        RelationshipVo relationship = objectMapper.readValue(jsonRelationship, RelationshipVo.class);

        try (Session session = driver.session()) {
            String relationshipCypher = String.format("MATCH (a {id: $sourceId}), (b {id: $targetId}) " +
                            "CREATE (a)-[r:%s {attributes: $attributes}]->(b)",
                    relationship.getType());
            Result relationshipResult = session.writeTransaction(tx ->
                    tx.run(relationshipCypher,
                            parameters("sourceId", relationship.getSource(), "targetId", relationship.getTarget(),
                                    "attributes", relationship.getAttributes())));
            if (relationshipResult.consume().counters().relationshipsCreated() == 0) {
                return "创建关系失败";
            }
            return "关系创建成功";
        }
    }


    public String findShortestPath(String jsonInput) throws JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> inputMap = objectMapper.readValue(jsonInput, Map.class);

        String nodeStartId = inputMap.get("nodeStartId");
        String nodeEndId = inputMap.get("nodeEndId");
        try (Session session = driver.session()) {
            String cypherQuery = "MATCH (start {id: $nodeId1}), (end {id: $nodeId2}) " +
                    "MATCH p = shortestPath((start)-[*..15]-(end)) " +
                    "RETURN p";
            Result result = session.run(cypherQuery, parameters("nodeId1", nodeStartId, "nodeId2", nodeEndId));

            if (result.hasNext()) {
                Record record = result.next();
                Path shortestPath = record.get("p").asPath();
                //TODO 这里可以根据需要进一步处理路径数据
                return "最短路径: " + shortestPath.toString();
            } else {
                return "未找到路径";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "查询过程中出现错误";
        }
    }


    @Transactional(rollbackFor = Exception.class)
    public boolean updateNodeById(String jsonNodeData) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> nodeData = objectMapper.readValue(jsonNodeData, Map.class);

        String nodeId = (String) nodeData.get("id");
        String newType = (String) nodeData.remove("type"); // 单独处理 type，不作为字段

        boolean types = nodeTypesService.exists(newType);
        if (!types) {
            // 如果新类型不存在，先添加
            nodeTypesService.add(new NodeTypes(newType));
        }

        Nodes nodes = new Nodes();
        nodes.setId(nodeId);
        nodes.setType(newType);
        nodes.setAttributes(nodeData.get("attributes").toString());
        nodes.setName(nodeData.get("name").toString());
        if (nodeData.containsKey("imgurl")) {
            nodes.setImgurl(nodeData.get("imgurl").toString());
        } else {
            nodes.setImgurl(null);
        }

        nodesService.updateNodesById(nodes, nodeId);

        try (Session session = driver.session()) {
            // 移除所有已知的标签
            List<String> allKnownTypes = nodeTypesService.getAllTypes();
            for (String type : allKnownTypes) {
                String removeTypeCypher = "MATCH (n) WHERE n.id = $id REMOVE n:" + type + " RETURN count(n) as count";
                Result result = session.run(removeTypeCypher, Map.of("id", nodeId));
                if (result.hasNext() && result.next().get("count").asInt() == 0) {
                    return false;

                }
            }

            // 更新节点属性
            String updatePropsCypher = "MATCH (n) WHERE n.id = $id SET n += $props RETURN count(n) as count";
            Result resultProps = session.run(updatePropsCypher, Map.of("id", nodeId, "props", nodeData));
            if (resultProps.hasNext() && resultProps.next().get("count").asInt() == 0) {
                return false;
            }

            // 添加新类型（标签）
            String addTypeCypher = "MATCH (n) WHERE n.id = $id SET n:" + newType + " RETURN count(n) as count";
            Result resultType = session.run(addTypeCypher, Map.of("id", nodeId));
            if (resultType.hasNext() && resultType.next().get("count").asInt() == 0) {
                throw new BusinessException("类型更新失败：未找到指定 ID 的节点");
            }

            return true;
        } catch (Exception e) {
            // Neo4j 更新失败，抛出异常触发事务回滚
            return false;
        }
    }


    public boolean deleteNodeById(String jsonNodeData) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> nodeData = objectMapper.readValue(jsonNodeData, Map.class);

        String nodeId = nodeData.get("nodeId");

        try (Session session = driver.session()) {
            String deleteCypher = "MATCH (n) WHERE n.id = $id DETACH DELETE n RETURN count(n) as count";
            Result result = session.run(deleteCypher, Map.of("id", nodeId));
            if (result.hasNext() && result.next().get("count").asInt() > 0) {
                return true;
            } else {
                return false;
            }
        }
    }

    public Map<String, Object> getGraphData(int limit) {
        Map<String, Object> graphData = new HashMap<>();
        List<Map<String, Object>> nodes = new ArrayList<>();
        List<Map<String, Object>> relationships = new ArrayList<>();

        try (Session session = driver.session()) {
            // 节点查询，获取节点的基本信息和标签，同时限制返回的节点数量
            String nodeQuery = "MATCH (n) RETURN n.id AS id, n.name AS name, n.attributes AS attributes, labels(n)[0] AS label LIMIT $limit";
            session.run(nodeQuery, Collections.singletonMap("limit", limit)).stream().forEach(record -> {
                Map<String, Object> node = new HashMap<>();
                node.put("id", record.get("id").asString());
                node.put("name", record.get("name").asString());
                node.put("label_0", record.get("label").asString());  // 获取单个标签
                node.put("attributes", record.get("attributes").asString());
                nodes.add(node);
            });

            // 关系查询，获取关系的基本信息和类型
            String relationshipQuery = "MATCH ()-[r]->() RETURN r.attributes AS attributes, TYPE(r) AS type, startNode(r).id AS source, endNode(r).id AS target LIMIT $limit";
            session.run(relationshipQuery, Collections.singletonMap("limit", limit)).stream().forEach(record -> {
                Map<String, Object> relationship = new HashMap<>();
                relationship.put("source", record.get("source").asString());
                relationship.put("target", record.get("target").asString());
                relationship.put("type", record.get("type").asString()); // 获取关系的类型
                relationship.put("attributes", record.get("attributes").asString());
                relationships.add(relationship);
            });

            graphData.put("nodes", nodes);
            graphData.put("edges", relationships);
        }
        return graphData;
    }





    public boolean deleteRelationship(String jsonNodeData) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> nodeData = objectMapper.readValue(jsonNodeData, Map.class);
        String source = nodeData.get("source");
        String target = nodeData.get("target");

        try (Session session = driver.session()) {
            return session.writeTransaction(tx -> {
                // 执行删除操作
                tx.run("MATCH (a)-[r]->(b) WHERE a.id = $source AND b.id = $target DELETE r",
                        parameters("source", source, "target", target));

                // 检查关系是否被删除
                Result result = tx.run("MATCH (a)-[r]->(b) WHERE a.id = $source AND b.id = $target RETURN r",
                        parameters("source", source, "target", target));
                return !result.hasNext(); // 如果没有结果，表示删除成功
            });
        } catch (Exception e) {
            // 处理异常情况
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateRelationship(String jsonNodeData) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        RelationshipVo relationshipData = objectMapper.readValue(jsonNodeData, RelationshipVo.class);

        try (Session session = driver.session()) {
            return session.writeTransaction(tx -> {
                // 删除现有关系
                String deleteQuery = "MATCH (a)-[r]->(b) WHERE a.id = $source AND b.id = $target DELETE r";
                tx.run(deleteQuery, parameters("source", relationshipData.getSource(), "target", relationshipData.getTarget()));

                // 创建具有新类型的关系
                String createQuery = "MATCH (a), (b) WHERE a.id = $source AND b.id = $target " +
                        "CREATE (a)-[r:" + relationshipData.getType() + "]->(b) " +
                        "SET r.attributes = $attributes " +
                        "RETURN count(r) > 0 as updated";

                Result result = tx.run(createQuery,
                        parameters("source", relationshipData.getSource(),
                                "target", relationshipData.getTarget(),
                                "attributes", relationshipData.getAttributes()));

                // 检查是否成功创建了新关系
                return result.single().get("updated").asBoolean();
            });
        } catch (Exception e) {
            // 处理异常情况
            e.printStackTrace();
            return false;
        }
    }




    public Map<String, Object> getRelatedNodesAndRelationships(String jsonNodeName) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> nodeData = objectMapper.readValue(jsonNodeName, Map.class);
        String nodeName = nodeData.get("name");

        Map<String, Object> graphData = new HashMap<>();
        List<Map<String, Object>> nodes = new ArrayList<>();
        List<Map<String, Object>> edges = new ArrayList<>();

        try (Session session = driver.session()) {
            // 首先查询指定名称的节点
            String nodeQuery = "MATCH (n {name: $name}) RETURN n.id AS id, n.name AS name, n.attributes AS attributes, labels(n)[0] AS label";
            var nodeResult = session.run(nodeQuery, parameters("name", nodeName));

            if (!nodeResult.hasNext()) {
                graphData.put("error", "未找到名称为 '" + nodeName + "' 的节点");
                return graphData;
            }

            var nodeRecord = nodeResult.single();
            Map<String, Object> mainNode = new HashMap<>();
            mainNode.put("id", nodeRecord.get("id").asString());
            mainNode.put("name", nodeRecord.get("name").asString());
            mainNode.put("attributes", nodeRecord.get("attributes").asString());
            mainNode.put("label_0", nodeRecord.get("label").asString());
            nodes.add(mainNode);

            // 然后检查是否有关联的一度节点和关系
            String relatedNodesQuery = "MATCH (n {name: $name})-[r]-(related) " +
                    "RETURN related.id AS relatedId, related.name AS relatedName, related.attributes AS relatedAttributes, labels(related)[0] AS relatedLabel, " +
                    "TYPE(r) AS relationshipType, r.attributes AS relationshipAttributes";
            var relatedNodesResult = session.run(relatedNodesQuery, parameters("name", nodeName));

            relatedNodesResult.stream().forEach(record -> {
                Map<String, Object> relatedNode = new HashMap<>();
                relatedNode.put("id", record.get("relatedId").asString());
                relatedNode.put("name", record.get("relatedName").asString());
                relatedNode.put("attributes", record.get("relatedAttributes").asString());
                relatedNode.put("label_0", record.get("relatedLabel").asString());

                if (nodes.stream().noneMatch(n -> n.get("id").equals(relatedNode.get("id")))) {
                    nodes.add(relatedNode);
                }

                Map<String, Object> edge = new HashMap<>();
                edge.put("source", nodeRecord.get("id").asString());
                edge.put("target", record.get("relatedId").asString());
                edge.put("type", record.get("relationshipType").asString());
                edge.put("attributes", record.get("relationshipAttributes").asString());

                edges.add(edge);
            });
        } catch (Exception e) {
            graphData.put("error", "查询过程中发生错误: " + e.getMessage());
            return graphData;
        }

        graphData.put("nodes", nodes);
        graphData.put("edges", edges);
        return graphData;
    }

    /**
     * 执行 Cypher 查询并返回结果。只允许查询操作。
     * @param jsonCypherQuery Cypher 查询语句。
     * @return 查询结果或错误信息。
     */
    public Map<String, Object> executeCypherQuery(String jsonCypherQuery) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> nodeData = objectMapper.readValue(jsonCypherQuery, Map.class);
        String cypherQuery = nodeData.get("cypher");

        Map<String, Object> response = new HashMap<>();
        boolean isReadQuery = ReadQuery.isReadQuery(cypherQuery);
        if (!isReadQuery) {
            response.put("error", "只允许执行查询操作。");
            return response;
        }

        try (Session session = driver.session()) {
            List<Map<String, Object>> queryResults = new ArrayList<>();
            Result result = session.run(cypherQuery);

            while (result.hasNext()) {
                Record record = result.next();
                Map<String, Object> row = new HashMap<>();
                record.fields().forEach(field -> {
                    Value value = field.value();
                    if (value.type().name().equals("NODE")) {
                        Node node = value.asNode();
                        Map<String, Object> nodeDataMap = new HashMap<>();
                        node.keys().forEach(key -> nodeDataMap.put(key, node.get(key).asObject()));
                        row.put(field.key(), nodeDataMap);
                    } else {
                        // 对于其他类型，直接转换
                        row.put(field.key(), value.asObject());
                    }
                });
                queryResults.add(row);
            }
            response.put("data", queryResults);
        } catch (Exception e) {
            response.put("error", "执行 Cypher 查询失败: " + e.getMessage());
        }
        return response;
    }


}