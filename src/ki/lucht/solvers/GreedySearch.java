package ki.lucht.solvers;

import ki.lucht.actions.*;
import ki.lucht.heuristics.ManhattanDistance;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;

public class GreedySearch {

    protected ManhattanDistance heuristic;
    protected AbstractAction[] actions;

    GreedySearch(ManhattanDistance heuristic, AbstractAction[] actions) {
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
                new SolutionNode(null, "Initial", initial, heuristic.estimateTotalCosts(initial, target))
        );

        while (!openList.isEmpty()) {
            SolutionNode currentNode = openList.poll();

            if (goalTest(currentNode.state, target)) {
                return numberOfHops;
            }

            closedList.add(currentNode.state);
            numberOfHops++;

            for (AbstractAction action: actions) {
                int[] generatedState = action.execute(currentNode.state);

                if (null == generatedState) {
                    continue;
                }

                SolutionNode generatedNode = new SolutionNode(
                        currentNode,
                        action.toString(),
                        generatedState,
                        heuristic.estimateTotalCosts(generatedState, target)
                );

                if (!openList.contains(generatedNode) && !closedList.contains(generatedState)) {
                    openList.add(generatedNode);
                }
                else if (openList.removeIf(node -> node.distance > generatedNode.distance)) {
                    openList.add(generatedNode);
                }
            }
        }

        return numberOfHops;
    }

    protected boolean goalTest(int[] currentState, int[] target) {
        return Arrays.equals(currentState, target);
    }
}
