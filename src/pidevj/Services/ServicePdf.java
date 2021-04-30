/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevj.Services;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import pidevj.Entities.disponibilite;
import pidevj.Entities.societelivraison;

/**
 *
 * @author Abdelalim Mahfoudh
 */
public class ServicePdf {
    
     public void liste_disponibilitePDF() throws FileNotFoundException, DocumentException {

        ServiceDisponibilite sd = new ServiceDisponibilite();
        String message = "Voici la liste des disponibilites \n\n";

        String file_name = "src/PDF/liste_disponibilite.pdf";
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(file_name));
        document.open();
        Paragraph para = new Paragraph(message);
        document.add(para);
         List<disponibilite> disponibilite = sd.afficherDisponibiliteList();
        PdfPTable table = new PdfPTable(4);

        PdfPCell cl = new PdfPCell(new Phrase("id"));
        table.addCell(cl);
        PdfPCell cl1 = new PdfPCell(new Phrase("nom_societe"));
        table.addCell(cl1);
        PdfPCell cl2 = new PdfPCell(new Phrase("jours"));
        table.addCell(cl2);
        PdfPCell cl3 = new PdfPCell(new Phrase("region"));
        table.addCell(cl3);
        
        
        
        table.setHeaderRows(1);
        document.add(table);

        int i = 0;
        for (i = 0; i < disponibilite.size(); i++) {
            table.addCell("" + disponibilite.get(i).getId());
            table.addCell(disponibilite.get(i).getSocietes_id().getNom());
            table.addCell("" + disponibilite.get(i).getJours());
            table.addCell("" + disponibilite.get(i).getRegion());

            
          


                    

        }
        document.add(table);

        document.close();

    }
     
      public void liste_societePDF() throws FileNotFoundException, DocumentException {

        ServiceSocietelivraison ss = new ServiceSocietelivraison();
        String message = "Voici la liste des societes \n\n";

        String file_name = "src/PDF/liste_societe.pdf";
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(file_name));
        document.open();
        Paragraph para = new Paragraph(message);
        document.add(para);
         List<societelivraison> societelivraison = ss.afficherSocieteList();
        PdfPTable table = new PdfPTable(4);

        PdfPCell cl = new PdfPCell(new Phrase("Id"));
        table.addCell(cl);
        PdfPCell cl1 = new PdfPCell(new Phrase("Nom"));
        table.addCell(cl1);
        PdfPCell cl2 = new PdfPCell(new Phrase("Adresse"));
        table.addCell(cl2);
        PdfPCell cl3 = new PdfPCell(new Phrase("Contact"));
        table.addCell(cl3);
        
        
        
        table.setHeaderRows(1);
        document.add(table);

        int i = 0;
        for (i = 0; i < societelivraison.size(); i++) {
            table.addCell("" + societelivraison.get(i).getId());
            table.addCell("" + societelivraison.get(i).getNom());
            table.addCell("" + societelivraison.get(i).getAdresse());
            table.addCell("" + societelivraison.get(i).getContact());

            //table.addCell(societelivraison.get(i).getSocietes_id().getNom());
          


                    

        }
        document.add(table);

        document.close();

    }
    
}
