/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guizi;

import entitieszi.article;

import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.io.Properties;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.list.ContainerList;
import com.codename1.ui.list.ListModel;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.gui.ListeDispo;
import com.mycompany.myapp.gui.ShowlistResForm;
import com.mycompany.myapp.gui.elyesform;
import com.teknikindustries.bulksms.SMS;
import mobilezi.mainzi;
import serviceszi.serviceart;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
//import javax.mail.Session;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;

/**
 *
 * @author BENJOU
 */
public class articleform extends Form {
 private ContainerList listContainer;
 private TextField chercher;
 private Button chercherbutton;
 Resources theme;
 
 Form current;
    //private Resources theme;
     private Resources theme_2;
    public articleform(Resources theme) {
        this.theme=theme;
        this.setLayout(BoxLayout.y());
                    


        addGui();
        addAction();
        actionPerformed();
     
      
    }

    private void addGui() {
        
        current = this; //Récupération de l'interface(Form) en cours
        theme_2 = UIManager.initFirstTheme("/theme_2");

        theme = UIManager.initFirstTheme("/theme_1");
        
      Toolbar tb =new Toolbar();
        setToolbar(tb);
      //  getTitleArea().setUIID("container");
             setTitle("Home");
             getContentPane().setScrollVisible(false);
             
                     Image img1 = theme.getImage("logo.png");
        if(img1.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img1 = img1.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img1);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        
               tb.addComponentToSideMenu(LayeredLayout.encloseIn(
                sl)); 
   tb.addMaterialCommandToSideMenu("Reclamation", FontImage.MATERIAL_UPDATE, e -> new elyesform().show());
   Form hi = new prodcats.gui.HomeForm();
   tb.addMaterialCommandToSideMenu("Livreurs", FontImage.MATERIAL_UPDATE, e ->  hi.show());
  tb.addMaterialCommandToSideMenu("Blog", FontImage.MATERIAL_UPDATE, e ->  new articleform(theme_2).show());
    tb.addMaterialCommandToSideMenu("Disponibilité", FontImage.MATERIAL_UPDATE, e ->  new ListeDispo(current).show());
        
        
        
        
        
        
        
       chercher = new TextField("","chercher un article");
        chercherbutton = new Button("chercher");
        Container chercherContianer = new Container();
        chercherContianer.setLayout(BoxLayout.y());
        chercherContianer.addAll(chercher,chercherbutton);
        this.add(chercherContianer);     
        listContainer = new ContainerList();
        listContainer.setLayout(BoxLayout.y());
        listContainer.setScrollableY(false);
        listContainer.setScrollVisible(true);
serviceart ms = new serviceart();
     ArrayList<article> allarticles = ms.getart();
        for (article m : allarticles){
       
        EncodedImage placeHolder = EncodedImage.createFromImage(prodcats.MyApplication.theme2.getImage("placeholder-image.png"), false);
            String url = "http://127.0.0.1:8000/Uploads/Image/" + m.getImage();
            Image img = URLImage.createToStorage(placeHolder, url, url);
            ImageViewer image = new ImageViewer(img);
       Container c = new Container();
       c.setLayout(BoxLayout.y());
      c.add(image);
       c.add(new SpanLabel("Sujet : " + m.getSujet()));
       c.add(new SpanLabel("Description : " + m.getDescription()));
     
        Button contact = new Button("contacter");
       c.add(contact);
       contact.addActionListener((evt)->{
           
      Message msg = new Message("Body of message");
      Display.getInstance().sendMessage(new String[]{""}, "Subject of message", msg);
       });
       listContainer.add(c);
       
   
           SMS sms=new SMS();
                 String nt= "+21654428182";
                 sms.SendSMS("zika02","Ziedkamoun123", "Votre publication a été ajoute avec succes", nt ,"https://bulksms.vsms.net/eapi/submission/send_sms/2/2.0");
   }
   this.add(listContainer);
  
   }

    private void addAction() {
     
     chercherbutton.addActionListener((evt)->{
         Form f= new  chercherarticle(theme,chercher.getText());
         f.show();
     });
             }
    
    public void actionPerformed() {
          // SMS sms=new SMS();
                 String nt= "+21655568071";
              //   sms.SendSMS("zika999","Ziedkamoun123", "Nouveaux articles disponibles", nt ,"https://bulksms.vsms.net/eapi/submission/send_sms/2/2.0");
       }
       
 
}
