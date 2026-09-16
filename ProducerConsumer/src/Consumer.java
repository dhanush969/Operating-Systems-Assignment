public class Consumer implements Runnable {

    private SharedBuffer sharedBuffer;

    public Consumer(SharedBuffer sharedBuffer) {
        this.sharedBuffer = sharedBuffer;
    }

    @Override
    public void run() {

        try {

            for (int i = 0; i < 10; i++) {

                sharedBuffer.consume();

                // Simulate time taken to consume
                Thread.sleep(500);
            }

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();

            System.out.println("Consumer interrupted");
        }
    }
}