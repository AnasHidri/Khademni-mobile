/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.services;

import com.mycompany.entities.Offre;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Container;

import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.mycompany.entities.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.mycompany.statics.Statics;

/**
 *
 * @author CYBERLAND
 */
public class OffreService {
     public ArrayList<Offre> offres;

    public static OffreService instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private OffreService() {
        req = new ConnectionRequest();
    }

    public static OffreService getInstance() {
        if (instance == null) {
            instance = new OffreService();
        }
        return instance;
    }

   public boolean addOffre(Offre o) {
 User u = User.getCurrent_User();
    String titre = o.getTitre();
    String description = o.getDescription();
    String adresse_societe = o.getAdresse_societe();
    String domaine_offre = o.getDomaine_offre();
  
 String url = Statics.BASE_URL + "/offreApiRest/ajout/offreApiRest?" 
           + "titre=" + titre 
           + "&description=" + description 
           + "&adresse_societe=" + adresse_societe 
           + "&domaine_offre=" + domaine_offre 
            + "&iduser=" + u.getIdUser() 
           ;

   // Configure connection request
        req.setUrl(url);
        req.setPost(false);

        // Add response listener to handle server response
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                // Set flag indicating if operation succeeded based on HTTP response code
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK

                // Remove this listener from the request
                req.removeResponseListener(this);
            }
        });

        // Send request to server and wait for response
        NetworkManager.getInstance().addToQueueAndWait(req);

        // Return flag indicating if operation succeeded
        return resultOK;
    }
