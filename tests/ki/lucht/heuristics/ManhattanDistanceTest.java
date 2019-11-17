package ki.lucht.heuristics;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ManhattanDistanceTest {
    int[] initial = new int[]{
            1, 2, 3,
            4, 5, 6,
            7, 0, 8
    };

    int[] target = new int[]{
            1, 2, 3,
            4, 5, 6,
            7, 8, 0
    };

    @Test
    public void itCalculatesTheDistanceForOneField() {
        ManhattanDistance heuristic = new ManhattanDistance();

        assertEquals(0, heuristic.estimateField(initial, target, 0)); // test first field
        assertEquals(1, heuristic.estimateField(initial, target, 8)); // test last field

        int[] fuzzyTarget = new int[]{
                2, 3, 8,
                4, 7, 1,
                6, 5, 0
        };

        assertEquals(3, heuristic.estimateField(initial, fuzzyTarget, 0)); // test 1
        assertEquals(2, heuristic.estimateField(initial, fuzzyTarget, 6)); // test 7
    }

    @Test
    public void itFindsAGivenValue() {
        ManhattanDistance heuristic = new ManhattanDistance();

        // find 8 in target
        assertArrayEquals(new int[]{1, 2}, heuristic.findCoordinatesForValue(target, 8));
        // find 1 in target
        assertArrayEquals(new int[]{0, 0}, heuristic.findCoordinatesForValue(target, 1));
    }

    @Test
    public void itEstimatesCostsToTargetState() {
        ManhattanDistance heuristic = new ManhattanDistance();

        assertEquals(1, heuristic.estimateCosts(initial, target));
    }
}