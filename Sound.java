import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {
    private Clip aClip;
    private URL aSoundUrl[] = new URL[30];
    FloatControl aVolume;
    int aVolumeScale = 3;
    float aVolumeValue;

    public Sound() {
        aSoundUrl[0] = getClass().getResource("/sound/GameTheme.wav");
    }

    public void setFile(int pIndex) {
        try {
            AudioInputStream vAudioInputStream = AudioSystem.getAudioInputStream(aSoundUrl[pIndex]);
            aClip = AudioSystem.getClip();
            aClip.open(vAudioInputStream);
            aVolume = (FloatControl) aClip.getControl(FloatControl.Type.MASTER_GAIN);
            checkVolume();
        } catch (Exception e) {
        }
    }

    public void play() {
        aClip.start();
    }

    public void loop() {
        aClip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        aClip.stop();
    }

    public void checkVolume() {
        switch (aVolumeScale) {
            case 0:
                aVolumeValue = -80.0f;
                break;
            case 1:
                aVolumeValue = -20.0f;
                break;

            case 2:
                aVolumeValue = -12f;
                break;
            
            case 3:
                aVolumeValue = -5f;
                break;

            case 4:
                aVolumeValue = 1.0f;
                break;

            case 5:
                aVolumeValue = 6f;
                break;
        }
        aVolume.setValue(aVolumeValue);
    }

    public int getVolume() {
        return aVolumeScale;
    }

    public void setVolume(int pVolume) {
        aVolumeScale = pVolume;
    }
}
