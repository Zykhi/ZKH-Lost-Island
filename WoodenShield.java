import javax.imageio.ImageIO;

public class WoodenShield extends Entity{

    public WoodenShield(GamePanel pGamePanel) {
        super(pGamePanel);
        
        setName("Wood Shield");
        setType(getShieldType());
        setDefenseValue(1);
        try {
            setImage(ImageIO.read(getClass().getResourceAsStream("/img/item/Shield1.png")));
            setImage(getUtilityTool().scaleImage(getImage(), pGamePanel.getTileSize(), pGamePanel.getTileSize()));
        } catch (Exception e) {
        }
    }
}
