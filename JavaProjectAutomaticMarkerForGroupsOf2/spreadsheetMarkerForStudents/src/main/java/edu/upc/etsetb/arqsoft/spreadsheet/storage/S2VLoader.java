/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.arqsoft.spreadsheet.storage;

import edu.upc.etsetb.arqsoft.spreadsheet.auxiliar.ColumnManager;
import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Cell;
import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Content;
import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Coordinate;
import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Formula;
import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.FormulaComponent;
import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.NumericalContent;
import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Spreadsheet;
import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.TextContent;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.CircularDependencyException;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.ContentException;
import edu.upc.etsetb.arqsoft.spreadsheet.exceptions.TokenWrittenIncorrectlyException;
import edu.upc.etsetb.arqsoft.spreadsheet.exceptions.WrongSyntaxException;
import edu.upc.etsetb.arqsoft.spreadsheet.usecases.marker.ReadingSpreadSheetException;
import edu.upc.etsetb.arqsoft.spreadsheet.usecases.project.Specifier;
import edu.upc.etsetb.arqsoft.spreadsheet.usecases.project.SyntaxChecker;
import edu.upc.etsetb.arqsoft.spreadsheet.usecases.project.Token;
import static edu.upc.etsetb.arqsoft.spreadsheet.usecases.project.Token.TokenType.TEXT_CONTENT;
import edu.upc.etsetb.arqsoft.spreadsheet.usecases.project.Tokenizer;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author oscar
 */
public class S2VLoader implements Loader{
    private Scanner scanner;
    private Tokenizer tokenizerLine;
    private S2VLineChecker checker;
    private List<Cell> cells;
    private int row;
    private String col;
    private final String newCol;
    
    public S2VLoader(String filename) throws ReadingSpreadSheetException{
        try{
            this.scanner = new Scanner(new FileInputStream(filename));
        }
        catch(FileNotFoundException ex){
            throw new ReadingSpreadSheetException(ex.getMessage());
        }
        this.tokenizerLine = new Tokenizer(Tokenizer.TokenizerType.S2VLINE);
        this.cells = new LinkedList<>();
        this.row = 0;
        this.col = String.valueOf((char)('A'-1));
        this.newCol = String.valueOf((char)('A'-1));
    }

    @Override
    public Spreadsheet loadSpreadsheet() throws ReadingSpreadSheetException, ContentException, CircularDependencyException{
        try {
            while (scanner.hasNextLine()) {
                this.row++;
                this.col = this.newCol;
                this.loadLine();
            }
        } catch (ReadingSpreadSheetException | ContentException | CircularDependencyException ex) {
            // Log the exception or handle it appropriately
            throw ex;
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        Spreadsheet spreadsheet = new Spreadsheet(this.cells);
        return spreadsheet;
    }
    
    public void loadLine() throws ReadingSpreadSheetException, ContentException, CircularDependencyException{
        String s = this.scanner.nextLine();
        List<Token> tokens = new LinkedList<>();
        try{
            tokens = tokenizerLine.tokenize(s);
        }
        catch(TokenWrittenIncorrectlyException ex){
            throw new ReadingSpreadSheetException(ex.getMessage());
        }
        checker = new S2VLineChecker(tokens);
        checker.check();
        for(Token token : tokens){
            this.loadCell(token);
        }
    }
    
    public void loadCell(Token token) throws ReadingSpreadSheetException, ContentException, CircularDependencyException{
        this.col = ColumnManager.newColumn(this.col);
        Coordinate coord = new Coordinate(this.row, this.col);
        if(token.token != Token.TokenType.SEMICOLON){
            Content content = Specifier.specifyContent(token, coord, this.cells);
            this.cells.add(new Cell(coord, content));
        }
    }
}
