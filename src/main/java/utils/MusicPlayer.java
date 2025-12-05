package utils;

import javax.sound.sampled.*;
import java.io.IOException;
import java.io.InputStream;

public class MusicPlayer {

    private static Clip clip;

    public static void play(String resourcePath) {
        try {
            if (clip != null && clip.isRunning()) {
                clip.stop();
                clip.close();
            }

            InputStream is = MusicPlayer.class.getResourceAsStream(resourcePath);
            if (is == null) {
                System.out.println("No se encontró el recurso: " + resourcePath);
                return;
            }

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(is);

            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);

            FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volume.setValue(-15.0f);

            clip.start();

        } catch (Exception e) {
            System.out.println("Error al reproducir música: " + e.getMessage());
        }
    }
}