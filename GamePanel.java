import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
    private final int aTileSize = 16; // 16x16 pixels
    private final int aScale = 3;
    private final int aTileScale = aTileSize * aScale; // 48x48
    private final int aMaxScreenCols = 16;
    private final int aMaxScreenRows = 12;
    private final int aScreenWidth = aTileScale * aMaxScreenCols; // 980 pixels
    private final int aScreenHeight = aTileScale * aMaxScreenRows; // 576 pixels
    private final int aFPS = 60;
    private final int aMaxWorldCol = 50;
    private final int aMaxWorldRow = 50;

    private Input aInput = new Input(this);
    private Player aPlayer = new Player(this, aInput);
    private TileManager aTileManager = new TileManager(this);
    private CollisionChecker aCollisionChecker = new CollisionChecker(this);
    private AssetSetter aAssetSetter = new AssetSetter(this);
    private Entity aItems[] = new Entity[10];
    private Entity aNPC[] = new Entity[10];
    private Entity aMonster[] = new Entity[10];
    private InteractiveTile aInteractiveTile[] = new InteractiveTile[50];
    private ArrayList<Entity> aEntityList = new ArrayList<>();
    private ArrayList<Entity> aProjetctileList = new ArrayList<>();
    private Sound aMusic = new Sound();
    private Sound aSoundEffect = new Sound();
    private UserInterface aGUI = new UserInterface(this);
    private EventHandler aEventHandler = new EventHandler(this);
    private Config aConfig = new Config(this);

    private Thread aGameThread;

    // Game States
    private int aGameState;
    private final int aTitleState = 0;
    private final int aPlayState = 1;
    private final int aPauseState = 2;
    private final int aDialogueState = 3;
    private final int aCharacterState = 4;
    private final int aOptionState = 5;
    private final int aGameOverState = 6;
    private final int aTradeState = 7;

    public GamePanel() {
        this.setPreferredSize(new Dimension(aScreenWidth, aScreenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(aInput);
        this.setFocusable(true);
        setupGame();
        start();
    }

    public void setupGame() {
        setAsset();
        // playMusic(0);
        // stopMusic();
        aGameState = aTitleState;
    }

    private void setAsset(){
        aAssetSetter.setItem();
        aAssetSetter.setNPC();
        aAssetSetter.setMonster();
        aAssetSetter.setInteractiveTile();
    }

    public void start() {
        aGameThread = new Thread(this);
        aGameThread.start();
    }

    @Override
    public void run() {
        double vDrawInterval = 1000000000 / aFPS;
        double vDelta = 0;
        long vLastTime = System.nanoTime();
        long vCurrentTime;

        while (aGameThread != null) {

            vCurrentTime = System.nanoTime();
            vDelta += (vCurrentTime - vLastTime) / vDrawInterval;
            vLastTime = vCurrentTime;
            if (vDelta >= 1) {
                update();
                repaint();
                vDelta--;
            }
        }
    }

    public void update() {
        if (aGameState == aPlayState) {
            // Player
            aPlayer.update();
            // NPC
            for (int i = 0; i < aNPC.length; i++) {
                if (aNPC[i] != null) {
                    aNPC[i].update();
                }
            }
            // Monster
            for (int i = 0; i < aMonster.length; i++) {
                if (aMonster[i] != null) {
                    if (aMonster[i].isAlive() && aMonster[i].isDying()==false) {
                        aMonster[i].update();
                    }
                    if(aMonster[i].isAlive() == false){
                        aMonster[i].checkDrop();
                        aMonster[i] = null;
                    }
                }
            }

            //Projectile
            for (int i = 0; i < aProjetctileList.size(); i++) {
                if (aProjetctileList.get(i) != null) {
                    if (aProjetctileList.get(i).isAlive()) {
                        aProjetctileList.get(i).update();
                    }
                    if(aProjetctileList.get(i).isAlive() == false){
                        aProjetctileList.remove(i);
                    }
                }
            }

            //InteractiveTile
            for (int i = 0; i < aInteractiveTile.length; i++) {
                if (aInteractiveTile[i] != null) {
                    aInteractiveTile[i].update();
                }
            }
        }
        if (aGameState == aPauseState) {

        }
    }

    public void paintComponent(Graphics pGraphics) {
        super.paintComponent(pGraphics);
        Graphics2D g2d = (Graphics2D) pGraphics;

        // Title Screen

        if (aGameState == aTitleState) {
            aGUI.draw(g2d);
        } else {

            // Tiles
            aTileManager.draw(g2d);

            //InteractiveTile
            for (int i = 0; i < aInteractiveTile.length; i++) {
                if (aInteractiveTile[i] != null) {
                    aInteractiveTile[i].draw(g2d);
                }
            }

            // Add entity to list
            aEntityList.add(aPlayer);
            for (int i = 0; i < aNPC.length; i++) {
                if (aNPC[i] != null) {
                    aEntityList.add(aNPC[i]);
                }
            }
            for (int i = 0; i < aItems.length; i++) {
                if (aItems[i] != null) {
                    aEntityList.add(aItems[i]);
                }
            }
            for (int i = 0; i < aMonster.length; i++) {
                if (aMonster[i] != null) {
                    aEntityList.add(aMonster[i]);
                }
            }
            for (int i = 0; i < aProjetctileList.size(); i++) {
                if (aProjetctileList.get(i) != null) {
                    aEntityList.add(aProjetctileList.get(i));
                }
            }
            // Sort
            Collections.sort(aEntityList, new Comparator<Entity>() {

                @Override
                public int compare(Entity pEntity1, Entity pEntity2) {
                    int vResult = Integer.compare(pEntity1.getWorldY() + pEntity1.getSolidArea().y,
                            pEntity2.getWorldY() + pEntity2.getSolidArea().y);
                    return vResult;
                }

            });

            // Draw entities
            for (Entity vEntity : aEntityList) {
                vEntity.draw(g2d);
            }
            // clear list
            aEntityList.clear();

            // UI
            aGUI.draw(g2d);

            g2d.dispose();
        }
    }

    public void resetGame(){
        //reset all asset
        setAsset();
        //reset player values and stuff
        //aPlayer.restoreLifeAndAmmo();
        aPlayer.setDefault();
        aPlayer.setItems();
    }

    public void playMusic(int pIndex) {
        aMusic.setFile(pIndex);
        aMusic.play();
        aMusic.loop();
    }

    public void stopMusic() {
        aMusic.stop();
    }

    public void playSoundEffect(int pIndex) {
        aSoundEffect.setFile(pIndex);
        aSoundEffect.play();
    }

    public int getGameState() {
        return aGameState;
    }

    public int getTitleState() {
        return aTitleState;
    }

    public int getPlayState() {
        return aPlayState;
    }

    public int getPauseState() {
        return aPauseState;
    }

    public int getDialogueState() {
        return aDialogueState;
    }

    public int getCharacterState() {
        return aCharacterState;
    }

    public int getOptionState() {
        return aOptionState;
    }

    public int getGameOverState() {
        return aGameOverState;
    }

    public int getTradeState() {
        return aTradeState;
    }

    public int getTileSize() {
        return aTileScale;
    }

    public int getScreenWidth() {
        return aScreenWidth;
    }

    public int getScreenHeight() {
        return aScreenHeight;
    }

    public int getMaxWorldCol() {
        return aMaxWorldCol;
    }

    public int getMaxWorldRow() {
        return aMaxWorldRow;
    }

    public ArrayList<Entity> getProjectileList() {
        return aProjetctileList;
    }

    public Player getPlayer() {
        return aPlayer;
    }

    public TileManager getTileManager() {
        return aTileManager;
    }

    public CollisionChecker getCollisionChecker() {
        return aCollisionChecker;
    }

    public Entity[] getItems() {
        return aItems;
    }

    public Entity getItem(int pIndex) {
        return aItems[pIndex];
    }

    public Entity[] getNPC() {
        return aNPC;
    }

    public Entity getNPC(int pIndex) {
        return aNPC[pIndex];
    }

    public Entity[] getMonster() {
        return aMonster;
    }

    public Entity getMonster(int pIndex) {
        return aMonster[pIndex];
    }

    public InteractiveTile getInteractiveTile(int pIndex) {
        return aInteractiveTile[pIndex];
    }

    public InteractiveTile[] getInteractiveTile() {
        return aInteractiveTile;
    }

    public Sound getMusic() {
        return aMusic;
    }

    public Sound getSoundEffect() {
        return aSoundEffect;
    }

    public UserInterface getUserInterface() {
        return aGUI;
    }

    public EventHandler getEventHandler() {
        return aEventHandler;
    }

    public Input getInput() {
        return aInput;
    }

    public Config getConfig() {
        return aConfig;
    }

    public void setGameState(int pGameState) {
        aGameState = pGameState;
    }

    public void setNPC(Entity pNPC, int pIndex) {
        aNPC[pIndex] = pNPC;
    }

    public void setItem(Entity pItem, int pIndex) {
        aItems[pIndex] = pItem;
    }

    public void setMonster(Entity pMonster, int pIndex) {
        aMonster[pIndex] = pMonster;
    }

    public void setInteractiveTile(InteractiveTile pInteractiveTile, int pIndex) {
        aInteractiveTile[pIndex] = pInteractiveTile;
    }
}
