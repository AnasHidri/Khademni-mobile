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
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.materialscreens.LoginForm;
import com.codename1.uikit.materialscreens.ProfileForm;
import com.mycompany.entities.User;
import com.mycompany.gui.ListUsers;
import com.mycompany.gui.ProfileSettingsForm;
import com.mycompany.statics.Statics;
import java.io.ByteArrayInputStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.text.ParseException;

/**
 *
 * @author ASUS
 */
public class UtilisateurService {
    
    
     //singleton 
    public static UtilisateurService instance = null ;
        public ArrayList<User> users;

    public static boolean resultOk = true;

    //initilisation connection request 
    private ConnectionRequest req;
    
    
    public static UtilisateurService getInstance() {
        if(instance == null )
            instance = new UtilisateurService();
        return instance ;
    }
    
    
    
    public UtilisateurService() {
        req = new ConnectionRequest();
        
    }

    
    public ArrayList<User> filterUsers(String filter) {
    ArrayList<User> users = getAllUsers();
    ArrayList<User> filteredUsers = new ArrayList<>();
    for (User user : users) {
        String fullName = user.getNom() + " " + user.getPrenom();
        String role = user.getRole();
        String mail = user.getMail();
        String domaine = user.getDomaine();
        if (fullName.toLowerCase().contains(filter.toLowerCase())
                || role.toLowerCase().contains(filter.toLowerCase())
                || mail.toLowerCase().contains(filter.toLowerCase())
                || domaine.toLowerCase().contains(filter.toLowerCase())) {
            filteredUsers.add(user);
        }
    }
    return filteredUsers;
}
    
public ArrayList<User> parseUsers(String jsonText) {
    try {
        ArrayList<User> users = new ArrayList<>();
        JSONParser parser = new JSONParser();
        Map<String, Object> usersListJson = parser.parseJSON(new CharArrayReader(jsonText.toCharArray()));

        List<Map<String, Object>> list = (List<Map<String, Object>>) usersListJson.get("root");
        for (Map<String, Object> obj : list) {
            User user = new User();
                    float id = Float.parseFloat(obj.get("id_user").toString());
                    user.setIdUser((int)id);
                    user.setNom(obj.get("nom").toString());
                    user.setPrenom(obj.get("prenom").toString());
                    user.setRole(obj.get("role").toString());
                    user.setMail(obj.get("mail").toString());
                    user.setDomaine(obj.get("domaine").toString());
            users.add(user);
        }
        return users;
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
        return null;
    }
}

public ArrayList<User> getAllUsers() {
    String url = Statics.BASE_URL + "/userAPI/listeAPi";
    ConnectionRequest req = new ConnectionRequest(url);
    req.setPost(false);
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            users = parseUsers(new String(req.getResponseData())); // assign the obtained ArrayList to the users field
            req.removeResponseListener(this);
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
    return users;
}


    
public boolean deleteUser(int id) {
    String url = Statics.BASE_URL + "/userAPI/deleteAPI/" + id;
    ConnectionRequest req = new ConnectionRequest(url);

    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            // Check if the response was successful
            if (req.getResponseCode() == 200) {
                resultOk = true;
            } else {
                resultOk = false;
            }
        }
    });

    NetworkManager.getInstance().addToQueueAndWait(req);
    return resultOk;
}
    
  //Update User
    public boolean updateUser(User user) {
        String url = Statics.BASE_URL + "/userAPI/updateAPI/"+user.getIdUser()+"?nom="+user.getNom()+"&prenom="+user.getPrenom()+"&mail="+user.getMail()+"&domaine="+user.getDomaine();

        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200 ;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });
        
    NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
    return resultOk;
        
    }
    
      //Update User PAssword
    public boolean updateUserPass(User user,String actuelPassword,String newPass) {
        String url = Statics.BASE_URL + "/userAPI/updatePassAPI/"+user.getIdUser()+"?actuelPassword="+actuelPassword+"&password="+newPass;

        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200 ;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });
        
    NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
    return resultOk;
        
    }
    
    
public boolean signUp(String role, String nom, String prenom, String mail, String domaine, String login, String password) {
    String url = Statics.BASE_URL + "/userAPI/addAPI/new?role=" + role + "&nom=" + nom + "&prenom=" + prenom + "&mail=" + mail + "&domaine=" + domaine + "&login=" + login + "&password=" + password;

    ConnectionRequest req = new ConnectionRequest();
    req.setUrl(url);
    req.setPost(false);

    boolean[] resultOK = { false };
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            byte[] responseData = req.getResponseData();
            String response = new String(responseData);
            resultOK[0] = req.getResponseCode() == 200;
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
    return resultOK[0];
}

public boolean checkLogin(String login) {
    String url = Statics.BASE_URL + "/userAPI/checkLoginApi/" + login;
    ConnectionRequest req = new ConnectionRequest();
    req.setUrl(url);

    NetworkManager.getInstance().addToQueueAndWait(req);

    int responseCode = req.getResponseCode();
    System.out.println("responseCode  : "+responseCode);

    if (responseCode == 200) {
        // the login is available
        System.out.println("login not used : ");
        return true;
    } else if (responseCode == 400) {
        // the login is not available
        System.out.println(" login used  ");
        return false;
    } else {
        // some other error occurred
        return false;
    }
}

public boolean checkEmail(String mail) {
    String url = Statics.BASE_URL + "/userAPI/checkEmailApi/" + mail;
    ConnectionRequest req = new ConnectionRequest();
    req.setUrl(url);

    NetworkManager.getInstance().addToQueueAndWait(req);

    int responseCode = req.getResponseCode();
    System.out.println("responseCode  : "+responseCode);

    if (responseCode == 200) {
        // the login is available
        System.out.println(" mail not used : ");
        return true;
    } else if (responseCode == 400) {
        // the login is not available
        System.out.println(" mail used  ");
        return false;
    } else {
        // some other error occurred
        return false;
    }
    
}

public void login(String login,String password,Resources theme) {
    String url = Statics.BASE_URL + "/userAPI/user/"+login+"/"+password;
    ConnectionRequest request = new ConnectionRequest() {
        @Override
        protected void readResponse(InputStream input) throws IOException {
            JSONParser parser = new JSONParser();
            Map<String, Object> response = parser.parseJSON(new InputStreamReader(input));
            // TODO: Handle response data
                        if(response.get("id_user")!= null){
                        System.out.println("Response: " +  response.get("role")); // Print the response map to the console
                        System.out.println("Response: " + response.toString()); 
                        // Session
                        User u = new User();
                        u.setIdUser((int) (double) response.get("id_user"));
                        u.setRole((String) response.get("role"));
                        u.setNom((String) response.get("nom"));
                        u.setPrenom((String) response.get("prenom"));
                        u.setMail((String) response.get("mail"));
                        u.setDomaine((String) response.get("domaine"));
                        u.setSolde((float)(double) response.get("solde"));
                        u.setLogin((String) response.get("login"));
                        User.setCurrent_User(u);
                        
                        
                        // Pour utiliser la session : 
                        // String nomCurrent=User.getCurrent_User().getNom();
                        // System.out.println("current :: "+User.getCurrent_User().toString());
                      if(response.get("role").equals("Admin")){
                                new ListUsers(theme).show();
                            }else{
                                new ProfileSettingsForm(theme).show();
                            }
                        }else{
                         Dialog.show("Erreur", (String) response.get("message"), "OK", null);
                        }

        }
    };
    request.setUrl(url);
    NetworkManager.getInstance().addToQueue(request);
}




}
