package ki.lucht;

public class ManhattanDistance {
    public int estimateTotalCosts(int[] currentState, int[] targetState) {
        int totalCosts = 0;
        for (int i = 0; i < 9; i++) {
            totalCosts += estimateField(currentState, targetState, i);
        }

        return totalCosts;
    }

    public int estimateField(int[] currentState, int[] targetState, int i) {
        if (currentState[i] == 0) {
            return 0;
        }

        int[] currentPosition = new int[]{
                i % 3,
                i / 3
        };
        int valueOfCurrentField = currentState[i];
        int[] targetPosition = findCoordinatesForValue(targetState, valueOfCurrentField);

        return Math.abs(currentPosition[0] - targetPosition[0]) + Math.abs(currentPosition[1] - targetPosition[1]);
    }

    public int[] findCoordinatesForValue(int[] space, int needle) {
        int i;
        for (i = 0; i < 9; i++) {
            if (space[i] == needle) {
                break;
            }
        }

        return new int[]{
                i % 3,
                i / 3
        };
    }
}
