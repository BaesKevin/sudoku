/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

/**
 *
 * @author kevin
 */
public class Sudoku {
    private int width, height, sectionWidth, sectionHeight;
    
    private ArrayList<SudokuSquareList> rows;
    private ArrayList<SudokuSquareList> cols;
    private ArrayList<SudokuSquareList> sections;
    
//    private Stack<Undoable> previousMoves;
    
//    public Sudoku() {
//        this(new int[9][9]);
//    }

    public Sudoku(int[][] sudoku, int sectionWidth, int sectionHeight) {
        
        int[][] sudokuInput = cloneTwoDimArray(sudoku);

        this.width = sudokuInput[0].length;
        this.height =  sudokuInput.length;
        this.sectionWidth = sectionWidth;
        this.sectionHeight = sectionHeight;
        
        int numSections = width / sectionWidth * height / sectionHeight;
        initSquareLists(height, width, numSections);
        
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                addNewSquare(row, col, sudokuInput[row][col]);
            }
        }
        
        initPossibleNumbers();
        
//        previousMoves = new Stack<Undoable>();
    }
    
    private void initSquareLists(int numRows, int numCols, int numSections){
        this.cols = new ArrayList();
        for (int i = 0; i < numCols; i++) {
            cols.add(new SudokuSquareList());
        }
        this.rows = new ArrayList();
        for (int i = 0; i < numRows; i++) {
            rows.add(new SudokuSquareList());
        }
        this.sections = new ArrayList();
        for (int i = 0; i < numSections; i++) {
            sections.add(new SudokuSquareList());
        }
    }
    
    private void addNewSquare(int rownum, int colnum, int number){
        SudokuSquareList row = rows.get(rownum);
        SudokuSquareList col = cols.get(colnum);
        
        int sectionNumber = getSectionNumber(rownum, colnum);
        SudokuSquareList section = sections.get(sectionNumber);
        
        SudokuSquare sq = new SudokuSquare(number, row, col, section);
        row.add(sq);
        col.add(sq);
        section.add(sq);
    }
    
    private void initPossibleNumbers(){
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                getSquareAt(row, col).setAvailableNumbers();
            }
        }
    }

    private int getSectionNumber(int row, int col){
        int numSectionsInWidth = width / sectionWidth;
        int sectionNum = numSectionsInWidth * (row / sectionHeight) + col / sectionWidth;
        /*
        0011
        0011
        2233
        2233
        
        */
        return sectionNum;
    }
    
    //place a number on the board, row and col must be between 1 and 9 (included)
    public boolean place(int row, int col, int number) {
        boolean legalCoordinate = isLegalCoordinate(row, col);
        boolean legalNumber = isLegalNumber(number);

        if (!legalCoordinate || !legalNumber/* || !isUniqueInRowColAndSquare(row, col, number)*/) {
            return false;
        }
        
        setNumberAt(row, col, number);
        SudokuPlaceCommand command = new SudokuPlaceCommand(this, row, col);
//        this.previousMoves.add(command);

        return true;
    }
    
    public boolean placeWithoutAddingToStack(int row, int col, int number) {
        boolean legalCoordinate = isLegalCoordinate(row, col);
        boolean legalNumber = isLegalNumber(number);

        if (!legalCoordinate || !legalNumber || !isUniqueInRowColAndSquare(row, col, number)) {
            return false;
        }
        
        setNumberAt(row, col, number);

        return true;
    }

    public SudokuSquare getSquareAt(int row, int col){
        return rows.get(row).get(col);
    }
    
    public int getNumberAt(int row, int col) {
        if (!isLegalCoordinate(row, col)) {
            return 0;
        }

        return getSquareAt(row,col).getNumber();
    }
    
    private void setNumberAt(int row, int col, int number){
        getSquareAt(row, col).setNumber(number);
    }

    public void resetPosition(int row, int col) {
        int numberBeingReset = this.getNumberAt(row, col);
        SudokuResetCommand resetCommand = new SudokuResetCommand(this, row, col, numberBeingReset);
//        this.previousMoves.add(resetCommand);
        
        setNumberAt(row, col, 0);
    }
    
    public void resetPositionWithoutAddingToStack(int row, int col) {
        setNumberAt(row, col, 0);
    }

    

    private boolean isLegalCoordinate(int row, int col) {
        boolean isLegalRow = 0 <= row && row <= height - 1;
        boolean isLegalCol = 0 <= col && col <= width - 1;

        return isLegalCol && isLegalRow;
    }

    private boolean isLegalNumber(int number) {
        return 1 <= number && number <= width;
    }

    private boolean isUniqueInRowColAndSquare(int row, int col, int number) {
        boolean uniqueInRow = rows.get(row).numberCanBePlaced(number);
        boolean uniqueInCol = cols.get(col).numberCanBePlaced(number);
        boolean uniqueInSquare = sections.get(getSectionNumber(row, col)).numberCanBePlaced(number);

        return uniqueInRow && uniqueInCol && uniqueInSquare;
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
//        Undoable moveToUndo = this.previousMoves.pop();
        
//        moveToUndo.undo();
    }
    
    public int getWidth(){
        return width;
    }
    
    public int getHeight(){
        return height;
    }
    
    public boolean equals(Sudoku toCompare) {
        boolean equal = true;

        for (int row = 1; row <= height; row++) {
            for (int col = 1; col <= width; col++) {
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
        output += "cols\n";
        for (SudokuSquareList row : cols) {
            output += row;
            output += "\n";
        }
        output+="\n";
        output += "rows\n";
        for (SudokuSquareList row : rows) {
            output += row;
            output += "\n";
        }
        output+="\nSections\n";
        for (SudokuSquareList row : sections) {
            output += row;
            output += "\n";
        }

        return output;
    }
}
