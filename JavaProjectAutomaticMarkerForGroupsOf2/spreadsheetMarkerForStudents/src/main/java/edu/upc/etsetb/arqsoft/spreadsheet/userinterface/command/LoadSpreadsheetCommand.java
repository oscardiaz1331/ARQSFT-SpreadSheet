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
import edu.upc.etsetb.arqsoft.spreadsheet.userinterface.edu.upc.etsetb.arqsoft.spreadsheet.userinterface.entities.ILoadSpreadSheet;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author esthe
 */
public class LoadSpreadsheetCommand extends Command implements ILoadSpreadSheet{

    @Override
    public Spreadsheet Load() {
        Scanner scanner = new Scanner(System.in);

        //TODO de abajo
        System.out.print("Ingrese el nombre del archivo para cargar la hoja de cálculo: ");
        String filename = scanner.nextLine();
        Spreadsheet spreadsheet = new Spreadsheet();
        //TODO
        //Ingresar un while mientras vaya mal -> repetir-> seguir pidiendo filename 
        //si va bien (sale del while) -> lo que esta a continuacion
        try {
            S2VLoader loader = new S2VLoader(filename);
            spreadsheet = loader.loadSpreadsheet();
            System.out.println("Hoja de cálculo cargada correctamente desde " + filename);
            
        } catch (ReadingSpreadSheetException e) {
            System.out.println("Error al cargar la hoja de cálculo: " + e.getMessage());
        }
        return spreadsheet;
    }
}
