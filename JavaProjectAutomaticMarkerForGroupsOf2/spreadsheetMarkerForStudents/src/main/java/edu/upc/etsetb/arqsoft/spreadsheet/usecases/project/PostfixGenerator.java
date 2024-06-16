/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.arqsoft.spreadsheet.usecases.project;

import edu.upc.etsetb.arqsoft.spreadsheet.auxiliar.PostfixVisitor;
import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.FormulaComponent;
import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Operand;
import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Operator;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.CircularDependencyException;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.ContentException;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.NoNumberException;
import edu.upc.etsetb.arqsoft.spreadsheet.exceptions.MathErrorException;
import edu.upc.etsetb.arqsoft.spreadsheet.exceptions.TokenWrittenIncorrectlyException;
import edu.upc.etsetb.arqsoft.spreadsheet.exceptions.WrongSyntaxException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author oscar
 */
public class PostfixGenerator implements PostfixVisitor{
    private List<FormulaComponent> components, output;
    private LinkedList<Operator> stack;
    
    public PostfixGenerator(List<FormulaComponent> components){
        this.components = components;
        this.output = new LinkedList<>();
        this.stack = new LinkedList<>();
    }

    @Override
    public void visitOperand(Operand operand) {
        output.add(operand);
    }

    @Override
    public void visitOperator(Operator operator) {
        if(operator.getType() == Operator.OperatorType.OPEN_PAREN){
            stack.add(operator);
        }
        else if(operator.getType() == Operator.OperatorType.CLOSE_PAREN){
            while(stack.getLast().getType() != Operator.OperatorType.OPEN_PAREN){
                output.add(stack.removeLast());
                //stack.add(operator);
            }
            stack.removeLast();
        }
        else if(stack.isEmpty() || stack.getLast().getPriority() < operator.getPriority()){
            stack.add(operator);
        }
        else{  
            output.add(stack.removeLast());
            stack.add(operator);
        }
    }
    
    public List<FormulaComponent> result() throws NoNumberException, TokenWrittenIncorrectlyException, WrongSyntaxException, CircularDependencyException, ContentException{
        for(FormulaComponent component : this.components){
            component.accept(this);
        }
        
        while (!stack.isEmpty()) {
            output.add(stack.removeLast());
        }
        return this.output;
    }  
}
