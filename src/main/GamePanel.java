package main;

import entity.Player;
import tile.TileManager;
import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    final int originalTitleSize = 16;
    final int scale = 3;

    public final int titleSize = originalTitleSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = titleSize * maxScreenCol;
    public final int screenHeight = titleSize * maxScreenRow;

    public final int MaxWorldCol = 50;
    public final int MaxWorldRow = 50;
    public final int WorldWidth = titleSize * MaxWorldCol;
    public final int WorldHeight = titleSize * MaxWorldRow;

    int FPS = 60;

    TileManager tileM = new TileManager(this);
    InputHandler InputH = new InputHandler();
    Thread gameThread;
    public Player player = new Player(this, InputH);

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(InputH);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void run() {
        double drawINT = 1000000000/FPS;
        double nextDrawT = System.nanoTime() + drawINT;

        while(gameThread != null) {
            update();
            repaint();
            try {
                double RemTime = nextDrawT - System.nanoTime();
                RemTime = RemTime/1000000;

                if(RemTime < 0) {
                    RemTime = 0;
                }
                Thread.sleep((long) RemTime);
                nextDrawT += drawINT;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void update() {
        player.Update();
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        tileM.draw(g2);
        player.draw(g2);
        g2.dispose();
    }

}