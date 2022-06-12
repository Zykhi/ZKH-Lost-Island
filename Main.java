import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        
        JFrame aWindow = new JFrame("ZKH Lost Island");
        aWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        aWindow.setResizable(false);

        GamePanel aGamePanel = new GamePanel();
        aWindow.add(aGamePanel);

        aWindow.pack();
        aWindow.setVisible(true);
    }
}