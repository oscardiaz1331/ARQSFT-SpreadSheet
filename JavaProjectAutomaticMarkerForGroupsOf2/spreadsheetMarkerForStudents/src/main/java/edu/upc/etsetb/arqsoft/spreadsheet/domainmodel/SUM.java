/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.arqsoft.spreadsheet.domainmodel;

import java.util.List;

/**
 *
 * @author oscar
 */
public class SUM implements Function {

    List<Argument> args;
    
    public SUM(List<Argument> args){
        this.args = args;
    }
    
    @Override
    public Number compute() {
        Number aux = new Number(0);
        //for (Argument arg : this.args) {
            //TODO
        //}
        return aux;
    }
    
}
