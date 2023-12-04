package Tasks.task7;

public class CyclicBarrier {
    private final int parties;
    private int count = 0;

    public CyclicBarrier(int parties) {
        this.parties = parties;
    }

    public synchronized void await() throws InterruptedException {
        count++;

        if (count < parties) {
            // The current thread waits until the desired number of threads is reached
            wait();
        } else {
            // The required number of threads is reached, reset the counter and wake up all threads
            count = 0;
            notifyAll();
        }
    }
}

