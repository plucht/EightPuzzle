package ki.lucht;

import ki.lucht.actions.*;
import ki.lucht.heuristics.ManhattanDistance;
import ki.lucht.solvers.InformedSearch;
import ki.lucht.solvers.SolutionNode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws Exception {
        int[] initial = readStateFromInput();

        SolutionNode solution = getSolutionNode(initial);

        renderSolution(initial, solution);
    }

    private static SolutionNode getSolutionNode(int[] initial) {
        InformedSearch solver = new InformedSearch(
                new ManhattanDistance(),
                new AbstractAction[]{
                        new Left(),
                        new Right(),
                        new Up(),
                        new Down(),
                }
        );

        int[] target = new int[]{
                1, 2, 3,
                4, 5, 6,
                7, 8, 0
        };

        return solver.solve(initial, target);
    }

    private static void renderSolution(int[] initial, SolutionNode solution) {
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
        for (int i = 0; i < 3; i++) {
            System.out.print("Please configure row #" + (i+1) + ": ");

            BufferedReader reader = new BufferedReader(new InputStreamReader((System.in)));
            String[] tokens = reader.readLine().split(" ");
            int[] row = Arrays.stream(tokens)
                    .map(String::trim)
                    .filter(s -> s.length() == 1)
                    .mapToInt(Integer::parseInt)
                    .filter(number -> number >= 0 && number <= 8)
                    .toArray();

            if (row.length != 3) {
                i -= 1;
                continue;
            }

            initialStateInput.add(row[0]);
            initialStateInput.add(row[1]);
            initialStateInput.add(row[2]);
        }
        System.out.println("Processing ...");

        return initialStateInput.stream()
                .mapToInt(i -> i)
                .toArray();
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
