package Test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import Tasks.task9.Phaser;

public class PhaserTest {

    @Test
    void testCustomPhaser() throws InterruptedException {
        Phaser phaser = new Phaser(3);

        Thread thread1 = new Thread(() -> {
            phaser.arriveAndAwaitAdvance();
        });

        Thread thread2 = new Thread(() -> {
            phaser.arriveAndAwaitAdvance();
        });

        Thread thread3 = new Thread(() -> {
            phaser.arriveAndAwaitAdvance();
        });

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

        assertEquals(1, phaser.getPhase());
        assertEquals(3, phaser.getParties());
    }
}
