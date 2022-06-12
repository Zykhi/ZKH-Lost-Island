import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
    private Clip aClip;
    private URL aSoundUrl[] = new URL[30];

    public Sound(){
        aSoundUrl[0] = getClass().getResource("/sound/GameTheme.wav");
    }

    public void setFile(int pIndex){
        try {
            AudioInputStream vAudioInputStream = AudioSystem.getAudioInputStream(aSoundUrl[pIndex]);
            aClip = AudioSystem.getClip();
            aClip.open(vAudioInputStream);

        } catch (Exception e) {
        }
    }

    public void play(){
        aClip.start();
    }
    public void loop(){
        aClip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
        aClip.stop();
    }
    
}
