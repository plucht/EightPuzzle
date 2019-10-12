package ki.lucht.actions;

public class Down extends AbstractAction {
    @Override
    public int[] createResultState(int[] state, int origin) {
        int[] resultState = state.clone();
        int tmp = resultState[origin + 3];
        resultState[origin + 3] = resultState[origin];
        resultState[origin] = tmp;

        return resultState;
    }

    @Override
    protected boolean isImpossibleAction(int origin) {
        return origin == 6 || origin == 7 || origin == 8;
    }
}
