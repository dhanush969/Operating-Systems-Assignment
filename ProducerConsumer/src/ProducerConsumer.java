public class ProducerConsumer {

    public static void main(String[] args) {

        // Buffer capacity = 5
        SharedBuffer sharedBuffer = new SharedBuffer(5);

        // Create producer thread
        Thread producerThread =
                new Thread(new Producer(sharedBuffer), "Producer-Thread");

        // Create consumer thread
        Thread consumerThread =
                new Thread(new Consumer(sharedBuffer), "Consumer-Thread");

        // Start both threads
        producerThread.start();
        consumerThread.start();

        try {

            // Wait for producer to finish
            producerThread.join();

            // Wait for consumer to finish
            consumerThread.join();

        } catch (InterruptedException e) {

            e.printStackTrace();
        }

        System.out.println("Both threads have finished execution.");
    }
}