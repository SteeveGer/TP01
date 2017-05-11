package dao;
import outils.FileStr;
import objets.Personne;
import objets.Cadeau;
import java.util.*;
/**
 *
 * @author steeve.gerson
 */
public class CadeauxNoelDao {
    
    private static ArrayList<Personne> listePersonnes = new ArrayList();
    private static ArrayList<Cadeau> listeCadeaux = new ArrayList();
    
    private static final String DEL_CHAMPS = ";";
    
    public static String[] readFile(String filename){
        return FileStr.read(filename);
    }

    public static void writeFile(String filename, String[] tab){
        FileStr.write(filename, tab);
    }
    
    /*Lis le fichier de données des personnes*/
    public static void readPersonne(String filename){
        String[] tabPers = readFile(filename);
        
        for(int i = 0; i < tabPers.length; i++){
           String lignePers = tabPers[i];
           separerChampsPersonnes(lignePers);
        }
    }
    
    /*Reçoit chaque ligne du tableau de String retourné par readFile, sépare les champs et appel la procédure d'instanciation d'une Personne*/
    private static void separerChampsPersonnes(String ligne){
        ArrayList lstCadeauxPersonnes = new ArrayList();
        StringTokenizer stChamp = new StringTokenizer(ligne, DEL_CHAMPS);
        String nom = stChamp.nextToken();
        if(stChamp.hasMoreTokens()){
            while(stChamp.hasMoreTokens()){
                int id = Integer.parseInt(stChamp.nextToken());
                Integer idCadeaux = new Integer(id);
                lstCadeauxPersonnes.add(idCadeaux);
            }
        }
        ajouterPersonne(creerPersonne(nom, lstCadeauxPersonnes));
    }
    
    /*Créér une instance de personne*/
    private static Personne creerPersonne(String nom, ArrayList lst){
        Personne p = new Personne(nom, lst);
        return p;
    }
    
    /*Ajoute la personne créée dans une ArrayList contenant toutes les personnes "listePersonnes"*/
    private static void ajouterPersonne(Personne p){
        listePersonnes.add(p);
    }
    
    /*Lis le fichier de données des cadeaux*/
    public static void readCadeau(String filename){
        String[] tabCad = readFile(filename);
        for(int i = 0; i < tabCad.length; i++){
            String ligneCad = tabCad[i];
            separerChampsCadeaux(ligneCad);
        }
    }
    
    /*Reçoit chaque ligne du tableau de String retourné par readFile, sépare les champs et appel la procédure d'instanciation d'un cadeau*/
    private static void separerChampsCadeaux(String ligne){
        StringTokenizer stChamp = new StringTokenizer(ligne, DEL_CHAMPS);
        int id = Integer.parseInt(stChamp.nextToken());
        String libele = stChamp.nextToken();
        int prix = Integer.parseInt(stChamp.nextToken());
        ajouterCadeau(creerCadeaux(id, libele, prix));
    }
    
    /*Créér une instance de cadeau*/
    private static Cadeau creerCadeaux(int id, String libele, int prix){
        Cadeau c = new Cadeau(id, libele, prix);
        return c;
    }
    
    /*Ajoute le cadeau créé dans une ArrayList contenant tous les cadeaux "listeCadeaux"*/
    private static void ajouterCadeau(Cadeau c){
        listeCadeaux.add(c);
    }
    
    /*Retourne l'ArrayList de tous les cadeaux*/
    public static ArrayList getListeCadeaux(){return listeCadeaux;}
    
    /*Retourne l'ArrayList de toutes les personnes*/
    public static ArrayList getListePersonnes(){return listePersonnes;}
}
