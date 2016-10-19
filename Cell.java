/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Timothy Aerts, Mathieu Kessels, Benjamin Parment
 */
import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;

public class Cell {
    private boolean alive;
    private int numNeighbours;
    private int row;
    private int col;
    private int timeofdeath = Integer.MAX_VALUE;
    Cell (String str, int row, int col){//Cell (alive or not, row, column)
        if (str.equals("*")){           //Reading from translated input string
            this.setAlive(true);        //"*"=alive "."=not alive
        }else if (str.equals(".")){
            this.setAlive(false);
        }else {                         //Error for wrong input
            System.out.println("ERROR Wrong string in input file");
        }
        this.row = row;                 
        this.col = col;
    }
    Cell (int row, int col){            //Cell (row, column)
        this.row = row;
        this.col = col;
    }
    
    public int getRow (){               //Returning current row
        return this.row;
    }
    public int getCol (){               //Returning current column
        return this.col;
    }
    void setAlive(boolean state) {      //Boolean to set Cells alive
        if(state){
            alive = true;
        }else{
            alive = false;
        }
    }
    boolean isAlive() {                 //Boolean to return alive or not
        return alive;
    }
    
    void setNumNeighbours (int n){      //The amount of neighbours n
        numNeighbours = n;
    }
    /**
     * 
     * @param cell indicate the grid being used
     * @param row row index of the current cell
     * @param col column index of current cell
     */
    public int calculateNumNeighbors (Cell[][] cell){   //Check the amount of 
        row = this.row;                                 //neighbours per cell
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
        
        while (i <= rowMax){                //Looping through rows and columns
                j = col -1;                 
            if (j < 0){
                j = col;
            }
            if (colMax >= cell[0].length){
                colMax = cell[0].length-1;
            }
            while (j <= colMax){            
                if (i==row && j==col){      //Skipping itself
                    j++;
                }else if (cell[i][j].isAlive()){    //Adding the neighbours it
                    aliveneighbours +=1;            //finds and moving on                
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
        int aliveNeighbours = this.calculateNumNeighbors(cell);
        //using the previous counter, the following logic sets the current 
        //cell to true or false according to the specifications 
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
    
    public void setTimeOfDeath(int n){      //counter for fading colors
        this.timeofdeath = n;
    }
    
    public int getTimeOfDeath (){           //returning when the cell died
        return this.timeofdeath;
    }
    public Color deadColor (int n){         //color set depending on how long
        float[] rgb;                        //the cell has been dead for
        rgb =new float[] {0.5f,0.5f,0.5f};  //initial rgb values on death
        for (int i=0;i<rgb.length;i++){
            rgb[i] = rgb[i] + (n*0.05f);    //increments of 0.05f for 9 steps
            if (rgb[i]>1){                  //till white, starting at 0.55f
                rgb[i]=1.0f;
            }
        }
        Color currentcolor = new Color(rgb[0],rgb[1],rgb[2]);
        return currentcolor;
    }
    
    public Color AliveColor (){             //color alive cells
        return Color.BLACK;
    }
   public CellButton CellBTN (){            //giving cells colors
       CellButton pan = new CellButton(this.row, this.col);
       pan.setEnabled(true);
       if (this.isAlive()){
           pan.setBackground(this.AliveColor());
       }else{
           pan.setBackground(this.deadColor(this.timeofdeath));
       }
       pan.setPreferredSize(new Dimension(3,3));
       pan.setBorder(BorderFactory.createLineBorder(Color.BLACK));
       pan.setName("");
       return pan;
   }
}