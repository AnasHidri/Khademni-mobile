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
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;

import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Evenement;
import com.mycompany.entities.User;
import com.mycompany.statics.Statics;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;



import java.util.Date;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;



/**
 *
 * @author user
 */
public class EvenementService {
    
      //singleton 
    public static EvenementService instance = null ;
    
    public static boolean resultOk = true;

    //initilisation connection request 
    private ConnectionRequest req;
  
    
    
    
    
    public static EvenementService getInstance() {
        if(instance == null )
            instance = new EvenementService();
        return instance ;
    }
    
     public EvenementService() {
        req = new ConnectionRequest();
        
    }
     
     public void ajoutEvenement(Evenement evenement) {
           User u = User.getCurrent_User();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String dateDebutStr = dateFormat.format(evenement.getDate_debut());
    String dateFinStr = dateFormat.format(evenement.getDate_fin());
    
    String url = Statics.BASE_URL + "/evenementJson/new?dateDebut=" + dateDebutStr + "&dateFin=" + dateFinStr
            + "&titre=" + evenement.getTitre() + "&description=" + evenement.getDescription() + "&nom_societe="
            + evenement.getNom_societe() + "&lieu=" + evenement.getLieu()+"&iduser="+u.getIdUser();

    req.setUrl(url);
    req.addResponseListener((e) -> {

        String str = new String(req.getResponseData());
        System.out.println("data == " + str);
    });

    NetworkManager.getInstance().addToQueueAndWait(req);
}
     
 public ArrayList<Evenement> affichageEvenement() {
    ArrayList<Evenement> result = new ArrayList<>();
    String url = Statics.BASE_URL + "/evenementJson/all";
    req.setUrl(url);

    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            JSONParser jsonp = new JSONParser();
            try {
                Map<String, Object> mapEvenements = jsonp.parseJSON(
                        new CharArrayReader(new String(req.getResponseData()).toCharArray()));

                List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapEvenements.get("root");

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

for (Map<String, Object> obj : listOfMaps) {
    Evenement ev = new Evenement();
    float id_evenement = Float.parseFloat(obj.get("id_evenement").toString());
    String titre = (String) obj.get("titre");
    String description = (String) obj.get("description");
    String nomSociete = (String) obj.get("nom_societe");
    String lieu = (String) obj.get("lieu");

    // récupérer les dates sous forme de chaînes de caractères
    String dateDebutStr = (String) obj.get("date_debut");
    String dateFinStr = (String) obj.get("date_fin");

    // parser les dates en objets Date
    Date dateDebut = dateFormat.parse(dateDebutStr);
    Date dateFin = dateFormat.parse(dateFinStr);

    ev.setId_evenement((int) id_evenement);
    ev.setDate_debut(dateDebut);
    ev.setDate_fin(dateFin);
    ev.setTitre(titre);
    ev.setDescription(description);
    ev.setNom_societe(nomSociete);
    ev.setLieu(lieu);
    result.add(ev);
}

            } catch(Exception ex) {
                    
                    ex.printStackTrace();
                }
        }
    });

    NetworkManager.getInstance().addToQueueAndWait(req);//execution  request 
    return result;
}
 
 public boolean deleteEvenement(int id_evenement ) {
        String url = Statics.BASE_URL +"/evenementJson/delEv/"+id_evenement;

        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    
                    req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  resultOk;
    }
 
 public boolean modifierEvenement(Evenement evenement) {
        String url = Statics.BASE_URL +"/evenementJson/"+evenement.getId_evenement()+"/edit"+"?titre="+evenement.getTitre()+"&description="+evenement.getDescription()+"&nom_societe="+evenement.getNom_societe()+"&lieu="+evenement.getLieu();
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200 ;  
                req.removeResponseListener(this);
            }
        });
        
    NetworkManager.getInstance().addToQueueAndWait(req);
    return resultOk;
        
 
     
      }
 
 public void showEvenement(int id_evenement) {
   
   String url = Statics.BASE_URL + "/evenementJson/show/"+id_evenement;
    req.setUrl(url);
    req.addResponseListener((e) -> {
        try {
            JSONParser parser = new JSONParser();
            Map<String, Object> eventMap = parser.parseJSON(new InputStreamReader(new ByteArrayInputStream(req.getResponseData()), "UTF-8"));
            // Vous pouvez maintenant accéder aux données de l'événement et les utiliser dans votre application
             
            String titre = (String) eventMap.get("titre");
            String description = (String) eventMap.get("description");
            String nom_societe = (String) eventMap.get("nom_societe");
            String lieu = (String) eventMap.get("lieu");
            // ...
        } catch(Exception ex) {
                    
                    ex.printStackTrace();
        }
    });
    NetworkManager.getInstance().addToQueue(req);
}
 
