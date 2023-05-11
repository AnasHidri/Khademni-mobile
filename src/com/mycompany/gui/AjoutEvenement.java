/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;


import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;

import com.mycompany.entities.Evenement;
import com.mycompany.services.EvenementService;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Lenovo
 */
public class AjoutEvenement extends Form {
    private Form current;

    public AjoutEvenement(Resources res) {
        super("Evenement", BoxLayout.y());

        Toolbar tb = new Toolbar(true);
        current = this;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Ajout Evenement");
        getContentPane().setScrollVisible(false);

        Picker dateDebut = new Picker();
        dateDebut.setType(Display.PICKER_TYPE_DATE);
        dateDebut.setUIID("TextFieldBlack");
        addStringValue("Date début", dateDebut);

        Picker dateFin = new Picker();
        dateFin.setType(Display.PICKER_TYPE_DATE);
        dateFin.setUIID("TextFieldBlack");
        addStringValue("Date fin", dateFin);

        TextField titre = new TextField("", "entrer titre!!");
        titre.setUIID("TextFieldBlack");
        addStringValue("titre",titre);

        TextField description = new TextField("", "entrer description!!");
        description.setUIID("TextFieldBlack");
        addStringValue("description",description);

        TextField nom_societe = new TextField("", "entrer nom_societe!!");
        nom_societe.setUIID("TextFieldBlack");
        addStringValue("nom_societe",nom_societe);

        TextField lieu = new TextField("", "entrer lieu!!");
        lieu.setUIID("TextFieldBlack");
        addStringValue("lieu",lieu);

        Button btnAjouter = new Button("Ajouter");
        addStringValue("", btnAjouter);

        //onclick button event 

        btnAjouter.addActionListener((e) -> {
            try {
                if(titre.getText().equals("") || description.getText().equals("") || nom_societe.getText().equals("") || lieu.getText().equals("")) {
                    Dialog.show("Veuillez vérifier les données","","Annuler", "OK");
                } else {
                    Evenement ev = new Evenement(
                            1,
                            dateDebut.getDate(),
                            dateFin.getDate(),
                            String.valueOf(titre.getText()),
                            String.valueOf(description.getText()),
                            String.valueOf(nom_societe.getText()),
                            String.valueOf(lieu.getText())
                            
                    );
                    System.out.println("data  evenement == "+ev);

                    //appelle methode ajouterReclamation mt3 service Reclamation bch nzido données ta3na fi base 
                    EvenementService.getInstance().ajoutEvenement(ev);

                new ListeEvenementEmp(res).show();
                    
                    
                    refreshTheme();//Actualisation
                }
            } catch(Exception ex ) {
                ex.printStackTrace();
            }
        });
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

        
    
    
//
//    private void addTab(Tabs swipe, Label spacer , Image image, String string, String text, Resources res) {
//        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
//        
//        if(image.getHeight() < size) {
//            image = image.scaledHeight(size);
//        }
//        
//        
//        
//        if(image.getHeight() > Display.getInstance().getDisplayHeight() / 2 ) {
//            image = image.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
//        }
//        
//        ScaleImageLabel imageScale = new ScaleImageLabel(image);
//        imageScale.setUIID("Container");
//        imageScale.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
//        
//        Label overLay = new Label("","ImageOverlay");
//        
//        
//        Container page1 = 
//                LayeredLayout.encloseIn(
//                imageScale,
//                        overLay,
//                        BorderLayout.south(
//                        BoxLayout.encloseY(
//                        new SpanLabel(text, "LargeWhiteText"),
//                                        spacer
//                        )
//                    )
//                );
//        
//        swipe.addTab("",res.getImage("back-logo.jpeg"), page1);
//        
//        
//        
//        
//    }
//    
//    
//    
//    public void bindButtonSelection(Button btn , Label l ) {
//        
//        btn.addActionListener(e-> {
//        if(btn.isSelected()) {
//            updateArrowPosition(btn,l);
//        }
//    });
//    }
//
//    private void updateArrowPosition(Button btn, Label l) {
//        
//        l.getUnselectedStyle().setMargin(LEFT, btn.getX() + btn.getWidth()  / 2  - l.getWidth() / 2 );
//        l.getParent().repaint();
//    }
//    
//   
//   
//    
//}
