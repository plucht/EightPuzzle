package ki.lucht;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


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

    @ParameterizedTest
    @MethodSource("stateProvider")
    void itSuppressesImpossibleActions(int[] state) {
        ActionUp action = new ActionUp();

        assertNull(action.execute(state));
    }

    static Stream<int[]> stateProvider() {
        return Stream.of(
                new int[]{
                        0, 1, 2,
                        3, 4, 5,
                        6, 7, 8,
                },
                new int[]{
                        1, 0, 3,
                        2, 5, 6,
                        7, 8, 4
                },
                new int[]{
                        1, 2, 0,
                        4, 5, 6,
                        3, 7, 8
                }
        );
    }
}