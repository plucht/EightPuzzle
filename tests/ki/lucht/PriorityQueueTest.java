package ki.lucht;

import org.junit.jupiter.api.Test;

import java.util.PriorityQueue;

import static org.junit.jupiter.api.Assertions.assertSame;

public class PriorityQueueTest {

    class Node implements Comparable {
        String state;
        Node(String state) {
            this.state = state;
        }

        int getComparable() {
            return Integer.parseInt(state);
        }

        @Override
        public int compareTo(Object o) {
            Node otherNode = (Node)o;

            return Integer.compare(getComparable(), otherNode.getComparable());
        }
    }

    @Test
    void name() {
        PriorityQueue<Integer> openList = new PriorityQueue<>();
        openList.add(42);
        openList.add(14);
        openList.add(99);

        assertSame(14, openList.poll());
        assertSame(42, openList.poll());
        assertSame(99, openList.poll());
    }

    @Test
    void testNodeInQueue() {
        Node smallestNode = new Node("4");
        Node biggestNode = new Node("1024");
        Node arbitraryNode = new Node("42");

        PriorityQueue<Node> openList = new PriorityQueue<>();
        openList.add(arbitraryNode);
        openList.add(smallestNode);
        openList.add(biggestNode);

        assertSame("4", openList.poll().state);
    }
}
