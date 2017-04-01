/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

import java.util.Arrays;
import java.util.Stack;

/**
 *
 * @author kevin
 */
public class Sudoku {

    private int[][] sudoku;
    private Stack<Undoable> previousMoves;
    
    public Sudoku() {
        this(new int[9][9]);
    }

    public Sudoku(int[][] sudoku) {
        this.sudoku = cloneTwoDimArray(sudoku);
        previousMoves = new Stack<Undoable>();
    }

    //place a number on the board, row and col must be between 1 and 9 (included)
    public boolean place(int row, int col, int number) {
        boolean legalCoordinate = isLegalCoordinate(row, col);
        boolean legalNumber = isLegalNumber(number);

        if (!legalCoordinate || !legalNumber || !isUniqueInRowColAndSquare(row, col, number)) {
            return false;
        }
        
        sudoku[row][col] = number;
        SudokuPlaceCommand command = new SudokuPlaceCommand(this, row, col);
        this.previousMoves.add(command);

        return true;
    }
    
    public boolean placeWithoutAddingToStack(int row, int col, int number) {
        boolean legalCoordinate = isLegalCoordinate(row, col);
        boolean legalNumber = isLegalNumber(number);

        if (!legalCoordinate || !legalNumber || !isUniqueInRowColAndSquare(row, col, number)) {
            return false;
        }
        
        sudoku[row][col] = number;

        return true;
    }

    public int getNumberAt(int row, int col) {
        if (!isLegalCoordinate(row, col)) {
            return 0;
        }

        return sudoku[row][col];
    }

    public void resetPosition(int row, int col) {
        int numberBeingReset = this.getNumberAt(row, col);
        SudokuResetCommand resetCommand = new SudokuResetCommand(this, row, col, numberBeingReset);
        this.previousMoves.add(resetCommand);
        
        sudoku[row][col] = 0;
    }
    
    public void resetPositionWithoutAddingToStack(int row, int col) {
        sudoku[row][col] = 0;
    }

    public boolean equals(Sudoku toCompare) {
        boolean equal = true;

        for (int row = 1; row <= 9 && equal; row++) {
            for (int col = 1; col <= 9 && equal; col++) {
                int first = this.getNumberAt(row, col);
                int second = toCompare.getNumberAt(row, col);
                if(first != second){
                    equal = false;
                }
            }
        }

        return equal;
    }

    @Override
    public String toString() {
        String output = "";

        for (int[] row : sudoku) {
            output += Arrays.toString(row);
            output += "\n";
        }

        return output;
    }

    private boolean isLegalCoordinate(int row, int col) {
        boolean isLegalRow = 0 <= row && row <= 8;
        boolean isLegalCol = 0 <= col && col <= 8;

        return isLegalCol && isLegalRow;
    }

    private boolean isLegalNumber(int number) {
        return 1 <= number && number <= 9;
    }

    private boolean isUniqueInRowColAndSquare(int row, int col, int number) {
        boolean uniqueInRow = isUniqueInRow(row, number);
        boolean uniqueInCol = isUniqueInCol(col, number);
        boolean uniqueInSquare = isUniqueInSquare(row, col, number);

        return uniqueInRow && uniqueInCol && uniqueInSquare;
    }

    private boolean isUniqueInRow(int row, int number) {
        boolean unique = true;

        for (int col = 0; col <= 8 && unique; col++) {
            if (sudoku[row][col] == number) {
                unique = false;
            }
        }

        return unique;
    }

    private boolean isUniqueInCol(int col, int number) {
        boolean unique = true;

        for (int row = 0; row <= 8 && unique; row++) {
            if (sudoku[row][col] == number) {
                unique = false;
            }
        }

        return unique;
    }

    private boolean isUniqueInSquare(int row, int col, int number) {
        int rowOfTopLeftSquare = row / 3 * 3;
        int colofTopLeftSquare = col / 3 * 3;
        boolean unique = true;

        for (int rowInSquare = rowOfTopLeftSquare; rowInSquare < rowOfTopLeftSquare + 3 && unique; rowInSquare++) {
            for (int colInSquare = colofTopLeftSquare; colInSquare < colofTopLeftSquare + 3 && unique; colInSquare++) {
                if (sudoku[rowInSquare][colInSquare] == number) {
                    unique = false;
                }
            }
        }

        return unique;
    }

    private int[][] cloneTwoDimArray(int[][] original) {
        int length = original.length;
        int[][] clone = new int[length][];

        for (int i = 0; i < length; i++) {
            clone[i] = cloneArray(original[i]);
        }

        return clone;
    }

    private int[] cloneArray(int[] original) {
        int length = original.length;
        int[] clone = new int[length];

        for (int i = 0; i < length; i++) {
            clone[i] = original[i];
        }

        return clone;
    }

    public void undo() {
        Undoable moveToUndo = this.previousMoves.pop();
        
        moveToUndo.undo();
    }
}
