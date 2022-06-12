import java.awt.image.BufferedImage;

import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.awt.AlphaComposite;
import java.awt.Color;

public class Entity {
    private int aWorldX;
    private int aWorldY;

    // Image
    // Movement
    private BufferedImage aRight1;
    private BufferedImage aRight2;
    private BufferedImage aRight3;
    private BufferedImage aRight4;
    private BufferedImage aLeft1;
    private BufferedImage aLeft2;
    private BufferedImage aLeft3;
    private BufferedImage aLeft4;
    private BufferedImage aDown1;
    private BufferedImage aDown2;
    private BufferedImage aDown3;
    private BufferedImage aDown4;
    private BufferedImage aUp1;
    private BufferedImage aUp2;
    private BufferedImage aUp3;
    private BufferedImage aUp4;

    // attack
    private BufferedImage aAttackUp1;
    private BufferedImage aAttackUp2;
    private BufferedImage aAttackUp3;
    private BufferedImage aAttackUp4;
    private BufferedImage aAttackDown1;
    private BufferedImage aAttackDown2;
    private BufferedImage aAttackDown3;
    private BufferedImage aAttackDown4;
    private BufferedImage aAttackRight1;
    private BufferedImage aAttackRight2;
    private BufferedImage aAttackRight3;
    private BufferedImage aAttackRight4;
    private BufferedImage aAttackLeft1;
    private BufferedImage aAttackLeft2;
    private BufferedImage aAttackLeft3;
    private BufferedImage aAttackLeft4;

    // death
    private BufferedImage aDeathRight1;
    private BufferedImage aDeathRight2;
    private BufferedImage aDeathRight3;
    private BufferedImage aDeathRight4;
    private BufferedImage aDeathLeft1;
    private BufferedImage aDeathLeft2;
    private BufferedImage aDeathLeft3;
    private BufferedImage aDeathLeft4;
    private BufferedImage aDeathDown1;
    private BufferedImage aDeathDown2;
    private BufferedImage aDeathDown3;
    private BufferedImage aDeathDown4;
    private BufferedImage aDeathUp1;
    private BufferedImage aDeathUp2;
    private BufferedImage aDeathUp3;
    private BufferedImage aDeathUp4;

    // damage
    private BufferedImage aDamageRight1;
    private BufferedImage aDamageRight2;
    private BufferedImage aDamageLeft1;
    private BufferedImage aDamageLeft2;
    private BufferedImage aDamageDown1;
    private BufferedImage aDamageDown2;
    private BufferedImage aDamageUp1;
    private BufferedImage aDamageUp2;

    // Item Image
    private BufferedImage aImage;
    private BufferedImage aImage2;
    private BufferedImage aImage3;

    private String aDirection = "idle";

    private int aSpriteNum = 1;
    private int aSolidAreaDefaultX;
    private int aSolidAreaDefaultY;

    private Rectangle aSolidArea = new Rectangle(0, 0, 48, 48);
    private Rectangle aAttackArea = new Rectangle(0, 0, 0, 0);
    private boolean aCollisionOn = false;
    private GamePanel aGamePanel;
    private String aDialogues[] = new String[10];
    private int aDialogueIndex = 0;

    private UtilityTool aUtilityTool = new UtilityTool();

    private boolean aCollision = false;

    private boolean aInvincible = false;

    private boolean aAttacking = false;

    private boolean HPBarOn = false;

    private boolean aAlive = true;
    private boolean aDying = false;

    private String aLastDirection;

    // Counter
    private int aSpriteCounter = 0;
    private int aActionLockCounter = 0;
    private int aInvincibleCounter = 0;
    private int aShotAvailableCounter = 0;
    private int aDyingCounter = 0;
    private int aDamageCounter = 0;
    private int HPBarCounter = 0;

    // Character Status
    private String aName;
    private int aSpeed;
    private int aMaxLife;
    private int aLife;
    private int aLevel;
    private int aStrength;
    private int aDexterity;
    private int aAttack;
    private int aDefense;
    private int aExp;
    private int aExpNeededToLevelUp;
    private int aMoney;
    private int aMaxAmmo;
    private int aAmmo;
    private Entity aCurrentWeapon;
    private Entity aCurrentShield;
    private Projectile aProjectile;

    // Item Attributes
    private int aValue;
    private int aAttackValue;
    private int aDefenseValue;
    private String aDescription = "[Default Description]";
    private int aUseCost;

