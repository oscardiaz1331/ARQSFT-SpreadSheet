/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.arqsoft.spreadsheet.domainmodel;

import java.util.List;

/**
 *
 * @author oscar
 */
public class Formula extends Content {
    List<FormulaComponent> components;
    
    public Formula(List<FormulaComponent> components, Value value){
        this.components = components;
        this.value = value;
    }
    public void setFormulaComponents(List<FormulaComponent> components){
        this.components = components;
    }
}
