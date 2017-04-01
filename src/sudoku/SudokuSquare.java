/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

import java.util.ArrayList;

/**
 *
 * @author Kevin
 */
public class SudokuSquare {
    private int number;
    private SudokuSquareList row, col, section;
    private ArrayList<Integer> availableNumbers;
    
    public SudokuSquare(int number, SudokuSquareList row, SudokuSquareList col, SudokuSquareList section){
        setNumber(number);
        this.row = row;
        this.col = col;
        this.section = section;
    }
    
    public int getNumber(){
        return number;
    }
    
    public void setNumber(int number){
        this.number = number;
    }
    
    public void setAvailableNumbers(){
        this.availableNumbers = calcAvailableNumbers();
    }
    
    public ArrayList<Integer> getAvailableNumbers(){
        return availableNumbers;
    }
    
    public ArrayList<Integer> calcAvailableNumbers(){
        int maxNumber = row.size();
        ArrayList<Integer> numbers = new ArrayList();
        
        for(int i = 1; i <= maxNumber; i++){
            if(row.numberCanBePlaced(i) && col.numberCanBePlaced(i) && section.numberCanBePlaced(i)){
                numbers.add(i);
            }
        }
        
        return numbers;
    }
}
