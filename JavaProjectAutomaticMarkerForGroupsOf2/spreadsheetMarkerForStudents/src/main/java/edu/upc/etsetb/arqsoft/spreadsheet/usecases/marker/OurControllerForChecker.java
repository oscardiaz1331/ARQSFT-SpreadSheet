/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.arqsoft.spreadsheet.usecases.marker;

import edu.upc.etsetb.arqsoft.spreadsheet.auxiliar.CoordinateCreator;
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
    private Tokenizer tokenizerLine;
    
    public OurControllerForChecker(Spreadsheet spreadsheet){
        this.cells = spreadsheet.getCells();
        this.tokenizerLine = new Tokenizer(Tokenizer.TokenizerType.S2VLINE);
    }
    
    @Override
    public void setCellContent(String cellCoord, String strContent) throws ContentException, BadCoordinateException, CircularDependencyException {
        Coordinate coord = CoordinateCreator.create(cellCoord);
        List<Token> tokens;
        try {
            tokens = this.tokenizerLine.tokenize(strContent);
        } catch (TokenWrittenIncorrectlyException ex) {
            throw new ContentException(ex.getMessage());
        }
        if(tokens.size()!=1){
            throw new ContentException("Different number of 1 in tokens after tokenize line is not allowed");
        }
        for(Cell cell : this.cells){
            if(cell.getStringCoordinate().equals(cellCoord)){
                try {
                    cell.setContent(Specifier.specifyContent(tokens.getFirst(), coord, cells));
                    break;
                }
                catch(WrongSyntaxException | TokenWrittenIncorrectlyException ex){
                    throw new ContentException(ex.getMessage());
                }
            }
        }
        Content content = Specifier.specifyContent(tokens.getFirst(), coord, cells);
        this.cells.add(new Cell(coord, content));
    }

    @Override
    public double getCellContentAsDouble(String cellCoord) throws BadCoordinateException, NoNumberException {
        Coordinate coord = CoordinateCreator.create(cellCoord);
        double output = 0;
         for(Cell cell : this.cells){
            if(cell.getStringCoordinate().equals(cellCoord)){
                output = cell.getNumericValue();
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
                output = cell.getTextValue();
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
        if(!output.contains("=")){
            throw new BadCoordinateException("There is not a formula expression because this is not a formula, not contains the '=' at the beginning");
        }
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
        try {
            loader.loadSpreadsheet();
        } catch (ContentException | CircularDependencyException ex) {
            throw new ReadingSpreadSheetException(ex.getMessage());
        }
    }
    
}
