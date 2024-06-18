/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.arqsoft.spreadsheet.domainmodel;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author oscar
 */
public class Number extends Operand implements Argument, Value {
    private double num;
    
    public Number(double num){
        this.num = num;
    }
    
    public double getNum(){
        return this.num;
    }
    public void setNum(double num){
        this.num = num;
    }

    @Override
    public double getNumericValue() {
        return this.num;
    }

    @Override
    public String getTextValue() {
        if(this.num % 1 == 0){
            return String.valueOf((int)num);
        }
        return String.valueOf(this.num);
    }

    @Override
    public List<Double> getValue() {
        List<Double> aux = new LinkedList<>();
        aux.add(this.num);
        return aux;
    }

    
}
