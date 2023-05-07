/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;

import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;

import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;

import com.mycompany.entities.Evenement;
import com.mycompany.services.EvenementService;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;


/**
 *
 * @author user
 */
public class ListeEvenement extends Form{
   
  Form current;
    public ListeEvenement(Resources res ) {
          super("Evenement",BoxLayout.y()); 
        Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("");
        getContentPane().setScrollVisible(false);
        
        
        tb.addSearchCommand(e ->  {
            
        });
        
        Tabs swipe = new Tabs();
        
        Label s1 = new Label();
        Label s2 = new Label();
        
        addTab(swipe,s1, res.getImage("activite.png"),"","",res);
        
        // Welcome current user
        
//        System.out.println("user connecté id ="+ SessionManager.getId());
//        
//        
//        
//        System.out.println("user connecté username ="+ SessionManager.getUserName());
//        
//        System.out.println("user connecté password ="+ SessionManager.getPassowrd());
//        
//        System.out.println("user connecté email ="+ SessionManager.getEmail());
        
        
        
        
         swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();

        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for (int iter = 0; iter < rbs.length; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }

       // rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if (!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });

        
        
        Component.setSameSize(radioContainer, s1, s2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));

        ButtonGroup barGroup = new ButtonGroup();
        RadioButton mesListes = RadioButton.createToggle("Les evenement", barGroup);
        mesListes.setUIID("SelectBar");
        RadioButton stats = RadioButton.createToggle("stats", barGroup);
        stats.setUIID("SelectBar");
        RadioButton add = RadioButton.createToggle("Ajouter", barGroup);
        add.setUIID("SelectBar");
         RadioButton mesParticipations = RadioButton.createToggle("Mes Participations", barGroup);
        mesParticipations.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");


        mesListes.addActionListener((e) -> {
               InfiniteProgress ip = new InfiniteProgress();
        final Dialog ipDlg = ip.showInifiniteBlocking();
        
          ListeEvenement a = new ListeEvenement(res);
            a.show();
            refreshTheme();
        });
        
         add.addActionListener((e) -> {
               InfiniteProgress ip = new InfiniteProgress();
        final Dialog ipDlg = ip.showInifiniteBlocking();
    
         new AjoutEvenement(res).show();
           
            refreshTheme();
              updateArrowPosition(add, arrow);
        });
        
         mesParticipations.addActionListener((e) -> {
               InfiniteProgress ip = new InfiniteProgress();
        final Dialog ipDlg = ip.showInifiniteBlocking();
    
         new MesParticipations(res).show();
           
            refreshTheme();
              updateArrowPosition(mesParticipations, arrow);
        });
 /*stats.addActionListener((e) -> {
               InfiniteProgress ip = new InfiniteProgress();
        final Dialog ipDlg = ip.showInifiniteBlocking();
    
         new StatistiquePieForm(res).show();
           
            refreshTheme();
              updateArrowPosition(stats, arrow);
        });*/
        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(4, mesListes, stats, add,mesParticipations),
                FlowLayout.encloseBottom(arrow)
        ));

        add.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(add, arrow);
        });
        bindButtonSelection(mesListes, arrow);
        bindButtonSelection(stats, arrow);
        bindButtonSelection(add, arrow);
        bindButtonSelection(mesParticipations, arrow);
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });
        
      
        //Appel affichage methode
        ArrayList<Evenement>list = EvenementService.getInstance().affichageEvenement();
        
        for(Evenement rec : list ) {
             String urlImage ="activite.png";//image statique pour le moment ba3d taw fi  videos jayin nwarikom image 
            
             Image placeHolder = Image.createImage(120, 90);
             EncodedImage enc =  EncodedImage.createFromImage(placeHolder,false);
             URLImage urlim = URLImage.createToStorage(enc, urlImage, urlImage, URLImage.RESIZE_SCALE);
             
                addButton(urlim,rec,res);
        
                ScaleImageLabel image = new ScaleImageLabel(urlim);
                
                Container containerImg = new Container();
                
                image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        }
        
        
        
    }
    
    
    
    
    
    
    
    
       private void addTab(Tabs swipe, Label spacer , Image image, String string, String text, Resources res) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        
