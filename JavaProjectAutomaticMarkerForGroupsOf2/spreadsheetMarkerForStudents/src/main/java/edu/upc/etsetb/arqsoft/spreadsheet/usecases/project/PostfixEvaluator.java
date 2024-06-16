/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.arqsoft.spreadsheet.usecases.project;

import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Number;
import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Operand;
import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Operator;
import edu.upc.etsetb.arqsoft.spreadsheet.auxiliar.OperationCalculator;
import edu.upc.etsetb.arqsoft.spreadsheet.auxiliar.PostfixVisitor;
import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.FormulaComponent;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.NoNumberException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author oscar
 */
public class PostfixEvaluator implements PostfixVisitor{
    private List<FormulaComponent> components;
    private LinkedList<Operand> stack;
    
    public PostfixEvaluator(List<FormulaComponent> components){
        this.components = components;
        this.stack = new LinkedList<>();
    }
    
    @Override
    public void visitOperand(Operand operand){
        this.stack.add(operand);
    }
    
    @Override
    public void visitOperator(Operator operator) throws NoNumberException{
        Operand secondElement = this.stack.removeLast();
        Operand firstElement = this.stack.removeLast();
        Number result = new OperationCalculator(firstElement, operator, secondElement).compute();
        this.stack.add(result);
    }
    
    public Number result() throws NoNumberException{
        for(FormulaComponent component : this.components){
            component.accept(this);
        }
        return (Number) this.stack.getLast();
    }

}
