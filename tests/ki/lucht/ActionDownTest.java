package ki.lucht;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ActionDownTest {

    @Test
    void execute() {
        int[] initial = new int[]{
                1, 2, 3,
                4, 0, 5,
                6, 7, 0,
        };

        int[] target = new int[]{
                1, 2, 3,
                4, 7, 5,
                6, 0, 0,
        };

        ActionDown action = new ActionDown();

        assertArrayEquals(target, action.execute(initial));
    }

    @Test
    void itSuppressesImpossibleActions() {
        ActionDown action = new ActionDown();

        int[] state1 = new int[]{
                6, 1, 2,
                3, 4, 5,
                0, 7, 8,
        };
        assertEquals(null, action.execute(state1));

        int[] state2 = new int[]{
                1, 8, 3,
                2, 5, 6,
                7, 0, 4
        };
        assertEquals(null, action.execute(state2));

        int[] state3 = new int[]{
                1, 2, 8,
                4, 5, 6,
                3, 7, 0
        };
        assertEquals(null, action.execute(state3));
    }
}