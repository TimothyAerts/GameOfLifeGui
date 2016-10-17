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
    private Cell[][] InitialCells = this.CreateCells();
    Cell[][] Cells;
    
    public void PrepGui() {
        Mainframe = new JFrame("Game Of Life GUI");
        Mainframe.setSize(800,600);
        //Mainframe.setLayout(new GridLayout(2,2));
        CellPane = new JTextArea();
        Buttons = new JPanel();
        Mainframe.add(CellPane);
        Mainframe.add(Buttons, BorderLayout.SOUTH);

    }
    public Cell[][] CreateCells () {
        readBirth readinitial = new readBirth("C:/Users/timothy/Documents/GitHub/GameOfLifeGui/src/gameoflifegui/birth.txt");
        Cells = readinitial.readCells();
        return Cells;
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
        Mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.CreateCells();
    }
    

   private void updateGeneration (){
    for (int i = 0; i <= this.CreateCells().length ; i++){
        for (int j = 0 ; j <= this.CreateCells()[i].length; j++){
            this.InitialCells[i][j].update(this.CreateCells(), i, j);
        }
    }
}
   private class PrintCells {
       private Cell[][] CellGrid;
       JTextArea CellPane;
       PrintCells (Cell[][] Grid, JTextArea CellPane){
           CellGrid = Grid;
           for (int row=0 ; row<=this.CellGrid.length;row++){
                for (int column = 0 ;column <= this.CellGrid[row].length;column++){
                    if(CellGrid[row][column].isAlive() == true){
                        String val = "*";
                        this.CellPane.append(val);
                    }else{
                        String val =" ";
                        this.CellPane.append(val);
                    }       
            }
        }
          
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
