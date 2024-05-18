package TetrisBlocks;

import tetris.Block;

import java.awt.*;

public class LShape extends Block {
    public LShape(){
        super(new int[][]{{1,0},{1,0},{1,1}}, Color.orange);
    }
}
