package ki.lucht.actions;

abstract public class AbstractAction {
    protected abstract int getOffset();

    abstract protected boolean isImpossibleAction(int origin);

    public int[] execute(int[] state) {
        int origin = findOrigin(state);

        if (isImpossibleAction(origin)) {
            return null;
        }

        return createResultState(state, origin);
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
