package ru.ac.uniyar.katkov;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class Node {
    private Tree tree;
    private long id = 0;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setId() {
        this.id = tree.createId();
        children.forEach(Node::setId);
    }

    private void setParent(Node parent) {
        this.parent = parent;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void addChild(Node child) {
        if (child == this) return;
        if (child.parent != null) return;
        try {
            findByName(child.getName());

        } catch (NoSuchElementException e) {
            children.add(child);
            child.setParent(this);
            child.setTree(tree);
            if (tree != null) {
                child.setId();
            }
        }
    }

    public Node findByName(String name) {
        return children.stream().filter(node -> node.name.equals(name)).findFirst().get();
    }

    protected void setTree(Tree tree) {
        this.tree = tree;
        children.forEach(node -> node.setTree(tree));
    }


    public void removeChild(String name) {
        Node node;
        try {
            node = findByName(name);
            children.remove(node);
        } catch (NoSuchElementException ignored) {
        }
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
            try {
                return node.deepSearchById(id);
            } catch (NoSuchElementException ignored) {
            }
        }
        throw new NoSuchElementException();
    }
}
