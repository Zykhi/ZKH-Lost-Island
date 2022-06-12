public class Projectile extends Entity {

    Entity aUser;

    public Projectile(GamePanel pGamePanel) {
        super(pGamePanel);

    }

    public void set(int pWorldX, int pWorldY, String pDirection, boolean pAlive, Entity pUser) {
        this.setWorldX(pWorldX);
        this.setWorldY(pWorldY);
        this.setDirection(pDirection);
        this.setAlive(pAlive);
        this.aUser = pUser;
        this.setLife(this.getMaxLife());
    }

    public void update() {

        if (aUser == getGamePanel().getPlayer()) {
            int vMonsterIndex = getGamePanel().getCollisionChecker().checkEntity(this, getGamePanel().getMonster());
            if (vMonsterIndex != 999) {
                getGamePanel().getPlayer().damageMonster(vMonsterIndex, getAttack());
                setAlive(false);
            }
        } else {
            boolean vContactPlayer = getGamePanel().getCollisionChecker().checkPlayer(this);
            if (getGamePanel().getPlayer().getInvincible() == false && vContactPlayer == true) {
                damagePlayer(getAttack());
                setAlive(false);
            }
        }

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

        setLife(getLife() - 1);
        if (getLife() <= 0) {
            setAlive(false);
        }

        setSpriteCounter(getSpriteCounter() + 1);
        if (getSpriteCounter() > 12) {
            if (getSpriteNum() == 1) {
                setSpriteNum(2);
            } else {
                setSpriteNum(1);
            }
            setSpriteCounter(0);
        }
    }

    public boolean haveResource(Entity pUser){
        boolean vHaveResource = false;
        return vHaveResource;
    }

    public void substractResource(Entity pUser){
        
    }

}