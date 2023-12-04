package Test;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

import Tasks.task3.ThreadHierarchyPrinter;

class ThreadHierarchyPrinterTest {

    @Test
    void testPrintThreadHierarchy() {
        ThreadGroup group = new ThreadGroup("TestThreadGroup");

        Thread thread1 = new Thread(group, () -> {
            while (!Thread.interrupted()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        thread1.start();

        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        ThreadHierarchyPrinter.printThreadHierarchy(group);

        String consoleOutput = outputStreamCaptor.toString().trim();

        thread1.interrupt();

        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertEquals("Thread Group: TestThreadGroup\n" +
                "Parent Group: None\n" +
                "Threads:\n" +
                "  " + thread1.getName() + "\n" +
                "Subgroups:\n" +
                "Thread Group: main\n" +
                "Parent Group: system\n" +
                "Threads:\n" +
                "  " + Thread.currentThread().getName(), consoleOutput);
    }
}
