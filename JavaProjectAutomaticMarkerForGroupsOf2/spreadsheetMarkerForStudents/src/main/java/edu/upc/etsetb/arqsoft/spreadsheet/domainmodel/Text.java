/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.arqsoft.spreadsheet.domainmodel;

/**
 *
 * @author oscar
 */
public class Text implements Value {

    private String text;
    
    public Text(String text){
        this.text = text;
    }
    
    public void setText(String text){
        this.text = text;
    }
    
    @Override
    public double getNumericValue() {
        //TODO
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getTextValue() {
        return this.text;
    }
    
}
