import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Input implements KeyListener {

    private boolean aUp;
    private boolean aDown;
    private boolean aLeft;
    private boolean aRight;
    private boolean aEnter;
    private boolean aShot;
    private GamePanel aGamePanel;

    public Input(GamePanel pGamePanel) {
        this.aGamePanel = pGamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int vKeyCode = e.getKeyCode();

        // Title State
        if (aGamePanel.getGameState() == aGamePanel.getTitleState()) {
            titleState(vKeyCode);
        }

        // Game State
        else if (aGamePanel.getGameState() == aGamePanel.getPlayState()) {
            playState(vKeyCode);
        }
        // Pause State
        else if (aGamePanel.getGameState() == aGamePanel.getPauseState()) {
            pauseState(vKeyCode);
        }
        // Dialogue state
        else if (aGamePanel.getGameState() == aGamePanel.getDialogueState()) {
            dialogueState(vKeyCode);
        }
        // Character state
        else if (aGamePanel.getGameState() == aGamePanel.getCharacterState()) {
            characterState(vKeyCode);
        }
        // Option state
        else if (aGamePanel.getGameState() == aGamePanel.getOptionState()) {
            optionState(vKeyCode);
        }
        // Game Over state
        else if (aGamePanel.getGameState() == aGamePanel.getGameOverState()) {
            gameOverState(vKeyCode);
        }
        // Trade state
        else if (aGamePanel.getGameState() == aGamePanel.getTradeState()) {
            tradeState(vKeyCode);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_Z) {
            aUp = false;
        }
        if (keyCode == KeyEvent.VK_S) {
            aDown = false;
        }
        if (keyCode == KeyEvent.VK_Q) {
            aLeft = false;
        }
        if (keyCode == KeyEvent.VK_D) {
            aRight = false;
        }
        if (keyCode == KeyEvent.VK_ENTER) {
            aEnter = false;
        }
        if (keyCode == KeyEvent.VK_F) {
            aShot = false;
        }

    }

    private void titleState(int pCode) {
        // Navigate into game title screen
        if (pCode == KeyEvent.VK_UP) {
            aGamePanel.getUserInterface().setCommandNumber(aGamePanel.getUserInterface().getCommandNumber() - 1);
            if (aGamePanel.getUserInterface().getCommandNumber() < 0) {
                aGamePanel.getUserInterface().setCommandNumber(2);
            }
        }
        if (pCode == KeyEvent.VK_DOWN) {
            aGamePanel.getUserInterface().setCommandNumber(aGamePanel.getUserInterface().getCommandNumber() + 1);
            if (aGamePanel.getUserInterface().getCommandNumber() > 2) {
                aGamePanel.getUserInterface().setCommandNumber(0);
            }
        }
        // Select state
        if (pCode == KeyEvent.VK_ENTER) {
            if (aGamePanel.getUserInterface().getCommandNumber() == 0) {
                aGamePanel.setGameState(aGamePanel.getPlayState());
                aGamePanel.playMusic(0);

            }
            if (aGamePanel.getUserInterface().getCommandNumber() == 1) {
                // TODO : Save State
            }
            if (aGamePanel.getUserInterface().getCommandNumber() == 2) {
                System.exit(0);
            }
        }
    }

    private void playState(int pCode) {
        if (pCode == KeyEvent.VK_Z) {
            aUp = true;
        }
        if (pCode == KeyEvent.VK_S) {
            aDown = true;
        }
        if (pCode == KeyEvent.VK_Q) {
            aLeft = true;
        }
        if (pCode == KeyEvent.VK_D) {
            aRight = true;
        }
        if (pCode == KeyEvent.VK_P) {
            aGamePanel.setGameState(aGamePanel.getPauseState());
        }
        if (pCode == KeyEvent.VK_C) {
            aGamePanel.setGameState(aGamePanel.getCharacterState());
        }
        if (pCode == KeyEvent.VK_ENTER) {
            aEnter = true;
        }
        if (pCode == KeyEvent.VK_F) {
            aShot = true;
        }
        if (pCode == KeyEvent.VK_ESCAPE) {
            aGamePanel.setGameState(aGamePanel.getOptionState());
        }
    }

    private void pauseState(int pCode) {
        if (pCode == KeyEvent.VK_P) {
            aGamePanel.setGameState(aGamePanel.getPlayState());
        }
        if (pCode == KeyEvent.VK_ESCAPE) {
            aGamePanel.setGameState(aGamePanel.getPlayState());
        }
    }

    private void dialogueState(int pCode) {
        if (pCode == KeyEvent.VK_ENTER) {
            aGamePanel.setGameState(aGamePanel.getPlayState());
        }
    }

    private void characterState(int pCode) {
        // Go back to game
        if (pCode == KeyEvent.VK_C) {
            aGamePanel.setGameState(aGamePanel.getPlayState());
        }
        if (pCode == KeyEvent.VK_ESCAPE) {
            aGamePanel.setGameState(aGamePanel.getPlayState());
        }

        // move into character state
        playerInventory(pCode);

        // select
        if (pCode == KeyEvent.VK_ENTER) {
            aGamePanel.getPlayer().selectItem();
        }
    }

    private void optionState(int pCode) {
        if (pCode == KeyEvent.VK_ESCAPE) {
            aGamePanel.setGameState(aGamePanel.getPlayState());
            aGamePanel.getUserInterface().setCommandNumber(0);
        }
        if (pCode == KeyEvent.VK_ENTER) {
            aEnter = true;
        }

        // Navigate into option screen
        int vMaxCommandNum = 0;
        switch (aGamePanel.getUserInterface().getSubState()) {
            case 0:
                vMaxCommandNum = 4;
                break;
            case 2:
                vMaxCommandNum = 1;
                break;
        }
        if (pCode == KeyEvent.VK_UP) {
            aGamePanel.getUserInterface().setCommandNumber(aGamePanel.getUserInterface().getCommandNumber() - 1);
            if (aGamePanel.getUserInterface().getCommandNumber() < 0) {
                aGamePanel.getUserInterface().setCommandNumber(vMaxCommandNum);
            }
        }
        if (pCode == KeyEvent.VK_DOWN) {
            aGamePanel.getUserInterface().setCommandNumber(aGamePanel.getUserInterface().getCommandNumber() + 1);
            if (aGamePanel.getUserInterface().getCommandNumber() > vMaxCommandNum) {
                aGamePanel.getUserInterface().setCommandNumber(0);
            }
        }

        // Change volume
        if (pCode == KeyEvent.VK_LEFT) {
            if (aGamePanel.getUserInterface().getSubState() == 0) {
                if (aGamePanel.getUserInterface().getCommandNumber() == 0 && aGamePanel.getMusic().getVolume() > 0) {
                    aGamePanel.getMusic().setVolume(aGamePanel.getMusic().getVolume() - 1);
                    aGamePanel.getMusic().checkVolume();
                }
                if (aGamePanel.getUserInterface().getCommandNumber() == 1
                        && aGamePanel.getSoundEffect().getVolume() > 0) {
                    aGamePanel.getSoundEffect().setVolume(aGamePanel.getSoundEffect().getVolume() - 1);
                }
            }
        } else if (pCode == KeyEvent.VK_RIGHT) {
            if (aGamePanel.getUserInterface().getSubState() == 0) {
                if (aGamePanel.getUserInterface().getCommandNumber() == 0 && aGamePanel.getMusic().getVolume() < 5) {
                    aGamePanel.getMusic().setVolume(aGamePanel.getMusic().getVolume() + 1);
                    aGamePanel.getMusic().checkVolume();
                }
                if (aGamePanel.getUserInterface().getCommandNumber() == 1
                        && aGamePanel.getSoundEffect().getVolume() < 5) {
                    aGamePanel.getSoundEffect().setVolume(aGamePanel.getSoundEffect().getVolume() + 1);
                }
            }
        }
    }

    private void gameOverState(int pCode) {
        if (pCode == KeyEvent.VK_ENTER) {
            if (aGamePanel.getUserInterface().getCommandNumber() == 0) {
                aGamePanel.resetGame();
                aGamePanel.setGameState(aGamePanel.getPlayState());
            } else {
                aGamePanel.setGameState(aGamePanel.getTitleState());
                aGamePanel.stopMusic();
                aGamePanel.resetGame();
                aGamePanel.getUserInterface().setCommandNumber(0);
            }
        }
        if (pCode == KeyEvent.VK_UP) {
            aGamePanel.getUserInterface().setCommandNumber(aGamePanel.getUserInterface().getCommandNumber() - 1);
            if (aGamePanel.getUserInterface().getCommandNumber() < 0) {
                aGamePanel.getUserInterface().setCommandNumber(1);
            }
        }
        if (pCode == KeyEvent.VK_DOWN) {
            aGamePanel.getUserInterface().setCommandNumber(aGamePanel.getUserInterface().getCommandNumber() + 1);
            if (aGamePanel.getUserInterface().getCommandNumber() > 1) {
                aGamePanel.getUserInterface().setCommandNumber(0);
            }
        }
    }

    private void tradeState(int pCode) {
        if (pCode == KeyEvent.VK_ENTER) {
            aEnter = true;
        }
        if (aGamePanel.getUserInterface().getSubState() == 0) {
            if (pCode == KeyEvent.VK_UP) {
                aGamePanel.getUserInterface().setCommandNumber(aGamePanel.getUserInterface().getCommandNumber() - 1);
                if (aGamePanel.getUserInterface().getCommandNumber() < 0) {
                    aGamePanel.getUserInterface().setCommandNumber(2);
                }
            }
            if (pCode == KeyEvent.VK_DOWN) {
                aGamePanel.getUserInterface().setCommandNumber(aGamePanel.getUserInterface().getCommandNumber() + 1);
                if (aGamePanel.getUserInterface().getCommandNumber() > 2) {
                    aGamePanel.getUserInterface().setCommandNumber(0);
                }
            }
        }
        if(aGamePanel.getUserInterface().getSubState() == 1){
            //Move in npc inventory
            npcInventory(pCode);
            //escape input
            if(pCode == KeyEvent.VK_ESCAPE){
                aGamePanel.getUserInterface().setSubState(0);
            }
        }if(aGamePanel.getUserInterface().getSubState() == 2){
            //Move in npc inventory
            playerInventory(pCode);
            //escape input
            if(pCode == KeyEvent.VK_ESCAPE){
                aGamePanel.getUserInterface().setSubState(0);
            }
        }
    }

    private void playerInventory(int pCode) {
        if (pCode == KeyEvent.VK_UP) {
            if (aGamePanel.getUserInterface().getSlotRow() != 0) {
                aGamePanel.getUserInterface().setSlotRow(aGamePanel.getUserInterface().getSlotRow() - 1);
            }
        }
        if (pCode == KeyEvent.VK_DOWN) {
            if (aGamePanel.getUserInterface().getSlotRow() != 3) {
                aGamePanel.getUserInterface().setSlotRow(aGamePanel.getUserInterface().getSlotRow() + 1);
            }
        }
        if (pCode == KeyEvent.VK_RIGHT) {
            if (aGamePanel.getUserInterface().getSlotCol() != 4) {
                aGamePanel.getUserInterface().setSlotCol(aGamePanel.getUserInterface().getSlotCol() + 1);
            }
        }
        if (pCode == KeyEvent.VK_LEFT) {
            if (aGamePanel.getUserInterface().getSlotCol() != 0) {
                aGamePanel.getUserInterface().setSlotCol(aGamePanel.getUserInterface().getSlotCol() - 1);
            }
        }
    }

    private void npcInventory(int pCode) {
        if (pCode == KeyEvent.VK_UP) {
            if (aGamePanel.getUserInterface().getNPCSlotRow() != 0) {
                aGamePanel.getUserInterface().setNPCSlotRow(aGamePanel.getUserInterface().getNPCSlotRow() - 1);
            }
        }
        if (pCode == KeyEvent.VK_DOWN) {
            if (aGamePanel.getUserInterface().getNPCSlotRow() != 3) {
                aGamePanel.getUserInterface().setNPCSlotRow(aGamePanel.getUserInterface().getNPCSlotRow() + 1);
            }
        }
        if (pCode == KeyEvent.VK_RIGHT) {
            if (aGamePanel.getUserInterface().getNPCSlotCol() != 4) {
                aGamePanel.getUserInterface().setNPCSlotCol(aGamePanel.getUserInterface().getNPCSlotCol() + 1);
            }
        }
        if (pCode == KeyEvent.VK_LEFT) {
            if (aGamePanel.getUserInterface().getNPCSlotCol() != 0) {
                aGamePanel.getUserInterface().setNPCSlotCol(aGamePanel.getUserInterface().getNPCSlotCol() - 1);
            }
        }
    }

    public boolean isUp() {
        return aUp;
    }

    public boolean isDown() {
        return aDown;
    }

    public boolean isLeft() {
        return aLeft;
    }

    public boolean isRight() {
        return aRight;
    }

    public boolean isEnter() {
        return aEnter;
    }

    public boolean isShot() {
        return aShot;
    }

    public void setEnter(boolean pEnter) {
        aEnter = pEnter;
    }

}
