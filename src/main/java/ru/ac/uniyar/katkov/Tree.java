package ru.ac.uniyar.katkov;

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
}
