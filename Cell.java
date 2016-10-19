/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author timothy
 */
import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;

public class Cell {
    private boolean alive;
    private int numNeighbours;
    private int row;
    private int col;
    Cell (String str, int row, int col){
        if (str.equals("*")){
            this.setAlive(true);
        }else if (str.equals(".")){
            this.setAlive(false);
        }else {
            System.out.println("ERROR Wrong string in input file");
        }
        this.row = row;
        this.col = col;
    }
    Cell (int row, int col){
        this.row = row;
        this.col = col;
    }
    
    public int getRow (){
        return this.row;
    }
    public int getCol (){
        return this.col;
    }
    void setAlive(boolean state) {
    if(state){
        alive = true;
    }else{
        alive = false;
    }
}
    boolean isAlive() {
        return alive;
    }
    
    void setNumNeighbours (int n){
        numNeighbours = n;
    }
    /**
     * 
     * @param cell indicate the grid being used
     * @param row row index of the current cell
     * @param col column index of current cell
     */
    public int checkNeighbour (Cell[][] cell){
        row = this.row;
        col = this.col;
        int j = col - 1;
        int colMax = col + 1;
        int rowMax = row + 1;
        int aliveneighbours = 0;
        int i = row - 1;
        if (i < 0){
            i = row;    
        }
        if (rowMax >= cell.length){
            rowMax = cell.length-1;            
        }
        
        while (i <= rowMax){
                j = col -1;
            if (j < 0){
                j = col;
            }
            if (colMax >= cell[0].length){
                colMax = cell[0].length-1;
            }
            while (j <= colMax){
                if (i==row && j==col){
                    j++;
                }else if (cell[i][j].isAlive()){
                    aliveneighbours +=1;                    
                    j++;
                }else{
                    j++;
                }
            }
            i ++;
        }
        return aliveneighbours;
        
    }
    
    public boolean update (Cell[][] cell){
        row = this.row;
        col = this.col;
       int aliveNeighbours = this.checkNeighbour(cell);
       // using the previous counter the following logic sets the current cell to true or false according to the specifications 
       
       if (aliveNeighbours < 2 && this.isAlive()){
           return false;
       }else if (aliveNeighbours > 3 && this.isAlive()){
           return false;
       }else if (2== aliveNeighbours && aliveNeighbours == 3 && this.isAlive()){
           return true;
       }else if (!this.isAlive() && aliveNeighbours == 3){
           return true;
       } else {
       return this.isAlive();
       }
    }
    
    public Color currentColor (){
        float[] rgb = new float[3];
        Color currentcolor;
        if (this.isAlive()){
            rgb[0] = 0;
            rgb[1] = 0;
            rgb[2] = 0;
            currentcolor = new Color(rgb[0],rgb[1],rgb[2]);
            return currentcolor;
        }else{
            rgb[0] = 1;
            rgb[1] = 1;
            rgb[2] = 1;
            currentcolor = new Color(rgb[0],rgb[1],rgb[2]);
            return currentcolor;
        }
    }
   public CellButton CellBTN (){
       CellButton pan = new CellButton(this.row, this.col);
       pan.setEnabled(true);
       pan.setBackground(this.currentColor());
       pan.setPreferredSize(new Dimension(3,3));
       pan.setBorder(BorderFactory.createLineBorder(Color.BLACK));
       pan.setName("");
       return pan;
   }
}