/*        if(image.getHeight() < size) {
            image = image.scaledHeight(size);
        }
        
        
        
        if(image.getHeight() > Display.getInstance().getDisplayHeight() / 2 ) {
            image = image.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }*/
        
        ScaleImageLabel imageScale = new ScaleImageLabel(image);
        imageScale.setUIID("Container");
        imageScale.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        
        Label overLay = new Label("","ImageOverlay");
        
        
        Container page1 = 
                LayeredLayout.encloseIn(
                imageScale,
                        overLay,
                        BorderLayout.south(
                        BoxLayout.encloseY(
                        new SpanLabel(text, "LargeWhiteText"),
                                        spacer
                        )
                    )
                );
        
        swipe.addTab("",res.getImage("back-logo.jpeg"), page1);
        
        
        
        
    }
    
    
    
    public void bindButtonSelection(Button btn , Label l ) {
        
        btn.addActionListener(e-> {
        if(btn.isSelected()) {
            updateArrowPosition(btn,l);
        }
    });
    }

    private void updateArrowPosition(Button btn, Label l) {
        
        l.getUnselectedStyle().setMargin(LEFT, btn.getX() + btn.getWidth()  / 2  - l.getWidth() / 2 );
        l.getParent().repaint();
    }

    private void addButton(Image img,Evenement rec , Resources res) {
        
        int height = Display.getInstance().convertToPixels(11.5f);
        int width = Display.getInstance().convertToPixels(14f);
        
        Button image = new Button(img.fill(width, height));
        image.setUIID("Label");
        Container cnt = BorderLayout.west(image);
        
        
        //kif nzidouh  ly3endo date mathbih fi codenamone y3adih string w y5alih f symfony dateTime w ytab3ni cha3mlt taw yjih
    //   
    
    Label dateDebuttxt = new Label("date deb : "+rec.getDate_debut(),"NewsTopLine2");
    Label dateFintxt = new Label("date fin : "+rec.getDate_fin(),"NewsTopLine2");
        Label nomevtxt = new Label("Titre evenement : "+rec.getTitre(),"NewsTopLine2");
        Label descriptiontxt = new Label("Description : "+rec.getDescription(),"NewsTopLine2");
        Label nom_socTxt = new Label("Societe Organisatrice : "+rec.getNom_societe(),"NewsTopLine2" );
        Label lieuTxt = new Label("lieu : "+rec.getLieu(),"NewsTopLine2" );
        
        
        createLineSeparator();
        
       
        
        
     
        //supprimer button
        Label lSupprimer = new Label(" ");
        lSupprimer.setUIID("NewsTopLine");
        Style supprmierStyle = new Style(lSupprimer.getUnselectedStyle());
        supprmierStyle.setFgColor(0xf21f1f);
        
        FontImage suprrimerImage = FontImage.createMaterial(FontImage.MATERIAL_DELETE, supprmierStyle);
        lSupprimer.setIcon(suprrimerImage);
        lSupprimer.setTextPosition(RIGHT);
        
        //click delete icon
        lSupprimer.addPointerPressedListener(l -> {
            
            Dialog dig = new Dialog("Suppression");
            
            if(dig.show("Suppression","Vous voulez supprimer cet evenement ?","Annuler","Oui")) {
                dig.dispose();
            }
            else {
                dig.dispose();
                 }
                //n3ayto l suuprimer men service Reclamation
                if(EvenementService.getInstance().deleteEvenement(rec.getId_evenement())) {
                    new ListeEvenement(res).show();
                    refreshTheme(); 
                }
           
                

                            refreshTheme(); 

                
                
                
                
                
                
                
        });
        
        //Update icon 
        Label lModifier = new Label(" ");
        lModifier.setUIID("NewsTopLine");
        Style modifierStyle = new Style(lModifier.getUnselectedStyle());
        modifierStyle.setFgColor(0xf7ad02);
        
        FontImage mFontImage = FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, modifierStyle);
        lModifier.setIcon(mFontImage);
        lModifier.setTextPosition(LEFT);
        
        
        lModifier.addPointerPressedListener(l -> {
         //   System.out.println("hello update");
            new ModifierEvenement(res,rec).show();
        });
        
        
       
        
        //Details icon 
Label lDetails = new Label(" ");
lDetails.setUIID("NewsTopLine");
Style detailsStyle = new Style(lDetails.getUnselectedStyle());
detailsStyle.setFgColor(0x0000ff);
FontImage dFontImage = FontImage.createMaterial(FontImage.MATERIAL_INFO, detailsStyle);
lDetails.setIcon(dFontImage);
lDetails.setTextPosition(LEFT);

lDetails.addPointerPressedListener(l -> {
    new DetailsEvenement(res, rec).show();
});

     cnt.add(BorderLayout.CENTER,BoxLayout.encloseY(
                                
                
                BoxLayout.encloseX(nomevtxt),
                BoxLayout.encloseX(descriptiontxt),
                BoxLayout.encloseX(nom_socTxt),
                BoxLayout.encloseX(dateDebuttxt),
                BoxLayout.encloseX(dateFintxt),
                BoxLayout.encloseX(lieuTxt,lModifier,lSupprimer,lDetails)));
        
        
        
        add(cnt);
        
    }

    public Component createLineSeparator() {
        Label separator = new Label("", "WhiteSeparator");
        separator.setShowEvenIfBlank(true);
        return separator;
    }
    

    }
    
    
    
    
    
    
    
    
    
    

