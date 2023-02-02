package entity;

import main.GamePanel;
import main.InputHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player extends Entity {

    GamePanel gp;
    InputHandler InputH;

    public final int DisplayX;
    public final int DisplayY;


    public Player(GamePanel gp, InputHandler InputH) {
        this.gp = gp;
        this.InputH = InputH;

        DisplayX = gp.screenWidth/2 - gp.titleSize/2;
        DisplayY = gp.screenHeight/2 - gp.titleSize/2;

        setDefVal();
        GetPlayerIMG();
    }
    public void setDefVal() {
        WorldX = gp.titleSize * 23;
        WorldY = gp.titleSize * 21;
        speed = 4;
        direction = "down";
    }
    public void GetPlayerIMG() {
        try {
            up1 = ImageIO.read(new File(("res/character/idle.png")));
            up2 = ImageIO.read(new File(("res/character/idle.png")));
            down1 = ImageIO.read(new File(("res/character/idle.png")));
            down2 = ImageIO.read(new File(("res/character/idle.png")));
            left1 = ImageIO.read(new File(("res/character/idle.png")));
            left2 = ImageIO.read(new File(("res/character/idle.png")));
            right1 = ImageIO.read(new File(("res/character/idle.png")));
            right2 = ImageIO.read(new File(("res/character/idle.png")));
        }catch (IOException e) {
            System.out.println("Problem! image can't be loaded!");
        }
    }
    public void Update() {
        int prevX = WorldX;
        int prevY = WorldY;

        if(InputH.UpActivated == true) {
            direction = "up";
            WorldY -= speed;
        } else if(InputH.DownActivated == true) {
            direction = "down";
            WorldY += speed;
        } else if(InputH.LeftActivated == true) {
            direction = "left";
            WorldX -= speed;
        } else if(InputH.RightActivated == true) {
            direction = "right";
            WorldX += speed;
        }

        if (WorldX < 0) {
            WorldX = prevX;
        } else if (WorldX > gp.WorldWidth - gp.titleSize) {
            WorldX = prevX;
        }
        if (WorldY < 0) {
            WorldY = prevY;
        } else if (WorldY > gp.WorldHeight - gp.titleSize) {
            WorldY = prevY;
        }
    }
    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        switch (direction) {
            case "up":
                image = up1;
                break;
            case "down":
                image = down1;
                break;
            case "left":
                image = left1;
                break;
            case "right":
                image = right1;
                break;
        }
        g2.drawImage(image, DisplayX, DisplayY, gp.titleSize, gp.titleSize, null);
    }
}