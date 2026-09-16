package matrixmultiplication;

public class RowMultiplier implements Runnable {

    private final int[][] first;
    private final int[][] second;
    private final int[][] output;
    private final int rowNumber;

    public RowMultiplier(int[][] first, int[][] second,
                   int[][] output, int rowNumber) {

        this.first = first;
        this.second = second;
        this.output = output;
        this.rowNumber = rowNumber;
    }

    @Override
    public void run() {

        for (int column = 0; column < second[0].length; column++) {

            int total = 0;

            for (int index = 0; index < first[0].length; index++) {
                total += first[rowNumber][index] * second[index][column];
            }

            output[rowNumber][column] = total;
        }
    }
}