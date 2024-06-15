/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.arqsoft.spreadsheet.domainmodel;

import edu.upc.etsetb.arqsoft.spreadsheet.storage.ContentVisitor;

/**
 *
 * @author oscar
 */
public class NumericalContent extends Content {
    
    public NumericalContent(Number value) {
        this.value = value;
    }

    @Override
    public String getContent() {
        return this.value.getTextValue();
    }
    
}
