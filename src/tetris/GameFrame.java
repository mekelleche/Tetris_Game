package tetris;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class GameFrame extends JFrame {
    private GamePanel game;
    public GameFrame(){
        game=new GamePanel(10);
        this.add(game);
        this.setSize(300, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        startGame();
        controls();
    }
    private void controls(){
        InputMap im=this.getRootPane().getInputMap();
        ActionMap am=this.getRootPane().getActionMap();
        im.put(KeyStroke.getKeyStroke("RIGHT"),"right");
        im.put(KeyStroke.getKeyStroke("LEFT"),"left");
        im.put(KeyStroke.getKeyStroke("UP"),"up");
        im.put(KeyStroke.getKeyStroke("DOWN"),"down");

        am.put("right", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.movBlockRight();
            }
        });
        am.put("left", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.movBlockLeft();
            }
        });
        am.put("up", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.rotatBlock();
            }
        });
        am.put("down", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.dropBlock();
            }
        });

    }
    public void startGame(){
        new GameThread(game).start();
    }


    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
             new GameFrame().setVisible(true);
            }
        });
    }

}
