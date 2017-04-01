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
public class SudokuSquareList {
    private ArrayList<SudokuSquare> squares;
    
    public SudokuSquareList(){
        squares = new ArrayList();
    }
    
    public SudokuSquare get(int index){
        return squares.get(index);
    }
    
    public void add(SudokuSquare sq){
        this.squares.add(sq);
    }
    
    public boolean numberCanBePlaced(int number) {
        boolean unique = true;
        int size = squares.size();
        
        for (int i = 0; i < size && unique; i++) {
            if (squares.get(i).getNumber() == number) {
                unique = false;
            }
        }

        return unique;
    }
    
    public int size(){
        return squares.size();
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        
        for(SudokuSquare sq : squares){
            sb.append(sq.getNumber()).append(" ");
        }
        
        return sb.toString();
    }
}
