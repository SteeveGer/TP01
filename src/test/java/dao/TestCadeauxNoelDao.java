/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import java.util.StringTokenizer;
import objets.Cadeau;
import objets.Personne;
import org.testng.annotations.*;
import static org.testng.Assert.assertEquals;
/**
 *
 * @author Steeve
 */
public class TestCadeauxNoelDao {
    
    private static final String DEL_CHAMPS = ";";
    private static ArrayList listePersonnes = new ArrayList();
    private static ArrayList listeCadeaux = new ArrayList();
    
    @Test
    private static void separerChampsPersonnes(String ligne){
        ligne = "Pierre;1;3;12;5";
        ArrayList lstCadeauxPersonnes = new ArrayList();
        StringTokenizer stChamp = new StringTokenizer(ligne, DEL_CHAMPS);
        String nom = stChamp.nextToken();
        int id = Integer.parseInt(stChamp.nextToken());
        Integer idCadeaux = new Integer(id);
        lstCadeauxPersonnes.add(idCadeaux);
        ajouterPersonne(creerPersonne(nom, lstCadeauxPersonnes));
        assertEquals(nom.equals("Pierre"), true);
    }
    
    @Test
    private static Personne creerPersonne(String nom, ArrayList lst){
        Personne p = new Personne(nom, lst);
        return p;
    }
    
    @Test
    private static void ajouterPersonne(Object p){
        if(p instanceof Personne){
            assertEquals(listePersonnes.add(p), true);
        }else{assertEquals(listePersonnes.add(p), false);}
        
    }
    
    @Test
    private static void separerChampsCadeaux(String ligne){
        StringTokenizer stChamp = new StringTokenizer(ligne, DEL_CHAMPS);
        int id = Integer.parseInt(stChamp.nextToken());
        String libele = stChamp.nextToken();
        int prix = Integer.parseInt(stChamp.nextToken());
        ajouterCadeau(creerCadeaux(id, libele, prix));
    }
    
    @Test
    private static Cadeau creerCadeaux(int id, String libele, int prix){
        Cadeau c = new Cadeau(id, libele, prix);
        return c;
    }
    
    @Test
    private static void ajouterCadeau(Object c){
        if(c instanceof Cadeau){
            assertEquals(listeCadeaux.add(c), true);
        }else{assertEquals(listeCadeaux.add(c), false);}
    }
}
