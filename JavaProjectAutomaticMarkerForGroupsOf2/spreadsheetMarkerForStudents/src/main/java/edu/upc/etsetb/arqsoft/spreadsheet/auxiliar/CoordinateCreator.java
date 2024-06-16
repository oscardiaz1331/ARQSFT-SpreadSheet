package edu.upc.etsetb.arqsoft.spreadsheet.auxiliar;

import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Coordinate;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.BadCoordinateException;
import edu.upc.etsetb.arqsoft.spreadsheet.exceptions.TokenWrittenIncorrectlyException;
import edu.upc.etsetb.arqsoft.spreadsheet.exceptions.WrongSyntaxException;
import edu.upc.etsetb.arqsoft.spreadsheet.usecases.project.SyntaxChecker;
import edu.upc.etsetb.arqsoft.spreadsheet.usecases.project.Token;
import edu.upc.etsetb.arqsoft.spreadsheet.usecases.project.Tokenizer;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author oscar
 */
public class CoordinateCreator {
    public static Coordinate create(String cellCoord){
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
        return coord;
    }
}
