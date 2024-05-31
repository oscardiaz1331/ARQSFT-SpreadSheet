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
    protected abstract double getNumericValue();
}

