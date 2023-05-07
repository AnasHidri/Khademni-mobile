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
import com.codename1.ui.events.ActionListener;
import entity.Historique;
import entity.Ligne_commande;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 *
 * @author mikea
 */
public class HistoriqueService {
    
      public ArrayList<Historique> historiques;
      public static HistoriqueService instance = null;
        private ConnectionRequest req;
         public boolean resultOK;
      
      public HistoriqueService(){
            req = new ConnectionRequest();
      }
      
        public static HistoriqueService getInstance() {
        if (instance == null) {
            instance = new HistoriqueService();
        }
        return instance;
    }
        
        
        public ArrayList<Historique> parseHistorique(String jsonText) {
    try {
        ArrayList<Historique> historiques = new ArrayList<>();
       JSONParser historique = new JSONParser();
            Map<String, Object> historiqueListJson
                    = historique.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            java.util.List<Map<String, Object>> list = (java.util.List<Map<String, Object>>) historiqueListJson.get("root");
            for (Map<String, Object> obj : list) {
            Historique histo = new Historique();
                    float id_historique = Float.parseFloat(obj.get("id_historique").toString());
                    histo.setId_historique((int) id_historique);
                 Date date = new Date();
                    histo.setDate_action(date);
                    histo.setAction(obj.get("action").toString());
                     
            historiques.add(histo);
        }
        return historiques;
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
        return null;
    }
}
        
        
          public ArrayList<Historique> getAllHistorique() {
    
        String url = Statics.BASE_URL + "historiqueapi/";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                historiques = parseHistorique(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return historiques;
    }
      
    
       
          
          
         
    
    
}
