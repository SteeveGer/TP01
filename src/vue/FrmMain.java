package vue;
import objets.*;
import java.util.*;
import dao.CadeauxNoelDao;


/**
 * Module 633.1-Programmation / TP Série P10
 * 
 * Fenêtre principale de l'application
 *
 * @author Steeve Gerson
 *
*/

public class FrmMain extends java.awt.Frame {
  
  private static final String PERSONNES = "Personnes.txt";
  private static final String CADEAUX = "Cadeaux.txt";
  private static ArrayList listePersonnes;
  private static ArrayList listeCadeaux;
  
  /* Constructeur */
  public FrmMain() {
    initComponents();
    chargerListes();
  } // Constructeur
  
  /*Charge les deux listes de données*/
  private void chargerListes(){
      chargerListePersonnes(PERSONNES);
      chargerListeCadeaux(CADEAUX);
  }
  
  /*Charge la liste des personnes, appels le StringTokenizer du DAO et stocke chaque personne dans une ArrayList "listePersonnes"*/
  private void chargerListePersonnes(String filename){
      CadeauxNoelDao.readPersonne(filename);
      listePersonnes = CadeauxNoelDao.getListePersonnes();
      for(int i = 0; i < listePersonnes.size(); i++){
          ajouterPersonnes((Personne)listePersonnes.get(i));
      }
  }
  
  /*Ajoute chaque personne créée dans la liste d'affichage*/
  private void ajouterPersonnes(Personne pers){
      int i = lstPersonnes.getItemCount()-1;
      while((i >= 0)&&(pers.getNom().compareTo(lstPersonnes.getItem(i))< 0)){
          i--;
      }
      lstPersonnes.add(pers.getNom());
  }
  
   /*Charge la liste des cadeaux, appels le StringTokenizer du DAO et stocke chaque cadeau dans une ArrayList "listeCadeaux"*/
  private void chargerListeCadeaux(String filename){
      CadeauxNoelDao.readCadeau(filename);
      listeCadeaux = CadeauxNoelDao.getListeCadeaux();
      for(int i = 0; i < listeCadeaux.size(); i++){
          ajouterCadeaux((Cadeau)listeCadeaux.get(i));
      }
  }
  
    /*Ajoute chaque cadeau créée dans la liste d'affichage*/
  private void ajouterCadeaux(Cadeau cadeau){
      int i = lstCadeaux.getItemCount()-1;
      while((i >= 0)&&(cadeau.getLibelle().compareTo(lstCadeaux.getItem(i))< 0)){
          i--;
      }
      lstCadeaux.add(cadeau.getLibelle()+" ["+cadeau.getPrix()+".-]");
  }
  
  /*Cherche la personne sélectionnée dans l'ArrayListe "listePersonnes"*/
  private Personne chercherPersonne(String nom){
      Personne p = new Personne(nom, new ArrayList());
      if(listePersonnes.contains(p)){
          int index = listePersonnes.indexOf(p);
          return (Personne)listePersonnes.get(index);
      }
      return null;
  }
  
  /*Cherche les cadeaux de la personne correspondantes, appel la méthode d'affichage des cadeaux, celles de calcul et d'affichage du prix*/
  private void chercherCadeaux(Personne p){
      int prix = 0;
      ArrayList lstIdCad = p.getIdsCadeaux();
      for(int i = 0; i < lstIdCad.size(); i++){
          int idCad = ((Integer)lstIdCad.get(i)).intValue();
          Cadeau c = new Cadeau(idCad);
          int index = listeCadeaux.indexOf(c);
          afficherCadeaux(index);   
          prix = calculerPrix(prix, index); 
      }
      afficherPrix(prix);
  }
  
  /*Afficher les cadeaux de la personne séléctionnée*/
  private void afficherCadeaux(int index){
      Cadeau cadeau = (Cadeau)listeCadeaux.get(index);
      lstCadeauxCourante.add(cadeau.toString());
  }
  
  /*Calcul le total du prix des cadeaux de la personne séléctionnée*/
  private int calculerPrix(int prix, int index){
      prix += ((Cadeau)listeCadeaux.get(index)).getPrix();
      return prix;
  }
  
  /*Affiche le prix total*/
  private void afficherPrix(int prix){
      lblSomme.setText("Somme totale: "+prix+".-");
  }
  
  private void ajouter(){
    Personne p = (Personne)listePersonnes.get(lstPersonnes.getSelectedIndex());
    Cadeau c = (Cadeau)listeCadeaux.get(lstCadeaux.getSelectedIndex());
    p.getIdsCadeaux().add(new Integer (c.getId()));
    lstCadeauxCourante.add(c.toString());
    ArrayList lstIdCad = p.getIdsCadeaux();
    int prix = 0;
    for(int i = 0; i<p.getIdsCadeaux().size(); i++){
        int idCad = ((Integer)p.getIdsCadeaux().get(i)).intValue();
        Cadeau cad = new Cadeau(idCad);
        int index = listeCadeaux.indexOf(cad);
        prix = calculerPrix(prix, index);
    }
  afficherPrix(prix);
  }
  
