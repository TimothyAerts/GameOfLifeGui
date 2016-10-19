/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Timothy Aerts, Mathieu Kessels, Benjamin Parment
 */
import javax.swing.JButton;
import javax.swing.*;
import java.awt.event.*;

public class CellButton extends JButton{    //adding properties to cell buttons
    int row;                                //so they have row and column
    int col;
    public CellButton (int row, int col){
        this.row = row;
        this.col = col;
    }
    
    public int getRow(){                    //making row returnable
        return this.row;
    }
    
    public int getCol(){                    //making column returnable
        return this.col;
    }
    
    
}
