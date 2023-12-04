package Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import Tasks.task8.ReentrantLock;

public class ReentrantLockTest {

    @Test
    void testLockUnlock() {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        assertTrue(lock.isLocked());
        lock.unlock();
        assertFalse(lock.isLocked());
    }

    @Test
    void testLockUnlockMultipleTimes() {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        assertTrue(lock.isLocked());
        lock.lock();
        assertTrue(lock.isLocked());
        lock.unlock();
        assertTrue(lock.isLocked());
        lock.unlock();
        assertFalse(lock.isLocked());
    }

    @Test
    void testTryLock() {
        ReentrantLock lock = new ReentrantLock();
        assertTrue(lock.tryLock());
        assertTrue(lock.isLocked());
        assertFalse(lock.tryLock());
        lock.unlock();
        assertFalse(lock.isLocked());
    }

    @Test
    void testIsLocked() {
        ReentrantLock lock = new ReentrantLock();
        assertFalse(lock.isLocked());
        lock.lock();
        assertTrue(lock.isLocked());
        lock.unlock();
        assertFalse(lock.isLocked());
    }
}
