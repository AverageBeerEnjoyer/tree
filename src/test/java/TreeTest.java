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
    @Test
    void deepSearchByNameTest(){
        Tree tree = new Tree();
        Node proxi1 = new Node("proxi1");
        Node proxi2 = new Node("proxi2");
        Node target1 = new Node("target1");
        Node target2 = new Node("target2");
        proxi2.addChild(target1);
        proxi2.addChild(target2);
        proxi1.addChild(proxi2);
        tree.getRoot().addChild(proxi1);
        assertEquals(target1,tree.deepSearchByName("target1").get(0),"Found wrong node");
        assertEquals(target2, tree.deepSearchByName("target2").get(0), "Found wrong node");
    }
    @Test
    void deepSearchByIdTest(){
        Tree tree = new Tree();
        Node proxi1 = new Node("proxi1");
        Node proxi2 = new Node("proxi2");
        Node target1 = new Node("target1");
        Node target2 = new Node("target2");
        proxi2.addChild(target1);
        proxi2.addChild(target2);
        proxi1.addChild(proxi2);
        tree.getRoot().addChild(proxi1);
        long id = target1.getId();
        assertEquals(target1,tree.deepSearchById(id), "Found wrong note");
    }
}
