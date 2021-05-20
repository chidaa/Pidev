/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.mycompany.myapp.entities.Reclamation;
import com.codename1.components.SpanLabel;
import com.codename1.messaging.Message;
import static com.codename1.push.PushContent.setTitle;
import com.codename1.ui.Button;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.myapp.services.ServiceReclamation;


/**
 *
 * @author admin
 */
public class DetailsResForm extends Form {

    Form current;

    public DetailsResForm() {

    }

    public DetailsResForm(Form previous, Reclamation e) {
   

        LocalNotification n = new LocalNotification();
        n.setId("demo-notification");
        n.setAlertBody("It's time to take a break and look at me");
        n.setAlertTitle("Break Time!");
        n.setAlertSound("/notification_sound_bells.mp3"); //file name must begin with notification_sound


        Display.getInstance().scheduleLocalNotification(
                n,
                System.currentTimeMillis() + 10 * 1000, // fire date/time
                LocalNotification.REPEAT_MINUTE  // Whether to repeat and what frequency
        );
      


//Message m = new Message("Body of message");
//
//Display.getInstance().sendMessage(new String[] {"aicha.khlif@esprit.tn"}, "hello", m);
        
        setTitle("Details de la Reclamation ");
        
        SpanLabel lbnom = new SpanLabel();
        Label lbemail = new Label();
        Label lbpays = new Label();
        Label lbm = new Label();
        Label lbi = new Label();
        Label lbc = new Label();


        System.out.println("\n");
        Button btnSuppRes = new Button("Supprimer cet Reclamation");

        btnSuppRes.addActionListener(p -> ServiceReclamation.getInstance().delete(e));

        addAll(lbnom, lbemail, lbpays, lbm ,btnSuppRes);

        lbnom.setText("id :" + e.getId());
        lbemail.setText("description :" + e.getDescription());
        lbpays.setText("thme :" + e.getTheme());
        lbm.setText("user :" + e.getUser_id().getIduser());
        

        show();

        getToolbar().addCommandToLeftBar("back", null, ev -> {
            previous.show();
        });

    }

    DetailsResForm(Form current, ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    
}
