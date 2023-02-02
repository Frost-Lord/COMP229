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

    public Player(GamePanel gp, InputHandler InputH) {
        this.gp = gp;
        this.InputH = InputH;
        setDefVal();
        GetPlayerIMG();
    }
    public void setDefVal() {
        x = 100;
        y = 100;
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
        if(InputH.UpActivated == true) {
            direction = "up";
            y -= speed;
        } else if(InputH.DownActivated == true) {
            direction = "down";
            y += speed;
        } else if(InputH.LeftActivated == true) {
            direction = "left";
            x -= speed;
        } else if(InputH.RightActivated == true) {
            direction = "right";
            x += speed;
        }
    }
    public void draw(Graphics2D g2) {
        //g2.setColor(Color.WHITE);
        //g2.fillRect(x, y, gp.titleSize, gp.titleSize);
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
        g2.drawImage(image, x, y, gp.titleSize, gp.titleSize, null);
    }
}