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
public class SudokuPlaceCommand implements Undoable{
    
    private int row;
    private int col;
    private Sudoku s;
    
    public SudokuPlaceCommand(Sudoku s, int row, int col){
        this.row = row;
        this.col = col;
        this.s = s;
    }
    
    public void undo(){
        s.resetPositionWithoutAddingToStack(row, col);
    }
    
}
