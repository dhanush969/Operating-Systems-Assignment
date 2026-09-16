import java.util.LinkedList;
import java.util.Queue;

public class SharedBuffer {

    private Queue<Integer> buffer = new LinkedList<>();
    private int capacity;

    public SharedBuffer(int capacity) {
        this.capacity = capacity;
    }

    // Called by producer thread
    public synchronized void produce(int item) throws InterruptedException {

        // Wait while buffer is full
        while (buffer.size() == capacity) {
            System.out.println("Buffer full. Producer waiting...");
            wait();
        }

        buffer.add(item);

        System.out.println("Produced: " + item +
                "  | Buffer size: " + buffer.size());

        // Wake up waiting consumer
        notifyAll();
    }

    // Called by consumer thread
    public synchronized int consume() throws InterruptedException {

        // Wait while buffer is empty
        while (buffer.isEmpty()) {
            System.out.println("Buffer empty. Consumer waiting...");
            wait();
        }

        int item = buffer.poll();

        System.out.println("Consumed: " + item +
                "  | Buffer size: " + buffer.size());

        // Wake up waiting producer
        notifyAll();

        return item;
    }
}