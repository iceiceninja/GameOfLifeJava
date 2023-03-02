package gameoflife;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Josh Bonham
 */
public class GameOfLife extends JPanel{

    /**
     * @param args the command line arguments
     */
        
    static Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
    int generationCounter = 0;
    static Cell [][] gameBoard;
    private static final Random rnd = new Random();

    public static void main(String[] args) {
        // TODO code application logic here
        GameOfLife gol = new GameOfLife();
        JFrame frame = new JFrame();
        frame.getContentPane().add(gol);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(screenDim.width, screenDim.height);
        frame.setVisible(true);
        gameBoard = createEmptyGameboard();
        gol.setupGrid();
        
        new Timer(100, (ActionEvent e) -> {
            gol.nextGeneration();
            gol.repaint();
        }).start();
    }
    void nextGeneration()
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
        gameBoard = newGameboard;
    }
    static Cell[][] createEmptyGameboard()
    {
//        System.out.println(screenDim.width+""+screenDim.height);
        Cell[][] gb = new Cell[screenDim.height][screenDim.width];//Cell[500][500];//Cell[screenDim.width][screenDim.height];
        for (Cell[] row : gb) {
            for (int j = 0; j < row.length; j++) {
                row[j] = new Cell();
            }
        }
        return gb;
    }
    private void setupGrid() {
//        int counter=0;
        for (Cell[] row : gameBoard) {
            for (Cell cell : row) {
                if (rnd.nextDouble() < 0.08) {
                    cell.alive = true;
                }
            }
        }
    }
        @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Color gColor = g.getColor();
                
        g.drawString("Generation: " + generationCounter++, 0, 10);
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                if (gameBoard[i][j].alive) {
                    g.setColor(Color.CYAN);
                    g.fillRect(j * 4, i * 4, 4, 4);
                }
            }
        }

        g.setColor(gColor);
    }
}
