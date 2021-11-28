import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import javax.swing.JOptionPane;
//----------------------------------------------------------------------------
//Programmet skall spela upp ett ljud n�r Boolean = true
//----------------------------------------------------------------------------
public class GameSound {
    //----------------------------------------------------------------------------
//Deklaratioiner
//----------------------------------------------------------------------------
    boolean play = true;
    Clip gameMusic;
    //----------------------------------------------------------------------------
//Konstruktor
//----------------------------------------------------------------------------
    public void playBgMusic(String musicLocation) {
        try {
            File musicPath = new File(musicLocation);
            if(musicPath.exists()){

                AudioInputStream audioInput =
                        AudioSystem.getAudioInputStream(musicPath);
                gameMusic = AudioSystem.getClip();
                gameMusic.open(audioInput);
                gameMusic.start();
                gameMusic.loop(gameMusic.LOOP_CONTINUOUSLY);
            }//end if
            else{
                System.out.println("Can't find sound file");

            }//end else
        }//end try
        catch (Exception ex) {
            ex.printStackTrace();
        }//end catch
    }//end playBgMusic
    //----------------------------------------------------------------------------
    public void playBreak() {
        try {
            AudioInputStream audioInputStream =
                    AudioSystem.getAudioInputStream(this.getClass().getResource("breakBlockSound.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        }//end try
        catch (Exception ex) {
            ex.printStackTrace();
        }//end catch
    }//end playBreak
    //----------------------------------------------------------------------------
    public void playBounce(){
        try {
            AudioInputStream audioInputStream =
                    AudioSystem.getAudioInputStream(this.getClass().getResource("ballbounce.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        }//end try
        catch (Exception ex) {
            ex.printStackTrace();
        }//end catch
    }//end playBounce
    //----------------------------------------------------------------------------
    public void playGameOver(){
        try {
            AudioInputStream audioInputStream =
                    AudioSystem.getAudioInputStream(this.getClass().getResource("GameOverSound.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        }//end try
        catch (Exception ex) {
            ex.printStackTrace();
        }//end catch
    }//end playGameOver
    //----------------------------------------------------------------------------
    public void playWinGame(){
        try {
            AudioInputStream audioInputStream =
                    AudioSystem.getAudioInputStream(this.getClass().getResource("VictorySound.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        }//end try
        catch (Exception ex) {
            ex.printStackTrace();
        }//end catch
    }//end playWinGame
} //end class
//----------------------------------------------------------------------------
//----------------------------------------------------------------------------