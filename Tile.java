import java.awt.image.BufferedImage;

public class Tile {
    private BufferedImage aImage;
    private boolean aCollision = false;
    
    public boolean getCollision() {
        return aCollision;
    }
    
    public BufferedImage getImage() {
        return aImage;
    }

    public void setCollision(boolean pCollision) {
        aCollision = pCollision;
    }

    public void setImage(BufferedImage pImage) {
        aImage = pImage;
    }
}
