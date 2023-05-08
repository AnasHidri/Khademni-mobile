/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;

import com.mycompany.services.OffreService;
import com.mycompany.entities.Offre;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import java.util.ArrayList;
import com.mycompany.statics.Statics;

/**
 *
 * @author CYBERLAND
 */
    public class ModifierOffreFromEmplo extends Form {

    public ModifierOffreFromEmplo(Form previous,Offre offre) {
         
        setTitle("Liste des Offres Employeur");
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));


        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
          int id_offre =offre.getId_offre();


    TextField titreTextField = new TextField(offre.getTitre());
    TextField descriptionTextField = new TextField(offre.getDescription());
    
    TextField adresseTextField = new TextField(offre.getAdresse_societe());
    TextField domaineTextField = new TextField(offre.getDomaine_offre());


    Button modifierButton = new Button("Modifier");
    modifierButton.addActionListener((evt) -> {
        String url = Statics.BASE_URL + "/offreApiRest/employeurApiRest/" + id_offre + "/edit?titre=" + titreTextField.getText() + "&description=" + descriptionTextField.getText() + "&adresse_societe=" + adresseTextField.getText() + "&domaine_offre=" + domaineTextField.getText();
        ConnectionRequest request = new ConnectionRequest();
        request.setUrl(url);
    
        request.addResponseListener((NetworkEvent evtt) -> {
            String response = new String(request.getResponseData());
            System.out.println("Offre Modifié: " + response);
        });
        NetworkManager.getInstance().addToQueue(request);
    });

    Button supprimerButton = new Button("Supprimer");
    supprimerButton.addActionListener((evt) -> {
        String url = Statics.BASE_URL +"/offreApiRest/deleteApiRest/?id_offre="+offre.getId_offre();
        ConnectionRequest request = new ConnectionRequest();
        request.setUrl(url);
    
        request.addResponseListener((NetworkEvent evtt) -> {
            String response = new String(request.getResponseData());
            System.out.println("Offre Supprimé: " + response);
        });
        NetworkManager.getInstance().addToQueue(request);
    });

    Container container = new Container(new GridLayout(2, 2));
    
    container.add(new Label("Titre"));
    container.add(titreTextField);
    container.add(new Label("Description"));
    container.add(descriptionTextField);
    container.add(new Label("Adresse"));
    container.add(adresseTextField);
    container.add(new Label("Domaine"));
    container.add(domaineTextField);
        
    container.add(BoxLayout.encloseX(modifierButton, supprimerButton));
       addComponent(container); 
    
    }


    }
