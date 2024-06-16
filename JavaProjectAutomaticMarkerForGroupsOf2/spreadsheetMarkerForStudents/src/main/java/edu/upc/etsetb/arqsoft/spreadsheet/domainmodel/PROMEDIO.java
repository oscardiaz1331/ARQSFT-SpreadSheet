/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.arqsoft.spreadsheet.domainmodel;

import edu.upc.etsetb.arqsoft.spreadsheet.entities.NoNumberException;
import java.util.LinkedList;

/**
 *
 * @author oscar
 */
public class PROMEDIO extends Function {
    public PROMEDIO(LinkedList<Argument> args) throws NoNumberException {
        super(args);
    }
    
    @Override
    public Number compute(){
        double aux = 0;
        for(double argValue : this.args){
            aux += argValue;
        }
        aux /= this.args.size();
        this.result = new Number(aux);
        return this.result;
    }
}
