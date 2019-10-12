package ki.lucht;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActionRightTest {

    @Test
    void execute() {
        int[] initial = new int[]{
                1, 2, 3,
                4, 0, 5,
                6, 7, 0,
        };

        int[] target = new int[]{
                1, 2, 3,
                4, 5, 0,
                6, 7, 0,
        };

        ActionRight action = new ActionRight();

        assertArrayEquals(target, action.execute(initial));
    }

    @Test
    void itSuppressesImpossibleActions() {
        ActionRight action = new ActionRight();

        int[] state1 = new int[]{
                1, 2, 0,
                3, 4, 5,
                6, 7, 8,
        };
        assertNull(action.execute(state1));

        int[] state2 = new int[]{
                1, 2, 3,
                5, 6, 0,
                7, 8, 4
        };
        assertNull(action.execute(state2));

        int[] state3 = new int[]{
                1, 2, 3,
                4, 5, 6,
                7, 8, 0
        };
        assertNull(action.execute(state3));
    }
}