    // Type
    private int aType; // 0 = Player, 1 = NPC, 2 = Monster
    private final int aPlayerType = 0;
    private final int aNPCType = 1;
    private final int aMonsterType = 2;
    private final int aSwordType = 3;
    private final int aAxeType = 4;
    private final int aShieldType = 5;
    private final int aConsumableType = 6;
    private final int aBowType = 7;
    private final int aPickupOnlyType = 8;
    private final int aPickaxeType = 9;

    public Entity(GamePanel pGamePanel) {
        aGamePanel = pGamePanel;
    }

    public void setAction() {

    }

    public void damageReaction() {

    }

    public void use(Entity pEntity) {

    }

    public void damagePlayer(int pAttack) {
        if (aGamePanel.getPlayer().getInvincible() == false) {
            int vDamage = pAttack - aGamePanel.getPlayer().getDefense();
            if (vDamage < 0) {
                vDamage = 0;
            }
            aGamePanel.getPlayer().setLife(aGamePanel.getPlayer().getLife() - vDamage);
            aGamePanel.getPlayer().setInvincible(true);
        }
    }

    public void speak() {
        if (aDialogues[aDialogueIndex] == null) {
            aDialogueIndex = 0;
        }
        aGamePanel.getUserInterface().setCurrentDialogue(aDialogues[aDialogueIndex]);
        aDialogueIndex++;
        switch (aGamePanel.getPlayer().getDirection()) {
            case "up":
                aDirection = "down";
                break;
            case "down":
                aDirection = "up";
                break;
            case "right":
                aDirection = "left";
                break;
            case "left":
                aDirection = "right";
                break;
        }
    }

    public void checkDrop(){

    }

    public void dropItem(Entity pDroppedItem){
        for (int i=0; i<aGamePanel.getItems().length; i++){
            if (aGamePanel.getItems()[i] == null){
                aGamePanel.getItems()[i] = pDroppedItem;
                aGamePanel.getItems()[i].setWorldX(getWorldX());
                aGamePanel.getItems()[i].setWorldY(getWorldY());
                break;
            }
        }
    }

    private void dyingAnimation(Graphics2D pGraphics) {
        BufferedImage vImage = null;
        resetMovementImage();
        resetDamageImage();
        int vScreenX = aWorldX - aGamePanel.getPlayer().getWorldX() + aGamePanel.getPlayer().getScreenX();
        int vScreenY = aWorldY - aGamePanel.getPlayer().getWorldY() + aGamePanel.getPlayer().getScreenY();

        aDyingCounter++;
        if (aDyingCounter < 10) {
            if(aLastDirection == "right"){
                vImage = aDeathRight1;
            }
            if(aLastDirection == "left"){
                vImage = aDeathLeft1;
            }
            if(aLastDirection == "up"){
                vImage = aDeathUp1;
            }
            if(aLastDirection == "down"){
                vImage = aDeathDown1;
            }
        }
        if (aDyingCounter >= 10 && aDyingCounter < 15) {
            if(aLastDirection == "right"){
                vImage = aDeathRight2;
            }
            if(aLastDirection == "left"){
                vImage = aDeathLeft2;
            }
            if(aLastDirection == "up"){
                vImage = aDeathUp2;
            }
            if(aLastDirection == "down"){
                vImage = aDeathDown2;
            }
        }
        if (aDyingCounter >= 15 && aDyingCounter < 20) {
            if(aLastDirection == "right"){
                vImage = aDeathRight3;
            }
            if(aLastDirection == "left"){
                vImage = aDeathLeft3;
            }
            if(aLastDirection == "up"){
                vImage = aDeathUp3;
            }
            if(aLastDirection == "down"){
                vImage = aDeathDown3;
            }
        }
        if (aDyingCounter >= 20 && aDyingCounter < 25) {
            if(aLastDirection == "right"){
                vImage = aDeathRight4;
            }
            if(aLastDirection == "left"){
                vImage = aDeathLeft4;
            }
            if(aLastDirection == "up"){
                vImage = aDeathUp4;
            }
            if(aLastDirection == "down"){
                vImage = aDeathDown4;
            }
        }
        if (aDyingCounter >= 25) {
            aAlive = false;
        }
        pGraphics.drawImage(vImage, vScreenX, vScreenY, aGamePanel.getTileSize(),
                aGamePanel.getTileSize(), null);
    }

