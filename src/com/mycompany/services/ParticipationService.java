/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionListener;
import static com.mycompany.services.EvenementService.resultOk;
import com.mycompany.statics.Statics;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.mycompany.entities.Evenement;
import com.mycompany.entities.User;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author user
 */
public class ParticipationService {
    
     //singleton 
    public static ParticipationService instance = null ;
    
    public static boolean resultOk = true;

    //initilisation connection request 
    private ConnectionRequest req;
  
    
    
    
    
    public static ParticipationService getInstance() {
        if(instance == null )
            instance = new ParticipationService();
        return instance ;
    }
    
     public ParticipationService() {
        req = new ConnectionRequest();
        
    }
     
    public void ajoutParticipation(int id_evenement) {
    String url = Statics.BASE_URL + "/participationJson/new/" + id_evenement;
    req.setUrl(url);
    req.addResponseListener((e) -> {
        String str = new String(req.getResponseData());
        System.out.println("data == " + str);
         
            Dialog.show("Confirmation", "Participation ajoutée avec succès", "OK", null);
       
       
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
}
     
    public void annulerParticipation(int id_evenement) {
    String url = Statics.BASE_URL + "/participationJson/delete/" + id_evenement;
    req.setUrl(url);
    req.addResponseListener(e -> {
        try {
            JSONParser parser = new JSONParser();
            Map<String, Object> resultMap = parser.parseJSON(new InputStreamReader(new ByteArrayInputStream(req.getResponseData()), "UTF-8"));
             Dialog.show("Confirmation", "Votre Participation a ete annulee", "OK", null);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
}
    
   public ArrayList<Evenement> mesParticipations(int id_user) {
    ArrayList<Evenement> result = new ArrayList<>();
     User u = User.getCurrent_User();
    String url = Statics.BASE_URL + "/participationJson/user/participations/" +u.getIdUser();
    
    req.setUrl(url);

    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            JSONParser jsonp = new JSONParser();
            try {
                Map<String, Object> mapEvenements = jsonp.parseJSON(
                        new CharArrayReader(new String(req.getResponseData()).toCharArray()));

                List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapEvenements.get("root");

                for (Map<String, Object> obj : listOfMaps) {
                    Evenement ev = new Evenement();
                    float id_evenement = Float.parseFloat(obj.get("id_evenement").toString());
                    String titre = (String) obj.get("titre");
                    String description = (String) obj.get("description");
                    String nomSociete = (String) obj.get("nom_societe");
                    String lieu = (String) obj.get("lieu");

                    ev.setId_evenement((int) id_evenement);
                    ev.setTitre(titre);
                    ev.setDescription(description);
                    ev.setNom_societe(nomSociete);
                    ev.setLieu(lieu);
                    System.out.println("liste" +ev);
                    result.add(ev);
                }
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
    });

    NetworkManager.getInstance().addToQueueAndWait(req);
    return result;
}

  public void like(int id_evenement) {
        User u = User.getCurrent_User();
    String url = Statics.BASE_URL + "/participationJson/" + id_evenement + "/like/"+u.getIdUser();
   
    req.setUrl(url);

    req.addResponseListener((e) -> {
        try {
            if (req.getResponseCode() == 200) {
                // La requête a réussi, analysez la réponse JSON
                JSONParser parser = new JSONParser();
                Map<String, Object> response = parser.parseJSON(new InputStreamReader(new ByteArrayInputStream(req.getResponseData()), "UTF-8"));
                Dialog.show("Confirmation", "Vous avez aimé cet evenement", "OK", null);
            } else {
                // La requête a échoué, affichez un message d'erreur
                Dialog.show("Erreur", "La requête a échoué", "OK", null);
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    });

    NetworkManager.getInstance().addToQueueAndWait(req);
}
 
  public void Dislike(int id_evenement) {
         User u = User.getCurrent_User();
    String url = Statics.BASE_URL + "/participationJson/" + id_evenement + "/dislike/"+u.getIdUser();
   
    req.setUrl(url);

    req.addResponseListener((e) -> {
        try {
            if (req.getResponseCode() == 200) {
                // La requête a réussi, analysez la réponse JSON
                JSONParser parser = new JSONParser();
                Map<String, Object> response = parser.parseJSON(new InputStreamReader(new ByteArrayInputStream(req.getResponseData()), "UTF-8"));
                Dialog.show("Confirmation", "Vous avez vote contre cet evenement", "OK", null);
            } else {
                // La requête a échoué, affichez un message d'erreur
                Dialog.show("Erreur", "La requête a échoué", "OK", null);
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    });

    NetworkManager.getInstance().addToQueueAndWait(req);
}

public void getLikesDislikes(int id_evenement, Label likesLabel, Label dislikesLabel) {
    String url = Statics.BASE_URL + "/participationJson/evenement/" + id_evenement;

    req.setUrl(url);

    req.addResponseListener((e) -> {
        try {
            if (req.getResponseCode() == 200) {
                // La requête a réussi, analysez la réponse JSON

                JSONParser parser = new JSONParser();
                Map<String, Object> response = parser.parseJSON(new InputStreamReader(new ByteArrayInputStream(req.getResponseData()), "UTF-8"));
                int likes = Integer.parseInt((String) response.get("likes"));
                int dislikes = Integer.parseInt((String) response.get("dislikes"));

                // Mettre à jour les labels
                likesLabel.setText("Likes : " + likes);
                dislikesLabel.setText("Dislikes : " + dislikes);
            } else {
                // La requête a échoué, affichez un message d'erreur
                Dialog.show("Erreur", "La requête a échoué", "OK", null);
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    });

    NetworkManager.getInstance().addToQueueAndWait(req);
} 

    
}
