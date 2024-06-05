/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package edu.upc.etsetb.arqsoft.spreadsheet.usecases.project;

import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Cell;
import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Coordinate;
import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.TextContent;
import edu.upc.etsetb.arqsoft.spreadsheet.exceptions.NonExistentCell;
import edu.upc.etsetb.arqsoft.spreadsheet.exceptions.TokenWrittenIncorrectlyException;
import edu.upc.etsetb.arqsoft.spreadsheet.exceptions.WrongSyntaxExpression;
import java.util.LinkedList;

/**
 *
 * @author oscar
 */
public class NewMain {

    /**
     * @param args the command line arguments
     * @throws edu.upc.etsetb.arqsoft.spreadsheet.exceptions.TokenWrittenIncorrectlyException
     */
    public static void main(String[] args) throws TokenWrittenIncorrectlyException, WrongSyntaxExpression, NonExistentCell {
        // TODO code application logic here
        Tokenizer tokenizer = new Tokenizer(Tokenizer.TokenizerType.FORMULA);
        LinkedList<Token> tokens = tokenizer.tokenize("=1.9+A42*B6*6+SUM(A42:B6;A42;3;SUM(A42:B6;B6;3;B6))+SUM(2)");
        for (Token token : tokens){
            System.out.println(token.token+" "+token.sequence+"\n");
        }
        SyntaxChecker checker = new SyntaxChecker(tokens);
        
        checker.checkFormulaSyntax();
        LinkedList<Cell> cells = new LinkedList<>();
        cells.add(new Cell(new Coordinate(42,"A"),new TextContent()));
        cells.add(new Cell(new Coordinate(6,"B"),new TextContent()));
        Specifier specifier = new Specifier(tokens, cells);
        specifier.specifyFormulaComponents();
    }
    
}
