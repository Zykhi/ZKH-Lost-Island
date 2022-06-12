import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.awt.BasicStroke;

public class UserInterface {
    private GamePanel aGamePanel;
    private Font a8bitFont;
    private Graphics2D aGraphics2D;
    private BufferedImage aHeartFull;
    private BufferedImage aHeartBlank;
    private BufferedImage aHeartHalf;
    private BufferedImage aAmmoFull;
    private BufferedImage aAmmoBlank;

    private ArrayList<String> aMessage = new ArrayList<>();
    private ArrayList<Integer> aMessageCounter = new ArrayList<>();

    private int aCommandNumber = 0;

    private int aSlotRow = 0;
    private int aSlotCol = 0;

    private String aCurrentDialogue = "";

    public UserInterface(GamePanel pGamePanel) {
        this.aGamePanel = pGamePanel;

        InputStream vFontStream = getClass().getResourceAsStream("/font/8bit.ttf");
        try {
            a8bitFont = Font.createFont(Font.TRUETYPE_FONT, vFontStream);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create HUD
        Entity vHeart = new Heart(pGamePanel);
        aHeartFull = vHeart.getImage();
        aHeartHalf = vHeart.getImage2();
        aHeartBlank = vHeart.getImage3();

        Entity vArrowAmmo = new ArrowAmmo(pGamePanel);
        aAmmoFull = vArrowAmmo.getImage();
        aAmmoBlank = vArrowAmmo.getImage2();
    }

    public void draw(Graphics2D pGraphics) {
        this.aGraphics2D = pGraphics;

        pGraphics.setFont(a8bitFont);
        pGraphics.setColor(Color.WHITE);

        // Title State
        if (aGamePanel.getGameState() == aGamePanel.getTitleState()) {
            drawTitleScreen();
        }
        // Game State
        if (aGamePanel.getGameState() == aGamePanel.getPlayState()) {
            drawPlayerLife();
            drawAmmo();
            drawMessage();
        }
        // Pause State
        if (aGamePanel.getGameState() == aGamePanel.getPauseState()) {
            drawPlayerLife();
            drawAmmo();
            drawPauseScreen();
        }
        // Dialogue State
        if (aGamePanel.getGameState() == aGamePanel.getDialogueState()) {
            drawPlayerLife();
            drawAmmo();
            drawDialogueScreen();
        }
        // Character State
        if (aGamePanel.getGameState() == aGamePanel.getCharacterState()) {
            drawPlayerLife();
            drawAmmo();
            drawCharacterScreen();
            drawInventory();
        }

    }

    private void drawTitleScreen() {
        // Title name
        aGraphics2D.setFont(aGraphics2D.getFont().deriveFont(Font.BOLD, 72f));
        String vText = "ZKH Lost Island";
        int vX = getXforCenterText(vText);
        int vY = aGamePanel.getTileSize() * 3;

        // Shadow
        aGraphics2D.setColor(Color.darkGray);
        aGraphics2D.drawString(vText, vX + 3, vY + 3);

        // Main color
        aGraphics2D.setColor(Color.white);
        aGraphics2D.drawString(vText, vX, vY);

        // Image
        vX = aGamePanel.getScreenWidth() / 2 - (aGamePanel.getTileSize() * 2) / 2;
        vY = aGamePanel.getTileSize() * 4;
        aGraphics2D.drawImage(aGamePanel.getPlayer().getDown2(), vX, vY, aGamePanel.getTileSize() * 2,
                aGamePanel.getTileSize() * 2, null);

        // Menu
        aGraphics2D.setFont(aGraphics2D.getFont().deriveFont(Font.BOLD, 48f));

        vText = "NEW GAME";
        vX = getXforCenterText(vText);
        vY += aGamePanel.getTileSize() * 4;
        aGraphics2D.drawString(vText, vX, vY);
        if (aCommandNumber == 0) {
            aGraphics2D.drawString(">", vX - aGamePanel.getTileSize(), vY);
        }

        vText = "LOAD GAME";
        vX = getXforCenterText(vText);
        vY += aGamePanel.getTileSize();
        aGraphics2D.drawString(vText, vX, vY);
        if (aCommandNumber == 1) {
            aGraphics2D.drawString(">", vX - aGamePanel.getTileSize(), vY);
        }

        vText = "QUIT";
        vX = getXforCenterText(vText);
        vY += aGamePanel.getTileSize();
        aGraphics2D.drawString(vText, vX, vY);
        if (aCommandNumber == 2) {
            aGraphics2D.drawString(">", vX - aGamePanel.getTileSize(), vY);
        }

    }

    private void drawPlayerLife() {
        int vX = aGamePanel.getTileSize() / 2;
        int vY = aGamePanel.getTileSize() / 2;
        int vI = 0;

        // Draw max life
        while (vI < aGamePanel.getPlayer().getMaxLife() / 2) {
            aGraphics2D.drawImage(aHeartBlank, vX, vY, null);
            vX += aGamePanel.getTileSize();
            vI++;
        }

        // Reset
        vX = aGamePanel.getTileSize() / 2;
        vY = aGamePanel.getTileSize() / 2;
        vI = 0;

        // Draw current life
        while (vI < aGamePanel.getPlayer().getLife()) {
            aGraphics2D.drawImage(aHeartHalf, vX, vY, null);
            vI++;
            if (vI < aGamePanel.getPlayer().getLife()) {
                aGraphics2D.drawImage(aHeartFull, vX, vY, null);
            }
            vI++;
            vX += aGamePanel.getTileSize();
        }
    }

    private void drawAmmo(){
        int vX = aGamePanel.getTileSize() / 2;
        int vY = aGamePanel.getTileSize() / 2;
        int vI = 0;

        if (aGamePanel.getPlayer().getCurrentWeapon().getType() == aGamePanel.getPlayer().getBowType()) {
            // Draw max ammo
            vX = aGamePanel.getTileSize() / 2;
            vY = (int) (aGamePanel.getTileSize() * 1.5);
            vI = 0;

            while (vI < aGamePanel.getPlayer().getMaxAmmo()) {
                aGraphics2D.drawImage(aAmmoBlank, vX, vY, null);
                vI++;
                vX += aGamePanel.getTileSize()-16;
            }

            // Draw ammo
            vX = aGamePanel.getTileSize() / 2;
            vY = (int) (aGamePanel.getTileSize() * 1.5);
            vI = 0;

            while (vI < aGamePanel.getPlayer().getAmmo()) {
                aGraphics2D.drawImage(aAmmoFull, vX, vY, null);
                vI++;
                vX += aGamePanel.getTileSize()-16;
            }
        }
    }

    private void drawMessage() {
        int vMessageX = aGamePanel.getTileSize();
        int vMessageY = aGamePanel.getTileSize() * 4;

        aGraphics2D.setFont(aGraphics2D.getFont().deriveFont(Font.BOLD, 32f));

        for (int i = 0; i < aMessage.size(); i++) {
            if (aMessage.get(i) != null) {

                // shadow
                aGraphics2D.setColor(Color.black);
                aGraphics2D.drawString(aMessage.get(i), vMessageX + 2, vMessageY + 2);

                aGraphics2D.setColor(Color.white);
                aGraphics2D.drawString(aMessage.get(i), vMessageX, vMessageY);

                int vCounter = aMessageCounter.get(i) + 1; // message counter ++
                aMessageCounter.set(i, vCounter);

                vMessageY += 50;

                if (aMessageCounter.get(i) > 180) {
                    aMessage.remove(i);
                    aMessageCounter.remove(i);
                }
            }
        }
    }

    private void drawPauseScreen() {
        String vPauseMessage = "PAUSE";
        aGraphics2D.setFont(aGraphics2D.getFont().deriveFont(Font.BOLD, 80f));
        int vX = getXforCenterText(vPauseMessage);
        int vY = aGamePanel.getScreenHeight() / 2;
        aGraphics2D.drawString(vPauseMessage, vX, vY);
    }

    private void drawDialogueScreen() {
        // Window
        int vX = aGamePanel.getTileSize() * 2;
        int vY = aGamePanel.getTileSize() * (19 / 2);
        int vWidth = aGamePanel.getScreenWidth() - aGamePanel.getTileSize() * 4;
        int vHeight = aGamePanel.getTileSize() * 2;

        drawSubWindow(vX, vY, vWidth, vHeight);

        aGraphics2D.setFont(aGraphics2D.getFont().deriveFont(Font.PLAIN, 20f));
        vX += aGamePanel.getTileSize();
        vY += aGamePanel.getTileSize();

        for (String vLine : aCurrentDialogue.split("\n")) {
            aGraphics2D.drawString(vLine, vX, vY);
            vY += 40;
        }

    }

    private void drawSubWindow(int pX, int pY, int pWidth, int pHeight) {
        Color vColor = new Color(0, 0, 0, 220);
        aGraphics2D.setColor(vColor);
        aGraphics2D.fillRoundRect(pX, pY, pWidth, pHeight, 35, 35);
        vColor = new Color(255, 255, 255);
        aGraphics2D.setColor(vColor);
        aGraphics2D.setStroke(new BasicStroke(5));
        aGraphics2D.drawRoundRect(pX + 5, pY + 5, pWidth - 10, pHeight - 10, 25, 25);
    }

    private void drawCharacterScreen() {
        // Window
        final int vFrameX = aGamePanel.getTileSize() / 2;
        final int vFrameY = aGamePanel.getTileSize() * 2;
        final int vFrameWidth = aGamePanel.getTileSize() * 6;
        final int vFrameHeight = aGamePanel.getTileSize() * 9;

        drawSubWindow(vFrameX, vFrameY, vFrameWidth, vFrameHeight);

        // Text
        aGraphics2D.setColor(Color.white);
        aGraphics2D.setFont(aGraphics2D.getFont().deriveFont(32f));
        int vTextX = vFrameX + 20;
        int vTextY = vFrameY + aGamePanel.getTileSize();
        final int lineHeight = 35;

        // Names
        aGraphics2D.drawString("Health: ", vTextX, vTextY);
        vTextY += lineHeight;
        aGraphics2D.drawString("Level: ", vTextX, vTextY);
        vTextY += lineHeight;
        aGraphics2D.drawString("Experience: ", vTextX, vTextY);
        vTextY += lineHeight;
        aGraphics2D.drawString("Strength: ", vTextX, vTextY);
        vTextY += lineHeight;
        aGraphics2D.drawString("Dexterity: ", vTextX, vTextY);
        vTextY += lineHeight;
        aGraphics2D.drawString("Attack: ", vTextX, vTextY);
        vTextY += lineHeight;
        aGraphics2D.drawString("Defense: ", vTextX, vTextY);
        vTextY += lineHeight;
        aGraphics2D.drawString("Gold: ", vTextX, vTextY);
        vTextY += lineHeight + 16;
        aGraphics2D.drawString("Weapon: ", vTextX, vTextY);
        vTextY += lineHeight + 16;
        aGraphics2D.drawString("Shield: ", vTextX, vTextY);

        // values
        int vTailX = (vFrameX + vFrameWidth) - 30;
        // reset
        vTextY = vFrameY + aGamePanel.getTileSize();

        String vValue;

        vValue = String.valueOf(aGamePanel.getPlayer().getLife() + "/" + aGamePanel.getPlayer().getMaxLife());
        vTextX = getXforAllignToRightText(vValue, vTailX);
        aGraphics2D.drawString(vValue, vTextX, vTextY);
        vTextY += lineHeight;

        vValue = String.valueOf(aGamePanel.getPlayer().getLevel());
        vTextX = getXforAllignToRightText(vValue, vTailX);
        aGraphics2D.drawString(vValue, vTextX, vTextY);
        vTextY += lineHeight;

        vValue = String.valueOf(aGamePanel.getPlayer().getExp());
        vTextX = getXforAllignToRightText(vValue, vTailX);
        aGraphics2D.drawString(vValue, vTextX, vTextY);
        vTextY += lineHeight;

        vValue = String.valueOf(aGamePanel.getPlayer().getStrength());
        vTextX = getXforAllignToRightText(vValue, vTailX);
        aGraphics2D.drawString(vValue, vTextX, vTextY);
        vTextY += lineHeight;

        vValue = String.valueOf(aGamePanel.getPlayer().getDexterity());
        vTextX = getXforAllignToRightText(vValue, vTailX);
        aGraphics2D.drawString(vValue, vTextX, vTextY);
        vTextY += lineHeight;

        vValue = String.valueOf(aGamePanel.getPlayer().getAttack());
        vTextX = getXforAllignToRightText(vValue, vTailX);
        aGraphics2D.drawString(vValue, vTextX, vTextY);
        vTextY += lineHeight;

        vValue = String.valueOf(aGamePanel.getPlayer().getDefense());
        vTextX = getXforAllignToRightText(vValue, vTailX);
        aGraphics2D.drawString(vValue, vTextX, vTextY);
        vTextY += lineHeight;

        vValue = String.valueOf(aGamePanel.getPlayer().getMoney());
        vTextX = getXforAllignToRightText(vValue, vTailX);
        aGraphics2D.drawString(vValue, vTextX, vTextY);
        vTextY += lineHeight;

        aGraphics2D.drawImage(aGamePanel.getPlayer().getCurrentWeapon().getImage(), vTailX - aGamePanel.getTileSize(),
                vTextY - 16, null);
        vTextY += aGamePanel.getTileSize();

        aGraphics2D.drawImage(aGamePanel.getPlayer().getCurrentShield().getImage(), vTailX - aGamePanel.getTileSize(),
                vTextY - 16, null);

    }

    private void drawInventory() {

        // frame
        int vFrameX = aGamePanel.getTileSize() * 9;
        int vFrameY = aGamePanel.getTileSize() * 2;
        int vFrameWidth = aGamePanel.getTileSize() * 6;
        int vFrameHeight = aGamePanel.getTileSize() * 5;

        drawSubWindow(vFrameX, vFrameY, vFrameWidth, vFrameHeight);

        // Slot
        final int vSlotXStart = vFrameX + 20;
        final int vSlotYStart = vFrameY + 20;
        int vSlotX = vSlotXStart;
        int vSlotY = vSlotYStart;
        int vSlotSize = aGamePanel.getTileSize() + 3;

        // Draw Items
        for (int i = 0; i < aGamePanel.getPlayer().getInventory().size(); i++) {

            // Equip
            if (aGamePanel.getPlayer().getInventory().get(i) == aGamePanel.getPlayer().getCurrentWeapon()
                    || aGamePanel.getPlayer().getInventory().get(i) == aGamePanel.getPlayer().getCurrentShield()) {
                aGraphics2D.setColor(Color.yellow);
                aGraphics2D.fillRoundRect(vSlotX, vSlotY, aGamePanel.getTileSize(), aGamePanel.getTileSize(), 10, 10);
            }

            aGraphics2D.drawImage(((Entity) aGamePanel.getPlayer().getInventory().get(i)).getImage(), vSlotX, vSlotY,
                    null);
            vSlotX += vSlotSize;
            if (i == 4 || i == 9 || i == 14) {
                vSlotX = vSlotXStart;
                vSlotY += vSlotSize;
            }
        }

        // Cursor
        int vCursorX = vSlotXStart + (vSlotSize * aSlotCol);
        int vCursorY = vSlotYStart + (vSlotSize * aSlotRow);
        int vCursorWidth = aGamePanel.getTileSize();
        int vCursorHeight = aGamePanel.getTileSize();

        // draw cursor
        aGraphics2D.setColor(Color.white);
        aGraphics2D.setStroke(new BasicStroke(3));
        aGraphics2D.drawRoundRect(vCursorX, vCursorY, vCursorWidth, vCursorHeight, 10, 10);

        // description frame
        int vDescriptionFrameX = vFrameX;
        int vDescriptionFrameY = vFrameY + vFrameHeight + aGamePanel.getTileSize();
        int vDescriptionFrameWidth = vFrameWidth;
        int vDescriptionFrameHeight = aGamePanel.getTileSize() * 3;

        // draw description
        int vTextX = vDescriptionFrameX + 20;
        int vTextY = vDescriptionFrameY + aGamePanel.getTileSize();

        aGraphics2D.setFont(aGraphics2D.getFont().deriveFont(28f));

        int vItemIndex = getItemIndexOnSlot();

        if (vItemIndex < aGamePanel.getPlayer().getInventory().size()) {

            // draw subwindow only if there is an item on the slot
            drawSubWindow(vDescriptionFrameX, vDescriptionFrameY, vDescriptionFrameWidth, vDescriptionFrameHeight);

            for (String vLine : (aGamePanel.getPlayer()).getInventory().get(vItemIndex).getDescription().split("\n")) {
                aGraphics2D.drawString(vLine, vTextX, vTextY);
                vTextY += 32;
            }
        }

    }

    public void addMessage(String pMessage) {
        aMessage.add(pMessage);
        aMessageCounter.add(0);
    }

    public String getCurrentDialogue() {
        return aCurrentDialogue;
    }

    private int getXforCenterText(String pText) {
        int vLength = (int) aGraphics2D.getFontMetrics().getStringBounds(pText, aGraphics2D).getWidth();
        int vX = aGamePanel.getScreenWidth() / 2 - vLength / 2;
        return vX;
    }

    private int getXforAllignToRightText(String pText, int pTailX) {
        int vLength = (int) aGraphics2D.getFontMetrics().getStringBounds(pText, aGraphics2D).getWidth();
        int vX = pTailX - vLength;
        return vX;
    }

    public int getItemIndexOnSlot() {
        int vItemIndex = aSlotRow * 5 + aSlotCol;
        return vItemIndex;
    }

    public int getCommandNumber() {
        return aCommandNumber;
    }

    public int getSlotCol() {
        return aSlotCol;
    }

    public int getSlotRow() {
        return aSlotRow;
    }

    public void setCommandNumber(int pCommandNumber) {
        this.aCommandNumber = pCommandNumber;
    }

    public void setCurrentDialogue(String pCurrentDialogue) {
        this.aCurrentDialogue = pCurrentDialogue;
    }

    public void setSlotCol(int pSlotCol) {
        this.aSlotCol = pSlotCol;
    }

    public void setSlotRow(int pSlotRow) {
        this.aSlotRow = pSlotRow;
    }
}
