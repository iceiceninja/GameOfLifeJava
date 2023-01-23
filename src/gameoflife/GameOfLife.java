/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package gameoflife;

import java.util.Arrays;

/**
 *
 * @author Josh Bonham
 */
public class GameOfLife {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Cell [][] gameBoard = createEmptyGameboard();
        gameBoard[5][6].alive = true;
        gameBoard[6][6].alive = true;
        gameBoard[6][5].alive = true;
        gameBoard[7][5].alive = true;
        printBoard(gameBoard);
        gameBoard = nextGeneration(gameBoard);
        printBoard(gameBoard);
        gameBoard = nextGeneration(gameBoard);
        printBoard(gameBoard);
        gameBoard = nextGeneration(gameBoard);
        printBoard(gameBoard);
    }
    static Cell[][] nextGeneration(Cell[][] gameBoard)
    {
        Cell [][] oldGameboard = gameBoard;
        Cell[][] newGameboard = createEmptyGameboard();
        
        /*
        Rules are as below:
        Each cell with one or no neighbors dies
        Each cell with four or more neighbors dies
        Each cell with two or three neighbors survives
        
        For a space that is empty or unpopulated:
            Each cell with exactly three neighbors becomes populated.
        */
        
        for(int i =0; i < gameBoard.length;i++)
        {
            for(int j=0; j<gameBoard.length;j++)
            {
                if(oldGameboard[j][i].alive == true){
                    switch(oldGameboard[j][i].checkNeighbors(oldGameboard, i, j))
                    {
                        case 2:
                            newGameboard[j][i].alive = true;
                            break;
                        case 3:
                            newGameboard[j][i].alive = true;
                            break;
                        default:
                            newGameboard[j][i].alive = false;
                            break;
                    }
                }
                else if(oldGameboard[j][i].checkNeighbors(oldGameboard, i, j) == 3)
                {
                    newGameboard[j][i].alive = true;
                }
            }
        }
        return newGameboard;
    }
    static Cell[][] createEmptyGameboard()
    {
        Cell[][] gameBoard = new Cell[10][10];
            for(int i =0; i < gameBoard.length;i++)
            {
                for(int j=0; j<gameBoard.length;j++)
                {
                    gameBoard[j][i] = new Cell();
                }
            }
            return gameBoard;
    }
    static void printBoard(Cell[][] gameBoard)
    {
        for(int i = 0; i<gameBoard.length;i++)
        {
            for(int j = 0; j<gameBoard.length;j++)
            {
                if(gameBoard[j][i].alive == true)
                {
                    System.out.print("O");
                }else
                {
                    System.out.print("X");
                }
            }
            System.out.println();
        }
        System.out.println("==================");
    }
}
