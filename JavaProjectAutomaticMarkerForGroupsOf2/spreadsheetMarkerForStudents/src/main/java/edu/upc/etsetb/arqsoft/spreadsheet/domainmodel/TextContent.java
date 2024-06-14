/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.arqsoft.spreadsheet.domainmodel;

/**
 *
 * @author oscar
 */
public class TextContent extends Content {
    
    public TextContent(String text) {
        if(text.isEmpty()){
            this.value = new Number(0);
        }
        else{
            this.value = new Text(text);
        }
    }
    
}
