/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.arqsoft.spreadsheet.storage;

import edu.upc.etsetb.arqsoft.spreadsheet.auxiliar.Checker;
import edu.upc.etsetb.arqsoft.spreadsheet.exceptions.TokenWrittenIncorrectlyException;
import edu.upc.etsetb.arqsoft.spreadsheet.usecases.marker.ReadingSpreadSheetException;
import edu.upc.etsetb.arqsoft.spreadsheet.usecases.project.Token;
import edu.upc.etsetb.arqsoft.spreadsheet.usecases.project.Tokenizer;
import java.util.List;

/**
 *
 * @author oscar
 */
public class S2VLineChecker implements Checker {
    private List<Token> tokens;
    
    public S2VLineChecker(List<Token> tokens){
        this.tokens = tokens;
    }
    
    @Override
    public void check() throws ReadingSpreadSheetException {
        Boolean notContentTurn = false;
        for(Token token : this.tokens){
            //Also can be done with a if semicolon, else. But I think this is easier to understand
            switch(token.token){
                case FORMULA:
                case TEXT_CONTENT:
                case FLOAT_NUM:
                case INTEGER:
                    if(notContentTurn){
                        throw new ReadingSpreadSheetException("The spreadsheet that you are trying to load is corrupted.");
                    }
                    notContentTurn = true;
                    break;
                case SEMICOLON:
                    notContentTurn = false;
                    break;
            }
        }
    }   
}
