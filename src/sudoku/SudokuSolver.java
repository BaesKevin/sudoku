package sudoku;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author kevin
 */
public class SudokuSolver {

    private Sudoku sudoku;
    private int solutionCount = 0;

    public SudokuSolver() {
        this(new Sudoku());
    }

    public SudokuSolver(Sudoku sudoku) {
        this.sudoku = sudoku;
    }

    public Sudoku getCompletedSudoku() {
        this.generateSolutionRecursively(false);

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

        int[] emptyLocation = findEmptyLocation();
        
        if (emptyLocation == null) {
            return continueFindingSolutions(emptyLocation, doCount);
        }

        int row = emptyLocation[0];
        int col = emptyLocation[1];

        boolean canPlaceNumber = tryPlacingNumber(row, col, doCount);

        return canPlaceNumber;
    }

    private boolean tryPlacingNumber(int row, int col, boolean doCount) {
        int[] possibleNumbers = getShuffledRow();

        for (int num : possibleNumbers) {
            if (sudoku.place(row, col, num)) {

                if (generateSolutionRecursively(doCount)) {
                    return true;
                }

                sudoku.resetPosition(row, col); //no solution possible, unset the location
            }
        }

        return false;
    }

    private int[] findEmptyLocation() {
        int[] emptyLocation = null;

        for (int row = 0; row < 9 && emptyLocation == null; row++) {
            for (int col = 0; col < 9 && emptyLocation == null; col++) {
                if (sudoku.getNumberAt(row, col) == 0) {
                    emptyLocation = new int[]{row, col};
                }
            }
        }

        return emptyLocation;
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
