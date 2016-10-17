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
    Cell[][] Cells;
        
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
        this.Cells = initialBirth.readCells();
        
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
    
    public void printCells() {
        System.out.println(Cells[0][0]);
           for (int row=0 ; row<this.Cells.length;row++){
                for (int column = 0 ;column < this.Cells[row].length;column++){
                    if(this.Cells[row][column].isAlive()){
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
    
    private class ButtonClickListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String command = e.getActionCommand();
            if (command.equals( "Start")){
                System.out.println("Start");
            } else if (command.equals( "Stop" )){
                System.out.println("Stop");
            }
        }
    
    }
            
        
        
        
   
    
}
