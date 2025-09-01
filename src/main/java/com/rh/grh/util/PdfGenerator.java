package com.rh.grh.util;

import com.lowagie.text.Font;
import com.rh.grh.entity.Employee;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;

import java.awt.*;
import java.io.IOException;
import java.util.List;

public class PdfGenerator {

    // Générer un PDF de la liste des employés et l'envoyer via la réponse HTTP
    public static void generateEmployeePdf(List<Employee> employeeList, HttpServletResponse response) throws IOException {
        Document document = new Document(PageSize.A4);
        try {
            PdfWriter.getInstance(document, response.getOutputStream());

            document.open();

            // Titre du PDF
            Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            fontTitle.setSize(18);
            fontTitle.setColor(Color.BLUE);

            Paragraph title = new Paragraph("Liste des Employés", fontTitle);
            title.setAlignment(Paragraph.ALIGN_CENTER);
            title.setSpacingAfter(20);
            document.add(title);

            // Table avec 7 colonnes
            PdfPTable table = new PdfPTable(7);
            table.setWidthPercentage(100f);
            table.setSpacingBefore(10f);

            // En-tête de table
            Font fontHeader = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            fontHeader.setColor(Color.WHITE);

            String[] headers = {"ID", "Nom", "Prénom", "Email", "Poste", "Date d'embauche", "Département"};

            for (String header : headers) {
                com.lowagie.text.pdf.PdfPCell cell = new com.lowagie.text.pdf.PdfPCell(new Phrase(header, fontHeader));
                cell.setBackgroundColor(Color.GRAY);
                cell.setPadding(5);
                table.addCell(cell);
            }

            // Données des employés
            for (Employee emp : employeeList) {
                table.addCell(emp.getId() != null ? emp.getId().toString() : "");
                table.addCell(emp.getNom() != null ? emp.getNom() : "");
                table.addCell(emp.getPrenom() != null ? emp.getPrenom() : "");
                table.addCell(emp.getEmail() != null ? emp.getEmail() : "");
                table.addCell(emp.getPoste() != null ? emp.getPoste() : "");
                table.addCell(emp.getDateEmbauche() != null ? emp.getDateEmbauche().toString() : "");
                table.addCell(emp.getDepartment() != null ? emp.getDepartment() : "");
            }

            document.add(table);
            document.close();
        } catch (DocumentException e) {
            throw new IOException("Erreur lors de la génération du PDF", e);
        }
    }
}
