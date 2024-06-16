/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.arqsoft.spreadsheet.domainmodel;

import edu.upc.etsetb.arqsoft.spreadsheet.entities.CircularDependencyException;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.ContentException;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.NoNumberException;
import edu.upc.etsetb.arqsoft.spreadsheet.exceptions.TokenWrittenIncorrectlyException;
import edu.upc.etsetb.arqsoft.spreadsheet.exceptions.WrongSyntaxException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author oscar
 */
public class MIN extends Function{
    
    public MIN(LinkedList<Argument> args) throws NoNumberException, TokenWrittenIncorrectlyException, WrongSyntaxException, CircularDependencyException, ContentException {
        super(args);
    }
    
    @Override
    public Number compute(){
        double aux = this.args.getFirst();
        for(double argValue : this.args){
            if(aux > argValue){
                aux = argValue;
            }
        }
        this.result = new Number(aux);
        return this.result;
    }
}
