/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflifegui;

/**
 *
 * @author timothy
 */
public class Cell {
    private boolean alive;
    private int numNeighbours;
    Cell (String str){
        if (str.equals("*")){
            this.setAlive(true);
        }else if (str.equals(".")){
            this.setAlive(false);
        }else {
            System.out.println("ERROR Wrong string in input file");
        }
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
    public int checkNeighbour (Cell[][] cell, int row, int col){
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
//                System.out.println("boolean"+cell[i][j].isAlive());
                if (i==row && j==col){
                    System.out.println("i+j "+i+" "+j);
                    j++;
                }else if (cell[i][j].isAlive()){
                    aliveneighbours +=1;                    
                    System.out.println("i+j2 "+i+" "+j);
                    j++;
                }else{
                    System.out.println("i+j3 "+i+" "+j);
                    j++;
                }
            }
            System.out.println("------------");
            i++; 
            System.out.println("i "+i);
            System.out.println("------------");
        }
        return aliveneighbours;
        
    }
    
    public boolean update (Cell[][] cell,int row, int col){
        
       int aliveNeighbours = this.checkNeighbour(cell, row, col);        
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
}
