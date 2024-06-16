/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.arqsoft.spreadsheet.auxiliar;

import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Operand;
import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Operator;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.NoNumberException;

/**
 *
 * @author oscar
 */
public interface PostfixVisitor {
    
    public void visitOperand(Operand operand);
    public void visitOperator(Operator operator) throws NoNumberException ;
}
