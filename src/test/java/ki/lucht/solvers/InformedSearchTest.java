package ki.lucht.solvers;

import ki.lucht.actions.*;
import ki.lucht.heuristics.Heuristic;
import ki.lucht.heuristics.ManhattanDistance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class InformedSearchTest {
    private final int[] target = new int[]{
            1, 2, 3,
            4, 5, 6,
            7, 8, 0,
    };

    private InformedSearch solver;

    @BeforeEach
    void setUp() {
        solver = new InformedSearch(
                getHeuristic(),
                getActions()
        );
    }

    @Test
    void itTerminatesIfTheInitialNodeIsTheTarget() {
        SolutionNode solution = solver.solve(target, target);

        assertSame(target, solution.state);
    }

    @Test
    void itFindsTheTargetOneMoveAway() {
        int[] initial = new int[]{
                1, 2, 3,
                4, 5, 6,
                7, 0, 8,
        };

        SolutionNode solution = solver.solve(initial, target);

        assertArrayEquals(target, solution.state);
    }

    @Test
    void itFindsTheTargetTwoMovesAway() {
        int[] initial = new int[]{
                1, 2, 3,
                4, 0, 6,
                7, 5, 8,
        };

        SolutionNode solution = solver.solve(initial, target);

        assertArrayEquals(target, solution.state);
    }

    private Heuristic getHeuristic() {
        return new ManhattanDistance();
    }

    private Action[] getActions() {
        return new AbstractAction[]{
                new Up(),
                new Left(),
                new Down(),
                new Right(),
        };
    }
}