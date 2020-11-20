package ki.lucht.solvers;

import java.util.Arrays;
import java.util.Stack;

public class SolutionNode implements Comparable {
    private SolutionNode parent;
    public String createdByAction;
    public int[] state;
    int distance;
    public int depth;

    public SolutionNode(SolutionNode parent, String createdByAction, int[] state, int distance, int depth) {
        this.parent = parent;
        this.createdByAction = createdByAction;
        this.state = state;
        this.distance = distance;
        this.depth = depth;
    }

    @Override
    public int compareTo(Object o) {
        if (!(o instanceof SolutionNode)) {
            throw new IllegalArgumentException("Object of type [Node] can only be compared with object of type [Node].");
        }

        return Integer.compare(distance, ((SolutionNode) o).distance);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof SolutionNode)) {
            return false;
        }

        SolutionNode otherNode = (SolutionNode) obj;

        if (state.length != otherNode.state.length) {
            return false;
        }

        for (int i = 0; i < state.length; i++) {
            if (state[i] != otherNode.state[i]) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(state);
    }

    public int countMoves() {
        if (null == parent) {
            return 0;
        }

        return 1 + parent.countMoves();
    }

    public Stack<SolutionNode> getSolutionPath() {
        Stack<SolutionNode> solutionPath = new Stack<>();
        solutionPath.push(this);

        while (null != solutionPath.peek().parent) {
            solutionPath.push(solutionPath.peek().parent);
        }

        return solutionPath;
    }
}
