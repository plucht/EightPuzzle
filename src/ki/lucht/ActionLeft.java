package ki.lucht;

public class ActionLeft {
    public int[] execute(int[] state) {
        int[] resultState = state.clone();

        int positionOfZero;
        for (positionOfZero = 0; positionOfZero < 9; positionOfZero++) {
            if (state[positionOfZero] == 0) {
                break;
            }
        }

        if (positionOfZero == 0 || positionOfZero == 3 || positionOfZero == 6) {
            return null;
        }

        int tmp = resultState[positionOfZero - 1];
        resultState[positionOfZero - 1] = resultState[positionOfZero];
        resultState[positionOfZero] = tmp;

        return resultState;
    }
}
