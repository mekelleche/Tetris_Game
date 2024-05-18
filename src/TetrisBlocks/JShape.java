package TetrisBlocks;

import tetris.Block;

import java.awt.*;

public class JShape extends Block {
    public JShape(){
        super(new int[][]{{0,1},{0,1},{1,1}}, Color.blue);
    }
}
