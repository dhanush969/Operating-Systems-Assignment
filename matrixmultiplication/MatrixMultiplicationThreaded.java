package matrixmultiplication;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MatrixMultiplicationThreaded {

    static final int A_ROWS = 2000;
    static final int A_COLS = 100;
    static final int B_ROWS = 100;
    static final int B_COLS = 2000;

    public static void main(String[] args) throws InterruptedException {

        int[][] a = MatrixGenerator.create(A_ROWS, A_COLS);
        int[][] b = MatrixGenerator.create(B_ROWS, B_COLS);

        if (A_COLS != B_ROWS) {
            System.out.println("Matrix dimensions are not compatible.");
            return;
        }

        int[][] answer = new int[A_ROWS][B_COLS];

        System.out.println("Matrices created.");
        System.out.println("Starting multiplication...");

        long start = System.nanoTime();

        int numberOfThreads = Runtime.getRuntime().availableProcessors();
        ExecutorService service =
                Executors.newFixedThreadPool(numberOfThreads);

        for (int r = 0; r < A_ROWS; r++) {
            service.submit(new RowMultiplier(a, b, answer, r));
        }

        service.shutdown();
        service.awaitTermination(1, TimeUnit.HOURS);

        long finish = System.nanoTime();

        System.out.println("Multiplication finished.");
        System.out.println("Thread pool size: " + numberOfThreads);
        System.out.println("Time taken: "
                + ((finish - start) / 1_000_000) + " ms");

        showPartOfMatrix(answer);
    }

    private static void showPartOfMatrix(int[][] matrix) {

        System.out.println("\nFirst 5 x 5 values:");

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }
}