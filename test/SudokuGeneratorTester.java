
import org.junit.Assert;
import org.junit.Test;
import sudoku.*;

/**
 *
 * @author kevin
 */
public class SudokuGeneratorTester {

    private int[][] unique = {
        {0, 0, 0, 7, 0, 3, 0, 0, 5},
        {0, 0, 1, 0, 0, 0, 0, 0, 8},
        {0, 0, 2, 0, 4, 0, 3, 7, 0},
        {0, 9, 0, 3, 1, 0, 0, 0, 0},
        {0, 0, 0, 8, 6, 0, 4, 5, 0},
        {2, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 1, 0, 0, 5, 0, 0, 2, 0},
        {0, 0, 5, 0, 7, 0, 0, 0, 0},
        {0, 0, 9, 0, 0, 6, 0, 0, 0}
    };

    private int[][] completed = {
        {2, 6, 1, 8, 9, 3, 7, 4, 5},
        {7, 9, 4, 2, 1, 5, 6, 8, 3},
        {8, 5, 3, 6, 7, 4, 1, 9, 2},
        {5, 4, 8, 3, 6, 2, 9, 7, 1},
        {1, 3, 9, 4, 5, 7, 2, 6, 8},
        {6, 2, 7, 1, 8, 9, 3, 5, 4},
        {3, 8, 6, 9, 4, 1, 5, 2, 7},
        {9, 1, 5, 7, 2, 8, 4, 3, 6},
        {4, 7, 2, 5, 3, 6, 8, 1, 9}
    };

    @Test
    public void testGeneratingSudokuWithUniqueSolution() {
        Sudoku uniqueSudoku = new Sudoku(unique);
        SudokuGenerator generator = new SudokuGenerator(uniqueSudoku);

        Sudoku generated = generator.getSudokuWithUniqueSolution();

        SudokuSolver ss = new SudokuSolver(generated);

        Assert.assertEquals(1, ss.getSolutionCount());
    }
}
