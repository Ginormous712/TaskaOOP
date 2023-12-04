package Test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import Tasks.task2.TridiagonalSystemSolver;

import java.util.Arrays;

class TridiagonalSystemSolverTest {

    @Test
    void solve() {
        // Arrange
        double[][] matrix = {
                {2, -1, 0, 1},
                {-1, 2, -1, 0},
                {0, -1, 2, -1}
        };

        double[] expectedSolution = {0.5, 0.0, -0.5}; // Expected solution

        TridiagonalSystemSolver solver = new TridiagonalSystemSolver(matrix);

        // Act
        solver.solve();
        double[] actualSolution = solver.getResult();
        //System.out.println(Arrays.toString(actualSolution));
        // Assert
        assertArrayEquals(expectedSolution, actualSolution, 1e-8);
    }
}
