package Tasks.task2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TridiagonalSystemSolver {
    private final int size;
    private final double[] a;
    private final double[] b;
    private final double[] c;
    private final double[] d;
    private double[] x;

    public TridiagonalSystemSolver(int size, double[] a, double[] b, double[] c, double[] d) {
        this.size = size;
        this.a = a.clone();
        this.b = b.clone();
        this.c = c.clone();
        this.d = d.clone();
        this.x = new double[size];
    }

    public TridiagonalSystemSolver(double[][] matrix) {
        this.size = matrix.length;
        this.a = new double[size - 1];
        this.b = new double[size];
        this.c = new double[size - 1];
        this.d = new double[size];
        this.x = new double[size];

        for (int i = 0; i < size; i++) {
            b[i] = matrix[i][i];
            d[i] = matrix[i][size];
            if (i > 0) {
                a[i - 1] = matrix[i][i - 1];
            }
            if (i < size - 1) {
                c[i] = matrix[i][i + 1];
            }
        }
    }

    public void solve() {
        ExecutorService executor = Executors.newFixedThreadPool(size);

        // Forward elimination
        for (int i = 1; i < size; i++) {
            final int k = i;
            executor.submit(() -> {
                double m = a[k] / b[k - 1];
                b[k] -= m * c[k - 1];
                d[k] -= m * d[k - 1];
            });
        }

        // Back substitution
        x[size - 1] = d[size - 1] / b[size - 1];

        for (int i = size - 2; i >= 0; i--) {
            final int k = i;
            executor.submit(() -> x[k] = (d[k] - c[k] * x[k + 1]) / b[k]);
        }

        executor.shutdown();

        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public double[] getResult() {
        return x;
    }

    public int getSize() {
        return size;
    }
}
