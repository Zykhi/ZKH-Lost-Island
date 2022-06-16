import javax.imageio.ImageIO;

public class NPCMerchant extends Entity {

    public NPCMerchant(GamePanel pGamePanel) {
        super(pGamePanel);
        setDirection("down");
        setSpeed(0);
        setImage();
        setDialogue();
        setItems();
    }

    public void setImage() {
        try {
            setDown1(ImageIO.read(getClass().getResourceAsStream("/img/npc/tile000.png")));
            setDown2(ImageIO.read(getClass().getResourceAsStream("/img/npc/tile001.png")));
            setDown3(ImageIO.read(getClass().getResourceAsStream("/img/npc/tile002.png")));
            setDown4(ImageIO.read(getClass().getResourceAsStream("/img/npc/tile003.png")));
            setLeft1(ImageIO.read(getClass().getResourceAsStream("/img/npc/tile004.png")));
            setLeft2(ImageIO.read(getClass().getResourceAsStream("/img/npc/tile005.png")));
            setLeft3(ImageIO.read(getClass().getResourceAsStream("/img/npc/tile006.png")));
            setLeft4(ImageIO.read(getClass().getResourceAsStream("/img/npc/tile007.png")));
            setRight1(ImageIO.read(getClass().getResourceAsStream("/img/npc/tile008.png")));
            setRight2(ImageIO.read(getClass().getResourceAsStream("/img/npc/tile009.png")));
            setRight3(ImageIO.read(getClass().getResourceAsStream("/img/npc/tile010.png")));
            setRight4(ImageIO.read(getClass().getResourceAsStream("/img/npc/tile011.png")));
            setUp1(ImageIO.read(getClass().getResourceAsStream("/img/npc/tile012.png")));
            setUp2(ImageIO.read(getClass().getResourceAsStream("/img/npc/tile013.png")));
            setUp3(ImageIO.read(getClass().getResourceAsStream("/img/npc/tile014.png")));
            setUp4(ImageIO.read(getClass().getResourceAsStream("/img/npc/tile015.png")));
        } catch (Exception e) {

        }
    }

    public void setDialogue() {
        setDialogue("Hello, I'm a Merchant", 0);
    }

    public void speak() {
        super.speak();
        getGamePanel().setGameState(getGamePanel().getTradeState());
        getGamePanel().getUserInterface().setNPC(this);
    }

    public void setItems(){
        getInventory().add(new Potion(getGamePanel()));
    }
}