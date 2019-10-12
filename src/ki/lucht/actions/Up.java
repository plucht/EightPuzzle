package ki.lucht.actions;

public class Up extends AbstractAction {
    @Override
    protected int getOffset() {
        return -3;
    }

    @Override
    protected boolean isImpossibleAction(int origin) {
        return origin == 0 || origin == 1 || origin == 2;
    }
}
