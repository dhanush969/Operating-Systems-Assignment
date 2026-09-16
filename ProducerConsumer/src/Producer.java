public class Producer implements Runnable {

    private SharedBuffer sharedBuffer;

    public Producer(SharedBuffer sharedBuffer) {
        this.sharedBuffer = sharedBuffer;
    }

    @Override
    public void run() {

        int item = 0;

        try {

            for (int i = 0; i < 10; i++) {

                sharedBuffer.produce(item);

                item++;

                // Simulate time taken to produce
                Thread.sleep(300);
            }

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();

            System.out.println("Producer interrupted");
        }
    }
}