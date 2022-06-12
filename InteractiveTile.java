public class InteractiveTile extends Entity {

    GamePanel aGamePanel;
    public boolean isDestructible = false;
    
    public InteractiveTile(GamePanel pGamePanel, int pCol, int pRow) {
        super(pGamePanel);
        this.aGamePanel = pGamePanel;
    }

    public boolean isCorrectItem(Entity pItem) {
        boolean vCorrectItem = false;
        return vCorrectItem;
    }

    public InteractiveTile getDestroyedForm(){
        InteractiveTile vDestroyedForm = null;
        return vDestroyedForm;
    }

    public void update(){
        if(getInvincible() == true){
            setInvincibleCounter(getInvincibleCounter() + 1);
            if(getInvincibleCounter() > 20){
                setInvincible(false);
                setInvincibleCounter(0);
            }
        }
    }
    
}
