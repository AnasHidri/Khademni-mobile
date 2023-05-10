/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.services;

import com.mycompany.entities.Candidature;
import com.mycompany.entities.Offre;
import com.mycompany.entities.User;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.mycompany.statics.Statics;

/**
 *
 * @author CYBERLAND
 */
public class CandidatureService {
      public ArrayList<Candidature> candidatures;
    
   
    
    public static CandidatureService instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private CandidatureService() {
        req = new ConnectionRequest();
    }

    public static CandidatureService getInstance() {
        if (instance == null) {
            instance = new CandidatureService();
        }
        return instance;
    }
    
    public ArrayList<Candidature> parseCandidatures(String jsonText) {
        
        try {
            candidatures = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> candidaturesListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) candidaturesListJson.get("root");
                       ArrayList<Offre> offres = OffreService.getInstance().getAllOffres();

            for (Map<String, Object> obj : list) {
                Candidature c = new Candidature();
                  float id_candidature = Float.parseFloat(obj.get("idCandidature").toString());
                   c.setId_candidature((int) id_candidature);

                if (obj.get("etat") == null) {
                    c.setEtat("null");
                } else {
                    c.setEtat(obj.get("etat").toString());
                
                }
                //  float id_offre = Float.parseFloat(obj.get("idOffre").toString());
                  // c.setId_offre((int) id_offre);

          
          /*    String titre = (String)((Map<String, Object>) obj.get("idOffre")).get("titre");

String adresseSociete = (String)((Map<String, Object>) obj.get("idOffre")).get("adresseSociete");
String description = (String)((Map<String, Object>) obj.get("idOffre")).get("description");
String nom = (String)((Map<String, Object>) obj.get("idUser")).get("nom");
String mail = (String)((Map<String, Object>) obj.get("idUser")).get("mail");

                 System.out.println("hhhh"+adresseSociete);
                    System.out.println("hhhh"+description);
                                        System.out.println("hhhh"+titre);
                    System.out.println("hhhh"+mail);
*/
                  candidatures.add(c);
            
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return candidatures;
    }
 
   public ArrayList<Candidature> getAllCandidatures() {
    String url = Statics.BASE_URL + "/candidatureApiRest/allApiRest";
    req.setUrl(url);
    req.setPost(false);
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            candidatures = parseCandidatures(new String(req.getResponseData()));
 req.removeResponseListener(this);
            }
        });
        
    NetworkManager.getInstance().addToQueueAndWait(req);
    return candidatures;
}
 

    public ArrayList<Candidature> getMesCandidatures() {
          User u = User.getCurrent_User();
        String url = Statics.BASE_URL + "/candidatureApiRest/clientApiRest/MesCandidatures/"+u.getIdUser();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                candidatures = parseCandidatures(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return candidatures;
    }
  
}