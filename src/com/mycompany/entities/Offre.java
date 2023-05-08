/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entities;


import java.util.Date;
import java.util.Objects;


/**
 *
 * @author CYBERLAND
 */
public class Offre {
    private int id_offre ;
    private int id_user;
    private String titre ;
        private String    description,adresse_societe,domaine_offre;
    private Date date_debut;
    private String etat;
 
    public Offre() {
    }
  public Offre(int id_offre, String titre) {
        this.id_offre = id_offre;
        this.titre = titre;
    }

    public Offre(String titre, String description, String adresse_societe, String domaine_offre) {
        this.titre = titre;
        this.description = description;
        this.adresse_societe = adresse_societe;
        this.domaine_offre = domaine_offre;
    }
  public Offre(int id_offre,String titre, String description, String adresse_societe, String domaine_offre) {
      this.id_offre = id_offre;
      this.titre = titre;
        this.description = description;
        this.adresse_societe = adresse_societe;
        this.domaine_offre = domaine_offre;
    }
    public Offre(String titre, String description, String adresse_societe, String domaine_offre, Date date_debut) {
        this.titre = titre;
        this.description = description;
        this.adresse_societe = adresse_societe;
        this.domaine_offre = domaine_offre;
        this.date_debut = date_debut;
    }

    public void setId_offre(int id_offre) {
        this.id_offre = id_offre;
    }
   public Offre(String titre, String description) {
        this.titre = titre;
        this.description = description;}

    public Offre(int id_user, String titre, String description, String adresse_societe, String domaine_offre, Date date_debut) {
        this.id_user = id_user;
        this.titre = titre;
        this.description = description;
        this.adresse_societe = adresse_societe;
        this.domaine_offre = domaine_offre;
        this.date_debut = date_debut;
      

    }

    public Offre(String titre, String description, String etat) {
        this.titre = titre;
        this.description = description;
        this.etat = etat;
    }

    public Offre(int id_offre, int id_user, String titre, String description, String adresse_societe, String domaine_offre, Date date_debut) {
        this.id_offre = id_offre;
        this.id_user = id_user;
        this.titre = titre;
        this.description = description;
        this.adresse_societe = adresse_societe;
        this.domaine_offre = domaine_offre;
        this.date_debut = date_debut;
         
    }

    public Offre(int id_offre, int id_user, String titre, String description, String adresse_societe, String domaine_offre, Date date_debut, String etat) {
        this.id_offre = id_offre;
        this.id_user = id_user;
        this.titre = titre;
        this.description = description;
        this.adresse_societe = adresse_societe;
        this.domaine_offre = domaine_offre;
        this.date_debut = date_debut;
        this.etat = etat;
    }

    
    public Offre(String titre, String description, String adresse_societe, String domaine_offre, Date date_debut,String etat) {
        this.titre = titre;
        this.description = description;
        this.adresse_societe = adresse_societe;
        this.domaine_offre = domaine_offre;
        this.date_debut = date_debut;
          }

   
   

    public int getId_offre() {
        return id_offre;
    }

    public int getId_user() {
        return id_user;
    }

    public String getTitre() {
        return titre;
    }
    public String getDescription() {
        return description;
    }

    public String getAdresse_societe() {
        return adresse_societe;
    }

    public String getDomaine_offre() {
        return domaine_offre;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

  

      public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAdresse_societe(String adresse_societe) {
        this.adresse_societe = adresse_societe;
    }

    public void setDomaine_offre(String domaine_offre) {
        this.domaine_offre = domaine_offre;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

   

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.id_offre;
        hash = 59 * hash + this.id_user;
        hash = 59 * hash + Objects.hashCode(this.titre);
        hash = 59 * hash + Objects.hashCode(this.description);
        hash = 59 * hash + Objects.hashCode(this.adresse_societe);
        hash = 59 * hash + Objects.hashCode(this.domaine_offre);
        hash = 59 * hash + Objects.hashCode(this.date_debut);
        hash = 59 * hash + Objects.hashCode(this.etat);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Offre other = (Offre) obj;
        if (this.id_offre != other.id_offre) {
            return false;
        }
        if (this.id_user != other.id_user) {
            return false;
        }
        if (!Objects.equals(this.titre, other.titre)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.adresse_societe, other.adresse_societe)) {
            return false;
        }
        if (!Objects.equals(this.domaine_offre, other.domaine_offre)) {
            return false;
        }
        if (!Objects.equals(this.date_debut, other.date_debut)) {
            return false;
      
        }
        return Objects.equals(this.etat, other.etat);
    }

    @Override
    public String toString() {
        return "Offre{" + "id_offre=" + id_offre + ", id_user=" + id_user + ", titre=" + titre + ", description=" + description + ", adresse_societe=" + adresse_societe + ", domaine_offre=" + domaine_offre + ", date_debut=" + date_debut + ", etat=" + etat + '}';
    }


   
  

    

   
}
