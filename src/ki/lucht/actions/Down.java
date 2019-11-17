package ki.lucht.actions;

public class Down extends AbstractAction {
    @Override
    protected int getOffset() {
        return 3;
    }

    @Override
    protected int[] getInvalidOrigins() {
        return new int[]{6, 7, 8};
    }
}
