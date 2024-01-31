package examples.arduino;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * @author Antony Garcia Gonzalez
 * <br>Un codigo que permite recibir datos desde el Arduino. Debe ser utilizado
 * con el codigo single_data_send.ino corriendo en el Arduino
 */
public class wawa {

    public static void main(String[] args) throws MalformedURLException, IOException, LineUnavailableException {   
        AudioInputStream audioIn = null;
        try {
            File f = new File("C:/Users/marti/Downloads/y2mate.com - Cencor beep sound effect.wav");
            audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL());
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(wawa.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                audioIn.close();
            } catch (IOException ex) {
                Logger.getLogger(wawa.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
