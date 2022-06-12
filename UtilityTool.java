import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

public class UtilityTool {

    public BufferedImage scaleImage(BufferedImage pOriginalImage, int pWidth, int pHeight) {
        BufferedImage vScaledImage = new BufferedImage(pWidth, pHeight, pOriginalImage.getType());
        Graphics2D vGraphics = vScaledImage.createGraphics();
        vGraphics.drawImage(pOriginalImage, 0, 0, pWidth, pHeight, null);
        vGraphics.dispose();
        return vScaledImage;
    }
}
