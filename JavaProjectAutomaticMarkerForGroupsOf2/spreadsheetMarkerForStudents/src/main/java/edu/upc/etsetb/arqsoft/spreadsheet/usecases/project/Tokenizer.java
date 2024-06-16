/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.arqsoft.spreadsheet.usecases.project;

/**
 *
 * @author oscar
 */

import java.util.LinkedList;
import java.util.regex.Pattern;
import edu.upc.etsetb.arqsoft.spreadsheet.exceptions.TokenWrittenIncorrectlyException;
import java.util.List;
import java.util.regex.Matcher;



public class Tokenizer {

    public enum TokenizerType {
        FORMULA,
//        FUNCTION,
        COORDINATES,
        S2VLINE
    }
    
    private class TokenInfo{
        
        public final Pattern regex;
        public final Token.TokenType token;
        
        public TokenInfo(Pattern regex, Token.TokenType token){
            super();
            this.regex = regex;
            this.token = token;
        }
    }
    private LinkedList<TokenInfo> tokenInfos;
    private LinkedList<Token> tokens;
    String integer, letters , operators, floatNum , functionName, cellCoord, all;
    
    public Tokenizer(TokenizerType type){
        this.tokenInfos = new LinkedList<>();
        this.tokens = new LinkedList<>();
        this.integer = "[0-9]+";
        this.letters = "([a-zA-Z]|[ ])+";
        this.operators = "[+-]|[*/]";
        this.floatNum = this.integer + "[.]|[,]" + this.integer;
        this.functionName = "(SUM|MIN|MAX|PROMEDIO)";
        this.cellCoord = this.letters + this.integer;
        this.all = "(" + this.letters + "|" + this.floatNum + "|" + this.operators + "|" + this.integer + "|" + ",|[()]" + ")+";
        
        if(null != type)switch (type) {
            case FORMULA:
                this.addFormulaTokens();
                break;
            case COORDINATES:
                this.addCoordinateTokens();
                break;
            case S2VLINE:
                this.addS2VLineTokens();
                break;
            default:
                break;
        }

    }
    public void add(String regex, Token.TokenType token){
        this.tokenInfos.add(new TokenInfo(Pattern.compile("^("+regex+")"), token));
    }
    
    private void addFormulaTokens(){     
        this.add("=", Token.TokenType.EQUALS);
        this.add(operators, Token.TokenType.OPERATOR);
        this.add(functionName, Token.TokenType.FUNCTION_NAME);
        this.add(floatNum, Token.TokenType.FLOAT_NUM);
        this.add(integer, Token.TokenType.INTEGER);
        this.add("[:]", Token.TokenType.COLON);
        this.add(cellCoord, Token.TokenType.CELL_COORD);
        this.add("[(]", Token.TokenType.OPEN_PAREN);
        this.add("[)]", Token.TokenType.CLOSE_PAREN);
        this.add(";", Token.TokenType.SEMICOLON);
        this.add(letters, Token.TokenType.LETTER);
    }
    
    private void addCoordinateTokens(){
        this.add(letters, Token.TokenType.LETTER);
        this.add(integer, Token.TokenType.INTEGER);
    }
    
    private void addS2VLineTokens() {
        this.add(";", Token.TokenType.SEMICOLON);
        this.add("[=]" + functionName + this.all, Token.TokenType.FUNCTION);
        this.add(this.integer + "|" + this.floatNum, Token.TokenType.NUMERICAL_CONTENT);
        this.add(this.all, Token.TokenType.TEXT_CONTENT);
    }
    
//    private void addFunctionTokens() {
//        this.add(functionName, Token.TokenType.FUNCTION_NAME);
//        this.add(floatNum, Token.TokenType.FLOAT_NUM);
//        this.add(integer, Token.TokenType.INTEGER);
//        this.add("[:]", Token.TokenType.COLON);
//        this.add(cellCoord, Token.TokenType.CELL_COORD);
//        this.add("[(]", Token.TokenType.OPEN_PAREN);
//        this.add("[)]", Token.TokenType.CLOSE_PAREN);
//        this.add(";", Token.TokenType.SEMICOLON);
//    }
    
    public List<Token> tokenize(String argument) throws TokenWrittenIncorrectlyException {
        String s = argument;
        tokens.clear();
        while(!s.equals("")){
            boolean match = false;
            for(TokenInfo info : tokenInfos){
                Matcher m = info.regex.matcher(s);
                if(m.find()){
                    match = true;
                    
                    String tok = m.group().trim();
                    tokens.add(new Token(info.token, tok));
                    s = m.replaceFirst("");
                    break;
                }
            }
        if(!match) throw new TokenWrittenIncorrectlyException("Unexpected character in input: "+s);
        }
        return this.tokens;
    }
}
