/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.arqsoft.spreadsheet.storage;

import edu.upc.etsetb.arqsoft.spreadsheet.auxiliar.ColumnManager;
import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Cell;
import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Spreadsheet;
import edu.upc.etsetb.arqsoft.spreadsheet.usecases.marker.SavingSpreadSheetException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author oscar
 */
public class S2VStore implements Store {
    private FileWriter writer;
    private Spreadsheet spreadsheet;
    private List<Cell> cells;
    private ContentVisitor contentVisitor;
    
    public S2VStore(String filename, Spreadsheet spreadsheet) throws SavingSpreadSheetException{
        try {
            this.writer = new FileWriter(filename, true);
        } catch (IOException ex) {
            throw new SavingSpreadSheetException("Error creating the file\n"+ex.getMessage());
        }
        this.spreadsheet = spreadsheet;
        this.contentVisitor = new ContentVisitor();
    }
    
    @Override
    public void storeSpreadsheet() throws SavingSpreadSheetException{
        String line = "";
        try {
            //Sort cells
            this.cells = this.spreadsheet.getCells();
            Collections.sort(this.cells);
            Iterator<Cell> it = this.cells.iterator();
            int anteriorRow = 0, anteriorCol = 0;
            while(it.hasNext()){
                anteriorCol++;

                Cell cell = it.next();

                //Repasar bien el orden de esta parte, tanto de la row como de la col
                while(cell.getRow() > anteriorRow){
                    anteriorRow++;
                    while(line.endsWith(";")){
                        line = line.substring(0, line.length()-1);
                    }
                    this.writer.write(line+"\n");
                    line = "";
                    anteriorCol = 0;
                }
                while(ColumnManager.columnToNumber(cell.getCol()) > anteriorCol){
                    line += ";";
                    anteriorCol++;
                }
                line += cell.getContentAsString() + ";";
            }

                this.writer.write(line);
        } catch (IOException ex) {
            throw new SavingSpreadSheetException(ex.getMessage());
        }
    }
}
