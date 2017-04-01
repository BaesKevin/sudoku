/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import sudoku.Sudoku;
import sudoku.SudokuSolver;

/**
 *
 * @author kevin
 */
public class SudokuTester {

    private Sudoku s;

    @Before
    public void before() {
        this.s = new Sudoku();
    }

    @Test
    public void testInitialValues() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int value = s.getNumberAt(i, j);
                Assert.assertEquals(0, value);
            }
        }
    }

    @Test
    public void testPlacingNumberInBounds() {
        boolean canPlace1 = s.place(0, 0, 1);
        boolean canPlace2 = s.place(0, 8, 2);
        boolean canPlace3 = s.place(8, 0, 3);
        boolean canPlace4 = s.place(8, 8, 4);

        Assert.assertEquals(1, s.getNumberAt(0, 0));
        Assert.assertEquals(2, s.getNumberAt(0, 8));
        Assert.assertEquals(3, s.getNumberAt(8, 0));
        Assert.assertEquals(4, s.getNumberAt(8, 8));
    }

    @Test
    public void testPlacingNumberOutofBounds() {
        boolean cantPlace1 = s.place(-1, 0, 5);
        boolean cantPlace2 = s.place(9, 8, 5);
        boolean cantPlace3 = s.place(0, -1, 5);
        boolean cantPlace4 = s.place(8, 9, 5);

        Assert.assertFalse(cantPlace1);
        Assert.assertFalse(cantPlace2);
        Assert.assertFalse(cantPlace3);
        Assert.assertFalse(cantPlace4);
    }

    @Test
    public void testPlacingIllegalNumber() {
        boolean cantPlace1 = s.place(1, 1, 0);
        boolean cantPlace2 = s.place(9, 9, 10);

        Assert.assertFalse(cantPlace1);
        Assert.assertFalse(cantPlace2);
    }

    @Test
    public void testRowValidation() {

        for (int col = 0; col <= 8; col++) {
            s.place(1, col, col);
        }

        for (int col = 0; col <= 8; col++) {
            boolean canPlace = s.place(1, col, col);

            Assert.assertFalse(canPlace);
            Assert.assertEquals(col, s.getNumberAt(1, col));
        }
    }

    @Test
    public void testColValidation() {

        for (int row = 0; row <= 8; row++) {
            s.place(row, 1, row);
        }

        for (int row = 0; row <= 8; row++) {
            boolean canPlace = s.place(row, 1, row);

            Assert.assertFalse(canPlace);
            Assert.assertEquals(row, s.getNumberAt(row, 1));
        }
    }

    @Test
    public void testSquareValidation() {
        int number = 1;
        for (int row = 0; row <= 2; row++) {
            for (int col = 0; col <= 2; col++) {
                s.place(row, col, number++);
            }
        }

        boolean canPlace1 = s.place(0, 0, 4);
        boolean canPlace2 = s.place(2, 2, 5);

        Assert.assertFalse(canPlace1);
        Assert.assertFalse(canPlace2);
    }

    @Test
    public void testEquality() {
        int[][] completed = {
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

        Sudoku first = new Sudoku(completed);
        Sudoku second = new Sudoku(completed);

        Assert.assertTrue(first.equals(second));

        first.resetPosition(4, 4);

        Assert.assertFalse(first.equals(second));

    }

    @Test
    public void TestUndoResets() {

        int[][] completed = {
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

        Sudoku s = new Sudoku(completed);

        int expected3 = s.getNumberAt(5, 5);
        int expected2 = s.getNumberAt(5, 6);
        int expected1 = s.getNumberAt(5, 7);

        s.resetPosition(5, 5);
        s.resetPosition(5, 6);
        s.resetPosition(5, 7);
        s.undo();
        Assert.assertEquals(expected1, s.getNumberAt(5, 7));
        s.undo();
        Assert.assertEquals(expected2, s.getNumberAt(5, 6));
        s.undo();
        Assert.assertEquals(expected3, s.getNumberAt(5, 5));

    }

    @Test
    public void TestUndoPlacements() {

        int[][] unique = {
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

        Sudoku s = new Sudoku(unique);

        int expected1 = 4;
        int expected2 = 6;
        int expected3 = 8;
        
        s.place(0, 0, expected1);
        s.place(0, 1, expected2);
        s.place(0, 2, expected3);
        
        s.undo();
        s.undo();
        s.undo();

        Assert.assertEquals(0, s.getNumberAt(0, 0));
        Assert.assertEquals(0, s.getNumberAt(0, 1));
        Assert.assertEquals(0, s.getNumberAt(0, 2));
    }
}
