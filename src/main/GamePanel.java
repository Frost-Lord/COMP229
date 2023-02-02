package main;

import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicTreeUI;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    final int originalTitleSize = 16;
    final int scale = 3;

    final int titleSize = originalTitleSize * scale;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = titleSize * maxScreenCol;
    final int screenHeight = titleSize * maxScreenRow;

    int FPS = 60;

    InputHandler InputH = new InputHandler();
    Thread gameThread;

    int PlayerX = 100;
    int PlayerY = 100;
    int PlayerSpeed = 4;

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

        }
    }

    public void update() {
        if(InputH.UpActivated == true) {
            PlayerY -= PlayerSpeed;
        } else if(InputH.DownActivated == true) {
            PlayerY += PlayerSpeed;
        } else if(InputH.LeftActivated == true) {
            PlayerX -= PlayerSpeed;
        } else if(InputH.RightActivated == true) {
            PlayerX += PlayerSpeed;
        }
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.WHITE);
        g2.fillRect(PlayerX, PlayerY, titleSize, titleSize);
        g2.dispose();
    }

}