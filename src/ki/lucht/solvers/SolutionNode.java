package ki.lucht.solvers;

import java.util.ArrayList;
import java.util.Stack;

class SolutionNode implements Comparable {
    private SolutionNode parent;
    String createdByAction;
    int[] state;
    int distance;

    public SolutionNode(SolutionNode parent, String createdByAction, int[] state, int distance) {
        this.parent = parent;
        this.createdByAction = createdByAction;
        this.state = state;
        this.distance = distance;
    }

    @Override
    public int compareTo(Object o) {
        if (!(o instanceof SolutionNode)) {
            throw new IllegalArgumentException("Object of type [Node] can only be compared with object of type [Node].");
        }

        return Integer.compare(distance, ((SolutionNode)o).distance);
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