public void getTopEventsByLikes(Label label) {
   String url = Statics.BASE_URL + "/evenementJson/topVoteJson";

   req.setUrl(url);
   
   req.addResponseListener((e) -> {
       try {
           if (req.getResponseCode() == 200) {
               // La requête a réussi, analysez la réponse JSON
               JSONParser parser = new JSONParser();
               Map<String, Object> response = parser.parseJSON(new InputStreamReader(new ByteArrayInputStream(req.getResponseData()), "UTF-8"));

               // Vérifiez que la réponse contient une clé 'events'
               if (response.containsKey("events")) {
                   List<Map<String, Object>> events = (List<Map<String, Object>>) response.get("events");

                   // Mettre à jour le label avec les événements
                   String text = "";
                   if (events != null && !events.isEmpty()) {
                       for (Map<String, Object> event : events) {
                           String titre = (String) event.get("title");
                           String likes = String.valueOf(event.get("likes"));
                           text += titre + " - " + likes + " likes\n";
                           System.out.println("sayabna:"+text);
                       }
                   } else {
                       text = "Aucun événement trouvé.";
                   }
                   label.setText(text);
               } else {
                   // La réponse ne contient pas la clé 'events'
                   Dialog.show("Erreur", "La réponse est invalide", "OK", null);
               }
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

public ArrayList<Evenement> affichageEvenementAd() {
    ArrayList<Evenement> result = new ArrayList<>();
    String url = Statics.BASE_URL + "/evenementJson/allAd";
    req.setUrl(url);

    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            JSONParser jsonp = new JSONParser();
            try {
                Map<String, Object> mapEvenements = jsonp.parseJSON(
                        new CharArrayReader(new String(req.getResponseData()).toCharArray()));

                List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapEvenements.get("root");

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

for (Map<String, Object> obj : listOfMaps) {
    Evenement ev = new Evenement();
    float id_evenement = Float.parseFloat(obj.get("id_evenement").toString());
    String titre = (String) obj.get("titre");
    String description = (String) obj.get("description");
    String nomSociete = (String) obj.get("nom_societe");
    String lieu = (String) obj.get("lieu");

    // récupérer les dates sous forme de chaînes de caractères
    String dateDebutStr = (String) obj.get("date_debut");
    String dateFinStr = (String) obj.get("date_fin");

    // parser les dates en objets Date
    Date dateDebut = dateFormat.parse(dateDebutStr);
    Date dateFin = dateFormat.parse(dateFinStr);

    ev.setId_evenement((int) id_evenement);
    ev.setDate_debut(dateDebut);
    ev.setDate_fin(dateFin);
    ev.setTitre(titre);
    ev.setDescription(description);
    ev.setNom_societe(nomSociete);
    ev.setLieu(lieu);
    result.add(ev);
}

            } catch(Exception ex) {
                   
                    ex.printStackTrace();
                }
        }
    });

    NetworkManager.getInstance().addToQueueAndWait(req);//execution  request
    return result;
}
public ArrayList<Evenement> affichageEvenementEmp() {
    ArrayList<Evenement> result = new ArrayList<>();
    User u = User.getCurrent_User();
    String url = Statics.BASE_URL + "/evenementJson/allEmp/"+u.getIdUser();
    req.setUrl(url);

    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            JSONParser jsonp = new JSONParser();
            try {
                Map<String, Object> mapEvenements = jsonp.parseJSON(
                        new CharArrayReader(new String(req.getResponseData()).toCharArray()));

                List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapEvenements.get("root");

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

for (Map<String, Object> obj : listOfMaps) {
    Evenement ev = new Evenement();
    float id_evenement = Float.parseFloat(obj.get("id_evenement").toString());
    String titre = (String) obj.get("titre");
    String description = (String) obj.get("description");
    String nomSociete = (String) obj.get("nom_societe");
    String lieu = (String) obj.get("lieu");

    // récupérer les dates sous forme de chaînes de caractères
    String dateDebutStr = (String) obj.get("date_debut");
    String dateFinStr = (String) obj.get("date_fin");

    // parser les dates en objets Date
    Date dateDebut = dateFormat.parse(dateDebutStr);
    Date dateFin = dateFormat.parse(dateFinStr);

    ev.setId_evenement((int) id_evenement);
    ev.setDate_debut(dateDebut);
    ev.setDate_fin(dateFin);
    ev.setTitre(titre);
    ev.setDescription(description);
    ev.setNom_societe(nomSociete);
    ev.setLieu(lieu);
    result.add(ev);
}

            } catch(Exception ex) {
                   
                    ex.printStackTrace();
                }
        }
    });

    NetworkManager.getInstance().addToQueueAndWait(req);//execution  request
    return result;
}


 
}
