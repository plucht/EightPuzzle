package ki.lucht;

public class Main {

    public static void main(String[] args) {
        int[] initial = new int[]{
                1, 2, 3,
                4, 5, 6,
                7, 0, 8
        };

        int[] target = new int[]{
                1, 2, 3,
                4, 5, 6,
                7, 8, 0
        };

        printMatrix(initial);
    }

    private static void printMatrix(int[] matrix) {
        for (int i = 0; i < 9; i += 3) {
            System.out.println(printCharacter(matrix[i]) + " " + printCharacter(matrix[i+1]) + " " + printCharacter(matrix[i+2]));
        }
    }

    private static String printCharacter(int number) {
        return number != 0 ? String.valueOf(number) : " ";
    }
}
