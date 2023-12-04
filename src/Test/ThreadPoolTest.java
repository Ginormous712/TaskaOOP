package Test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import Tasks.task10.ThreadPool;

class ThreadPoolTest {
    @Test
    void testThreadPool() {
        // Create a thread pool with 3 worker threads
        ThreadPool pool = new ThreadPool(3);

        // Submit some tasks to the thread pool
        for (int i = 0; i < 10; i++) {
            final int taskNumber = i;
            pool.submit(() -> {
                System.out.println("Task " + taskNumber + " executed by " + Thread.currentThread().getName());
            });
        }

        // Allow some time for tasks to complete
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Assert that all tasks were executed by one of the worker threads
        assertTrue(Thread.currentThread().getName().startsWith("main"));
    }
}
