/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Timothy Aerts, Mathieu Kessels, Benjamin Parment
 */
public class GameOfLifeGui {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //application logic here
        GameOflife actualrun = new GameOflife();
        actualrun.PrepGui();
        actualrun.run();
        }
    
}
