/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Timothy Aerts, Mathieu Kessels, Benjamin Parment
 */
import java.awt.event.*;
import javax.swing.*;

//Start of Mouse Drag Extra

public class MouseMotionEvent extends JPanel implements MouseMotionListener{
    
    public MouseMotionEvent (){
        addMouseMotionListener(this);
        setVisible(true);
    }
    public void mouseMoved (MouseEvent me){
        System.out.println("mouse moved");
        repaint();
    }
    public void mouseDragged(MouseEvent me){
        mouseMoved(me);
    }
    public void mouseClicked(MouseEvent me){
        
    }
}
