/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package edu.upc.etsetb.arqsoft.spreadsheet.domainmodel;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author oscar
 */
public abstract class Function extends Operand implements Argument{
    protected List<Double> args = new LinkedList<>();
    protected Number result;
    public static final String SUM = "SUM", MAX = "MAX", MIN = "MIN", PROMEDIO = "PROMEDIO";
    
    public Function(LinkedList<Argument> args){
        this.result = new Number(Double.POSITIVE_INFINITY);
        this.args = new LinkedList<>();
        for(Argument arg : args){
            this.args.addAll(arg.getValue());
        }
    }
    public abstract Number compute();
    
    @Override
    public double getNumericValue(){
        return this.compute().getNum();
    }
    
    @Override 
     public List<Double> getValue() {
        List<Double> aux = new LinkedList<>();
        if(this.result.getNumericValue() == Double.POSITIVE_INFINITY){
            aux.add(this.compute().getNumericValue());
            return aux;
        }
        aux.add(this.result.getNumericValue());
        return aux;
     }  
}
