import java.awt.Rectangle;

public class EventHandler {
    GamePanel aGamePanel;
    Rectangle aEventRectangle;
    int aEventRectDefaultX;
    int aEventRectDefaultY;

    public EventHandler(GamePanel pGamePanel) {
        this.aGamePanel = pGamePanel;

        aEventRectangle = new Rectangle();
        aEventRectangle.x = 23;
        aEventRectangle.y = 23;
        aEventRectangle.width = 2;
        aEventRectangle.height = 2;
        aEventRectDefaultX = aEventRectangle.x;
        aEventRectDefaultY = aEventRectangle.y;
    }

    // Col += 1 and Row += 1 when you get coord on map 'cause tile start at 0,0 and txt line 1,1 
    public void checkEvent() {
        if (hit(8, 10, "right") == true) {
            damagePit(aGamePanel.getDialogueState());
        }
        if (hit(11,12, "left")== true){ //tile 12,13 on map.txt
            healingPool(aGamePanel.getDialogueState());
        }
    }

    private boolean hit(int pEventCol, int pEventRow, String pReqDirection) {
        boolean vHit = false;

        aGamePanel.getPlayer().getSolidArea().x = aGamePanel.getPlayer().getWorldX()
                + aGamePanel.getPlayer().getSolidArea().x;
        aGamePanel.getPlayer().getSolidArea().y = aGamePanel.getPlayer().getWorldY()
                + aGamePanel.getPlayer().getSolidArea().y;
        aEventRectangle.x = pEventCol * aGamePanel.getTileSize() + aEventRectangle.x;
        aEventRectangle.y = pEventRow * aGamePanel.getTileSize() + aEventRectangle.y;

        if (aGamePanel.getPlayer().getSolidArea().intersects(aEventRectangle)) {
            if (aGamePanel.getPlayer().getDirection().contentEquals(pReqDirection)
                    || pReqDirection.contentEquals("any")) {
                vHit = true;
            }
        }

        aGamePanel.getPlayer().getSolidArea().x = aGamePanel.getPlayer().getSolidAreaDefaultX();
        aGamePanel.getPlayer().getSolidArea().y = aGamePanel.getPlayer().getSolidAreaDefaultY();
        aEventRectangle.x = aEventRectDefaultX;
        aEventRectangle.y = aEventRectDefaultY;

        return vHit;
    }

    private void damagePit(int pGameState) {
        aGamePanel.setGameState(pGameState);
        aGamePanel.getUserInterface().setCurrentDialogue("You fell into a pit. You lose.");
        aGamePanel.getPlayer().setLife(aGamePanel.getPlayer().getLife() - 1);

    }

    private void healingPool(int pGameState) {
        if (aGamePanel.getInput().isEnter() == true) {
            aGamePanel.setGameState(pGameState);
            aGamePanel.getPlayer().setAttackCancel(true);
            aGamePanel.getUserInterface().setCurrentDialogue("You found a healing pool. You gain 1 life.");
            aGamePanel.getPlayer().setLife(aGamePanel.getPlayer().getMaxLife());
        }
    }

    public Rectangle getEventRectangle() {
        return aEventRectangle;
    }
}
