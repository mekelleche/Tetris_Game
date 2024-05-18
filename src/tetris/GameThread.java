package tetris;

public class GameThread extends Thread{
    private GamePanel game;

    public GameThread(GamePanel game){
        this.game=game;
    }


    @Override
    public void run(){
        while (true) {
            game.spawnblock();
            while(game.moveBlockDown()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
