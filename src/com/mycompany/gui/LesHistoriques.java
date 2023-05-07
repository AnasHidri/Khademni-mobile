/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;

import com.mycompany.services.HistoriqueService;
import com.mycompany.services.PanierService;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.uikit.materialscreens.SideMenuBaseForm;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.materialscreens.ProfileForm;
import com.mycompany.entities.Historique;
import java.util.ArrayList;

/**
 *
 * @author mikea
 */
public class LesHistoriques extends SideMenuBaseForm {

     @Override
    protected void showOtherForm(Resources res) {
        new ProfileForm(res).show();
    }
    
  public LesHistoriques(Resources res){
          super(BoxLayout.y());
    ArrayList<Historique> historiques = HistoriqueService.getInstance().getAllHistorique();
  
    Form myForm = new Form("MonPanier", new TableLayout(historiques.size() + 1, 2));
    
    // Add header row
  
    myForm.add(new Label("Description"));
     myForm.add(new Label("Date action"));

    // Add data rows
    for (Historique hist : historiques) {
        myForm.add(new Label(hist.getAction()));
        myForm.add(new Label(String.valueOf(hist.getDate_action())));
        

    }

    // Add myForm to this container (MonPanier) or another container
   add(myForm);
}

    
}
