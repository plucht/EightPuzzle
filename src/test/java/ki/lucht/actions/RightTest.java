package ki.lucht.actions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class RightTest {

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

        Right action = new Right();

        assertArrayEquals(target, action.generate(initial));
    }

    @ParameterizedTest
    @MethodSource("stateProvider")
    void itSuppressesImpossibleActions(int[] state) {
        Right action = new Right();

        assertNull(action.generate(state));
    }

    static Stream<int[]> stateProvider() {
        return Stream.of(
                new int[]{
                        1, 2, 0,
                        3, 4, 5,
                        6, 7, 8,
                },
                new int[]{
                        1, 2, 3,
                        5, 6, 0,
                        7, 8, 4
                },
                new int[]{
                        1, 2, 3,
                        4, 5, 6,
                        7, 8, 0
                }
        );
    }
}