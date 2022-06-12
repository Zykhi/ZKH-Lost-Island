public class CollisionChecker {

    private GamePanel aGamePanel;

    public CollisionChecker(GamePanel pGamePanel) {
        this.aGamePanel = pGamePanel;
    }

    // Check collision with tile
    public void checkTile(Entity pEntity) {
        int vEntityLeftWorldX = pEntity.getWorldX() + pEntity.getSolidArea().x;
        int vEntityRightWorldX = pEntity.getWorldX() + pEntity.getSolidArea().x + pEntity.getSolidArea().width;
        int vEntityTopWorldY = pEntity.getWorldY() + pEntity.getSolidArea().y;
        int vEntityBottomWorldY = pEntity.getWorldY() + pEntity.getSolidArea().y + pEntity.getSolidArea().height;

        int vEntityLeftCol = vEntityLeftWorldX / aGamePanel.getTileSize();
        int vEntityRightCol = vEntityRightWorldX / aGamePanel.getTileSize();
        int vEntityTopRow = vEntityTopWorldY / aGamePanel.getTileSize();
        int vEntityBottomRow = vEntityBottomWorldY / aGamePanel.getTileSize();

        int vTileNum1;
        int vTileNum2;

        switch (pEntity.getDirection()) {
            case "up":
                vEntityTopRow = (vEntityTopWorldY - pEntity.getSpeed()) / aGamePanel.getTileSize();
                vTileNum1 = aGamePanel.getTileManager().getTileNumber(vEntityLeftCol, vEntityTopRow);
                vTileNum2 = aGamePanel.getTileManager().getTileNumber(vEntityRightCol, vEntityTopRow);
                if (aGamePanel.getTileManager().getTile(vTileNum1).getCollision() == true
                        || aGamePanel.getTileManager().getTile(vTileNum2).getCollision() == true) {
                    pEntity.setCollision(true);
                }
                break;
            case "down":
                vEntityBottomRow = (vEntityBottomWorldY + pEntity.getSpeed()) / aGamePanel.getTileSize();
                vTileNum1 = aGamePanel.getTileManager().getTileNumber(vEntityLeftCol, vEntityBottomRow);
                vTileNum2 = aGamePanel.getTileManager().getTileNumber(vEntityRightCol, vEntityBottomRow);
                if (aGamePanel.getTileManager().getTile(vTileNum1).getCollision() == true
                        || aGamePanel.getTileManager().getTile(vTileNum2).getCollision() == true) {
                    pEntity.setCollision(true);
                }
                break;
            case "left":
                vEntityLeftCol = (vEntityLeftWorldX - pEntity.getSpeed()) / aGamePanel.getTileSize();
                vTileNum1 = aGamePanel.getTileManager().getTileNumber(vEntityLeftCol, vEntityTopRow);
                vTileNum2 = aGamePanel.getTileManager().getTileNumber(vEntityLeftCol, vEntityBottomRow);
                if (aGamePanel.getTileManager().getTile(vTileNum1).getCollision() == true
                        || aGamePanel.getTileManager().getTile(vTileNum2).getCollision() == true) {
                    pEntity.setCollision(true);
                }
                break;
            case "right":
                vEntityRightCol = (vEntityRightWorldX + pEntity.getSpeed()) / aGamePanel.getTileSize();
                vTileNum1 = aGamePanel.getTileManager().getTileNumber(vEntityRightCol, vEntityTopRow);
                vTileNum2 = aGamePanel.getTileManager().getTileNumber(vEntityRightCol, vEntityBottomRow);
                if (aGamePanel.getTileManager().getTile(vTileNum1).getCollision() == true
                        || aGamePanel.getTileManager().getTile(vTileNum2).getCollision() == true) {
                    pEntity.setCollision(true);
                }
                break;
        }
    }

    // check collision with object
    public int checkObject(Entity pEntity, boolean pPlayer) {
        int vIndex = 999;

        for (int i = 0; i < aGamePanel.getItems().length; i++) {
            if (aGamePanel.getItem(i) != null) {
                pEntity.getSolidArea().x = pEntity.getWorldX() + pEntity.getSolidArea().x;
                pEntity.getSolidArea().y = pEntity.getWorldY() + pEntity.getSolidArea().y;

                aGamePanel.getItem(i).getSolidArea().x = aGamePanel.getItem(i).getWorldX()
                        + aGamePanel.getItem(i).getSolidArea().x;
                aGamePanel.getItem(i).getSolidArea().y = aGamePanel.getItem(i).getWorldY()
                        + aGamePanel.getItem(i).getSolidArea().y;

                switch (pEntity.getDirection()) {
                    case "up":
                        pEntity.getSolidArea().y -= pEntity.getSpeed();
                        break;
                    case "down":
                        pEntity.getSolidArea().y += pEntity.getSpeed();
                        break;
                    case "left":
                        pEntity.getSolidArea().x -= pEntity.getSpeed();
                        break;
                    case "right":
                        pEntity.getSolidArea().x += pEntity.getSpeed();
                        break;
                }
                if (pEntity.getSolidArea().intersects(aGamePanel.getItem(i).getSolidArea())) {
                    if (aGamePanel.getItem(i).getCollision() == true) {
                        pEntity.setCollision(true);
                    }
                    if (pPlayer == true) {
                        vIndex = i;
                    }
                }
                pEntity.getSolidArea().x = pEntity.getSolidAreaDefaultX();
                pEntity.getSolidArea().y = pEntity.getSolidAreaDefaultY();
                aGamePanel.getItem(i).getSolidArea().x = aGamePanel.getItem(i).getSolidAreaDefaultX();
                aGamePanel.getItem(i).getSolidArea().y = aGamePanel.getItem(i).getSolidAreaDefaultY();
            }

        }

        return vIndex;
    }

    // NPC or Monster collision
    public int checkEntity(Entity pEntity, Entity[] pTarget) {
        int vIndex = 999;

        for (int i = 0; i < pTarget.length; i++) {
            if (pTarget[i] != null) {
                pEntity.getSolidArea().x = pEntity.getWorldX() + pEntity.getSolidArea().x;
                pEntity.getSolidArea().y = pEntity.getWorldY() + pEntity.getSolidArea().y;

                pTarget[i].getSolidArea().x = pTarget[i].getWorldX() + pTarget[i].getSolidArea().x;
                pTarget[i].getSolidArea().y = pTarget[i].getWorldY() + pTarget[i].getSolidArea().y;

                switch (pEntity.getDirection()) {
                    case "up":
                        pEntity.getSolidArea().y -= pEntity.getSpeed();
                        break;
                    case "down":
                        pEntity.getSolidArea().y += pEntity.getSpeed();
                        break;
                    case "left":
                        pEntity.getSolidArea().x -= pEntity.getSpeed();
                        break;
                    case "right":
                        pEntity.getSolidArea().x += pEntity.getSpeed();
                        break;
                }
                if (pEntity.getSolidArea().intersects(pTarget[i].getSolidArea())) {
                    if (pTarget[i] != pEntity) {
                        pEntity.setCollision(true);
                        vIndex = i;
                    }
                }
                pEntity.getSolidArea().x = pEntity.getSolidAreaDefaultX();
                pEntity.getSolidArea().y = pEntity.getSolidAreaDefaultY();
                pTarget[i].getSolidArea().x = pTarget[i].getSolidAreaDefaultX();
                pTarget[i].getSolidArea().y = pTarget[i].getSolidAreaDefaultY();
            }

        }

        return vIndex;
    }

    // check collision with the player for NPC
    public boolean checkPlayer(Entity pEntity) {

        boolean vContactPlayer = false;

        pEntity.getSolidArea().x = pEntity.getWorldX() + pEntity.getSolidArea().x;
        pEntity.getSolidArea().y = pEntity.getWorldY() + pEntity.getSolidArea().y;

        aGamePanel.getPlayer().getSolidArea().x = aGamePanel.getPlayer().getWorldX()
                + aGamePanel.getPlayer().getSolidArea().x;
        aGamePanel.getPlayer().getSolidArea().y = aGamePanel.getPlayer().getWorldY()
                + aGamePanel.getPlayer().getSolidArea().y;

        switch (pEntity.getDirection()) {
            case "up":
                pEntity.getSolidArea().y -= pEntity.getSpeed();
                break;
            case "down":
                pEntity.getSolidArea().y += pEntity.getSpeed();
                break;
            case "left":
                pEntity.getSolidArea().x -= pEntity.getSpeed();
                break;
            case "right":
                pEntity.getSolidArea().x += pEntity.getSpeed();
                break;

        }
        if (pEntity.getSolidArea().intersects(aGamePanel.getPlayer().getSolidArea())) {
            pEntity.setCollision(true);
            vContactPlayer = true;
        }

        pEntity.getSolidArea().x = pEntity.getSolidAreaDefaultX();
        pEntity.getSolidArea().y = pEntity.getSolidAreaDefaultY();
        aGamePanel.getPlayer().getSolidArea().x = aGamePanel.getPlayer().getSolidAreaDefaultX();
        aGamePanel.getPlayer().getSolidArea().y = aGamePanel.getPlayer().getSolidAreaDefaultY();

        return vContactPlayer;
    }
}
