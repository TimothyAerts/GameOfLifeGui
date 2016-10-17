/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflifegui;
import java.io.*;
import java.util.Scanner;
/**
 *
 * @author timothy
 */
public class readBirth{
    private int[] size = new int[2];
    private File file;
    private Scanner input;
    private String line = "";
    Cell[][] Cells;
    readBirth(String str){
        file = new File(str);
        try {
            input = new Scanner(file);
        }catch (Exception e) {
            System.out.println("File not found");
        }
        
    }
    
    public int[] readSize(){
        int i =0;
        while (input.hasNextInt() && i<2){
            size[i] = input.nextInt();
            i++;
        }
        return size;
    }
    
    public Cell[][] readCells(){
        this.readSize();
        Cells  = new Cell[this.readSize()[0]][this.readSize()[1]];
        int rowcounter = 0;
        //skip line after the size ints
        input.nextLine();
        //while there are lines keep scanning could also be done with forloop since we have size.
        while (input.hasNextLine()){
            String[] linearray;
            line  = input.nextLine().trim();
            linearray = line.split(" ");
            int columncounter = 0; // reset the column counter after the 15 columns have been visited.
            for (int i=0 ;i<linearray.length ;i++){
                Cell cellval;
                cellval = new Cell(linearray[i]);
                System.out.println("cellvall " + cellval.isAlive() + "origineel " + linearray[i]);
                Cells[rowcounter][columncounter] = cellval;
                columncounter++; // increase colcounter to add next val to next row.col
            }
            rowcounter ++; //increase rowcounter to add next line into next row of grid.
        }
        return Cells;  
    }

}