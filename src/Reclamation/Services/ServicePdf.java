/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reclamation.Services;

import Reclamation.Entities.Garantie;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import Reclamation.Entities.Reclamation;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

/**
 *
 * @author admin
 */
public class ServicePdf {
    
       public void liste_reclamationPDF() throws FileNotFoundException, DocumentException {

        ServiceReclamation so = new ServiceReclamation();
        String message = "                       Voici la liste des Reclamations \n\n";
      
        
        String file_name = "src/PDF/liste_reclamations.pdf";
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(file_name));
        document.open();
        Paragraph para = new Paragraph(message);
        document.add(para);
         List<Reclamation> reclamation = so.afficherRecList();
        PdfPTable table = new PdfPTable(4);

        PdfPCell cl = new PdfPCell(new Phrase("ID"));
        table.addCell(cl);
        PdfPCell cl1 = new PdfPCell(new Phrase("Description"));
        table.addCell(cl1);
        PdfPCell cl2 = new PdfPCell(new Phrase("Theme"));
        table.addCell(cl2);
        PdfPCell cl3 = new PdfPCell(new Phrase("Client"));
        table.addCell(cl3);
        
        
        
        table.setHeaderRows(1);
        document.add(table);

        int i = 0;
        for (i = 0; i < reclamation.size(); i++) {
            table.addCell(""+ reclamation.get(i).getId());
            table.addCell("" + reclamation.get(i).getDescription());
            table.addCell("" + reclamation.get(i).getTheme());

            table.addCell(""+reclamation.get(i).getUser_id().getIduser());
         


                    

        }
        document.add(table);

        document.close();

    }
    
public void liste_garantiePDF() throws FileNotFoundException, DocumentException {

        ServiceGarantie so = new ServiceGarantie();
        String message = "                       Voici la liste des Garanties \n\n";
      
        
        String file_name = "src/PDF/liste_garanties.pdf";
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(file_name));
        document.open();
        Paragraph para = new Paragraph(message);
        document.add(para);
         List<Garantie> gar = so.afficherGarList();
        PdfPTable table = new PdfPTable(4);

        PdfPCell cl = new PdfPCell(new Phrase("ID"));
        table.addCell(cl);
        PdfPCell cl1 = new PdfPCell(new Phrase("Description"));
        table.addCell(cl1);
        PdfPCell cl2 = new PdfPCell(new Phrase("Produi"));
        table.addCell(cl2);
        PdfPCell cl3 = new PdfPCell(new Phrase("Client"));
        table.addCell(cl3);
        
        
        
        table.setHeaderRows(1);
        document.add(table);

        int i = 0;
        for (i = 0; i < gar.size(); i++) {
            table.addCell(""+ gar.get(i).getId());
            table.addCell("" + gar.get(i).getDescription());
             table.addCell("" + gar.get(i).getId_produit().getId_produit());
            table.addCell(""+gar.get(i).getUser_id().getIduser());
                  

        }
        document.add(table);

        document.close();

    }
}
