/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.arqsoft.spreadsheet.userinterface;

import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Spreadsheet;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.CircularDependencyException;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.ContentException;
import edu.upc.etsetb.arqsoft.spreadsheet.storage.S2VLoader;
import edu.upc.etsetb.arqsoft.spreadsheet.storage.S2VStore;
import edu.upc.etsetb.arqsoft.spreadsheet.usecases.marker.ReadingSpreadSheetException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author esthe
 */
public class LoadSpreadsheet extends Command{
    @Override
    public void execute(Spreadsheet spreadsheet, S2VLoader loader, S2VStore store) {
        Scanner scanner = new Scanner(System.in);

        // Solicitar nombre del archivo para cargar la hoja de cálculo
        System.out.print("Ingrese el nombre del archivo para cargar la hoja de cálculo: ");
        String filename = scanner.nextLine();

        // Cargar la hoja de cálculo desde el archivo especificado
        try {
            loader = new S2VLoader(filename);
            spreadsheet = loader.loadSpreadsheet();
            System.out.println("Hoja de cálculo cargada correctamente desde " + filename);
        } catch (ReadingSpreadSheetException e) {
            System.out.println("Error al cargar la hoja de cálculo: " + e.getMessage());
        }

        // Mostrar la hoja de cálculo cargada
        if (spreadsheet != null) {
            spreadsheet.display();
        }
    }
}
