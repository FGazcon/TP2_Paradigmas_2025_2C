package utils;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class MusicPlayer {

    private static Clip clip;

    public static void play(String path) {
        try {
            if (clip != null && clip.isRunning()) {
                clip.stop();
                clip.close();
            }

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(path));

            clip = AudioSystem.getClip();
            clip.open(audioStream);
            
            clip.loop(Clip.LOOP_CONTINUOUSLY);

            FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volume.setValue(-15.0f);

            clip.start();

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println("Error al reproducir música: " + e.getMessage());
        }
    }
}