public class AssetSetter {
    private GamePanel aGamePanel;

    public AssetSetter(GamePanel pGamePanel) {
        this.aGamePanel = pGamePanel;
    }

    public void setItem(){
        aGamePanel.setItem(new Key(aGamePanel), 0);
        aGamePanel.getItem(0).setWorldX(aGamePanel.getTileSize() * 8);
        aGamePanel.getItem(0).setWorldY(aGamePanel.getTileSize() * 8);

        aGamePanel.setItem(new HandmadeAxe(aGamePanel), 1);
        aGamePanel.getItem(1).setWorldX(aGamePanel.getTileSize() * 7);
        aGamePanel.getItem(1).setWorldY(aGamePanel.getTileSize() * 7);

        aGamePanel.setItem(new Potion(aGamePanel), 2);
        aGamePanel.getItem(2).setWorldX(aGamePanel.getTileSize() * 10);
        aGamePanel.getItem(2).setWorldY(aGamePanel.getTileSize() * 6);

        aGamePanel.setItem(new HandmadeBow(aGamePanel), 3);
        aGamePanel.getItem(3).setWorldX(aGamePanel.getTileSize() * 20);
        aGamePanel.getItem(3).setWorldY(aGamePanel.getTileSize() * 10);

        aGamePanel.setItem(new ArrowAmmo(aGamePanel), 4);
        aGamePanel.getItem(4).setWorldX(aGamePanel.getTileSize() * 20);
        aGamePanel.getItem(4).setWorldY(aGamePanel.getTileSize() * 12);

        aGamePanel.setItem(new HandmadePickaxe(aGamePanel), 5);
        aGamePanel.getItem(5).setWorldX(aGamePanel.getTileSize() * 20);
        aGamePanel.getItem(5).setWorldY(aGamePanel.getTileSize() * 8);
    }

    public void setNPC(){
        aGamePanel.setNPC(new NPC(aGamePanel), 0);
        aGamePanel.getNPC(0).setWorldX(aGamePanel.getTileSize() * 10);
        aGamePanel.getNPC(0).setWorldY(aGamePanel.getTileSize() * 10);

        aGamePanel.setNPC(new NPCMerchant(aGamePanel), 1);
        aGamePanel.getNPC(1).setWorldX(aGamePanel.getTileSize() * 20);
        aGamePanel.getNPC(1).setWorldY(aGamePanel.getTileSize() * 20);
    }

    public void setMonster(){
        aGamePanel.setMonster(new Slime(aGamePanel), 0);
        aGamePanel.getMonster(0).setWorldX(aGamePanel.getTileSize() * 10);
        aGamePanel.getMonster(0).setWorldY(aGamePanel.getTileSize() * 15);

        aGamePanel.setMonster(new Skeleton(aGamePanel), 1);
        aGamePanel.getMonster(1).setWorldX(aGamePanel.getTileSize() * 25);
        aGamePanel.getMonster(1).setWorldY(aGamePanel.getTileSize() * 25);
    }

    public void setInteractiveTile(){

        aGamePanel.setInteractiveTile(new Rock(aGamePanel, 20, 15), 0);

    }
}
