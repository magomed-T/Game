package representation;

import javax.sound.sampled.*;
import java.io.File;

/**
 * La classe SoundNode est un décorateur pour un Event qui ajoute la fonctionnalité de jouer un son.
 * Elle étend la classe DecorateurNode.
 */
public class SoundNode extends DecorateurNode {

    /** Le chemin du fichier audio à jouer. */
    private String fileName;

    /** Le volume du son à jouer. */
    private float volume;

    /**
     * Constructeur avec un Event, un nom de fichier audio et un volume spécifiés.
     * 
     * @param E        L'Event à décorer.
     * @param fileName Le nom du fichier audio.
     * @param volume   Le volume du son à jouer.
     */
    public SoundNode(Event E, String fileName, float volume) {
        super(E);
        this.fileName = fileName;
        this.volume = volume;
    }

    /**
     * Affiche le contenu de l'Event et joue le son associé.
     */
    @Override
    public void display() {
        // Chemin complet du fichier audio
        String filePath = "representation/Sons/" + this.fileName;
        // Joue le son avec le volume spécifié
        try{
        playAudio(filePath, this.volume);
        }
        catch(Exception e){
            System.out.println("\nL'AUDIO N'A PAS ETE EXECUTE\n");
        }
        finally{
        // Affiche le contenu de l'Event décoré
        super.display();
        }
    }

    /**
     * Joue un fichier audio avec un volume spécifié.
     * 
     * @param filePath Le chemin du fichier audio.
     * @param volume   Le volume du son à jouer.
     */
    public static void playAudio(String filePath, float volume) throws Exception{
            // Initialisation du fichier audio
            File audioFile = new File(filePath);

            // Lecture des données audio
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

            // Lecteur audio
            Clip clip = AudioSystem.getClip(); // Initialisation lecteur audio
            clip.open(audioStream); // Affectation des données audio

            // Gestion du volume audio
            FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volumeControl.setValue(volume); // Réduit le son de 'volume' dB
            clip.start(); // Joue le clip audio


            // Attente de la fin de l'audio
            /* 
            clip.addLineListener(event -> { // Joue un audio en arrière-plan (Thread)
                if (event.getType() == LineEvent.Type.STOP) {
                    clip.close(); // Ferme le clip une fois la lecture terminée
                }
            });
            */
    }
}

