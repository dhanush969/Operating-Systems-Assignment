package matrixmultiplication;

import java.util.Random;

public class MatrixGenerator {

    public static int[][] create(int rows, int columns) {

        int[][] matrix = new int[rows][columns];
        Random random = new Random();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = random.nextInt(10);
            }
        }

        return matrix;
    }
}