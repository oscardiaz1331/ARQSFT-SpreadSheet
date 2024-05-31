/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.arqsoft.spreadsheet.domainmodel;

import java.util.LinkedList;

/**
 *
 * @author oscar
 */
public class MAX extends Function {
    public MAX(LinkedList<Argument> args) {
        super(args);
    }
    
    @Override
    public Number compute(){
        double aux = this.args.getFirst();
        for(double argValue : this.args){
            if(aux < argValue){
                aux = argValue;
            }
        }
        this.result = new Number(aux);
        return this.result;
    }
}
