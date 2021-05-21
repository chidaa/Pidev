/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.io.Log;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Stroke;
import com.codename1.ui.Tabs;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.gui.AddResForm;
import com.mycompany.myapp.gui.ShowlistResForm;
import com.mycompany.myapp.services.ServiceReclamation;
import guizi.articleform;

import java.util.List;

/**
 *
 * @author bhk
 */
public class HomeForm extends Form {

    Form current;
    private Resources theme;
    private Resources theme_2;
    
        public Component createLineSeparator() {
        Label separator = new Label("", "WhiteSeparator");
        separator.setShowEvenIfBlank(true);
        return separator;
    }
    
    public Component createLineSeparator(int color) {
        Label separator = new Label("", "WhiteSeparator");
        separator.getUnselectedStyle().setBgColor(color);
        separator.getUnselectedStyle().setBgTransparency(255);
        separator.setShowEvenIfBlank(true);
        return separator;
    }

    public HomeForm() {
        
                 
        current = this; //Récupération de l'interface(Form) en cours
        theme_2 = UIManager.initFirstTheme("/theme_2");

        theme = UIManager.initFirstTheme("/theme_1");
        
      Toolbar tb =new Toolbar();
        setToolbar(tb);
                  setLayout(BoxLayout.y());
Container c = new Container();
             ImageViewer img1 = new ImageViewer(theme.getImage("taswira.png").scaled(880,850));
             
             c.addAll(img1);
             
      //  getTitleArea().setUIID("container");
             setTitle("PAGE D'ACCEUIL");
             getContentPane().setScrollVisible(false);
             
                     Image img = theme.getImage("logo.png");
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        
               tb.addComponentToSideMenu(LayeredLayout.encloseIn(
                sl)); 
               
   tb.addMaterialCommandToSideMenu("Reclamation", FontImage.MATERIAL_UPDATE, e -> new elyesform().show());
   Form hi = new prodcats.gui.HomeForm();
   tb.addMaterialCommandToSideMenu("Livreurs", FontImage.MATERIAL_UPDATE, e ->  hi.show());
  tb.addMaterialCommandToSideMenu("Blog", FontImage.MATERIAL_UPDATE, e ->  new articleform(theme_2).show());
    tb.addMaterialCommandToSideMenu("Disponibilité", FontImage.MATERIAL_UPDATE, e ->  new ListeDispo(current).show());
 
   
           this.add(c);

    }
    

}
