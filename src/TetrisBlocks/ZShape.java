package TetrisBlocks;

import tetris.Block;

import java.awt.*;

public class ZShape extends Block {
    public ZShape(){
        super(new int[][]{{1,1,0},{0,1,1}}, Color.red);
    }
}
