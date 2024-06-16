/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.arqsoft.spreadsheet.auxiliar;

import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Number;
import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Operand;
import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Operator;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.NoNumberException;

/**
 *
 * @author oscar
 */

public class OperationCalculator {
    private Operand firstElement, secondElement;
    private Operator operator;
    
    public OperationCalculator(Operand firstElement, Operator operator, Operand secondElement){
        this.firstElement = firstElement;
        this.operator = operator;
        this.secondElement = secondElement;
    }
    
    //Easier to introduce new operator
    public Number compute() throws NoNumberException{
        switch(this.operator.getType()){
            case ADD:
                return this.computeAdd();
            case SUB:
                return this.computeSub();
            case MULT:
                return this.computeMult();
            case DIV:
                return this.computeDiv();
        }
        return null;
    }
    
    private Number computeAdd() throws NoNumberException{
        return new Number(this.firstElement.getNumericValue() + this.secondElement.getNumericValue());
    }
    private Number computeSub() throws NoNumberException{
        return new Number(this.firstElement.getNumericValue() - this.secondElement.getNumericValue());
    }
    private Number computeMult() throws NoNumberException{
        return new Number(this.firstElement.getNumericValue() * this.secondElement.getNumericValue());
    }
    private Number computeDiv() throws NoNumberException{
        return new Number(this.firstElement.getNumericValue() / this.secondElement.getNumericValue());
    }
}
