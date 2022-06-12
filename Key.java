import javax.imageio.ImageIO;

public class Key extends Entity {

    public Key(GamePanel pGamePanel) {

        super(pGamePanel);

        setName("Key"); 
        try {
            setImage(ImageIO.read(getClass().getResourceAsStream("/img/item/key.png")));
            setImage(getUtilityTool().scaleImage(getImage(), pGamePanel.getTileSize(), pGamePanel.getTileSize()));
        } catch (Exception e) {

        }
    }

}
