package ki.lucht.solvers;

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
}