    private void damageAnimation(Graphics2D pGraphics) {
        BufferedImage vImage = null;
        int vScreenX = aWorldX - aGamePanel.getPlayer().getWorldX() + aGamePanel.getPlayer().getScreenX();
        int vScreenY = aWorldY - aGamePanel.getPlayer().getWorldY() + aGamePanel.getPlayer().getScreenY();

        aDamageCounter++;

        if (aDamageCounter < 20) {
            if(aLastDirection == "right"){
                vImage = aDamageRight1;
            }
            else if(aLastDirection == "left"){
                vImage = aDamageLeft1;
            }
            else if(aLastDirection == "down"){
                vImage = aDamageDown1;
            }
            else if(aLastDirection == "up"){
                vImage = aDamageUp1;
            }
        }
        if (aDamageCounter >= 20 && aDamageCounter < 40) {
            if(aLastDirection == "right"){
                vImage = aDamageRight2;
            }
            else if(aLastDirection == "left"){
                vImage = aDamageLeft2;
            }
            else if(aLastDirection == "down"){
                vImage = aDamageDown2;
            }
            else if(aLastDirection == "up"){
                vImage = aDamageUp2;
            }
        }
        aDamageCounter = 0;
        pGraphics.drawImage(vImage, vScreenX, vScreenY, aGamePanel.getTileSize(),
                aGamePanel.getTileSize(), null);
    }

    public void update() {
        setAction();

        aCollisionOn = false;
        aGamePanel.getCollisionChecker().checkTile(this);
        aGamePanel.getCollisionChecker().checkObject(this, false);
        aGamePanel.getCollisionChecker().checkEntity(this, aGamePanel.getNPC());
        aGamePanel.getCollisionChecker().checkEntity(this, aGamePanel.getMonster());
        aGamePanel.getCollisionChecker().checkEntity(this, aGamePanel.getInteractiveTile());

        boolean vContactPlayer = aGamePanel.getCollisionChecker().checkPlayer(this);

        if (this.aType == aMonsterType && vContactPlayer == true) {
            damagePlayer(aAttack);
        }

        if (aCollisionOn == false) {
            switch (aDirection) {
                case "up":
                    aLastDirection = "up";
                    aWorldY -= aSpeed;
                    break;
                case "down":
                    aLastDirection = "down";
                    aWorldY -= aSpeed;
                    break;
                case "left":
                    aLastDirection = "left";
                    aWorldX -= aSpeed;
                    break;
                case "right":
                    aLastDirection = "right";
                    aWorldX += aSpeed;
                    break;
            }
        }

        aSpriteCounter++;
        if (aSpriteCounter > 10) {
            if (aSpriteNum == 1) {
                aSpriteNum = 2;
            } else if (aSpriteNum == 2) {
                aSpriteNum = 3;
            } else if (aSpriteNum == 3) {
                aSpriteNum = 4;
            } else if (aSpriteNum == 4) {
                aSpriteNum = 1;
            }
            aSpriteCounter = 0;
        }

        if (getInvincible() == true) {
            setInvincibleCounter(getInvincibleCounter() + 1);
            if (getInvincibleCounter() > 40) {
                setInvincible(false);
                setInvincibleCounter(0);
            }
        }

        if (getShotAvailableCounter() < 30) {
            setShotAvailableCounter(getShotAvailableCounter() + 1);
        }
    }

