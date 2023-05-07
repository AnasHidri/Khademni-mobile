/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;

import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.materialscreens.LoginForm;
import com.mycompany.entities.User;

/**
 *
 * @author ASUS
 */
public abstract class SideMenuBaseFormUsers  extends Form {

    public SideMenuBaseFormUsers(String title, Layout contentPaneLayout) {
        super(title, contentPaneLayout);
    }

    public SideMenuBaseFormUsers(String title) {
        super(title);
    }

    public SideMenuBaseFormUsers() {
    }

    public SideMenuBaseFormUsers(Layout contentPaneLayout) {
        super(contentPaneLayout);
    }
    
    public void setupSideMenu(Resources res) {
        String nomCurrent=User.getCurrent_User().getNom();
String prenomCurrent=User.getCurrent_User().getPrenom();

        Image profilePic = res.getImage("img3.png");
        Image mask = res.getImage("round-mask.png");
        mask = mask.scaledHeight(mask.getHeight() / 4 * 3);
        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        Label profilePicLabel = new Label(nomCurrent+" "+prenomCurrent, profilePic, "SideMenuTitle");
        profilePicLabel.setMask(mask.createMask());

        Container sidemenuTop = BorderLayout.center(profilePicLabel);
        sidemenuTop.setUIID("SidemenuTop");
        
        getToolbar().addComponentToSideMenu(sidemenuTop);
        getToolbar().addMaterialCommandToSideMenu("  Acceuil", FontImage.MATERIAL_DASHBOARD,  e -> showOtherForm(res));
        getToolbar().addMaterialCommandToSideMenu("  Formations", FontImage.MATERIAL_EXIT_TO_APP,  e -> new ListUsers(res).show());
        getToolbar().addMaterialCommandToSideMenu("  Offres", FontImage.MATERIAL_TRENDING_UP,  e -> showOtherForm(res));
        getToolbar().addMaterialCommandToSideMenu("  Evenements", FontImage.MATERIAL_ACCESS_TIME,  e -> new ListeEvenement(res).show());
           getToolbar().addMaterialCommandToSideMenu("  Panier", FontImage.MATERIAL_ACCESS_TIME,  e -> new MonPanier(res).show());
        getToolbar().addMaterialCommandToSideMenu("  Logout", FontImage.MATERIAL_EXIT_TO_APP,  e -> new LoginForm(res).show());

    }
    
    protected abstract void showOtherForm(Resources res);
}
