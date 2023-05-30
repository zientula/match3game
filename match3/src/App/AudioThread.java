package App;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

/**
 * Klasa związana z odtwarzaniem i zatrzymywaniem muzyki w naszej grze w konkretnych momentach.
 */
public class AudioThread{
    private Clip clip;

    public AudioThread(String fp){
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(new File(fp).getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void run(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop() {clip.stop();}
    public void close() {clip.close();}
    public void start(){clip.start();}
}
