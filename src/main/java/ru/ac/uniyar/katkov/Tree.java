package ru.ac.uniyar.katkov;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.util.List;

public class Tree {
    private Node root;
    private long idCounter = 0;



    public Tree(String rootName) {
        root = new Node(rootName);
        root.setTree(this);
    }

    public Tree(){}

    public Node getRoot() {
        return root;
    }

    public void setIdCounter(long idCounter) {
        this.idCounter = idCounter;
    }
    public void setRoot(Node root) {
        this.root = root;
    }
    public long getIdCounter() {
        return idCounter;
    }

    public long createId() {
        ++idCounter;
        return idCounter;
    }

    public List<Node> deepSearchByName(String name) {
        return root.deepSearchByName(name);
    }

    public Node deepSearchById(long id) {
        return root.deepSearchById(id);
    }
    public String toJsonObject() {
        String res;
        try {
            res = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            res = "";
        }
        return res;
    }
    public static Tree fromJson(String json){
        try{
            Tree tree = new ObjectMapper().readValue(json, Tree.class);
            tree.getRoot().setTree(tree);
            return tree;
        } catch (JsonProcessingException e){
            return null;
        }
    }
}
