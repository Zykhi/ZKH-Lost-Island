import javax.imageio.ImageIO;

public class RockProjectile extends Projectile {

    GamePanel aGamePanel;

    public RockProjectile(GamePanel pGamePanel) {
        super(pGamePanel);
        this.aGamePanel = pGamePanel;

        setName("Rock");
        setSpeed(4);
        setMaxLife(80);
        setLife(getMaxLife());
        setAttack(2);
        setUseCost(1);
        setAlive(false);
        getRockImage();
    }

    private void getRockImage() {
        try {
            setImage(ImageIO.read(getClass().getResourceAsStream("/img/item/potion_red.png")));
            setImage(getUtilityTool().scaleImage(getImage(), aGamePanel.getTileSize(), aGamePanel.getTileSize()));
        } catch (Exception e) {
        }
    }
}
