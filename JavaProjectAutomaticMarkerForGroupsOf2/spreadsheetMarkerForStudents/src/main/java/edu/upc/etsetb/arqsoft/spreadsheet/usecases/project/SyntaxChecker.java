/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.arqsoft.spreadsheet.usecases.project;

import edu.upc.etsetb.arqsoft.spreadsheet.auxiliar.Checker;
import java.util.List;
import edu.upc.etsetb.arqsoft.spreadsheet.exceptions.WrongSyntaxException;


/**
 *
 * @author oscar
 */
public class SyntaxChecker implements Checker{
    private List<Token> tokens;
    private int numOpenParen;
    private int nFunc;
    private boolean operatorTurn;
    
    public SyntaxChecker(List<Token> tokens){
        this.tokens = tokens;
        this.numOpenParen = 0;
        this.nFunc = 0;
        this.operatorTurn = false;
    }
    
    @Override
    public void check() throws WrongSyntaxException {
        if(this.tokens.getFirst().token != Token.TokenType.EQUALS){
            throw new WrongSyntaxException("The arguments must start with an equal character");
        }
        for(int i=1; i < this.tokens.size(); i++){
            switch (this.tokens.get(i).token){
                case LETTER:
                    throw new WrongSyntaxException("Letters are not allowed except to define Cell coordinates or a Function");
                case EQUALS :
                    throw new WrongSyntaxException("The equal character only can be the first character");
                case OPEN_PAREN:
                    this.numOpenParen++;
                    if(this.operatorTurn == true && i != 1 && this.nFunc == 0){
                        throw new WrongSyntaxException("There is a operand before an open parethesis, it must be an operator");
                    }
                    this.operatorTurn = false;
                    break;
                case CLOSE_PAREN:
                    if(this.tokens.get(i-1).token == Token.TokenType.OPEN_PAREN){
                        throw new WrongSyntaxException("A parenthesis is opened and closed with nothing between parenthesis");
                    }
                    this.numOpenParen--;
                    if(this.numOpenParen < 0){
                        throw new WrongSyntaxException("A parenthesis is closed before been opened");
                    }
                    if(this.nFunc > 0){
                        this.nFunc--;
                    }
                    if(this.operatorTurn == false){
                        throw new WrongSyntaxException("There is a operator before a close parenthesis, it must be an operand");
                    }
                    break;
                case SEMICOLON:
                    if(this.nFunc <= 0){
                        throw new WrongSyntaxException("The semicolon character ';' can not be used out of a function");
                    }
                    if(this.operatorTurn == false){
                        throw new WrongSyntaxException("There is more than one operator preceding another operator, you must write a semicolon in the function");
                    }
                    this.operatorTurn = false;
                    break;
                case COLON:
                    if(i+1 >= this.tokens.size() || this.tokens.get(i+1).token != Token.TokenType.CELL_COORD || this.tokens.get(i-1).token != Token.TokenType.CELL_COORD){
                        throw new WrongSyntaxException("The colon character must be used only between cell coordinates");
                    }
                    if(this.nFunc <= 0){
                        throw new WrongSyntaxException("The range can not be used out of a function");
                    }
                    if(this.operatorTurn == false){
                        throw new WrongSyntaxException("There is more than one operator preceding another operator, you must write a semicolon in the function");
                    }
                    this.operatorTurn = false;
                    break;
                case INTEGER:
                case FLOAT_NUM:
                case CELL_COORD:
                    if(this.operatorTurn == true){
                        throw new WrongSyntaxException("There is more than one operand preceding another operand, you must write an operator or a semicolon in case of been in a function");
                    }
                    this.operatorTurn = true;
                    break;
                case OPERATOR:
                    if(this.operatorTurn == false){
                        throw new WrongSyntaxException("There is more than one operator preceding another operator, you must write an operand");
                    }
                    if(this.nFunc > 0){
                        throw new WrongSyntaxException("Operators can not be used inside function");
                    }
                    this.operatorTurn = false;
                    break;
                case FUNCTION_NAME:
                    if(this.operatorTurn == true){
                        throw new WrongSyntaxException("There is more than one operand preceding another operand, you must write an operator");
                    }
                    this.operatorTurn = true;
                    if(i+1 >= this.tokens.size() || this.tokens.get(i+1).token != Token.TokenType.OPEN_PAREN){
                        throw new WrongSyntaxException("A parenthesis must be opened after the function name");
                    }
                    this.nFunc++;
                    break;
            }
        }
        if(this.nFunc > 0 ){
            throw new WrongSyntaxException("There is some function opened");
        }
        if(this.numOpenParen > 0){
            throw new WrongSyntaxException("There is some parenthesis opened");
        }
        if(!this.operatorTurn){
            throw new WrongSyntaxException("The formula must close with an operand");
        }
    }
    
    public void checkCoord() throws WrongSyntaxException{
        if(tokens.size() != 2){
            throw new WrongSyntaxException("The coordinate tokens are not 2 tokens");
        }
        if(tokens.getFirst().token != Token.TokenType.LETTER || tokens.getLast().token != Token.TokenType.INTEGER){
            throw new WrongSyntaxException("The coordinate tokens must be [LETTER]+[INTEGER]");
        }
    }
}

