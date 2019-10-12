package ki.lucht.actions;

public class Right extends AbstractAction {
    @Override
    public int[] createResultState(int[] state, int origin) {
        int[] resultState = state.clone();
        int tmp = resultState[origin + 1];
        resultState[origin + 1] = resultState[origin];
        resultState[origin] = tmp;

        return resultState;
    }

    @Override
    protected boolean isImpossibleAction(int origin) {
        return origin == 2 || origin == 5 || origin == 8;
    }
}
