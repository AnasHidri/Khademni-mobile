/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.services;

import com.mycompany.statics.Statics;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.List;
import com.codename1.ui.events.ActionListener;
import entity.Ligne_commande;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author mikea
 */
public class PanierService {
    
      public ArrayList<Ligne_commande> lignes;
      public static PanierService instance = null;
        private ConnectionRequest req;
         public boolean resultOK;
      
      public PanierService(){
            req = new ConnectionRequest();
      }
      
        public static PanierService getInstance() {
        if (instance == null) {
            instance = new PanierService();
        }
        return instance;
    }
        
        
        public ArrayList<Ligne_commande> parseLigne(String jsonText) {
    try {
        ArrayList<Ligne_commande> ligne_commandes = new ArrayList<>();
       JSONParser lignecommande = new JSONParser();
            Map<String, Object> ligneListJson
                    = lignecommande.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            java.util.List<Map<String, Object>> list = (java.util.List<Map<String, Object>>) ligneListJson.get("root");
            for (Map<String, Object> obj : list) {
            Ligne_commande ligne = new Ligne_commande();
                    float id_ligne = Float.parseFloat(obj.get("id_ligne_commande").toString());
                    ligne.setId_ligne_commande((int) id_ligne);
                    ligne.setTitre(obj.get("titre").toString());
                          float prix = Float.parseFloat(obj.get("prix").toString());
                    ligne.setPrix((int) prix);
                      float status = Float.parseFloat(obj.get("status").toString());
                    ligne.setStatus((int) status);    
            ligne_commandes.add(ligne);
        }
        return ligne_commandes;
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
        return null;
    }
}
        
        
          public ArrayList<Ligne_commande> getAllLigne() {
    
        String url = Statics.BASE_URL + "ligne/commandeapi/AllLigneUser";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                lignes = parseLigne(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return lignes;
    }
      
          
          public boolean deleteCommande(int id_ligne_commande) {
              System.out.println(id_ligne_commande);
    String url = Statics.BASE_URL + "ligne/commandeapi/removeapi/" + id_ligne_commande;
    ConnectionRequest req = new ConnectionRequest(url);

    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            // Check if the response was successful
            if (req.getResponseCode() == 200) {
                resultOK = true;
            } else {
                resultOK = false;
            }
        }
    });
      NetworkManager.getInstance().addToQueueAndWait(req);
          return resultOK;

 }  
       
          
          
           public boolean PayerCommande(int id_ligne_commande) {
              System.out.println(id_ligne_commande);
    String url = Statics.BASE_URL + "ligne/commandeapi/payeroneAPI/" + id_ligne_commande;
    ConnectionRequest req = new ConnectionRequest(url);

    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            // Check if the response was successful
            if (req.getResponseCode() == 200) {
                resultOK = true;
            } else {
                resultOK = false;
            }
        }
    });
      NetworkManager.getInstance().addToQueueAndWait(req);
          return resultOK;

 }  
          
    
        public   ArrayList<Ligne_commande> FiltrerCommande (int prixmin, int prixmax){
            
              String url = Statics.BASE_URL + "ligne/commandeapi/ligneAPI/filtrer/"+prixmin+"/"+prixmax;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                lignes = parseLigne(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return lignes;
        } 
           
           
}
