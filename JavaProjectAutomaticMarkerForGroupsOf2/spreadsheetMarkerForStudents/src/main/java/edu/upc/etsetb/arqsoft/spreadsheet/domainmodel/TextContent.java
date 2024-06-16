/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.arqsoft.spreadsheet.domainmodel;

import edu.upc.etsetb.arqsoft.spreadsheet.auxiliar.Recomputator;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.CircularDependencyException;
import edu.upc.etsetb.arqsoft.spreadsheet.exceptions.TokenWrittenIncorrectlyException;
import edu.upc.etsetb.arqsoft.spreadsheet.exceptions.WrongSyntaxException;
import edu.upc.etsetb.arqsoft.spreadsheet.storage.ContentVisitor;

/**
 *
 * @author oscar
 */
public class TextContent extends Content {
    
    public TextContent(String text) {
        this.content = text;
        if(text.isEmpty()){
            this.value = new Number(0);
        }
        else{
            this.value = new Text(text);
        }
    }

    @Override
    public String getContent() {
        return this.value.getTextValue();
    }

    @Override
    public void accept(Recomputator visitor) throws CircularDependencyException, WrongSyntaxException, TokenWrittenIncorrectlyException {
        visitor.visitText(this.content);    
    }
}
