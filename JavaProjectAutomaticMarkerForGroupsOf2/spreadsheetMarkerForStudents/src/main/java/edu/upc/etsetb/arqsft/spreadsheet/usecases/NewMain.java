/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package edu.upc.etsetb.arqsft.spreadsheet.usecases;

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
    public static void main(String[] args) throws TokenWrittenIncorrectlyException, WrongSyntaxExpression {
        // TODO code application logic here
        Tokenizer tokenizer = new Tokenizer(Tokenizer.TokenizerType.FORMULA);
        LinkedList<Token> tokens = tokenizer.tokenize("=1.9+A42*B6*6+SUM(A1:B4;A3;3;SUM(A1:B4;A3;3;B5))+SUM(2)");
        for (Token token : tokens){
            System.out.println(token.token+" "+token.sequence+"\n");
        }
        SyntaxChecker checker = new SyntaxChecker(tokens);
        
        checker.checkFormulaSyntax();
    }
    
}
