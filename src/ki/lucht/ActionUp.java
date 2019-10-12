package ki.lucht;

public class ActionUp extends AbstractAction {
    @Override
    public int[] createResultState(int[] state, int origin) {
        int[] resultState = state.clone();
        int tmp = resultState[origin - 3];
        resultState[origin - 3] = resultState[origin];
        resultState[origin] = tmp;

        return resultState;
    }

    @Override
    protected boolean isImpossibleAction(int origin) {
        return origin == 0 || origin == 1 || origin == 2;
    }
}
