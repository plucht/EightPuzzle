package ki.lucht.actions;

public class Up extends AbstractAction {
    @Override
    protected int getOffset() {
        return -3;
    }

    @Override
    protected int[] getInvalidOrigins() {
        return new int[]{0, 1, 2};
    }
}
