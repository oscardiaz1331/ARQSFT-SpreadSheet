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
    private LinkedList<TokenInfo> tokens;
    
    public Tokenizer(){
        this.tokenInfos = new LinkedList<TokenInfo>();
        this.tokens = new LinkedList<Token>();
    }
    public void add(String regex, int token){
        this.tokenInfos.add(new TokenInfo(Pattern.compile("^("+regex+")"), token));
    }
}
