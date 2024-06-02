/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package edu.upc.etsetb.arqsoft.spreadsheet.domainmodel;

/**
 *
 * @author oscar
 */
public abstract class Operand implements FormulaComponent {
    private String stringRepresentation;
    
    public Operand(){}
    
    protected abstract double getNumericValue();
    
}

