package ki.lucht;

abstract public class AbstractAction {
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

    abstract protected boolean isImpossibleAction(int origin);

    abstract public int[] createResultState(int[] state, int origin);
}
