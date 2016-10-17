/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflifegui;

/**
 *
 * @author timothy
 */
public class GameOfLifeGui {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        GameOflife GameOflife = new GameOflife();
        GameOflife.PrepGui();
        GameOflife.run();
    }
    
}
