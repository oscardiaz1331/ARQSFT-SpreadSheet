/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.arqsoft.spreadsheet.domainmodel;

import edu.upc.etsetb.arqsoft.spreadsheet.entities.CircularDependencyException;
import edu.upc.etsetb.arqsoft.spreadsheet.exceptions.TokenWrittenIncorrectlyException;
import edu.upc.etsetb.arqsoft.spreadsheet.exceptions.WrongSyntaxException;
import edu.upc.etsetb.arqsoft.spreadsheet.storage.ContentVisitor;
import edu.upc.etsetb.arqsoft.spreadsheet.auxiliar.FormulaComputator;
import edu.upc.etsetb.arqsoft.spreadsheet.auxiliar.Recomputator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author oscar
 */
public class Formula extends Content {
    List<FormulaComponent> components;
    String content;
    List<Cell> cells;
    
    public Formula(String content, List<FormulaComponent> components, List<Cell> cells) throws CircularDependencyException, TokenWrittenIncorrectlyException, WrongSyntaxException{
        this.content = content;
        this.components = components;
        //Todo, assign value
        //this.value = value;
        FormulaComputator computator = new FormulaComputator(cells);
        this.value = computator.compute(content);
        this.cells= cells;
    }
    public void setFormulaComponents(List<FormulaComponent> components){
        this.components = components;
    }

    @Override
    public String getContent() {
        return this.content.replaceAll(";", ",");
    }
    
    @Override 
    public void accept(Recomputator visitor) throws CircularDependencyException, WrongSyntaxException, TokenWrittenIncorrectlyException{
        this.value = visitor.visitFormula(content, this.cells);
    }
    
    
}
