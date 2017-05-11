package vue;

import java.util.ArrayList;
import objets.Cadeau;
import objets.Personne;
import org.testng.annotations.*;
import static org.testng.Assert.assertEquals;
/**
 *
 * @author steeve.gerson
 */

public class TestFrmMain {
   
    private static ArrayList listePersonnes;
    private static ArrayList listeCadeaux = FrmMain.listeCadeaux;
    
    @Test
    public void chercherPersonne(String nom){
        nom = "Pierre";
        Personne p = new Personne(nom, new ArrayList());
        assertEquals(listePersonnes.contains(p), true);
    }
    
    @Test
    public void calculerPrix(int prix, int index){
        for(index = 0; index < listeCadeaux.size(); index++){
            prix += ((Cadeau)listeCadeaux.get(index)).getPrix();
        }
        assertEquals(prix > 0, true);
    }
}
