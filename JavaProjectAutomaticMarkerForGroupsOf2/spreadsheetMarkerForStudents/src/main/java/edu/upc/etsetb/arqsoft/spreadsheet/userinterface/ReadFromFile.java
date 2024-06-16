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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author esthe
 */
public class ReadFromFile extends Command{
    private String filePath;
    public ReadFromFile(String filePath){
        this.filePath = filePath;
    }
    @Override
    public void execute(Spreadsheet spreadsheet, S2VLoader loader, S2VStore store) {
        try {
            loader = new S2VLoader(filePath);
            spreadsheet = loader.loadSpreadsheet();
            System.out.println("Hoja de cálculo cargada exitosamente desde el archivo: " + filePath);
        } catch (ReadingSpreadSheetException ex) {
            Logger.getLogger(ReadFromFile.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error al cargar la hoja de cálculo desde el archivo: " + filePath);
        }
        spreadsheet.display();
    }
}
