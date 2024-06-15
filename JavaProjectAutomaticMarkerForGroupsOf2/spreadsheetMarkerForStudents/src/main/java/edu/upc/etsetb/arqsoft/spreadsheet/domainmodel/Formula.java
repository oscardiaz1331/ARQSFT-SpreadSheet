/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.arqsoft.spreadsheet.domainmodel;

import edu.upc.etsetb.arqsoft.spreadsheet.storage.ContentVisitor;
import java.util.List;

/**
 *
 * @author oscar
 */
public class Formula extends Content {
    List<FormulaComponent> components;
    String content;
    
    public Formula(String content, List<FormulaComponent> components){
        this.content = content;
        this.components = components;
        //Todo, assign value
        //this.value = value;
    }
    public void setFormulaComponents(List<FormulaComponent> components){
        this.components = components;
    }

    @Override
    public String getContent() {
        return this.content.replaceAll(";", ",");
    }
}
