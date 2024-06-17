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
import edu.upc.etsetb.arqsoft.spreadsheet.userinterface.edu.upc.etsetb.arqsoft.spreadsheet.userinterface.entities.IReadFromFile;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author esthe
 */
public class ReadFromFileCommand extends Command implements IReadFromFile{
    private String filePath;
    public ReadFromFileCommand(String filePath){
        this.filePath = filePath;
    }
    
    //TODO eliminate after copy
//    @Override
//    public void execute(Spreadsheet spreadsheet, S2VLoader loader, S2VStore store) {
//        try {
//            loader = new S2VLoader(filePath);
//            spreadsheet = loader.loadSpreadsheet();
//            System.out.println("Hoja de cálculo cargada exitosamente desde el archivo: " + filePath);
//        } catch (ReadingSpreadSheetException ex) {
//            Logger.getLogger(ReadFromFile.class.getName()).log(Level.SEVERE, null, ex);
//            System.out.println("Error al cargar la hoja de cálculo desde el archivo: " + filePath);
//        }
//        spreadsheet.display();
//    }

    @Override
    public void readCommand(String filename) {
        //TODO: creo que este es el mas complejo de todos, asique dejalo para el final y haz los otros antes
        Scanner scanner = new Scanner(System.in);
        Spreadsheet spreadsheet = new Spreadsheet();

        while (true) {
            // Solicitar nombre del archivo para cargar la hoja de cálculo
            System.out.print("Enter the file name to load the spreadsheet: ");
            filename = scanner.nextLine();

            try {
                // Cargar la hoja de cálculo desde el archivo especificado
                S2VLoader loader = new S2VLoader(filename);
                spreadsheet = loader.loadSpreadsheet();
                System.out.println("Spreadsheet loaded successfully from " + filename);
                break;
            } catch (ReadingSpreadSheetException e) {
                System.out.println("Error loading spreadsheet from file: " + e.getMessage());
            }
        }

        // Mostrar la hoja de cálculo cargada
        spreadsheet.display();
    }
}
