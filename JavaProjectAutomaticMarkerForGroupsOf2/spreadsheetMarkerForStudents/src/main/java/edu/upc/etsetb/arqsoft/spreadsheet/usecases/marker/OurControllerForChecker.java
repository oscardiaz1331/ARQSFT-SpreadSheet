/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.arqsoft.spreadsheet.usecases.marker;

import edu.upc.etsetb.arqsoft.spreadsheet.auxiliar.CoordinateCreator;
import edu.upc.etsetb.arqsoft.spreadsheet.auxiliar.EditCellContent;
import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Cell;
import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Content;
import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Coordinate;
import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Spreadsheet;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.BadCoordinateException;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.CircularDependencyException;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.ContentException;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.NoNumberException;
import edu.upc.etsetb.arqsoft.spreadsheet.exceptions.TokenWrittenIncorrectlyException;
import edu.upc.etsetb.arqsoft.spreadsheet.exceptions.WrongSyntaxException;
import edu.upc.etsetb.arqsoft.spreadsheet.storage.S2VLoader;
import edu.upc.etsetb.arqsoft.spreadsheet.storage.S2VStore;
import edu.upc.etsetb.arqsoft.spreadsheet.usecases.project.Specifier;
import edu.upc.etsetb.arqsoft.spreadsheet.usecases.project.SyntaxChecker;
import edu.upc.etsetb.arqsoft.spreadsheet.usecases.project.Token;
import edu.upc.etsetb.arqsoft.spreadsheet.usecases.project.Tokenizer;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author oscar
 */
public class OurControllerForChecker implements ISpreadsheetControllerForChecker {
    private List<Cell> cells;
    private Spreadsheet spread;
    
    public OurControllerForChecker(Spreadsheet spreadsheet){
        this.cells = spreadsheet.getCells();
        this.spread = spreadsheet;
    }
    
    @Override
    public void setCellContent(String cellCoord, String strContent) throws ContentException, BadCoordinateException, CircularDependencyException {
        EditCellContent.edit(cellCoord, strContent, cells);
    }

    @Override
    public double getCellContentAsDouble(String cellCoord) throws BadCoordinateException, NoNumberException {
        Coordinate coord = CoordinateCreator.create(cellCoord);
        double output = 0;
         for(Cell cell : this.cells){
            if(cell.getStringCoordinate().equals(cellCoord)){
                try {
                    output = cell.getNumericValue();
                } catch (TokenWrittenIncorrectlyException | WrongSyntaxException | CircularDependencyException | ContentException ex) {
                    throw new NoNumberException(ex.getMessage());
                }
                break;
            }
        }
        return output;
    }

    @Override
    public String getCellContentAsString(String cellCoord) throws BadCoordinateException {
        Coordinate coord = CoordinateCreator.create(cellCoord);
        String output = "";
        for(Cell cell : this.cells){
            if(cell.getStringCoordinate().equals(cellCoord)){
                try {
                    output = cell.getTextValue();
                } catch (NoNumberException | TokenWrittenIncorrectlyException | WrongSyntaxException | CircularDependencyException ex) {
                    throw new BadCoordinateException(ex.getMessage());
                } catch (ContentException ex) {
                    throw new BadCoordinateException(ex.getMessage());
                }
                break;
            }
        }
        return output;
    }

    @Override
    public String getCellFormulaExpression(String cellCoord) throws BadCoordinateException {
        Coordinate coord = CoordinateCreator.create(cellCoord);
        String output = "";
        for(Cell cell : this.cells){
            if(cell.getStringCoordinate().equals(cellCoord)){
                output = cell.getContentAsString();
                break;
            }
        }
//        if(!output.contains("=")){
//            throw new BadCoordinateException("There is not a formula expression because this is not a formula, not contains the '=' at the beginning");
//        }
        output = output.replaceAll("=", "").replaceAll(" ", "");
        return output;
    }

    @Override
    public void saveSpreadSheetToFile(String nameInUserDir) throws SavingSpreadSheetException {
        S2VStore store = new S2VStore(nameInUserDir, new Spreadsheet(this.cells));
        store.storeSpreadsheet();
    }

    @Override
    public void readSpreadSheetFromFile(String nameInUserDir) throws ReadingSpreadSheetException {
        S2VLoader loader = new S2VLoader(nameInUserDir);
        this.spread = loader.loadSpreadsheet();
        this.cells.clear();
        this.cells = spread.getCells();
    }
    
}
