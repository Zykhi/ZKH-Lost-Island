import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.AlphaComposite;

import javax.imageio.ImageIO;

public class Player extends Entity {

    private final int aScreenX;
    private final int aScreenY;
    private boolean aAttackCancel = false;
    private Input aInput;

    public Player(GamePanel pGamePanel, Input pInput) {
        super(pGamePanel);
        this.aInput = pInput;

        aScreenX = pGamePanel.getScreenWidth() / 2 - (pGamePanel.getTileSize() / 2);
        aScreenY = pGamePanel.getScreenHeight() / 2 - (pGamePanel.getTileSize() / 2);

        setSolidArea(new Rectangle());
        getSolidArea().x = 8;
        getSolidArea().y = 16;
        setSolidAreaDefaultX(getSolidArea().x);
        setSolidAreaDefaultY(getSolidArea().y);
        getSolidArea().width = 32;
        getSolidArea().height = 32;

        getAttackArea().width = 37;
        getAttackArea().height = 37;

        setDefault();
        getPlayerImage();
        getPlayerAttackImage();
        setItems();
    }

    public void setDefaultPosition() {
        this.setWorldX(getGamePanel().getTileSize() * 20);
        this.setWorldY(getGamePanel().getTileSize() * 7);
        this.setDirection("down");
    }

    public void restoreLifeAndAmmo() {
        setLife(getMaxLife());
        setAmmo(getMaxAmmo());
        setInvincible(false);
    }

    public void setDefault() {
        setDefaultPosition();
        this.setSpeed(4);
        // Player Status
        setLevel(1);
        setMaxLife(6);
        setMaxAmmo(10);
        setLife(getMaxLife());
        setAmmo(getMaxAmmo());
        setStrength(1);
        setDexterity(1);
        setExp(0);
        setExpToLevelUp(5);
        setMoney(0);
        setCurrentWeapon(new HandmadeSword(getGamePanel()));
        setCurrentShield(new WoodenShield(getGamePanel()));
        setProjectile(new Arrow(getGamePanel()));
        setAttack(getPlayerAttack());
        setDefense(getPlayerDefense());
        setInvincible(false);
    }

    public void setItems() {
        // reset inventory before starting game
        getInventory().clear();
        // add starting items to inventory
        getInventory().add(getCurrentWeapon());
        getInventory().add(getCurrentShield());
    }

    private int getPlayerAttack() {
        setAttackArea(getCurrentWeapon().getAttackArea());
        return getStrength() * getCurrentWeapon().getAttackValue();
    }

    private int getPlayerDefense() {
        return getDexterity() * getCurrentShield().getDefenseValue();
    }

    public void getPlayerImage() {
        try {
            setDown1(ImageIO.read(getClass().getResourceAsStream("/img/player/walk/tile000.png")));
            setDown2(ImageIO.read(getClass().getResourceAsStream("/img/player/walk/tile001.png")));
            setDown3(ImageIO.read(getClass().getResourceAsStream("/img/player/walk/tile002.png")));
            setDown4(ImageIO.read(getClass().getResourceAsStream("/img/player/walk/tile003.png")));
            setLeft1(ImageIO.read(getClass().getResourceAsStream("/img/player/walk/tile004.png")));
            setLeft2(ImageIO.read(getClass().getResourceAsStream("/img/player/walk/tile005.png")));
            setLeft3(ImageIO.read(getClass().getResourceAsStream("/img/player/walk/tile006.png")));
            setLeft4(ImageIO.read(getClass().getResourceAsStream("/img/player/walk/tile007.png")));
            setRight1(ImageIO.read(getClass().getResourceAsStream("/img/player/walk/tile008.png")));
            setRight2(ImageIO.read(getClass().getResourceAsStream("/img/player/walk/tile009.png")));
            setRight3(ImageIO.read(getClass().getResourceAsStream("/img/player/walk/tile010.png")));
            setRight4(ImageIO.read(getClass().getResourceAsStream("/img/player/walk/tile011.png")));
            setUp1(ImageIO.read(getClass().getResourceAsStream("/img/player/walk/tile012.png")));
            setUp2(ImageIO.read(getClass().getResourceAsStream("/img/player/walk/tile013.png")));
            setUp3(ImageIO.read(getClass().getResourceAsStream("/img/player/walk/tile014.png")));
            setUp4(ImageIO.read(getClass().getResourceAsStream("/img/player/walk/tile015.png")));
        } catch (Exception e) {

        }
    }

