package TetrisBlocks;

import tetris.Block;

import java.awt.*;

public class SShape extends Block {
    public SShape(){
        super(new int[][]{{0,1,1},{1,1,0}}, Color.green);
    }
}
