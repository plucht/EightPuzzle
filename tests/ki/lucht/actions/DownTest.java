package ki.lucht.actions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class DownTest {

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

        Down action = new Down();

        assertArrayEquals(target, action.execute(initial));
    }

    @ParameterizedTest
    @MethodSource("stateProvider")
    void itSuppressesImpossibleActions(int[] state) {
        Down action = new Down();

        assertNull(action.execute(state));
    }

    static Stream<int[]> stateProvider() {
        return Stream.of(
                new int[]{
                        6, 1, 2,
                        3, 4, 5,
                        0, 7, 8,
                },
                new int[]{
                        1, 8, 3,
                        2, 5, 6,
                        7, 0, 4
                },
                new int[]{
                        1, 2, 8,
                        4, 5, 6,
                        3, 7, 0
                }
        );
    }
}