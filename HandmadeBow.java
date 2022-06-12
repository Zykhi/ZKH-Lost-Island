import javax.imageio.ImageIO;

public class HandmadeBow extends Entity{

    public HandmadeBow(GamePanel pGamePanel) {
        super(pGamePanel);
        
        setName("Handmade Bow");
        setType(getBowType());
        //setAttackValue();
        setDescription("["+getName()+"]\n.");
        getAttackArea().width = 36;
        getAttackArea().height = 36;

        try {
            setImage(ImageIO.read(getClass().getResourceAsStream("/img/item/Bow1.png")));
            setImage(getUtilityTool().scaleImage(getImage(), pGamePanel.getTileSize(), pGamePanel.getTileSize()));
        } catch (Exception e) {

        }
    }
    

}