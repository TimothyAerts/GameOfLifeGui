/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.*;
import java.awt.event.*;
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
    CellLayout cells;
    JButton start;
    Cell[][] CurrentGeneration;
    Cell[][] NewGeneration;
    int GenerationCounter;
    Boolean timerrunning = false;
    CellButton[][] btns;
    int[] size;
    
    public void CreateInitialCells () {      
        readBirth initialBirth = new readBirth("C:/Users/timothy/Documents/GitHub/GameOfLifeGui/src/birth.txt");
        this.CurrentGeneration = initialBirth.readCells();
        this.size = initialBirth.readSize();
    }
    
    public void PrepGui() {
        Mainframe = new JFrame("Game Of Life GUI");
        Mainframe.setSize(400,400);
        Buttons = new JPanel();
        this.CreateInitialCells();
        cells = new CellLayout(size[0], size[1]);
        Mainframe.add(cells);
        Mainframe.add(Buttons, BorderLayout.SOUTH);
        Mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        

    }

    
    public void run() {
        start = new JButton("Start/resume");
        Buttons.add(start);
        start.setActionCommand("Start");
        start.addActionListener(new ButtonClickListener());
        Mainframe.setVisible(true);
    }
    
    private void updateCells(){
        NewGeneration = new Cell[this.CurrentGeneration.length][this.CurrentGeneration[0].length];
        for (int row=0 ; row<this.CurrentGeneration.length;row++){
            for (int column = 0 ;column < this.CurrentGeneration[row].length;column++){
                String strCell;
                boolean updateBool = CurrentGeneration[row][column].update(CurrentGeneration);

                NewGeneration[row][column] = new Cell(row, column);
                NewGeneration[row][column].setTimeOfDeath(CurrentGeneration[row][column].getTimeOfDeath()); 
                NewGeneration[row][column].setAlive(updateBool);
                if (!NewGeneration[row][column].isAlive() && CurrentGeneration[row][column].isAlive()){
                    NewGeneration[row][column].setTimeOfDeath(GenerationCounter);
                }
                }
            }     
    }
    

    
    
    public void runGeneration(){
        timerrunning = true;
        this.updateCells();
        for (int row=0;row<CurrentGeneration.length;row++){
            for (int col=0;col<CurrentGeneration[0].length;col++){
                if (NewGeneration[row][col].isAlive()){
                    btns[row][col].setBackground(NewGeneration[row][col].AliveColor());
                }else{
                    int n = 1+GenerationCounter -NewGeneration[row][col].getTimeOfDeath();
                    if (n <0){
                        n = 999;
                    }
                    btns[row][col].setBackground(NewGeneration[row][col].deadColor(n));
                }
                
            }
        }
        cells.repaint();
        CurrentGeneration = NewGeneration;
        GenerationCounter ++;
    }
    
    Timer timer = new Timer(750, new ActionListener() {
        public void actionPerformed(ActionEvent e){
            runGeneration();
        }
    });
   public class CellLayout extends JPanel{
       public CellLayout(int row, int col){
        
        btns = new CellButton[CurrentGeneration.length][CurrentGeneration[0].length];
        for (int cellrow=0;cellrow<CurrentGeneration.length;cellrow++){
            for (int cellcol=0;cellcol<CurrentGeneration[0].length;cellcol++){
                btns[cellrow][cellcol] = CurrentGeneration[cellrow][cellcol].CellBTN();
            }
        }
        setLayout(new GridLayout(row, col));
        setBorder(BorderFactory.createEmptyBorder(1,1,1,1));
        
        for (int btnrow=0;btnrow<CurrentGeneration.length;btnrow++){
            for (int btncol=0;btncol<CurrentGeneration[0].length;btncol++){
                int getBtnCol = btns[btnrow][btncol].getCol();
                int getBtnRow = btns[btnrow][btncol].getRow();
                add(btns[btnrow][btncol]);
                btns[btnrow][btncol].addActionListener(new ButtonClickListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){
                       if (CurrentGeneration[getBtnRow][getBtnCol].isAlive() && !timerrunning){
                           CurrentGeneration[getBtnRow][getBtnCol].setAlive(false);
                           btns[getBtnRow][getBtnCol].setBackground(CurrentGeneration[getBtnRow][getBtnCol].deadColor(999));
                       }else if(!CurrentGeneration[getBtnRow][getBtnCol].isAlive() && !timerrunning){
                           CurrentGeneration[getBtnRow][getBtnCol].setAlive(true);
                           btns[getBtnRow][getBtnCol].setBackground(CurrentGeneration[getBtnRow][getBtnCol].AliveColor());
                       }
                    }
                });
            }
        }
            

   }
   }
       
   public class ButtonClickListener implements ActionListener{
        public boolean started = false;
        @Override
        public void actionPerformed(ActionEvent e){
            String command = e.getActionCommand();
            if (command.equals( "Start")){
                if (!started){
                    started = true;
                    timer.start();
                }else{
                    started = false;
                    timer.stop();
                    timerrunning = false;
                }
            }
        }
   }
   
}