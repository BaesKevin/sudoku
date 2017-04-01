/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

import java.util.Arrays;

/**
 *
 * @author kevin
 */
public class GeneratorRunerr {

    public static void main(String[] args) {
        int[][] completed = {
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
        
        Sudoku s = new Sudoku(completed, 3,3);
//        System.out.println("Unsolved:");
//        System.out.println(s.toString());
//        System.out.println("\nSolved:");
//        SudokuSolver ss = new SudokuSolver(s);
//        Sudoku solution = ss.getCompletedSudoku();
//        System.out.println(solution.toString());

        System.out.println(s);
    }
}
