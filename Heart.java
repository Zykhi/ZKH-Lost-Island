import javax.imageio.ImageIO;

public class Heart extends Entity{

    public Heart(GamePanel pGamePanel) {

        super(pGamePanel);

        setName("Heart"); 
        try {
            setImage(ImageIO.read(getClass().getResourceAsStream("/img/playerUI/heart_full.png")));
            setImage2(ImageIO.read(getClass().getResourceAsStream("/img/playerUI/heart_half.png")));
            setImage3(ImageIO.read(getClass().getResourceAsStream("/img/playerUI/heart_blank.png")));
            setImage(getUtilityTool().scaleImage(getImage(), pGamePanel.getTileSize(), pGamePanel.getTileSize()));
            setImage2(getUtilityTool().scaleImage(getImage2(), pGamePanel.getTileSize(), pGamePanel.getTileSize()));
            setImage3(getUtilityTool().scaleImage(getImage3(), pGamePanel.getTileSize(), pGamePanel.getTileSize()));
        } catch (Exception e) {

        }
    }
}
