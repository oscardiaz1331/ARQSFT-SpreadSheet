/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.arqsft.spreadsheet.usecases;

/**
 *
 * @author oscar
 */

import java.util.LinkedList;
import java.util.regex.Pattern;
import edu.upc.etsetb.arqsoft.spreadsheet.exceptions.TokenWrittenIncorrectlyException;
import java.util.regex.Matcher;



public class Tokenizer {
    
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

    
    public Tokenizer(){
        this.tokenInfos = new LinkedList<>();
        this.tokens = new LinkedList<>();
        
        String operators = "[+-]|[*/]";
        String integer = "[0-9]+";
        String letters = "[a-zA-Z]+";
        String floatNum = integer + "[.,]" + integer;
        String functionName = "(SUM|MIN|MAX|PROMEDIO)";
        String cellCoord = letters + integer;
        //String allAllowedChars = letters + "|" + floatNum + "|" + integer + "|" + ":" + "|" +";";
        //String argument = "(" + range + "|" + floatNum + "|" + integer + "|" + cellCoord + "|(" + functionName + "[(](" + allAllowedChars + ")+[)]))";
        //String function = functionName + "[(]" + argument +  "(;" + argument  + ")*[)]";
        //String operands = "(" + function + ")|(" + floatNum + ")|(" + integer + ")|(" + cellCoord + ")";
        this.add("=", Token.TokenType.EQUALS);
        this.add(operators, Token.TokenType.OPERATOR);
        this.add(functionName, Token.TokenType.FUNCTION_NAME);
        this.add(floatNum, Token.TokenType.FLOAT_NUM);
        this.add(integer, Token.TokenType.INTEGER);
        this.add("[:]", Token.TokenType.COLON);
        this.add(cellCoord,Token.TokenType.CELL_COORD);
        this.add("[(]", Token.TokenType.OPEN_PAREN);
        this.add("[)]", Token.TokenType.CLOSE_PAREN);
        this.add(";", Token.TokenType.SEMICOLON);
    }
    public void add(String regex, Token.TokenType token){
        this.tokenInfos.add(new TokenInfo(Pattern.compile("^("+regex+")"), token));
    }
    
    public LinkedList<Token> tokenizeFormula(String argument) throws TokenWrittenIncorrectlyException {
        String s = new String(argument);
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
