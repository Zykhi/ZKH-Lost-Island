import javax.imageio.ImageIO;

public class Arrow extends Projectile {

    GamePanel aGamePanel;

    public Arrow(GamePanel pGamePanel) {
        super(pGamePanel);
        this.aGamePanel = pGamePanel;

        setName("Arrow");
        setSpeed(5);
        setMaxLife(80);
        setLife(getMaxLife());
        setAttack(2);
        setUseCost(1);
        setAlive(false);
        getArrowImage();
    }

    private void getArrowImage() {
        try {
            setUp1(ImageIO.read(getClass().getResourceAsStream("/img/projectile/arrow/ArrowProj-1.png")));
            setUp2(ImageIO.read(getClass().getResourceAsStream("/img/projectile/arrow/ArrowProj-1.png")));
            setUp3(ImageIO.read(getClass().getResourceAsStream("/img/projectile/arrow/ArrowProj-1.png")));
            setUp4(ImageIO.read(getClass().getResourceAsStream("/img/projectile/arrow/ArrowProj-1.png")));
            setLeft1(ImageIO.read(getClass().getResourceAsStream("/img/projectile/arrow/ArrowProj-2.png")));
            setLeft2(ImageIO.read(getClass().getResourceAsStream("/img/projectile/arrow/ArrowProj-2.png")));
            setLeft3(ImageIO.read(getClass().getResourceAsStream("/img/projectile/arrow/ArrowProj-2.png")));
            setLeft4(ImageIO.read(getClass().getResourceAsStream("/img/projectile/arrow/ArrowProj-2.png")));
            setDown1(ImageIO.read(getClass().getResourceAsStream("/img/projectile/arrow/ArrowProj-3.png")));
            setDown2(ImageIO.read(getClass().getResourceAsStream("/img/projectile/arrow/ArrowProj-3.png")));
            setDown3(ImageIO.read(getClass().getResourceAsStream("/img/projectile/arrow/ArrowProj-3.png")));
            setDown4(ImageIO.read(getClass().getResourceAsStream("/img/projectile/arrow/ArrowProj-3.png")));
            setRight1(ImageIO.read(getClass().getResourceAsStream("/img/projectile/arrow/ArrowProj-4.png")));
            setRight2(ImageIO.read(getClass().getResourceAsStream("/img/projectile/arrow/ArrowProj-4.png")));
            setRight3(ImageIO.read(getClass().getResourceAsStream("/img/projectile/arrow/ArrowProj-4.png")));
            setRight4(ImageIO.read(getClass().getResourceAsStream("/img/projectile/arrow/ArrowProj-4.png")));

        } catch (Exception e) {
        }
    }

    public boolean haveResource(Entity pUser){
        boolean vHaveResource = false;

        if(pUser.getAmmo() >= getUseCost()){
            vHaveResource = true;
        }
        return vHaveResource;
    }

    public void substractResource(Entity pUser){
        pUser.setAmmo(pUser.getAmmo() - getUseCost());
    }
}
