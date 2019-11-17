package ki.lucht.solvers;

import ki.lucht.actions.Action;
import ki.lucht.heuristics.Heuristic;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;

public class GreedySearch {
    protected Heuristic heuristic;
    protected Action[] actions;

    GreedySearch(Heuristic heuristic, Action[] actions) {
        this.heuristic = heuristic;
        this.actions = actions;
    }

    int solve(int[] initial, int[] target) {
        if (goalTest(initial, target)) {
            return 0;
        }

        int numberOfHops = 0;

        HashSet<int[]> closedList = new HashSet<>();
        PriorityQueue<SolutionNode> openList = new PriorityQueue<>();
        openList.add(
                new SolutionNode(null, "Initial", initial, heuristic.estimateCosts(initial, target))
        );

        while (!openList.isEmpty()) {
            SolutionNode currentNode = openList.poll();

            if (goalTest(currentNode.state, target)) {
                return numberOfHops;
            }

            closedList.add(currentNode.state);
            numberOfHops++;

            for (Action action : actions) {
                SolutionNode generatedNode = generateNode(target, currentNode, action);
                if (null == generatedNode) {
                    continue;
                }

                if (!openList.contains(generatedNode) && !closedList.contains(generatedNode.state)) {
                    openList.add(generatedNode);
                } else if (openList.removeIf(node -> node.distance > generatedNode.distance)) {
                    openList.add(generatedNode);
                }
            }
        }

        return numberOfHops;
    }

    private SolutionNode generateNode(int[] target, SolutionNode currentNode, Action action) {
        int[] generatedState = action.generate(currentNode.state);

        if (null == generatedState) {
            return null;
        }

        return new SolutionNode(
                currentNode,
                action.toString(),
                generatedState,
                heuristic.estimateCosts(generatedState, target)
        );
    }

    protected boolean goalTest(int[] currentState, int[] target) {
        return Arrays.equals(currentState, target);
    }
}
