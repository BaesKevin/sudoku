package sudoku;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author kevin
 */
public class SudokuSolver {

    private Sudoku sudoku;
    private int solutionCount = 0;

//    public SudokuSolver() {
//        this(new Sudoku());
//    }

    public SudokuSolver(Sudoku sudoku) {
        this.sudoku = sudoku;
    }

    public Sudoku getCompletedSudoku() {
        this.generateSolutionRecursively(false);
        System.out.println(sudoku);
        return this.sudoku;
    }

    public int getSolutionCount() {
        this.generateSolutionRecursively(true);

        return this.solutionCount;
    }
    
    public void setSudoku(Sudoku s){
        this.sudoku = s;
    }

    private boolean generateSolutionRecursively(boolean doCount) {

//        int[] emptyLocation = findEmptyLocation();
        int[] emptyLocation = findRandomEmptyLocation();
        
        if (emptyLocation == null) {
            return continueFindingSolutions(emptyLocation, doCount);
        }

        int row = emptyLocation[0];
        int col = emptyLocation[1];

        boolean canPlaceNumber = tryPlacingNumber(row, col, doCount);

        return canPlaceNumber;
    }

    private boolean tryPlacingNumber(int row, int col, boolean doCount) {
        ArrayList<Integer> possibleNumbers = sudoku.getSquareAt(row, col).calcAvailableNumbers(); 
        System.out.println(possibleNumbers);
        for (int num : possibleNumbers) {
            if (sudoku.place(row, col, num)) {

                if (generateSolutionRecursively(doCount)) {
                    return true;
                }
                System.out.println("Had to backtrack");
                sudoku.resetPosition(row, col); //no solution possible, unset the location
            }
        }

        return false;
    }

    private int[] findEmptyLocation() {
        int[] pos = null;
        int width = sudoku.getWidth();
        int height = sudoku.getHeight();
        int smallestAmount = width + 1;
        int amount;
        SudokuSquare sq = null;
        
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                sq = sudoku.getSquareAt(row, col);
                
                if (sq.getNumber() == 0) {
                    amount = sq.calcAvailableNumbers().size();
                    if( amount < smallestAmount){
                        smallestAmount = amount;
                        pos = new int[]{row, col};
                    }
                        
                }
            }
        }

        return pos;
    }
    
    private int[] findRandomEmptyLocation(){
        int[] pos = null;
        int width = sudoku.getWidth();
        int height = sudoku.getHeight();
        SudokuSquare sq;
        
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                sq = sudoku.getSquareAt(row, col);
                
                if (sq.getNumber() == 0) {
                    return new int[]{row, col};
                }
            }
        }

        return pos;
    }

    private boolean continueFindingSolutions(int[] location, boolean doCount) {
        if (doCount) {
            this.solutionCount++;
            return false;
        }
        return true;
    }

    private int[] getShuffledRow() {
        int[] row = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        Random rnd = ThreadLocalRandom.current();
        for (int i = row.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            int a = row[index];
            row[index] = row[i];
            row[i] = a;
        }

        return row;
    }

}
