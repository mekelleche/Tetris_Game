package tetris;

import java.awt.*;
import java.util.Random;

public class Block {
    private int[][] shape;
    private Color color;
    private int x;
    private int y;
    private int[][][] shapes;
    private int Cshape;

    public Block(int[][] shape,Color color){
        this.shape=shape;
        this.color=color;
        theShapes();
    }
    private void theShapes(){
        shapes=new int[4][][];
        for(int i=0;i<4;i++){
            int r=shape[0].length;
            int c=shape.length;
            shapes[i]=new int[r][c];
            for(int j=0;j<r;j++){
                for(int k=0;k<c;k++){
                    shapes[i][j][k]=shape[c-k-1][j];
                }
            }
            shape=shapes[i];
        }
    }
    public void spawn(int w){
        Random r=new Random();

        Cshape=r.nextInt(shape.length);
        shape=shapes[Cshape];
         y=0-getHeight();
         x=r.nextInt(w-getWidth());
    }
    public int[][] getShape(){return shape;}
    public Color getColor(){return color;}
    public int getHeight(){return shape.length;}
    public int getWidth(){return shape[0].length;}
    public int getX(){return x;}
    public int getY(){return y;}
    public void setX(int x){this.x=x;}
    public void setY(int y){this.y=y;}
    public void movDown(){y++;}
    public void movRight(){x++;}
    public void movLeft(){x--;}
    public void rotete(){
        Cshape++;
        if(Cshape>3) Cshape=0;
        shape=shapes[Cshape];
    }
    public int getbotom(){return y+getHeight();}
    public int getright(){return x+getWidth();}
    public int getleft(){return x;}


}
