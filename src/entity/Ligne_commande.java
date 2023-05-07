/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;


/**
 *
 * @author mikea
 */
public class Ligne_commande {
    
    private int id_ligne_commande;
    private int id_panier;
    private int id_formation;
    private int prix;
    private String titre;
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Ligne_commande(int id_ligne_commande, int id_formation, int id_panier, int prix, String titre, int status) {
        this.id_ligne_commande = id_ligne_commande;
        this.id_panier = id_panier;
        this.id_formation = id_formation;
        this.prix = prix;
        this.titre = titre;
        this.status = status;
    }

    public Ligne_commande(int id_panier, int id_formation, int prix, String titre, int status) {
        this.id_panier = id_panier;
        this.id_formation = id_formation;
        this.prix = prix;
        this.titre = titre;
        this.status = status;
    }
    

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }



    public Ligne_commande() {
    }

    public int getId_ligne_commande() {
        return id_ligne_commande;
    }

    public void setId_ligne_commande(int id_ligne_commande) {
        this.id_ligne_commande = id_ligne_commande;
    }

    public int getId_panier() {
        return id_panier;
    }

    public void setId_panier(int id_panier) {
        this.id_panier = id_panier;
    }

    public int getId_formation() {
        return id_formation;
    }

    public void setId_formation(int id_formation) {
        this.id_formation = id_formation;
    }

    @Override
    public String toString() {
        return "Ligne_commande{" + "id_formation=" + id_formation + ", prix=" + prix + ", titre=" + titre + '}';
    }

  
  

   

    public Ligne_commande(int id_ligne_commande, int id_panier, int prix, String titre) {
        this.id_ligne_commande = id_ligne_commande;
        this.id_panier = id_panier;
        this.prix = prix;
        this.titre = titre;
    }

    public Ligne_commande(int id_ligne_commande, int id_formation, int id_panier,  int prix, String titre) {
        this.id_ligne_commande = id_ligne_commande;
        this.id_panier = id_panier;
        this.id_formation = id_formation;
        this.prix = prix;
        this.titre = titre;
    }

    public Ligne_commande(int id_formation, int prix, String titre) {
        this.id_formation = id_formation;
        this.prix = prix;
        this.titre = titre;
    }

   

 




   /* public Ligne_commande(int id_ligne_commande, int prix, String titre) {
        this.id_ligne_commande = id_ligne_commande;
        this.prix = prix;
        this.titre = titre;
    }*/

    public Ligne_commande(int prix, String titre) {
        this.prix = prix;
        this.titre = titre;
    }


    
    
}
