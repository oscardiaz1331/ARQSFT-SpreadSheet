/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.arqsoft.spreadsheet.domainmodel;

import edu.upc.etsetb.arqsoft.spreadsheet.auxiliar.Recomputator;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.CircularDependencyException;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.ContentException;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.NoNumberException;
import edu.upc.etsetb.arqsoft.spreadsheet.exceptions.TokenWrittenIncorrectlyException;
import edu.upc.etsetb.arqsoft.spreadsheet.exceptions.WrongSyntaxException;
import edu.upc.etsetb.arqsoft.spreadsheet.storage.ContentVisitor;

/**
 *
 * @author oscar
 */
public abstract class Content {
    protected Value value;
    protected String content;
    
    public double getNumericValue() throws NoNumberException{
        return this.value.getNumericValue();
    }
    
    public String getTextValue(){
        return this.value.getTextValue();
    }
    
    public abstract String getContent();

    public void setContent(String content) {
        this.content = content;
    }
    
    public Value getValue(){
        return this.value;
    }
    public void setValue(Value value){
        this.value = value;
    }
    
    public static Content fromString(String contentStr) throws WrongSyntaxException {
        if (contentStr.matches("-?\\d+(\\.\\d+)?")) {
            return new NumericalContent(new Number(Double.parseDouble(contentStr)));
        } else {
            return new TextContent(contentStr);
        }
    }
    
    public abstract void accept(Recomputator visitor) throws CircularDependencyException, WrongSyntaxException, TokenWrittenIncorrectlyException, ContentException;

}
