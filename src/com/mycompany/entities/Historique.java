/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entities;


import java.util.Date;

/**
 *
 * @author mikea
 */
public class Historique {
    private int id_historique;
    private int id_user;
    private Date date_action;
    private String action;

    public Historique(int id_historique, int id_user, Date date_action, String action) {
        this.id_historique = id_historique;
        this.id_user = id_user;
        this.date_action = date_action;
        this.action = action;
    }

    public Historique() {
    }
    
    

    public Historique(int id_user, Date date_action, String action) {
        this.id_user = id_user;
        this.date_action = date_action;
        this.action = action;
    }

    public Historique(Date date_action, String action) {
        this.date_action = date_action;
        this.action = action;
    }

    public int getId_historique() {
        return id_historique;
    }

    public void setId_historique(int id_historique) {
        this.id_historique = id_historique;
    }

    public int getId_user() {
        return id_user;
    }


    public Date getDate_action() {
        return date_action;
    }

    public void setDate_action(Date date_action) {
        this.date_action = date_action;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return "Historique{" + "id_user=" + id_user + ", date_action=" + date_action + ", action=" + action + '}';
    }
    
    
    
}