    public void draw(Graphics2D pGraphics) {
        int vScreenX = aWorldX - aGamePanel.getPlayer().getWorldX() + aGamePanel.getPlayer().getScreenX();
        int vScreenY = aWorldY - aGamePanel.getPlayer().getWorldY() + aGamePanel.getPlayer().getScreenY();
        BufferedImage vImage = null;

        if (aWorldX + aGamePanel.getTileSize() > aGamePanel.getPlayer().getWorldX()
                - aGamePanel.getPlayer().getScreenX() &&
                aWorldX - aGamePanel.getTileSize() < aGamePanel.getPlayer().getWorldX()
                        + aGamePanel.getPlayer().getScreenX()
                &&
                aWorldY + aGamePanel.getTileSize() > aGamePanel.getPlayer().getWorldY()
                        - aGamePanel.getPlayer().getScreenY()
                &&
                aWorldY - aGamePanel.getTileSize() < aGamePanel.getPlayer().getWorldY()
                        + aGamePanel.getPlayer().getScreenY()) {

            switch (aDirection) {
                case "up":
                    if (aSpriteNum == 1) {
                        vImage = aUp1;
                    }
                    if (aSpriteNum == 2) {
                        vImage = aUp2;
                    }
                    if (aSpriteNum == 3) {
                        vImage = aUp3;
                    }
                    if (aSpriteNum == 4) {
                        vImage = aUp4;
                    }
                    break;
                case "down":
                    if (aSpriteNum == 1) {
                        vImage = aDown1;
                    }
                    if (aSpriteNum == 2) {
                        vImage = aDown2;
                    }
                    if (aSpriteNum == 3) {
                        vImage = aDown3;
                    }
                    if (aSpriteNum == 4) {
                        vImage = aDown4;
                    }
                    break;
                case "left":
                    if (aSpriteNum == 1) {
                        vImage = aLeft1;
                    }
                    if (aSpriteNum == 2) {
                        vImage = aLeft2;
                    }
                    if (aSpriteNum == 3) {
                        vImage = aLeft3;
                    }
                    if (aSpriteNum == 4) {
                        vImage = aLeft4;
                    }
                    break;
                case "right":
                    if (aSpriteNum == 1) {
                        vImage = aRight1;
                    }
                    if (aSpriteNum == 2) {
                        vImage = aRight2;
                    }
                    if (aSpriteNum == 3) {
                        vImage = aRight3;
                    }
                    if (aSpriteNum == 4) {
                        vImage = aRight4;
                    }
                    break;
                case "idle":
                    if (aSpriteNum == 1) {
                        vImage = aImage;
                    }
                    if (aSpriteNum == 2) {
                        vImage = aImage;
                    }
                    if (aSpriteNum == 3) {
                        vImage = aImage;
                    }
                    if (aSpriteNum == 4) {
                        vImage = aImage;
                    }
                    break;
            }
            // MonsterHP Bar
            if (aType == 2 && HPBarOn == true) {

                double vOneScale = (double) aGamePanel.getTileSize() / aMaxLife;
                double vHPBarValue = vOneScale * aLife;

                pGraphics.setColor(new Color(35, 35, 35));
                pGraphics.fillRect(vScreenX - 1, vScreenY - 16,
                        aGamePanel.getTileSize() + 2, 12);
                pGraphics.setColor(new Color(255, 0, 30));
                pGraphics.fillRect(vScreenX, vScreenY - 15, (int) vHPBarValue,
                        10);

                HPBarCounter++;

                if (HPBarCounter > 300) {
                    HPBarCounter = 0;
                    HPBarOn = false;
                }

            }
            if (getInvincible() == true) {
                HPBarOn = true;
                HPBarCounter = 0;
                damageAnimation(pGraphics);
                setAlpha(pGraphics, 0f);
            }
            if (isDying() == true) {
                setAlpha(pGraphics, 1f);
                dyingAnimation(pGraphics);
            }
            pGraphics.drawImage(vImage, vScreenX, vScreenY, aGamePanel.getTileSize(),
                    aGamePanel.getTileSize(), null);
            setAlpha(pGraphics, 1f);
        }
    }

