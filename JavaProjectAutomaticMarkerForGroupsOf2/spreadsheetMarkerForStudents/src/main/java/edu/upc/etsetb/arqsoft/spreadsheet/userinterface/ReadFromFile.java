/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.arqsoft.spreadsheet.userinterface;

import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Spreadsheet;
import edu.upc.etsetb.arqsoft.spreadsheet.storage.S2VLoader;
import edu.upc.etsetb.arqsoft.spreadsheet.storage.S2VStore;

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
        System.out.println("Reading commands from file: " + filePath);
    }
}
