/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;

import com.mycompany.services.OffreService;
import com.mycompany.entities.Offre;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;


/**
 *
 * @author CYBERLAND
 */
public class AddOffreForm extends Form{
     public AddOffreForm(Form previous) {
        setTitle("Add a new Offre");
        setLayout(BoxLayout.y());
 TextField tftitre = new TextField("", "Titre");
TextField tfdescription = new TextField("", "Description");
TextField tfadresse_societe = new TextField("", "adresse_societe");
TextField tfdomaine_offre = new TextField("", "domaine_offre");
ComboBox<String> cbAdresseSociete = new ComboBox<>("Adresse de la société");
        cbAdresseSociete.addItem("Tunis");
        cbAdresseSociete.addItem("Bizerte");
        cbAdresseSociete.addItem("Tozeur");
        
        ComboBox<String> cbDomaineOffre = new ComboBox<>("Domaine de l'offre");
        cbDomaineOffre.addItem("Web Programming");
        cbDomaineOffre.addItem("Game Development");
        cbDomaineOffre.addItem("Digital Marketing");
        

      
Button btnValider = new Button("Add Offre");
btnValider.addActionListener(new ActionListener() {
    @Override
      public void actionPerformed(ActionEvent evt) {
        if ((tftitre.getText().length()==0)||(tfdescription.getText().length()==0)||(cbAdresseSociete.getSelectedItem().length()==0) ||(cbDomaineOffre.getSelectedItem().length()==0) )
            Dialog.show("Alert", "Remplir tous les Champs ", new Command("OK"));
        else
        {
           try {
                // Create a new Offre object with all the fields including the selected date
                Offre o = new Offre(tftitre.getText().toString(), tfdescription.getText().toString(), cbAdresseSociete.getSelectedItem().toString(), cbDomaineOffre.getSelectedItem().toString());

                // Call the addOffre method with the new Offre object
                if (OffreService.getInstance().addOffre(o)) {
                    Dialog.show("Success","Connection accepted",new Command("OK"));
                } else {
                    Dialog.show("ERROR", "Server error", new Command("OK"));
                }
            } catch (NumberFormatException e) {
                Dialog.show("ERROR", "Status must be a number", new Command("OK"));
            }
        }
    }
});

       addAll(tftitre, tfdescription, cbAdresseSociete, cbDomaineOffre, btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());






     }
}
