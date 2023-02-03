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
        tile = new Tile[100];
        MapNum = new int[gp.MaxWorldCol][gp.MaxWorldRow];
        loadTileImages();
        LoadMap();
    }

    public void loadTileImages() {
        try {
            tile[10] = new Tile();
            tile[10].image = ImageIO.read(new File(("res/tile/grass00.png")));

            tile[11] = new Tile();
            tile[11].image = ImageIO.read(new File(("res/tile/road00.png")));

            tile[12] = new Tile();
            tile[12].image = ImageIO.read(new File(("res/tile/water00.png")));

            tile[13] = new Tile();
            tile[13].image = ImageIO.read(new File(("res/tile/tree.png")));

            tile[14] = new Tile();
            tile[14].image = ImageIO.read(new File(("res/tile/sand.png")));

            tile[15] = new Tile();
            tile[15].image = ImageIO.read(new File(("res/tile/wall.png")));

            tile[16] = new Tile();
            tile[16].image = ImageIO.read(new File(("res/tile/wall.png")));

            tile[17] = new Tile();
            tile[17].image = ImageIO.read(new File(("res/tile/water02.png")));

            tile[18] = new Tile();
            tile[18].image = ImageIO.read(new File(("res/tile/water03.png")));

            tile[19] = new Tile();
            tile[19].image = ImageIO.read(new File(("res/tile/water05.png")));

            tile[20] = new Tile();
            tile[20].image = ImageIO.read(new File(("res/tile/water06.png")));

            tile[21] = new Tile();
            tile[21].image = ImageIO.read(new File(("res/tile/water04.png")));

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