/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entities;

import java.util.Map;

/**
 *
 * @author ASUS
 */
public class User {
    private int id_user;
    private String login;
    private String password;
    private String nom;
    private String prenom;
    private String cv ;
    private String certif ;
    private String mail;
    private float  solde ;
    private String domaine;
    private String nomSociete ;
    private String role ;
    private String etat ;
    private String image ;
    private String reset_token;
        public static User Current_User;


    public User(int id_user, String login, String password, String nom, String prenom, String cv, String certif, String mail, float solde, String domaine, String nomSociete, String role, String etat, String image, String reset_token) {
        this.id_user = id_user;
        this.login = login;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.cv = cv;
        this.certif = certif;
        this.mail = mail;
        this.solde = solde;
        this.domaine = domaine;
        this.nomSociete = nomSociete;
        this.role = role;
        this.etat = etat;
        this.image = image;
        this.reset_token = reset_token;
    }

    public User(String login, String password, String nom, String prenom, String mail, float solde, String domaine, String nomSociete, String role, String etat, String image) {
        this.login = login;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.solde = solde;
        this.domaine = domaine;
        this.nomSociete = nomSociete;
        this.role = role;
        this.etat = etat;
        this.image = image;
    }

    public User(String login, String password, String nom, String prenom, String cv, String mail, float solde, String domaine, String role, String etat, String image) {
        this.login = login;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.cv = cv;
        this.mail = mail;
        this.solde = solde;
        this.domaine = domaine;
        this.role = role;
        this.etat = etat;
        this.image = image;
    }

    public User() {
    }

    public User(int id_user, String nom, String prenom, String mail, String domaine) {
        this.id_user = id_user;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.domaine = domaine;
    }

    public User(String login, String password, String nom, String prenom, String domaine, String role) {
        this.login = login;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.domaine = domaine;
        this.role = role;
    }

    
    public int getIdUser() {
        return id_user;
    }

    public void setIdUser(int id_user) {
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

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public String getCertif() {
        return certif;
    }

    public void setCertif(String certif) {
        this.certif = certif;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public float getSolde() {
        return solde;
    }

    public void setSolde(float solde) {
        this.solde = solde;
    }

    public String getDomaine() {
        return domaine;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }

    public String getNomSociete() {
        return nomSociete;
    }

    public void setNomSociete(String nomSociete) {
        this.nomSociete = nomSociete;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getReset_token() {
        return reset_token;
    }

    public void setReset_token(String reset_token) {
        this.reset_token = reset_token;
    }
    /*
    public static User fromMap(Map<String, Object> map) {
    User user = new User();
    user.setIdUser((int) map.get("id"));
    user.setNom((String) map.get("nom"));
    user.setPrenom((String) map.get("prenom"));
    user.setLogin((String) map.get("login"));
    user.setMail((String) map.get("mail"));
    user.setPassword((String) map.get("password"));
    return user;
}
    */
    public static User getCurrent_User() {
        return Current_User;
    }

    public static void setCurrent_User(User Current_User) {
        User.Current_User = Current_User;
    }

    @Override
    public String toString() {
        return "User{" + "login=" + login + ", nom=" + nom + ", prenom=" + prenom + ", mail=" + mail + ", domaine=" + domaine + ", role=" + role + '}';
    }
    
    
    
}
