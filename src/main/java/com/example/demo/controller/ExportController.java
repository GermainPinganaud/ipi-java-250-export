package com.example.demo.controller;

import com.example.demo.entity.Client;
import com.example.demo.service.ClientService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Controlleur pour réaliser les exports.
 */
@Controller
@RequestMapping("/")
public class ExportController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/clients/csv")
    public void clientsCSV(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"clients.csv\"");
        PrintWriter writer = response.getWriter();
        List<Client> allClients = clientService.findAllClients();
        writer.println( "Nom;Prénom;Date de naissance;Age" );
        LocalDate now = LocalDate.now();
        for (Client client : allClients) {
            writer.println(
                    client.getNom() + ";" +
                    client.getPrenom() + ";" +
                    client.getBirthdate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ";" +
                    Period.between(client.getBirthdate(), now).getYears()
            );
        }
    }
    @GetMapping("/clients/xlsx")
    public void clientsXLSX(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/xlsx");
        response.setHeader("Content-Disposition", "attachment; filename=\"clients.xlsx\"");
        List<Client> allClients = clientService.findAllClients();
        LocalDate now = LocalDate.now();
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Clients");
        Row headerRow = sheet.createRow(0);
        Cell cellPrenom = headerRow.createCell(0);
        cellPrenom.setCellValue("Prénom");
        workbook.write(response.getOutputStream());
        workbook.close();


    }
}
