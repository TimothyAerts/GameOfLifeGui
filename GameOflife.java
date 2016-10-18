/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflifegui;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;
/**
 *
 * @author timothy
 */
public class GameOflife extends JPanel{
    JFrame Mainframe;
    JTextArea CellPane;
    JPanel Buttons;
    JButton start;
    Cell[][] oldCells;
    Cell[][] newCells;
    Boolean benjifukaboi;
        
    public void PrepGui() {
        Mainframe = new JFrame("Game Of Life GUI");
        Mainframe.setSize(400,400);
        CellPane = new JTextArea();
        Buttons = new JPanel();
        Mainframe.add(CellPane);
        Mainframe.add(Buttons, BorderLayout.SOUTH);
        Mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        

    }
    public void CreateInitialCells () {      
        readBirth initialBirth = new readBirth("./birth.txt");
        this.oldCells = initialBirth.readCells();
        
    }
    public void run() {
        start = new JButton("Start/resume");
        Buttons.add(start);
        start.setActionCommand("Start");
        start.addActionListener(new ButtonClickListener());
        Mainframe.setVisible(true);
        this.CreateInitialCells();
        this.printCells();
        
    }
    
    private void updateCells(){
        newCells = new Cell[this.oldCells.length][this.oldCells[0].length];
        for (int row=0 ; row<this.oldCells.length;row++){
            for (int column = 0 ;column < this.oldCells[row].length;column++){
                System.out.println("row/column "+row+" "+column);
                String strCell;
                boolean updateBool = oldCells[row][column].update(oldCells, row, column);
                if(updateBool){
                    strCell = "*";
                }else{
                    strCell = ".";
                }
                newCells[row][column] = new Cell(strCell);
                newCells[row][column].setAlive(updateBool);
                }
            }
        oldCells = newCells;       
    }
    
    public void printCells() {
        System.out.println(oldCells[0][0]);
           for (int row=0 ; row<this.oldCells.length;row++){
                for (int column = 0 ;column < this.oldCells[row].length;column++){
                    if(this.oldCells[row][column].isAlive()){
                        String val = "* ";
                        this.CellPane.append(val);
                    }else{
                        String val =" . ";
                        this.CellPane.append(val);
                    }
                }
               this.CellPane.append("\n");
            
           }
    }
    
    
    
    public void runGeneration(){
        this.updateCells();
        CellPane.setText("");
        printCells();
 
    }
   Timer timer = new Timer(1000, new ActionListener() {
        public void actionPerformed(ActionEvent e){
            runGeneration();
        }
    });
         
       private class ButtonClickListener implements ActionListener{
        boolean started = false;
        public void actionPerformed(ActionEvent e){
            String command = e.getActionCommand();
            if (command.equals( "Start")){
                if(!started){
                    started = true;
                    timer.start();
                }else{
                    started = false;
                    timer.stop();
                }
            }
        }
    
    }
         
}
