/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.arqsoft.spreadsheet.auxiliar;

import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Cell;
import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Number;
import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Text;
import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Value;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.CircularDependencyException;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.ContentException;
import edu.upc.etsetb.arqsoft.spreadsheet.exceptions.TokenWrittenIncorrectlyException;
import edu.upc.etsetb.arqsoft.spreadsheet.exceptions.WrongSyntaxException;
import java.util.List;

/**
 *
 * @author oscar
 */
public class Recomputator {
    
    public Value visitFormula(String content, List<Cell> cells) throws TokenWrittenIncorrectlyException, WrongSyntaxException, CircularDependencyException{
        FormulaComputator computator = new FormulaComputator(cells);
        return computator.compute(content);
    }
    public Value visitNumerical(String number) throws ContentException{
        double num;
        try{
        num = Double.parseDouble(number);
        }catch(NumberFormatException ex){
            throw new ContentException(ex.getMessage());
        }
        return new Number(num);
    }
    public Value visitNumerical(double number){
        return new Number(number);
    }
    public Value visitText(String content){
        if(content.isEmpty()){
            return new Number(0) {};
        }
        else{
            return new Text(content);
        }
    }
}
