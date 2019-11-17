package ki.lucht.actions;

public class Left extends AbstractAction {
    @Override
    protected int getOffset() {
        return -1;
    }

    @Override
    protected int[] getInvalidOrigins() {
        return new int[]{0, 3, 6};
    }
}
