import javax.imageio.ImageIO;

public class HandmadeAxe extends Entity {

    public HandmadeAxe(GamePanel pGamePanel) {
        super(pGamePanel);

        setName("Handmade Axe");
        setType(getAxeType());
        setAttackValue(2);
        setDescription("[" + getName() + "]\nA simple Axe.");
        getAttackArea().width = 40;
        getAttackArea().height = 40;

        try {
            setImage(ImageIO.read(getClass().getResourceAsStream("/img/item/Axe1.png")));
            setImage(getUtilityTool().scaleImage(getImage(), pGamePanel.getTileSize(), pGamePanel.getTileSize()));
        } catch (Exception e) {

        }
    }
}
