package ki.lucht.actions;

public interface Action {
    int[] generate(int[] state);
    boolean isNotAllowed(int[] state);
}
