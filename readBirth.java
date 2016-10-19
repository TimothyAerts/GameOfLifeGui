/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;
import java.util.Scanner;
import java.net.URL;
/**
 *
 * @author timothy
 */
public class readBirth{
    private int[] size = new int[2];
    private File file;
    String birthFilename;
    private Scanner input;
    private String line = "";
    Cell[][] Cells;
    readBirth(String str){
        URL path = GameOflife.class.getResource(str);
        file = new File(path.getFile());
        birthFilename = str;
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
        size = this.readSize();
        Cells  = new Cell[size[0]][size[1]];
        int rowcounter = 0;
        input.nextLine();
        
        while (input.hasNextLine()){
            String[] linearray;
            line  = input.nextLine().trim();
            linearray = line.split(" ");
            int columncounter = 0;
            for (int col=0 ;col<linearray.length ;col++){
                Cell cellval;
                cellval = new Cell(linearray[col], rowcounter,columncounter);
                Cells[rowcounter][columncounter] = cellval;
                columncounter++;
            }
            rowcounter ++;
        }
        return Cells;
        
    }

}

    
    

