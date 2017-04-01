/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

/**
 *
 * @author kevin
 */
public class SudokuResetCommand implements Undoable{
    private int row;
    private int col;
    private int number;
    private Sudoku s;
    
    public SudokuResetCommand(Sudoku s, int row, int col, int number){
        this.row = row;
        this.col = col;
        this.number = number;
        this.s = s;
    }
    
    public void undo(){
        s.placeWithoutAddingToStack(row, col, number);
    }
}
