/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package edu.upc.etsetb.arqsoft.spreadsheet.domainmodel;

import edu.upc.etsetb.arqsoft.spreadsheet.auxiliar.PostfixVisitor;

/**
 *
 * @author oscar
 */
public abstract class Operand implements FormulaComponent {
    private String stringRepresentation;
    
    public Operand(){}
    
    public abstract double getNumericValue();
    
    @Override
    public void accept(PostfixVisitor visitor){
        visitor.visitOperand(this);
    }
    
}

