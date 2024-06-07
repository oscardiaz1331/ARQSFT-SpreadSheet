/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.arqsoft.spreadsheet.domainmodel;

/**
 *
 * @author oscar
 */
public class Operator implements FormulaComponent{
    private String operator;
    private int priority;
    private OperatorType type;
    
    public enum OperatorType {
        ADD,
        SUB,
        MULT,
        DIV,
        OPEN_PAREN, 
        CLOSE_PAREN, 
    }
    
    public Operator(String operator){
        this.operator = operator;
        switch(operator){
            case "+":
                this.type = OperatorType.ADD;
                this.priority = 1;
                break;
            case "-":
                this.type = OperatorType.SUB;
                this.priority = 1;
                break;
            case "*":
                this.type = OperatorType.MULT;
                this.priority = 2;
                break;
            case "/":
                this.type = OperatorType.DIV;
                this.priority = 2;
                break;
            case ")":
                this.type = OperatorType.CLOSE_PAREN;
                this.priority = 3;
                break;
            case "(":
                this.type = OperatorType.OPEN_PAREN;
                this.priority = 0;
                break;
        }
    }
    
    public String getOperator(){
        return this.operator;
    }
    
    public int getPriority(){
        return this.priority;
    }
    
    public OperatorType getType(){
        return this.type;
    }
}
