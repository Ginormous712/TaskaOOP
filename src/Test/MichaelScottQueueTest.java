package Test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import Tasks.task6.MichaelScottQueue;

class MichaelScottQueueTest {
    @Test
    void testEnqueueDequeue() {
        MichaelScottQueue<Integer> queue = new MichaelScottQueue<>();

        assertNull(queue.dequeue()); // Empty queue, dequeue should return null

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        assertEquals(1, queue.dequeue()); // Dequeue first element
        assertEquals(2, queue.dequeue()); // Dequeue second element
        assertEquals(3, queue.dequeue()); // Dequeue third element

        assertNull(queue.dequeue()); // Queue is empty again
    }

    @Test
    void testConcurrentEnqueueDequeue() throws InterruptedException {
        MichaelScottQueue<Integer> queue = new MichaelScottQueue<>();
        int numThreads = 10;
        int numItemsPerThread = 100;

        Thread[] threads = new Thread[numThreads];

        for (int i = 0; i < numThreads; i++) {
            final int threadNum = i;
            threads[i] = new Thread(() -> {
                for (int j = 0; j < numItemsPerThread; j++) {
                    queue.enqueue(threadNum * numItemsPerThread + j);
                }
            });
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        // Ensure all items are enqueued

        for (int i = 0; i < numThreads * numItemsPerThread; i++) {
            assertNotNull(queue.dequeue());
        }

        assertNull(queue.dequeue()); // Queue is empty again
    }
}
