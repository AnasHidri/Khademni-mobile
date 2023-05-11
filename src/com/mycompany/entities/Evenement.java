/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entities;

//import java.sql.Date;
import java.util.Date;

/**
 *
 * @author user
 */
public class Evenement {
     private int id_evenement;
    private int id_user;
    private Date date_debut;
    private Date date_fin;
    private String titre;
    private String description;
    private String nom_societe;
    private String lieu;

    public Evenement() {
    }
    

    
    public Evenement(int id_user,String titre, String description, String nom_societe, String lieu) {
        this.id_user = id_user;
       
        this.titre = titre;
        this.description = description;
        this.nom_societe = nom_societe;
        this.lieu = lieu;
    }

    public Evenement(int id_evenement, int id_user, Date date_debut, Date date_fin, String titre, String description, String nom_societe, String lieu) {
        this.id_evenement = id_evenement;
        this.id_user = id_user;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.titre = titre;
        this.description = description;
        this.nom_societe = nom_societe;
        this.lieu = lieu;
    }

   

    public Evenement(int id_user, Date date_debut, Date date_fin, String titre, String description, String nom_societe, String lieu) {
        this.id_user = id_user;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.titre = titre;
        this.description = description;
        this.nom_societe = nom_societe;
        this.lieu = lieu;
    }

    public Evenement(int id_evenement, String titre, String nom_societe) {
        this.id_evenement = id_evenement;
        this.titre = titre;
        this.nom_societe = nom_societe;
    }

    public Evenement(Date date_debut, Date date_fin, String titre, String description, String nom_societe, String lieu) {
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.titre = titre;
        this.description = description;
        this.nom_societe = nom_societe;
        this.lieu = lieu;
    }

   
    
    public int getId_evenement() {
        return id_evenement;
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

    public String getNom_societe() {
        return nom_societe;
    }

    public String getLieu() {
        return lieu;
    }

    public void setId_evenement(int id_evenement) {
        this.id_evenement = id_evenement;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

   

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNom_societe(String nom_societe) {
        this.nom_societe = nom_societe;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    @Override
    public String toString() {
        return "Evenement{" + "id_evenement=" + id_evenement + ", id_user=" + id_user + ", date_debut=" + date_debut + ", date_fin=" + date_fin + ", titre=" + titre + ", description=" + description + ", nom_societe=" + nom_societe + ", lieu=" + lieu + '}';
    }
    
    public boolean contains(String query){
        return titre.contains(query);
    }
}
