/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author user
 */
public class Utilisateur {
    private int id_user;
    private String login,password,nom,prenom,role,mail,domaine,etat,image;
    public static Utilisateur Current_User;

    
    public Utilisateur() {
    }
    
/*
public Utilisateur(int id, String nom, String prenom, String domaine,String mail,String password ) {
        this.id_user=id;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.domaine = domaine;
    }
    */
    
    public Utilisateur(String nom, String prenom,String login, String password,String role, String mail, String domaine) {
        this.login = login;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.role = role;
        this.mail = mail;
        this.domaine = domaine;
    }
    
    public Utilisateur(int id, String nom, String prenom,String login, String password,String role, String mail, String domaine,String etat,String image) {
        this.id_user=id;
        this.login = login;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.role = role;
        this.mail = mail;
        this.domaine = domaine;
        this.etat=etat;
        this.image=image;
    }

        public Utilisateur(int id,String nom, String prenom,String login, String role,String etat,String mail,String domaine,String password) {
        this.id_user=id;
        this.nom = nom;
        this.prenom = prenom;
        this.login=login;
        this.role = role;
        this.etat = etat;
        this.mail = mail;
        this.domaine=domaine;
        this.password=password;
    }

    public Utilisateur(String nom, String prenom, String mail) {
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
    }
    

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

   

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    
    
    public String getDomaine() {
        return domaine;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }

    
    public static Utilisateur getCurrent_User() {
        return Current_User;
    }

    public static void setCurrent_User(Utilisateur Current_User) {
        Utilisateur.Current_User = Current_User;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
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
        final Utilisateur other = (Utilisateur) obj;
        return this.id_user == other.id_user;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "nom=" + nom + ", prenom=" + prenom + ", role=" + role + ", mail=" + mail + ", etat=" + etat + '}';
    }
}
