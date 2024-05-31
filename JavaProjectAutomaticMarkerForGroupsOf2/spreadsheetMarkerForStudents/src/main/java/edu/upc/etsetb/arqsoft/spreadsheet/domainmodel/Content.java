/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.arqsoft.spreadsheet.domainmodel;

/**
 *
 * @author oscar
 */
public abstract class Content {
    Value value;
    
    
    public double getNumericValue(){
        return this.value.getNumericValue();
    }
    
    public String getTextValue(){
        return this.value.getTextValue();
    }
}
