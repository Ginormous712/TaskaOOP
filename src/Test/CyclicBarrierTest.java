package Test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import Tasks.task7.CyclicBarrier;

class CyclicBarrierTest {
    private static final int NUM_THREADS = 3;

    @Test
    void testCyclicBarrier() throws InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(NUM_THREADS);

        TestRunnable runnable = new TestRunnable(cyclicBarrier);

        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        Thread thread3 = new Thread(runnable);

        thread1.start();
        thread2.start();
        thread3.start();

        // Waiting for all threads to complete
        thread1.join();
        thread2.join();
        thread3.join();

        assertEquals(NUM_THREADS, runnable.getCount()); // All threads must be called
    }

    private static class TestRunnable implements Runnable {
        private final CyclicBarrier cyclicBarrier;
        private int count = 0;

        TestRunnable(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + " started");
                Thread.sleep(100); // To ensure random start order of flows
                cyclicBarrier.await();
                count++;
                System.out.println(Thread.currentThread().getName() + " continues");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        int getCount() {
            return count;
        }
    }
}
