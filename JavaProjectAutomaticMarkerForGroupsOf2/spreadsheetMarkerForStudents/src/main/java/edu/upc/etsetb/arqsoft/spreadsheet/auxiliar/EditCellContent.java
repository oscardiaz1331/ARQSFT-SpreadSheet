/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.arqsoft.spreadsheet.auxiliar;

import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Cell;
import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Content;
import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Coordinate;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.CircularDependencyException;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.ContentException;
import edu.upc.etsetb.arqsoft.spreadsheet.exceptions.TokenWrittenIncorrectlyException;
import edu.upc.etsetb.arqsoft.spreadsheet.exceptions.WrongSyntaxException;
import edu.upc.etsetb.arqsoft.spreadsheet.usecases.project.Specifier;
import edu.upc.etsetb.arqsoft.spreadsheet.usecases.project.Token;
import edu.upc.etsetb.arqsoft.spreadsheet.usecases.project.Tokenizer;
import java.util.List;

/**
 *
 * @author oscar
 */
public class EditCellContent {
    public static void edit(String cellCoord, String strContent, List<Cell> cells) throws ContentException, CircularDependencyException{
        Tokenizer tokenizerLine = new Tokenizer(Tokenizer.TokenizerType.SIMPLE_LINE);
        Coordinate coord = CoordinateCreator.create(cellCoord);
        List<Token> tokens;
        try {
            tokens = tokenizerLine.tokenize(strContent);
        } catch (TokenWrittenIncorrectlyException ex) {
            throw new ContentException(ex.getMessage());
        }
        if(tokens.size()!=1){
            throw new ContentException("Different number of 1 in tokens after tokenize line is not allowed");
        }
        for(Cell cell : cells){
            if(cell.getStringCoordinate().equals(cellCoord)){
                try {
                    cell.setContent(Specifier.specifyContent(tokens.getFirst(), coord, cells));
                    //this.cells.add(new Cell(coord, content, this.cells));
                    return;
                    
                }
                catch(WrongSyntaxException | TokenWrittenIncorrectlyException ex){
                    throw new ContentException(ex.getMessage());
                }
            }
        }
        Content content = Specifier.specifyContent(tokens.getFirst(), coord, cells);
        try {
            cells.add(new Cell(coord, content, cells));
        } catch (WrongSyntaxException | TokenWrittenIncorrectlyException ex) {
           throw new ContentException(ex.getMessage());
        }
    }
}
