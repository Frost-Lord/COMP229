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
            String[] TileNames = {
                    "res/tile/grass00.png", //10
                    "res/tile/road00.png", //11
                    "res/tile/water00.png", //13
                    "res/tile/tree.png", //14
                    "res/tile/sand.png", //15
                    "res/tile/wall.png", //16
                    "res/tile/wall.png", //17
                    "res/tile/water02.png", //18
                    "res/tile/water03.png", //19
                    "res/tile/water05.png", //20
                    "res/tile/water06.png", //21
                    "res/tile/water04.png", //22
                    "res/tile/water07.png", //23
                    "res/tile/water08.png", //24
                    "res/tile/water09.png", //25
                    "res/tile/hut.png", //25
                    "res/tile/table01.png" //26
            };
            for (int i = 0; i < TileNames.length; i++) {
                tile[i + 10] = new Tile();
                tile[i + 10].image = ImageIO.read(new File(TileNames[i]));
            }

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