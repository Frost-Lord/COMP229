package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;

public class TileManager {
    private GamePanel gp;
    private Tile[] tile;
    int MapNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[10];
        MapNum = new int[gp.MaxWorldCol][gp.MaxWorldRow];
        loadTileImages();
        LoadMap();
    }

    public void loadTileImages() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(new File(("res/tile/grass.png")));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(new File(("res/tile/earth.png")));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(new File(("res/tile/water.png")));

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(new File(("res/tile/tree.png")));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(new File(("res/tile/sand.png")));

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(new File(("res/tile/wall.png")));

            tile[6] = new Tile();
            tile[6].image = ImageIO.read(new File(("res/tile/door.png")));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void LoadMap() {
        try {
            FileReader fileReader = new FileReader("res/maps/map02.txt");
            BufferedReader br = new BufferedReader(fileReader);

            int col = 0;
            int row = 0;

            while(col < gp.MaxWorldCol && row < gp.MaxWorldRow) {
                String Line = br.readLine();

                if (Line == null) {
                    break;
                }

                while(col < gp.MaxWorldCol) {
                    String numbers[] = Line.split(" ");
                    int num = Integer.parseInt(numbers[col]);

                    MapNum[col][row] = num;
                    col++;
                }

                if(col == gp.MaxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2) {
        int WorldCol = 0;
        int WorldRow = 0;

        while (WorldRow < gp.MaxWorldRow) {
            int TileNum = MapNum[WorldCol][WorldRow];

            int WorldX = WorldCol * gp.titleSize;
            int WorldY = WorldRow * gp.titleSize;
            int DisplayX = WorldX - gp.player.WorldX + gp.player.DisplayX;
            int DisplayY = WorldY - gp.player.WorldY + gp.player.DisplayY;

            if (WorldX + gp.titleSize > gp.player.WorldX - gp.player.DisplayX &&
                    WorldX - gp.titleSize < gp.player.WorldX + gp.player.DisplayX &&
                    WorldY + gp.titleSize > gp.player.WorldY - gp.player.DisplayY &&
                    WorldY - gp.titleSize < gp.player.WorldY + gp.player.DisplayY) {
                g2.drawImage(tile[TileNum].image, DisplayX, DisplayY, gp.titleSize, gp.titleSize, null);
            }
            WorldCol++;

            if (WorldCol == gp.MaxWorldCol) {
                WorldCol = 0;
                WorldRow++;
            }
        }
    }

}