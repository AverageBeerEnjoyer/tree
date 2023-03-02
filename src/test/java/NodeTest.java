import org.junit.jupiter.api.Test;
import ru.ac.uniyar.katkov.Node;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class NodeTest {

    Node createNodeWith3Children(){
        Node node = new Node("node");
        Node node1 = new Node("node1");
        Node node2 = new Node("node2");
        Node node3 = new Node("node3");
        node.addChild(node1);
        node.addChild(node2);
        node.addChild(node3);
        return node;
    }
    @Test
    void addNodeTest() {
        Node node1 = new Node("node1");
        Node node2 = new Node("node2");
        node1.addChild(node2);
        assertEquals(1, node1.getChildren().size(), "Node was not added");
        assertEquals(node2, node1.getChildren().get(0), "Wrong node added");
    }

    @Test
    void addNodeTwice(){
        Node node = new Node("node");
        Node node1 = new Node("node1");
        Node node2 = new Node("node1");
        node.addChild(node1);
        node.addChild(node1);
        node.addChild(node2);
        assertEquals(1, node.getChildren().size(),"Added node with already existing name");
    }


    @Test
    void foundTest() {
        Node node = createNodeWith3Children();
        assertEquals("node1", node.findByName("node1").getName(), "Wrong node found");
        assertEquals("node2", node.findByName("node2").getName(), "Wrong node found");
        assertEquals("node3", node.findByName("node3").getName(), "Wrong node found");
    }

    @Test
    void notFoundTest(){
        Node node = createNodeWith3Children();
        assertThrows(NoSuchElementException.class,()->node.findByName("node4"), "Found not existing node");
    }

    @Test
    void removeChildTest(){
        Node node = createNodeWith3Children();
        node.removeChild("node1");
        assertThrows(NoSuchElementException.class,()->node.findByName("node1"), "Can not remove child");
        assertEquals(2, node.getChildren().size(), "Removed more than 1 nodes");
    }

    @Test
    void removeAllChildren(){
        Node node = createNodeWith3Children();
        node.removeAllChildren();
        assertEquals(0, node.getChildren().size(), "Some children was not removed");
    }

    @Test
    void changeName(){
        Node node = new Node("node");
        node.setName("node1");
        assertEquals("node1",node.getName(), "Node name is not changing");
    }
}
