package ki.lucht.actions;

public class Right extends AbstractAction {
    @Override
    protected int getOffset() {
        return 1;
    }

    @Override
    protected int[] getInvalidOrigins() {
        return new int[]{2, 5, 8};
    }
}
