package ki.lucht.actions;

public class Right extends AbstractAction {
    @Override
    protected int getOffset() {
        return 1;
    }

    @Override
    protected boolean isImpossibleAction(int origin) {
        return origin == 2 || origin == 5 || origin == 8;
    }
}
