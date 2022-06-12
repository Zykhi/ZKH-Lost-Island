import javax.imageio.ImageIO;

public class HandmadeSword extends Entity{

    public HandmadeSword(GamePanel pGamePanel) {
        super(pGamePanel);
        
        setName("Handmade Sword");
        setType(getSwordType());
        setAttackValue(1);
        setDescription("["+getName()+"]\nA normal sword.");
        getAttackArea().width = 36;
        getAttackArea().height = 36;

        try {
            setImage(ImageIO.read(getClass().getResourceAsStream("/img/item/Sword1.png")));
            setImage(getUtilityTool().scaleImage(getImage(), pGamePanel.getTileSize(), pGamePanel.getTileSize()));
        } catch (Exception e) {

        }
    }
    

}
