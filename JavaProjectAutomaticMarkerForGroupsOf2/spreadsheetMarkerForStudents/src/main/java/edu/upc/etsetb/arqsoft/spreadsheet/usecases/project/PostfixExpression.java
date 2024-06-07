/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.arqsoft.spreadsheet.usecases.project;

import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.FormulaComponent;
import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Operator;
import edu.upc.etsetb.arqsoft.spreadsheet.exceptions.MathErrorException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author oscar
 */
public class PostfixExpression {
    List<FormulaComponent> components;
    LinkedList<Operator> stack;
    
    public PostfixExpression(List<FormulaComponent> components){
        this.components = components;
    }
    
    public void generate(){
        this.stack = new LinkedList<>();
        for(FormulaComponent comp : components){
            if(comp instanceof Operator){
                Operator op =(Operator) comp;
                if(op.getType() == Operator.OperatorType.OPEN_PAREN){
                    stack.add(op);
                }
                else if(op.getType() == Operator.OperatorType.CLOSE_PAREN){
                    while(stack.getLast().getType() != Operator.OperatorType.OPEN_PAREN){
                        components.add(stack.removeLast());
                        stack.add(op);
                    }
                    stack.removeLast();
                }
                else if(stack.isEmpty() || stack.getLast().getPriority() > op.getPriority()){
                    stack.add(op);
                }
                else{  
                    components.add(stack.removeLast());
                    stack.add(op);
                }
            }
            else{
                components.add(comp);
            }
        }
        while (!stack.isEmpty()) {
            components.add(stack.removeLast());
        }
    }
//    public Number evaluate() throws MathErrorException {
//        this.stack = new LinkedList<>();
//
//    }
    
    
}
