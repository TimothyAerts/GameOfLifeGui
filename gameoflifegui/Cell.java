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
       //implement rules and checks to neighbours.
       
    }
}
