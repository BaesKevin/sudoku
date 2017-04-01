/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

/**
 *
 * @author Kevin
 */
public class SudokuSquare {
    private int number;
    
    public SudokuSquare(int number){
        setNumber();
    }
    
    public int getNumber(){
        return number;
    }
    
    public void setNumber(){
        this.number = number;
    }
}
