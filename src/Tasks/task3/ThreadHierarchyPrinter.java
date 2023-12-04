package Tasks.task3;

public class ThreadHierarchyPrinter {
    public static void printThreadHierarchy(ThreadGroup group) {
        System.out.println("Thread Group: " + group.getName());
        System.out.println("Parent Group: " + (group.getParent() != null ? group.getParent().getName() : "None"));

        Thread[] threads = new Thread[group.activeCount()];
        int numThreads = group.enumerate(threads, false);

        System.out.println("Threads:");
        for (int i = 0; i < numThreads; i++) {
            System.out.println("  " + threads[i].getName());
        }

        System.out.println();

        ThreadGroup[] subgroups = new ThreadGroup[group.activeGroupCount()];
        int numGroups = group.enumerate(subgroups, false);

        System.out.println("Subgroups:");
        for (int i = 0; i < numGroups; i++) {
            printThreadHierarchy(subgroups[i]);
        }
    }
}

