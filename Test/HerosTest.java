/*
// Nous avons fait les tests Mais les tests Junit ne fonctionnait pas sur nos ordinateurs.

package Test;
import org.junit.Test;
import univers.Objets.Objet;
import univers.Personnages.Heros;

import static org.junit.Assert.*;

public class HerosTest {

    @Test
    public void testConstructeur() {
        Heros heros = new Heros("Heros");
        assertEquals("Heros", heros.getNom());
    }

    @Test
    public void testAddObjet() {
        Heros heros = new Heros("Heros");
        heros.addObjet(Objet.AILES_ICARE);
        assertTrue(heros.hasObjet(Objet.AILES_ICARE));
    }
    @Test
    public void testHasObjet() {
        Heros heros = new Heros("Heros");
        Objet objet = Objet.BOUCLIER_MIROIR;
        heros.addObjet(objet);
        assertTrue(heros.hasObjet(objet));
        assertFalse(heros.hasObjet(Objet.AMULETTE_OEDIPE));
    }
}
 */