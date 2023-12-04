package Tasks.task8;

public class ReentrantLock {
    private boolean isLocked = false;
    private Thread lockedBy = null;
    private int lockCount = 0;

    public synchronized void lock() {
        Thread currentThread = Thread.currentThread();
        while (isLocked && lockedBy != currentThread) {
            try {
                wait();
            } catch (InterruptedException e) {
                // Handle interruption
            }
        }
        isLocked = true;
        lockedBy = currentThread;
        lockCount++;
    }

    public synchronized void unlock() {
        if (Thread.currentThread() == lockedBy) {
            lockCount--;

            if (lockCount == 0) {
                isLocked = false;
                lockedBy = null;
                notify();
            }
        }
    }

    public synchronized boolean tryLock() {
        if (!isLocked) {
            lock();
            return true;
        }
        return false;
    }

    public synchronized boolean isLocked() {
        return isLocked;
    }
}


