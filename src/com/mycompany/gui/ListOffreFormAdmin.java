
package com.mycompany.gui;

import com.mycompany.services.OffreService;
import com.mycompany.entities.Offre;
import com.codename1.components.MultiButton;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import java.util.ArrayList;
import com.mycompany.statics.Statics;


/**
 *
 * @author CYBERLAND
 */


public class ListOffreFormAdmin extends Form {

    public ListOffreFormAdmin(Form previous) {
        setTitle("Liste des Offres Admin");
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));

        ArrayList<Offre> offre = OffreService.getInstance().getAllOffresAdmin();
       for (Offre o : offre) {
    addElement(o); 
}

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }




public void addElement(Offre offre) {
    
    int id_offre =offre.getId_offre();

       String titre = offre.getTitre();
    Label titreLabel = new Label("Titre : " + titre);
     String description = offre.getDescription();
     
     System.out.println("testtt1"+description);
    Label DescriptionLabel = new Label("Description : " + description);
 

         String adresse = offre.getAdresse_societe();
    Label AdresseLabel = new Label("Adresse_societe : " + adresse);
    
          String domaine_offre = offre.getDomaine_offre();
    Label DomaineLabel = new Label("DomaineOffre : " + domaine_offre);
    
     String etat = offre.getEtat();
    Label EtatLabel = new Label("etat : " + etat);
    
    


        
    Button AccepterButton = new Button("Accepter");
    AccepterButton.addActionListener((evt) -> {
  boolean value = Dialog.show("Confirmation", "Voulez-vous vraiment Accepter cette offre?", "Oui", "Non");
    if (value == true) {
             String url = Statics.BASE_URL + "/offreApiRest/adminApiRest/"+id_offre+"/accepter";
            ConnectionRequest request = new ConnectionRequest();
            request.setUrl(url);
            
            request.addResponseListener((NetworkEvent evtt) -> {
                
                String response = new String(request.getResponseData());
                System.out.println("Offre Accepté: " + response);
            });
            NetworkManager.getInstance().addToQueue(request);
    }});
   
       Button RefuserButton = new Button("Refuser");
        RefuserButton.addActionListener((evt) -> {
                         boolean value = Dialog.show("Confirmation", "Voulez-vous vraiment Refuser cette offre?", "Oui", "Non");
    if (value == true) {

             String url = Statics.BASE_URL + "/offreApiRest/adminApiRest/"+id_offre+"/refuser";
            ConnectionRequest request = new ConnectionRequest();
            request.setUrl(url);
            
            request.addResponseListener((NetworkEvent evtt) -> {
                String response = new String(request.getResponseData());
                System.out.println("Offre Refusé: " + response);
            });
            NetworkManager.getInstance().addToQueue(request);
    }  });

    Container container = new Container(new GridLayout(4, 1));

 container.add(titreLabel);
        container.add(DescriptionLabel);
   
        container.add(AdresseLabel);
        container.add(DomaineLabel);
                container.add(EtatLabel);


    container.add(BoxLayout.encloseX( AccepterButton,RefuserButton));
    
    addComponent(container);
}
    


   /*  private ArrayList<Offre> offres;

    public ListOffreFormAdmin(Form previous) {
        setTitle("Liste des Offres");
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));

        Button fetchButton = new Button("Afficher les Offres");
        fetchButton.addActionListener((evt) -> {
            offres = OffreService.getInstance().getAllOffresAdmin();
            removeAll();

            if (offres.isEmpty()) {
                add(new Label("Aucun Offre trouvé"));
            } else {
                // Add header row
                addHeaderRow("id", "titre", "Etat");
                // Add data rows
                for (Offre offre : offres) {
                    addOffre(offre);
                }
            }

            revalidate();
        });

        add(fetchButton);
    }

    private void addHeaderRow(String col1, String col2, String col3) {
        MultiButton mb = new MultiButton();
        mb.setTextLine1(col1);
        mb.setTextLine2(col2);
        mb.setTextLine3(col3);
        mb.setUIID("TableHeader");
        add(mb);
    }

    private void addOffre(Offre offre) {
        MultiButton mb = new MultiButton();
        mb.setTextLine1(Integer.toString(offre.getId_offre()));
        mb.setTextLine2(offre.getTitre());
        mb.setTextLine3(offre.getEtat());

        Button AccepterButton = new Button("Accepter");
        AccepterButton.addActionListener((evt) -> {
            int id_offre = offre.getId_offre();
            
             String url = Statics.BASE_URL + "offreApiRest/adminApiRest/" + id_offre + "/Accepter";
            ConnectionRequest request = new ConnectionRequest();
            request.setUrl(url);
            
            request.addResponseListener((NetworkEvent evtt) -> {
                String response = new String(request.getResponseData());
                System.out.println("OffreAccepter: " + response);
                // Do something here when confirmation is successful
            });
            NetworkManager.getInstance().addToQueue(request);
        });

        mb.addComponent(BorderLayout.EAST, AccepterButton);

        add(mb);
    }

    // Method to get the Appointment object from the list based on the clicked button
    
*/
    
   
}
 
    
   