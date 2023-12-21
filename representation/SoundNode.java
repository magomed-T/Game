package representation;
import javax.sound.sampled.*;
import java.io.File;
import java.util.Scanner;


public class SoundNode extends DecorateurNode{

    private String fileName;
    private float volume;
    // Joue le son donnée par le chemin du fichier avec un volume fixé.
    public static void playAudio(String filePath,float volume){
        try{

        // Initialisation du fichier Audio
        File audioFile = new File(filePath);

        // Lecture des données audio 
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);  
        
        // Lecteur audio
        Clip clip = AudioSystem.getClip(); // Initialisation lecteur audio
        clip.open(audioStream); // Affectation des donnée audios

        // Gestion du volume Audio
        FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        volumeControl.setValue(volume); // Réduit le son de 'volume' dB
        clip.start();  // Joue le clip audio

        // clip.loop(Clip.LOOP_CONTINUOUSLY); //Permet de jouer le son en boucle

        // Attente de la fin de l'audio
        clip.addLineListener(event -> {    // Joue un audio en arrière plan ( Thread )
            if (event.getType() == LineEvent.Type.STOP) {
                clip.close(); // Ferme le clip une fois la lecture terminée
            }
        });
        } 

        // Relève l'erreur s'il y'en a une
        catch(Exception e){
            e.printStackTrace();
        }

    }

    public SoundNode(Event E,String fileName,float volume){
        super(E);
        this.fileName= fileName;
        this.volume = volume;
    }
    @Override
    public void display(){
        String filePath = "representation/Sons/"+this.fileName ;
        playAudio(filePath,this.volume); // Volume autorisé de : 
        super.display();
    }
}
