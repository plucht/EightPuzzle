package ki.lucht.solvers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class SolutionNodeTest {

    @Test
    void compareTo() {
        SolutionNode nearBy = new SolutionNode(null, "irrelevant", null, 1);
        SolutionNode farAway = new SolutionNode(null, "irrelevant", null, 99);

        assertSame(-1, nearBy.compareTo(farAway));
    }

    @Test
    void countMovesForRoot() {
        SolutionNode root = new SolutionNode(null, "irrelevant", null, 1);

        assertSame(0, root.countMoves());
    }

    @Test
    void countMovesForChild() {
        SolutionNode root = new SolutionNode(null, "irrelevant", null, 1);
        SolutionNode target = new SolutionNode(root, "irrelevant", null, 0);

        assertSame(1, target.countMoves());
    }

    @Test
    void countMovesForArbitraryNumberOfChildren() {
        SolutionNode root = new SolutionNode(null, "irrelevant", null, 1);
        SolutionNode closeToTarget = new SolutionNode(root, "irrelevant", null, 0);
        SolutionNode closerToTarget = new SolutionNode(closeToTarget, "irrelevant", null, 0);
        SolutionNode closestToTarget = new SolutionNode(closerToTarget, "irrelevant", null, 0);
        SolutionNode target = new SolutionNode(closestToTarget, "irrelevant", null, 0);

        assertSame(4, target.countMoves());
    }

    @Test
    void getSolutionPathForRoot() {
        SolutionNode root = new SolutionNode(null, "irrelevant", null, 1);

        assertSame(1, root.getSolutionPath().size());
    }

    @Test
    void getSolutionPathForArbitraryNumberOfChildren() {
        SolutionNode root = new SolutionNode(null, "irrelevant", null, 1);
        SolutionNode closeToTarget = new SolutionNode(root, "irrelevant", null, 0);
        SolutionNode closerToTarget = new SolutionNode(closeToTarget, "irrelevant", null, 0);
        SolutionNode closestToTarget = new SolutionNode(closerToTarget, "irrelevant", null, 0);
        SolutionNode target = new SolutionNode(closestToTarget, "irrelevant", null, 0);

        assertSame(5, target.getSolutionPath().size());
    }

    @Test
    void getSolutionDescriptionForRoot() {
        SolutionNode root = new SolutionNode(null, "irrelevant", new int[]{1, 0, 2}, 1);
        SolutionNode target = new SolutionNode(root, "right", new int[]{1, 2, 0}, 0);

        assertEquals("Move right", target.getSolutionDescription().get(0));
    }
}