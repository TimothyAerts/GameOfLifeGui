/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Timothy Aerts, Mathieu Kessels, Benjamin Parment
 */
import java.awt.Color;
//Start of FadingCell, not currently in use because we used different way
public class FadingCell extends Cell {
    int age;
    FadingCell (int row, int col){
        super(row, col);
    }
    
    public void setAge(int n){
        this.age = n;
    }
    public int getAge (){
        return this.age;
    }
    @Override
    public Color deadColor (int n){     //Fading colors logic depending on
        n = this.age;                   //when the cell died
        float[] rgb;
        rgb =new float[] {0.5f,0.5f,0.5f};
        for (int i=0;i<rgb.length;i++){
            rgb[i] = rgb[i] + (n*0.05f);
            if (rgb[i]>1){
                rgb[i]=1.0f;
            }
        }
        Color currentcolor = new Color(rgb[0],rgb[1],rgb[2]);
        return currentcolor;
    }
}
