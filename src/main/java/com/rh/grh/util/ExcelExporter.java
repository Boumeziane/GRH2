package com.rh.grh.util;

import com.rh.grh.entity.Employee;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class ExcelExporter {

    private XSSFWorkbook workbook;
    private Sheet sheet;
    private List<Employee> employeeList;

    public ExcelExporter(List<Employee> employeeList) {
        this.employeeList = employeeList;
        workbook = new XSSFWorkbook();
    }

    // Créer l'en-tête du fichier Excel
    private void writeHeaderLine() {
        sheet = workbook.createSheet("Employees");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(14);
        style.setFont(font);

        createCell(row, 0, "ID", style);
        createCell(row, 1, "Nom", style);
        createCell(row, 2, "Prénom", style);
        createCell(row, 3, "Email", style);
        createCell(row, 4, "Poste", style);
        createCell(row, 5, "Date d'embauche", style);
        createCell(row, 6, "Département", style);
    }

    // Créer une cellule
    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);

        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Long) {
            cell.setCellValue((Long) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else {
            cell.setCellValue(value != null ? value.toString() : "");
        }

        cell.setCellStyle(style);
    }

    // Remplir les données dans le fichier Excel
    private void writeDataLines() {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(12);
        style.setFont(font);

        for (Employee emp : employeeList) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, emp.getId(), style);
            createCell(row, columnCount++, emp.getNom(), style);
            createCell(row, columnCount++, emp.getPrenom(), style);
            createCell(row, columnCount++, emp.getEmail(), style);
            createCell(row, columnCount++, emp.getPoste(), style);
            createCell(row, columnCount++, emp.getDateEmbauche() != null ? emp.getDateEmbauche().toString() : "", style);
            createCell(row, columnCount++, emp.getDepartment(), style);
        }
    }

    // Exporter le fichier Excel via la réponse HTTP
    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}
