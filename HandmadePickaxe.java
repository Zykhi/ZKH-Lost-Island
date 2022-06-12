import javax.imageio.ImageIO;

public class HandmadePickaxe extends Entity{

    public HandmadePickaxe(GamePanel pGamePanel) {
        super(pGamePanel);
        
        setName("Handmade Pickaxe");
        setType(getPickaxeType());
        setAttackValue(1);
        setDescription("["+getName()+"]\n.");
        getAttackArea().width = 36;
        getAttackArea().height = 36;

        try {
            setImage(ImageIO.read(getClass().getResourceAsStream("/img/item/Pickaxe1.png")));
            setImage(getUtilityTool().scaleImage(getImage(), pGamePanel.getTileSize(), pGamePanel.getTileSize()));
        } catch (Exception e) {

        }
    }
    

}