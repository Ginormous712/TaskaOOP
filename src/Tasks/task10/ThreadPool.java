package Tasks.task2;

import java.util.LinkedList;
import java.util.Queue;

class WorkerThread extends Thread {
    private final ThreadPool pool;

    WorkerThread(ThreadPool pool) {
        this.pool = pool;
    }

    @Override
    public void run() {
        while (true) {
            Runnable task = pool.getTask();
            task.run();
        }
    }
}

public class ThreadPool {
    private final int size;
    private final Queue<Runnable> taskQueue;
    private final WorkerThread[] threads;

    public ThreadPool(int size) {
        this.size = size;
        this.taskQueue = new LinkedList<>();
        this.threads = new WorkerThread[size];

        for (int i = 0; i < size; i++) {
            threads[i] = new WorkerThread(this);
            threads[i].start();
        }
    }

    public synchronized void submit(Runnable task) {
        taskQueue.add(task);
        notify();
    }

    public synchronized Runnable getTask() {
        while (taskQueue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return taskQueue.poll();
    }
}

