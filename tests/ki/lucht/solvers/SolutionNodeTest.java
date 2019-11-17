package ki.lucht.solvers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
}