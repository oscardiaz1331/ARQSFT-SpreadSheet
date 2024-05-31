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
        public final int token;
        
        public TokenInfo(Pattern regex, int token){
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
        this.add("=", 0);
        String operators = "[+-*/]";
        this.add(operators, 1);
        String integer = "[0-9]+";
        this.add(integer, 2);
        String floatNum = integer + ".|," + integer;
        this.add(floatNum, 3);
        String functions = "SUM|MIN|MAX|PROMEDIO";
        this.add(functions, 4);
        String cellCoord = "[Aa-zA-Z]+"+integer;
        this.add(cellCoord, 5);
        this.add("\\(", 6);
        this.add("\\)", 7);
        String range = cellCoord + ":" + cellCoord; 
        this.add(range, 8);
               
    }
    public void add(String regex, int token){
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