  private void enregistrer(){
      String[] tab = CadeauxNoelDao.readFile(PERSONNES);
      int idCadeau = ((Cadeau)listeCadeaux.get(lstCadeaux.getSelectedIndex())).getId();
      tab[lstPersonnes.getSelectedIndex()] = tab[lstPersonnes.getSelectedIndex()] +";"+idCadeau;
      CadeauxNoelDao.writeFile(PERSONNES, tab);
  }
  
  /**
   * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
   * content of this method is always regenerated by the Form Editor.
   */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        java.awt.Label label1 = new java.awt.Label();
        lstPersonnes = new java.awt.List();
        lblCadeauxPour = new java.awt.Label();
        lstCadeauxCourante = new java.awt.List();
        lblSomme = new java.awt.Label();
        java.awt.Label label2 = new java.awt.Label();
        lstCadeaux = new java.awt.List();
        btnAjouter = new java.awt.Button();

        setTitle("Cadeaux de Noël");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        label1.setText("Personnes");

        lstPersonnes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                lstPersonnesItemStateChanged(evt);
            }
        });

        lblCadeauxPour.setText("Cadeaux pour XYZ");

        lblSomme.setText("Somme totale: 99.-");

        label2.setText("Cadeaux possibles");

        lstCadeaux.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                lstCadeauxItemStateChanged(evt);
            }
        });

        btnAjouter.setEnabled(false);
        btnAjouter.setLabel("Ajouter");
        btnAjouter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAjouterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblSomme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lstCadeauxCourante, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lstPersonnes, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCadeauxPour, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAjouter, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lstCadeaux, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lstPersonnes, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblCadeauxPour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lstCadeaux, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lstCadeauxCourante, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAjouter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSomme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

  /* Fin de l'application */
  private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
      dispose();
  }//GEN-LAST:event_formWindowClosing

  private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
    System.exit(0);
  }//GEN-LAST:event_formWindowClosed

    private void lstCadeauxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_lstCadeauxItemStateChanged
        if(lstPersonnes.getSelectedItem().isEmpty()){
            btnAjouter.setEnabled(false);
        }
        /*Personne pers = new Personne(lstPersonnes.getSelectedItem(), new ArrayList());
        Personne persSelect = (Personne)listePersonnes.get(listePersonnes.indexOf(pers)); 
        System.out.println(lstCadeaux.getSelectedIndex());
        for(int i = 0; i < (persSelect.getIdsCadeaux()).size(); i++){
            System.out.println((persSelect.getIdsCadeaux()).get(i));
        }
        Cadeau c = (Cadeau)listeCadeaux.get(lstCadeaux.getSelectedIndex());
        System.out.println(c);
        if((persSelect.getIdsCadeaux()).contains(c)){
            System.out.println("Contient");
        }else{
            System.out.println("Ne contient pas");
        }*/
        boolean estCadeauCourant = true;
        int indexCadeauSelect = lstCadeaux.getSelectedIndex();
        int idCadeau = ((Cadeau)listeCadeaux.get(indexCadeauSelect)).getId();
        int indexPersSelect = lstPersonnes.getSelectedIndex();
        ArrayList aLCadeaux = ((Personne)listePersonnes.get(indexPersSelect)).getIdsCadeaux();
        for(int i = 0; i < aLCadeaux.size(); i++){
            int index = ((Integer)aLCadeaux.get(i)).intValue();
            if(index == idCadeau){
               estCadeauCourant = false;
            }
        }
        btnAjouter.setEnabled(estCadeauCourant);
    }//GEN-LAST:event_lstCadeauxItemStateChanged

    private void lstPersonnesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_lstPersonnesItemStateChanged
        if(lstCadeauxCourante.getItemCount()>0){lstCadeauxCourante.removeAll();}
        lblCadeauxPour.setText("Cadeaux pour "+lstPersonnes.getSelectedItem());
        String nom = lstPersonnes.getSelectedItem();
        chercherCadeaux(chercherPersonne(nom));
    }//GEN-LAST:event_lstPersonnesItemStateChanged

    private void btnAjouterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAjouterActionPerformed
        btnAjouter.setEnabled(false);
        ajouter();
      enregistrer();
    }//GEN-LAST:event_btnAjouterActionPerformed

  /* Méthode principale */
  public static void main(String args[]) {
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        new FrmMain().setVisible(true);
      }
    });
  }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button btnAjouter;
    private java.awt.Label lblCadeauxPour;
    private java.awt.Label lblSomme;
    private java.awt.List lstCadeaux;
    private java.awt.List lstCadeauxCourante;
    private java.awt.List lstPersonnes;
    // End of variables declaration//GEN-END:variables

} // FrmMain