    public void getPlayerAttackImage() {
        try {
            if (getCurrentWeapon().getType() == getSwordType()) {
                setAttackLeft1(ImageIO.read(getClass().getResourceAsStream("/img/player/attackRightLeft/tile000.png")));
                setAttackLeft2(ImageIO.read(getClass().getResourceAsStream("/img/player/attackRightLeft/tile001.png")));
                setAttackLeft3(ImageIO.read(getClass().getResourceAsStream("/img/player/attackRightLeft/tile002.png")));
                setAttackLeft4(ImageIO.read(getClass().getResourceAsStream("/img/player/attackRightLeft/tile003.png")));
                setAttackRight1(
                        ImageIO.read(getClass().getResourceAsStream("/img/player/attackRightLeft/tile004.png")));
                setAttackRight2(
                        ImageIO.read(getClass().getResourceAsStream("/img/player/attackRightLeft/tile005.png")));
                setAttackRight3(
                        ImageIO.read(getClass().getResourceAsStream("/img/player/attackRightLeft/tile006.png")));
                setAttackRight4(
                        ImageIO.read(getClass().getResourceAsStream("/img/player/attackRightLeft/tile007.png")));
                setAttackDown1(ImageIO.read(getClass().getResourceAsStream("/img/player/attackUpDown/tile000.png")));
                setAttackDown2(ImageIO.read(getClass().getResourceAsStream("/img/player/attackUpDown/tile001.png")));
                setAttackDown3(ImageIO.read(getClass().getResourceAsStream("/img/player/attackUpDown/tile002.png")));
                setAttackDown4(ImageIO.read(getClass().getResourceAsStream("/img/player/attackUpDown/tile003.png")));
                setAttackUp1(ImageIO.read(getClass().getResourceAsStream("/img/player/attackUpDown/tile004.png")));
                setAttackUp2(ImageIO.read(getClass().getResourceAsStream("/img/player/attackUpDown/tile005.png")));
                setAttackUp3(ImageIO.read(getClass().getResourceAsStream("/img/player/attackUpDown/tile006.png")));
                setAttackUp4(ImageIO.read(getClass().getResourceAsStream("/img/player/attackUpDown/tile007.png")));
            }
            if (getCurrentWeapon().getType() == getBowType()) {
                setAttackLeft1(ImageIO.read(getClass().getResourceAsStream("/img/player/attackRightLeft/tile000.png")));
                setAttackLeft2(ImageIO.read(getClass().getResourceAsStream("/img/player/attackRightLeft/tile001.png")));
                setAttackLeft3(ImageIO.read(getClass().getResourceAsStream("/img/player/attackRightLeft/tile002.png")));
                setAttackLeft4(ImageIO.read(getClass().getResourceAsStream("/img/player/attackRightLeft/tile003.png")));
                setAttackRight1(
                        ImageIO.read(getClass().getResourceAsStream("/img/player/attackRightLeft/tile004.png")));
                setAttackRight2(
                        ImageIO.read(getClass().getResourceAsStream("/img/player/attackRightLeft/tile005.png")));
                setAttackRight3(
                        ImageIO.read(getClass().getResourceAsStream("/img/player/attackRightLeft/tile006.png")));
                setAttackRight4(
                        ImageIO.read(getClass().getResourceAsStream("/img/player/attackRightLeft/tile007.png")));
                setAttackDown1(ImageIO.read(getClass().getResourceAsStream("/img/player/attackUpDown/tile000.png")));
                setAttackDown2(ImageIO.read(getClass().getResourceAsStream("/img/player/attackUpDown/tile001.png")));
                setAttackDown3(ImageIO.read(getClass().getResourceAsStream("/img/player/attackUpDown/tile002.png")));
                setAttackDown4(ImageIO.read(getClass().getResourceAsStream("/img/player/attackUpDown/tile003.png")));
                setAttackUp1(ImageIO.read(getClass().getResourceAsStream("/img/player/attackUpDown/tile004.png")));
                setAttackUp2(ImageIO.read(getClass().getResourceAsStream("/img/player/attackUpDown/tile005.png")));
                setAttackUp3(ImageIO.read(getClass().getResourceAsStream("/img/player/attackUpDown/tile006.png")));
                setAttackUp4(ImageIO.read(getClass().getResourceAsStream("/img/player/attackUpDown/tile007.png")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void pickUpObject(int pIndex) {
        if (pIndex != 999) {

            // pickup only items
            if (getGamePanel().getItem(pIndex).getType() == getPickupOnlyType()) {
                getGamePanel().getItem(pIndex).use(this);
                getGamePanel().setItem(null, pIndex);
            } else {

                // Inventory items
                String vText;

                if (getInventory().size() != getMaxInventorySize()) {
                    getInventory().add(getGamePanel().getItem(pIndex));
                    vText = "You picked up " + getGamePanel().getItem(pIndex).getName();
                } else {
                    vText = "Your inventory is full";
                }
                getGamePanel().getUserInterface().addMessage(vText);
                getGamePanel().setItem(null, pIndex);
            }
        }
    }

    private void interactNPC(int pIndex) {
        if (getGamePanel().getInput().isEnter() == true) {

            if (pIndex != 999) {
                aAttackCancel = true;
                getGamePanel().setGameState(getGamePanel().getDialogueState());
                getGamePanel().getNPC(pIndex).speak();
            }
        }

    }

    private void contactMonster(int pIndex) {
        if (pIndex != 999) {
            if (getInvincible() == false && getGamePanel().getMonster(pIndex).isDying() == false) {
                int vDamage = getGamePanel().getMonster(pIndex).getAttack() - getDefense();
                if (vDamage < 0) {
                    vDamage = 0;
                }
                setLife(getLife() - vDamage);
                setInvincible(true);
            }
        }
    }

    public void damageMonster(int pIndex, int pAttack) {
        if (pIndex != 999) {
            if (getGamePanel().getMonster(pIndex).getInvincible() == false) {

                int vDamage = pAttack - getGamePanel().getMonster(pIndex).getDefense();
                if (vDamage < 0) {
                    vDamage = 0;
                }

                getGamePanel().getMonster(pIndex).setLife(getGamePanel().getMonster(pIndex).getLife() - vDamage);
                getGamePanel().getUserInterface().addMessage(vDamage + " damage!");

                getGamePanel().getMonster(pIndex).setInvincible(true);
                getGamePanel().getMonster(pIndex).damageReaction();

                if (getGamePanel().getMonster(pIndex).getLife() <= 0) {
                    getGamePanel().getMonster(pIndex).setDying(true);
                    getGamePanel().getUserInterface()
                            .addMessage("Killed the " + getGamePanel().getMonster(pIndex).getName() + "!");
                    getGamePanel().getUserInterface()
                            .addMessage("Exp +" + getGamePanel().getMonster(pIndex).getExp() + "!");
                    setExp(getExp() + getGamePanel().getMonster(pIndex).getExp());
                    checkLvlUp();
                }
            }
        }
    }

    private void damageInteractiveTile(int pIndex) {
        if (pIndex != 999 && getGamePanel().getInteractiveTile(pIndex).isDestructible == true
                && getGamePanel().getInteractiveTile(pIndex).isCorrectItem(this) == true
                && getGamePanel().getInteractiveTile(pIndex).getInvincible() == false) {

            getGamePanel().getInteractiveTile(pIndex).setLife(getGamePanel().getInteractiveTile(pIndex).getLife() - 1);
            getGamePanel().getInteractiveTile(pIndex).setInvincible(true);

            if (getGamePanel().getInteractiveTile(pIndex).getLife() <= 0) {
                getGamePanel().setInteractiveTile(getGamePanel().getInteractiveTile(pIndex).getDestroyedForm(), pIndex);
            }
        }
    }

    private void checkLvlUp() {
        if (getExp() >= getExpToNextLevel()) {
            setLevel(getLevel() + 1);
            setExp(0);
            setExpToLevelUp(getExpToNextLevel() * 2);
            setDexterity(getDexterity() + 1);
            setStrength(getStrength() + 1);
            setMaxLife(getMaxLife() + 2);
            setAttack(getPlayerAttack());
            setDefense(getPlayerDefense());

            getGamePanel().getUserInterface().addMessage("Level Up!");

            getGamePanel().setGameState(getGamePanel().getDialogueState());
            getGamePanel().getUserInterface().setCurrentDialogue("You are now level " + getLevel() + "!");
        }
    }

    public void selectItem() {
        int vItemIndex = getGamePanel().getUserInterface().getItemIndexOnSlot(
                getGamePanel().getUserInterface().getSlotCol(), getGamePanel().getUserInterface().getSlotRow());

        if (vItemIndex < getInventory().size()) {
            Entity vSelectedItem = getInventory().get(vItemIndex);

            if (vSelectedItem.getType() == getSwordType() || vSelectedItem.getType() == getAxeType()
                    || vSelectedItem.getType() == getBowType() || vSelectedItem.getType() == getPickaxeType()) {
                setCurrentWeapon(vSelectedItem);
                setAttack(getPlayerAttack());
                getPlayerAttackImage();
            }
            if (vSelectedItem.getType() == getShieldType()) {
                setCurrentShield(vSelectedItem);
                setDefense(getPlayerDefense());
            }
            if (vSelectedItem.getType() == getConsumableType()) {
                vSelectedItem.use(this);
                getInventory().remove(vItemIndex);
            }
        }
    }

    private void attacking() {
        setSpriteCounter(getSpriteCounter() + 1);
        if (getSpriteCounter() < 10) {
            setSpriteNum(1);
        }
        if (getSpriteCounter() >= 10 && getSpriteCounter() < 15) {
            setSpriteNum(2);

            // Save current data
            int vCurrentWorldX = getWorldX();
            int vCurrentWorldY = getWorldY();
            int vSolidAreaWidth = getSolidArea().width;
            int vSolidAreaHeight = getSolidArea().height;

            // adjust data for attack
            switch (getDirection()) {
                case "up":
                    setWorldX(getWorldX() - getAttackArea().width);
                    break;
                case "down":
                    setWorldX(getWorldX() + getAttackArea().width);
                    break;
                case "right":
                    setWorldX(getWorldX() + getAttackArea().width);
                    break;
                case "left":
                    setWorldX(getWorldX() - getAttackArea().width);
                    break;
            }
            // attack area become solidArea
            getSolidArea().width = getAttackArea().width;
            getSolidArea().height = getAttackArea().height;
            // check monster collision
            int vMonsterIndex = getGamePanel().getCollisionChecker().checkEntity(this, getGamePanel().getMonster());
            damageMonster(vMonsterIndex, getPlayerAttack());

            int vInteractiveTileIndex = getGamePanel().getCollisionChecker().checkEntity(this,
                    getGamePanel().getInteractiveTile());
            damageInteractiveTile(vInteractiveTileIndex);

            // reset
            setWorldX(vCurrentWorldX);
            setWorldY(vCurrentWorldY);
            getSolidArea().width = vSolidAreaWidth;
            getSolidArea().height = vSolidAreaHeight;
        }
        if (getSpriteCounter() >= 15 && getSpriteCounter() < 25) {
            setSpriteNum(3);
        }
        if (getSpriteCounter() >= 25 && getSpriteCounter() < 35) {
            setSpriteNum(4);
        }
        if (getSpriteCounter() > 35) {
            setSpriteNum(1);
            setSpriteCounter(0);
            isAttacking(false);
        }
    }

    public void update() {

        if (isAttack() == true) {
            attacking();
        } else if (aInput.isUp() == true || aInput.isDown() == true || aInput.isLeft() == true
                || aInput.isRight() == true
                || aInput.isEnter() == true) {
            if (aInput.isUp()) {
                this.setDirection("up");
            }
            if (aInput.isDown()) {
                this.setDirection("down");
            }
            if (aInput.isLeft()) {
                this.setDirection("left");
            }
            if (aInput.isRight()) {
                this.setDirection("right");
            }

            // Check Tile Collision
            setCollision(false);
            getGamePanel().getCollisionChecker().checkTile(this);

            // Check Object Collision
            int vObjectIndex = getGamePanel().getCollisionChecker().checkObject(this, true);
            pickUpObject(vObjectIndex);

            // Check NPC Collision
            int vNPCIndex = getGamePanel().getCollisionChecker().checkEntity(this, getGamePanel().getNPC());
            interactNPC(vNPCIndex);

            // Check Monster Collision
            int vMonsterIndex = getGamePanel().getCollisionChecker().checkEntity(this, getGamePanel().getMonster());
            contactMonster(vMonsterIndex);

            // Check interactive tile collision
            int vInteractiveTileIndex = getGamePanel().getCollisionChecker().checkEntity(this,
                    getGamePanel().getInteractiveTile());

            // Check event
            getGamePanel().getEventHandler().checkEvent();

            // If no collision
            if (getCollision() == false && aInput.isEnter() == false) {
                switch (getDirection()) {
                    case "up":
                        setWorldY(getWorldY() - getSpeed());
                        break;
                    case "down":
                        setWorldY(getWorldY() + getSpeed());
                        break;
                    case "left":
                        setWorldX(getWorldX() - getSpeed());
                        break;
                    case "right":
                        setWorldX(getWorldX() + getSpeed());
                        break;
                }
            }

            if (aInput.isEnter() == true && aAttackCancel == false) {
                isAttacking(true);
                setSpriteCounter(0);
            }

            aAttackCancel = false;
            getGamePanel().getInput().setEnter(false);

            setSpriteCounter(getSpriteCounter() + 1);
            if (getSpriteCounter() > 10) {
                if (getSpriteNum() == 1) {
                    setSpriteNum(2);
                } else if (getSpriteNum() == 2) {
                    setSpriteNum(3);
                } else if (getSpriteNum() == 3) {
                    setSpriteNum(4);
                } else if (getSpriteNum() == 4) {
                    setSpriteNum(1);
                }
                setSpriteCounter(0);
            }
        }

        // if you got a bow
        if (getCurrentWeapon().getType() == getBowType()) {
            if (getGamePanel().getInput().isShot() == true && getProjectile().isAlive() == false
                    && getShotAvailableCounter() == 30 && getProjectile().haveResource(this) == true) {
                getProjectile().set(getWorldX(), getWorldY(), getDirection(), true, this);

                getProjectile().substractResource(this);

                getGamePanel().getProjectileList().add(getProjectile());

                setShotAvailableCounter(0);
            }
            if (getShotAvailableCounter() < 30) {
                setShotAvailableCounter(getShotAvailableCounter() + 1);
            }
        }

        if (getInvincible() == true) {
            setInvincibleCounter(getInvincibleCounter() + 1);
            if (getInvincibleCounter() > 60) {
                setInvincible(false);
                setInvincibleCounter(0);
            }
        }

        if (getLife() > getMaxLife()) {
            setLife(getMaxLife());
        }

        if (getAmmo() > getMaxAmmo()) {
            setAmmo(getMaxAmmo());
        }

        if (getLife() <= 0) {
            getGamePanel().setGameState(getGamePanel().getGameOverState());
            getGamePanel().getUserInterface().setCommandNumber(-1);
        }

    }

    public void draw(Graphics2D pGraphics) {

        BufferedImage vImage = null;

        switch (getDirection()) {
            case "up":
                if (isAttack() == false) {
                    if (getSpriteNum() == 1) {
                        vImage = getUp1();
                    }
                    if (getSpriteNum() == 2) {
                        vImage = getUp2();
                    }
                    if (getSpriteNum() == 3) {
                        vImage = getUp3();
                    }
                    if (getSpriteNum() == 4) {
                        vImage = getUp4();
                    }
                }
                if (isAttack() == true) {
                    if (getSpriteNum() == 1) {
                        vImage = getAttackUp1();
                    }
                    if (getSpriteNum() == 2) {
                        vImage = getAttackUp2();
                    }
                    if (getSpriteNum() == 3) {
                        vImage = getAttackUp3();
                    }
                    if (getSpriteNum() == 4) {
                        vImage = getAttackUp4();
                    }
                }
                break;
            case "down":
                if (isAttack() == false) {
                    if (getSpriteNum() == 1) {
                        vImage = getDown1();
                    }
                    if (getSpriteNum() == 2) {
                        vImage = getDown2();
                    }
                    if (getSpriteNum() == 3) {
                        vImage = getDown3();
                    }
                    if (getSpriteNum() == 4) {
                        vImage = getDown4();
                    }
                }
                if (isAttack() == true) {
                    if (getSpriteNum() == 1) {
                        vImage = getAttackDown1();
                    }
                    if (getSpriteNum() == 2) {
                        vImage = getAttackDown2();
                    }
                    if (getSpriteNum() == 3) {
                        vImage = getAttackDown3();
                    }
                    if (getSpriteNum() == 4) {
                        vImage = getAttackDown4();
                    }
                }
                break;
            case "left":
                if (isAttack() == false) {
                    if (getSpriteNum() == 1) {
                        vImage = getLeft1();
                    }
                    if (getSpriteNum() == 2) {
                        vImage = getLeft2();
                    }
                    if (getSpriteNum() == 3) {
                        vImage = getLeft3();
                    }
                    if (getSpriteNum() == 4) {
                        vImage = getLeft4();
                    }
                }
                if (isAttack() == true) {
                    if (getSpriteNum() == 1) {
                        vImage = getAttackLeft1();
                    }
                    if (getSpriteNum() == 2) {
                        vImage = getAttackLeft2();
                    }
                    if (getSpriteNum() == 3) {
                        vImage = getAttackLeft3();
                    }
                    if (getSpriteNum() == 4) {
                        vImage = getAttackLeft4();
                    }
                }
                break;
            case "right":
                if (isAttack() == false) {
                    if (getSpriteNum() == 1) {
                        vImage = getRight1();
                    }
                    if (getSpriteNum() == 2) {
                        vImage = getRight2();
                    }
                    if (getSpriteNum() == 3) {
                        vImage = getRight3();
                    }
                    if (getSpriteNum() == 4) {
                        vImage = getRight4();
                    }
                }
                if (isAttack() == true) {
                    if (getSpriteNum() == 1) {
                        vImage = getAttackRight1();
                    }
                    if (getSpriteNum() == 2) {
                        vImage = getAttackRight2();
                    }
                    if (getSpriteNum() == 3) {
                        vImage = getAttackRight3();
                    }
                    if (getSpriteNum() == 4) {
                        vImage = getAttackRight4();
                    }
                }
                break;
        }

        if (getInvincible() == true) {
            pGraphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }

        pGraphics.drawImage(vImage, aScreenX, aScreenY, getGamePanel().getTileSize(), getGamePanel().getTileSize(),
                null);

        pGraphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }

    public int getScreenX() {
        return aScreenX;
    }

    public int getScreenY() {
        return aScreenY;
    }

    public boolean isAttackCancel() {
        return aAttackCancel;
    }

    public void setAttackCancel(boolean pAttackCancel) {
        aAttackCancel = pAttackCancel;
    }
}
