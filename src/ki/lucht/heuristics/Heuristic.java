package ki.lucht.heuristics;

public interface Heuristic {
    int estimateCosts(int[] currentState, int[] targetState);
}
