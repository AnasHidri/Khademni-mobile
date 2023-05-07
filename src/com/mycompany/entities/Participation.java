/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entities;

/**
 *
 * @author user
 */
public class Participation {
    private int id_participation;
    private int id_evenement;
    private int id_user;
    private String status;
    private int vote=0;

    public Participation(int id_evenement, int id_user, String status) {
        this.id_evenement = id_evenement;
        this.id_user = id_user;
        this.status = status;
    }

    public int getId_participation() {
        return id_participation;
    }

    public void setId_participation(int id_participation) {
        this.id_participation = id_participation;
    }

    public int getId_evenement() {
        return id_evenement;
    }

    public void setId_evenement(int id_evenement) {
        this.id_evenement = id_evenement;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }
    
    

    @Override
    public String toString() {
        return "Participation{" + "id_participation=" + id_participation + ", id_evenement=" + id_evenement + ", id_user=" + id_user + ", status=" + status + '}';
    }

}
