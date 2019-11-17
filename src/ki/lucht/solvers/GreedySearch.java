package ki.lucht.solvers;

import ki.lucht.actions.*;
import ki.lucht.heuristics.ManhattanDistance;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;

public class GreedySearch {

    class Node implements Comparable {
        String createdByAction;
        int[] state;
        private int distance;

        public Node(String createdByAction, int[] state, int distance) {
            this.createdByAction = createdByAction;
            this.state = state;
            this.distance = distance;
        }

        @Override
        public int compareTo(Object o) {
            if (!(o instanceof Node)) {
                throw new IllegalArgumentException("Object of type [Node] can only be compared with object of type [Node].");
            }

            return Integer.compare(distance, ((Node)o).distance);
        }
    }

    ManhattanDistance heuristic = new ManhattanDistance();

    int solve(int[] initial, int[] target) {
        if (goalTest(initial, target)) {
            return 0;
        }

        AbstractAction[] actions = new AbstractAction[] {
                new Down(),
                new Up(),
                new Left(),
                new Right(),
        };

        int numberOfHops = 0;

        HashSet<int[]> closedList = new HashSet<>();
        PriorityQueue<Node> openList = new PriorityQueue<>();
        openList.add(
                new Node("Initial", initial, heuristic.estimateTotalCosts(initial, target))
        );

        while (!openList.isEmpty()) {
            Node currentNode = openList.poll();

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

                Node generatedNode = new Node(
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
