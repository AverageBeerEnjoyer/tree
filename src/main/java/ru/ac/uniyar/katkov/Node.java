package ru.ac.uniyar.katkov;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Node {
    private Tree tree;
    private long id=0;
    private String name;

    private Node parent;

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

    public void setName(String name){
        this.name = name;
    }
    public void setId(long id){
        this.id = id;
    }
    private void setParent(Node parent){
        this.parent = parent;
    }
    public List<Node> getChildren() {
        return children;
    }

    public void addChild(Node child) {
        if(child.parent!=null) return;
        try {
            find(child.getName());

        } catch (NoSuchElementException e) {
            children.add(child);
            child.setId(tree.createId());
        }
    }

    public Node find(String name) {
        return children.stream().filter(node -> node.name.equals(name)).findFirst().get();
    }
    protected void setTree(Tree tree){
        this.tree = tree;
    }


    public void removeChild(String name) {
        Node node;
        try {
            node = find(name);
            children.remove(node);
        } catch (NoSuchElementException ignored){}
    }

    public void removeAllChildren(){
        children.clear();
    }
}
