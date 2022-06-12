import java.awt.Rectangle;
import java.util.Random;

import javax.imageio.ImageIO;

public class Skeleton extends Entity{
    public Skeleton(GamePanel pGamePanel) {
        super(pGamePanel);

        setName("Skeleton");
        setSpeed(2);
        setMaxLife(6);
        setLife(getMaxLife());
        setAttack(3);
        setDefense(1);
        setExp(4);

        setImage();

        setDirection("down");
        setSolidArea(new Rectangle(8, 8, 32, 40));
        setSolidAreaDefaultX(getSolidArea().x);
        setSolidAreaDefaultY(getSolidArea().y);
    }

    public void setImage() {
        try {
            setDown1(ImageIO.read(getClass().getResourceAsStream("/img/skeleton/walk/tile000.png")));
            setDown2(ImageIO.read(getClass().getResourceAsStream("/img/skeleton/walk/tile001.png")));
            setDown3(ImageIO.read(getClass().getResourceAsStream("/img/skeleton/walk/tile002.png")));
            setDown4(ImageIO.read(getClass().getResourceAsStream("/img/skeleton/walk/tile003.png")));
            setLeft1(ImageIO.read(getClass().getResourceAsStream("/img/skeleton/walk/tile004.png")));
            setLeft2(ImageIO.read(getClass().getResourceAsStream("/img/skeleton/walk/tile005.png")));
            setLeft3(ImageIO.read(getClass().getResourceAsStream("/img/skeleton/walk/tile006.png")));
            setLeft4(ImageIO.read(getClass().getResourceAsStream("/img/skeleton/walk/tile007.png")));
            setRight1(ImageIO.read(getClass().getResourceAsStream("/img/skeleton/walk/tile008.png")));
            setRight2(ImageIO.read(getClass().getResourceAsStream("/img/skeleton/walk/tile009.png")));
            setRight3(ImageIO.read(getClass().getResourceAsStream("/img/skeleton/walk/tile010.png")));
            setRight4(ImageIO.read(getClass().getResourceAsStream("/img/skeleton/walk/tile011.png")));
            setUp1(ImageIO.read(getClass().getResourceAsStream("/img/skeleton/walk/tile012.png")));
            setUp2(ImageIO.read(getClass().getResourceAsStream("/img/skeleton/walk/tile013.png")));
            setUp3(ImageIO.read(getClass().getResourceAsStream("/img/skeleton/walk/tile014.png")));
            setUp4(ImageIO.read(getClass().getResourceAsStream("/img/skeleton/walk/tile015.png")));

            setDeathDown1(ImageIO.read(getClass().getResourceAsStream("/img/skeleton/dead/tile000.png")));
            setDeathDown2(ImageIO.read(getClass().getResourceAsStream("/img/skeleton/dead/tile001.png")));
            setDeathDown3(ImageIO.read(getClass().getResourceAsStream("/img/skeleton/dead/tile002.png")));
            setDeathDown4(ImageIO.read(getClass().getResourceAsStream("/img/skeleton/dead/tile003.png")));
            setDeathLeft1(ImageIO.read(getClass().getResourceAsStream("/img/skeleton/dead/tile004.png")));
            setDeathLeft2(ImageIO.read(getClass().getResourceAsStream("/img/skeleton/dead/tile005.png")));
            setDeathLeft3(ImageIO.read(getClass().getResourceAsStream("/img/skeleton/dead/tile006.png")));
            setDeathLeft4(ImageIO.read(getClass().getResourceAsStream("/img/skeleton/dead/tile007.png")));
            setDeathRight1(ImageIO.read(getClass().getResourceAsStream("/img/skeleton/dead/tile008.png")));
            setDeathRight2(ImageIO.read(getClass().getResourceAsStream("/img/skeleton/dead/tile009.png")));
            setDeathRight3(ImageIO.read(getClass().getResourceAsStream("/img/skeleton/dead/tile010.png")));
            setDeathRight4(ImageIO.read(getClass().getResourceAsStream("/img/skeleton/dead/tile011.png")));
            setDeathUp1(ImageIO.read(getClass().getResourceAsStream("/img/skeleton/dead/tile012.png")));
            setDeathUp2(ImageIO.read(getClass().getResourceAsStream("/img/skeleton/dead/tile013.png")));
            setDeathUp3(ImageIO.read(getClass().getResourceAsStream("/img/skeleton/dead/tile014.png")));
            setDeathUp4(ImageIO.read(getClass().getResourceAsStream("/img/skeleton/dead/tile015.png")));

            setDamageDown1(ImageIO.read(getClass().getResourceAsStream("/img/skeleton/damage/tile000.png")));
            setDamageDown2(ImageIO.read(getClass().getResourceAsStream("/img/skeleton/damage/tile001.png")));
            setDamageLeft1(ImageIO.read(getClass().getResourceAsStream("/img/skeleton/damage/tile002.png")));
            setDamageLeft2(ImageIO.read(getClass().getResourceAsStream("/img/skeleton/damage/tile003.png")));
            setDamageRight1(ImageIO.read(getClass().getResourceAsStream("/img/skeleton/damage/tile004.png")));
            setDamageRight2(ImageIO.read(getClass().getResourceAsStream("/img/skeleton/damage/tile005.png")));
            setDamageUp1(ImageIO.read(getClass().getResourceAsStream("/img/skeleton/damage/tile006.png")));
            setDamageUp2(ImageIO.read(getClass().getResourceAsStream("/img/skeleton/damage/tile007.png")));

        } catch (Exception e) {

        }
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