    private void setAlpha(Graphics2D pGraphics, float pAlpha) {
        pGraphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, pAlpha));
    }

    private void resetMovementImage() {
        aRight1 = null;
        aRight2 = null;
        aRight3 = null;
        aRight4 = null;
        aLeft1 = null;
        aLeft2 = null;
        aLeft3 = null;
        aLeft4 = null;
        aUp1 = null;
        aUp2 = null;
        aUp3 = null;
        aUp4 = null;
        aDown1 = null;
        aDown2 = null;
        aDown3 = null;
        aDown4 = null;
    }

    private void resetDamageImage() {
        aDamageRight1 = null;
        aDamageRight2 = null;
        aDamageLeft1 = null;
        aDamageLeft2 = null;
        aDamageUp1 = null;
        aDamageUp2 = null;
        aDamageDown1 = null;
        aDamageDown2 = null;
    }

    public int getWorldX() {
        return aWorldX;
    }

    public int getWorldY() {
        return aWorldY;
    }

    public int getMaxLife() {
        return aMaxLife;
    }

    public int getLife() {
        return aLife;
    }

    public int getSpeed() {
        return aSpeed;
    }

    public int getSolidAreaDefaultX() {
        return aSolidAreaDefaultX;
    }

    public int getSolidAreaDefaultY() {
        return aSolidAreaDefaultY;
    }

    public int getActionLockCounter() {
        return aActionLockCounter;
    }

    public int getInvincibleCounter() {
        return aInvincibleCounter;
    }

    public int getShotAvailableCounter() {
        return aShotAvailableCounter;
    }

    public int getSpriteCounter() {
        return aSpriteCounter;
    }

    public int getDyingCounter() {
        return aDyingCounter;
    }

    public int getSpriteNum() {
        return aSpriteNum;
    }

    public int getType() {
        return aType;
    }

    public int getLevel() {
        return aLevel;
    }

    public int getStrength() {
        return aStrength;
    }

    public int getDexterity() {
        return aDexterity;
    }

    public int getAttack() {
        return aAttack;
    }

    public int getValue() {
        return aValue;
    }

    public int getDefense() {
        return aDefense;
    }

    public int getExp() {
        return aExp;
    }

    public int getExpToNextLevel() {
        return aExpNeededToLevelUp;
    }

    public int getMoney() {
        return aMoney;
    }

    public int getAttackValue() {
        return aAttackValue;
    }

    public int getDefenseValue() {
        return aDefenseValue;
    }

    public int getPlayerType() {
        return aPlayerType;
    }

    public int getNPCType() {
        return aNPCType;
    }

    public int getMonsterType() {
        return aMonsterType;
    }

    public int getSwordType() {
        return aSwordType;
    }

    public int getAxeType() {
        return aAxeType;
    }

    public int getShieldType() {
        return aShieldType;
    }

    public int getConsumableType() {
        return aConsumableType;
    }

    public int getPickupOnlyType() {
        return aPickupOnlyType;
    }

    public int getPickaxeType() {
        return aPickaxeType;
    }

    public int getBowType() {
        return aBowType;
    }

    public int getAmmo() {
        return aAmmo;
    }

    public int getMaxAmmo() {
        return aMaxAmmo;
    }

    public int getUseCost() {
        return aUseCost;
    }

    public boolean getItemCollision() {
        return aCollision;
    }

    public boolean getInvincible() {
        return aInvincible;
    }

    public boolean isAttack() {
        return aAttacking;
    }

    public boolean isAlive() {
        return aAlive;
    }

    public boolean isDying() {
        return aDying;
    }

    public String getName() {
        return aName;
    }

    public String getDirection() {
        return aDirection;
    }

    public String getDescription() {
        return aDescription;
    }

    public GamePanel getGamePanel() {
        return aGamePanel;
    }

    public Entity getCurrentWeapon() {
        return aCurrentWeapon;
    }

    public Entity getCurrentShield() {
        return aCurrentShield;
    }

    public Projectile getProjectile() {
        return aProjectile;
    }

    public Rectangle getSolidArea() {
        return aSolidArea;
    }

    public Rectangle getAttackArea() {
        return aAttackArea;
    }

    public BufferedImage getUp1() {
        return aUp1;
    }

    public BufferedImage getUp2() {
        return aUp2;
    }

    public BufferedImage getUp3() {
        return aUp3;
    }

    public BufferedImage getUp4() {
        return aUp4;
    }

    public BufferedImage getDown1() {
        return aDown1;
    }

    public BufferedImage getDown2() {
        return aDown2;
    }

    public BufferedImage getDown3() {
        return aDown3;
    }

    public BufferedImage getDown4() {
        return aDown4;
    }

    public BufferedImage getRight1() {
        return aRight1;
    }

    public BufferedImage getRight2() {
        return aRight2;
    }

    public BufferedImage getRight3() {
        return aRight3;
    }

    public BufferedImage getRight4() {
        return aRight4;
    }

    public BufferedImage getLeft1() {
        return aLeft1;
    }

    public BufferedImage getLeft2() {
        return aLeft2;
    }

    public BufferedImage getLeft3() {
        return aLeft3;
    }

    public BufferedImage getLeft4() {
        return aLeft4;
    }

    public BufferedImage getAttackRight1() {
        return aAttackRight1;
    }

    public BufferedImage getAttackRight2() {
        return aAttackRight2;
    }

    public BufferedImage getAttackRight3() {
        return aAttackRight3;
    }

    public BufferedImage getAttackRight4() {
        return aAttackRight4;
    }

    public BufferedImage getAttackLeft1() {
        return aAttackLeft1;
    }

    public BufferedImage getAttackLeft2() {
        return aAttackLeft2;
    }

    public BufferedImage getAttackLeft3() {
        return aAttackLeft3;
    }

    public BufferedImage getAttackLeft4() {
        return aAttackLeft4;
    }

    public BufferedImage getAttackUp1() {
        return aAttackUp1;
    }

    public BufferedImage getAttackUp2() {
        return aAttackUp2;
    }

    public BufferedImage getAttackUp3() {
        return aAttackUp3;
    }

    public BufferedImage getAttackUp4() {
        return aAttackUp4;
    }

    public BufferedImage getAttackDown1() {
        return aAttackDown1;
    }

    public BufferedImage getAttackDown2() {
        return aAttackDown2;
    }

    public BufferedImage getAttackDown3() {
        return aAttackDown3;
    }

    public BufferedImage getAttackDown4() {
        return aAttackDown4;
    }

    public BufferedImage getDeathRight1() {
        return aDeathRight1;
    }

    public BufferedImage getDeathRight2() {
        return aDeathRight2;
    }

    public BufferedImage getDeathRight3() {
        return aDeathRight3;
    }

    public BufferedImage getDeathRight4() {
        return aDeathRight4;
    }

    public BufferedImage getDeathLeft1() {
        return aDeathLeft1;
    }

    public BufferedImage getDeathLeft2() {
        return aDeathLeft2;
    }

    public BufferedImage getDeathLeft3() {
        return aDeathLeft3;
    }

    public BufferedImage getDeathLeft4() {
        return aDeathLeft4;
    }

    public BufferedImage getDeathUp1() {
        return aDeathUp1;
    }

    public BufferedImage getDeathUp2() {
        return aDeathUp2;
    }

    public BufferedImage getDeathUp3() {
        return aDeathUp3;
    }

    public BufferedImage getDeathUp4() {
        return aDeathUp4;
    }

    public BufferedImage getDeathDown1() {
        return aDeathDown1;
    }

    public BufferedImage getDeathDown2() {
        return aDeathDown2;
    }

    public BufferedImage getDeathDown3() {
        return aDeathDown3;
    }

    public BufferedImage getDeathDown4() {
        return aDeathDown4;
    }

    public BufferedImage getDamageRight1() {
        return aDamageRight1;
    }

    public BufferedImage getDamageRight2() {
        return aDamageRight2;
    }

    public BufferedImage getDamageLeft1() {
        return aDamageLeft1;
    }

    public BufferedImage getDamageLeft2() {
        return aDamageLeft2;
    }

    public BufferedImage getDamageUp1() {
        return aDamageUp1;
    }

    public BufferedImage getDamageUp2() {
        return aDamageUp2;
    }

    public BufferedImage getDamageDown1() {
        return aDamageDown1;
    }

    public BufferedImage getDamageDown2() {
        return aDamageDown2;
    }

    public boolean getCollision() {
        return aCollisionOn;
    }

    public BufferedImage getImage() {
        return aImage;
    }

    public BufferedImage getImage2() {
        return aImage2;
    }

    public BufferedImage getImage3() {
        return aImage3;
    }

    public UtilityTool getUtilityTool() {
        return aUtilityTool;
    }

    public void setWorldX(int pWorldX) {
        this.aWorldX = pWorldX;
    }

    public void setWorldY(int pWorldY) {
        this.aWorldY = pWorldY;
    }

    public void setMaxLife(int pMaxLife) {
        this.aMaxLife = pMaxLife;
    }

    public void setLife(int pLife) {
        this.aLife = pLife;
    }

    public void setDirection(String pDirection) {
        this.aDirection = pDirection;
    }

    public void setSpeed(int pSpeed) {
        this.aSpeed = pSpeed;
    }

    public void setSolidAreaDefaultX(int pSolidAreaDefaultX) {
        this.aSolidAreaDefaultX = pSolidAreaDefaultX;
    }

    public void setSolidAreaDefaultY(int pSolidAreaDefaultY) {
        this.aSolidAreaDefaultY = pSolidAreaDefaultY;
    }

    public void setSolidArea(Rectangle pSolidArea) {
        this.aSolidArea = pSolidArea;
    }

    public void setAttackArea(Rectangle pAttackArea) {
        this.aAttackArea = pAttackArea;
    }

    public void setCollision(boolean pCollision) {
        this.aCollisionOn = pCollision;
    }

    public void setActionLockCounter(int pActionLockCounter) {
        this.aActionLockCounter = pActionLockCounter;
    }

    public void setDialogue(String pDialogue, int pIndex) {
        this.aDialogues[pIndex] = pDialogue;
    }

    public void setSpriteCounter(int pSpriteCounter) {
        this.aSpriteCounter = pSpriteCounter;
    }

    public void setSpriteNum(int pSpriteNum) {
        this.aSpriteNum = pSpriteNum;
    }

    public void setUp1(BufferedImage pUp1) {
        this.aUp1 = pUp1;
    }

    public void setUp2(BufferedImage pUp2) {
        this.aUp2 = pUp2;
    }

    public void setUp3(BufferedImage pUp3) {
        this.aUp3 = pUp3;
    }

    public void setUp4(BufferedImage pUp4) {
        this.aUp4 = pUp4;
    }

    public void setDown1(BufferedImage pDown1) {
        this.aDown1 = pDown1;
    }

    public void setDown2(BufferedImage pDown2) {
        this.aDown2 = pDown2;
    }

    public void setDown3(BufferedImage pDown3) {
        this.aDown3 = pDown3;
    }

    public void setDown4(BufferedImage pDown4) {
        this.aDown4 = pDown4;
    }

    public void setRight1(BufferedImage pRight1) {
        this.aRight1 = pRight1;
    }

    public void setRight2(BufferedImage pRight2) {
        this.aRight2 = pRight2;
    }

    public void setRight3(BufferedImage pRight3) {
        this.aRight3 = pRight3;
    }

    public void setRight4(BufferedImage pRight4) {
        this.aRight4 = pRight4;
    }

    public void setLeft1(BufferedImage pLeft1) {
        this.aLeft1 = pLeft1;
    }

    public void setLeft2(BufferedImage pLeft2) {
        this.aLeft2 = pLeft2;
    }

    public void setLeft3(BufferedImage pLeft3) {
        this.aLeft3 = pLeft3;
    }

    public void setLeft4(BufferedImage pLeft4) {
        this.aLeft4 = pLeft4;
    }

    public void setAttackUp1(BufferedImage pAttackUp1) {
        this.aAttackUp1 = pAttackUp1;
    }

    public void setAttackUp2(BufferedImage pAttackUp2) {
        this.aAttackUp2 = pAttackUp2;
    }

    public void setAttackUp3(BufferedImage pAttackUp3) {
        this.aAttackUp3 = pAttackUp3;
    }

    public void setAttackUp4(BufferedImage pAttackUp4) {
        this.aAttackUp4 = pAttackUp4;
    }

    public void setAttackDown1(BufferedImage pAttackDown1) {
        this.aAttackDown1 = pAttackDown1;
    }

    public void setAttackDown2(BufferedImage pAttackDown2) {
        this.aAttackDown2 = pAttackDown2;
    }

    public void setAttackDown3(BufferedImage pAttackDown3) {
        this.aAttackDown3 = pAttackDown3;
    }

    public void setAttackDown4(BufferedImage pAttackDown4) {
        this.aAttackDown4 = pAttackDown4;
    }

    public void setName(String pName) {
        this.aName = pName;
    }

    public void setImage(BufferedImage pImage) {
        aImage = pImage;
    }

    public void setImage2(BufferedImage pImage) {
        aImage2 = pImage;
    }

    public void setImage3(BufferedImage pImage) {
        aImage3 = pImage;
    }

    public void setAttackRight1(BufferedImage pAttackRight1) {
        this.aAttackRight1 = pAttackRight1;
    }

    public void setAttackRight2(BufferedImage pAttackRight2) {
        this.aAttackRight2 = pAttackRight2;
    }

    public void setAttackRight3(BufferedImage pAttackRight3) {
        this.aAttackRight3 = pAttackRight3;
    }

    public void setAttackRight4(BufferedImage pAttackRight4) {
        this.aAttackRight4 = pAttackRight4;
    }

    public void setAttackLeft1(BufferedImage pAttackLeft1) {
        this.aAttackLeft1 = pAttackLeft1;
    }

    public void setAttackLeft2(BufferedImage pAttackLeft2) {
        this.aAttackLeft2 = pAttackLeft2;
    }

    public void setAttackLeft3(BufferedImage pAttackLeft3) {
        this.aAttackLeft3 = pAttackLeft3;
    }

    public void setAttackLeft4(BufferedImage pAttackLeft4) {
        this.aAttackLeft4 = pAttackLeft4;
    }

    public void setDeathRight1(BufferedImage pDeathRight1) {
        this.aDeathRight1 = pDeathRight1;
    }

    public void setDeathRight2(BufferedImage pDeathRight2) {
        this.aDeathRight2 = pDeathRight2;
    }

    public void setDeathRight3(BufferedImage pDeathRight3) {
        this.aDeathRight3 = pDeathRight3;
    }

    public void setDeathRight4(BufferedImage pDeathRight4) {
        this.aDeathRight4 = pDeathRight4;
    }

    public void setDeathLeft1(BufferedImage pDeathLeft1) {
        this.aDeathLeft1 = pDeathLeft1;
    }

    public void setDeathLeft2(BufferedImage pDeathLeft2) {
        this.aDeathLeft2 = pDeathLeft2;
    }

    public void setDeathLeft3(BufferedImage pDeathLeft3) {
        this.aDeathLeft3 = pDeathLeft3;
    }

    public void setDeathLeft4(BufferedImage pDeathLeft4) {
        this.aDeathLeft4 = pDeathLeft4;
    }

    public void setDeathUp1(BufferedImage pDeathUp1) {
        this.aDeathUp1 = pDeathUp1;
    }

    public void setDeathUp2(BufferedImage pDeathUp2) {
        this.aDeathUp2 = pDeathUp2;
    }

    public void setDeathUp3(BufferedImage pDeathUp3) {
        this.aDeathUp3 = pDeathUp3;
    }

    public void setDeathUp4(BufferedImage pDeathUp4) {
        this.aDeathUp4 = pDeathUp4;
    }

    public void setDeathDown1(BufferedImage pDeathDown1) {
        this.aDeathDown1 = pDeathDown1;
    }

    public void setDeathDown2(BufferedImage pDeathDown2) {
        this.aDeathDown2 = pDeathDown2;
    }

    public void setDeathDown3(BufferedImage pDeathDown3) {
        this.aDeathDown3 = pDeathDown3;
    }

    public void setDeathDown4(BufferedImage pDeathDown4) {
        this.aDeathDown4 = pDeathDown4;
    }

    public void setDamageDown1(BufferedImage pDamageDown1) {
        this.aDamageDown1 = pDamageDown1;
    }

    public void setDamageDown2(BufferedImage pDamageDown2) {
        this.aDamageDown2 = pDamageDown2;
    }

    public void setDamageUp1(BufferedImage pDamageUp1) {
        this.aDamageUp1 = pDamageUp1;
    }

    public void setDamageUp2(BufferedImage pDamageUp2) {
        this.aDamageUp2 = pDamageUp2;
    }

    public void setDamageRight1(BufferedImage pDamageRight1) {
        this.aDamageRight1 = pDamageRight1;
    }

    public void setDamageRight2(BufferedImage pDamageRight2) {
        this.aDamageRight2 = pDamageRight2;
    }

    public void setDamageLeft1(BufferedImage pDamageLeft1) {
        this.aDamageLeft1 = pDamageLeft1;
    }

    public void setDamageLeft2(BufferedImage pDamageLeft2) {
        this.aDamageLeft2 = pDamageLeft2;
    }

    public void setInvincible(boolean pInvincible) {
        this.aInvincible = pInvincible;
    }

    public void setInvincibleCounter(int pInvincibleCounter) {
        this.aInvincibleCounter = pInvincibleCounter;
    }

    public void setType(int pType) {
        this.aType = pType;
    }

    public void isAttacking(boolean pAttack) {
        this.aAttacking = pAttack;
    }

    public void setDying(boolean pDying) {
        this.aDying = pDying;
    }

    public void setAlive(boolean pAlive) {
        this.aAlive = pAlive;
    }

    public void setDyingCounter(int pDyingCounter) {
        this.aDyingCounter = pDyingCounter;
    }

    public void setLevel(int pLevel) {
        this.aLevel = pLevel;
    }

    public void setStrength(int pStrength) {
        this.aStrength = pStrength;
    }

    public void setDexterity(int pDexterity) {
        this.aDexterity = pDexterity;
    }

    public void setAttack(int pAttack) {
        this.aAttack = pAttack;
    }

    public void setDefense(int pDefense) {
        this.aDefense = pDefense;
    }

    public void setExp(int pExp) {
        this.aExp = pExp;
    }

    public void setExpToLevelUp(int pExpToLevel) {
        this.aExpNeededToLevelUp = pExpToLevel;
    }

    public void setMoney(int pMoney) {
        this.aMoney = pMoney;
    }

    public void setCurrentWeapon(Entity pCurrentWeapon) {
        this.aCurrentWeapon = pCurrentWeapon;
    }

    public void setCurrentShield(Entity pCurrentShield) {
        this.aCurrentShield = pCurrentShield;
    }

    public void setAttackValue(int pAttackValue) {
        this.aAttackValue = pAttackValue;
    }

    public void setDefenseValue(int pDefenseValue) {
        this.aDefenseValue = pDefenseValue;
    }

    public void setDescription(String pDescription) {
        this.aDescription = pDescription;
    }

    public void setUseCost(int pUseCost) {
        this.aUseCost = pUseCost;
    }

    public void setProjectile(Projectile pProjectile) {
        this.aProjectile = pProjectile;
    }

    public void setShotAvailableCounter(int pShotAvailableCounter) {
        this.aShotAvailableCounter = pShotAvailableCounter;
    }

    public void setMaxAmmo(int pMaxAmmo) {
        this.aMaxAmmo = pMaxAmmo;
    }

    public void setAmmo(int pAmmo) {
        this.aAmmo = pAmmo;
    }

    public void setValue(int pValue) {
        this.aValue = pValue;
    }
}
