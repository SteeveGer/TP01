package objets;
import java.util.ArrayList;

/**
 * Module 633.1-Programmation / TP Série P10
 * 
 * Représentation d'une Personne
 *
 * @author Peter DAEHNE - HEG-Genève
 *
*/
public class Personne {
  
  private String nom;
  private ArrayList idsCadeaux; /* Liste des identifiants des cadeaux de la Personne */
  
  /* Constructeur */
  public Personne (String nom, ArrayList idsCadeaux) {this.nom = nom; this.idsCadeaux = idsCadeaux;}

  /* Accesseurs */
  public String getNom () {return nom;}
  public ArrayList getIdsCadeaux () {return idsCadeaux;}

  public boolean equals (Object o) {return nom.equals(((Personne)o).nom);}
  
  public String toString () {return nom;}
  
} // Personne