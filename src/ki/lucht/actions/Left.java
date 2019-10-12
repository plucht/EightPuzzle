package ki.lucht.actions;

public class Left extends AbstractAction {
    @Override
    protected int getOffset() {
        return -1;
    }

    @Override
    protected boolean isImpossibleAction(int origin) {
        return origin == 0 || origin == 3 || origin == 6;
    }
}
