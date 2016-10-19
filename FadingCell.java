/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author timothy
 */
import java.awt.Color;
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
    public Color deadColor (int n){
        n = this.age;
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
