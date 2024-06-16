/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.arqsoft.spreadsheet.domainmodel;

import edu.upc.etsetb.arqsoft.spreadsheet.entities.NoNumberException;

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
    public double getNumericValue() throws NoNumberException{
        double result;
        try{
             result = Double.parseDouble(this.text);
        }
        catch(NumberFormatException ex){
            throw new NoNumberException("This cell has a text value that can not be converted to a number");
        }
        return result;
    }

    @Override
    public String getTextValue() {
        return this.text;
    }
    
}
