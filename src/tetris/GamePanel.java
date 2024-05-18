package tetris;

import TetrisBlocks.*;

import javax.swing.JPanel;
import java.awt.*;
import java.util.Random;

public class GamePanel extends JPanel {
    private int colums;
    private int rows;
    private int cellsize;
    private Block block;
    private Block[] blocks;
    private Color[][] background;

    public GamePanel(int c){
        this.setBounds(100,50,280,570);
        this.setBackground(Color.black);
        colums=c;
        cellsize=this.getBounds().width/colums;
        rows=this.getBounds().height/cellsize;
        background=new Color[rows][colums];
        blocks=new Block[]{new Ishape(),new JShape(),new LShape(),new OShape(),new SShape(),new TShape(),new ZShape()};
    }
    public void spawnblock(){
        Random r=new Random();
        block=blocks[r.nextInt(blocks.length)];
        block.spawn(colums);
    }
    public boolean moveBlockDown(){
        if(!chekBotom()) {
         movBlocktoBackground();
         clearLine();
         return false;
        }
        block.movDown();
        repaint();
        return true;
    }
    public void movBlockRight(){
        if(!chekRight()) return;
        block.movRight();
        repaint();
    }
    public void movBlockLeft(){
        if(!chekLeft()) return;
        block.movLeft();
        repaint();
    }
    public void dropBlock(){
        while(chekBotom()) {
            block.movDown();
        }
        repaint();
    }
    public void rotatBlock(){
        block.rotete();
        if(block.getleft()<0) block.setX(0);
        if(block.getright()>=colums) block.setX(colums-block.getWidth());
        if(block.getright()>=rows) block.setY(rows-block.getHeight());
        repaint();
    }
    private boolean chekBotom(){
        if(block.getbotom()==rows) return false;
          int[][] shape=block.getShape();
          int w= block.getWidth();
          int h= block.getHeight();
          for(int i=0;i<w;i++){
              for(int j=h-1;j>=0;j--){
                  if(shape[j][i]!=0){
                      int x=i+block.getX();
                      int y=j+block.getY()+1;
                      if(y<0) break;
                      if(background[y][x]!=null) return false;
                      break;
                  }
              }
          }
            return true;


    }
    private boolean chekRight(){
        if(block.getright()==colums) return false;
        int[][] shape=block.getShape();
          int w= block.getWidth();
          int h= block.getHeight();
          for(int i=0;i<h;i++){
              for(int j=w-1;j>=0;j--){
                  if(shape[i][j]!=0){
                      int x=j+block.getX()+1;
                      int y=i+block.getY();
                      if(y<0) break;
                      if(background[y][x]!=null) return false;
                      break;
                  }
              }
          }

        return true;
    }
    private boolean chekLeft(){
        if(block.getleft()==0) return false;
          int[][] shape=block.getShape();
          int w= block.getWidth();
          int h= block.getHeight();
          for(int i=0;i<h;i++){
              for(int j=0;j<w;j++){
                  if(shape[i][j]!=0){
                      int x=j+block.getX()-1;
                      int y=i+block.getY();
                      if(y<0) break;
                      if(background[y][x]!=null) return false;
                      break;
                  }
              }
          }
        return true;
    }
    public void clearLine(){
        boolean fullLine;
        for(int i=rows-1;i>=0;i--){
            fullLine=true;
            for(int j=0;j<colums;j++){
                if(background[i][j]==null){
                    fullLine=false;
                    break;
                }
            }
            if(fullLine){
                clearl(i);
                shiftDown(i);
                clearl(0);
                i++;
                repaint();
            }
        }
    }
    public void clearl(int i){
        for(int k=0;k<colums;k++){
         background[i][k]=null;
        }
    }
    public void shiftDown(int i){
        for(int j=i;j>0;j--){
            for(int k=0;k<colums;k++){
                background[j][k]=background[j-1][k];
            }
        }
    }
    private void movBlocktoBackground(){
        int[][] shape= block.getShape();
        int h= block.getHeight();
        int w= block.getWidth();
        int x= block.getX();
        int y= block.getY();
        Color color=block.getColor();
        for(int i=0;i<h;i++){
            for(int j=0;j<w;j++){
                if(shape[i][j]==1){
                    background[i+y][j+x]=color;
                }
            }
        }
    }
    private void drawblock(Graphics g){

        int h=block.getHeight();
        int w= block.getWidth();
        int[][] shape= block.getShape();
        Color c=block.getColor();

        for(int i=0;i<h;i++){
            for(int j=0;j<w;j++){
                if(shape[i][j]==1) {
                int x=(block.getX()+j)*cellsize;
                int y=(block.getY()+i)*cellsize;
                drawSqaur(g,c,x,y);
                }
            }
        }
    }
    public void drawBackground(Graphics g){
        Color color;
        for(int i=0;i<rows;i++){
            for(int j=0;j<colums;j++){
                color=background[i][j];
                if(color!=null){
                    int x=j*cellsize;
                    int y=i*cellsize;
                    drawSqaur(g,color,x,y);
                }
            }
        }
    }
    public void drawSqaur(Graphics g,Color color,int x,int y){
         g.setColor(color);
         g.fillRect(x,y,cellsize,cellsize);
         g.setColor(Color.gray);
         g.drawRect(x,y,cellsize,cellsize);
    }
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        drawBackground(g);
        drawblock(g);

    }

}
