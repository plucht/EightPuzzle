package ki.lucht.actions;

import java.util.Arrays;

abstract public class AbstractAction implements Action {
    protected abstract int getOffset();

    abstract protected int[] getInvalidOrigins();

    public int[] generate(int[] state) {
        if (isNotAllowed(state)) {
            return null;
        }

        return createResultState(state, findOrigin(state));
    }

    public boolean isNotAllowed(int[] state) {
        int origin = findOrigin(state);
        int[] invalidOrigins = getInvalidOrigins();

        return Arrays.stream(invalidOrigins).anyMatch(i -> i == origin);
    }

    private int findOrigin(int[] state) {
        int origin;
        for (origin = 0; origin < 9; origin++) {
            if (state[origin] == 0) {
                break;
            }
        }

        return origin;
    }

    public int[] createResultState(int[] state, int origin) {
        int offset = getOffset();
        int[] resultState = state.clone();
        int tmp = resultState[origin + offset];
        resultState[origin + offset] = resultState[origin];
        resultState[origin] = tmp;

        return resultState;
    }
}
