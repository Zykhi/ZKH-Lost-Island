import java.io.IOException;

import javax.imageio.ImageIO;

public class Rock extends InteractiveTile {

    GamePanel aGamePanel;

    public Rock(GamePanel pGamePanel, int pCol, int pRow) {
        super(pGamePanel, pCol, pRow);
        this.aGamePanel = pGamePanel;

        this.setWorldX(pCol * pGamePanel.getTileSize());
        this.setWorldY(pRow * pGamePanel.getTileSize());

        setLife(2);

        isDestructible = true;
        try {
            setImage(ImageIO.read(getClass().getResourceAsStream("/img/tile/decoration/tile6.png")));
            
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public boolean isCorrectItem(Entity pItem) {
        boolean vCorrectItem = false;

        if(pItem.getCurrentWeapon().getType() == getPickaxeType()){
            vCorrectItem = true;
        }

        return vCorrectItem;
    }

    public InteractiveTile getDestroyedForm() {
        InteractiveTile vDestroyedForm = null;
        return vDestroyedForm;
    }

}
