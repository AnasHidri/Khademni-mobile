/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import static com.mycompany.myapp.services.EvenementService.resultOk;
import com.mycompany.myapp.utils.Statics;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.mycompany.myapp.entities.Evenement;
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
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
}
     
    public boolean annulerParticipation(int id_evenement) {
    String url = Statics.BASE_URL + "/participationJson/delete/" + id_evenement;
    req.setUrl(url);
    boolean[] resultOk = new boolean[1];
    req.addResponseListener(e -> {
        JSONParser parser = new JSONParser();
        try {
            Map<String, Object> resultMap = parser.parseJSON(new InputStreamReader(new ByteArrayInputStream(req.getResponseData()), "UTF-8"));
            String message = (String) resultMap.get("message");
            if (message.contains("Participation deleted successfully")) {
                resultOk[0] = true;
            } else {
                resultOk[0] = false;
            }
        }  catch(Exception ex) {
                    
                    ex.printStackTrace();
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
    return resultOk[0];
}
    
   public ArrayList<Evenement> mesParticipations(int id_user) {
    ArrayList<Evenement> result = new ArrayList<>();
    String url = Statics.BASE_URL + "/participationJson/user/participations/" + id_user;
    
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


    
}
