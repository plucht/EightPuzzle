package ki.lucht;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ActionUpTest {

    @Test
    void execute() {
        int[] initial = new int[]{
                1, 2, 3,
                4, 0, 5,
                6, 7, 0,
        };

        int[] target = new int[]{
                1, 0, 3,
                4, 2, 5,
                6, 7, 0,
        };

        ActionUp action = new ActionUp();

        assertArrayEquals(target, action.execute(initial));
    }

    @Test
    void itSuppressesImpossibleActions() {
        ActionUp action = new ActionUp();

        int[] state1 = new int[]{
                0, 1, 2,
                3, 4, 5,
                6, 7, 8,
        };
        assertEquals(null, action.execute(state1));

        int[] state2 = new int[]{
                1, 0, 3,
                2, 5, 6,
                7, 8, 4
        };
        assertEquals(null, action.execute(state2));

        int[] state3 = new int[]{
                1, 2, 0,
                4, 5, 6,
                3, 7, 8
        };
        assertEquals(null, action.execute(state3));
    }
}