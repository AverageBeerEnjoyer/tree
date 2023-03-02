import org.junit.jupiter.api.Test;
import ru.ac.uniyar.katkov.Node;
import ru.ac.uniyar.katkov.Tree;

import static org.junit.jupiter.api.Assertions.*;

public class TreeTest {
    @Test
    void creationTest() {
        Tree tree = new Tree();
        Node root = tree.getRoot();
        assertNotNull(tree, "Tree does not exist");
        assertNotNull(root, "No root node");
    }

    @Test
    void idIsUniqueTest() {
        Tree tree = new Tree();
        Node root = tree.getRoot();
        Node node1 = new Node("node1");
        Node node2 = new Node("node2");
        root.addChild(node1);
        root.addChild(node2);
        assertNotEquals(root.getId(), node1.getId(), "Found equal ids");
        assertNotEquals(root.getId(), node2.getId(), "Found equal ids");
        assertNotEquals(node1.getId(), node2.getId(), "Found equal ids");
    }
}
