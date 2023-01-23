/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameoflife;

/**
 *
 * @author Josh Bonham
 */
public class Cell {
    boolean alive = false;
    public static void Cell()
    {} 
    //returns how many living neighbors of current cell
    int checkNeighbors(Cell[][] array,int row, int col)
    {
        int aliveNeighbors = 0;
        
        for(int i = -1; i<2; i++)
        {
            try{
                for(int j = -1; j<2;j++)
                {
                    if(array[col-j][row-i].alive == true && (i!=0 || j!=0))
                    {
                        aliveNeighbors++;
                    }  
                }
            }catch(ArrayIndexOutOfBoundsException e){}
        } 
        return aliveNeighbors;
    }
    
}
