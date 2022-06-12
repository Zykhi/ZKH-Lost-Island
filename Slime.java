import java.awt.Rectangle;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Slime extends Entity {

    public Slime(GamePanel pGamePanel) {
        super(pGamePanel);

        setType(getMonsterType());
        setName("Slime");
        setSpeed(1);
        setMaxLife(4);
        setLife(getMaxLife());
        setAttack(5);
        setDefense(0);
        setExp(2);

        setProjectile(new RockProjectile(pGamePanel));

        setDirection("down");
        setSolidArea(new Rectangle(6, 23, 37, 25));
        setSolidAreaDefaultX(getSolidArea().x);
        setSolidAreaDefaultY(getSolidArea().y);

        setImage();
    }

    public void setImage() {
        try {
            setRight1(ImageIO.read(getClass().getResourceAsStream("/img/slime/walk/tile000.png")));
            setRight2(ImageIO.read(getClass().getResourceAsStream("/img/slime/walk/tile001.png")));
            setRight3(ImageIO.read(getClass().getResourceAsStream("/img/slime/walk/tile002.png")));
            setRight4(ImageIO.read(getClass().getResourceAsStream("/img/slime/walk/tile003.png")));
            setLeft1(ImageIO.read(getClass().getResourceAsStream("/img/slime/walk/tile000.png")));
            setLeft2(ImageIO.read(getClass().getResourceAsStream("/img/slime/walk/tile001.png")));
            setLeft3(ImageIO.read(getClass().getResourceAsStream("/img/slime/walk/tile002.png")));
            setLeft4(ImageIO.read(getClass().getResourceAsStream("/img/slime/walk/tile003.png")));
            setUp1(ImageIO.read(getClass().getResourceAsStream("/img/slime/walk/tile000.png")));
            setUp2(ImageIO.read(getClass().getResourceAsStream("/img/slime/walk/tile001.png")));
            setUp3(ImageIO.read(getClass().getResourceAsStream("/img/slime/walk/tile002.png")));
            setUp4(ImageIO.read(getClass().getResourceAsStream("/img/slime/walk/tile003.png")));
            setDown1(ImageIO.read(getClass().getResourceAsStream("/img/slime/walk/tile000.png")));
            setDown2(ImageIO.read(getClass().getResourceAsStream("/img/slime/walk/tile001.png")));
            setDown3(ImageIO.read(getClass().getResourceAsStream("/img/slime/walk/tile002.png")));
            setDown4(ImageIO.read(getClass().getResourceAsStream("/img/slime/walk/tile003.png")));

            setDeathLeft1(ImageIO.read(getClass().getResourceAsStream("/img/slime/dead/tile000.png")));
            setDeathLeft2(ImageIO.read(getClass().getResourceAsStream("/img/slime/dead/tile001.png")));
            setDeathLeft3(ImageIO.read(getClass().getResourceAsStream("/img/slime/dead/tile002.png")));
            setDeathLeft4(ImageIO.read(getClass().getResourceAsStream("/img/slime/dead/tile003.png")));
            setDeathRight1(ImageIO.read(getClass().getResourceAsStream("/img/slime/dead/tile000.png")));
            setDeathRight2(ImageIO.read(getClass().getResourceAsStream("/img/slime/dead/tile001.png")));
            setDeathRight3(ImageIO.read(getClass().getResourceAsStream("/img/slime/dead/tile002.png")));
            setDeathRight4(ImageIO.read(getClass().getResourceAsStream("/img/slime/dead/tile003.png")));
            setDeathUp1(ImageIO.read(getClass().getResourceAsStream("/img/slime/dead/tile000.png")));
            setDeathUp2(ImageIO.read(getClass().getResourceAsStream("/img/slime/dead/tile001.png")));
            setDeathUp3(ImageIO.read(getClass().getResourceAsStream("/img/slime/dead/tile002.png")));
            setDeathUp4(ImageIO.read(getClass().getResourceAsStream("/img/slime/dead/tile003.png")));
            setDeathDown1(ImageIO.read(getClass().getResourceAsStream("/img/slime/dead/tile000.png")));
            setDeathDown2(ImageIO.read(getClass().getResourceAsStream("/img/slime/dead/tile001.png")));
            setDeathDown3(ImageIO.read(getClass().getResourceAsStream("/img/slime/dead/tile002.png")));
            setDeathDown4(ImageIO.read(getClass().getResourceAsStream("/img/slime/dead/tile003.png")));

            setDamageLeft1(ImageIO.read(getClass().getResourceAsStream("/img/slime/damage/tile000.png")));
            setDamageLeft2(ImageIO.read(getClass().getResourceAsStream("/img/slime/damage/tile001.png")));
            setDamageRight1(ImageIO.read(getClass().getResourceAsStream("/img/slime/damage/tile000.png")));
            setDamageRight2(ImageIO.read(getClass().getResourceAsStream("/img/slime/damage/tile001.png")));
            setDamageUp1(ImageIO.read(getClass().getResourceAsStream("/img/slime/damage/tile000.png")));
            setDamageUp2(ImageIO.read(getClass().getResourceAsStream("/img/slime/damage/tile001.png")));
            setDamageDown1(ImageIO.read(getClass().getResourceAsStream("/img/slime/damage/tile000.png")));
            setDamageDown2(ImageIO.read(getClass().getResourceAsStream("/img/slime/damage/tile001.png")));

        } catch (IOException e) {
            e.printStackTrace();
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
        int i = new Random().nextInt(100)+1;
        if (i > 99 && getProjectile().isAlive() == false && getShotAvailableCounter() == 30){
            getProjectile().set(getWorldX(), getWorldY(), getDirection(), true, this);
            getGamePanel().getProjectileList().add(getProjectile());
            setShotAvailableCounter(0);
        }
    }

    public void damageReaction(){
        setActionLockCounter(0);
        setDirection("right");
    }

    public void checkDrop(){
        int vI = new Random().nextInt(100)+1;

        //set monster drop
        if(vI <50){
            dropItem(new ArrowAmmo(getGamePanel()));
        }
    }
    
}
