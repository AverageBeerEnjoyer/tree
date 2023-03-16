package ru.ac.uniyar.katkov;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class Node {
    @JsonIgnore
    private Tree tree;
    private long id = 0;
    private String name;

    public void setId(long id) {
        this.id = id;
    }

    public Tree getTree() {
        return tree;
    }

    public Node(){}

    private final List<Node> children = new ArrayList<>();

    public Node(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId() {
        this.id = tree.createId();
        children.forEach(Node::setId);
    }
    public List<Node> getChildren() {
        return children;
    }

    public void addChild(Node child) {
        if (child == this) return;
        try {
            findByName(child.getName());

        } catch (NoSuchElementException e) {
            children.add(child);
            child.setTree(tree);
            if (tree != null) {
                child.setId();
            }
        }
    }

    public Node findByName(String name) {
        Optional<Node> res;
        res = children.stream().filter(node -> node.name.equals(name)).findFirst();
        if (res.isPresent()) return res.get();
        throw new NoSuchElementException("Not found");
    }

    protected void setTree(Tree tree) {
        this.tree = tree;
        children.forEach(node -> node.setTree(tree));
    }


    public void removeChild(String name) {
        Node node;
        try {
            node = findByName(name);
        } catch (NoSuchElementException e) {
            return;
        }
        children.remove(node);
    }

    public void removeAllChildren() {
        children.clear();
    }

    public List<Node> deepSearchByName(String name) {
        List<Node> res = new ArrayList<>();
        children.forEach(node -> {
            if (node.getName().equals(name))
                res.add(node);
        });
        children.forEach((node -> res.addAll(node.deepSearchByName(name))));
        return res;
    }

    public Node deepSearchById(long id) {
        Optional<Node> a = children.stream().filter(node -> node.id == id).findFirst();
        if (a.isPresent()) {
            return a.get();
        }
        for (Node node : children) {
            Node res;
            try {
                res = node.deepSearchById(id);
            } catch (NoSuchElementException ignored) {
                continue;
            }
            return res;
        }
        throw new NoSuchElementException();
    }

    public String toJsonObject() {
        try {
            return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
        } catch(JsonProcessingException e){
            return "";
        }
    }
    public static Node fromJson(String json){
        try{
            return new ObjectMapper().readValue(json, Node.class);
        } catch (JsonProcessingException e){
            return null;
        }
    }
}
