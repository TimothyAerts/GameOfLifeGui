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
        switch (str) {
            case "*":
                this.setAlive(true);
                break;
            case ".":
                this.setAlive(false);
                break;
            default:
                System.out.println("ERROR Wrong string in input file");
                break;
        }
    }
    void setAlive(boolean state) {
        alive = state;
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
    boolean update (Cell[][] cell,int row, int col){
        int aliveneighbours = 0;
        int i = row - 1;//go 1 row up to start.
        while (i <= row+1){
            int j = col -1; //start 1 col to the left this is reset within every row.
            while (j <= col +1){
                if(cell[row][j].isAlive()){
                    aliveneighbours +=1; //set living neighbour count to increase if we find a living neighbour.
                }else{
                    continue;
                } 
               j++;
            }
            i++;
    }
       // using the previous counter the following logic sets the current cell to true or false according to the specifications 
       if (aliveneighbours <= 2 && this.isAlive()){
           this.setAlive(false);
           return false;
       }else if (aliveneighbours >= 3 && this.isAlive()){
           this.setAlive(false);
           return true;
       }else if (2<= aliveneighbours && aliveneighbours <= 3 && this.isAlive()){
           this.setAlive(true);
           return true;
       }else if (!this.isAlive() && aliveneighbours == 3){
           this.setAlive(true);
           return true;
       }
        return true;
    }
}