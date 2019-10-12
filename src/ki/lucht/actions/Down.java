package ki.lucht.actions;

public class Down extends AbstractAction {
    @Override
    protected int getOffset() {
        return 3;
    }

    @Override
    protected boolean isImpossibleAction(int origin) {
        return origin == 6 || origin == 7 || origin == 8;
    }
}
