import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Config {
    GamePanel aGamePanel;

    public Config(GamePanel pGamePanel) {
        this.aGamePanel = pGamePanel;
    }

    public void saveConfig() {
        try {
            BufferedWriter vBufferedWriter = new BufferedWriter(new FileWriter("config.txt"));

            // Music
            vBufferedWriter.write(String.valueOf(aGamePanel.getMusic().getVolume()));
            vBufferedWriter.newLine();
            // SE
            vBufferedWriter.write(String.valueOf(aGamePanel.getSoundEffect().getVolume()));
            vBufferedWriter.newLine();

            vBufferedWriter.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void loadConfig() {
        try {
            BufferedReader vBufferedReader = new BufferedReader(new FileReader("config.txt"));

            String vTempString;

            // Music
            vTempString = vBufferedReader.readLine();
            aGamePanel.getMusic().setVolume(Integer.parseInt(vTempString));

            // SE
            vTempString = vBufferedReader.readLine();
            aGamePanel.getSoundEffect().setVolume(Integer.parseInt(vTempString));

            vBufferedReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}