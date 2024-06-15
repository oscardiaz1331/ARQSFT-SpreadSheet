/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.arqsoft.spreadsheet.userinterface;

import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Spreadsheet;
import edu.upc.etsetb.arqsoft.spreadsheet.storage.S2VLoader;
import edu.upc.etsetb.arqsoft.spreadsheet.storage.S2VStore;
import edu.upc.etsetb.arqsoft.spreadsheet.usecases.marker.ReadingSpreadSheetException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author esthe
 */
public class LoadSpreadsheet extends Command{
    @Override
    public void execute(Spreadsheet spreadsheet, S2VLoader loader, S2VStore store) {
        try {
            loader.loadSpreadsheet();
        } catch (ReadingSpreadSheetException ex) {
            Logger.getLogger(LoadSpreadsheet.class.getName()).log(Level.SEVERE, null, ex);
        }
        spreadsheet.display();
    }
}
