/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.gui;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Evenement;
import com.mycompany.myapp.services.EvenementService;
import java.text.SimpleDateFormat;

/**
 *
 * @author user
 */
public class ModifierEvenement extends Form{
     Form current;
    public ModifierEvenement(Resources res , Evenement r) {
         super("Newsfeed",BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
    
        Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("");
        getContentPane().setScrollVisible(false);
        
        
       // super.addSideMenu(res);
        
        TextField titre = new TextField(r.getTitre(), "titre" , 20 , TextField.ANY);
        TextField description = new TextField(r.getDescription(), "description" , 20 , TextField.ANY);
        TextField lieu = new TextField(r.getLieu(), "lieu" , 20 , TextField.ANY);
        TextField nom_societe = new TextField(r.getNom_societe(), "lieu" , 20 , TextField.ANY);
       /* Picker dateDebut = new Picker();
dateDebut.setType(Display.PICKER_TYPE_DATE);
dateDebut.setUIID("TextFieldBlack");
addStringValue("Date début", dateDebut);

Picker dateFin = new Picker();
dateFin.setType(Display.PICKER_TYPE_DATE);
dateFin.setUIID("TextFieldBlack");
addStringValue("Date fin", dateFin);


 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");*/


 
        //etat bch na3mlo comobbox bon lazm admin ya3mlleha approuver mais just chnwarikom ComboBox
        
        
        titre.setUIID("NewsTopLine");
        description.setUIID("NewsTopLine");
        lieu.setUIID("NewsTopLine");
        nom_societe.setUIID("NewsTopLine");
        
        titre.setSingleLineTextArea(true);
        description.setSingleLineTextArea(true);
        lieu.setSingleLineTextArea(true);
        nom_societe.setSingleLineTextArea(true);
      //  prix_total.setSingleLineTextArea(true);
        
        Button btnModifier = new Button("Modifier");
       btnModifier.setUIID("Button");
       
       //Event onclick btnModifer
       
       btnModifier.addPointerPressedListener(l ->   { 
          
           r.setTitre(titre.getText());
r.setDescription(description.getText());
r.setLieu(lieu.getText());
r.setNom_societe(nom_societe.getText());

/*r.setDate_debut(dateFormat.format(dateDebut.getDate()));
r.setDate_fin(dateFormat.format(dateFin.getDate()));*/
           
                            

           
   
       
       //appel fonction modfier reclamation men service
       
       if(EvenementService.getInstance().modifierEvenement(r)) { // if true
           new ListeEvenement(res).show();
       }
        });
       Button btnAnnuler = new Button("Annuler");
       btnAnnuler.addActionListener(e -> {
           new ListeEvenement(res).show();
       });
       
               Label l1 = new Label();

       Label l2 = new Label("");
       
       Label l3 = new Label("");
       
       Label l4 = new Label("");
       
       Label l5 = new Label("");
       
        
        Container content = BoxLayout.encloseY(
                l1, l2, 
                new FloatingHint(titre),
                createLineSeparator(),
                new FloatingHint(description),
                createLineSeparator(),
             new FloatingHint(lieu),

                createLineSeparator(),//ligne de séparation
                btnModifier,
                btnAnnuler
                
               
        );
        
        add(content);
        show();
        
        
        }
  public Component createLineSeparator() {
        Label separator = new Label("", "WhiteSeparator");
        separator.setShowEvenIfBlank(true);
        return separator;
    }
  
  private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s,"PaddedLabel"))
        .add(BorderLayout.CENTER,v));
        add(createLineSeparator(0xeeeeee));
    }
  
  private Component createLineSeparator(int color) {
    Container cnt = new Container(new FlowLayout(Component.CENTER));
    cnt.add(new Label("", "Separator")).getAllStyles().setBgColor(color);
    cnt.setUIID("Separator");
    return cnt;
}
        
}
