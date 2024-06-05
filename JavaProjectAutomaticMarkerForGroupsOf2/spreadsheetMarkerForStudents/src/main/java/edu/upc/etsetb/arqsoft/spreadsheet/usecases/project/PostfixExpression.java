/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.arqsoft.spreadsheet.usecases.project;

import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.FormulaComponent;
import edu.upc.etsetb.arqsoft.spreadsheet.exceptions.MathErrorException;
import java.util.List;

/**
 *
 * @author oscar
 */
public class PostfixExpression {
    List<FormulaComponent> components;
    
    public PostfixExpression(List<FormulaComponent> components){
        this.components = components;
    }
    
    //public List<FormulaComponent> generate(List<FormulaComponent> components){
        
    //}
    //public Number evaluate() throws MathErrorException {
        //TODO

    //}
    
    
}
