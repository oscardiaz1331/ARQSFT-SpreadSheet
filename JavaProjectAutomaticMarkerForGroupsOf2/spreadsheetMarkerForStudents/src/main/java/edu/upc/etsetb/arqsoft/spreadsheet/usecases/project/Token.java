/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.arqsoft.spreadsheet.usecases.project;

/**
 *
 * @author oscar
 */
public class Token {
    
    public enum TokenType {
        EQUALS, 
        OPERATOR, 
        FUNCTION_NAME, 
        FLOAT_NUM,
        INTEGER, 
        COLON, 
        CELL_COORD, 
        OPEN_PAREN, 
        CLOSE_PAREN, 
        SEMICOLON,
        LETTER,
        TEXT_CONTENT,
        FORMULA
    }
    
    public final TokenType token;
    public final String sequence;
    
    public Token(TokenType token, String sequence){
        super();
        this.token = token;
        this.sequence = sequence;
    }
}
