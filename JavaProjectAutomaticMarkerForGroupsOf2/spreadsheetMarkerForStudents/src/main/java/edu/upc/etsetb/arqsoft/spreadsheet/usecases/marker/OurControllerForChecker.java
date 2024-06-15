/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.arqsoft.spreadsheet.usecases.marker;

import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Cell;
import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Coordinate;
import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Spreadsheet;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.BadCoordinateException;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.CircularDependencyException;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.ContentException;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.NoNumberException;
import edu.upc.etsetb.arqsoft.spreadsheet.exceptions.TokenWrittenIncorrectlyException;
import edu.upc.etsetb.arqsoft.spreadsheet.exceptions.WrongSyntaxException;
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
        Tokenizer tokenizer = new Tokenizer(Tokenizer.TokenizerType.COORDINATES);
        Coordinate coord;
        try {
            List<Token> coordTokens = tokenizer.tokenize(cellCoord);
            SyntaxChecker checker = new SyntaxChecker(coordTokens);
            checker.checkCoord();
            int row = 0;
            String column = "";
            for(Token coordToken : coordTokens){
                //It has passed away the syntax checker function. 
                //There are not errors
                if(coordToken.token == Token.TokenType.LETTER){
                    column = coordToken.sequence;
                }
                else if(coordToken.token == Token.TokenType.INTEGER){
                    row =  Integer.parseInt(coordToken.sequence);
                }
            }
            coord = new Coordinate(row, column);
        } catch (TokenWrittenIncorrectlyException | WrongSyntaxException ex) {
            throw new BadCoordinateException(ex.getMessage());
        }
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
                cell.setContent(Specifier.specifyContent(tokens.getFirst(), coord, cells));
            }
        }
    }

    @Override
    public double getCellContentAsDouble(String coord) throws BadCoordinateException, NoNumberException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getCellContentAsString(String cooord) throws BadCoordinateException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getCellFormulaExpression(String coord) throws BadCoordinateException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void saveSpreadSheetToFile(String nameInUserDir) throws SavingSpreadSheetException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void readSpreadSheetFromFile(String nameInUserDir) throws ReadingSpreadSheetException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
