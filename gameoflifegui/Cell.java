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
    void update (Cell[][] cell,int row, int col){
        int aliveneighbours = 0;
        int i = row - 1;
        while (i <= row+1){
            int j = col -1;
            while (j <= col +1){
                if(cell[row][j].isAlive()){
                    aliveneighbours +=1;
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
       }else if (aliveneighbours >= 3 && this.isAlive()){
           this.setAlive(false);
       }else if (2<= aliveneighbours && aliveneighbours <= 3 && this.isAlive()){
           this.setAlive(true);
       }else if (!this.isAlive() && aliveneighbours == 3){
           this.setAlive(true);
       }
       
    }
}
