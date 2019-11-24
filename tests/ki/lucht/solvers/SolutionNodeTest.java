package ki.lucht.solvers;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.PriorityQueue;

import static org.junit.jupiter.api.Assertions.*;

class SolutionNodeTest {

    @Test
    void compareTo() {
        SolutionNode nearBy = new SolutionNode(null, "irrelevant", null, 1, 0);
        SolutionNode farAway = new SolutionNode(null, "irrelevant", null, 99, 0);

        assertSame(-1, nearBy.compareTo(farAway));
    }

    @Test
    void countMovesForRoot() {
        SolutionNode root = new SolutionNode(null, "irrelevant", null, 1, 0);

        assertSame(0, root.countMoves());
    }

    @Test
    void countMovesForChild() {
        SolutionNode root = new SolutionNode(null, "irrelevant", null, 1, 0);
        SolutionNode target = new SolutionNode(root, "irrelevant", null, 0, 0);

        assertSame(1, target.countMoves());
    }

    @Test
    void countMovesForArbitraryNumberOfChildren() {
        SolutionNode root = new SolutionNode(null, "irrelevant", null, 1, 0);
        SolutionNode closeToTarget = new SolutionNode(root, "irrelevant", null, 0, 0);
        SolutionNode closerToTarget = new SolutionNode(closeToTarget, "irrelevant", null, 0, 0);
        SolutionNode closestToTarget = new SolutionNode(closerToTarget, "irrelevant", null, 0, 0);
        SolutionNode target = new SolutionNode(closestToTarget, "irrelevant", null, 0, 0);

        assertSame(4, target.countMoves());
    }

    @Test
    void getSolutionPathForRoot() {
        SolutionNode root = new SolutionNode(null, "irrelevant", null, 1, 0);

        assertSame(1, root.getSolutionPath().size());
    }

    @Test
    void getSolutionPathForArbitraryNumberOfChildren() {
        SolutionNode root = new SolutionNode(null, "irrelevant", null, 1, 0);
        SolutionNode closeToTarget = new SolutionNode(root, "irrelevant", null, 0, 0);
        SolutionNode closerToTarget = new SolutionNode(closeToTarget, "irrelevant", null, 0, 0);
        SolutionNode closestToTarget = new SolutionNode(closerToTarget, "irrelevant", null, 0, 0);
        SolutionNode target = new SolutionNode(closestToTarget, "irrelevant", null, 0, 0);

        assertSame(5, target.getSolutionPath().size());
    }

    @Test
    void hashSetContains() {
        HashSet<SolutionNode> set = new HashSet<>();
        set.add(new SolutionNode(null, "", new int[]{1, 2, 3}, 0, 0));

        assertTrue(set.contains(new SolutionNode(null, "", new int[]{1, 2, 3}, 0, 0)));
    }

    @Test
    void priorityQueueContains() {
        PriorityQueue<SolutionNode> queue = new PriorityQueue<>();
        queue.add(new SolutionNode(null, "", new int[]{1, 2, 3}, 0, 0));

        assertTrue(queue.contains(new SolutionNode(null, "", new int[]{1, 2, 3}, 0, 0)));
    }
}