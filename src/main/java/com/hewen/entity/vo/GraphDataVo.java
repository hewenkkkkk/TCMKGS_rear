package com.hewen.entity.vo;

import java.util.List;

public class GraphDataVo {
    private List<NodeVo> nodes;
    private List<RelationshipVo> relationships;

    public GraphDataVo() {
    }

    // Getters 和 Setters
    public List<NodeVo> getNodes() {
        return nodes;
    }

    public void setNodes(List<NodeVo> nodes) {
        this.nodes = nodes;
    }

    public List<RelationshipVo> getRelationships() {
        return relationships;
    }

    public void setRelationships(List<RelationshipVo> relationships) {
        this.relationships = relationships;
    }
}

