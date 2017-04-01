///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package sudoku;
//
///**
// *
// * @author kevin
// */
//public class SudokuGenerator {
//
//    private Sudoku completedSudoku;
//    private SudokuSolver solver;
//
//    public SudokuGenerator(Sudoku completed) {
//        this.completedSudoku = completed;
//        solver = new SudokuSolver();
//    }
//
//    public Sudoku getSudokuWithUniqueSolution() {
//        Sudoku uniqueSudoku = completedSudoku;
//
//        removeNumber(uniqueSudoku);
//        solver.setSudoku(uniqueSudoku);
//        int solutionsForCurrentSolution = solver.getSolutionCount();
//
//        while (solutionsForCurrentSolution == 1) {
//            removeNumber(uniqueSudoku);
//            solver.setSudoku(uniqueSudoku);
//            solutionsForCurrentSolution = solver.getSolutionCount();
//        }
//        
//        return uniqueSudoku;
//    }
//
//    private void removeNumber(Sudoku uniqueSudoku) {
//        int[] postionToReset = getFilledInPosition();
//        int row = postionToReset[0];
//        int col = postionToReset[1];
//
//        
//        uniqueSudoku.resetPosition(row, col);
//    }
//
//    private int[] getFilledInPosition() {
//        int[] filledIn = null;
//        int number;
//
//        for (int row = 0; row < 9 && filledIn == null; row++) {
//            for (int col = 0; col < 9 && filledIn == null; col++) {
//                number = completedSudoku.getNumberAt(row, col);
//                if (number != 0) {
//                    filledIn = new int[]{row, col};
//                }
//            }
//        }
//
//        return filledIn;
//    }
//
//}
