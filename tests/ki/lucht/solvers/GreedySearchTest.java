package ki.lucht.solvers;

import ki.lucht.actions.*;
import ki.lucht.heuristics.Heuristic;
import ki.lucht.heuristics.ManhattanDistance;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

class GreedySearchTest {

    @Test
    void itTerminatesIfTheInitialNodeIsTheTarget() {
        int[] target = new int[]{
                1, 2, 3,
                4, 5, 6,
                7, 8, 0,
        };

        GreedySearch solver = new GreedySearch(
                getHeuristic(),
                getActions()
        );

        int numberOfHops = solver.solve(target, target);

        assertSame(0, numberOfHops);
    }

    @Test
    void itFindsTheTargetNodeIfItIsAChild() {
        int[] initial = new int[]{
                1, 2, 3,
                4, 5, 6,
                7, 0, 8,
        };

        int[] target = new int[]{
                1, 2, 3,
                4, 5, 6,
                7, 8, 0,
        };

        GreedySearch solver = new GreedySearch(
                getHeuristic(),
                getActions()
        );

        int numberOfHops = solver.solve(initial, target);

        assertSame(1, numberOfHops);
    }

    @Test
    void itFindsTheTargetNodeIfItIsAChildOfAChild() {
        int[] initial = new int[]{
                1, 2, 3,
                4, 0, 6,
                7, 5, 8,
        };

        int[] target = new int[]{
                1, 2, 3,
                4, 5, 6,
                7, 8, 0,
        };

        GreedySearch solver = new GreedySearch(
                getHeuristic(),
                getActions()
        );

        int numberOfHops = solver.solve(initial, target);

        assertSame(2, numberOfHops);
    }

    private Heuristic getHeuristic() {
        return new ManhattanDistance();
    }

    private Action[] getActions() {
        return new AbstractAction[] {
                new Up(),
                new Left(),
                new Down(),
                new Right(),
        };
    }
}