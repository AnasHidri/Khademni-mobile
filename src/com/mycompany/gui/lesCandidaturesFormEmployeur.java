/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;

import com.mycompany.services.CandidatureService;
import com.mycompany.services.OffreService;
import com.mycompany.entities.Candidature;
import com.mycompany.entities.Offre;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.mycompany.entities.User;
import java.util.ArrayList;
import java.util.Map;
import com.mycompany.statics.Statics;

/**
 *
 * @author CYBERLAND
 */
public class lesCandidaturesFormEmployeur extends Form {
      public lesCandidaturesFormEmployeur(Form previous) {
        setTitle("Liste Les Candidatures");
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));

                       
        ArrayList<Candidature> candidature = CandidatureService.getInstance().getAllCandidatures();

        for (Candidature c : candidature) {
            addElement(c);
        }
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }






public void addElement(Candidature candidature) {

    int id_candidature = candidature.getId_candidature();
    Label id_candidatureLabel = new Label("Id_candidatutre : " + id_candidature);
    System.out.println("hdhdg"+id_candidature);
String etat = candidature.getEtat();
Label etatLabel = new Label("Etat : " + etat);
int id_offre = candidature.getId_offre();
    Label id_offreLabel = new Label("Id_offre : " + id_offre);
    System.out.println("hdhdg"+id_candidature);
    Button AccepterButton = new Button("Accepter");
    AccepterButton.addActionListener((evt) -> {
  boolean value = Dialog.show("Confirmation", "Voulez-vous vraiment Accepter cette Candidature?", "Oui", "Non");
    if (value == true) {
          User u = User.getCurrent_User();
             String url = Statics.BASE_URL +"/candidatureApiRest/candidatureApiRest/"+id_candidature+"/accepter/"+u.getIdUser();
            ConnectionRequest request = new ConnectionRequest();
            request.setUrl(url);
            
            request.addResponseListener((NetworkEvent evtt) -> {
                
                String response = new String(request.getResponseData());
                System.out.println("Candidature Accepté: " + response);
            });
            NetworkManager.getInstance().addToQueue(request);
    }});
   
       Button RefuserButton = new Button("Refuser");
        RefuserButton.addActionListener((evt) -> {
                         boolean value = Dialog.show("Confirmation", "Voulez-vous vraiment Refuser cette candidature?", "Oui", "Non");
    if (value == true) {

             String url = Statics.BASE_URL + "/candidatureApiRest/candidatureApiRest/"+id_candidature+"/refuser";
            ConnectionRequest request = new ConnectionRequest();
            request.setUrl(url);
            
            request.addResponseListener((NetworkEvent evtt) -> {
                String response = new String(request.getResponseData());
                System.out.println("Candidature Refusé: " + response);
            });
            NetworkManager.getInstance().addToQueue(request);
    }  });


    Button DetailsButton = new Button("Details");
        DetailsButton.addActionListener((evt) -> {
    
        String url = Statics.BASE_URL +"/candidatureApiRest/allApiRest/"+id_offre;
        ConnectionRequest request = new ConnectionRequest();
        request.setUrl(url);

        request.addResponseListener((NetworkEvent evtt) -> {
            String response = new String(request.getResponseData());
            System.out.println("Offre Supprimé: " + response);

            // Redémarre l'application
        });
        NetworkManager.getInstance().addToQueue(request);
   
});
    Container container = new Container(new GridLayout(3, 1));
    container.add(id_candidatureLabel);
    container.add(etatLabel);
    container.add(id_offreLabel);

    container.add(BoxLayout.encloseX( AccepterButton,RefuserButton));

    addComponent(container);
}


}