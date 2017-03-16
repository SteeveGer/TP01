package objets;

/**
 * Module 633.1-Programmation / TP Série P10
 * 
 * Représentation d'un Cadeau
 *
 * @author Peter DAEHNE - HEG-Genève
 *
*/
public class Cadeau {
  
  private int id;         /* Identifiant */
  private String libelle; /* Libellé du cadeau */
  private int prix;       /* Prix du cadeau */

  /* Constructeur */
  public Cadeau (int id, String libelle, int prix) {this.id = id; this.libelle = libelle; this.prix = prix;}
  public Cadeau (int id) {this(id, null, 0);}

  /* Accesseurs */
  public int getId () {return id;}
  public String getLibelle () {return libelle;}
  public int getPrix () {return prix;}

  public boolean equals (Object o) {return id == ((Cadeau)o).id;}
  
  public String toString () {return libelle + " [" + prix + ".-]";}
  
} // Cadeau
