/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.arqsoft.spreadsheet.userinterface;

import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Spreadsheet;
import edu.upc.etsetb.arqsoft.spreadsheet.storage.S2VLoader;
import edu.upc.etsetb.arqsoft.spreadsheet.storage.S2VStore;
import edu.upc.etsetb.arqsoft.spreadsheet.usecases.marker.SavingSpreadSheetException;
import java.util.Scanner;


/**
 *
 * @author esthe
 */
public class CreateSpreadsheet extends Command{
    @Override
    public void execute(Spreadsheet spreadsheet, S2VLoader loader, S2VStore store) {
        Scanner scanner = new Scanner(System.in);

        // Solicitar nombre del archivo para guardar la nueva hoja de cálculo
        System.out.print("Ingrese el nombre del archivo para guardar la nueva hoja de cálculo: ");
        String filename = scanner.nextLine();

        // Crear una nueva hoja de cálculo vacía
        Spreadsheet newSpreadsheet = new Spreadsheet();
        
        // Guardar la nueva hoja de cálculo en el archivo especificado
        try {
            store = new S2VStore(filename, newSpreadsheet);
            store.storeSpreadsheet();
            System.out.println("Nueva hoja de cálculo creada y guardada en " + filename);
        } catch (SavingSpreadSheetException e) {
            System.out.println("Error al guardar la hoja de cálculo: " + e.getMessage());
        }

        // Mostrar la hoja de cálculo vacía
        newSpreadsheet.display();
    }
}
