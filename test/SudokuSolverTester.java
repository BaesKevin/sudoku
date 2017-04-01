/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.Assert;
import org.junit.Test;
import sudoku.Sudoku;
import sudoku.SudokuSolver;

/**
 *
 * @author kevin
 */
public class SudokuSolverTester {

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

    //took out a number in the first line, now 577 solutions are possible??
    private int[][] multiple = {
        {0, 0, 0, 7, 0, 0, 0, 0, 5},
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
        {9, 4, 6, 7, 8, 3, 2, 1, 5},
        {3, 7, 1, 6, 2, 5, 9, 4, 8},
        {8, 5, 2, 1, 4, 9, 3, 7, 6},
        {5, 9, 4, 3, 1, 7, 8, 6, 2},
        {1, 3, 7, 8, 6, 2, 4, 5, 9},
        {2, 6, 8, 5, 9, 4, 1, 3, 7},
        {6, 1, 3, 9, 5, 8, 7, 2, 4},
        {4, 8, 5, 2, 7, 1, 6, 9, 3},
        {7, 2, 9, 4, 3, 6, 5, 8, 1}
    };

    @Test
    public void testSolver() {

        for (int i = 0; i < 100; i++) {
            Sudoku toSolve = new Sudoku(unique);
            Sudoku actualSolution = new Sudoku(completed);

            SudokuSolver generator = new SudokuSolver(toSolve);

            Sudoku foundSolution = generator.getCompletedSudoku();

            Assert.assertTrue(actualSolution.equals(foundSolution));
        }

    }

    @Test
    public void testCountSolutions() {
        Sudoku uniqueSudoku = new Sudoku(unique);
        Sudoku multipleSoltionsSudoku = new Sudoku(multiple);

        SudokuSolver uniqueGenerator = new SudokuSolver(uniqueSudoku);
        SudokuSolver mulitpleGenerator = new SudokuSolver(multipleSoltionsSudoku);

        int uniqueSolutions = uniqueGenerator.getSolutionCount();
        int multipleSolutions = mulitpleGenerator.getSolutionCount();

        Assert.assertTrue(1 == uniqueSolutions);
        Assert.assertTrue(1 < multipleSolutions);
    }

}