//-------------------------client---------------------
   
    public ArrayList<Offre> parseOffres(String jsonText) {
        try {
            offres = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> offresListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) offresListJson.get("root");
            for (Map<String, Object> obj : list) {
                Offre o = new Offre();
                 float id_offre = Float.parseFloat(obj.get("idOffre").toString());
                o.setId_offre((int) id_offre);   
                if (obj.get("titre") == null) {
                    o.setTitre("null");
                } else {
                    o.setTitre(obj.get("titre").toString());
                }
                
                if (obj.get("description") == null) {
                    o.setDescription("null");
                } else {
                    o.setDescription(obj.get("description").toString());
                }
                 if (obj.get("adresseSociete") == null) {
                    o.setAdresse_societe("null");
                } else {
                    o.setAdresse_societe(obj.get("adresseSociete").toString());
                }
                if (obj.get("domaineOffre") == null) {
                    o.setDomaine_offre("null");
                } else {
                    o.setDomaine_offre(obj.get("domaineOffre").toString());
                }
               offres.add(o);
            
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return offres;
    }

    public ArrayList<Offre> getAllOffres() {
        String url = Statics.BASE_URL + "/offreApiRest/liste/clientApiRest";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                offres = parseOffres(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return offres;
    }
    //---------------------------Employeur-----------------------------------
     public ArrayList<Offre> parseOffresEmplo(String jsonText) {
        try {
            offres = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> offresEmpListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) offresEmpListJson.get("root");
            for (Map<String, Object> obj : list) {
                Offre o = new Offre();
                
              float id_offre = Float.parseFloat(obj.get("idOffre").toString());
                o.setId_offre((int) id_offre);   
                if (obj.get("titre") == null) {
                    o.setTitre("null");
                } else {
                    o.setTitre(obj.get("titre").toString());
                }   if (obj.get("description") == null) {
                    o.setDescription("null");
                } else {
                    o.setDescription(obj.get("description").toString());
                }  
                 if (obj.get("etat") == null) {
                    o.setEtat("null");
                } else {
                    o.setEtat(obj.get("etat").toString());
                }  
                 if (obj.get("adresseSociete") == null) {
                    o.setAdresse_societe("null");
                } else {
                    o.setAdresse_societe(obj.get("adresseSociete").toString());
                } 
                  if (obj.get("domaineOffre") == null) {
                    o.setDomaine_offre("null");
                } else {
                    o.setDomaine_offre(obj.get("domaineOffre").toString());
                } 
               offres.add(o);
            
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return offres;
    }

   
    public ArrayList<Offre> getAllOffresEmplo() {
         User u = User.getCurrent_User();
        String url = Statics.BASE_URL + "/offreApiRest/employeurApiRest/Mesoffres/"+u.getIdUser();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                offres = parseOffresEmplo(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return offres;
    }
    //------------------------------------Admin--------------------------------------
    
    
    
   
    public ArrayList<Offre> parseOffresAdmin(String jsonText) {
    try {
        offres = new ArrayList<>();
        JSONParser j = new JSONParser();
        Map<String, Object> offresAdmListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

        List<Map<String, Object>> list = (List<Map<String, Object>>) offresAdmListJson.get("root");
        for (Map<String, Object> obj : list) {
            Offre o = new Offre();
            
                 float id_offre = Float.parseFloat(obj.get("idOffre").toString());
                o.setId_offre((int) id_offre);       
                if (obj.get("titre") == null) {
                    o.setTitre("null");
                } else {
                    o.setTitre(obj.get("titre").toString());
                }
                
                if (obj.get("description") == null) {
                    o.setDescription("null");
                } else {
                    o.setDescription(obj.get("description").toString());
                }
                if (obj.get("adresseSociete") == null) {
                    o.setAdresse_societe("null");
                } else {
                    o.setAdresse_societe(obj.get("adresseSociete").toString());
                }
                if (obj.get("domaineOffre") == null) {
                    o.setDomaine_offre("null");
                } else {
                    o.setDomaine_offre(obj.get("domaineOffre").toString());
                }if (obj.get("etat") == null) {
                    o.setEtat("null");
                } else {
                    o.setEtat(obj.get("etat").toString());
                }
             
            offres.add(o);
        }
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    return offres;
}

    public ArrayList<Offre> getAllOffresAdmin() {
        String url = Statics.BASE_URL + "/offreApiRest/";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                offres = parseOffresAdmin(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return offres;
    }

  /*  public ArrayList<Offre> getAllOffresAdmin() {
    String url = Statics.BASE_URL + "offreApiRest/";
    ConnectionRequest request = new ConnectionRequest();
    request.setUrl(url);
   
    ArrayList<Offre> offres = new ArrayList<>();
    request.addResponseListener((evt) -> {
        String jsonText = new String(request.getResponseData());
        try {
            offres.addAll(parseoffres(jsonText));
        } catch (IOException ex) {
            
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(request);
    return offres;
}

private ArrayList<Offre> parseoffres(String jsonText) throws IOException {
    ArrayList<Offre> offres = new ArrayList<>();
    JSONParser j = new JSONParser();
    Map<String,Object> offreListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
    List<Map<String, Object>> list = (List<Map<String, Object>>) offreListJson.get("root");
    for (Map<String, Object> obj : list) {
        Offre o = new Offre();
        if (obj.containsKey("id_offre")) {
            o.setId_offre((int) Double.parseDouble(obj.get("id_offre").toString()));
        } else {
            System.out.println("La clé 'id_offre' n'existe pas dans l'objet JSON.");
        }
       if (obj.get("titre") == null) {
                o.setTitre("null");
            } else {
                o.setTitre(obj.get("titre").toString());
            
            }
             if (obj.containsKey("etat")) {
                o.setEtat(obj.get("etat").toString());
        } else {
            System.out.println("La clé 'etat' n'existe pas dans l'objet JSON.");
        }
       
        offres.add(o);
    }
    return offres;
}









*/




//----------------------------Delete
public boolean deleteOffre(int id_offre ) {
        String url = Statics.BASE_URL +"/offreApiRest/deleteApiRest/?id="+id_offre;
        
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    
                    req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  resultOK;
    }




 public ArrayList<Offre> parseDetailsOffresEmplo(String jsonText) {
        try {
            offres = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> offresEmpListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) offresEmpListJson.get("root");
            for (Map<String, Object> obj : list) {
                Offre o = new Offre();
                
              float id_offre = Float.parseFloat(obj.get("idOffre").toString());
                o.setId_offre((int) id_offre);   
                if (obj.get("titre") == null) {
                    o.setTitre("null");
                } else {
                    o.setTitre(obj.get("titre").toString());
                    
                }   if (obj.get("description") == null) {
                    o.setDescription("null");
                } else {
                    o.setDescription(obj.get("description").toString());
                    
                }  
               if (obj.get("adresseSociete") == null) {
                    o.setAdresse_societe("null");
                } else {
                    o.setAdresse_societe(obj.get("adresseSociete").toString());
                }  
                  if (obj.get("domaineOffre") == null) {
                    o.setDomaine_offre("null");
                } else {
                    o.setDomaine_offre(obj.get("domaineOffre").toString());
                }    if (obj.get("etat") == null) {
                    o.setEtat("null");
                } else {
                    o.setEtat(obj.get("etat").toString());
                } 
               offres.add(o);
            
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return offres;
    }

   
   public ArrayList<Offre> getDetailsOffresEmplo(Offre offre) {
    String url = Statics.BASE_URL + "/offreApiRest/offreApiRest/employeur/"+offre.getId_offre();
    req.setUrl(url);
    req.setPost(false);
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            offres = parseDetailsOffresEmplo(new String(req.getResponseData()));
            req.removeResponseListener(this);
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
    return offres;
}

      public ArrayList<Offre> getModifierOffresEmplo(Offre offre) {
          String url = Statics.BASE_URL + "/offreApiRest/employeurApiRest/" +offre.getId_offre()  + "/edit?titre=" +offre.getTitre() + "&description=" + offre.getDescription()+ "&adresse_societe=" + offre.getAdresse_societe() + "&domaine_offre=" + offre.getDomaine_offre();

    req.setUrl(url);
    req.setPost(false);
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            offres = parseDetailsOffresEmplo(new String(req.getResponseData()));
            req.removeResponseListener(this);
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
    return offres;
}
}




