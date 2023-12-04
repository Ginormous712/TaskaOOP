package Tasks.task9;

public class Phaser {
    private int parties;
    private int phase;

    public Phaser(int parties) {
        this.parties = parties;
        this.phase = 0;
    }

    public synchronized void arriveAndAwaitAdvance() {
        int currentPhase = phase;
        parties--;

        if (parties == 0) {
            phase++;
            parties = 3;
            notifyAll();
        } else {
            while (currentPhase == phase) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public synchronized int getPhase() {
        return phase;
    }

    public synchronized int getParties() {
        return parties;
    }
}
