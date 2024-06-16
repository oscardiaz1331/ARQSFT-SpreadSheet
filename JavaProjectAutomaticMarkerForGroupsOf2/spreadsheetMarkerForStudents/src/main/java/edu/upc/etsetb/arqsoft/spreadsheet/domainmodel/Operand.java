/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package edu.upc.etsetb.arqsoft.spreadsheet.domainmodel;

import edu.upc.etsetb.arqsoft.spreadsheet.auxiliar.PostfixVisitor;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.NoNumberException;

/**
 *
 * @author oscar
 */
public abstract class Operand implements FormulaComponent {
    private String stringRepresentation;
    
    public Operand(){}
    
    public abstract double getNumericValue()throws NoNumberException;
    
    public abstract String getTextValue();
    
    @Override
    public void accept(PostfixVisitor visitor){
        visitor.visitOperand(this);
    }
    
}

