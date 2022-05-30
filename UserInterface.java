import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

public class UserInterface {
    JFrame aWindow;
    final int aTileSize = 16; // 16x16 pixels
    final int aScale = 3;
    final int aTileScale = aTileSize * aScale; // 48x48
    final int aMaxScreenCols = 16;
    final int aMaxScreenRows = 12;
    final int aScreenWidth = aTileScale * aMaxScreenCols; // 768 pixels
    final int aScreenHeight = aTileScale * aMaxScreenRows; // 576 pixels
    
    public UserInterface() {
        createPanel();
    }

    private void createPanel() {
        aWindow = new JFrame("ZKH Lost Island");
        aWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        aWindow.setPreferredSize(new Dimension(aScreenWidth, aScreenHeight));
        aWindow.setResizable(false);
        aWindow.setBackground(Color.BLACK);

        aWindow.pack();
        aWindow.setVisible(true);
    
    }


}
