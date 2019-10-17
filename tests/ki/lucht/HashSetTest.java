package ki.lucht;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

public class HashSetTest {
    class Node {
        private int[] state;

        Node(int[] state) {
            this.state = state;
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(state);
        }
    }

    class UniqueMap {
        private HashMap<String, Node> nodes = new HashMap<>();

        public Node put(Node node) {
            nodes.putIfAbsent(
                    createKey(node),
                    node
            );

            return node;
        }

        public Node get(String key) {
            return nodes.get(key);
        }

        public int size() {
            return nodes.size();
        }

        private String createKey(Node node) {
            return String.valueOf(node.hashCode());
        }
    }

    @Test
    public void testHashSetLookup() {
        HashSet<int[]> set = new HashSet<>();
        set.add(new int[]{1, 2, 3});

        assertFalse(set.contains(new int[]{1, 2, 3}));
    }

    @Test
    void testConsecutiveInserts() {
        HashSet<int[]> set = new HashSet<>();
        set.add(new int[]{1, 2, 3});
        set.add(new int[]{1, 2, 3});

        assertEquals(2, set.size());
    }

    @Test
    void testHashSetWithNode() {
        Node nodeA = new Node(new int[]{1, 2, 3});
        Node nodeB = new Node(new int[]{1, 2, 3});

        HashSet<Node> set = new HashSet<>();
        set.add(nodeA);
        set.add(nodeB);

        assertEquals(2, set.size());
    }

    @Test
    void testWrappedHashMap() {
        UniqueMap nodes = new UniqueMap();
        Node nodeA = new Node(new int[]{1, 2, 3});
        Node nodeB = new Node(new int[]{1, 2, 3});

        nodes.put(nodeA);
        nodes.put(nodeB);

        assertEquals(1, nodes.size());
    }

    @Test
    void testHashMap() {
        HashMap<String, int[]> map = new HashMap<>();

        int[] firstState = {1, 2, 3};
        map.put(String.valueOf(Arrays.hashCode(firstState)), firstState);
        int[] duplicatedFirstState = {1, 2, 3};
        map.put(String.valueOf(Arrays.hashCode(duplicatedFirstState)), duplicatedFirstState);

        assertEquals(1, map.size());
        assertTrue(map.containsKey(String.valueOf(Arrays.hashCode(firstState))));

        // this shows that the last element overwrites the first, if they have the same key
        assertFalse(map.containsValue(firstState));
        assertTrue(map.containsValue(duplicatedFirstState));
    }

    @Test
    void testHashMapPutVal() {
        HashMap<String, int[]> map = new HashMap<>();

        int[] firstState = {1, 2, 3};
        map.putIfAbsent(String.valueOf(Arrays.hashCode(firstState)), firstState);
        int[] duplicatedFirstState = {1, 2, 3};
        map.putIfAbsent(String.valueOf(Arrays.hashCode(duplicatedFirstState)), duplicatedFirstState);

        assertEquals(1, map.size());
        assertTrue(map.containsKey(String.valueOf(Arrays.hashCode(firstState))));

        // this shows that the first element stays within the map if another element with the same key is about to be
        // inserted.
        assertTrue(map.containsValue(firstState));
        assertFalse(map.containsValue(duplicatedFirstState));
    }
}
