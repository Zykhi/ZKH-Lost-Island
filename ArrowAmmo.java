import javax.imageio.ImageIO;

public class ArrowAmmo extends Entity {

    GamePanel aGamePanel;

    public ArrowAmmo(GamePanel pGamePanel) {
        super(pGamePanel);

        this.aGamePanel = pGamePanel;

        setType(getPickupOnlyType());
        setValue(1);
        setName("Arrow");
        try {
            setImage(ImageIO.read(getClass().getResourceAsStream("/img/playerUI/arrow_full.png")));
            setImage2(ImageIO.read(getClass().getResourceAsStream("/img/playerUI/arrow_blank.png")));
            setImage(getUtilityTool().scaleImage(getImage(), pGamePanel.getTileSize(), pGamePanel.getTileSize()));
            setImage2(getUtilityTool().scaleImage(getImage2(), pGamePanel.getTileSize(), pGamePanel.getTileSize()));
        } catch (Exception e) {
        }
    }

    public void use(Entity pEntity) {
        aGamePanel.getUserInterface().addMessage("Ammo +" + getValue());
        pEntity.setAmmo(pEntity.getAmmo() + getValue());
    }
}
