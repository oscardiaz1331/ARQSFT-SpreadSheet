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
    
    public Operator(String operator){
        this.operator = operator;
    }
    
    public String getOperator(){
        return this.operator;
    }
}
