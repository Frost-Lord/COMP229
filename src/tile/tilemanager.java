package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;

public class TileManager {
    private GamePanel gp;
    private Tile[] tile;
    private int[][] MapNum;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[10];
        MapNum = new int[gp.maxScreenCol][gp.maxScreenRow];
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
            FileReader fileReader = new FileReader("res/maps/map01.txt");
            BufferedReader br = new BufferedReader(fileReader);
            
            String Line = null;
            int row = 0;

            while((Line = br.readLine()) != null) {
                String[] nums = Line.split(" ");
                for (int col = 0; col < nums.length; col++) {
                    MapNum[col][row] = Integer.parseInt(nums[col]);
                }
                row++;
            }
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2) {
        int x = 0, y = 0;
        for (int row = 0; row < gp.maxScreenRow; row++) {
            for (int col = 0; col < gp.maxScreenCol; col++) {
                int tileNum = MapNum[col][row];
                g2.drawImage(tile[tileNum].image, x, y, gp.titleSize, gp.titleSize, null);
                x += gp.titleSize;
            }
            x = 0;
            y += gp.titleSize;
        }
    }
}