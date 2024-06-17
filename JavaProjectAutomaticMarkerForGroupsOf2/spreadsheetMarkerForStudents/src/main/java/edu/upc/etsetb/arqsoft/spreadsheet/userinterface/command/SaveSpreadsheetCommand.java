/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.arqsoft.spreadsheet.userinterface.command;

import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Spreadsheet;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.CircularDependencyException;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.ContentException;
import edu.upc.etsetb.arqsoft.spreadsheet.exceptions.TokenWrittenIncorrectlyException;
import edu.upc.etsetb.arqsoft.spreadsheet.exceptions.WrongSyntaxException;
import edu.upc.etsetb.arqsoft.spreadsheet.storage.S2VLoader;
import edu.upc.etsetb.arqsoft.spreadsheet.storage.S2VStore;
import edu.upc.etsetb.arqsoft.spreadsheet.usecases.marker.ReadingSpreadSheetException;
import edu.upc.etsetb.arqsoft.spreadsheet.usecases.marker.SavingSpreadSheetException;
import edu.upc.etsetb.arqsoft.spreadsheet.userinterface.edu.upc.etsetb.arqsoft.spreadsheet.userinterface.entities.ISaveSpreadSheet;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author esthe
 */
public class SaveSpreadsheetCommand extends Command implements ISaveSpreadSheet{

    @Override
    public void save(String filename, Spreadsheet spreadsheet) {
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            // Solicitar nombre del archivo para guardar la hoja de cálculo
            System.out.print("Enter the file name to save the spreadsheet: ");
            filename = scanner.nextLine();

            try {
                S2VStore store = new S2VStore(filename, spreadsheet);
                store.storeSpreadsheet();
                System.out.println("Spreadsheet saved successfully in " + filename);
                break; // Salir del bucle si se guarda correctamente
            } catch (SavingSpreadSheetException e) {
                System.out.println("Error saving spreadsheet: " + e.getMessage());
                System.out.println("Please try again.");
            }
        }
    }
}
