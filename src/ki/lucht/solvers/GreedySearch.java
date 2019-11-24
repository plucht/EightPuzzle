package ki.lucht.solvers;

import ki.lucht.actions.Action;
import ki.lucht.heuristics.Heuristic;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;

public class GreedySearch {
    protected Heuristic heuristic;
    protected Action[] actions;

    public GreedySearch(Heuristic heuristic, Action[] actions) {
        this.heuristic = heuristic;
        this.actions = actions;
    }

    public SolutionNode solve(int[] initial, int[] target) {
        if (goalTest(initial, target)) {
            return new SolutionNode(null, "Initial", initial, 0, 0);
        }

        HashSet<SolutionNode> closedList = new HashSet<>();
        PriorityQueue<SolutionNode> openList = new PriorityQueue<>();
        openList.add(new SolutionNode(null, "Initial", initial, heuristic.estimateCosts(initial, target), 0));

        while (!openList.isEmpty()) {
            SolutionNode currentNode = openList.poll();

            if (goalTest(currentNode.state, target)) {
                return currentNode;
            }

            closedList.add(currentNode);

            for (Action action : actions) {
                if (action.isNotAllowed(currentNode.state)) {
                    continue;
                }

                int[] generatedState = action.generate(currentNode.state);
                int depth = currentNode.depth + 1;
                SolutionNode generatedNode = new SolutionNode(
                        currentNode,
                        action.toString(),
                        generatedState,
                        depth + heuristic.estimateCosts(generatedState, target),
                        depth
                );

                if (!openList.contains(generatedNode) && !closedList.contains(generatedNode)) {
                    openList.add(generatedNode);
                } else if (openList.remove(generatedNode)) {
                    openList.add(generatedNode);
                }
            }
        }

        return null;
    }

    protected boolean goalTest(int[] currentState, int[] target) {
        return Arrays.equals(currentState, target);
    }
}
