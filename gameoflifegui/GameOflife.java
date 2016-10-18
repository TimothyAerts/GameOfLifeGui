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
    JButton stop;
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
        readBirth initialBirth = new readBirth("C:\\Users\\Mathieu\\Documents\\NetBeansProjects\\gameoflifegui\\src\\gameoflifegui\\birth.txt");
        this.oldCells = initialBirth.readCells();
        
    }
    public void run() {
        start = new JButton("Start/resume");
        stop = new JButton("Stop");
        Buttons.add(start);
        Buttons.add(stop);
        start.setActionCommand("Start");
        stop.setActionCommand("Stop");
        start.addActionListener(new ButtonClickListener());
        stop.addActionListener(new ButtonClickListener());
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
    if(benjifukaboi){
        this.updateCells();
        CellPane.setText("");
        printCells();
    }
    }
    
    
    private class StartListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            timer.start();
        }
    }
    
    private class StopListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            timer.stop();
        }
    }
    
    private class ButtonClickListener implements ActionListener{
        Timer timer;        
        
            timer = new Timer(1000, new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    runGenerate();
                }
            });
            
        public void actionPerformed(ActionEvent e){
             String command = e.getActionCommand();
            if(e.getSource("Start")){
            timer.start();
            updateCells();
            CellPane.setText("");
            printCells();
        }
        else if(command.equals("Stop")){
            timer.stop();
        }
         
        public void actionPerformed(ActionEvent e){
            String command = e.getActionCommand();
            
            Timer timer1 = new Timer(1000, this);
            
            if (command.equals( "Start")){
                timer1.start();   
                runGeneration();
                System.out.println("Start");
            }
            if (command.equals( "Stop" )){
                timer1.restart();
                System.out.println("STOP");
                
            }
        }
    
    }
         
}
