package ki.lucht;

import ki.lucht.actions.*;
import ki.lucht.heuristics.ManhattanDistance;
import ki.lucht.solvers.GreedySearch;
import ki.lucht.solvers.SolutionNode;

import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        int[] initial = new int[]{
                1, 2, 3,
                4, 0, 6,
                7, 5, 8
        };

        int[] target = new int[]{
                1, 2, 3,
                4, 5, 6,
                7, 8, 0
        };

        GreedySearch solver = new GreedySearch(
                new ManhattanDistance(),
                new AbstractAction[]{
                        new Up(),
                        new Left(),
                        new Down(),
                        new Right(),
                }
        );

        SolutionNode solution = solver.solve(initial, target);

        System.out.println("From this state: ");
        System.out.println(renderState(initial));

        Stack<SolutionNode> solutionPath = solution.getSolutionPath();
        solutionPath.pop();
        while (!solutionPath.empty()) {
            SolutionNode currentNode = solutionPath.pop();
            System.out.println("Move " + currentNode.createdByAction + " to: \n" + renderState(currentNode.state));
        }
    }

    private static String renderState(int[] matrix) {
        String result = "";
        for (int i = 0; i < 9; i += 3) {
            result += (matrix[i]) + " " + printCharacter(matrix[i+1]) + " " + printCharacter(matrix[i+2]) + "\n";
        }

        return result;
    }

    private static String printCharacter(int number) {
        return number != 0 ? String.valueOf(number) : " ";
    }
}
