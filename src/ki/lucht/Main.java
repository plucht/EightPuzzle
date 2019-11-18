package ki.lucht;

import ki.lucht.actions.*;
import ki.lucht.heuristics.ManhattanDistance;
import ki.lucht.solvers.GreedySearch;
import ki.lucht.solvers.SolutionNode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws Exception {
        int[] target = new int[]{
                1, 2, 3,
                4, 5, 6,
                7, 8, 0
        };

        int[] initial;
        if (args.length == 1 && args[0].equals("--demo")) {
            initial = new int[]{
                    1, 2, 3,
                    4, 0, 6,
                    7, 5, 8
            };
        } else {
            initial = readStateFromInput();
        }


        GreedySearch solver = new GreedySearch(
                new ManhattanDistance(),
                new AbstractAction[]{
                        new Left(),
                        new Right(),
                        new Up(),
                        new Down(),
                }
        );

        SolutionNode solution = solver.solve(initial, target);
        if (null == solution) {
            System.out.println("No solution found. :see_no_evil:");
            System.exit(0);
        }

        Stack<SolutionNode> solutionPath = solution.getSolutionPath();
        solutionPath.pop();

        System.out.println("This is solvable with " + solutionPath.size() + " moves.");
        System.out.println("From this state: ");
        System.out.println(renderState(initial));

        while (!solutionPath.empty()) {
            SolutionNode currentNode = solutionPath.pop();
            System.out.println("Move " + currentNode.createdByAction.toLowerCase() + " to: \n" + renderState(currentNode.state));
        }
    }

    private static int[] readStateFromInput() throws IOException {
        ArrayList<Integer> initialStateInput = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            System.out.println("Please configure field at position " + i + ": ");
            BufferedReader reader = new BufferedReader(new InputStreamReader((System.in)));
            int input = Integer.parseInt(reader.readLine());

            if (initialStateInput.contains(input)) {
                System.out.println("Please use each number only once.");
                System.exit(0);
            }

            initialStateInput.add(input);
        }

        return initialStateInput.stream().mapToInt(i -> i).toArray();
    }

    private static String renderState(int[] matrix) {
        String result = "";
        for (int i = 0; i < 9; i += 3) {
            result += printCharacter(matrix[i]) + " " + printCharacter(matrix[i + 1]) + " " + printCharacter(matrix[i + 2]) + "\n";
        }

        return result;
    }

    private static String printCharacter(int number) {
        return number != 0 ? String.valueOf(number) : " ";
    }
}
