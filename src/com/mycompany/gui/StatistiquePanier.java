package com.mycompany.gui;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.views.PieChart;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.materialscreens.ProfileForm;
import com.codename1.uikit.materialscreens.SideMenuBaseForm;
import com.mycompany.entities.Ligne_commande;
import com.mycompany.services.PanierService;
import java.util.ArrayList;


public class StatistiquePanier extends Form {
    
   public StatistiquePanier(Resources res) {

    super("My Chart");
    
    ArrayList<Ligne_commande> lignes = PanierService.getInstance().StatistiqueCommande();
       System.out.println("ligneeee : "+lignes);
    Container chartContainer = new Container();
    add(chartContainer);

    CategorySeries series = new CategorySeries("My Data");
    DefaultRenderer renderer = new DefaultRenderer();
    renderer.setLabelsTextSize(30);
    renderer.setLegendTextSize(30);
    renderer.setMargins(new int[]{20, 30, 15, 0});
    renderer.setZoomButtonsVisible(true);

    for (int i = 0; i < lignes.size(); i++) {
        String titre = lignes.get(i).getTitre();
        int count = lignes.get(i).getCount();
        series.add(titre, count);
        SimpleSeriesRenderer r = new SimpleSeriesRenderer();
        r.setColor(getColor(i));
        renderer.addSeriesRenderer(r);
    }

    PieChart chart = new PieChart(series, renderer);
    chartContainer.add(new ChartComponent(chart));

}


private int[] colors = new int[]{0xff2ecc71, 0xffe74c3c, 0xff3498db, 0xff9b59b6, 0xfff1c40f};

private int getColor(int index) {
    return colors[index % colors.length];
}


  
    protected void showOtherForm(Resources res) {
        new ProfileForm(res).show();
    }
    
}
