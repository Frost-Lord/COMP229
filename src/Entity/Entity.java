package entity;

import java.awt.image.BufferedImage;

public class Entity {
    public int WorldX, WorldY;
    public String MapName;
    public int speed;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;
}