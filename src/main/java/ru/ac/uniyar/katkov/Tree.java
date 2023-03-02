package ru.ac.uniyar.katkov;

import java.util.List;

public class Tree {
    private final Node root;
    private long idCounter=0;
    public Tree(){
        root = new Node("Root");
        root.setTree(this);
    }
    public Node getRoot(){
        return root;
    }
    public long createId(){
        ++idCounter;
        return idCounter;
    }
    public List<Node> deepSearchByName(String name){
        return root.deepSearchByName(name);
    }
    public Node deepSearchById(long id){
        return root.deepSearchById(id);
    }
}
