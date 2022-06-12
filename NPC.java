import java.util.Random;

import javax.imageio.ImageIO;

public class NPC extends Entity {

    public NPC(GamePanel pGamePanel) {
        super(pGamePanel);
        setDirection("down");
        setSpeed(1);
        setImage();
        setDialogue();
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
        setDialogue("Hello, I'm a NPC", 0);
        setDialogue("I'm a NPC", 1);
        setDialogue("I'm not a player", 2);

    }

    public void speak() {
        super.speak();
    }

    public void setAction() {
        setActionLockCounter(getActionLockCounter()+1);
        if (getActionLockCounter() == 120) {
            Random vRandom = new Random();
            int vRandomNumber = vRandom.nextInt(100) + 1;

            if (vRandomNumber <= 25) {
                setDirection("up");
            }
            if (vRandomNumber > 25 && vRandomNumber <= 50) {
                setDirection("down");
            }
            if (vRandomNumber > 50 && vRandomNumber <= 75) {
                setDirection("left");
            }
            if (vRandomNumber > 75 && vRandomNumber <= 100) {
                setDirection("right");
            }

            setActionLockCounter(0);
        }
    }
}
