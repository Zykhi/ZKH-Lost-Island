import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/*
 *
 */
public class TileManager {
    private GamePanel aGamePanel;
    private Tile[] aTiles;
    private int aMapTileNum[][];

    public TileManager(GamePanel pGamePanel) {
        this.aGamePanel = pGamePanel;
        aTiles = new Tile[100];
        aMapTileNum = new int[aGamePanel.getMaxWorldCol()][aGamePanel.getMaxWorldRow()];
        getTileImage();
        loadMap();
    }

    public void getTileImage() {
        try {
            // grass tiles
            for (int i = 0; i < 67; i++) {
                aTiles[i] = new Tile();
                aTiles[i].setImage(ImageIO.read(getClass().getResourceAsStream("/img/tile/tile" + i + ".png")));
            }
            // water tiles
            for (int i = 0; i < 13; i++) {
                aTiles[i + 67] = new Tile();
                aTiles[i + 67].setImage(ImageIO
                        .read(getClass().getResourceAsStream("/img/tile/water/tile" + i + ".png")));
                aTiles[i + 67].setCollision(true);
            }
            // decoration tiles
            for (int i = 0; i < 20; i++) {
                aTiles[i + 80] = new Tile();
                aTiles[i + 80].setImage(ImageIO
                        .read(getClass().getResourceAsStream("/img/tile/decoration/tile" + i + ".png")));
            }

        } catch (Exception e) {
        }
    }

    public void loadMap() {
        try {
            InputStream vInputStream = getClass().getResourceAsStream("/maps/map.txt");
            BufferedReader vBufferedReader = new BufferedReader(new InputStreamReader(vInputStream));

            int vCol = 0;
            int vRow = 0;

            while (vCol < aGamePanel.getMaxWorldCol() && vRow < aGamePanel.getMaxWorldRow()) {
                String vLine = vBufferedReader.readLine();

                while (vCol < aGamePanel.getMaxWorldCol()) {
                    String vTileNumber[] = vLine.split(" ");
                    int vTileNb = Integer.parseInt(vTileNumber[vCol]);

                    aMapTileNum[vCol][vRow] = vTileNb;
                    vCol++;
                }
                if (vCol == aGamePanel.getMaxWorldCol()) {
                    vCol = 0;
                    vRow++;
                }
            }
            vBufferedReader.close();
        } catch (Exception e) {

        }
    }

    public void draw(Graphics2D pGraphics) {

        int vWorldCol = 0;
        int vWorldRow = 0;

        while (vWorldCol < aGamePanel.getMaxWorldCol() && vWorldRow < aGamePanel.getMaxWorldRow()) {
            int vTileNumber = aMapTileNum[vWorldCol][vWorldRow];
            int vWorldX = vWorldCol * aGamePanel.getTileSize();
            int vWorldY = vWorldRow * aGamePanel.getTileSize();
            int vScreenX = vWorldX - aGamePanel.getPlayer().getWorldX() + aGamePanel.getPlayer().getScreenX();
            int vScreenY = vWorldY - aGamePanel.getPlayer().getWorldY() + aGamePanel.getPlayer().getScreenY();

            if (vWorldX + aGamePanel.getTileSize() > aGamePanel.getPlayer().getWorldX()
                    - aGamePanel.getPlayer().getScreenX() &&
                    vWorldX - aGamePanel.getTileSize() < aGamePanel.getPlayer().getWorldX()
                            + aGamePanel.getPlayer().getScreenX()
                    &&
                    vWorldY + aGamePanel.getTileSize() > aGamePanel.getPlayer().getWorldY()
                            - aGamePanel.getPlayer().getScreenY()
                    &&
                    vWorldY - aGamePanel.getTileSize() < aGamePanel.getPlayer().getWorldY()
                            + aGamePanel.getPlayer().getScreenY()) {
                pGraphics.drawImage(aTiles[vTileNumber].getImage(), vScreenX, vScreenY, aGamePanel.getTileSize(),
                        aGamePanel.getTileSize(), null);
            }

            vWorldCol++;

            if (vWorldCol == aGamePanel.getMaxWorldCol()) {
                vWorldCol = 0;
                vWorldRow++;

            }
        }
    }

    public Tile getTile(int pTileNumber) {
        return aTiles[pTileNumber];
    }

    public int getTileNumber(int pWorldCol, int pWorldRow) {
        return aMapTileNum[pWorldCol][pWorldRow];
    }
}
