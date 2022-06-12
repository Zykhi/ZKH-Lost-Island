import javax.imageio.ImageIO;

public class Potion extends Entity {

    private GamePanel aGamePanel;

    public Potion(GamePanel pGamePanel) {
        super(pGamePanel);

        this.aGamePanel = pGamePanel;

        setType(getConsumableType());
        setName("Potion");
        setValue(5);
        setDescription("[" + getName() + "]\nA potion.");

        try {
            setImage(ImageIO.read(getClass().getResourceAsStream("/img/item/Potion1.png")));
            setImage(getUtilityTool().scaleImage(getImage(), pGamePanel.getTileSize(), pGamePanel.getTileSize()));
        } catch (Exception e) {
        }
    }

    public void use(Entity pEntity) {
        aGamePanel.setGameState(aGamePanel.getDialogueState());
        aGamePanel.getUserInterface().setCurrentDialogue("You drink the potion. This heal you by " + getValue() + ".");
        pEntity.setLife(pEntity.getLife() + getValue());
    }
}
